package com.crypted2.estrelasdonorte.database;

import com.crypted2.estrelasdonorte.model.Music;
import com.crypted2.estrelasdonorte.model.MusicGenre;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used to connect to the database
 * <p>
 * https://github.com/xerial/sqlite-jdbc/blob/master/Usage.md
 * https://github.com/xerial/sqlite-jdbc/blob/master/demo/Sample.java
 * <p>
 * http://ormlite.com/sqlite_java_android_orm.shtml
 */
public class DbConnector {
    private Logger logger = LoggerFactory.getLogger(DbConnector.class);

    private Connection connection;

    public DbConnector() {

    }

    public void getConnection() throws SQLException, IOException {
        // this uses h2 but you can change it to match your database
        String databaseUrl = "jdbc:sqlite:sample.db";
// create a connection source to our database
        ConnectionSource connectionSource =
                new JdbcConnectionSource(databaseUrl);

// instantiate the DAO to handle Account with Integer id
        Dao<Music, Integer> accountDao =
                DaoManager.createDao(connectionSource, Music.class);

// if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource, Music.class);

// create an instance of Account
        Music account = new Music(
                "A Cabritinha",
                "Quim Barrieros",
                MusicGenre.PIMBA.ordinal(),
                "LoL");

// persist the account object to the database
        int id = accountDao.create(account);

// retrieve the account
        Music account2 = accountDao.queryForId(id);

        Map<String, Object> toQuery = new HashMap<String, Object>();
        toQuery.put("title", "A Cabritinha");

        List<Music> account3 = accountDao.queryForFieldValues(toQuery);
        logger.debug("LIST FOUND: " + account3.size());
        account3.forEach(m -> logger.debug("{}", m.getId()));

// show its password
        logger.debug("Account: " + id + " --> " + account2.getAuthor());

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
