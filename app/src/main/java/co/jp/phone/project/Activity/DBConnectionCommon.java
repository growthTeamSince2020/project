package co.jp.phone.project.Activity;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * @author growth
 * @date 2020/09/22.
 */
class DBConnectionCommon {

        static Connection getConnection() throws Exception {
            //JDBCドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //各設定
            String url = "jdbc:mysql://localhost:3306/mysqlserver?Timezone=JST";
            String user = "root";
            String password = "root";
            //データベースに接続
            Connection con = DriverManager.getConnection(url,user,password);
            return con;
        }
}

