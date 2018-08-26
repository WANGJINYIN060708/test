package com.example.demo.component;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
/**
 * 可以在链接上携带区域信息 可以切换中英文请求
 * @author 汪进银
 *
 */
public class MyLocalResolver implements LocaleResolver {

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		
		String l = request.getParameter("l");
		Locale locale = Locale.getDefault();
		if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1]);
        } 
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		
	}

}
