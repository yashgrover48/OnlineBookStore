package com.demo.onlinebookstore.request;

import lombok.*;

import java.util.Set;

@Data
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String username;

    private String password;

    private String email;

    private Set<String> roles;
}
