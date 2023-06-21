package com.example.test01.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test01.model.Lop;
import com.example.test01.model.SinhVien;
import com.example.test01.model.SinhVienLop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TextFileHelper {
    public Context context;

    public TextFileHelper(Context context) {
        this.context = context;
    }

    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> datas = new ArrayList<>();
        File f = new File(this.context.getFilesDir(), fileName);
        if(!f.exists()) {
            try {
                f.createNewFile();
                return datas;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String res = "";
        try {
            FileReader fr = new FileReader(f);
            int i;
            while ((i = fr.read()) != -1)
                res += ((char) i);
            fr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] ss = res.split("\n");
        System.out.println("lennnnn " + ss.length);
        for (String te : ss) {
            if (te.equalsIgnoreCase("")) {
                System.out.println("continueeeeeee");
                continue;
            }
            datas.add(te);
            System.out.println("sv_ " + te);
        }
//        datas.remove(0);
        System.out.println("Read from file: " + fileName);
        return datas;
    }

    public void writeFile(String fileName, String str) {
        try {
            ArrayList<String> curr = readFile(fileName);
            if (curr.size() == 0) {
                System.out.println("87498322497328947");
            }
            File f = new File(this.context.getFilesDir(), fileName);
            FileWriter writer = new FileWriter(f);
            for (String s : curr) {
                System.out.println("kdsjfdslkfjdslkjf");
                writer.append(s + "\n");
            }
            writer.append(str + "\n");
            System.out.println("Wrote to file: " + fileName);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SinhVien> getAllStudent() {
        List<SinhVien> students = new ArrayList<SinhVien>();
        ArrayList<String> tes = readFile("students.txt");
        for (String te : tes) {
//            System.out.println(te);
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

    public void addStudent(SinhVien s) {
        ArrayList<String> tes = readFile("students.txt");
        String sv = "" + (tes.size() + 1) + "_" + s.getName() + "_" + s.getDob() + "_" + s.getAddress() + "_" + s.getNamhoc();
        writeFile("students.txt", sv);
    }

    public List<Lop> getAllLop() {
        List<Lop> lops = new ArrayList<Lop>();
        ArrayList<String> tes = readFile("classes.txt");
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

    public void addLop(Lop l) {
        ArrayList<String> tes = readFile("classes.txt");
        String lte = "" + (tes.size() + 1) + "_" + l.getName() + "_" + l.getMota();
        writeFile("classes.txt", lte);
    }

    public List<SinhVienLop> getAllStudentClass() {
        List<SinhVienLop> student_class = new ArrayList<SinhVienLop>();
        ArrayList<String> tes = readFile("student_class.txt");
        for (String te : tes) {
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
        ArrayList<SinhVienLop> tes = (ArrayList<SinhVienLop>) getAllStudentClass();
        String lte = "" + (tes.size() + 1) + "_" + svl.getSinhVienId() + "_" + svl.getLopId();
        writeFile("student_class.txt", lte);
    }

    public List<SinhVien> locStudent() {
        List<SinhVien> students = new ArrayList<SinhVien>();
        ArrayList<String> tes = readFile("students.txt");
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
