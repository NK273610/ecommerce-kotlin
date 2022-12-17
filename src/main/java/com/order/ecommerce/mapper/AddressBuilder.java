package com.order.ecommerce.mapper;

import com.order.ecommerce.dto.request.AddressDto;
import com.order.ecommerce.model.Address;

public class AddressBuilder {

    public static Address buildAddress(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        return Address.builder().address1(addressDto.getAddress1())
                .address2(addressDto.getAddress2())
                .email(addressDto.getEmail())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .phone(addressDto.getPhone())
                .zip(addressDto.getZip())
                .build();
    }
}
