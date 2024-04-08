<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>* EL(Expression language)</h1>
	<p>
		기존에 사용했던 표현식(출력식) &lt; &gt;와 같이 <br>
		JSP상에서 표현하고자 하는 값을 \${name}의 형식으로 표현해서 작성하는것
	</p>
	
	<h4><a href="el.do">01_EL의 기본구문</a></h4>
	<h4><a href="operation.do">02_EL의 연산자</a></h4>
	
	<h1>JSP Action Tag</h1>
	<!-- 
		*JSP를 이루는 구성
		1. 스크립팅원소: JSP페이지 내에서 자바코드를 직접 기술할 수 있게 하는 기술
			ex) 스크립틀릿
			
		2. 지시어: JSP페이지 정보에 대한 내용을 표현한다거나 또 다른 페이지를 포함할 때 사용
				 JSP 기능을 확장시키는 라이브러리를 등록할 때 사용
		
		3. 액션태그: jsp페이지에서 어떤 동작을 하도록 지시하는 태그
					xml기술을 이용하여 기존의 jsp문법을 확장하는 기술을 제공하는 태그
			>> 표준 액션 태그(Standard Action Tag): jsp페이지에서 바로 사용이 가능하다.(별도의 연동이 필요 없다.)
					 -> 모든 태그명 앞에 jsp: 접두어를 붙여서 사용	
			>> 커스텀 액션 태그(custom action tag): jsp페이지에서 바로 사용 불가능(별도의 라이브러리 연동 필요)
					접두어에 jsp:가 있는 모든 태그를 제외한 모든 태그
					가장 대표적이고 유용한 라이브러리: jstl
	 -->
	 
	 <h3>* 표준액션태그(Standard Action Tag)</h3>
	 <a href="views/2_Standard_Action_Tag/01_include.jsp">01_jsp:include</a><br>
	 <a href="views/2_Standard_Action_Tag/02_forward.jsp">02_jsp:forward</a><br>
	 
	 <h3>* Custom Action Tag</h3>
	 <a href="views/3_Custom_Action_Tag/01_core.jsp">1. core</a>
	 
	 
</body>
</html>