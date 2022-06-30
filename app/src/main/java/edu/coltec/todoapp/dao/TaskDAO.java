package edu.coltec.todoapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public static final String CREATE_SCRIPT =
            "CREATE TABLE IF NOT EXISTS tasks (\n" +
                    "\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\tname TEXT NOT NULL,\n" +
                    "\tdescription TEXT NOT NULL,\n" +
                    "\tcategory_id INTEGER NOT NULL,\n" +
                    "\n" +
                    "\tFOREIGN KEY(category_id) REFERENCES categories(id)\n" +
                    ");";

    private static final String TABLE_NAME = "tasks";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String CATEGORY_COLUMN = "category_id";
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
            contentValues.put(CATEGORY_COLUMN, newTask.getCategory().getId());
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
        CategoryDAO categoryDAO = new CategoryDAO(this.appDB);
        SQLiteDatabase readDB = this.appDB.getReadableDatabase();

        try {

            String clause = NAME_COLUMN + "=?";
            String[] values = { task.getName() };
            Cursor res = readDB.query(TABLE_NAME, null, clause, values, null, null, null);

            if (res.moveToFirst()) {
                int id = res.getInt(res.getColumnIndex(ID_COLUMN));
                String name = res.getString(res.getColumnIndex(NAME_COLUMN));
                String description = res.getString(res.getColumnIndex(DESCRIPTION_COLUMN));

                int categoryId = res.getInt(res.getColumnIndex(CATEGORY_COLUMN));
                Category category = categoryDAO.getById(new Category(categoryId));

                searchedTask = new Task(id, name, description, category);
            }
        } catch(Exception e) {
            searchedTask = null;
        } finally {
            readDB.close();
        }

        return  searchedTask;
    }

    @SuppressLint("Range")
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAO(this.appDB);
        SQLiteDatabase readDB = this.appDB.getReadableDatabase();

        try {
            Cursor res = readDB.query(TABLE_NAME, null, null, null, null, null, null);
            if (res.moveToFirst()) {
                do {
                    int id = res.getInt(res.getColumnIndex(ID_COLUMN));
                    String name = res.getString(res.getColumnIndex(NAME_COLUMN));
                    String description = res.getString(res.getColumnIndex(DESCRIPTION_COLUMN));

                    int categoryId = res.getInt(res.getColumnIndex(CATEGORY_COLUMN));
                    Category category = categoryDAO.getById(new Category(categoryId));

                    tasks.add(new Task(id, name, description, category));
                } while (res.moveToNext());
            }
        } catch(Exception e) {
            tasks = null;
        } finally {
            readDB.close();
        }
        return tasks;
    }
}
