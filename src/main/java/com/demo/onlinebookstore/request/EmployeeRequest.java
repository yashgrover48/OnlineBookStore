package com.demo.onlinebookstore.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest implements Serializable {

    private String firstName;

    private String lastName;

    private String emailId;
}
