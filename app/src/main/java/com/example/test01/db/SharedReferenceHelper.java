package com.example.test01.db;

import android.content.SharedPreferences;

import com.example.test01.model.Lop;
import com.example.test01.model.SinhVien;
import com.example.test01.model.SinhVienLop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SharedReferenceHelper {
    SharedPreferences sharedPreferences;

    public SharedReferenceHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public List<SinhVien> getAllStudent() {
        List<SinhVien> students = new ArrayList<>();
        Set<String> st = this.sharedPreferences.getStringSet("students", null);
        if (st == null) {
            return students;
        }
        System.out.println("set len " + st.size());
        for (String s : st) {
            if(s.equalsIgnoreCase("")) {
                continue;
            }
            String[] t = s.split("_");
            int sid = Integer.parseInt(t[0]);
            String name = t[1];
            Integer dob = Integer.parseInt(t[2]);
            String address = t[3];
            String namhoc = t[4];
            students.add(new SinhVien(sid, name, dob, address, namhoc));
        }
        return students;
    }

    public void addStudent(SinhVien s) {
        Set<String> st = this.sharedPreferences.getStringSet("students", null);
        if (st == null) {
            st = new HashSet<>();
        }
        System.out.println("set len " + st.size());
        String te = "" + (st.size() + 1) + "_" + s.getName() + "_" + s.getDob() + "_" + s.getAddress() + "_" + s.getNamhoc();
        st.add(te);
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putStringSet("students", st);
        editor.apply();
    }

    public List<Lop> getAllLop() {
        List<Lop> lops = new ArrayList<>();
        Set<String> st = this.sharedPreferences.getStringSet("classes", null);
        if (st == null) {
            return lops;
        }
        System.out.println("set len " + st.size());
        for(String te : st) {
            if(te.equalsIgnoreCase("")) {
                continue;
            }
            String[] t = te.split("_");
            int lid = Integer.parseInt(t[0]);
            String name = t[1];
            String mota = t[2];
            lops.add(new Lop(lid, name, mota));
        }
        return lops;
    }

    public void addLop(Lop l) {
        Set<String> st = this.sharedPreferences.getStringSet("classes", null);
        if (st == null) {
            st = new HashSet<>();
        }
        String lte = "" + (st.size() + 1) + "_" + l.getName() + "_" + l.getMota();
        st.add(lte);
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putStringSet("classes", st);
        editor.apply();
    }

    public List<SinhVienLop> getAllStudentClass() {
        List<SinhVienLop> student_class = new ArrayList<>();
        Set<String> st = this.sharedPreferences.getStringSet("student_class", null);
        if (st == null) {
            return student_class;
        }
        for (String te : st) {
            if(te.equalsIgnoreCase("")) {
                continue;
            }
            String[] t = te.split("_");
            int id = Integer.parseInt(t[0]);
            int sid = Integer.parseInt(t[1]);
            int lid = Integer.parseInt(t[2]);

            student_class.add(new SinhVienLop(id, sid, lid));
        }
        return student_class;
    }

    public List<SinhVien> getStudentByLop(Integer lopId) {
        List<SinhVien> students = getAllStudent();
        List<SinhVien> res = new ArrayList<SinhVien>();
        List <SinhVienLop> sls = getAllStudentClass();

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

    public void addSinhVienLop(SinhVienLop svl) {
        Set<String> st = this.sharedPreferences.getStringSet("student_class", null);
        if (st == null) {
            st = new HashSet<>();
        }
        String lte = "" + (st.size() + 1) + "_" + svl.getSinhVienId() + "_" + svl.getLopId();
        st.add(lte);
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putStringSet("student_class", st);
        editor.apply();
    }

    public List<SinhVien> locStudent() {
        List<SinhVien> students = new ArrayList<>();
        Set<String> st = this.sharedPreferences.getStringSet("students", null);
        if (st == null) {
            return students;
        }
        System.out.println("set len " + st.size());
        for (String s : st) {
            if(s.equalsIgnoreCase("")) {
                continue;
            }
            String[] t = s.split("_");
            int sid = Integer.parseInt(t[0]);
            String name = t[1];
            Integer dob = Integer.parseInt(t[2]);
            String address = t[3];
            String namhoc = t[4];
            if (name.toLowerCase().contains("tung") && namhoc.equalsIgnoreCase("Nam bon")) {
                students.add(new SinhVien(sid, name, dob, address, namhoc));
            }
        }
        return students;

    }

    public Map<Lop, Integer> thongKe() {
        Map<Lop, Integer> res = new HashMap<>();
        Map<Lop, Integer> datas = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        List<SinhVienLop> sls = getAllStudentClass();
        for (SinhVienLop svl : sls) {
            Integer lid = svl.getLopId();
            if(map.get(lid) != null) {
                map.put(lid, map.get(lid));
            } else {
                map.put(lid, 1);
            }
        }
        List<Lop> lss = getAllLop();
        for(Lop l : lss) {
            int id = l.getId();
            String name = l.getName();
            String mota = l.getMota();
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
