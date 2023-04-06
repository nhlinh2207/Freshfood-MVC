package com.linh.dto.mapper;

import com.linh.dto.request.RegistryRequest;
import com.linh.model.User;

import java.util.Date;

public class Mapper {

    public static User convertToUserEntity(RegistryRequest req){
        return User.builder()
                .email(req.getEmail())
                .firstName(req.getFirstName())
                .middleName(req.getMiddleName())
                .lastName(req.getLastName())
                .fullName(req.getFirstName()+" "+req.getMiddleName()+" "+req.getLastName())
                .phoneNumber(req.getPhone())
                .age(20)
                .gender("Nam")
                .status("ACTIVE")
                .createTime(new Date())
                .build();
    }
}
