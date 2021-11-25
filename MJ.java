import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;



public class MJ extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://123.60.55.200/linux_final";
    static final String USER = "root";
    static final String PASS = "Aa123456..";
    Connection conn = null;
    Statement stmt = null;
    public static String NAME;
    public static int ID;


    public static void main(String[] args) {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT id,name FROM t_student WHERE id=4";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ID = rs.getInt("id");
                NAME = rs.getString("name");
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (
                SQLException se) {
            se.printStackTrace();
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();

            }
        }
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Student stu = new Student(ID,NAME);
        String json = gson.toJson(stu);

        out.println(json);
        out.flush();
        out.close();


    }
}

class Student {
    String name;
    int id;
public Student(int id,String name){
    this.id = id;
    this .name = name;
}
}
