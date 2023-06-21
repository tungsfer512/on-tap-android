package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test01.db.SQLiteHelper;
import com.example.test01.db.SharedReferenceHelper;
import com.example.test01.db.TextFileHelper;
import com.example.test01.db.TextFileReadHelper;
import com.example.test01.db.XMLFileHelper;
import com.example.test01.db.XMLFileReadHelper;
import com.example.test01.model.SinhVien;

import java.util.ArrayList;

public class ListSinhVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sinh_vien);

        LinearLayout list = (LinearLayout) findViewById(R.id.listView);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(list.getContext());
        TextFileHelper textFileHelper = new TextFileHelper(this);
        TextFileReadHelper textFileReadHelper = new TextFileReadHelper(this.getResources());
        XMLFileReadHelper xmlFileReadHelper = new XMLFileReadHelper(getResources());
        XMLFileHelper xmlFileHelper = new XMLFileHelper(this);
        SharedReferenceHelper sharedReferenceHelper = new SharedReferenceHelper(this.getSharedPreferences("tung", Context.MODE_PRIVATE));

        ArrayList<SinhVien> sv;
        Intent curr = getIntent();
        if (curr.hasExtra("lopId")) {
            String lopId = getIntent().getStringExtra("lopId");
            System.out.println(lopId);
//            sv = (ArrayList<SinhVien>) sqLiteHelper.getStudentByLop(Integer.parseInt(lopId));
//            sv = (ArrayList<SinhVien>) textFileHelper.getStudentByLop(Integer.parseInt(lopId));
//            sv = (ArrayList<SinhVien>) xmlFileReadHelper.getStudentByLop(R.xml.studentes, R.xml.student_classes, Integer.parseInt(lopId));
//            sv = (ArrayList<SinhVien>) xmlFileHelper.getStudentByLop(Integer.parseInt(lopId));
            sv = (ArrayList<SinhVien>) sharedReferenceHelper.getStudentByLop(Integer.parseInt(lopId));
        } else if (curr.hasExtra("condition")) {
//            sv = (ArrayList<SinhVien>) sqLiteHelper.locStudent();
//            sv = (ArrayList<SinhVien>) textFileHelper.locStudent();
//            sv = (ArrayList<SinhVien>) xmlFileReadHelper.locStudent(R.xml.studentes);
//            sv = (ArrayList<SinhVien>) xmlFileHelper.locStudent();
            sv = (ArrayList<SinhVien>) sharedReferenceHelper.locStudent();
        } else {
//            sv = (ArrayList<SinhVien>) sqLiteHelper.getAllStudent();
//            sv = (ArrayList<SinhVien>) textFileHelper.getAllStudent();
//            sv = (ArrayList<SinhVien>) textFileReadHelper.getAllStudent(R.raw.students);
//            sv = (ArrayList<SinhVien>) xmlFileReadHelper.getAllStudent(R.xml.studentes);
//            sv = (ArrayList<SinhVien>) xmlFileHelper.getAllStudent();
            sv = (ArrayList<SinhVien>) sharedReferenceHelper.getAllStudent();
        }
        System.out.println(sv.size());
        for (int i = 0; i < sv.size(); i ++) {
            System.out.println(sv.get(i).toString());
            TextView view = new TextView(list.getContext());
            SinhVien s = sv.get(i);
            view.setText(
                    "Msv: " + s.getId() + "\n" +
                    "Ho va ten: " + s.getName() + "\n" +
                    "Nam sinh: " + s.getDob() + "\n" +
                    "Que quan: " + s.getAddress() + "\n" +
                    "Nam hoc: " + s.getNamhoc()
            );
            view.setTextSize(16);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.color.aaa);
            list.addView(view);
        }
    }
}