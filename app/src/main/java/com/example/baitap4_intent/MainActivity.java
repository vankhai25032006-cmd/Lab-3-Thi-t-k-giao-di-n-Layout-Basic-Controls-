package com.example.baitap4_intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;
    StudentAdapter adapter;
    List<Student> studentList;
    RecyclerView rvStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        // Nạp dữ liệu mẫu
        addSampleStudents();

        // Ánh xạ và hiển thị
        rvStudents = findViewById(R.id.rvStudents);
        refreshStudentList();
    }

    private void addSampleStudents() {
        if (db.getAllStudents().size() == 0) {
            for (int i = 1; i <= 10; i++) {
                db.addStudent("Sinh viên " + i, "sinhvien" + i + "@gmail.com");
            }
        }
    }

    private void refreshStudentList() {
        studentList = db.getAllStudents();
        adapter = new StudentAdapter(studentList);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        rvStudents.setAdapter(adapter);
    }
}