<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.Board,com.kh.common.Attachment" %>

<%
	Board board = (Board)request.getAttribute("board");
	
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .outer {
            background: black;
            color: white;
            width: 1000px;
            margin: auto;
            margin-top: 50px;
        }
        
        .detail-area td, .detail-area th {
        	border: 1px solid white;
        	text-align:center;
        }
    </style>
</head>

<body>
    <%@ include file="../common/menubar.jsp" %>
        <div class="outer">
            <br>
            <h2 align="center">사진게시판 상세보기</h2>
            <br>

            <table class="detail-area" align="center">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3" width="600"><%=board.getBoard_title() %></td>
                </tr>
                <tr>
                	<th>작성자</th>
                	<td><%=board.getBoard_writer() %></td>
                	<th>작성일</th>
                	<td><%=board.getCreate_date() %></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"><p style="height: 50px;"><%=board.getBoard_content() %></p></td>
                </tr>
                <tr>
                    <th>대표이미지</th>
                    <td colspan="3">
                        <img src="<%=contextPath %>/<%=list.get(0).getFilePath() + list.get(0).getChangeName() %>" width="500" height="500">
                    </td>
                </tr>
                <tr>
                    <th>상세이미지</th>
                   	<td colspan="3">
                   		<div>
                   		<%for(int i = 1; i<list.size(); i++) { %>
                   			<img src="<%=contextPath %>/<%=list.get(i).getFilePath() + list.get(i).getChangeName() %>" width="200" height="150">
                   		<% } %>
                   		</div>
                   	</td>
                </tr>
            </table>

            <br>

            <div align="center">
                <a href="<%=contextPath %>/list.th" class="btn btn-sm btn-secondary">목록가기</a>
            </div>


        </div>
</body>

</html>