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
	 * 1. ����ֵ������ ModelAndView ���ͣ�������ͼView �� ģ�� Model ��Ϣ
	 * 2. SpringMVC ��� Model�е����ݷ�װ�� request �������--->�ײ�ʵ��
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
	 * ����ʹ�� Map ��ΪĿ�귽���Ĳ�����Ҳ������Model����/ModelMap���ͣ�����֮����ڼ̳�/ʵ�ֵĹ�ϵ
	 * ��ӵ�Map�е������ڵײ�ʵ�ֹ����лὫ����ͨ�� ModelAndView ��ӵ�request�������з��ظ�JSPҳ��
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
	 * ����ʵ��ķ��ط�װ�� ���� ModelAndView/Map����������request�������У�
	 * �����Ҫ�����ݴﵽ��Session�и��û�����ִ��һ�²�����
	 * 	1. ��Ҫ�ṩ��ǰ�˵����ݷ�װ�� Map ������
	 * 	2. ��ӡ����ע�⡿ @SessionAttrubutes(value,types)
	 * 	3. ��ǰ�˵�JSPҳ���л�ȡ����---> requestScope.������ / sessionScope.������
	 * 
	 * @SessionAttributes(value,types) ����������
	 * 1. value��һ�� String[] ���ͣ����ڴ��Keyֵ�����Դ�Ŷ��Keyֵ
	 * 2. types��һ��Class<?> ���ͣ����ڴ���������͵�ӳ�䣬�� String.class,User.class
	 * 	  Map����ĸ����͵�Valueֵ���Զ�ƥ�䵽ǰ��JSPҳ����.
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
	 * �� @ModelAttribute ���ע��ķ�����SpringMVC����ִ������Ŀ�귽��֮ǰ������������
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id" , required = false) String id,Map<String,Object> map ){
		System.out.println("Get the user-information from the database.");
		//ģ������ݿ��л�ȡ���ݣ�ʵ����û�������ݿ⽻��
		if(id!=null){
			User user = new User("1", "lhx", "123456", "21");
			System.out.println("�޸�ǰ�����ݣ�"+user);
			map.put("user", user);
		}
	}

	/**
	 * ��Ŀ�귽����ִ�����̣�
	 * 1. ִ�� @ModelAttribute ע��ķ����������ݿ��л�ȡ�õ� User ��������ݴ浽 Map ��
	 * 2. SpringMVC ���ӱ��л�ȡ�ı�Ԫ�ظ�ֵ�� �ո�ִ���� @ModelAttibute ע��� User ����Ķ�Ӧ����
	 * 3. SpringMVC �����������뵽Ŀ�귽���Ĳ�����.
	 * 
	 * ע�⣺�� @ModelAttribute ���εķ����У����뵽 Map�еļ���Ҫ��Ŀ�귽����������͵ĵ�һ����ĸСд���ַ���һ�£�����
	 * ����������� getUser() �����У����map.put("us",user); ������� testModelAttribute()��������ζ���
	 * ���´����Ķ����Ǵ�Map�л�ȡ�õ��ģ�����ִ�к�ᵼ�����ݲ�һ�µ�����.
	 * 
	 * �ײ��Դ����ִ�����̣�
	 * 1. ���� @ModelAttribute ע�����εķ�����ʵ���ϰ� @ModelAttribute ������Map������implicitModel��
	 * 2. ��������������Ŀ�������ʵ���ϸ�Ŀ�����������WebDataBinder�����target����
	 * 1). ����WebDataBinder����
	 * 2). SpringMVC �ѱ����������������WebDataBinder �� target����.
	 * 3). SpringMVC ���WebDataBinder��attrName��target����implicitModel.
	 * 4). ��WebDataBinder��target��Ϊ�������ݸ�Ŀ�귽�������.
	 * 
	 * 
	 * ע�⣺������ȷ��implicitModel�е�target����ʱ��������3�����SpringMVC�Ĵ�������
	 * 1. ��implicitModel�в���attrName��Ӧ������ֵ�������ڣ�ִ�гɹ�.
	 * 2. �������ڣ�����֤��ǰ�� Handler �Ƿ�ʹ���� @SessionAttributes �������Σ���ʹ���ˣ����Դ�Session
	 * 	      �л�ȡattrName����Ӧ������ֵ.��Session��û�ж�Ӧ������ֵ�����׳��쳣�����罫����getUer()��map.put()
	 * 	      ע�͵�������׳�java.lang.NoSuchMethodException�쳣.
	 * 3. ��Handlerû��ʹ�� @SessionAttributes �������Σ��� @SessionAttributes û��ʹ��valueֵָ����
	 * 	      ��attrName��ƥ�䣬��ͨ��������ƴ����µ�POJO����(Session�д���Ķ����ʱ����������)
	 * 
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("�޸ĺ������: " + user);
		return SUCCESS;
	}

}
