package com.lab4.demo.user.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
@Getter
@SuperBuilder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}