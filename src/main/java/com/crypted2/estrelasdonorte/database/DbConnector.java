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

    private static final List<Class<?>> createTableList = Arrays.asList(
            LiveConcertMusic.class,
            LiveConcertProgram.class,
            Music.class,
            UserProgram.class
    );
    private ConnectionSource connection;
    private static DbConnector dbConnector;

    private DbConnector() {
        try {
            init();
        } catch (SQLException e) {
            logger.error("Unable to initialize the DbConnector: {}", e.getMessage());
        }
    }

    public static DbConnector getInstance() {
        return dbConnector == null ? dbConnector = new DbConnector() : dbConnector;
    }

    private void init() throws SQLException {
        connection = new JdbcConnectionSource(EstrelasConfig.Database.URL);
        createTables();
    }

    public ConnectionSource getConnection() {
        return connection;
    }

    public void closeConnection() throws IOException {
        connection.close();
    }

    /**
     * Get Dao from the given class (id must be an integer)
     *
     * @param clazz Class used to get the instance of Dao
     * @return Instance of requested Dao
     */
    private <T> Dao<T, Integer> getDao(Class<T> clazz) {
        Dao<T, Integer> lookedUp = DaoManager.lookupDao(connection, clazz);
        try {
            return lookedUp != null ? lookedUp : DaoManager.createDao(connection, clazz);
        } catch (SQLException e) {
            logger.error("Unable to get Dao: {}", e.getMessage());
        }

        return null;
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

    /**
     * Read a result from DB using some conditions
     *
     * @param clazz      Class representing the object to fetch
     * @param conditions Conditions to use as filter
     * @return List of results
     */
    public <T> List<T> read(Class<T> clazz, Map<String, Object> conditions) {
        Dao<T, Integer> daoToUse = getDao(clazz);
        if (daoToUse == null) {
            logger.error("Unable to get Dao");
            return null;
        }

        try {
            return daoToUse.queryForFieldValues(conditions);
        } catch (SQLException e) {
            logger.error("Unable to read the result: {}", e.getMessage());
        }

        return null;
    }

    /**
     * Read all results present a DB belonging to a class
     *
     * @param clazz Class representing the object to fetch
     * @return List of results
     */
    public <T> List<T> readAll(Class<T> clazz) {
        Dao<T, Integer> daoToUse = getDao(clazz);
        if (daoToUse == null) {
            logger.error("Unable to get Dao");
            return null;
        }

        try {
            return daoToUse.queryForAll();
        } catch (SQLException e) {
            logger.error("Unable to read the result: {}", e.getMessage());
        }

        return null;
    }

    /**
     * Read a result from DB using a unique condition
     *
     * @param clazz Class representing the object to fetch
     * @param field Field to use
     * @param value Value to filter
     * @return List of results
     */
    public <T> List<T> read(Class<T> clazz, String field, Object value) {
        Map<String, Object> toQuery = new HashMap<>();
        toQuery.put(field, value);
        return read(clazz, toQuery);
    }

    /**
     * Read a result from DB using the id
     *
     * @param clazz Class representing the object to fetch
     * @param id    Id representing the object wanted
     * @return Result
     */
    public <T> T read(Class<T> clazz, int id) {
        Dao<T, Integer> daoToUse = getDao(clazz);
        if (daoToUse == null) {
            logger.error("Unable to get Dao");
            return null;
        }

        try {
            return (T) daoToUse.queryForId(id);
        } catch (SQLException e) {
            logger.error("Unable to read the result: {}", e.getMessage());
        }

        return null;
    }

    /**
     * Update an object using its id as reference (Update in CRUD)
     * <p>
     * //     * @param clazz        Class representing the object to update
     *
     * @param objectToUpdate Object updated
     * @return The number of rows updated in the Database (Should be 1)
     */
    public <T> int update(T objectToUpdate) {
        Dao<T, Integer> daoToUse = getDao((Class<T>) objectToUpdate.getClass());
        if (daoToUse == null) {
            logger.error("Unable to get Dao");
            return -1;
        }

        try {
            return daoToUse.update(objectToUpdate);
        } catch (SQLException e) {
            logger.error("Unable to update the result: {}", e.getMessage());
        }

        return -1;
    }

    /**
     * Delete an object from the Database
     *
     * @param objectToDelete Object to delete
     * @return Number of rows affected (Should be 1)
     */
    public <T> int delete(T objectToDelete) {
        Dao<T, Integer> daoToUse = getDao((Class<T>) objectToDelete.getClass());
        if (daoToUse == null) {
            logger.error("Unable to get Dao");
            return -1;
        }

        try {
            return daoToUse.delete(objectToDelete);
        } catch (SQLException e) {
            logger.error("Unable to delete the result: {}", e.getMessage());
        }

        return -1;
    }

    /**
     * Delete an object from the Database using its id
     *
     * @param clazz Class of the object to delete
     * @param id    Id of the object to delete
     * @return Number of rows affected (Should be 1)
     */
    public <T> int delete(Class<T> clazz, int id) {
        Dao<T, Integer> daoToUse = getDao(clazz);
        if (daoToUse == null) {
            logger.error("Unable to get Dao");
            return -1;
        }

        try {
            return daoToUse.deleteById(id);
        } catch (SQLException e) {
            logger.error("Unable to delete the result: {}", e.getMessage());
        }

        return -1;
    }


    //**********************************************************************************************************/
    //**********************************************************************************************************/
    //**********************************************************************************************************/

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

    /**
     * FixMe: To be deleted
     */
    public void doStuff() {
        Music music = new Music(
                "A Cabritinha",
                "Quim Barrieros",
                MusicGenre.PIMBA.ordinal(),
                "LoL");

        create(music);
        int id = music.getId();
        Music music2 = read(Music.class, id);
        List<Music> music3 = read(Music.class, "title", "A Cabritinha");

        logger.debug("LIST FOUND: " + music3.size());
        music3.forEach(m -> logger.debug("{}", m.getId()));

        logger.debug("Music: " + id + " --> " + music2.getAuthor());
    }
}
