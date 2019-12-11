package com.example.learnsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAddStudents extends Activity implements View.OnClickListener {

    private Button addTodoBtn;
    private EditText kelasET, namaET;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Masukkan Data");
        setContentView(R.layout.activity_addstudents);
        kelasET = findViewById(R.id.kelas_edittext);
        namaET = findViewById(R.id.nama_edittext);
        addTodoBtn = findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add_record:

                final String kelas = kelasET.getText().toString();
                final String nama = namaET.getText().toString();


                dbManager.insert(kelas, nama);

                Intent main = new Intent(ActivityAddStudents.this, ActivityDataStudents.class).
                        setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }

    }
}
