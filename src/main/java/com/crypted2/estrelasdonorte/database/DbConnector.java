package com.crypted2.estrelasdonorte.database;

import com.crypted2.estrelasdonorte.model.Music;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class used to connect to the database
 * <p>
 * https://github.com/xerial/sqlite-jdbc/blob/master/Usage.md
 * https://github.com/xerial/sqlite-jdbc/blob/master/demo/Sample.java
 * <p>
 * http://ormlite.com/sqlite_java_android_orm.shtml
 */
public class DbConnector {

    private Connection connection;

    public DbConnector() {

    }

    public void getConnection() throws SQLException, IOException {
        // this uses h2 but you can change it to match your database
        String databaseUrl = "jdbc:sqlite:";
// create a connection source to our database
        ConnectionSource connectionSource =
                new JdbcConnectionSource(databaseUrl);

// instantiate the DAO to handle Account with String id
        Dao<Music, StringProperty> accountDao =
                DaoManager.createDao(connectionSource, Music.class);

// if you need to create the 'accounts' table make this call
        TableUtils.createTable(connectionSource, Music.class);

// create an instance of Account
        Music account = new Music(
                new SimpleStringProperty("A Cabritinha"),
                new SimpleStringProperty("Quim Barrieros"),
                new SimpleStringProperty("Zé Carlos"),
//                MusicGenre.PIMBA,
                new SimpleStringProperty("PIMBA"),
                new SimpleIntegerProperty(138),
                new SimpleIntegerProperty(-1),
                new SimpleStringProperty("LoL"));

// persist the account object to the database
        accountDao.create(account);

// retrieve the account
        Music account2 = accountDao.queryForId(new SimpleStringProperty("A Cabritinha"));
// show its password
        System.out.println("Account: " + account2.getAuthor());

// close the connection source
        connectionSource.close();
    }

//    public void getConnection() {
//        try {
//            // create a database connection
//            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.
//
//            statement.executeUpdate("DROP TABLE IF EXISTS music");
//            statement.executeUpdate("CREATE TABLE \"music\" (\n" +
//                    "\"title\"  TEXT NOT NULL,\n" +
//                    "\"author\"  TEXT,\n" +
//                    "\"singer\"  TEXT,\n" +
//                    "\"genre\"  TEXT,\n" +
//                    "\"speed\"  INTEGER,\n" +
//                    "\"transpose\"  INTEGER,\n" +
//                    "\"pdfFileName\"  TEXT,\n" +
//                    "PRIMARY KEY (\"title\")\n" +
//                    ");");
//
//            statement.executeUpdate("INSERT INTO music VALUES(" +
//                    "'A Cabritinha', " +
//                    "'Quim Barreiros'," +
//                    "'Zé'," +
//                    "'PIMBA'," +
//                    "141," +
//                    "-1," +
//                    "'Unknown'" +
//                    ");");
//
//            ResultSet rs = statement.executeQuery("SELECT * FROM music");
//            while (rs.next()) {
//                // read the result set
//                System.out.println("title = " + rs.getString("title"));
//                System.out.println("author = " + rs.getInt("author"));
//            }
//        } catch (SQLException e) {
//            // if the error message is "out of memory",
//            // it probably means no database file is found
//            System.err.println(e.getMessage());
//        } finally {
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e) {
//                // connection close failed.
//                System.err.println(e);
//            }
//        }
//    }
}
