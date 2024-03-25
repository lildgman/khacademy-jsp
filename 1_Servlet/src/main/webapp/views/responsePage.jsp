<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 스크립틀릿(scriptlet) == html 문서내에서 자바코드를 쓸 수 있는 영역, 서버내에서 실행됨
		
		String name = (String)request.getAttribute("name");
	
	%>
	
	<h1>개인정보 응답화면 - POST</h1>
	<span id = name><%=name%></span>
</body>
</html>