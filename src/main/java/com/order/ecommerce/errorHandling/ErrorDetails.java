package com.order.ecommerce.errorHandling;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorDetails {

    INTERNAL_SERVER_ERROR(String.format("ORD_ECO_5001"),
            "Internal Server Error",
            "Something went wrong",
            HttpStatus.INTERNAL_SERVER_ERROR),

    BAD_REQUEST(String.format("ORD_ECO_4001"),
            "Invalid Request Body",
                    "Invalid Request Body",
    HttpStatus.BAD_REQUEST),

    BAD_REQUEST_PARAM(String.format("ORD_ECO_4002"),
            "Invalid Request Param",
            "Invalid Request Parameters",
            HttpStatus.BAD_REQUEST),

    BAD_REQUEST_PARAM_MISSING(String.format("ORD_ECO_4003"),
            "Request Param Missing",
            "Request Parameters Missing",
            HttpStatus.BAD_REQUEST),

    NO_PRODUCT_PRESENT(
            "ORD_ECO_4031",
            "No Product Present",
            "Product is not present",
            HttpStatus.FORBIDDEN
    ),
    NO_ORDER_PRESENT(
            "ORD_ECO_4031",
            "No Order Present",
            "Order is not present",
            HttpStatus.FORBIDDEN
    );

    private final String code;
    private final String title;
    private final String message;
    private HttpStatus httpStatus;

    ErrorDetails(String code, String title, String message, HttpStatus httpStatus) {
        this.code = code;
        this.title = title;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
