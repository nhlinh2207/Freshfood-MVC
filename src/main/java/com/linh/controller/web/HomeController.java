package com.linh.controller.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.linh.model.Cart;
import com.linh.model.CartItem;
import com.linh.model.Product;
import com.linh.service.*;
import com.linh.utils.MoneyFormatUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linh.model.User;

@Controller(value = "Controller_Of_Web")
@RequestMapping("/freshfood")
@AllArgsConstructor
public class HomeController {

	private final ICategoryService categoryService;
	private final IProductService fastFoodService;
	private final IUserService userService;
	private final ICartService cartService;
	private final ICountryService countryService;
	
    @GetMapping(value = "/trang-chu")
    public ModelAndView homePage() {
    	ModelAndView mv = new ModelAndView("web/trang-chu");
    	mv.addObject("category", categoryService.findAll());
    	mv.addObject("hoaQuaNhapKhau", fastFoodService.findByCategory(categoryService.findById(1)));
    	mv.addObject("rauCuSach", fastFoodService.findByCategory(categoryService.findById(2)));
    	mv.addObject("traiCayTuoi", fastFoodService.findByCategory(categoryService.findById(3)));
    	mv.addObject("haiSanTuoi", fastFoodService.findByCategory(categoryService.findById(4)));
    	mv.addObject("thitTuoi", fastFoodService.findByCategory(categoryService.findById(5)));
    	mv.addObject("doUong", fastFoodService.findByCategory(categoryService.findById(6)));
    	return mv;
    }

    @GetMapping(path = "/gioi-thieu")
    public ModelAndView intro() {
    	return new ModelAndView("web/gioi-thieu");
    }
    
    @GetMapping(value = "/san-pham")
    public ModelAndView product(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView("web/san-pham");
 	    String search = (request.getParameter("search") == null) ? null : request.getParameter("search");
 	    int currentPage = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));
 	    String sortBy = (request.getParameter("sort") == null)? "creTime" : request.getParameter("sort");
 	    String sortDir = (request.getParameter("sortdir") == null) ? "asc" : request.getParameter("sortdir");
        Integer cateId = (request.getParameter("id") == null) ? null : Integer.parseInt(request.getParameter("id"));
 	    
 	    List<Product> productList;
        Page<Product> pages = fastFoodService.findAll(currentPage, search, sortBy, sortDir);
        if (cateId == null) {
            productList = pages.getContent();
		}else {
	        productList = fastFoodService.findByCategory(categoryService.findById(cateId));
	        mv.addObject("cateName", categoryService.findById(cateId).getName());
		}
        
        int totalPages = pages.getTotalPages();
        long totalItems = pages.getTotalElements();
        
        String sortName = "";
        if(sortBy.equals("creTime")) sortName = "Mặc định";
        else if(sortBy.equals("name") && sortDir.equals("asc")) sortName = "Tên (A-Z)";
        else if(sortBy.equals("name") && sortDir.equals("desc")) sortName = "Tên (Z-A)";
        else if(sortBy.equals("price") && sortDir.equals("asc")) sortName = "Giá (Thấp-Cao)";
        else if(sortBy.equals("price") && sortDir.equals("desc")) sortName = "Giá (Cao-Thấp)";


        mv.addObject("search", search);
        mv.addObject("sortBy", sortBy);
        mv.addObject("sortDir", sortDir);
        mv.addObject("sortName", sortName);
        mv.addObject("totalItems", totalItems);
        mv.addObject("totalPages", totalPages);
        mv.addObject("currentPage", currentPage);
    	mv.addObject("productList", productList);
    	mv.addObject("category", categoryService.findAll());
    	return mv;
    }

    @GetMapping(value = "/chi-tiet-san-pham")
    public ModelAndView productDetail(@RequestParam("id") Integer id){
         ModelAndView mv = new ModelAndView("web/check");
         Product p = fastFoodService.findById(id);
         List<Product> splq = fastFoodService.findByCategory(p.getCategory());
         mv.addObject("product", p);
         mv.addObject("splq",splq);
         mv.addObject("user", userService.getCurrentLoginUser());
         return mv;
    }
    
    @GetMapping(value = "/cartitems")
    public ModelAndView allCartItem(HttpServletRequest request) {
    	User user = userService.getCurrentLoginUser();
		List<Cart> carts;
		Cart currentCart;
    	List<CartItem> cartDetails = new ArrayList<>();
    	int tongtien = 0;
    	if(user != null) {
			carts = cartService.findByUser(user);
			currentCart = carts.get(carts.size()-1);
    		cartDetails = currentCart.getCartItems();
    	}else {
    		HttpSession session = request.getSession();
    		Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
    		if (cart == null) {
				cart = new LinkedHashMap<>();
			}
    		int id = 0;
    		for (Entry<Integer, Integer> item: cart.entrySet()) {
				CartItem c = new CartItem();
				c.setProduct(fastFoodService.findById(item.getKey()));
				c.setQuantity(item.getValue());
				c.setCart(null);
				c.setId(id);
				cartDetails.add(c);
				id++;
			}
    	}
    	for (CartItem item : cartDetails){
    		tongtien += item.getProduct().getPrice() * item.getQuantity();
		}
    	ModelAndView mv = new ModelAndView("web/cartitems");
    	mv.addObject("cartitems", cartDetails);
    	mv.addObject("tongtien", MoneyFormatUtil.format(tongtien));
    	return mv;
    }
    
    @GetMapping(value = "/checkout")
    public ModelAndView checkout() {
    	ModelAndView mv = new ModelAndView("web/checkout");
    	//mv.addObject("username", userservice.getLoggingInUsser().getFullname());
    	User user = userService.getCurrentLoginUser();
    	if (user != null) {
			mv.addObject("username", user.getFullName());
		}
    	return mv;
    }
    
    @GetMapping(value = "/thanh-toan")
    public String payment(Model model){
    	model.addAttribute("country", countryService.findAll());
    	return "web/thanh-toan";
    }
    
    @GetMapping(value = "/lien-he")
    public String contact() {
    	return "web/lien-he";
    }

}
