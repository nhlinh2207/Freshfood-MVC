package com.linh.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

@Controller
@Slf4j
public class ErrorPageController implements ErrorController {
   
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		String errorPage = "error";
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				errorPage = "error/404";
				//Hiện lỗi ở Console
				log.error("404 Not Found");
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				errorPage = "error/500";
				//Hiện lỗi ở Console
				log.error("500 Internal server error");
			}else if(statusCode == HttpStatus.FORBIDDEN.value()) {
				errorPage = "error/403";
				//Hiện lỗi ở Console
				log.error("403 Forbidden");
			}
		}
		return errorPage;
	}
}
