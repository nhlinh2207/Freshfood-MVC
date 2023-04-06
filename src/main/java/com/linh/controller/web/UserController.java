package com.linh.controller.web;

import com.linh.dto.request.RegistryRequest;
import com.linh.model.City;
import com.linh.model.User;
import com.linh.service.ICityService;
import com.linh.service.ICountryService;
import com.linh.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/freshfood")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final IUserService userService;
    private final ICountryService countryService;
    private final ICityService cityService;

    @GetMapping(path = "/dang-nhap")
    public ModelAndView getLoginPage(){
        return new ModelAndView("web/dang-nhap");
    }

    @GetMapping(path = "/dang-ky")
    public ModelAndView getRegistryPage(){
        ModelAndView mv = new ModelAndView("web/dang-ki");
        RegistryRequest registryRequest = new RegistryRequest();
        mv.addObject("registryRequest", registryRequest);
        mv.addObject("countryList", countryService.findAll());
        return mv;
    }

    @PostMapping(path = "/dang-ky")
    public ModelAndView registry(@ModelAttribute("registryRequest") RegistryRequest registryRequest,
                                 BindingResult bindingResult ){
        ModelAndView mv = null;
        if(userService.existsByEmail(registryRequest.getEmail())) {
            log.info("Email đã tồn tại !");
            bindingResult.addError(new FieldError("registryRequest", "email", "email đã tồn tại !"));
            mv = new ModelAndView("web/dang-ki");
            mv.addObject("registryRequest", registryRequest);
            mv.addObject("countryList", countryService.findAll());
        }else{
            try {
                log.info("Thực hiện đăng kí");
                User registriedUser = userService.registry(registryRequest);
                mv = new ModelAndView("redirect:/freshfood/dang-nhap");
                mv.addObject("user", registriedUser);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return mv;
    }

    @GetMapping(path = "/quen-pass")
    public ModelAndView getForgetPassPage(){
        return new ModelAndView("web/forgetPass");
    }

    @GetMapping(path = "/change-password/{token}")
    public ModelAndView getChangePassPage(@PathVariable String token){
        ModelAndView mv = new ModelAndView("web/changePass");
        mv.addObject("token", token);
        return mv;
    }

    @GetMapping(path = "/doi-pass")
    public ModelAndView getChangeCurrentPassPage(){
        ModelAndView mv = new ModelAndView("web/changeCurrentPass");
        User currentUser = userService.getCurrentLoginUser();
        mv.addObject("user", currentUser);
        return mv;
    }

    @GetMapping(path = "/profile")
    public ModelAndView getProfilePage(){
        ModelAndView mv = new ModelAndView("web/profile");
        User currentUser = userService.getCurrentLoginUser();
        mv.addObject("user", currentUser);
        mv.addObject("countryList", countryService.findAll());
        City city = cityService.findById(currentUser.getAddress().getCityId());
        mv.addObject("city", city);
        return mv;
    }
}
