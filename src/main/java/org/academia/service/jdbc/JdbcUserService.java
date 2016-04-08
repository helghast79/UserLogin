package org.academia.service.jdbc;


/**
 * Created by codecadet on 28/03/16.
 */
/*
public class JdbcUserService implements UserService {


    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/javabase";

    //  Database credentials
    static final String USER = "java";
    static final String PASS = "password";

    public JdbcUserService() {

    }


    private Connection getConnection() {

        Connection conn = null;

        try {
            //Class.forName(JDBC_DRIVER); //not needed since JDBC 4.0
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        return conn;
    }

    @Override
    public boolean authenticate(String userName, String password) {
        boolean result = false;

        Connection connection = getConnection();

        //no connection
        if(connection==null){
            return false;
        }

        User user = null;

        try {

            String sql = "SELECT * FROM users WHERE users.username=? AND users.password=?";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1,userName);
            stmt.setString(2,password);

            // execute the query
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                result = true;
            }

            //close statement
            if (stmt != null) {
                stmt.close();
            }

            //close resultSet
            if (resultSet != null) {
                resultSet.close();
            }

            //close connection
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }


        return result;
    }

    @Override
    public void addUser(User user) {

        Connection connection = getConnection();


        //no connection
        if(connection==null){
            return ;
        }


        try {

            connection.setAutoCommit(false);

            String sql = "INSERT INTO users (username, password, email) VALUES (?,?,?)";

            // create a new statement
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getEmail());

            stmt.executeUpdate();

            if (stmt != null) {
                stmt.close();
            }

            //close connection
            if (connection != null) {
                connection.close();
            }



        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }


    }

    @Override
    public User findByName(String name) {

        User user = null;

        Connection connection = getConnection();


        //no connection
        if(connection==null){
            return null;
        }


        try {

            int result = 0;

            String sql = "SELECT * FROM users WHERE users.username=?";


            // create a new statement
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,name);

            // execute the query
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()) {

                String usernameValue = resultSet.getString("username");
                String passwordValue = resultSet.getString("password");
                String emailValue = resultSet.getString("email");

                user = new User(usernameValue, emailValue, passwordValue);

            }

            //close statement
            if (stmt != null) {
                stmt.close();
            }

            //close resultSet
            if (resultSet != null) {
                resultSet.close();
            }

            //close connection
            if (connection != null) {
                connection.close();
            }


        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }


        return user;

    }

    @Override
    public int count() {

        int result = 0;

        Connection connection = getConnection();

        //no connection
        if(connection==null){
            return -1;
        }



        try {

            // create a new statement
            Statement statement = connection.createStatement();

            // create a query
            String query = "SELECT COUNT(*) FROM user";

            // execute the query
            ResultSet resultSet = statement.executeQuery(query);

            // get the results
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }

            //close statement
            if (statement != null) {
                statement.close();
            }

            //close resultSet
            if (resultSet != null) {
                resultSet.close();
            }

            //close connection
            if (connection != null) {
                connection.close();
            }



        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }

        return result;

    }


    private boolean checkConnection(Connection connection) {
        if (connection == null) {
            return false;
        }

        return true;
    }


}
        */