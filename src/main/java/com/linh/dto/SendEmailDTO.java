package com.linh.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SendEmailDTO {
    private String from;
    private String to;
    private String subject;
    @Data
    @Getter
    @Setter
    @Builder
    public static class ContentEmail{
        private String customerName;
        private String customerEmail;
        private String customerPhone;
        private String roomName;
        private String roomTypeName;
        private String checkInDate;
        private String checkOutDate;
        private String roomPrice;
        private String totalPrice;
    }
}
