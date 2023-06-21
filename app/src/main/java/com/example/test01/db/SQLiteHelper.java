package com.example.test01.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.test01.model.Lop;
import com.example.test01.model.SinhVien;
import com.example.test01.model.SinhVienLop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DB_NAME = "test01.db";
    static int DB_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE sinhvien(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "dob TEXT," +
                "address TEXT," +
                "namhoc TEXT);";
        String sql2 = "CREATE TABLE lop(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "mota TEXT);";
        String sql3 = "CREATE TABLE sinhvienlop(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sinhvienId INTEGER," +
                "lopId INTEGER);";
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<SinhVien> getAllStudent() {
        List<SinhVien> students = new ArrayList<SinhVien>();
        SQLiteDatabase sqldb = getReadableDatabase();
        Cursor cursor = sqldb.query("sinhvien", null, null, null, null, null, null);
        while( cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Integer dob = cursor.getInt(2);
            String address = cursor.getString(3);
            String namhoc = cursor.getString(4);
            students.add(new SinhVien(id, name, dob, address, namhoc));
        }
        return students;
    }

    public long addStudent(SinhVien s) {
        ContentValues v = new ContentValues();
        v.put("name", s.getName());
        v.put("dob", s.getDob());
        v.put("address", s.getAddress());
        v.put("namhoc", s.getNamhoc());
        SQLiteDatabase sqldb = getWritableDatabase();
        return sqldb.insert("sinhvien", null, v);
    }

    public List<Lop> getAllLop() {
        List<Lop> lops = new ArrayList<Lop>();
        SQLiteDatabase sqldb = getReadableDatabase();
        Cursor cursor = sqldb.query("lop", null, null, null, null, null, null);
        while( cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String mota = cursor.getString(2);
            lops.add(new Lop(id, name, mota));
        }
        return lops;
    }

    public long addLop(Lop l) {
        ContentValues v = new ContentValues();
        v.put("name", l.getName());
        v.put("mota", l.getMota());
        SQLiteDatabase sqldb = getWritableDatabase();
        return sqldb.insert("lop", null, v);
    }

    public List<SinhVien> getStudentByLop(Integer lopId) {
        List<SinhVien> students = new ArrayList<SinhVien>();
        List<SinhVien> res = new ArrayList<SinhVien>();
        List <SinhVienLop> sls = new ArrayList<SinhVienLop>();
        SQLiteDatabase sqldb = getReadableDatabase();
        Cursor cursor = sqldb.query("sinhvien", null, null, null, null, null, null);
        while( cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Integer dob = cursor.getInt(2);
            String address = cursor.getString(3);
            String namhoc = cursor.getString(4);
            students.add(new SinhVien(id, name, dob, address, namhoc));
        }
        Cursor cursor2 = sqldb.query("sinhvienlop", null, null, null, null, null, null);
        while( cursor2 != null && cursor2.moveToNext()) {
            int sid = cursor2.getInt(1);
            int lid = cursor2.getInt(2);
            sls.add(new SinhVienLop(sid, lid));
        }
        System.out.println("resres1 " + students.size());
        System.out.println("resres2 " + sls.size());
        for (SinhVienLop sl : sls) {
            if (sl.getLopId() == lopId) {
                for (SinhVien sv : students) {
                    if (sv.getId() == sl.getSinhVienId()) {
                        res.add(sv);
                    }
                }
            }
        }
        System.out.println("resres " + res.size());
        return res;
    }

    public long addSinhVienLop(SinhVienLop svl) {
        ContentValues v = new ContentValues();
        v.put("sinhvienId", svl.getSinhVienId());
        v.put("lopId", svl.getLopId());
        SQLiteDatabase sqldb = getWritableDatabase();
        return sqldb.insert("sinhvienlop", null, v);
    }

    public List<SinhVien> locStudent() {
        List<SinhVien> students = new ArrayList<SinhVien>();
        SQLiteDatabase sqldb = getReadableDatabase();
        Cursor cursor = sqldb.rawQuery("SELECT * FROM sinhvien WHERE name LIKE '%tung%' AND namhoc = 'Nam hai';", null);
        while( cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Integer dob = cursor.getInt(2);
            String address = cursor.getString(3);
            String namhoc = cursor.getString(4);
            students.add(new SinhVien(id, name, dob, address, namhoc));
        }
        return students;
    }

    public Map<Lop, Integer> thongKe() {
        Map<Lop, Integer> res = new HashMap<>();
        Map<Lop, Integer> datas = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        SQLiteDatabase sqldb = getReadableDatabase();
        ArrayList<SinhVienLop> sls = new ArrayList<>();
        Cursor cursor = sqldb.rawQuery("SELECT lopId , COUNT(*) AS total FROM sinhvienlop GROUP BY lopId ORDER BY total DESC;", null);
        System.out.println(cursor);
        while (cursor != null && cursor.moveToNext()) {
            Integer lid = cursor.getInt(0);
            Integer total = cursor.getInt(1);
            map.put(lid, total);
        }
        Cursor cursor2 = sqldb.rawQuery("SELECT * FROM lop;", null);
        while( cursor2 != null && cursor2.moveToNext()) {
            int id = cursor2.getInt(0);
            String name = cursor2.getString(1);
            String mota = cursor2.getString(2);
            res.put(new Lop(id, name, mota), map.get(id));
        }
        List<Map.Entry<Lop, Integer>> ls = new LinkedList<Map.Entry<Lop, Integer>>(res.entrySet());
        Collections.sort(ls, new Comparator<Map.Entry<Lop, Integer>>() {
            @Override
            public int compare(Map.Entry<Lop, Integer> t0, Map.Entry<Lop, Integer> t1) {
                return (t0.getValue() - t1.getValue());
            }
        });

        for (Map.Entry<Lop, Integer> l : ls) {
            datas.put(l.getKey(), l.getValue());
        }
        return datas;
    }
}
