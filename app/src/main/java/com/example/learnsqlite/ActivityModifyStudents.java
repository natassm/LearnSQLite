package com.example.learnsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class ActivityModifyStudents extends Activity implements View.OnClickListener {

    private EditText kelasText, namaText;
    private Button updateBtn, deleteBtn;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Update Data");
        setContentView(R.layout.activity_modifydata);
        dbManager = new DBManager(this);
        dbManager.open();

        kelasText = findViewById(R.id.kelas_edittext);
        namaText = findViewById(R.id.nama_edittext);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String kelas = intent.getStringExtra("kelas");
        String nama = intent.getStringExtra("nama");

        _id = Long.parseLong(id);
        kelasText.setText(kelas);
        namaText.setText(nama);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_update:

                String kelas = kelasText.getText().toString();
                String nama = namaText.getText().toString();

                dbManager.update(_id, kelas, nama);

                this.returnHome();
                break;

            case R.id.btn_delete:

                dbManager.delete(_id);

                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent intent = new Intent(getApplicationContext(), ActivityDataStudents.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
