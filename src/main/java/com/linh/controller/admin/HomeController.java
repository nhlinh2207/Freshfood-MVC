package com.linh.controller.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linh.model.Cart;
import com.linh.model.Product;
import com.linh.respository.ICartRepo;
import com.linh.service.ICartService;
import com.linh.service.IUserService;
import com.linh.utils.MoneyFormatUtil;
import com.linh.utils.PDFExporter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.linh.service.ICategoryService;
import com.linh.service.IProductService;
import com.linh.utils.FileUploadUtils;

@Controller(value = "Controller_Of_Admin")
@RequestMapping(value = "/freshfood")
@AllArgsConstructor
@Slf4j
public class HomeController {

	private final IProductService fastFoodService;
	private final ICategoryService categoryService;
	private final ICartService cartService;
	private final IUserService userService;
	private final ICartRepo cartRepo;
	
	@RequestMapping(value = "/admin/trang-chu", method = RequestMethod.GET)
	public ModelAndView trangchu() throws ParseException {
		ModelAndView mv = new ModelAndView("admin/trang-chu-admin");
		SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		int currentMonth = cal.get(Calendar.MONTH)+1;
		int currentYear = cal.get(Calendar.YEAR);
		Date startDate = smf.parse("01/01/"+currentYear+" 00:00:00");
		Date endDate = smf.parse("31/12/"+currentYear+" 23:59:59");
		List<Cart> carts = cartRepo.findByYear(startDate, endDate);
		int totalMoneyInYear = 0, totalMoneyInMonth = 0;
		for (Cart c : carts){
			totalMoneyInYear += c.getTotalPrice();
			cal.setTime(c.getOrderTime());
			int month = cal.get(Calendar.MONTH)+1;
			if (month == currentMonth)
				totalMoneyInMonth += c.getTotalPrice();
		}

		int totalUser = userService.findAll().size();
		mv.addObject("totalMoneyInYear", MoneyFormatUtil.format(totalMoneyInYear));
		mv.addObject("totalMoneyInMonth", MoneyFormatUtil.format(totalMoneyInMonth));
		mv.addObject("totalUser", totalUser);
		return mv;
	}
	
	@GetMapping(value = "/admin/san-pham")
	public String sanpham(Model model, 
			              @RequestParam(name = "page", required = false) Integer pageNumber,
			              @RequestParam(name = "search", required = false) String search) {
		int currentPage = (pageNumber == null) ? 1: pageNumber;
		Page<Product> pages = fastFoodService.findAll(currentPage, search, "creTime", "asc");
		List<Product> productList = pages.getContent();
		model.addAttribute("productList", productList);
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalItems", pages.getTotalElements());
        model.addAttribute("currentPage", currentPage);
        return "admin/san-pham";
	}
	
	@RequestMapping(value = "/admin/them-moi", method = RequestMethod.GET)
	public ModelAndView themmoi(@RequestParam(value = "id", required = false) Integer id) {
		ModelAndView mv = new ModelAndView("admin/them-moi");
		Product product = (id != null) ? fastFoodService.findById(id) : new Product();
		mv.addObject("product", product);
		mv.addObject("category", categoryService.findAll());
		return mv;
	}
	
	@RequestMapping(value = "/admin/them-moi", method = RequestMethod.POST)
	public ModelAndView saveProduct(@ModelAttribute("product") Product productEntity,
			                        @RequestParam("main-img") MultipartFile mainMultipartFile,
			                        @RequestParam("ex-img") MultipartFile[] extraMultipartFiles,
			                        HttpServletRequest request) throws IOException {
		ModelAndView mv = new ModelAndView("redirect:/freshfood/admin/san-pham");
		Product newProduct = null;
		
		Integer id = request.getParameter("id").equals("") ? null: Integer.valueOf(request.getParameter("id"));
				
		if(id != null) {
			newProduct = fastFoodService.findById(id);
		}else {
			newProduct = new Product();
		}
		
		newProduct.setCreateTime(new Date());
		newProduct.setName(productEntity.getName());
		newProduct.setPrice(productEntity.getPrice());
		newProduct.setQuantity(productEntity.getQuantity());
	    
		String mainIngName = StringUtils.cleanPath(mainMultipartFile.getOriginalFilename()); //Lấy tên file ảnh đc gửi về
	    if (!mainIngName.equals("")) {
			newProduct.setImage(mainIngName);
		}
	    
	    int c = 1;
		for(MultipartFile m : extraMultipartFiles) {
			String extraImgName = StringUtils.cleanPath(m.getOriginalFilename());
            if (!extraImgName.equals("")) {
            	if(c == 1) newProduct.setExtra_img1(extraImgName);
                else newProduct.setExtra_img2(extraImgName);
            }
            c++;
		}
		
		newProduct.setCategory(categoryService.findById(Integer.parseInt(request.getParameter("category"))));
		Product savedProduct = fastFoodService.save(newProduct);
		//tạo thư mục chứa ảnh
		String uploadDir = "./image/san-pham/"+savedProduct.getId();
		//tạo đối tượng path chứ đg dẫn trong uploadDir
		if (!mainIngName.equals("")) {
			FileUploadUtils.saveFile(mainMultipartFile, mainIngName, uploadDir);
		}
		
		for(MultipartFile m : extraMultipartFiles) {
			String extraImgName = StringUtils.cleanPath(m.getOriginalFilename());
			if (!extraImgName.equals("")) {
				FileUploadUtils.saveFile(m,extraImgName,uploadDir);
			}
		}
		
		return mv;
	}
	
	@GetMapping(value = "/admin/danh-muc")
	public String category() {
		return "/admin/danh-muc-admin";
	}
	
	@GetMapping(value = "/admin/don-hang-chua-giao")
	public String unsentOrder() {
		return "/admin/don-hang-chua-giao";
	}

	@GetMapping(value = "/admin/don-hang-da-giao")
	public String sentOrder() {
		return "/admin/don-hang-da-giao";
	}
	
	@GetMapping(value = "/admin/unsent-order-detail")
	public String unsentOrderDetail(@RequestParam("id") Integer id, Model model) {
		Cart cart = cartService.findOneById(id);
		model.addAttribute("bill", cartService.findOneById(id));
		model.addAttribute("staffs",userService.getFreeStaff());
		if (cart.getStaff() != null){
			model.addAttribute("staffId", cart.getStaff().getId());
		}
		return "/admin/chi-tiet-don-hang-chua-giao";
	}

	@GetMapping(value = "/admin/sent-order-detail")
	public String billdetail(@RequestParam("id") Integer id, Model model) {
		Cart cart = cartService.findOneById(id);
		model.addAttribute("bill", cartService.findOneById(id));
		return "/admin/chi-tiet-don-hang-da-giao";
	}

	@GetMapping(path = "/invoice/export/{id}")
	public void exportPDF(HttpServletResponse response, @PathVariable Integer id) throws IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=invoice.pdf";
		response.setHeader(headerKey, headerValue);
		// Get Booking form
		Cart cart = cartService.findOneById(id);
		PDFExporter.export(response, cart);
	}
}
