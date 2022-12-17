package com.order.ecommerce.service.address;

import com.order.ecommerce.dto.request.AddressDto;
import com.order.ecommerce.mapper.AddressBuilder;
import com.order.ecommerce.model.Address;
import com.order.ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address createAddress(AddressDto addressDto) {
        return this.addressRepository
                .save(AddressBuilder
                        .buildAddress(addressDto));
    }
}
