package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import svclass.ChuyenNganh;
import svclass.DanhMucMonHoc;
import svclass.Day;
import svclass.Diem;
import svclass.LopHoc;
import svclass.MonHoc;
import svclass.TrangThaiHocTap;
import svnc.HocTrinh;
import svnc.SinhVienNienChe;
import svtc.HocPhan;
import svtc.SinhVienTinChi;
import users.QuanLy;
import users.SinhVien;

public class Input {
    private static ArrayList<QuanLy> danhSachQuanLyInput = new ArrayList<QuanLy>();
    private static ArrayList<SinhVien> danhSachSinhVienInput = new ArrayList<SinhVien>();
    private static ArrayList<SinhVienNienChe> danhSachNiencheInput = new ArrayList<SinhVienNienChe>();
    private static ArrayList<SinhVienTinChi> danhSachTinChiInput = new ArrayList<SinhVienTinChi>();
    private static ArrayList<HocPhan> danhSachHocPhanInput = new ArrayList<HocPhan>();
    private static ArrayList<HocTrinh> danhSachHocTrinhInput = new ArrayList<HocTrinh>();
    private static ArrayList<LopHoc<? extends MonHoc>> danhSachLopInput = new ArrayList<LopHoc<? extends MonHoc>>();
    private static ArrayList<LopHoc<HocPhan>> danhSachLopTinChiInput = new ArrayList<LopHoc<HocPhan>>();
    private static ArrayList<LopHoc<HocTrinh>> danhSachLopNienCheInput = new ArrayList<LopHoc<HocTrinh>>();
    private static ArrayList<DanhMucMonHoc<HocPhan>> heThongDaoTaoTinChi;
    private static ArrayList<DanhMucMonHoc<HocTrinh>> heThongDaoTaoNienChe;
    
    public static ArrayList<QuanLy> getDanhSachQuanLyInput() {
        return danhSachQuanLyInput;
    }
        
    public static ArrayList<SinhVien> getDanhSachSinhVienInput() {
    	return danhSachSinhVienInput;
    }

    public static ArrayList<SinhVienNienChe> getDanhSachNiencheInput() {
    	return danhSachNiencheInput;
    }
	
    public static ArrayList<SinhVienTinChi> getDanhSachTinChiInput() {
	return danhSachTinChiInput;
    }
	
    public static ArrayList<HocPhan> getDanhSachHocPhanInput() {
	return danhSachHocPhanInput;
    }
	
    public static ArrayList<HocTrinh> getDanhSachHocTrinhInput() {
	return danhSachHocTrinhInput;
    }
        
    public static ArrayList<LopHoc<? extends MonHoc>> getDanhSachLopInput() {
        return danhSachLopInput;
    }
	
    public static ArrayList<LopHoc<HocPhan>> getDanhSachLopTinChiInput() {
        return danhSachLopTinChiInput;
    }
    
    public static ArrayList<LopHoc<HocTrinh>> getDanhSachLopNienCheInput() {
        return danhSachLopNienCheInput;
    }
    
    public static ArrayList<DanhMucMonHoc<HocPhan>> getHeThongDaoTaoTinChi() {
	return heThongDaoTaoTinChi;
    }
	
	
    public static ArrayList<DanhMucMonHoc<HocTrinh>> getHeThongDaoTaoNienChe() {
    	return heThongDaoTaoNienChe;
    }
        
