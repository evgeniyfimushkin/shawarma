package edu.evgen.shawarma.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ShawarmaOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt = new Date();

    @NotBlank(message = "Необходимо ввести имя")
    private String deliveryName;
    @NotBlank(message = "Необходимо ввести улицу")
    private String deliveryStreet;
    @CreditCardNumber(message = "Неверный номер карты")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(\\d{4})$", message = "Формат даты ДД.MM" +
            ".ГГГГ")
    private String ccExpiration;
    @Digits(integer = 3,fraction = 0,message = "Неверны код")
    private String ccCVV;
    @NotEmpty(message = "Корзина пуста")
    @OneToMany(cascade = CascadeType.ALL)// При удалении заказа, все shawarmas тоже будут удалены
    private List<Shawarma> shawarmas = new ArrayList<>();

    public void addShawarma(Shawarma shawarma) {
        this.shawarmas.add(shawarma);
    }
}
