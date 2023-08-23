package com.linh.api.admin;

import com.linh.service.IChartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@AllArgsConstructor
@Slf4j
public class ChartAPI {

     private final IChartService chartService;

     @GetMapping(path = "/freshfood/chart/pieChart/{year}")
     public List<Integer> pieChart(@PathVariable Integer year) throws ParseException {
          SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
          Date start = smf.parse("01/01/"+year+" 00:00:00");
          Date end = smf.parse("31/12/"+year+" 23:59:59");
          return chartService.getPieChartData(start, end);
     }

     @GetMapping(path = "/freshfood/chart/lineChart/{year}")
     public List<Float> lineChart(@PathVariable Integer year) throws ParseException {
          SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
          Date start = smf.parse("01/01/"+year+" 00:00:00");
          Date end = smf.parse("31/12/"+year+" 23:59:59");
          return chartService.getLineChartData(start, end);
     }

     @GetMapping(path = "/freshfood/chart/barChart/{year}")
     public List<Map<String, String>> barChart(@PathVariable Integer year) throws ParseException {
          List<Map<String, String>> response = new ArrayList<>();
          SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
          Date start = smf.parse("01/01/"+year+" 00:00:00");
          Date end = smf.parse("31/12/"+year+" 23:59:59");
          List<Float> realIncome = chartService.getLineChartData(start, end);
          for (int i = 0; i < 6; i++){
               Map<String, String> item = new LinkedHashMap<>();
               item.put("month", "Tháng "+(i+1));
               item.put("goal", 5000000+"");
               item.put("real", realIncome.get(i)+"");
               response.add(item);
          }
          return response;
     }
}
