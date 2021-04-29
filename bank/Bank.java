package bank;


import java.sql.*;
import java.util.Arrays;

public class Bank {

    /*
        Strings de connection à la base postgres
     */
    // private static final String JDBC_DRIVER = "org.postgresql.Driver";
    // private static final String DB_URL = "jdbc:postgresql://localhost:5439/postgres";
    // private static final String DB_USER = "postgres";

    /*
        Strings de connection à la base mysql, à décommenter et compléter avec votre nom de bdd et de user
     */
     private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
     private static final String DB_URL = "jdbc:mysql://localhost:3306/bankkata";
     private static final String DB_USER = "root";
     private static final String DB_PASS = "root";

    private static final String TABLE_NAME = "accounts";

    private Connection c;

    public Bank() {
        initDb();

        // TODO
    }

    private void initDb() {
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Opened database successfully");

            // TODO Init DB

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void closeDb() {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("Could not close the database : " + e);
        }
    }

    // Modification postgres -> mysql
    void dropAllTables() {
        try (Statement s = c.createStatement()) {
            s.executeUpdate(
                    "DROP TABLE accounts;");
            s.executeUpdate(
                    "CREATE TABLE accounts(`name_account` varchar(40) NOT NULL," +
                            "`balance` int(11) DEFAULT '0'," +
                            "`threshold` int(11) NOT NULL DEFAULT '0'," +
                            "`statut` VARCHAR(5) DEFAULT 'f' );");
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public void createNewAccount(String name_account, int balance, int threshold) {
        try (Statement s = c.createStatement()) {
            if(threshold <= 0){
                String request = "insert into " + TABLE_NAME + " values ( '" + name_account + "'," + balance + ", " + threshold + ", 'f')";
                int rowCount = s.executeUpdate(
                        request);
                System.out.println(rowCount);
            }
            } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public String printAllAccounts() {
        String val = "";
        try (Statement s = c.createStatement()) {
            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM accounts";

            // execute the query, and get a java resultset
            ResultSet rs = s.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                Account acc = new Account(rs.getString("name_account"), rs.getInt("balance"),rs.getInt("threshold"),rs.getString("statut"));
                val += acc.toString();
            }
            s.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return val;
    }

    public void changeBalanceByName(String name_account, int balanceModifier) {
        try (Statement s = c.createStatement()) {
            // our SQL UPDATE query.
            String query = "UPDATE accounts SET balance = balance + "+balanceModifier+" WHERE name_account = '"+name_account+"' AND statut = 'f' AND balance + "+balanceModifier+" > threshold";

            s.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void blockAccount(String name_account) {
        try (Statement s = c.createStatement()) {
            // our SQL UPDATE query.
            String query = "UPDATE accounts SET statut = 't' WHERE name_account = '"+name_account+"'";
            System.out.println(query);
            s.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // For testing purpose
    String getTableDump() {
        String query = "select * from " + TABLE_NAME;
        String res = "";

        try (PreparedStatement s = c.prepareStatement(query)) {
            ResultSet r = s.executeQuery();

            // Getting nb colmun from meta data
            int nbColumns = r.getMetaData().getColumnCount();

            // while there is a next row
            while (r.next()){
                String[] currentRow = new String[nbColumns];

                // For each column in the row
                for (int i = 1 ; i <= nbColumns ; i++) {
                    currentRow[i - 1] = r.getString(i);
                }
                res += Arrays.toString(currentRow);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return res;
    }
}
