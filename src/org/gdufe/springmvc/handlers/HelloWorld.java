package org.gdufe.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

	/**
	 * 1. 使用 @RequestMapping 注解来映射请求的URL
	 * 2. 返回值通过视图解析器解析为实际的物理视图，对于 InternalResourceViewResolver 解析器，解析过程如下：
	 * 		通过 prefix + returnVal + suffix 得到实际的物理视图，然后做转发操作
	 * 		/WEB-INF/views/success.jsp
	 * @return
	 */
	@RequestMapping("/helloworld")
	public String hello(){
		System.out.println("HelloWorld");
		return "success";
	}
}
