package com.linh.api.staff;

import com.linh.model.Cart;
import com.linh.model.CartItem;
import com.linh.model.Message;
import com.linh.model.User;
import com.linh.service.ICartItemService;
import com.linh.service.ICartService;
import com.linh.service.IMessageService;
import com.linh.service.IUserService;
import com.linh.utils.MoneyFormatUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@AllArgsConstructor
@Slf4j
public class CartStaffAPI {

    private final ICartService cartService;
    private final IUserService userService;
    private final ICartItemService cartItemService;
    private final IMessageService messageService;

    @GetMapping(value = "/freshfood/staff/cart/all/{type}")
    public List<Map<String, String>> findAllOrderByType(@PathVariable String type){
        User currentUser = userService.findById(userService.getCurrentLoginUser().getId());
        List<Map<String, String>> maps = new ArrayList<Map<String,String>>();
        List<Cart> carts = cartService.findByStaff(type, currentUser);
        for (int i = 0; i < carts.size(); i++) {
            Map<String, String> cartItems = new LinkedHashMap<String, String>();
            cartItems.put("name", carts.get(i).getReceiverName());
            cartItems.put("phone", carts.get(i).getReceiverPhoneNumber());
            cartItems.put("bill-id", carts.get(i).getId()+"");
            List<CartItem> cartDetails =  cartItemService.findByCart(carts.get(i));
            Integer tongtien = 0;
            for (CartItem item: cartDetails) {
                tongtien += item.getProduct().getPrice() * item.getQuantity();
            }
            cartItems.put("tongtien", MoneyFormatUtil.format(tongtien));
            cartItems.put("time", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(carts.get(i).getOrderTime()));
            maps.add(cartItems);
        }
        return maps;
    }

    @PutMapping(value = "/freshfood/staff/cart/updateStatus/{cartId}")
    public String updateOrderStatus(@PathVariable String cartId){
        Cart cart = cartService.findOneById(Integer.parseInt(cartId));
        cart.setStatus("SENT");
        cart.setDeliverTime(new Date());
        cartService.save(cart);

        // Create Success Message for user
        Message message = messageService.save(Message.builder()
                .content("Đơn hàng "+cartId+" đã được giao thành công.")
                .title("Giao hàng thành công")
                .status("UNSEEN")
                .createTime(new Date())
                .cartId(cart.getId())
                .user(cart.getUser())
                .build());

        return "OK";
    }
}
