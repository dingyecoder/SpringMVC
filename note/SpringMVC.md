# SpringMVC

## 1.springmvc jar包
spring-aop-4.0.0.RELEASE.jar  
spring-beans-4.0.0.RELEASE.jar  
spring-context-4.0.0.RELEASE.jar  
spring-core-4.0.0.RELEASE.jar  
spring-expression-4.0.0.RELEASE.jar  
spring-web-4.0.0.RELEASE.jar  
spring-webmvc-4.0.0.RELEASE.jar  
同时需要加入Commons-logging-1.1.1.jar日志包

## 2. 配置web.xml文件
	主要是配置dispatcherServlet
    <!-- 配置 DispatcherServlet -->
	<servlet>
		<servlet-name>dispatcherServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<!-- 配置 DispatcherServlet 的一个初始化参数: 配置 SpringMVC 配置文件的位置和名称 -->
		<!-- 实际上也可以不通过 contextConfigLocation 来配置 SpringMVC 的配置文件, 而使用默认的. 默认的配置文件为: 
			/WEB-INF/<servlet-name>-servlet.xml -->
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:springmvc.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>dispatcherServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

## 3. 配置springmvc.xml文件

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:mvc="http://www.springframework.org/schema/mvc"
		xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
			http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
			http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd">
	
		<!-- 配置自定扫描的包 -->
		<context:component-scan base-package="com.springmvc"></context:component-scan>
	
		<!-- 配置视图解析器: 如何把 handler 方法返回值解析为实际的物理视图 -->
		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix" value="/WEB-INF/views/"></property>
			<property name="suffix" value=".jsp"></property>
		</bean>
	
	</beans>

## 4. @RequestMapping

@RequestMapping可以加在方法和类上,可以单独加，也可以都加，用来匹配url

如jsp:index.jsp:
	
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="submit">
	</form>

	<br>
	<a href="springmvc/testMethod">Test Method</a>

	<br>
	<a href="springmvc/requestMapping">Test RequestMapping</a>

	<br>
	<a href="helloworld">Hello World</a>

对应的类：

	@RequestMapping("springmvc")
	@Controller
	public class TestMapping {
	
		/匹配url,方法是Get
		@RequestMapping(value="testMethod",method=RequestMethod.GET)
		public String testMethodGet(){
			System.out.println("test Method get");
			return "success";
		}
		
		//匹配url,方法是POST
		@RequestMapping(value="testMethod",method=RequestMethod.POST)
		public String testMethodPost(){
			System.out.println("test method Post");
			return "success";
		}
		
		//匹配url,默认是get
		@RequestMapping("requestMapping")
		public String testMapping(){
			System.out.println("testmapping");
			return "success";
		}
		
	}


## 5.请求头和请求参数
	<a href="springmvc/testParamsHeader?username=Bill&age=10">testParamsHeader</a>

	//对应的类与方法
	@RequestMapping("springmvc")
	@Controller
	public class TestMapping {
		
		
		/**
		 * params中的age不能等于10，请求头header的Accept-Language必须为zh-CN,zh;q=0.9
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
	}


## 6. ant风格的URL匹配模式
###a. ? 代表可以匹配任一个字符，注意是任意的一个字符
###b. * 代表可以匹配0或任意个字符
###c. ** 这个字符匹配0个或任意个目录 

	<a href="springmvc/testAntPath/moredir/abc">test moredir ant path</a>
	<br><br>

	<a href="springmvc/testAntPath/more/abc">test more ant path</a>
	<br><br>

	<a href="springmvc/testAntPath/e/abc">test one ant path</a>

对应的类和方法：

	@RequestMapping("springmvc")
	@Controller
	public class TestMapping {
		
		/**
		 * ant风格：** 代表开业匹配任意多层目录
		 */
		@RequestMapping(value="testAntPath/**/abc")
		public String testMorePathAntPath(){
			System.out.println("testMorePathAntPath");
			return "success";
		}
		
		/**
		 *  ant 风格：*代表可以匹配任意多个字符
		 * @return
		 */
		@RequestMapping(value="testAntPath/*/abc")
		public String testMoreAntPath(){
			System.out.println("testMoreAntPath");
			return "success";
		}
				
		/**
		 *  ant 风格：*代表可以匹配任意一个字符
		 * @return
		 */
		@RequestMapping(value="testAntPath/?/abc")
		public String testOneAntPath(){
			System.out.println("testOneAntPath");
			return "success";
		}
	}	


# 7. 占位符 pathVariable
PathVariable是Spring3.0后出现的，这是Spring迈向Rest风格的里程碑

	<a href="springmvc/testPathVariable/asbc">test path variable</a>
	<br><br>

对应的类和方法:

	@RequestMapping("springmvc")
	@Controller
	public class TestMapping {
		/**
		 * pathVariable 占位符 
		 * RequestMapping中的{id}与方法中的id名称要对应
		 * @param id
		 * @return
		 */
		@RequestMapping(value="testPathVariable/{id}")
		public String testPathVariable(@PathVariable("id") String id){
			System.out.println("testPathVariable : "+  id);
			return "success";
		}
	}

# 8.Rest风格
## a. HTTP支持4种方式：GET,POST,PUT,DELETE
## b. form表单仅支持GET和POST两种请求
## c. SpringMVC利用HiddenHttpMethodFilter过滤器可以将post请求转换为PUT或者DELETE请求
## d. 在web.xml文件中配置HiddenHttpMethodFilter过滤器

	<filter>
		<filter-name>HiddenHttpMethodFilter</filter-name>
		<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
	</filter>
	
	<filter-mapping>
		<filter-name>HiddenHttpMethodFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

例子：

		<form action="springmvc/testRest/1" method="post">
			<input type="hidden" name="_method" value="PUT"/>
			<input type="submit" value="TestRest PUT"/>
		</form>
	
		<br><br>
		<form action="springmvc/testRest/1" method="post">
			<input type="hidden" name="_method" value="DELETE"/>
			<input type="submit" value="TestRest DELETE"/>
		</form>
		
		<br><br>
		<form action="springmvc/testRest" method="post">
			<input type="submit" value="TestRest POST"/>
		</form>
		
		<br><br>
		<a href="springmvc/testRest/1">Test Rest Get</a>

对应的类和方法：

		
	@RequestMapping("springmvc")
	@Controller
	public class TestMapping {
		
		
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
	}

注意： SpringMVC使用Rest风格可能出现如下错误：
<font color="red">JSPs only permit GET POST or HEAD</font>
修正方法：①使用Tomcat7及以下版本②返回的jsp配置

#9. RequestParam
例子：

	<a href="springmvc/testRequestParam?username=dingye&age=11">Test RequestParam</a>
对应的类和方法:

	@RequestMapping("springmvc")
	@Controller
	public class TestMapping {
		
		//RequestParam,可以判断是否需要添加此参数
		@RequestMapping(value="testRequestParam")
		public String testRequestParam(@RequestParam(value="username")String username,
									@RequestParam(value="age",required=false)String age ){
			System.out.println("test request param, username : " + username + " ,age : " + age );
			return "success";
		}
	}

# 10. RequestHeader

	@RequestMapping("springmvc")
	@Controller
	public class TestMapping {
		
		@RequestMapping(value="testRequestHeader")
		public String testRequestHeader(@RequestHeader("Accept")String accept){
			System.out.println(accept);
			return "success";
		}
	}