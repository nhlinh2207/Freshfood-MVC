package com.linh.utils;

import com.linh.model.*;
import com.linh.respository.ICartItemRepo;
import com.linh.service.ICartService;
import com.linh.service.IProductService;
import com.linh.service.IUserService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelExporter {

    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet xssfSheet1;
    private XSSFSheet xssfSheet2;
    private XSSFSheet xssfSheet3;

    private final IProductService productService;
    private final  IUserService userService;
    private final ICartItemRepo cartItemRepo;
    private final ICartService cartService;

    public ExcelExporter(IProductService productService, IUserService userService, ICartItemRepo cartItemRepo, ICartService cartService){
       xssfWorkbook = new XSSFWorkbook();
       xssfSheet1 = xssfWorkbook.createSheet("Món ăn");
       xssfSheet2 = xssfWorkbook.createSheet("Đơn hàng");
       xssfSheet3 = xssfWorkbook.createSheet("Người dùng");
       this.productService = productService;
       this.userService = userService;
       this.cartItemRepo = cartItemRepo;
       this.cartService = cartService;
    }

    public void writeHeaderRowSheet1(){
        Row row = xssfSheet1.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("id");

        cell = row.createCell(1);
        cell.setCellValue("Tên món ăn");

        cell = row.createCell(2);
        cell.setCellValue("Gía");

        cell = row.createCell(3);
        cell.setCellValue("Mô tả");

        cell = row.createCell(4);
        cell.setCellValue("Số lần mua");
    }

    public void writeHeaderRowSheet2(){
        Row row = xssfSheet2.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("id");

        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng");

        cell = row.createCell(2);
        cell.setCellValue("Địa chỉ giao hàng");

        cell = row.createCell(3);
        cell.setCellValue("Số lượng món ăn");

        cell = row.createCell(4);
        cell.setCellValue("Tổng giá");
    }

    public void writeHeaderRowSheet3(){
        Row row = xssfSheet3.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("id");

        cell = row.createCell(1);
        cell.setCellValue("Tên tài khoản");

        cell = row.createCell(2);
        cell.setCellValue("Email");

        cell = row.createCell(3);
        cell.setCellValue("role");

        cell = row.createCell(4);
        cell.setCellValue("type");

        cell = row.createCell(5);
        cell.setCellValue("trạng thái");

        cell = row.createCell(6);
        cell.setCellValue("Số đơn hàng đã đặt");
    }

    public void writeDataRow1(){
        List<Product> allProducts = productService.findAll();
        for (int i = 0 ;i < allProducts.size(); i++){
            Product product = allProducts.get(i);

            Row row = xssfSheet1.createRow(i+1);
            Cell cell = row.createCell(0);
            cell.setCellValue(product.getId());

            cell = row.createCell(1);
            cell.setCellValue(product.getName());

            cell = row.createCell(2);
            cell.setCellValue(product.getPrice());

            cell = row.createCell(3);
            cell.setCellValue(product.getDescription());

            int count = 0;
            List<CartItem> cartItems = cartItemRepo.findByProduct(product);
            for(CartItem cartItem : cartItems){
                count += cartItem.getQuantity();
            }

            cell = row.createCell(4);
            cell.setCellValue(count);
        }

    }

    public void writeDataRow3(){
        List<User> allUser = userService.findAll();
        for (int i = 0 ;i < allUser.size(); i++){
            User user = allUser.get(i);
            List<Cart> allCartOfUser = cartService.findByUser(user);

            Row row = xssfSheet3.createRow(i+1);
            Cell cell = row.createCell(0);
            cell.setCellValue(user.getId());

            cell = row.createCell(1);
            cell.setCellValue(user.getFullName());

            cell = row.createCell(2);
            cell.setCellValue(user.getEmail());

            cell = row.createCell(3);
            cell.setCellValue(user.getRoles().stream().map(Role::getName).collect(Collectors.joining(", ")));

            cell = row.createCell(4);
            cell.setCellValue(user.getType());

            cell = row.createCell(5);
            cell.setCellValue(user.getStatus());

            cell = row.createCell(6);
            cell.setCellValue(allCartOfUser.size());
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRowSheet1();
        writeHeaderRowSheet2();
        writeHeaderRowSheet3();
        writeDataRow1();
        writeDataRow3();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        xssfWorkbook.write(servletOutputStream);
        xssfWorkbook.close();
        servletOutputStream.close();
    }
}
