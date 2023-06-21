package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test01.db.SQLiteHelper;
import com.example.test01.db.SharedReferenceHelper;
import com.example.test01.db.TextFileHelper;
import com.example.test01.db.XMLFileHelper;
import com.example.test01.db.XMLFileReadHelper;
import com.example.test01.model.Lop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThongKeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        TextFileHelper textFileHelper = new TextFileHelper(this);
        XMLFileReadHelper xmlFileReadHelper = new XMLFileReadHelper(getResources());
        XMLFileHelper xmlFileHelper = new XMLFileHelper(this);
        SharedReferenceHelper sharedReferenceHelper = new SharedReferenceHelper(this.getSharedPreferences("tung", Context.MODE_PRIVATE));

//        Map<Lop, Integer> datas = sqLiteHelper.thongKe();
//        Map<Lop, Integer> datas = textFileHelper.thongKe();
//        Map<Lop, Integer> datas = xmlFileReadHelper.thongKe(R.xml.classes, R.xml.student_classes);
//        Map<Lop, Integer> datas = xmlFileHelper.thongKe();
        Map<Lop, Integer> datas = sharedReferenceHelper.thongKe();

        LinearLayout list = (LinearLayout) findViewById(R.id.listView);

        for (Map.Entry<Lop, Integer> data : datas.entrySet()) {
            TextView view = new TextView(list.getContext());
            Lop l = data.getKey();
            view.setText(
                    "Ma lop: " + l.getId() + "\n" +
                    "Ten lop: " + l.getName() + "\n" +
                    "Mo ta: " + l.getMota() + "\n" +
                    "Tong so sinh vien: " + data.getValue()
            );
            view.setTextSize(16);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 0);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.color.aaa);

            list.addView(view);
        }
    }
}