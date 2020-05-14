package com.demo.onlinebookstore.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String jwt;

    private Long id;

    private String username;

    private String email;

    private List<String> roles;
}
