package org.gdufe.springmvc.handlers;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.gdufe.springmvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc")
@SessionAttributes(value = {"user","school"},types={Integer.class})
public class SpringTest_2 {

	private static final String SUCCESS = "success";

	/**
	 * 1. 返回值可以是 ModelAndView 类型，包含视图View 和 模型 Model 信息
	 * 2. SpringMVC 会把 Model中的数据封装到 request 域对象中--->底层实现
	 * @return
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(){
		String viewname = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewname);
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}

	/**
	 * 可以使用 Map 作为目标方法的参数，也可以是Model类型/ModelMap类型，他们之间存在继承/实现的关系
	 * 添加到Map中的数据在底层实现过程中会将数据通过 ModelAndView 添加到request请求域中返回给JSP页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map){
		System.out.println(map.getClass().getName()); //org.springframework.validation.support.BindingAwareModelMap
		map.put("names", Arrays.asList("China","American","India"));
		return SUCCESS;
	}

	/**
	 * 上述实验的返回封装的 类型 ModelAndView/Map仅仅存在于request作用域中，
	 * 如果想要将数据达到在Session中给用户共享，执行一下操作：
	 * 	1. 将要提供给前端的数据封装到 Map 对象中
	 * 	2. 添加【类的注解】 @SessionAttrubutes(value,types)
	 * 	3. 在前端的JSP页面中获取数据---> requestScope.参数名 / sessionScope.参数名
	 * 
	 * @SessionAttributes(value,types) 参数解析：
	 * 1. value是一个 String[] 类型，用于存放Key值，可以存放多个Key值
	 * 2. types是一个Class<?> 类型，用于存放数据类型的映射，如 String.class,User.class
	 * 	  Map里面的该类型的Value值会自动匹配到前端JSP页面中.
	 * @param map
	 * @return
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String,Object> map){
		User user = new User("lhx","123456","21");
		map.put("user",user);
		map.put("school", "gdufe");
		map.put("age",21);
		return SUCCESS;
	}

	/**
	 * 有 @ModelAttribute 标记注解的方法，SpringMVC会在执行其他目标方法之前调用它！！！
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id" , required = false) String id,Map<String,Object> map ){
		System.out.println("Get the user-information from the database.");
		//模拟从数据库中获取数据，实际上没有与数据库交互
		if(id!=null){
			User user = new User("1", "lhx", "123456", "21");
			System.out.println("修改前的数据："+user);
			map.put("user", user);
		}
	}

	/**
	 * 该目标方法的执行流程：
	 * 1. 执行 @ModelAttribute 注解的方法，从数据库中获取得到 User 对象的数据存到 Map 中
	 * 2. SpringMVC 将从表单中获取的表单元素赋值给 刚刚执行完 @ModelAttibute 注解的 User 对象的对应属性
	 * 3. SpringMVC 将上述对象传入到目标方法的参数中.
	 * 
	 * 注意：在 @ModelAttribute 修饰的方法中，放入到 Map中的键需要和目标方法中入参类型的第一个字母小写的字符串一致！！！
	 * 比如在上面的 getUser() 方法中，如果map.put("us",user); 则下面的 testModelAttribute()方法的入参对象
	 * 是新创建的而不是从Map中获取得到的，所以执行后会导致数据不一致的现象.
	 * 
	 * 底层的源代码执行流程：
	 * 1. 调用 @ModelAttribute 注解修饰的方法，实际上把 @ModelAttribute 方法中Map的数据implicitModel中
	 * 2. 解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder对象的target属性
	 * 1). 创建WebDataBinder对象
	 * 2). SpringMVC 把表单的请求参数赋给了WebDataBinder 的 target属性.
	 * 3). SpringMVC 会把WebDataBinder的attrName和target给到implicitModel.
	 * 4). 把WebDataBinder的target作为参数传递给目标方法的入参.
	 * 
	 * 
	 * 注意：关于在确定implicitModel中的target属性时存在以下3种情况SpringMVC的处理方法：
	 * 1. 在implicitModel中查找attrName对应的属性值，若存在，执行成功.
	 * 2. 若不存在：则验证当前的 Handler 是否使用了 @SessionAttributes 进行修饰，若使用了，则尝试从Session
	 * 	      中获取attrName所对应的属性值.若Session中没有对应的属性值，则抛出异常，例如将上述getUer()的map.put()
	 * 	      注释掉，则会抛出java.lang.NoSuchMethodException异常.
	 * 3. 若Handler没有使用 @SessionAttributes 进行修饰，或 @SessionAttributes 没有使用value值指定的
	 * 	      的attrName相匹配，则通过反射机制创建新的POJO对象(Session中存入的对象此时不会起作用)
	 * 
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("修改后的数据: " + user);
		return SUCCESS;
	}

}
