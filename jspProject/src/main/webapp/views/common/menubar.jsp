<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
    a {
        text-decoration: none;
        color: white;
    }
    .login-area > * {
        float:right;
    }

    .nav-area {
        background: black;
    }
    .menu {
        display: table-cell;
        width: 150px;
        height: 50px;
    }

    .menu a {
        line-height: 50px;
        font-size: 20px;
        font-weight: bold;
        text-align: center;
        width: 100%;
        height: 100%;
        display: block;
    }
</style>
</head>
<body>
	
	<h1 align="center">Welcome KH World</h1>
    <div class="login-area">
        <!-- 로그인 전 -->
        <form action="<%=contextPath%>/login.me" method="POST">
        <table>
            <tr>
                <th>아이디</th>
                <td><input type="text" name="userId" required></td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" name="userPwd" required></td>
            </tr>
            <tr>
                <th colspan="2">
                    <input type="submit" value="로그인"></input>
                    <input type="button" value="회원가입"></input>
                </th>
            </tr>
        </table>
        </form>

        <!-- 로그인 후 -->
        <!-- <div>
            <b>___</b>님의 방문을 환영합니다. <br><br>
            <div align="center">
                <a href="">마이페이지</a>
                <a href="">로그아웃</a>
            </div>
        </div> -->
    </div>

    <br clear="both"><br>

    <div class="nav-area" align="center">
        <div class="menu">
            <a href="">HOME</a>
        </div>

        <div class="menu">
            <a href="">공지사항</a>
        </div>

        <div class="menu">
            <a href="">일반게시판</a>
        </div>

        <div class="menu">
            <a href="">사진게시판</a>
        </div>

    </div>

</body>
</html>