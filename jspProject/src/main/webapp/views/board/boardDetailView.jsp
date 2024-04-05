<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.model.vo.Board,com.kh.common.Attachment" %>
<%
    Board board = (Board)request.getAttribute("board");
	Attachment attachment = (Attachment)request.getAttribute("attachment");
	// 없을 수도 있다. -> null
	// 있다면 파일번호, 원본명, 수정명, 저장경로가 있을 것이다.

// 글번호, 카테고리명, 제목, 내용, 작성자, 작성일자
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
        height: 1000px;
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
    <div class="outer">
        <br>
        <h2 align="center">일반게시판 상세보기</h2>

        <table id="detail-area" border="1" align="center">
            <tr>
                <th width="70">카테고리</th>
                <td width="70"><%=board.getCategory() %></td>
                <th width="70">제목</th>
                <td width="350"><%=board.getBoard_title() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%=board.getBoard_writer() %></td>
                <th>작성일</th>
                <td><%=board.getCreate_date() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 200px"><%=board.getBoard_content() %></p>  
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                   
                    <% if(attachment == null) { %>
						 <!-- 첨부파일 없을 경우 -->
                   		 첨부파일이 없습니다.
					<% } else { %>
                    <!-- 첨부파일 있을 경우  경로: /kh/resource/board_upfile/바뀐 파일명-->
                    <a download="<%=attachment.getOriginName() %>" href="<%=contextPath %>/<%=attachment.getFilePath() + attachment.getChangeName() %>"><%=attachment.getOriginName() %></a>
					<% } %>
                </td>
            </tr>
        </table>
        <br>
        <div align="center">
            <a href="<%=contextPath %>/list.bo?cpage=1" class="btn btn-sm btn-secondary">목록가기</a>
            <%if(loginUser != null && loginUser.getUserId().equals(board.getBoard_writer())) { %>
            	<a href="<%=contextPath %>/updateForm.bo?bno=<%=board.getBoard_no() %>" class="btn btn-sm btn-warning">수정하기</a>
            	<a href="<%=contextPath %>/delete.bo?bno=<%=board.getBoard_no() %>" class="btn btn-sm btn-danger">삭제하기</a>
            <% } %>
        </div>
        
        <br>

        <div id="reply-area">
            <table align="center">
                <thead>
                    <tr>
                        <th>댓글작성</th>
                        <%if(loginUser != null) { %>
	                        <td>
	                            <textarea id="reply-content" cols="50" rows="3" style="resize: none;"></textarea>
	                        </td>
	                        <td>
	                            <button onclick="insertReply();">댓글등록</button>
	                        </td>
                        <% } else { %>
                        	<td>
	                            <textarea id="reply-content" cols="50" rows="3" style="resize: none;" readonly>댓글을 작성하시려면 로그인을 해주세요.</textarea>
	                        </td>
	                        <td>
	                            <button disabled>댓글등록</button>
	                        </td>
                        <% } %>
                    </tr>
                </thead>
                <tbody>
                	<!-- <tr>
                		<td>user06</td>
                        <td>댓글남깁니다.</td>
                        <td>2024/03/05</td>
                    </tr>
                    <tr>
                		<td>user06</td>
                        <td>댓글남깁니다.</td>
                        <td>2024/03/05</td>
                    </tr>
                    <tr>
                		<td>user06</td>
                        <td>댓글남깁니다.</td>
                        <td>2024/03/05</td>
                    </tr> -->
                </tbody>
            </table>
            <script>
                $(function() {
                    selectReplyList();
                    setInterval(selectReplyList,2000);
                    
                })

                function selectReplyList() {
                    $.ajax({
                        url:"rlist.bo",
                        data: {
                            bno : <%=board.getBoard_no() %>
                        },
                        success: function(res) {
                            console.log(res);
                            let str = "";
                            for(let reply of res) {
                                str += ( "<tr>" +
                                        "<td>"+ reply.replyWriter + "</td>" +
                                        "<td>"+ reply.replyContent + "</td>" +
                                        "<td>"+ reply.createDate + "</td>" 
                                     + "</tr>")
                                    console.log(str)
                            }
                            
                            document.querySelector("#reply-area tbody").innerHTML = str;

                        },

                        error: function() {
                            console.log("댓글 작성 중 ajax 통신 실패");
                        }
                        
                    })
                }

                
                function insertReply() {
                    const boardNo = <%=board.getBoard_no() %>;
                    const content = document.querySelector("#reply-content").value;

                    $.ajax({
                        url: "rinsert.bo",
                        data : {
                            bno: boardNo,
                            content: content
                        },
                        type : "POST",
                        success: function(res) {
                            document.querySelector("#reply-content").value = "";
                            selectReplyList();
                            console.log(res);
                        }, 

                        error : function() {
                            console.log("댓글 작성 중 ajax 통신 실패")
                        }
                    })
                }
            </script>
        </div>
       
        
    </div>
</body>
</html>