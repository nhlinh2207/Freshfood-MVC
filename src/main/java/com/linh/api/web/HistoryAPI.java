package com.linh.api.web;

import com.linh.model.Cart;
import com.linh.model.User;
import com.linh.service.ICartService;
import com.linh.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class HistoryAPI {

    private final IUserService userService;
    private final ICartService cartService;

    @GetMapping(value = "/freshfood/history/all")
    public List<Map<String, String>> getByUser(){

        SimpleDateFormat smf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        User user = userService.getCurrentLoginUser();
        List<Map<String, String>> result = new ArrayList<Map<String,String>>();
        List<Cart> carts = cartService.findByUser(user);
        for (Cart c : carts) {
            if (c.getOrderTime() != null){
                Map<String, String> item = new HashMap<>();
                item.put("id", c.getId()+"");
                item.put("orderTime", smf.format(c.getOrderTime()));
                item.put("status", c.getStatus().equals("SENT") ? "Đã giao hàng" : "Đang giao");
                item.put("deliveryTime", c.getDeliverTime() == null ? "" : smf.format(c.getDeliverTime()));
                result.add(item);
            }
        }
        return result;
    }

//    @DeleteMapping(value = "/freshfood/message/delete/{id}")
//    public String deleteMessage(@PathVariable("id") Integer id){
//        try {
//            messageService.delete(id);
//            return "OK";
//        }catch (Exception e){
//            return "Failed";
//        }
//    }
}