    public static ChuyenNganh[] getDanhMucChuyenNganh() {
        ChuyenNganh[] danhMucChuyenNganh = new ChuyenNganh[16];
        danhMucChuyenNganh[0] = ChuyenNganh.CHUA_PHAN;
        danhMucChuyenNganh[1] = ChuyenNganh.KHMT;
        danhMucChuyenNganh[2] = ChuyenNganh.HTTT;
        danhMucChuyenNganh[3] = ChuyenNganh.CNPM;
        danhMucChuyenNganh[4] = ChuyenNganh.KTMT;
        danhMucChuyenNganh[5] = ChuyenNganh.TTM;
        danhMucChuyenNganh[6] = ChuyenNganh.ATTT;
        danhMucChuyenNganh[7] = ChuyenNganh.CNTT;
        danhMucChuyenNganh[8] = ChuyenNganh.TA_KHKTVCN;
        danhMucChuyenNganh[9] = ChuyenNganh.TACNQT;
        danhMucChuyenNganh[10] = ChuyenNganh.CNTP;
        danhMucChuyenNganh[11] = ChuyenNganh.KTCDT;
        danhMucChuyenNganh[12] = ChuyenNganh.KSTN_CNTT;
        danhMucChuyenNganh[13] = ChuyenNganh.KSCLC_HTTT;
        danhMucChuyenNganh[14] = ChuyenNganh.KSTN_CDT;
        danhMucChuyenNganh[15] = ChuyenNganh.CTTT_CDT;
        
        return danhMucChuyenNganh;
    }
    
    public static ChuyenNganh getInstanceOfDanhMucChuyenNganh(String tenChuyenNganh) {
        for(int i = 0; i < getDanhMucChuyenNganh().length; i++) {  
            if(tenChuyenNganh.equals(getDanhMucChuyenNganh()[i].name()))
                return getDanhMucChuyenNganh()[i];
        }
        return null;
    }
    
    public static Day[] getNgay() {
        Day[] danhMucNgay = new Day[7];
        danhMucNgay[0] = Day.THUHAI;
        danhMucNgay[1] = Day.THUBA;
        danhMucNgay[2] = Day.THUTU;
        danhMucNgay[3] = Day.THUNAM;
        danhMucNgay[4] = Day.THUSAU;
        danhMucNgay[5] = Day.THUBAY;
        danhMucNgay[6] = Day.CHUNHAT;
        return danhMucNgay;
    }
    
    public static Day getInstanceOfNgay(String ngay) {
        for(int i = 0; i < getNgay().length; i++) {  
            if(ngay.equals(getNgay()[i].name()))
                return getNgay()[i];
        }
        return null;
    }
    
    public static boolean isTinChi(ChuyenNganh chuyenNganh) {
        if((chuyenNganh == ChuyenNganh.KSTN_CDT) || (chuyenNganh == ChuyenNganh.KSTN_CNTT)
           || (chuyenNganh == ChuyenNganh.CTTT_CDT) || (chuyenNganh == ChuyenNganh.KSCLC_HTTT))
            return false;
        else 
            return true;
    }
    
    public static TrangThaiHocTap[] getTrangThaiHoc() {
        TrangThaiHocTap[] trangThai = new TrangThaiHocTap[3];
        trangThai[0] = TrangThaiHocTap.DANGHOC;
        trangThai[1] = TrangThaiHocTap.BAOLUU;
        trangThai[2] = TrangThaiHocTap.THOIHOC;
        return trangThai;
    }
    
     public static TrangThaiHocTap getInstanceOfTrangThai(String trangThai) {
        for(int i = 0; i < getTrangThaiHoc().length; i++) {  
            if(trangThai.equals(getTrangThaiHoc()[i].name()))
                return getTrangThaiHoc()[i];
        }
        return null;
    }
     
    public static void capNhatDanhSachQuanLyInput() {
        getDanhSachQuanLyInput().add(new QuanLy(1234, "Lê Thị Thu", "25-10-1980", false, "Thạch Thất - Hà Nội", 59));
    }
        
    public static SinhVien getInstanceOfSinhVien(int mssv) {
        for(SinhVien sinhVien: getDanhSachSinhVienInput()) 
            if(sinhVien.getMssv() == mssv)
                return sinhVien;
        return null;
    }
    
