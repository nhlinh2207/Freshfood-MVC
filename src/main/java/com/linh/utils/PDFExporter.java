package com.linh.utils;

import com.linh.model.Cart;
import com.linh.model.CartItem;
import com.linh.model.Product;
import com.linh.model.User;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PDFExporter {

    public static void export(HttpServletResponse response, Cart bookingForm) throws IOException {
        User account = bookingForm.getUser();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        //Set font
        Font fontHeader = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 20);
        Font fontTitle = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18);
        Font fontDetail = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16);
        Font fontTotal = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18);
        fontTotal.setColor(Color.RED);

        // Set Header
        Paragraph header = new Paragraph("Hóa đơn mua hàng "+bookingForm.getId(), fontHeader);
        header.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(header);

        //Set customer info
        Paragraph customerTitle = new Paragraph("Thông tin khách mua hàng\n", fontTitle);
        customerTitle.setAlignment(Paragraph.ALIGN_LEFT);
        customerTitle.setSpacingBefore(30);
        document.add(customerTitle);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Paragraph customerInfo = new Paragraph();
        customerInfo.setFont(fontDetail);
        customerInfo.setAlignment(Paragraph.ALIGN_LEFT);
        customerInfo.setSpacingBefore(15);
        customerInfo.add("      Họ và tên:       "+(account == null ? "" : account.getFullName())+"\n\n");
        customerInfo.add("      Họ và tên người nhận hàng:       "+bookingForm.getReceiverName()+"\n\n");
        customerInfo.add("      Email:           "+(account == null ? bookingForm.getReceiverEmail() : account.getEmail())+"\n\n");
        customerInfo.add("      Số điện thoại:   "+bookingForm.getReceiverPhoneNumber()+"\n\n");
        customerInfo.add("     Thời gian đặt hàng:   "+simpleDateFormat.format(bookingForm. getOrderTime())+"\n\n");
        customerInfo.add("     Thời gian nhận hàng:   "+simpleDateFormat.format(bookingForm.getDeliverTime())+"\n\n");
        document.add(customerInfo);

        //Set room price
        int totalRoomPrice = 0;
        for (CartItem d : bookingForm.getCartItems()){
            totalRoomPrice += d.getQuantity()*d.getProduct().getPrice();
        }
        Paragraph roomPrice = new Paragraph("Tổng tiền mua hàng:  "+MoneyFormatUtil.format(totalRoomPrice), fontTitle);
        roomPrice.setAlignment(Paragraph.ALIGN_LEFT);
        roomPrice.setSpacingBefore(30);
        document.add(roomPrice);

        List<CartItem> cartDetails  = bookingForm.getCartItems();
        Paragraph serviceFeeInfo = new Paragraph();
        serviceFeeInfo.setFont(fontDetail);
        serviceFeeInfo.setAlignment(Paragraph.ALIGN_LEFT);
        serviceFeeInfo.setSpacingBefore(15);
        for (CartItem item : cartDetails){
            Product fastFood = item.getProduct();
            int foodPrice =item.getQuantity()*fastFood.getPrice();
            serviceFeeInfo.add("       "+fastFood.getName()+" :   "+fastFood.getPriceCurrency()+"  x"+item.getQuantity()+"    =   "+MoneyFormatUtil.format(foodPrice)+" \n\n");
        }
        document.add(serviceFeeInfo);

        //Set total to be paid
        Paragraph totalFee = new Paragraph("Tổng tiền đã trả:  "+MoneyFormatUtil.format(totalRoomPrice), fontTotal);
        totalFee.setAlignment(Paragraph.ALIGN_LEFT);
        totalFee.setSpacingBefore(30);
        document.add(totalFee);

        document.close();
    }
}
