package com.example.test01.db;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.example.test01.model.Lop;
import com.example.test01.model.SinhVien;
import com.example.test01.model.SinhVienLop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class XMLFileReadHelper {
    public Resources resources;

    public XMLFileReadHelper(Resources resources) {
        this.resources = resources;
    }

    public ArrayList<String> readFile(int xid, String t) {
        ArrayList<String> res = new ArrayList<>();
        XmlResourceParser parser = resources.getXml(xid);
        int tag = -1;
        while (tag != XmlResourceParser.END_DOCUMENT) {
            if (tag == XmlResourceParser.START_TAG) {
                String tag_name = parser.getName();
                if (tag_name.equalsIgnoreCase(t)) {
                    if (t.equalsIgnoreCase("student")) {
                        String id = parser.getAttributeValue(null, "id");
                        String name = parser.getAttributeValue(null, "name");
                        String dob = parser.getAttributeValue(null, "dob");
                        String address = parser.getAttributeValue(null, "address");
                        String namhoc = parser.getAttributeValue(null, "namhoc");
                        System.out.println("------------------------------------");
                        System.out.println("student " + id);
                        System.out.println("student " + name);
                        System.out.println("student " + dob);
                        System.out.println("student " + address);
                        System.out.println("student " + namhoc);
                        System.out.println("-------------------------------------");
                        String te = id + "_" + name + "_" + dob + "_" + address + "_" + namhoc;
                        res.add(te);
                    }
                    else if (t.equalsIgnoreCase("class")) {
                        String id = parser.getAttributeValue(null, "id");
                        String name = parser.getAttributeValue(null, "name");
                        String mota = parser.getAttributeValue(null, "mota");
                        System.out.println("------------------------------------");
                        System.out.println("class " + id);
                        System.out.println("class " + name);
                        System.out.println("class " + mota);
                        System.out.println("-------------------------------------");
                        String te = id + "_" + name + "_" + mota;
                        res.add(te);
                    } else {
                        Integer id = Integer.parseInt(parser.getAttributeValue(null, "id"));
                        String sinhvienId = parser.getAttributeValue(null, "sinhvienId");
                        String lopId = parser.getAttributeValue(null, "lopId");
                        System.out.println("------------------------------------");
                        System.out.println("student_class " + id);
                        System.out.println("student_class " + sinhvienId);
                        System.out.println("student_class " + lopId);
                        System.out.println("-------------------------------------");
                        String te = sinhvienId + "_" + lopId;
                        res.add(te);
                    }
                }
            }
            try {
                tag = parser.next();
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        return res;
    }

    public void writeFile(int xid, String t) {

    }

    public List<SinhVien> getAllStudent(int fid) {
        List<SinhVien> students = new ArrayList<SinhVien>();
        ArrayList<String> tes = readFile(fid, "student");
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

//    public void addStudent(SinhVien s) {
//        ArrayList<String> tes = readFile();
//        String sv = "" + (tes.size() + 1) + "_" + s.getName() + "_" + s.getDob() + "_" + s.getAddress() + "_" + s.getNamhoc();
//        writeFile("students.txt", sv);
//    }

    public List<Lop> getAllLop(int fid) {
        List<Lop> lops = new ArrayList<Lop>();
        ArrayList<String> tes = readFile(fid, "class");
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

//    public void addLop(Lop l) {
//        ArrayList<String> tes = readFile("classes.txt");
//        String lte = "" + (tes.size() + 1) + "_" + l.getName() + "_" + l.getMota();
//        writeFile("classes.txt", lte);
//    }

    public List<SinhVienLop> getAllStudentClass(int fid) {
        List<SinhVienLop> student_class = new ArrayList<SinhVienLop>();
        ArrayList<String> tes = readFile(fid, "student_class");
        for (String te : tes) {
            String[] t = te.split("_");
            int sid = Integer.parseInt(t[0]);
            int lid = Integer.parseInt(t[1]);

            student_class.add(new SinhVienLop(sid, lid));
        }
        return student_class;
    }

    public List<SinhVien> getStudentByLop(int fid_stu, int fid_stu_class, Integer lopId) {
        List<SinhVien> students = getAllStudent(fid_stu);
        List<SinhVien> res = new ArrayList<SinhVien>();
        List <SinhVienLop> sls = getAllStudentClass(fid_stu_class);

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

//    public void addSinhVienLop(SinhVienLop svl) {
//        ArrayList<String> tes = readFile("student_class.txt");
//        String lte = "" + (tes.size() + 1) + "_" + svl.getSinhVienId() + "_" + svl.getLopId();
//        writeFile("student_class.txt", lte);
//    }

    public List<SinhVien> locStudent(int fid) {
        List<SinhVien> students = new ArrayList<SinhVien>();
        ArrayList<String> tes = readFile(fid, "student");
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
//        Collections.sort(ls, new Comparator<Map.Entry<Lop, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Lop, Integer> t0, Map.Entry<Lop, Integer> t1) {
//                return (t0.getValue() - t1.getValue());
//            }
//        });

        for (Map.Entry<Lop, Integer> l : ls) {
            datas.put(l.getKey(), l.getValue());
        }
        return datas;
    }

}
