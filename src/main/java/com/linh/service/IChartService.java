package com.linh.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IChartService {

    List<Integer> getPieChartData(Date start, Date end) throws ParseException;
    List<Float> getLineChartData(Date start, Date end) throws ParseException;
}
