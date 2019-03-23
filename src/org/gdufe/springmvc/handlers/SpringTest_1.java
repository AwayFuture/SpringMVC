package org.gdufe.springmvc.handlers;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gdufe.springmvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/springmvc")
@Controller
public class SpringTest_1 {

	private static final String SUCCESS = "success";

	/**
	 * 1. @RequestMapping 除了可以修饰方法之外，还可以修饰类
	 * 2.
	 *  1) 修饰类时，提供初步的请求映射信息，相当于 WEB 的根目录/命名空间 ---> 使用时映射应为：springmvc/testSpringMapping
	 *  2) 修饰方法时，提供进一步的映射信息，若类定义处没有修饰，则方法处的 RequestMapping 相当于URL
	 * @return
	 */
	@RequestMapping("/testSpringMapping")
	public String testSpringMapping(){
		System.out.println("Test SpringMapping");
		return SUCCESS;
	}

	/**
	 * 1.通过method属性来指定请求的方式
	 * @return
	 */
	@RequestMapping(value="/testMethod",method=RequestMethod.POST)
	public String testMethod(){
		System.out.println("Test Method");
		return SUCCESS;
	}

	/**
	 * 通过 params 和 headers 来指定请求URL的参数和HTTP表头格式（实际开发使用的比较少）
	 * @return
	 */
	@RequestMapping(value="/testParamsAndHeaders",params={"username","age!=10"},headers={"Accept-Language=zh-CN,zh;q=0.9"})
	public String testParamsAndHeaders(){
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}

	/**
	 * @RequestMapping 支持 Ant风格的URL
	 * Ant风格资源地址支持3中匹配符：
	 * 	1) ? : 匹配文件名中的一个字符
	 * 	2) * : 匹配文件名中的任意字符
	 * 	3) ** : 匹配多层路径
	 * @return
	 */
	@RequestMapping("/testAntPath/*/mvc")
	public String testAntPath(){
		System.out.println("Test AntPath");
		return SUCCESS;
	}

	/**
	 * 通过 @PathVariable 可以将 URL中的占位符参数绑定到 控制器处理方法的参数中
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable(value="id") Integer id){
		System.out.println("testPathVariable "+ id);
		return SUCCESS;
	}

	/*
	 * REST风格请求方式进一步学习：https://blog.csdn.net/qq_34582693/article/details/79981742
	 * REST风格的URL：（CUDR操作）
	 * 新增:POST，更新：PUT，删除：DELETE，获取：GET
	 * 
	 * 如何在jsp页面发送PUT/DELETE请求？
	 * 1. 需要在web.xml配置过滤器HiddenHttpMethodFilter
	 * 2. 需要发送POST的表单请求
	 * 3. 需要在发送POST请求时携带一个 name="_method" 的隐藏域,值为POST
	 * 	    （为什么值不是DELETE/PUT呢？ 因为会出现请求解析出错的情况--> https://www.cnblogs.com/aeolian/p/8443727.html）
	 * 	        如果使用 Tomcat7 则不会出现上述的解析错误的情况
	 * 	        使用@ResponseBody()注解能解决 HTTP405 的错误，但是实现不了业务逻辑，仅仅是返回一个字符串给JSP页面
	 */

	@RequestMapping(value="/testRest/{id}")
	public String testRest(@PathVariable Integer id){
		System.out.println("Test Rest GET " + id);
		return SUCCESS;
	}

	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testRest(){
		System.out.println("Test Rest POST " );
		return SUCCESS;
	}

	@RequestMapping(value="/testRest/{id}",method=RequestMethod.DELETE)
	//@ResponseBody()
	public String testRestDelete(@PathVariable Integer id){
		System.out.println("Test Rest DELETE " + id);
		return SUCCESS;
	}

	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	//@ResponseBody
	public String testRestPut(@PathVariable Integer id){
		System.out.println("Test Rest PUT " + id);
		return SUCCESS;
	}

	/**
	 * 使用 @RequestParam 来映射请求参数
	 * value 值即为请求参数的参数名
	 * required 值指定该参数是否是必须的，例如当JSP页面没有传递该参数过来的时候要指定其值为false，其默认值为true
	 * defaultValue 值表示该参数默认的值
	 * @param username
	 * @param age
	 * @return
	 */
	@RequestMapping(value="/testRequestParam")
	public String testRequestParam(@RequestParam(value="username") String username,
			@RequestParam(value="age",required=false, defaultValue="0") Integer age){
		System.out.println("Test RequestParam, username : " + username + ", age : " + age);
		return SUCCESS;
	}

	/**
	 * 使用 @RequestHeader 映射请求头信息，用法跟 @RequestParam 相同
	 * @param al
	 * @return
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String al){
		System.out.println("Test RequestHeader, Accept-Language : " + al);
		return SUCCESS;
	}

	/**
	 * 映射请求的 Cookie 信息，用法同 @RequestParam
	 * @param sessionId
	 * @return
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionId){
		System.out.println("Test CookieValue , sessionId : " + sessionId);
		return SUCCESS;
	}

	/**
	 * 1. 当使用POJO作为参数时，SpringMVC 会按照JSP页面的参数名称跟POJO属性名进行自动匹配，为其填充属性值。
	 * 2. 支持POJO的级联属性，如：address.province, address.city
	 * @param user
	 * @return
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		System.out.println("Test POJO, User-Information : " + user);
		return SUCCESS;
	}

	/**
	 * 在 SpringMVC中支持使用 Servlet 的原生API作为目标方法的参数，支持以下几种原生的API
	 * HttpServletRequest
	 * HttpServletResponse
	 * HttpSession
	 * java.security.Priciple
	 * Locale
	 * InputStream / OutputStream
	 * Reader / Writer
	 */
	@RequestMapping("/testServletAPI")
	public void testServletAPI(HttpServletRequest req , HttpServletResponse res , Writer out) throws IOException{
		System.out.println("Test ServletAPI : " + req + " , " + res);
		//如果不设置 req.setCharacterEncoding 属性，获取输出的 req.getCharacterEncoding()==null
		//原因查看：https://stackoverflow.com/questions/12358101/request-getcharacterencoding-returns-null-why
		req.setCharacterEncoding("UTF-8");
		out.write("hello ServletAPI " + req.getCharacterEncoding());
		//return SUCCESS;
	}

}
