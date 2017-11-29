package com.crypted2.estrelasdonorte.database;

import com.crypted2.estrelasdonorte.EstrelasConfig;
import com.crypted2.estrelasdonorte.model.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
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
 * <p>
 * http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#GeneratedId-Column
 */
public class DbConnector {
    private Logger logger = LoggerFactory.getLogger(DbConnector.class);
    private ConnectionSource connection;

    private static List<Class<?>> createTableList = Arrays.asList(
            LiveConcertMusic.class,
            LiveConcertProgram.class,
            Music.class,
            UserProgram.class
    );

    public DbConnector() {

    }

    public void init() throws SQLException {
        connection = new JdbcConnectionSource(EstrelasConfig.Database.URL);
        createTables();
    }

    public ConnectionSource getConnection() {
        return connection;
    }

    public void closeConnection() throws IOException {
        connection.close();
    }

    private <T> Dao<T, Integer> getDao() {
        return null; // ToDo this
    }

    /**
     * Create (in CRUD)
     * Insert a new object to the DB
     *
     * @param toCreate Object to create in the Database
     * @return True if create successfully, Else otherwise
     */
    public <T> boolean create(T toCreate) {
        try {
            Dao<T, Integer> dao = DaoManager.createDao(connection, (Class<T>) toCreate.getClass());
            dao.create(toCreate);
        } catch (SQLException e) {
            logger.error("Not possible to insert the record: {}", e.getMessage());
            return false;
        }

        return true;
    }


    public void doStuff() throws SQLException {
        // instantiate the DAO to handle Account with Integer id
        Dao<Music, Integer> accountDao = DaoManager.createDao(connection, Music.class);

        // create an instance of Account
        Music account = new Music(
                "A Cabritinha",
                "Quim Barrieros",
                MusicGenre.PIMBA.ordinal(),
                "LoL");

        // persist the account object to the database
        accountDao.create(account);
        int id = account.getId();

        // retrieve the account
        Music account2 = accountDao.queryForId(id);

        Map<String, Object> toQuery = new HashMap<String, Object>();
        toQuery.put("title", "A Cabritinha");

        List<Music> account3 = accountDao.queryForFieldValues(toQuery);
        logger.debug("LIST FOUND: " + account3.size());
        account3.forEach(m -> logger.debug("{}", m.getId()));

        // show its password
        logger.debug("Account: " + id + " --> " + account2.getAuthor());
    }

    /**
     * Create tables if not exist in the Database
     */
    private void createTables() {
        createTableList.forEach(clazz -> {
            try {
                TableUtils.createTableIfNotExists(connection, clazz);
            } catch (SQLException e) {
                logger.error("Failed to create the table {}: {}", clazz.getSimpleName(), e.getMessage());
            }
        });

    }

    /**
     * Drop tables.
     * Warning: This is a destructive operation !!
     */
    private void dropTables() {
        createTableList.forEach(clazz -> {
            try {
                TableUtils.dropTable(DaoManager.createDao(connection, clazz), true);
            } catch (SQLException e) {
                logger.error("Failed to create the table {}: {}", clazz.getSimpleName(), e.getMessage());
            }
        });
    }
}
