import java.sql.*;
import java.util.List;

public class ConnectionDB {
    private java.sql.Connection con = null;
    private final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=LogParser;integratedSecurity=true;";

    private String getConnectionUrl() {
        return url;
    }

    public void InsertLogsToDataBase(List<ParseData> parseDataList) {

        String insertSqlPattern = "INSERT INTO dbo.Parser (Date, Stream, Log, Message) VALUES "
                + "('%s', '%s', '%s', '%s');";

        StringBuilder insertSql = new StringBuilder();
        for (ParseData parseData : parseDataList) {
            insertSql.append(String.format(insertSqlPattern, parseData.date, parseData.stream, parseData.log, parseData.message)).append('\n');
        }

        try (Connection connection = getConnection()) {

            PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql.toString(), Statement.RETURN_GENERATED_KEYS);
            {
                prepsInsertProduct.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    private java.sql.Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = java.sql.DriverManager.getConnection(getConnectionUrl());
            if (con != null) {
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
        }
        return con;
    }

    private void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            con = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
