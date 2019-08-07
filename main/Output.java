package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import svclass.Diem;
import svclass.LopHoc;
import svclass.MonHoc;
import svnc.HocTrinh;
import svnc.SinhVienNienChe;
import svtc.HocPhan;
import svtc.SinhVienTinChi;
import users.SinhVien;

public class Output { 
    public static void outputSinhVien(){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(Main.getFileName()[0]);
            bw = new BufferedWriter(fw);

            ArrayList<SinhVien> danhSachSinhVien = Input.getDanhSachSinhVienInput();
            for(SinhVien sinhVien: danhSachSinhVien) {
                bw.write(sinhVien.toString());
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
                if(fw != null) fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void outputHocPhan(){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(Main.getFileName()[1]);
            bw = new BufferedWriter(fw);

            ArrayList<HocPhan> danhSachHocPhan = Input.getDanhSachHocPhanInput();
            for(HocPhan hocPhan: danhSachHocPhan) {
                bw.write(hocPhan.toString());
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
                if(fw != null) fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void outputHocTrinh(){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(Main.getFileName()[2]);
            bw = new BufferedWriter(fw);

            ArrayList<HocTrinh> danhSachHocTrinh = Input.getDanhSachHocTrinhInput();
            for(HocTrinh hocTrinh: danhSachHocTrinh) {
                bw.write(hocTrinh.toString());
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
                if(fw != null) fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void outputLopHoc(){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(Main.getFileName()[3]);
            bw = new BufferedWriter(fw);

            ArrayList<LopHoc<? extends MonHoc>> danhSachLopHoc = Input.getDanhSachLopInput();
            for(LopHoc lopHoc: danhSachLopHoc) {
                bw.write(lopHoc.toString());
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
                if(fw != null) fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void outputBangDiem(SinhVien sinhVien) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(Main.getFileName()[4] + "/" + sinhVien.getMssv() + "_bangdiem.txt");
            bw = new BufferedWriter(fw);
            if(sinhVien instanceof SinhVienTinChi) {
                ArrayList<Diem<HocPhan>> bangDiem = ((SinhVienTinChi) sinhVien).getBangDiem().getDanhSachDiem();
                for(Diem diem: bangDiem) {
                    bw.write(diem.toString());
                    bw.newLine();
                }
            }else {
                ArrayList<Diem<HocTrinh>> bangDiem = ((SinhVienNienChe) sinhVien).getBangDiem().getDanhSachDiem();
                for(Diem diem: bangDiem) {
                    bw.write(diem.toString());
                    bw.newLine();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
                if(fw != null) fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void outputDangKy(LopHoc<? extends MonHoc> lopHoc) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(Main.getFileName()[5] + "/" + lopHoc.getMaLop() + "_dangky.txt");
            bw = new BufferedWriter(fw);
                ArrayList<SinhVien> danhSachDangKy = lopHoc.getDanhSachSinhVien();
                for(SinhVien sinhVien: danhSachDangKy) {
                    bw.write(sinhVien.toString());
                    bw.newLine();
                }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
                if(fw != null) fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }    

    public static void outputDangKy(SinhVien sinhVien) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(Main.getFileName()[6] + "/" + sinhVien.getMssv() + "_dangky.txt");
            bw = new BufferedWriter(fw);
            if(sinhVien instanceof SinhVienTinChi) {
                ArrayList<LopHoc<HocPhan>> danhSachDangKy = ((SinhVienTinChi) sinhVien).getDanhSachDangKy().getDanhSachLopHoc();
                for(LopHoc<HocPhan> lopHoc: danhSachDangKy) {
                    bw.write(lopHoc.toString());
                    bw.newLine();
                }
            }else {
                ArrayList<LopHoc<HocTrinh>> danhSachDangKy = ((SinhVienNienChe) sinhVien).getDanhSachDangKy().getDanhSachLopHoc();
                for(LopHoc<HocTrinh> lopHoc: danhSachDangKy) {
                    bw.write(lopHoc.toString());
                    bw.newLine();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
                if(fw != null) fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void outputDiemTungLop(LopHoc<? extends MonHoc> lopHoc, double[][] bangDiem) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(Main.getFileName()[7] + "/" + lopHoc.getMaLop() + "_dangky.txt");
            bw = new BufferedWriter(fw);
            ArrayList<SinhVien> danhSachDangKy = lopHoc.getDanhSachSinhVien();
            for(int i = 0; i < lopHoc.getSoLuongDangKy(); i++) {
                SinhVien sinhVien = lopHoc.getDanhSachSinhVien().get(i);
                String diemSinhVien = String.format("%-10d | %-20s | %-7s | %-10.1s | %-10.1s", 
                        sinhVien.getMssv(), sinhVien.getTen(), lopHoc.getMonHoc().getMaMonHoc(), bangDiem[i][0], bangDiem[i][1]);
                bw.write(diemSinhVien);
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
                if(fw != null) fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
