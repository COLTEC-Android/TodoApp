package edu.coltec.todoapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskDAO {

    public static final String CREATE_SCRIPT =
            "CREATE TABLE IF NOT EXISTS tasks (\n" +
            "\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\tname TEXT NOT NULL,\n" +
            "\tdescription TEXT NOT NULL\n" +
            ");";

    private static final String TABLE_NAME = "tasks";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";


    private AppDB appDB;

    public TaskDAO(AppDB appDB) {
        this.appDB = appDB;
    }


    public boolean create(Task newTask) {
        boolean result = true;
        SQLiteDatabase writeDb = this.appDB.getWritableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME_COLUMN, newTask.getName());
            contentValues.put(DESCRIPTION_COLUMN, newTask.getDescription());
            writeDb.insert(TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            result = false;
        } finally {
            writeDb.close();
        }

        return result;
    }


    @SuppressLint("Range")
    public Task getByName(Task task) {
        Task searchedTask = null;
        SQLiteDatabase readDB = this.appDB.getReadableDatabase();

        try {

            String clause = NAME_COLUMN + "=?";
            String[] values = { task.getName() };
            Cursor res = readDB.query(TABLE_NAME, null, clause, values, null, null, null);

            if (res.moveToFirst()) {
                int id = res.getInt(res.getColumnIndex(ID_COLUMN));
                String name = res.getString(res.getColumnIndex(NAME_COLUMN));
                String description = res.getString(res.getColumnIndex(DESCRIPTION_COLUMN));

                searchedTask = new Task(id, name, description);
            }
        } catch(Exception e) {
            searchedTask = null;
        } finally {
            readDB.close();
        }

        return  searchedTask;
    }
}
