package com.linh.utils;

import com.linh.model.*;
import com.linh.respository.ICartItemRepo;
import com.linh.respository.ICartRepo;
import com.linh.service.ICartService;
import com.linh.service.IChartService;
import com.linh.service.IProductService;
import com.linh.service.IUserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelExporter {

    private final XSSFWorkbook xssfWorkbook;
    private final XSSFSheet xssfSheetIncome;
    private final XSSFSheet xssfSheetTrend;

    private final IProductService productService;
    private final ICartItemRepo cartItemRepo;
    private final ICartRepo cartRepo;
    private final IChartService chartService;

    private final SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");


    public ExcelExporter(IProductService productService, ICartItemRepo cartItemRepo, ICartRepo cartRepo, IChartService chartService) throws IOException {
        xssfWorkbook = new XSSFWorkbook(new ClassPathResource("static/file/bao_cao_doanh_thu.xlsx").getInputStream());
        xssfSheetIncome = xssfWorkbook.getSheetAt(0);
        xssfSheetTrend = xssfWorkbook.getSheetAt(1);
        this.productService = productService;
        this.cartItemRepo = cartItemRepo;
        this.cartRepo = cartRepo;
        this.chartService = chartService;
    }

    public void writeDataSheetIncome() throws ParseException {
        //INCOME
        Date start = smf.parse("01/01/2023 00:00:00");
        Date end = smf.parse("31/12/2023 23:59:59");
        List<Float> actualIncome = chartService.getLineChartData(start, end);

        int rowIndex = 10;
        int totalActualIncome = 0;
        for (Float value : actualIncome){
            int roundValue = Math.round(value);
            String moneyFormat = MoneyFormatUtil.format(roundValue);
            String percent = (value / 30000000 * 100)+"";
            if (percent.length() > 4){
                percent = new StringBuilder(percent).substring(0, 4).toString()+" %";
            }else{
                percent = percent+" %";
            }
            Row row = xssfSheetIncome.getRow(rowIndex);
            Cell cell = row.getCell(2);
            cell.setCellValue(moneyFormat);
            setCellStyle(cell);

            cell = row.getCell(3);
            cell.setCellValue(MoneyFormatUtil.format(30000000));
            setCellStyle(cell);

            cell = row.getCell(4);
            cell.setCellValue(percent);
            setCellStyle(cell);

            totalActualIncome += Math.round(value);
            rowIndex++;
        }

        //NUMBER of ORDER
        rowIndex = 10;
        List<Integer> numberOfOrders = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0));
        List<Cart> carts = cartRepo.findByYear(start, end);
        Calendar cal = Calendar.getInstance();
        for (Cart cart : carts){
            cal.setTime(cart.getOrderTime());
            int month = cal.get(Calendar.MONTH);
            int oldValue = numberOfOrders.get(month);
            numberOfOrders.set(month, oldValue+1);
        }
        for (Integer value : numberOfOrders){
            Row row = xssfSheetIncome.getRow(rowIndex);
            Cell cell = row.getCell(1);
            cell.setCellValue(value);
            setCellStyle(cell);

            rowIndex++;
        }

        Row row = xssfSheetIncome.getRow(22);
        Cell cell = row.getCell(4);
        setCellStyle(cell);

        cell.setCellValue(MoneyFormatUtil.format(totalActualIncome));
    }

    public void writeDataSheetTrend() throws ParseException {
        Date start = smf.parse("01/01/2023 00:00:00");
        Date end = smf.parse("31/12/2023 23:59:59");
        List<Cart> carts = cartRepo.findByYear(start, end);
        Map<Integer, Integer> timeBuy = new TreeMap<>();
        Map<Integer, Integer> totalIncome = new LinkedHashMap<>();
        for (Cart cart : carts){
            List<CartItem> cartItems = cart.getCartItems();
            for (CartItem item : cartItems){
                Product product = item.getProduct();
                if (timeBuy.containsKey(product.getId())){
                    timeBuy.put(product.getId(), timeBuy.get(product.getId())+1);
                    totalIncome.put(product.getId(), totalIncome.get(product.getId()) + product.getPrice() * item.getQuantity());
                }else{
                    timeBuy.put(product.getId(), 1);
                    totalIncome.put(product.getId(), product.getPrice() * item.getQuantity());
                }
            }
        }

        int rowIndex = 10;
        for (Map.Entry<Integer, Integer> entry : timeBuy.entrySet()){
            Product product = productService.findById(entry.getKey());
            Row row = xssfSheetTrend.getRow(rowIndex);
            Cell cell = row.getCell(1);
            cell.setCellValue(product.getName());
            setCellStyle(cell);

            cell = row.getCell(2);
            cell.setCellValue(product.getCategory().getName());
            setCellStyle(cell);

            cell = row.getCell(3);
            cell.setCellValue(entry.getValue());
            setCellStyle(cell);

            cell = row.getCell(4);
            cell.setCellValue(MoneyFormatUtil.format(totalIncome.get(entry.getKey())));
            setCellStyle(cell);

            rowIndex ++;
        }
    }

    public void export(HttpServletResponse response) throws IOException, ParseException {
        writeDataSheetIncome();
        writeDataSheetTrend();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        xssfWorkbook.write(servletOutputStream);
        xssfWorkbook.close();
        servletOutputStream.close();
    }

    private void setCellStyle(Cell cell){
        Font newFont = cell.getSheet().getWorkbook().createFont();
        CellStyle cellStyle = cell.getCellStyle();
        System.out.println(newFont.getFontName());
        newFont.setFontHeight((short) (13*20));
        newFont.setFontName("Times New Roman");
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(newFont);
        cell.setCellStyle(cellStyle);
    }
}
