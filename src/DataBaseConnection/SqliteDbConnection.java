package DataBaseConnection;


import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


public class SqliteDbConnection implements IdbConnection {

    private Properties props;
    private Connection conn;

    /**
     * creates a db using name from config.properties file
     */
    public SqliteDbConnection(boolean create) {
        props = new Properties();
        String propFileName = "config.properties";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                props.load(inputStream);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }finally {
            if(create&& props!=null){

                String url = props.getProperty("dbUrl");

                try (Connection conn = DriverManager.getConnection(url)) {
                    if (conn != null) {
                        DatabaseMetaData meta = conn.getMetaData();
                        System.out.println("The driver name is " + meta.getDriverName());
                        System.out.println("A new database has been created.");
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public void connectToDb() {
        conn = null;
        try {
            // db parameters
            String url = this.props.getProperty("dbUrl");
            // create a connection to the database
            conn = DriverManager.getConnection(url, "yishaiazabary", "Yi82Za65");

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String[] getEntryById(String tableName,String entryId) {
        String[] ans=null;
        if (conn == null) {
            System.out.println("you have to connect to the DB first, use [dbInstance].connectToDb() function");
        }
        else{
            this.connectToDb();
            ans=new String[3];
            String sql = "SELECT id, first_name, score FROM "+tableName+"WHERE id="+entryId;
            try (Connection tempConn = this.conn;
                 Statement stmt  = tempConn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sql)){

                ans[0]=Integer.toString(rs.getInt("id"));
                ans[1]=rs.getString("first_name");
                ans[2]=Double.toString(rs.getDouble("score"));

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return ans;
        }
        return ans;
    }

    @Override
    public void deleteAllFromTable(String tableName) {
        this.connectToDb();
        String sql = "DELETE FROM "+tableName;

        try (Connection conn = this.conn;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteDb(String dbName) {

    }

    @Override
    public void updateEntry(int id, String first_name, double score) {
        this.connectToDb();
        String sql = "UPDATE "+this.props.getProperty("dbName")+" SET first_name = ? , "
                + "score = ? "
                + "WHERE id = ?";

        try (Connection tempConn = this.conn;
             PreparedStatement pstmt = tempConn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, first_name);
            pstmt.setDouble(2, score);
            pstmt.setInt(3, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteById(int id,String tableName){
        this.connectToDb();
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";

        try (Connection conn = this.conn;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List getAllFromTable(String tableName) {
        this.connectToDb();
        LinkedList<String[]> ans=new LinkedList<String[]>();
        String[] tempStringArray;

        String sql = "SELECT id, first_name, score FROM "+tableName;

        try (Connection tempConn = this.conn;
             Statement stmt  = tempConn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                tempStringArray = new String[3];
                tempStringArray[0]=Integer.toString(rs.getInt("id"));
                tempStringArray[1]=rs.getString("first_name");
                tempStringArray[2]=Double.toString(rs.getDouble("score"));
                ans.add(tempStringArray);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ans;
    }

    @Override
    public void insert(String tableName, int id, String first_name, double score) {
        String sql = "INSERT INTO "+tableName+"(id,first_name,score) VALUES(?,?,?)";
        this.connectToDb();
        try (Connection conn = this.conn;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, first_name);
            pstmt.setDouble(3, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (this.conn != null) {
                this.conn.close();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void createNewTable(String tableName) {
        // SQLite connection string
        String url = this.props.getProperty("dbUrl");

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS "+tableName+" (\n"
                + "	id integer,\n"
                + "	first_name text NOT NULL,\n"
                + "	score real\n"
                + ");";

        try (Connection tempConn=this.conn;
             Statement stmt = tempConn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}