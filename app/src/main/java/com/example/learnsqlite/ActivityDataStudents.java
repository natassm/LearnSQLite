package com.example.learnsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDataStudents extends AppCompatActivity {

    TextView idTV, kelasTV, namaTV;
    String id, kelas, nama;

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID, DatabaseHelper.KELAS, DatabaseHelper.NAMA };

    final int[] to = new int[] { R.id.id, R.id.kelas, R.id.nama };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_datastudents);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_fragment, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idTV = findViewById(R.id.id);
                kelasTV = findViewById(R.id.kelas);
                namaTV = findViewById(R.id.nama);

                id = idTV.getText().toString();
                kelas = kelasTV.getText().toString();
                nama = namaTV.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ActivityModifyStudents.class);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("kelas", kelas);
                modify_intent.putExtra("nama", nama);
                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record){
            Intent add_mem = new Intent(this, ActivityAddStudents.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}