    public static void capNhatDanhSachSinhVienInput(){
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(Main.getFileName()[0]);
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] strResult = line.split(" \\| ");
                int mssv = Integer.parseInt(strResult[0].trim());
                String ten = strResult[1].trim();
                String ngaySinh = strResult[2].trim();
                boolean gioiTinh = ("Nam".equals(strResult[3].trim()))? true: false;
                String diaChi = strResult[4].trim();
                String khoaVien = strResult[5].trim();
                ChuyenNganh chuyenNganh = getInstanceOfDanhMucChuyenNganh(strResult[6].trim());
                String lopSinhVien = strResult[7].trim();
                SinhVien sinhVien;
                if(isTinChi(chuyenNganh)) {
                    sinhVien = new SinhVienTinChi(mssv, ten, ngaySinh, gioiTinh, diaChi, khoaVien, chuyenNganh, lopSinhVien);
                } else {
                    sinhVien = new SinhVienNienChe(mssv, ten, ngaySinh, gioiTinh, diaChi, khoaVien, chuyenNganh, lopSinhVien);
                }
                
                getDanhSachSinhVienInput().add(sinhVien);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void capNhatTinChiVaNienChe() {
	for(SinhVien sinhVien: getDanhSachSinhVienInput())
            if(sinhVien instanceof SinhVienTinChi)
		getDanhSachTinChiInput().add((SinhVienTinChi)sinhVien);
            else
		getDanhSachNiencheInput().add((SinhVienNienChe)sinhVien);
    }
	
    public static MonHoc getInstanceOfMonHoc(String maMonHoc, String heDaoTao) {
        if("Tín chỉ".equals(heDaoTao)) {
            for(HocPhan hocPhan: getDanhSachHocPhanInput())
                if(hocPhan.getMaMonHoc().equals(maMonHoc))
                    return hocPhan;
        }
        
        if("Niên chế".equals(heDaoTao)) {
            for(HocTrinh hocTrinh: getDanhSachHocTrinhInput())
                if(hocTrinh.getMaMonHoc().equals(maMonHoc))
                    return hocTrinh;
        }
        
        return null;
    }
    
