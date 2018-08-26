package com.example.demo.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.component.LoginHandlerInterceptor;
import com.example.demo.component.MyLocalResolver;

/**
 * 配置类：设置首页
 * @author 汪进银
 *
 */
//标注此类为配置类
@Configuration

//使用 WebMvcConfigurerAdapter来扩展springMVC的功能
public class MyMvcConfig extends WebMvcConfigurerAdapter{
	
	    @Bean //一定要将这个定制器加入到容器中
	    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
	        return new EmbeddedServletContainerCustomizer() {

	        	//定制嵌入式的Servlet容器相关的规则
				@Override
				public void customize(ConfigurableEmbeddedServletContainer container) {
					 //container.setPort(8083);
				}
	            
	        };
	    }
	
   public void addViewController(ViewControllerRegistry  registry) {
	   //浏览器发送atguigu请求 来到success页面
	   registry.addViewController("/atguigu").setViewName("success");
	   
   }
   
   @Bean  //将组件添加到容器中
   //所有的webmvConfigurationAdpter组件都会一起器作用
   public WebMvcConfigurerAdapter  webMvcConfigurerAdapter() {
	   
	   WebMvcConfigurerAdapter adpter =  new WebMvcConfigurerAdapter() {
		   public void addViewControllers(ViewControllerRegistry  registry) {
			   
			   registry.addViewController("/").setViewName("index");
			   registry.addViewController("/index.html").setViewName("index");
			   registry.addViewController("/main.html").setViewName("dashboard");
		   }
		   
		   /**
		    * 注册拦截器
		    */
		   //静态资源 css，js等 springboot默认已经做好 静态资源映射
		   public void addInterceptors(InterceptorRegistry registry) {
			   registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
               .excludePathPatterns("/index.html","/","/user/login");
		   }
		   
	   };
	return adpter;
   }
   
   @Bean 
   public LocaleResolver localeResolver() {
	   return new MyLocalResolver();
   }
   
}
