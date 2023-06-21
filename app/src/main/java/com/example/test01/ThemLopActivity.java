package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.test01.db.SQLiteHelper;
import com.example.test01.db.SharedReferenceHelper;
import com.example.test01.db.TextFileHelper;
import com.example.test01.db.XMLFileHelper;
import com.example.test01.model.Lop;

public class ThemLopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);

        EditText name = findViewById(R.id.name);
        EditText mota = findViewById(R.id.mota);
        Button button = findViewById(R.id.themLop);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        TextFileHelper textFileHelper = new TextFileHelper(this);
        XMLFileHelper xmlFileHelper = new XMLFileHelper(this);
        SharedReferenceHelper sharedReferenceHelper = new SharedReferenceHelper(this.getSharedPreferences("tung", Context.MODE_PRIVATE));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sqLiteHelper.addLop(new Lop(name.getText().toString(), mota.getText().toString()));
//                textFileHelper.addLop(new Lop(name.getText().toString(), mota.getText().toString()));
//                xmlFileHelper.addLop(new Lop(name.getText().toString(), mota.getText().toString()));
                sharedReferenceHelper.addLop(new Lop(name.getText().toString(), mota.getText().toString()));
                Intent intent = new Intent(ThemLopActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}