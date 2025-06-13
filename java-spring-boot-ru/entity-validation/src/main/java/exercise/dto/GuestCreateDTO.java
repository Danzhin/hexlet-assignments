package exercise.dto;

// BEGIN
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank(message = "Имя пользователя name должно быть не пустым")
    private String name;

    @Email(message = "Электронная почта email должна быть валидной")
    @Column(unique = true)
    private String email;

    @Pattern(
            regexp = "^\\+\\d{11,13}$",
            message = "Номер телефона phoneNumber должен начинаться с символа + и содержать от 11 до 13 цифр"
    )
    private String phoneNumber;

    @Pattern(regexp = "^\\d{4}$",
            message = "Номер клубной карты clubCard должен состоять ровно из четырех цифр"
    )
    private String clubCard;

    @Future(message = "Срок действия клубной карты cardValidUntil должен быть еще не истекшим")
    private LocalDate cardValidUntil;
}
// END
