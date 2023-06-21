package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.test01.db.SQLiteHelper;
import com.example.test01.db.SharedReferenceHelper;
import com.example.test01.db.TextFileHelper;
import com.example.test01.db.XMLFileHelper;
import com.example.test01.model.Lop;
import com.example.test01.model.SinhVien;
import com.example.test01.model.SinhVienLop;

import java.util.ArrayList;

public class ThemSinhVienLopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sinh_vien_lop);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        TextFileHelper textFileHelper = new TextFileHelper(this);
        XMLFileHelper xmlFileHelper = new XMLFileHelper(this);
        SharedReferenceHelper sharedReferenceHelper = new SharedReferenceHelper(this.getSharedPreferences("tung", Context.MODE_PRIVATE));

//        ArrayList<Lop> te_ls = (ArrayList<Lop>) sqLiteHelper.getAllLop();
//        ArrayList<SinhVien> te_svs = (ArrayList<SinhVien>) sqLiteHelper.getAllStudent();

//        ArrayList<Lop> te_ls = (ArrayList<Lop>) textFileHelper.getAllLop();
//        ArrayList<SinhVien> te_svs = (ArrayList<SinhVien>) textFileHelper.getAllStudent();

//        ArrayList<Lop> te_ls = (ArrayList<Lop>) xmlFileHelper.getAllLop();
//        ArrayList<SinhVien> te_svs = (ArrayList<SinhVien>) xmlFileHelper.getAllStudent();

        ArrayList<Lop> te_ls = (ArrayList<Lop>) sharedReferenceHelper.getAllLop();
        ArrayList<SinhVien> te_svs = (ArrayList<SinhVien>) sharedReferenceHelper.getAllStudent();

        Lop[] ls = te_ls.toArray(new Lop[te_ls.size()]);
        SinhVien[] svs = te_svs.toArray(new SinhVien[te_svs.size()]);

        ArrayAdapter<Lop> lopArrayAdapter = new ArrayAdapter<Lop>(this, R.layout.spinner_namhoc_item, R.id.spinnerItem, ls);
        ArrayAdapter<SinhVien> sinhVienArrayAdapter = new ArrayAdapter<SinhVien>(this, R.layout.spinner_namhoc_item, R.id.spinnerItem, svs);

        Spinner sinhvien = findViewById(R.id.sinhvien);
        Spinner lop = findViewById(R.id.lop);
        Button button = findViewById(R.id.themSinhVienLop);

        sinhvien.setAdapter(sinhVienArrayAdapter);
        lop.setAdapter(lopArrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer lId = Integer.parseInt(lop.getSelectedItem().toString().split("_")[0]);
                Integer sId = Integer.parseInt(sinhvien.getSelectedItem().toString().split("_")[0]);
                System.out.println(lId + "___" + sId);
//                sqLiteHelper.addSinhVienLop(new SinhVienLop(sId, lId));
//                textFileHelper.addSinhVienLop(new SinhVienLop(sId, lId));
//                xmlFileHelper.addSinhVienLop(new SinhVienLop(sId, lId));
                sharedReferenceHelper.addSinhVienLop(new SinhVienLop(sId, lId));
                Intent intent = new Intent(ThemSinhVienLopActivity.this, MainActivity.class);
                startActivity(intent);
//                System.out.println(sinhvien.getSelectedItem().toString());
//                System.out.println(sinhvien.getSelectedItem().getClass());
            }
        });
    }
}