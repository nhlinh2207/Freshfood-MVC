package com.linh.controller.staff;

import com.linh.model.Cart;
import com.linh.service.ICartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "Controller_Of_Staff")
@RequestMapping(value = "/freshfood")
@AllArgsConstructor
@Slf4j
public class HomeController {

    private final ICartService cartService;

    @GetMapping("/staff/trang-chu")
    public String home(){
        return "staff/trang-chu";
    }

    @GetMapping("/staff/don-hang-chua-giao")
    public String unsentCartList(){
        return "staff/don-hang-chua-giao";
    }

    @GetMapping("/staff/don-hang-da-giao")
    public String sentCartList(){return "/staff/don-hang-da-giao";}

    @GetMapping("/staff/unsent-order-detail")
    public String unsentDetail(@RequestParam("id") Integer id, Model model){
        Cart cart = cartService.findOneById(id);
        model.addAttribute("bill", cartService.findOneById(id));
        return "/staff/chi-tiet-don-hang-chua-giao";
    }

    @GetMapping("/staff/sent-order-detail")
    public String sentDetail(@RequestParam("id") Integer id, Model model){
        Cart cart = cartService.findOneById(id);
        model.addAttribute("bill", cartService.findOneById(id));
        return "/staff/chi-tiet-don-hang-da-giao";
    }
}
