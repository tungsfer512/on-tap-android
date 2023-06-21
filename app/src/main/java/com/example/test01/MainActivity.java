package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test01.db.TextFileHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button danhSachSinhVien = findViewById(R.id.danhSachSinhVien);

        danhSachSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListSinhVienActivity.class);
                startActivity(intent);
            }
        });
        Button danhSachLop = findViewById(R.id.danhSachLop);

        danhSachLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListLopActivity.class);
                startActivity(intent);
            }
        });
        Button themSinhVien = findViewById(R.id.themSinhVien);

        themSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemSinhVienActivity.class);
                startActivity(intent);
            }
        });
        Button themLop = findViewById(R.id.themLop);

        themLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemLopActivity.class);
                startActivity(intent);
            }
        });
        Button themSinhVienLop = findViewById(R.id.themSinhVienLop);

        themSinhVienLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemSinhVienLopActivity.class);
                startActivity(intent);
            }
        });
        Button locSinhVien = findViewById(R.id.locSinhVien);

        locSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListSinhVienActivity.class);
                intent.putExtra("condition", "true");
                startActivity(intent);
            }
        });
        Button thongKe = findViewById(R.id.thongKe);

        thongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThongKeActivity.class);
                startActivity(intent);
            }
        });
        Button table = findViewById(R.id.table);

        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

    }
}