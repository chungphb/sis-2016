package svclass;

import java.util.ArrayList;

import svnc.HocTrinh;
import svtc.HocPhan;

public class DanhSachDangKy <T extends MonHoc>{
    private final int SOTINCHIMAX = 24;
    private final int SODONVIHOCTRINHMAX = 24;
    private int soTinChiDaDangKy;
    private int soDvhtDaDangKy;
    private ArrayList<LopHoc<T>> danhSachLopHoc;
    
    public DanhSachDangKy() {
    	danhSachLopHoc = new ArrayList<LopHoc<T>>();
    }
	
    public int getSoTinChiDaDangKy() {
	return soTinChiDaDangKy;
    }

    public void setSoTinChiDaDangKy(int soTinChiDaDangKy) {
	this.soTinChiDaDangKy = soTinChiDaDangKy;
    }

    public int getSoDvhtDaDangKy() {
	return soDvhtDaDangKy;
    }

    public void setSoDvhtDaDangKy(int soDvhtDaDangKy) {
        this.soDvhtDaDangKy = soDvhtDaDangKy;
    }

    public ArrayList<LopHoc<T>> getDanhSachLopHoc() {
    	return danhSachLopHoc;
    }

    public void setDanhSachLopHoc(ArrayList<LopHoc<T>> danhSachLopHoc) {
	this.danhSachLopHoc = danhSachLopHoc;
    }

    public int getSOTINCHIMAX() {
	return SOTINCHIMAX;
    }

    public int getSODONVIHOCTRINHMAX() {
	return SODONVIHOCTRINHMAX;
    }

    public boolean daDangKy(T monHoc) {
	for(LopHoc<T> lopHoc: getDanhSachLopHoc())
            if(lopHoc.getMonHoc() == monHoc) return true;
        return false;
    }
	
    public void addLopHoc(LopHoc<T> lopHoc) {
	getDanhSachLopHoc().add(lopHoc);
    }
	
    public void removeLopHoc(LopHoc<T> lopHocCanXoa) {
	for(int i = 0; i < getDanhSachLopHoc().size(); i++) {
            LopHoc<T> lopHoc = getDanhSachLopHoc().get(i);
            if(lopHoc == lopHocCanXoa) getDanhSachLopHoc().remove(i);
	}
    }
	
    public void updateLopHoc(LopHoc<T> lopCu, LopHoc<T> lopMoi) {
        for(LopHoc<T> lopHoc: getDanhSachLopHoc()) 
	if(lopHoc == lopCu) lopHoc = lopMoi;
    }
	
    public boolean daFullChua(LopHoc<T> lopHoc) {
	if(lopHoc.getMonHoc() instanceof HocPhan){
            if(getSOTINCHIMAX() < getSoTinChiDaDangKy() + ((HocPhan)lopHoc.getMonHoc()).getSoTinChi())
            	return true;
	}else if(lopHoc.getMonHoc() instanceof HocTrinh){
            if(getSODONVIHOCTRINHMAX() < getSoDvhtDaDangKy() + ((HocTrinh)lopHoc.getMonHoc()).getSoDonViHocTrinh())
		return true;
	}
	return false;
    }
    
    public boolean trungLich(LopHoc<T> lopDangKy) {
        System.out.println("HI");
        for(int i = 0; i < getDanhSachLopHoc().size(); i++) {
            LopHoc<T> lopHoc = getDanhSachLopHoc().get(i);
            if(lopDangKy.getNgayHoc() == lopHoc.getNgayHoc()) {
                System.out.println("HI");
                if((lopHoc.doiThoiGianRaPhut(lopHoc.getGioBatDau()) >= lopDangKy.doiThoiGianRaPhut(lopDangKy.getGioBatDau()) 
                 && lopHoc.doiThoiGianRaPhut(lopHoc.getGioBatDau()) <= lopDangKy.doiThoiGianRaPhut(lopDangKy.getGioKetThuc())) 
                 ||(lopHoc.doiThoiGianRaPhut(lopHoc.getGioKetThuc()) >= lopDangKy.doiThoiGianRaPhut(lopDangKy.getGioBatDau()) 
                 && lopHoc.doiThoiGianRaPhut(lopHoc.getGioKetThuc()) <= lopDangKy.doiThoiGianRaPhut(lopDangKy.getGioKetThuc())))
                    return true;
            }
        }
        return false;
    }
    
    public void inDanhSachDangKy() {
	System.out.println("\nDANH SÁCH LỚP HỌC ĐÃ ĐĂNG KÝ CỦA SINH VIÊN");
	for(LopHoc<T> lopHoc: getDanhSachLopHoc())
            System.out.println("Lớp học: " + lopHoc.getMaLop() + " - Học phần: " + lopHoc.getMonHoc().getTenMonHoc());
    }
}
