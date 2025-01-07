package DBAPIConnect;

import java.sql.*;

public class BDAPI {
    final String urlConnect = "jdbc:postgresql://localhost/online_store?user=postgres&password=qwe";
    StringBuilder content = new StringBuilder();
    public StringBuilder DBAPIConnect (String Method){
        Connection con = GetConnect();

        if(Method == "DBConnectionTest"){
            return Test(con);
        }
        else  {

        }

        return new StringBuilder();
    }

    ///Методы
    private StringBuilder Test(Connection con){

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT \"last_name\" FROM \"customers\";");
            return ReadDBData(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    ///Доп функции
    private Connection GetConnect (){

        try {
            return  DriverManager.getConnection(urlConnect);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private StringBuilder ReadDBData(ResultSet rs){
        try {
            StringBuilder content = new StringBuilder();
            while (rs.next()){
                System.out.println(rs.getString(1));
                content.append(rs.getString(1));
            }
            return content;
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
