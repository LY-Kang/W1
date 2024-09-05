package javaweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC 驅動與連接設定
    private static final String JDBC_URL = "jdbc:h2:./java-course"; // H2 的連線 URL
    private static final String USER = "sa"; // 預設用戶名
    private static final String PASSWORD = "sa"; // 預設密碼

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 設定回應的內容類型為 HTML
        response.setContentType("text/html;charset=utf-8");

        // 取得 PrintWriter 物件，用來寫入 HTML 內容
        PrintWriter out = response.getWriter();

        // 開始寫入 HTML 內容
        out.println("<html>");
        out.println("<head>");
        out.println("<title>H2 資料庫內容</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>資料庫表格內容</h1>");

        // JDBC 連接、語句和結果集
        try {
            // 載入 H2 JDBC 驅動
            Class.forName("org.h2.Driver");
            System.out.println("H2 JDBC 驅動載入成功!");

            // 建立資料庫連接
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                 Statement statement = connection.createStatement()) {

                // 連線成功，執行 SQL 查詢
                String sql = "SELECT * FROM INFORMATION_SCHEMA.TABLES";
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    // 開始寫入表格 HTML 內容
                    out.println("<table border='1'>");
                    out.println("<tr>");
                    out.println("<th>表名</th>");
                    out.println("</tr>");

                    // 處理查詢結果
                    while (resultSet.next()) {
                        String tableName = resultSet.getString("TABLE_NAME");
                        out.println("<tr>");
                        out.println("<td>" + tableName + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                } catch (SQLException e) {
                    out.println("<p>查詢失敗: " + e.getMessage() + "</p>");
                }
            } catch (SQLException e) {
                out.println("<p>連線失敗: " + e.getMessage() + "</p>");
            }
        } catch (ClassNotFoundException e) {
            out.println("<p>H2 JDBC 驅動載入失敗: " + e.getMessage() + "</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
