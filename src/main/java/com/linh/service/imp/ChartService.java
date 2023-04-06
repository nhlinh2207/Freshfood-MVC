package com.linh.service.imp;

import com.linh.model.Cart;
import com.linh.model.CartItem;
import com.linh.respository.ICartRepo;
import com.linh.service.IChartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ChartService implements IChartService {

    private final ICartRepo cartRepo;
    private final SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    @Override
    public List<Integer> getPieChartData(Date start, Date end) throws ParseException {
        List<Integer> pieChartData = new ArrayList<>();
        List<CartItem> data = cartRepo.findByYear(start, end)
                .stream()
                .map(Cart::getCartItems)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Map<String, Integer> res = new LinkedHashMap<>();
        res.put("Cơm bình dân", 0);
        res.put("Bánh mì", 0);
        res.put("Đồ uống", 0);
        res.put("Hoa quả tươi", 0);
        res.put("Đồ ăn nhẹ", 0);
        res.put("Sinh tố", 0);

        for (CartItem cartItem : data){
            String categoryName = cartItem.getProduct().getCategory().getName();
            res.put(categoryName, res.get(categoryName) + cartItem.getQuantity());
        }

        pieChartData.add(res.get("Cơm bình dân"));
        pieChartData.add(res.get("Bánh mì"));
        pieChartData.add(res.get("Đồ uống"));
        pieChartData.add(res.get("Hoa quả tươi"));
        pieChartData.add(res.get("Đồ ăn nhẹ"));
        pieChartData.add(res.get("Sinh tố"));

        return pieChartData;
    }

    @Override
    public List<Float> getLineChartData(Date start, Date end) throws ParseException {
        List<Float> lineChartData = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        List<Cart> data = cartRepo.findByYear(start, end);
        Map<Integer, Float> res = new LinkedHashMap<>();
        res.put(1, 0f);  res.put(2, 0f);   res.put(3, 0f);
        res.put(4, 0f);  res.put(5, 0f);   res.put(6, 0f);
        res.put(7, 0f);  res.put(8, 0f);   res.put(9, 0f);
        res.put(10, 0f); res.put(11, 0f);  res.put(12, 0f);
        for (Cart cart : data){
            cal.setTime(cart.getOrderTime());
            int month = cal.get(Calendar.MONTH)+1;
            res.put(month, res.get(month)+cart.getTotalPrice());
        }
        lineChartData.add(res.get(1));   lineChartData.add(res.get(2));
        lineChartData.add(res.get(3));   lineChartData.add(res.get(4));
        lineChartData.add(res.get(5));   lineChartData.add(res.get(6));
        lineChartData.add(res.get(7));   lineChartData.add(res.get(8));
        lineChartData.add(res.get(9));   lineChartData.add(res.get(10));
        lineChartData.add(res.get(11));   lineChartData.add(res.get(12));
        return lineChartData;
    }
}
