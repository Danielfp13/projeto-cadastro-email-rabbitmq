package com.github.Danielfp13.emailsender.dtos;

import com.github.Danielfp13.emailsender.entities.User;
import com.github.Danielfp13.emailsender.service.validations.UniqueField;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@UniqueField(fields = {"username", "cpf", "phoneNumber"})
public class UserDTO {
    private Long id;

    @NotBlank(message = "{field.required.username}")
    @Email(message = "{field.invalid.username}")
    private String username;

    @NotBlank(message = "{field.required.password}")
    private String password;

    @NotBlank(message = "{field.required.firstName}")
    private String firstName;

    @NotBlank(message = "{field.required.lastName}")
    private String lastName;

    @CPF(message = "{field.invalid.cpf}")
    @NotBlank(message = "{field.required.cpf}")
    private String cpf;

    @NotBlank(message = "{field.required.phoneNumber}")
    private String phoneNumber;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.cpf = user.getCpf();
        this.phoneNumber = user.getPhoneNumber();
    }
}
