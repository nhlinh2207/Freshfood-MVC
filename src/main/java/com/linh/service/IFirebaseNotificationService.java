package com.linh.service;

import com.linh.dto.request.PushNotificationRequest;
import com.linh.model.TokenDevice;
import com.linh.model.User;

public interface IFirebaseNotificationService {

    String pushNotificationToWeb(PushNotificationRequest pushNotification);
    TokenDevice getTokenDeviceByUser(User user);
}
