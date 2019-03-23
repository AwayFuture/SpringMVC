package org.gdufe.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

	/**
	 * 1. ʹ�� @RequestMapping ע����ӳ�������URL
	 * 2. ����ֵͨ����ͼ����������Ϊʵ�ʵ�������ͼ������ InternalResourceViewResolver �������������������£�
	 * 		ͨ�� prefix + returnVal + suffix �õ�ʵ�ʵ�������ͼ��Ȼ����ת������
	 * 		/WEB-INF/views/success.jsp
	 * @return
	 */
	@RequestMapping("/helloworld")
	public String hello(){
		System.out.println("HelloWorld");
		return "success";
	}
}
