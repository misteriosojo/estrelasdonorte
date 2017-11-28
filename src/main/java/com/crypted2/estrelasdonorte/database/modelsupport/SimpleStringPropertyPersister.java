package com.crypted2.estrelasdonorte.database.modelsupport;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;
import javafx.beans.property.SimpleStringProperty;

import java.sql.SQLException;

public class SimpleStringPropertyPersister extends BaseDataType {

    private static final SimpleStringPropertyPersister singleTon = new SimpleStringPropertyPersister();

    private SimpleStringPropertyPersister() {
        super(SqlType.STRING, new Class<?>[]{String.class});
    }

    public static SimpleStringPropertyPersister getSingleton() {
        return singleTon;
    }

    @Override
    public Object parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException {
        return new SimpleStringProperty(defaultStr);
    }

    @Override
    public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getString(columnPos);
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) {
        return new SimpleStringProperty((String) sqlArg);
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
        SimpleStringProperty stringProperty = (SimpleStringProperty) javaObject;
        return stringProperty.getValue();
    }
}