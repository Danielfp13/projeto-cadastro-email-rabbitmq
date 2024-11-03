package com.github.Danielfp13.emailsender.dtos;

import com.github.Danielfp13.emailsender.entities.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String cpf;

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
