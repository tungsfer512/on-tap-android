package com.example.test01.db;

import android.content.res.Resources;

import com.example.test01.model.Lop;
import com.example.test01.model.SinhVien;
import com.example.test01.model.SinhVienLop;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TextFileReadHelper {

    public Resources resources;

    public TextFileReadHelper(Resources resources) {
        this.resources = resources;
    }

    public ArrayList<String> readFile(int id) {
        InputStream file = this.resources.openRawResource(id);
        DataInputStream dataIO = new DataInputStream(file);
        String line = null;
        ArrayList<String> datas = new ArrayList<>();
        try {
            while ((line = dataIO.readLine()) != null)
                datas.add(line.toString());
            dataIO.close();
            file.close();
        } catch (IOException e) { }
        return datas;
    }

    public List<SinhVien> getAllStudent(int fid) {
        List<SinhVien> students = new ArrayList<SinhVien>();
        ArrayList<String> tes = readFile(fid);
        for (String te : tes) {
            String[] t = te.split("_");
            int sid = Integer.parseInt(t[0]);
            String name = t[1];
            Integer dob = Integer.parseInt(t[2]);
            String address = t[3];
            String namhoc = t[4];
            students.add(new SinhVien(sid, name, dob, address, namhoc));
        }
        return students;
    }

    public List<Lop> getAllLop(int fid) {
        List<Lop> lops = new ArrayList<Lop>();
        ArrayList<String> tes = readFile(fid);
        for(String te : tes) {
            System.out.println("asdgasd " + te);
            String[] t = te.split("_");
            int lid = Integer.parseInt(t[0]);
            String name = t[1];
            String mota = t[2];
            lops.add(new Lop(lid, name, mota));
        }
        return lops;
    }

    public List<SinhVienLop> getAllStudentClass(int fid) {
        List<SinhVienLop> student_class = new ArrayList<SinhVienLop>();
        ArrayList<String> tes = readFile(fid);
        for (String te : tes) {
            String[] t = te.split("_");
            int sid = Integer.parseInt(t[0]);
            int lid = Integer.parseInt(t[1]);

            student_class.add(new SinhVienLop(sid, lid));
        }
        return student_class;
    }

    public List<SinhVien> getStudentByLop(int fid_stu, int fid_class, Integer lopId) {
        List<SinhVien> students = getAllStudent(fid_stu);
        List<SinhVien> res = new ArrayList<SinhVien>();
        List <SinhVienLop> sls = getAllStudentClass(fid_class);

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

    public List<SinhVien> locStudent(int fid) {
        List<SinhVien> students = new ArrayList<SinhVien>();
        ArrayList<String> tes = readFile(fid);
        for (String te : tes) {
//            System.out.println(te);
            String[] t = te.split("_");
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

    public Map<Lop, Integer> thongKe(int fid_class, int fid_stu_class) {
        Map<Lop, Integer> res = new HashMap<>();
        Map<Lop, Integer> datas = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        List<SinhVienLop> sls = getAllStudentClass(fid_stu_class);
        for (SinhVienLop svl : sls) {
            Integer lid = svl.getLopId();
            if(map.get(lid) != null) {
                map.put(lid, map.get(lid));
            } else {
                map.put(lid, 1);
            }
        }
        List<Lop> lss = getAllLop(fid_class);
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
