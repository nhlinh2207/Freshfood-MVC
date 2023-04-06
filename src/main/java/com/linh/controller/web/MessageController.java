package com.linh.controller.web;

import com.linh.model.Message;
import com.linh.service.ICartService;
import com.linh.service.IMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/freshfood")
@AllArgsConstructor
public class MessageController {

    private final IMessageService messageService;
    private final ICartService cartService;

    @GetMapping(value = "/thong-bao")
    public ModelAndView thongbao() {
        return new ModelAndView("web/thong-bao");
    }

    @GetMapping(value = "/thong-bao-chi-tiet/{id}")
    public ModelAndView thongbaochitiet(@PathVariable Integer id) {

        Message message = messageService.findById(id);
        message.setStatus("SEEN");
        message = messageService.save(message);

        ModelAndView mv = new ModelAndView("web/thong-bao-chi-tiet");
        mv.addObject("bill", cartService.findOneById(message.getCartId()));
        mv.addObject("message", message);
        return mv;
    }
}
