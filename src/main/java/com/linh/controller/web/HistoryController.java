package com.linh.controller.web;

import com.linh.model.Cart;
import com.linh.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/freshfood")
@AllArgsConstructor
public class HistoryController {

    private final ICartService cartService;

    @GetMapping(value = "/history")
    public ModelAndView cartHistory() {
        return new ModelAndView("web/lich-su-mua-hang");
    }

    @GetMapping(value = "/history-detail/{id}")
    public ModelAndView cartDetail(@PathVariable Integer id) {
        Cart cart = cartService.findOneById(id);
        ModelAndView mv = new ModelAndView("web/lich-su-chi-tiet");
        mv.addObject("cart", cart);
        return mv;
    }
}
