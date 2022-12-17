package com.order.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AddressDto {

    @NotEmpty(message = "address1 cannot be null or empty")
    private String address1;

    @NotEmpty(message = "address2 cannot be null or empty")
    private String address2;

    @NotEmpty(message = "city cannot be null or empty")
    private String city;

    @NotEmpty(message = "state cannot be null or empty")
    private String state;

    @NotEmpty(message = "zip cannot be null or empty")
    private String zip;

    @Email(message = "invalid email provided")
    @NotEmpty(message = "email cannot be null or empty")
    private String email;

    @NotEmpty(message = "phone cannot be null or empty")
    private String phone;
}
