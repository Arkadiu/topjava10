package ru.javawebinar.topjava.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Alexander on 28.03.2017.
 */
public class DbUtil {

    public static Connection getConnection() {
        Connection connection = null;

        if (connection != null)
            return connection;
        else {
            try (InputStream inputStream = DbUtil.class.getResourceAsStream("/db.properties")) {
                //load properties file
                Properties prop = new Properties();
                prop.load(inputStream);

                //asign db parameters
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");

                //create a connection to the base

/*                Driver driver = new FabricMySQLDriver();
                DriverManager.registerDriver(driver);
                */
                Class.forName(driver);

                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
