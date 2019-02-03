package com.springmvc;


import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.bean.User;

@RequestMapping("springmvc")
@Controller
public class TestMapping {
	
	
	@RequestMapping(value="testModelAndView")
	public ModelAndView testModelAndView(){
		String returnValue = "success";
		ModelAndView modelAndView = new ModelAndView(returnValue);
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}
	
	/**
	 * ����ʹ�� Serlvet ԭ���� API ��ΪĿ�귽���Ĳ��� ����֧����������
	 * 
	 * HttpServletRequest 
	 * HttpServletResponse 
	 * HttpSession
	 * java.security.Principal 
	 * Locale 
	 * InputStream 
	 * OutputStream 
	 * Reader 
	 * Writer
	 * @throws IOException 
	 */
	@RequestMapping("testServletAPI")
	public void testServletAPI(HttpServletRequest request,HttpServletResponse response,Writer writer) throws IOException{
		System.out.println(request);
		System.out.println(response);
		writer.write("SpringMVC test Servlet Api");
	}
	
	
	@RequestMapping("testPojo")
	public String testPojo(User user){
		System.out.println(user);
		return "success";
	}
	
	/**
	 * ����Ϊcookie��name
	 * @param JSESSIONID
	 * @return
	 */
	@RequestMapping(value="testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID")String JSESSIONID){
		System.out.println("test Cookie Value : " + JSESSIONID);
		return "success";
	}
	
	@RequestMapping(value="testRequestHeader")
	public String testRequestHeader(@RequestHeader("Accept")String accept){
		System.out.println(accept);
		return "success";
	}
	
	@RequestMapping(value="testRequestParam")
	public String testRequestParam(@RequestParam(value="username")String username,@RequestParam(value="age",required=false,defaultValue = "1")String age ){
		System.out.println("test request param, username : " + username + " age : " + age );
		return "success";
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.DELETE)
	public String testDelete(@PathVariable Integer id){
		System.out.println("test delete :  "+ id);
		return "success";
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	public String testPut(@PathVariable Integer id){
		System.out.println("test Put : " + id);
		return "success";
	}
	
	
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testPost(){
		System.out.println("testRest Post");
		return "success";
	}
	
	
	@RequestMapping(value="testRest/{id}",method=RequestMethod.GET)
	public String testRestGet(@PathVariable Integer id){
		System.out.println("test Rest Get : " + id);
		return "success";
	}
	
	
	/**
	 * pathVariable ռλ�� 
	 * RequestMapping�е�{id}�뷽���е�id����Ҫ��Ӧ
	 * @param id
	 * @return
	 */
	@RequestMapping(value="testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") String id){
		System.out.println("testPathVariable : "+  id);
		return "success";
	}
	
	
	/**
	 * ant���** ����ҵƥ��������Ŀ¼
	 */
	@RequestMapping(value="testAntPath/**/abc")
	public String testMorePathAntPath(){
		System.out.println("testMorePathAntPath");
		return "success";
	}
	
	/**
	 *  ant ���*�������ƥ���������ַ�
	 * @return
	 */
	@RequestMapping(value="testAntPath/*/abc")
	public String testMoreAntPath(){
		System.out.println("testMoreAntPath");
		return "success";
	}
	
	
	/**
	 *  ant ���*�������ƥ������һ���ַ�
	 * @return
	 */
	@RequestMapping(value="testAntPath/?/abc")
	public String testOneAntPath(){
		System.out.println("testOneAntPath");
		return "success";
	}
	
	/**
	 * params�е�age���ܵ���10������ͷheader��Accept-Language����Ϊzh-CN,zh;q=0.9
	 * @return
	 */
	@RequestMapping(value="testParamsHeader",
					params={"username","age!=10"},
					headers={"Accept-Language=zh-CN,zh;q=0.9"}
					)
	public String testParamsHeader(){
		System.out.println("testParamsHeader");
		return "success";
	}

	@RequestMapping(value="testMethod",method=RequestMethod.GET)
	public String testMethodGet(){
		System.out.println("test Method get");
		return "success";
	}
	
	@RequestMapping(value="testMethod",method=RequestMethod.POST)
	public String testMethodPost(){
		System.out.println("test method Post");
		return "success";
	}
	
	@RequestMapping("requestMapping")
	public String testMapping(){
		System.out.println("testmapping");
		return "success";
	}
	
}
