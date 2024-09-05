import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connect_to_H2 {
    private static final String JDBC_URL = "jdbc:h2:./java-course"; // H2 的連線 URL
    private static final String USER = "sa"; // 預設用戶名
    private static final String PASSWORD = "sa"; // 預設密碼（空）

    public static void main(String[] args) {
        // 載入 H2 JDBC 驅動
        try {
            Class.forName("org.h2.Driver");
            System.out.println("H2 JDBC 驅動載入成功!");
        } catch (ClassNotFoundException e) {
            System.err.println("H2 JDBC 驅動載入失敗: " + e.getMessage());
            return;
        }

        // JDBC 連接、語句和結果集
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
             
            // 連線成功，輸出訊息
            System.out.println("連線成功!");

            // 執行 SQL 查詢
            String sql = "SELECT * FROM INFORMATION_SCHEMA.TABLES";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                // 處理查詢結果
                while (resultSet.next()) {
                    //int empId = resultSet.getInt("EMP_ID");
                    String name = resultSet.getString("TABLE_NAME");
                    //java.sql.Date birthDate = resultSet.getDate("BIRTH_DATE");
                    //String sex = resultSet.getString("SEX");
                    //double salary = resultSet.getDouble("SALARY");
                    //int branchId = resultSet.getInt("BRANCH_ID");
                    //int supId = resultSet.getInt("SUP_ID");

                    //System.out.printf("EMP_ID: %d, NAME: %s, BIRTH_DATE: %s, SEX: %s, SALARY: %.2f, BRANCH_ID: %d, SUP_ID: %d%n",
                          //  empId, name, birthDate, sex, salary, branchId, supId);
                    System.out.println(name);
                }
            } catch (SQLException e) {
                System.err.println("查詢失敗: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.err.println("連線失敗: " + e.getMessage());
        }
    }
}
