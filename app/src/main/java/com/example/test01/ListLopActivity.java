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
import com.example.test01.model.SinhVien;

import java.util.ArrayList;

public class ListLopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lop);

        LinearLayout list = (LinearLayout) findViewById(R.id.listView);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        TextFileHelper textFileHelper = new TextFileHelper(this);
        XMLFileReadHelper xmlFileReadHelper = new XMLFileReadHelper(getResources());
        XMLFileHelper xmlFileHelper = new XMLFileHelper(this);
        SharedReferenceHelper sharedReferenceHelper = new SharedReferenceHelper(this.getSharedPreferences("tung", Context.MODE_PRIVATE));

//        ArrayList<Lop> ls = (ArrayList<Lop>) sqLiteHelper.getAllLop();
//        ArrayList<Lop> ls = (ArrayList<Lop>) textFileHelper.getAllLop();
//        ArrayList<Lop> ls = (ArrayList<Lop>) xmlFileReadHelper.getAllLop(R.xml.classes);
//        ArrayList<Lop> ls = (ArrayList<Lop>) xmlFileHelper.getAllLop();
        ArrayList<Lop> ls = (ArrayList<Lop>) sharedReferenceHelper.getAllLop();


        for (int i = 0; i < ls.size(); i ++) {
            TextView view = new TextView(list.getContext());
            Lop l = ls.get(i);
            view.setText(
                    "Ma lop: " + l.getId() + "\n" +
                    "Ten lop: " + l.getName() + "\n" +
                    "Mo ta: " + l.getMota()
            );
            view.setTextSize(16);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 0);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.color.aaa);

            Button button = new Button(list.getContext());
            button.setText("Xem chi tiet");
            final String id = l.getId().toString();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ListLopActivity.this, ListSinhVienActivity.class);
                    intent.putExtra("lopId", "" + id);
                    startActivity(intent);
                }
            });
            LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsButton.setMargins(10, 0, 10, 20);
            button.setLayoutParams(paramsButton);
            list.addView(view);
            list.addView(button);
        }
    }
}