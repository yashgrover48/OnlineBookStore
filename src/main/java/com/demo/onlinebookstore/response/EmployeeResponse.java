package com.demo.onlinebookstore.response;

import java.util.UUID;

public interface EmployeeResponse {

    UUID getId();

    String getFirstName();

    String getLastName();

    String getEmailId();

    boolean getActive();
}
