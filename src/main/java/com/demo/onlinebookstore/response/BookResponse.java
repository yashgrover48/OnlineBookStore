package com.demo.onlinebookstore.response;

import com.demo.onlinebookstore.entity.BookCategory;

import java.math.BigDecimal;
import java.util.UUID;

public interface BookResponse {

    UUID getId();

    String getName();

    String getDescription();

    String getSku();

    BigDecimal getUnitPrice();

    String getImageUrl();

    boolean getActive();

    Integer getUnitsInStock();

    BookCategoryResponse getBookCategory();
}
