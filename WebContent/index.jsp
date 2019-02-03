<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<a href="springmvc/testModelAndView">Test Model And View</a>
	
	<br><br>
	<a href="springmvc/testServletAPI">Test Servlet Api</a>

	<br><br>
	<form action="springmvc/testPojo" method="post">
		username:<input name="username" type="text"><br>
		password:<input name="password" type="text"><br>
		email:<input name="email" type="text"><br>
		city:<input name="address.city" type="text"><br> 
		province:<input name="address.province" type="text"><br> 
		<input type="submit" value="Submit"><br> 
	</form>

	<br>
	<a href="springmvc/testCookieValue">test Cookie value</a>

	<br><br>
	<a href="springmvc/testRequestHeader">Test Request Header</a>

	<br><br>
	<a href="springmvc/testRequestParam?username=dingye&age=11">Test RequestParam</a>
	
	<br><br>
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
	<br><br>
	
	<a href="springmvc/testPathVariable/asbc">test path variable</a>
	<br><br>

	<a href="springmvc/testAntPath/moredir/abc">test moredir ant path</a>

	<br><br>

	<a href="springmvc/testAntPath/more/abc">test more ant path</a>

	<br><br>
	<a href="springmvc/testAntPath/e/abc">test one ant path</a>

	<br><br>
	<a href="springmvc/testParamsHeader?username=Bill&age=10">testParamsHeader</a>

	<br><br>
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="submit">
	</form>

	<br>
	<a href="springmvc/testMethod">Test Method</a>

	<br>
	<a href="springmvc/requestMapping">Test RequestMapping</a>

	<br>
	<a href="helloworld">Hello World</a>

</body>
</html>