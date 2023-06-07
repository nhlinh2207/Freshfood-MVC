package com.linh.service.imp;

import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.linh.dto.request.PushNotificationRequest;
import com.linh.model.TokenDevice;
import com.linh.model.User;
import com.linh.respository.ITokenDeviceRepo;
import com.linh.service.IFirebaseNotificationService;
import com.linh.utils.PushNotificationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class FirebaseNotificationService implements IFirebaseNotificationService {
    private final ITokenDeviceRepo tokenDeviceRepo;

    @Override
    public String pushNotificationToWeb(PushNotificationRequest pushNotification) {
        String message = "";
        try {
            return sendMessageToWeb(pushNotification);
        } catch (InterruptedException | ExecutionException | FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return message;
    }

    private String sendMessageToWeb(PushNotificationRequest request) throws ExecutionException, InterruptedException, FirebaseMessagingException {
        return PushNotificationUtil.pushNotification(request);
    }

    @Override
    public TokenDevice getTokenDeviceByUser(User user) {
        return tokenDeviceRepo.findByUser(user);
    }
}
