package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.test01.db.SQLiteHelper;
import com.example.test01.db.SharedReferenceHelper;
import com.example.test01.db.TextFileHelper;
import com.example.test01.db.XMLFileHelper;
import com.example.test01.model.Lop;
import com.example.test01.model.SinhVien;

import java.util.ArrayList;

public class ThemSinhVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sinh_vien);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        TextFileHelper textFileHelper = new TextFileHelper(this);
        XMLFileHelper xmlFileHelper = new XMLFileHelper(this);
        SharedReferenceHelper sharedReferenceHelper = new SharedReferenceHelper(this.getSharedPreferences("tung", Context.MODE_PRIVATE));

        Spinner namhoc = findViewById(R.id.namhoc);

        String[] namhocs = {"Nam nhat", "Nam hai", "Nam ba", "Nam bon"};

        ArrayAdapter<String> adapterNamHoc = new ArrayAdapter<String>(this, R.layout.spinner_namhoc_item, R.id.spinnerItem, namhocs);

        namhoc.setAdapter(adapterNamHoc);

        EditText name = findViewById(R.id.name);
        EditText dob = findViewById(R.id.dob);
        EditText address = findViewById(R.id.address);

        Button button = findViewById(R.id.themSinhVien);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hvt, qq, nh;
                Integer l, ns;
                hvt = name.getText().toString();
                ns = Integer.parseInt(dob.getText().toString());
                qq = address.getText().toString();
                nh = namhoc.getSelectedItem().toString();
//                sqLiteHelper.addStudent(new SinhVien(hvt, ns, qq, nh));
//                textFileHelper.addStudent(new SinhVien(hvt, ns, qq, nh));
//                xmlFileHelper.addStudent(new SinhVien(hvt, ns, qq, nh));
                sharedReferenceHelper.addStudent(new SinhVien(hvt, ns, qq, nh));
                Intent intent = new Intent(ThemSinhVienActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}