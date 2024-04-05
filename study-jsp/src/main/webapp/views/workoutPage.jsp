<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>PS5 - WORKOUT</title>
    <link rel="stylesheet" href="resources\workoutbody-style.css">
</head>

<body>
    <%@ include file="common/menubar_odg.jsp" %>
        <div id="content-container" style="margin: 0 auto;">
            <div class="left-container">
                <!-- 내용이 안들어있을 때 -->
                <h2>기록하기</h2>
                <table class="workout-table">
                    <tbody>
                        <tr>
                            <td colspan="4">
                                <button id="addWorkout">운동 추가하기</button>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <!-- 내용 들어있을 때 -->
                <table class="workout-table" align="center">
                        <tr>
                            <th rowspan="4" style="width: 200px;">
                                <div>벤치프레스</div>
                                <div>가슴</div>
                            </th>
                            <th>세트</th>
                            <th>중량</th>
                            <th>횟수</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>60</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>70</td>
                            <td>10</td>
                        </tr>
                </table>
            </div>
            <div class="right-container">

            </div>
        </div>

</body>

</html>