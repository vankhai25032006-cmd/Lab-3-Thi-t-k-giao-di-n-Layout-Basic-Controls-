package com.example.baitap4_intent;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    DatabaseHandler db;
    ArrayAdapter<String> adapter;
    List<String> noteList;
    ListView lvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DatabaseHandler(this);
        EditText editNote = findViewById(R.id.editNote);
        Button btnSaveNote = findViewById(R.id.btnSaveNote);
        lvNotes = findViewById(R.id.lvNotes);

        refreshList();

        btnSaveNote.setOnClickListener(v -> {
            String content = editNote.getText().toString().trim();
            if (!content.isEmpty()) {
                db.addNote(content);
                editNote.setText("");
                refreshList();
                Toast.makeText(this, "Đã lưu!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshList() {
        noteList = db.getAllNotes();
        if (noteList == null) {
            noteList = new ArrayList<>();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteList);
        lvNotes.setAdapter(adapter);
    }
}