    public static void capNhatDanhSachHocPhanInput() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(Main.getFileName()[1]);
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] strResult = line.split(" \\| ");
                String maMonHoc = strResult[0].trim();
                String tenMonHoc = strResult[1].trim();
                int soTinChi = Integer.parseInt(strResult[2].trim());
                double trongSo = Double.parseDouble(strResult[3].trim());
                
                HocPhan hocPhanHocTruoc;
                if("null".equals(strResult[4].trim())) {
                    hocPhanHocTruoc = null;
                } else {
                    hocPhanHocTruoc = (HocPhan) getInstanceOfMonHoc(strResult[4].trim(), "Tín chỉ");
                }
                
                String chuongTrinh = strResult[5].trim();
                HocPhan hocPhan = new HocPhan(maMonHoc, tenMonHoc, soTinChi, trongSo, hocPhanHocTruoc, chuongTrinh);
                getDanhSachHocPhanInput().add(hocPhan);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
	
    public static void capNhatDanhSachHocTrinhInput() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(Main.getFileName()[2]);
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] strResult = line.split(" \\| ");
                String maMonHoc = strResult[0].trim();
                String tenMonHoc = strResult[1].trim();
                int soDonViHocTrinh = Integer.parseInt(strResult[2].trim());
                double trongSo = Double.parseDouble(strResult[3].trim());
                
                HocTrinh hocTrinhHocTruoc;
                if("null".equals(strResult[4].trim())) {
                    hocTrinhHocTruoc = null;
                } else {
                    hocTrinhHocTruoc = (HocTrinh) getInstanceOfMonHoc(strResult[4].trim(), "Niên chế");
                }
                
                ChuyenNganh chuyenNganh = getInstanceOfDanhMucChuyenNganh(strResult[5].trim());
                int kyHoc = Integer.parseInt(strResult[6].trim());
                HocTrinh hocTrinh = new HocTrinh(maMonHoc, tenMonHoc, soDonViHocTrinh, trongSo, hocTrinhHocTruoc, chuyenNganh, kyHoc);
                getDanhSachHocTrinhInput().add(hocTrinh);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static LopHoc<? extends MonHoc> getInstanceOfLopHoc(int maLop) {
        for(LopHoc<? extends MonHoc> lopHoc: getDanhSachLopInput())
            if(lopHoc.getMaLop() == maLop)
                return lopHoc;
        return null;
    }
    
    public static void capNhatDanhSachLopInput(){
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(Main.getFileName()[3]);
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] strResult = line.split(" \\| ");
                int maLop = Integer.parseInt(strResult[0].trim());
                String phongHoc = strResult[2].trim();
                String gioBatDau = strResult[3].trim();
                String gioKetThuc = strResult[4].trim();
                String heDaoTao = strResult[6].trim();
                Day ngayHoc = getInstanceOfNgay(strResult[5].trim());
                
                if(heDaoTao.equals("TC")) {
                    HocPhan hocPhan = (HocPhan) getInstanceOfMonHoc(strResult[1].trim(), "Tín chỉ");
                    LopHoc<HocPhan> lopHoc = new LopHoc(maLop, hocPhan, phongHoc, gioBatDau, gioKetThuc, ngayHoc);
                    getDanhSachLopTinChiInput().add(lopHoc);
                    getDanhSachLopInput().add(lopHoc);
                } else if(heDaoTao.equals("NC")) {
                    HocTrinh hocTrinh = (HocTrinh) getInstanceOfMonHoc(strResult[1].trim(), "Niên chế");
                    LopHoc<HocTrinh> lopHoc = new LopHoc(maLop, hocTrinh, phongHoc, gioBatDau, gioKetThuc, ngayHoc);
                    getDanhSachLopNienCheInput().add(lopHoc);
                    getDanhSachLopInput().add(lopHoc);
                } else {
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void capNhatHeThongDaoTaoTinChi(){
	heThongDaoTaoTinChi = new ArrayList<DanhMucMonHoc<HocPhan>>();
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.KHMT));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.HTTT));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.CNPM));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.KTMT));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.TTM));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.ATTT));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.CNTT));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.TA_KHKTVCN));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.TACNQT));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.CNTP));
	heThongDaoTaoTinChi.add(new DanhMucMonHoc<HocPhan>(ChuyenNganh.KTCDT));
	for(HocPhan hocPhan: getDanhSachHocPhanInput()){
            if("DC".equals(hocPhan.getChuongTrinh())){
                for(int i = 0; i < heThongDaoTaoTinChi.size(); i++)
                    heThongDaoTaoTinChi.get(i).getDanhSachDaiCuong().add(hocPhan);
            }else{
		String maHocPhan = hocPhan.getMaMonHoc();
		String maVien = maHocPhan.replaceAll("[0-9]", "");
		String tenChuongTrinh = hocPhan.getChuongTrinh();
		String tenChuyenNganh = tenChuongTrinh.substring(5);
		if("IT".equals(maVien)){
                    heThongDaoTaoTinChi.get(6).getDanhSachChuyenNganh().add(hocPhan);
                    if(tenChuyenNganh == ChuyenNganh.KHMT.name()){
			heThongDaoTaoTinChi.get(0).getDanhSachChuyenNganh().add(hocPhan);
                    } else if(tenChuyenNganh == ChuyenNganh.HTTT.name()){
                        heThongDaoTaoTinChi.get(1).getDanhSachChuyenNganh().add(hocPhan);
                    } else if(tenChuyenNganh == ChuyenNganh.CNPM.name()){
			heThongDaoTaoTinChi.get(2).getDanhSachChuyenNganh().add(hocPhan);
                    } else if(tenChuyenNganh == ChuyenNganh.KTMT.name()){
                        heThongDaoTaoTinChi.get(3).getDanhSachChuyenNganh().add(hocPhan);
                    } else if(tenChuyenNganh == ChuyenNganh.TTM.name()){
                        heThongDaoTaoTinChi.get(4).getDanhSachChuyenNganh().add(hocPhan);
                    } else if(tenChuyenNganh == ChuyenNganh.ATTT.name()){
                        heThongDaoTaoTinChi.get(5).getDanhSachChuyenNganh().add(hocPhan);
                    } else {
			heThongDaoTaoTinChi.get(0).getDanhSachChuyenNganh().add(hocPhan);
			heThongDaoTaoTinChi.get(1).getDanhSachChuyenNganh().add(hocPhan);
			heThongDaoTaoTinChi.get(2).getDanhSachChuyenNganh().add(hocPhan);
			heThongDaoTaoTinChi.get(3).getDanhSachChuyenNganh().add(hocPhan);
			heThongDaoTaoTinChi.get(4).getDanhSachChuyenNganh().add(hocPhan);
			heThongDaoTaoTinChi.get(5).getDanhSachChuyenNganh().add(hocPhan);
                    }
		} else if("FL".equals(maVien)){
                    if(tenChuyenNganh == ChuyenNganh.TA_KHKTVCN.name()){
			heThongDaoTaoTinChi.get(7).getDanhSachChuyenNganh().add(hocPhan);
                    } else{
			heThongDaoTaoTinChi.get(8).getDanhSachChuyenNganh().add(hocPhan);
                    }
		} else if ("BF".equals(maVien)) {
                    heThongDaoTaoTinChi.get(9).getDanhSachChuyenNganh().add(hocPhan);
		} else if ("ME".equals(maVien)){
                    heThongDaoTaoTinChi.get(10).getDanhSachChuyenNganh().add(hocPhan);
		} else {
                    continue;
		}
            }
				
        }
	
        for(int i = 0; i < heThongDaoTaoTinChi.size(); i++){
            heThongDaoTaoTinChi.get(i).getChuongTrinhDaoTao().addAll(heThongDaoTaoTinChi.get(i).getDanhSachDaiCuong());
            heThongDaoTaoTinChi.get(i).getChuongTrinhDaoTao().addAll(heThongDaoTaoTinChi.get(i).getDanhSachChuyenNganh());
	}
    }
	
    public static DanhMucMonHoc<HocPhan> getInstanceOfDanhMucHocPhan(ChuyenNganh chuyenNganh){
    	for(DanhMucMonHoc<HocPhan> danhMucHocPhan: getHeThongDaoTaoTinChi())
            if(danhMucHocPhan.getChuyenNganh() == chuyenNganh){
		return danhMucHocPhan;
	}
        return null;
    }
	
    public static void capNhatHeThongDaoTaoNienChe(){
	heThongDaoTaoNienChe = new ArrayList<DanhMucMonHoc<HocTrinh>>();
	heThongDaoTaoNienChe.add(new DanhMucMonHoc<HocTrinh>(ChuyenNganh.KSTN_CNTT));
	heThongDaoTaoNienChe.add(new DanhMucMonHoc<HocTrinh>(ChuyenNganh.KSCLC_HTTT));
	heThongDaoTaoNienChe.add(new DanhMucMonHoc<HocTrinh>(ChuyenNganh.KSTN_CDT));
	heThongDaoTaoNienChe.add(new DanhMucMonHoc<HocTrinh>(ChuyenNganh.CTTT_CDT));
	
	for(HocTrinh hocTrinh: getDanhSachHocTrinhInput()) {
            if(hocTrinh.getChuyenNganh() == ChuyenNganh.CHUA_PHAN)
                for(int i = 0; i < heThongDaoTaoNienChe.size(); i++)
                    heThongDaoTaoNienChe.get(i).getDanhSachDaiCuong().add(hocTrinh);
            else if(hocTrinh.getChuyenNganh() == ChuyenNganh.KSTN_CNTT)
                heThongDaoTaoNienChe.get(0).getDanhSachChuyenNganh().add(hocTrinh);
            else if(hocTrinh.getChuyenNganh() == ChuyenNganh.KSCLC_HTTT)
                heThongDaoTaoNienChe.get(1).getDanhSachChuyenNganh().add(hocTrinh);
            else if(hocTrinh.getChuyenNganh() == ChuyenNganh.KSTN_CDT)
                heThongDaoTaoNienChe.get(2).getDanhSachChuyenNganh().add(hocTrinh);
            else 
                heThongDaoTaoNienChe.get(3).getDanhSachChuyenNganh().add(hocTrinh);
	}
        for(int i = 0; i < heThongDaoTaoNienChe.size(); i++){
            heThongDaoTaoNienChe.get(i).getChuongTrinhDaoTao().addAll(heThongDaoTaoNienChe.get(i).getDanhSachDaiCuong());
            heThongDaoTaoNienChe.get(i).getChuongTrinhDaoTao().addAll(heThongDaoTaoNienChe.get(i).getDanhSachChuyenNganh());
	}
    }
	
    public static DanhMucMonHoc<HocTrinh> getInstanceOfDanhMucHocTrinh(ChuyenNganh chuyenNganh){
	for(DanhMucMonHoc<HocTrinh> danhMucHocTrinh: getHeThongDaoTaoNienChe())
            if(danhMucHocTrinh.getChuyenNganh() == chuyenNganh){
		return danhMucHocTrinh;
	}
        return null;
    }
    
    public static void capNhatBangDiemSinhVien(SinhVien sinhVien) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(Main.getFileName()[4] + "/" + sinhVien.getMssv() + "_bangdiem.txt");
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] strResult = line.split(" \\| ");
                if(sinhVien instanceof SinhVienTinChi) {
                    HocPhan hocPhan = (HocPhan) getInstanceOfMonHoc(strResult[0].trim(), "Tín chỉ");
                    double diemGiuaKy = Double.parseDouble(strResult[2].trim());
                    double diemCuoiKy = Double.parseDouble(strResult[3].trim());
                    Diem<HocPhan> diem = new Diem<HocPhan>(hocPhan, diemGiuaKy, diemCuoiKy);
                    ((SinhVienTinChi) sinhVien).getBangDiem().addDiem(diem);
                } else {
                    HocTrinh hocTrinh = (HocTrinh) getInstanceOfMonHoc(strResult[0].trim(), "Niên chế");
                    double diemGiuaKy = Double.parseDouble(strResult[2].trim());
                    double diemCuoiKy = Double.parseDouble(strResult[3].trim());
                    Diem<HocTrinh> diem = new Diem<HocTrinh>(hocTrinh, diemGiuaKy, diemCuoiKy);
                    ((SinhVienNienChe) sinhVien).getBangDiem().addDiem(diem);
                }
            }
        } catch(java.io.FileNotFoundException e) {
            Output.outputBangDiem(sinhVien);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void capNhatSinhVienDangKyLop(LopHoc<? extends MonHoc> lopHoc) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(Main.getFileName()[5] + "/" + lopHoc.getMaLop() + "_dangky.txt");
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] strResult = line.split(" \\| ");
                int mssv = Integer.parseInt(strResult[0].trim());
                SinhVien sinhVien = getInstanceOfSinhVien(mssv);
                lopHoc.getDanhSachSinhVien().add(sinhVien);
            }
        } catch(java.io.FileNotFoundException e) {
            Output.outputDangKy(lopHoc);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void capNhatLopDangKyCuaSinhVien(SinhVien sinhVien) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(Main.getFileName()[6] + "/" + sinhVien.getMssv() + "_dangky.txt");
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] strResult = line.split(" \\| ");
                int maLop = Integer.parseInt(strResult[0].trim());
                if(sinhVien instanceof SinhVienTinChi) {
                    LopHoc<HocPhan> lopHoc = (LopHoc<HocPhan>) getInstanceOfLopHoc(maLop);
                    ((SinhVienTinChi) sinhVien).getDanhSachDangKy().addLopHoc(lopHoc);
                }else {
                    LopHoc<HocTrinh> lopHoc = (LopHoc<HocTrinh>) getInstanceOfLopHoc(maLop);
                    ((SinhVienNienChe) sinhVien).getDanhSachDangKy().addLopHoc(lopHoc);
                }
            }
        } catch(java.io.FileNotFoundException e) {
            Output.outputDangKy(sinhVien);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
