<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.notice.model.vo.Notice" %>

<%
	Notice notice = (Notice)request.getAttribute("notice");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer {
        background: black;
        color : white;
        width : 1000px;
        height: 500px;
        margin: auto;
        margin-top: 50px;
    }

    .outer table {
        border: 1px solid white;
        border-collapse: collapse;
    }

    .outer > table tr, .outer > table td {
        border: 1px solid white;
    }

    
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2 align="center">공지사항 상세보기</h2>
        <br>
        <table>
            <tr>
                <th width="70">제목</th>
                <td colspan="3" width="430"><%=notice.getTitle()%></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%=notice.getWriter()%></td>
                <th>작성일</th>
                <td><%=notice.getCreate_date()%></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 150px;"><%=notice.getContent()%></p>
                </td>
            </tr>

        </table>
        
        <br><br>
        
        <div>
            <a href="<%=contextPath%>/list.no" class="btn btn-sm btn-secondary text-white">목록가기</a>
            <% if(loginUser != null && loginUser.getUserId().equals(notice.getWriter())) { %>
            	<!-- 현재 로그인한 사용자가 해당 글을 쓴 본인일 때만 수정, 삭제 볼 수 있음 -->
            	
            	<a href="<%=contextPath %>/updateForm.no?num=<%=notice.getNotice_no() %>" class="btn btn-sm btn-warning text-white">수정하기</a>
            	<a href="<%=contextPath %>/delete.no?num=<%=notice.getNotice_no() %>" class="btn btn-sm btn-danger text-white">삭제하기</a>
            <% } %>
        </div> 
    </div>

</body>
</html>