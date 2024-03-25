<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>피자 주문 페이지</h1>

    <% 
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        // yyyy년 MM월 dd일
    %>
    <h2>오늘 날짜</h2>
    <h4><%=sdf.format(today)%></h4>
    <br><br>

    <!-- 
        주문자정보, 주문정보를 입력받아서 
        서버에 결제 요청(servlet에 전달)
        controller -> pizzaServlet.java 
        이후 응답을 위한 jsp페이지가 필요
        pizza > pizzaPayment.jsp        

     -->

     <form action="/jsp/confirmPizza.do" method="GET">
        <fieldset>주문자 정보
        <table>
            <tr>
                <td>이름: </td>
                <td><input type="text" name="userName" required></td>
            </tr>
            <tr>
                <td>전화번호: </td>
                <td><input type="text" name="phone" required></td>
            </tr>
            <tr>
                <td>주소: </td>
                <td><input type="text" name="address" required></td>
            </tr>
            <tr>
                <td>요청메세지: </td>
                <td><textarea type="text" name="message" required></textarea></td>
            </tr>
        </table>
    </fieldset>

    <fieldset>
        <legend>주문정보</legend>
            <table>
                <tr>
                    <th>피자</th>
                    <td>
                        <select name="pizza">
                            <option>콤비네이션</option>
                            <option>치즈</option>
                            <option>포테이토</option>
                            <option>고구마</option>
                            <option>하와이안</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>토핑</th>
                    <td>
                        <input type="checkbox" name="topping" value="고구마무스">고구마무스
                        <input type="checkbox" name="topping" value="페퍼로니">페퍼로니
                        <input type="checkbox" name="topping" value="포테이토">포테이토
                        <br>
                        <input type="checkbox" name="topping" value="치즈크러스트">치즈크러스트
                        <input type="checkbox" name="topping" value="치즈바이트">치즈바이트
                        <input type="checkbox" name="topping" value="파인애플">파인애플
                    </td>
                </tr>
                <tr>
                    <th>사이드</th>
                    <td>
                        <input type="checkbox" name="side" value="콜라">콜라
                        <input type="checkbox" name="side" value="사이다">사이다
                        <input type="checkbox" name="side" value="스파게트">스파게티
                        <br>
                        <input type="checkbox" name="side" value="버팔로윙">버팔로윙
                        <input type="checkbox" name="side" value="피클">피클
                        <input type="checkbox" name="side" value="갈릭소스">갈릭소스
                    </td>
                </tr>
                <tr>
                    <th>결제방식</th>
                    <td>
                        <input type="radio" name="payment" value="card" checked> 카드결제
                        <input type="radio" name="payment" value="cash"> 현금결제
                    </td>
                </tr>
            </table>
    </fieldset>

    <br>

    <input type="submit" value="주문">
    <input type="reset">
     </form>

</body>
</html>