package edu.evgen.shawarma.repositories;

import edu.evgen.shawarma.entities.IngredientRef;
import edu.evgen.shawarma.entities.Shawarma;
import edu.evgen.shawarma.entities.ShawarmaOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class JdbcOrderRepository implements OrderRepository {
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public ShawarmaOrder save(ShawarmaOrder shawarmaOrder) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Shawarma_Order " +
                        "(delivery_name, delivery_street, " +
                        "cc_number, cc_expiration, cc_cvv, placed_at) " +
                        "values (?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);
        shawarmaOrder.setPlacedAt(new Date());

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        shawarmaOrder.getDeliveryName(),
                        shawarmaOrder.getDeliveryStreet(),
                        shawarmaOrder.getCcNumber(),
                        shawarmaOrder.getCcExpiration(),
                        shawarmaOrder.getCcCVV(),
                        shawarmaOrder.getPlacedAt()
                )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        shawarmaOrder.setId(orderId);

        List<Shawarma> shawarmas = shawarmaOrder.getShawarmas();
        int i = 0;
        for (Shawarma shawarma : shawarmas) {
            saveShavarma(orderId, i++, shawarma);
        }
        return shawarmaOrder;
    }

    private long saveShavarma(long orderId, int orderKey, Shawarma shawarma) {
        shawarma.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Shawarma " +
                        "(name, created_at, shawarma_order, shawarma_order_key) " +
                        "values(?,?,?,?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        shawarma.getName(),
                        shawarma.getCreatedAt(),
                        orderId,
                        orderKey
                )
        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long shawarmaId = keyHolder.getKey().longValue();
        shawarma.setId(shawarmaId);

        saveIngredientRefs(shawarmaId, shawarma.getIngredients()
                .stream()
                .map(ingredient -> new IngredientRef(ingredient.getId()))
                .collect(Collectors.toList())
        );

        return shawarmaId;
    }

    private void saveIngredientRefs(long shawarmaId, List<IngredientRef> ingredientsRefs) {
        int key = 0;
        for (IngredientRef ingredientRef : ingredientsRefs) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, shawarma, shawarma_key) " +
                            "values(?,?,?)",
                    ingredientRef.getIngredient(), shawarmaId, key++
            );
        }
    }
}
