package svclass;

import java.util.ArrayList;

import svclass.ChuyenNganh;
import svclass.MonHoc;
import svnc.HocTrinh;
import svtc.HocPhan;

public class DanhMucMonHoc <T extends MonHoc>{
    private ArrayList<T> danhSachDaiCuong;
    private ArrayList<T> danhSachChuyenNganh;
    private ArrayList<T> chuongTrinhDaoTao;
    private ChuyenNganh chuyenNganh;
    private int soDonViHocTap;

    public DanhMucMonHoc(ChuyenNganh chuyenNganh) {
	danhSachDaiCuong = new ArrayList<T>();
    	danhSachChuyenNganh = new ArrayList<T>();
	chuongTrinhDaoTao = new ArrayList<T>();
	this.chuyenNganh = chuyenNganh;
    }
	
    public ArrayList<T> getDanhSachDaiCuong() {
	return danhSachDaiCuong;
    }

    public ArrayList<T> getDanhSachChuyenNganh() {
        return danhSachChuyenNganh;
    }

    public ArrayList<T> getChuongTrinhDaoTao() {
	return chuongTrinhDaoTao;
    }
	
    public void taoChuongTrinhDaoTao() {
	getChuongTrinhDaoTao().addAll(getDanhSachDaiCuong());
	getChuongTrinhDaoTao().addAll(getDanhSachChuyenNganh());
    }
	
    public ChuyenNganh getChuyenNganh() {
	return chuyenNganh;
    }

    public void setChuyenNganh(ChuyenNganh chuyenNganh) {
    	this.chuyenNganh = chuyenNganh;
    }

    public int getSoDonViHocTap() {
        for(T monHoc: getChuongTrinhDaoTao())
            if(monHoc instanceof HocPhan)
		soDonViHocTap += ((HocPhan) monHoc).getSoTinChi();
            else
		soDonViHocTap += ((HocTrinh) monHoc).getSoDonViHocTrinh();
        return soDonViHocTap;
    }

    public boolean coThuocChuongTrinh(T monHoc) {
	for(T monHoc2: getChuongTrinhDaoTao()){   
            if(monHoc == monHoc2) return true;
	}
	return false;
    }
	
    public void inDanhMucMonHoc() {
        System.out.println("\nDANH MỤC CÁC MÔN HỌC CHUYÊN NGÀNH " + getChuyenNganh().name());
	for(int i = 0; i < getChuongTrinhDaoTao().size(); i++){
            MonHoc monHoc = getChuongTrinhDaoTao().get(i);
            System.out.printf("%2d. %s\n", i + 1, monHoc.toString());
	}
    }
}
