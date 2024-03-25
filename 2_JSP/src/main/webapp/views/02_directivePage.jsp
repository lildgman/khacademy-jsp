<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, java.util.Date" %>
<!-- import는 길어질 수 있으니 따로 빼서 쓰기도 함 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>page지시어</h1>
	
	<%
		// 위쪽에서 import 해줘야함
		ArrayList<String> list = new ArrayList<>();
		list.add("Servlet"); //0번째 index
		list.add("JSP"); // 1번째 index
		Date today = new Date();
	%>
	
	현재날짜 및 시간: <%=today %> <br>
	0번째 인덱스: <%=list.get(0) %> <br>
	1번째 인덱스: <%=list.get(1) %> <br>
</body>
</html>