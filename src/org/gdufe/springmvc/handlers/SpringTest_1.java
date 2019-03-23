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
	 * 1. @RequestMapping ���˿������η���֮�⣬������������
	 * 2.
	 *  1) ������ʱ���ṩ����������ӳ����Ϣ���൱�� WEB �ĸ�Ŀ¼/�����ռ� ---> ʹ��ʱӳ��ӦΪ��springmvc/testSpringMapping
	 *  2) ���η���ʱ���ṩ��һ����ӳ����Ϣ�����ඨ�崦û�����Σ��򷽷����� RequestMapping �൱��URL
	 * @return
	 */
	@RequestMapping("/testSpringMapping")
	public String testSpringMapping(){
		System.out.println("Test SpringMapping");
		return SUCCESS;
	}

	/**
	 * 1.ͨ��method������ָ������ķ�ʽ
	 * @return
	 */
	@RequestMapping(value="/testMethod",method=RequestMethod.POST)
	public String testMethod(){
		System.out.println("Test Method");
		return SUCCESS;
	}

	/**
	 * ͨ�� params �� headers ��ָ������URL�Ĳ�����HTTP��ͷ��ʽ��ʵ�ʿ���ʹ�õıȽ��٣�
	 * @return
	 */
	@RequestMapping(value="/testParamsAndHeaders",params={"username","age!=10"},headers={"Accept-Language=zh-CN,zh;q=0.9"})
	public String testParamsAndHeaders(){
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}

	/**
	 * @RequestMapping ֧�� Ant����URL
	 * Ant�����Դ��ַ֧��3��ƥ�����
	 * 	1) ? : ƥ���ļ����е�һ���ַ�
	 * 	2) * : ƥ���ļ����е������ַ�
	 * 	3) ** : ƥ����·��
	 * @return
	 */
	@RequestMapping("/testAntPath/*/mvc")
	public String testAntPath(){
		System.out.println("Test AntPath");
		return SUCCESS;
	}

	/**
	 * ͨ�� @PathVariable ���Խ� URL�е�ռλ�������󶨵� �������������Ĳ�����
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable(value="id") Integer id){
		System.out.println("testPathVariable "+ id);
		return SUCCESS;
	}

	/*
	 * REST�������ʽ��һ��ѧϰ��https://blog.csdn.net/qq_34582693/article/details/79981742
	 * REST����URL����CUDR������
	 * ����:POST�����£�PUT��ɾ����DELETE����ȡ��GET
	 * 
	 * �����jspҳ�淢��PUT/DELETE����
	 * 1. ��Ҫ��web.xml���ù�����HiddenHttpMethodFilter
	 * 2. ��Ҫ����POST�ı�����
	 * 3. ��Ҫ�ڷ���POST����ʱЯ��һ�� name="_method" ��������,ֵΪPOST
	 * 	    ��Ϊʲôֵ����DELETE/PUT�أ� ��Ϊ��������������������--> https://www.cnblogs.com/aeolian/p/8443727.html��
	 * 	        ���ʹ�� Tomcat7 �򲻻���������Ľ�����������
	 * 	        ʹ��@ResponseBody()ע���ܽ�� HTTP405 �Ĵ��󣬵���ʵ�ֲ���ҵ���߼��������Ƿ���һ���ַ�����JSPҳ��
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
	 * ʹ�� @RequestParam ��ӳ���������
	 * value ֵ��Ϊ��������Ĳ�����
	 * required ֵָ���ò����Ƿ��Ǳ���ģ����統JSPҳ��û�д��ݸò���������ʱ��Ҫָ����ֵΪfalse����Ĭ��ֵΪtrue
	 * defaultValue ֵ��ʾ�ò���Ĭ�ϵ�ֵ
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
	 * ʹ�� @RequestHeader ӳ������ͷ��Ϣ���÷��� @RequestParam ��ͬ
	 * @param al
	 * @return
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String al){
		System.out.println("Test RequestHeader, Accept-Language : " + al);
		return SUCCESS;
	}

	/**
	 * ӳ������� Cookie ��Ϣ���÷�ͬ @RequestParam
	 * @param sessionId
	 * @return
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionId){
		System.out.println("Test CookieValue , sessionId : " + sessionId);
		return SUCCESS;
	}

	/**
	 * 1. ��ʹ��POJO��Ϊ����ʱ��SpringMVC �ᰴ��JSPҳ��Ĳ������Ƹ�POJO�����������Զ�ƥ�䣬Ϊ���������ֵ��
	 * 2. ֧��POJO�ļ������ԣ��磺address.province, address.city
	 * @param user
	 * @return
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		System.out.println("Test POJO, User-Information : " + user);
		return SUCCESS;
	}

	/**
	 * �� SpringMVC��֧��ʹ�� Servlet ��ԭ��API��ΪĿ�귽���Ĳ�����֧�����¼���ԭ����API
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
		//��������� req.setCharacterEncoding ���ԣ���ȡ����� req.getCharacterEncoding()==null
		//ԭ��鿴��https://stackoverflow.com/questions/12358101/request-getcharacterencoding-returns-null-why
		req.setCharacterEncoding("UTF-8");
		out.write("hello ServletAPI " + req.getCharacterEncoding());
		//return SUCCESS;
	}

}
