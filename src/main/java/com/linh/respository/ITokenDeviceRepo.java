package com.linh.respository;

import com.linh.model.TokenDevice;
import com.linh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenDeviceRepo extends JpaRepository<TokenDevice, Integer> {

    TokenDevice findByUser(User user);
}
