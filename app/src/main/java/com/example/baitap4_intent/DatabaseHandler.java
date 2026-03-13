package com.example.baitap4_intent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyProjectDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1. Tạo bảng Ghi chú (Cho HomeActivity)
        String CREATE_NOTES_TABLE = "CREATE TABLE TBL_NOTES (Id INTEGER PRIMARY KEY AUTOINCREMENT, Content TEXT)";
        db.execSQL(CREATE_NOTES_TABLE);

        // 2. Tạo bảng Sinh viên (Cho MainActivity)
        String CREATE_STUDENTS_TABLE = "CREATE TABLE TBL_STUDENT (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Email TEXT)";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TBL_NOTES");
        db.execSQL("DROP TABLE IF EXISTS TBL_STUDENT");
        onCreate(db);
    }

    // --- PHẦN CHO GHI CHÚ (HomeActivity) ---
    public void addNote(String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Content", content);
        db.insert("TBL_NOTES", null, values);
        db.close();
    }

    public List<String> getAllNotes() {
        List<String> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Content FROM TBL_NOTES", null);
        if (cursor.moveToFirst()) {
            do {
                noteList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return noteList;
    }

    // --- PHẦN CHO SINH VIÊN (MainActivity) ---
    // Đây là các hàm mà MainActivity đang báo lỗi "cannot find symbol"
    public void addStudent(String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Email", email);
        db.insert("TBL_STUDENT", null, values);
        db.close();
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TBL_STUDENT", null);
        if (cursor.moveToFirst()) {
            do {
                // Đảm bảo class Student của bạn có Constructor Student(int, String, String)
                list.add(new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}