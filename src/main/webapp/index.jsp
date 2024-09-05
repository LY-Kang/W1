<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>數字加法</title>
</head>
<body>
    <h2>數字加法器</h2>
    <form action="index.jsp" method="post">
        <label for="num1">數字 1:</label>
        <input type="text" id="num1" name="num1" required>
        <br><br>
        <label for="num2">數字 2:</label>
        <input type="text" id="num2" name="num2" required>
        <br><br>
        <input type="submit" value="計算">
    </form>

    <%
        // 讀取用戶提交的數字
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        if (num1Str != null && num2Str != null) {
            try {
                // 將輸入轉換為整數
                int num1 = Integer.parseInt(num1Str);
                int num2 = Integer.parseInt(num2Str);

                // 計算總和
                int sum = num1 + num2;

                // 顯示結果
                out.println("<h3>計算結果: " + sum + "</h3>");
            } catch (NumberFormatException e) {
                // 如果輸入不是數字，顯示錯誤信息
                out.println("<h3 style='color: red;'>錯誤: 輸入的值必須是數字。</h3>");
            }
        }
    %>
</body>
</html>
