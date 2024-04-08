<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Core Library</h1>
	
	<h3>1. 변수(속성 == attribute)</h3>
	<pre>
	*변수 선언과 동시에 초기화(c:set var="변수명" value="값" [scope="저장객체"])
	- 변수 선언하고 초기값을 대입해두는 기능을 제공
	- 해당 변수를 어떤 scope에 setAttribute를 통해서 담아둘지 결정할 수 있다.
	=> c:set으로 선언한 변수는 접근할 때 무조건! EL로 접근해야 한다.
	</pre>
	
	<c:set var="num1" value="10"/> <!-- pageContext.setAttribute("num1", 10); -->
	<c:set var="num2" value="20" scope="request" /> <!-- request.setAttribute("num2", 20); -->
	num1변수: ${num1 } <br>
	num2변수: ${num2 } <br>
	
	<c:set var="result" value="${num1 + num2 }" scope="session"/>
	result변수: ${result }
	
	${pageScope.num1 } <br>
	${pageScope.result } <br>
		
	<c:set var="result" scope="request">
		12345
	</c:set>
	${requestScope.result }<br>
	
	<pre>
	*변수 삭제(c:remove var="제거하고싶은 변수명" [scope=""])
	- 해당 scope영역에 해당 변수를 찾아서 제거하는 태그
	- scope 지정 생략 시 모든 scope에서 해당 변수를 다 찾아서 해당 변수를 다 찾아서 제거
	=? 즉, 해당 scope에 .removeAttribute를 통해 제거하는거다 로 생각하면 ㅇㅋ
	</pre>
	
	삭제전: ${result } <br><br>
	
	1) 특정 scope 지정해서 삭제
	<c:remove var="result" scope="request"/> <br>
	request에서 삭제후 request:${result } <br><br>
	
	2) 모든 scopre에서 삭제
	<c:remove var="result" />
	모두 삭제 후 result : ${result } <br><br>

	<pre>
	*변수(데이터) 출력 (c:out value="출력하고자하는갑") [default="기본값"][escapeXml="true | false"] )
	=> 데이터를  출력하고자 할 때 사용하는 태그
	</pre>
	
	<c:out value="${result }" /> <br>
	<c:out value="${result }" default="없음" /> <br>
	
	<c:set var="outTest" value="<b>출력테스트<b>" /> <br>
	<c:out value="${outTest }" /> <br> <!-- escapeXml 생략 시 기본값이 true == 태그해석 안됨 -->
	<c:out value="${outTest }" escapeXml="false"/> <br>
	
	<hr>
	
	<h3>2. 조건문 - if(c:if test="조건식")</h3>
	<pre>
	-JAVA의 if문과 비슷한 역할을 하는 태그
	-조건식은 test속성에 작성(단, EL구문으로 기술해야한다.)
	
	</pre>
	
	<c:if test="${num1 > num2 }">
		<b>num1이 num2보다 큽니다.</b>
	</c:if>
	
	<c:if test="${num1 lt num2 }">
		<b>num1이 num2보다 작습니다.</b>
	</c:if>
	
	<br>
	
	<c:set var="str" value="안녕하세요" />
	
	<c:if test="${str eq '안녕하세요' }">
		<h3>어서오고</h3>
	</c:if>
	
	<c:if test="${str ne '안녕하세요' }">
		<h3>누구세용</h3>
	</c:if>
	
	<h3>3.조건문 - choose(c:choose, c:when, c:otherwise)</h3>
	
	<c:choose>
		<c:when test="${num1 gt 30 }">
			num1은 30보다 크다
		</c:when>
		<c:when test="${num1 gt 20 }">
			num1은 20보다 크다
		</c:when>
		<c:when test="${num1 gt 10 }">
			num1은 10보다 크다
		</c:when>
		
		<c:otherwise>
			맞는게 없어
		</c:otherwise>
	</c:choose>
</body>
</html>