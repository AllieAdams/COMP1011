package Models;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private static String userName = "root";
    private static String password = "";

    public static ArrayList<String> getPhoneManufacturers() throws SQLException {
        ArrayList<String> manufacturers = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",
                    userName, password);

            //2.  Create a statement object
            statement = conn.createStatement();

            //3.  create and execute the query
            resultSet = statement.executeQuery("SELECT * FROM manufacturers");

            //4.  loop over the results and add to the ArrayList
            while (resultSet.next())
            {
                manufacturers.add(resultSet.getString("manufacturer"));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }

        return manufacturers;
    }

    public static String getOSForManufacturer(String manufacturer) throws SQLException {
        String os=null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            //1. Connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",
                    userName, password);

            //2. Create a SQL statement
            String sql = "SELECT os FROM manufacturers WHERE manufacturer = ?";

            //3. Create prepared statement
            ps = conn.prepareStatement(sql);

            //4. Bind the parameter(s)
            ps.setString(1, manufacturer);

            //5. get the results
            resultSet = ps.executeQuery();

            //6. Loop over the results
            while (resultSet.next())
            {
                System.out.println(resultSet.getString("os"));
                os = resultSet.getString("os");
            }
        }catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();

            if (ps != null)
                ps.close();

            if (resultSet != null)
                resultSet.close();
            }
            return os;

        }
    }

