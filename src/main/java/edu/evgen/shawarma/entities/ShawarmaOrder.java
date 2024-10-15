package edu.evgen.shawarma.entities;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Document
public class ShawarmaOrder implements Serializable {

    // Уникальный идентификатор версии для совместимости сериализации
    private static final long serialVersionUID = 1L;
    @Id
    private String id;

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

    @Digits(integer = 3, fraction = 0, message = "Неверны код")
    private String ccCVV;

    @NotEmpty(message = "Корзина пуста")
    private List<Shawarma> shawarmas = new ArrayList<>();

    public void addShawarma(Shawarma shawarma) {
        this.shawarmas.add(shawarma);
    }
}
