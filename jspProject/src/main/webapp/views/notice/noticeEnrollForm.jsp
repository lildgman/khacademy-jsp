<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    #enroll-form table {
        border: 1px solid white;
    }

    #enroll-form table input, #enroll-form table textarea {
        width: 100%;
    }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2 align="center">공지사항 작성하기</h2>
        <br>

        <form action="<%=contextPath %>/insert.no" id="enroll-form" method="POST">
            <table>
                <tr>
                    <th width="50">제목</th>
                    <td width="450"><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <th>내용</th>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="content" id="" cols="30" rows="10" style="resize:none"></textarea>
                    </td>
                </tr>
            </table>
            
            <br><br>
            
            <div align="center">
                <button type="submit" class="btn btn-sm text-white">등록하기</button>
                <button type="reset" class="btn btn-sm text-white">초기화</button>
                <button type="button" onclick="history.back()" class="btn btn-sm text-white">뒤로가기</button>
            </div>

        </form>
    </div>

</body>
</html>