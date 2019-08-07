package svnc;

import java.util.ArrayList;

import main.Input;
import svclass.ChuyenNganh;
import svclass.DanhMucMonHoc;
import users.SinhVien;

public class LopSinhVien {
    private String tenLop;
    private DanhMucMonHoc<HocTrinh> chuongTrinhDaoTao;
    private ArrayList<HocTrinh> danhMucHocTrinhBatBuoc;
    private ArrayList<SinhVienNienChe> danhSachSinhVien;

    public LopSinhVien(String tenLop) {
        this.tenLop = tenLop;
        this.danhMucHocTrinhBatBuoc = new ArrayList<HocTrinh>();
        this.danhSachSinhVien = new ArrayList<SinhVienNienChe>();
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public DanhMucMonHoc<HocTrinh> getChuongTrinhDaoTao() {
        return chuongTrinhDaoTao;
    }

    public void setChuongTrinhDaoTao(DanhMucMonHoc<HocTrinh> chuongTrinhDaoTao) {
        this.chuongTrinhDaoTao = chuongTrinhDaoTao;
    }

    public ArrayList<HocTrinh> getDanhMucHocTrinhBatBuoc() {
	return danhMucHocTrinhBatBuoc;
    }

    public ArrayList<SinhVienNienChe> getDanhSachSinhVien() {
	return danhSachSinhVien;
    }
	
    public void capNhatChuongTrinhDaoTao() {
	ChuyenNganh chuyenNganh = getDanhSachSinhVien().get(0).getChuyenNganh();
    	for(int i = 0; i < Input.getHeThongDaoTaoNienChe().size(); i++) {
            DanhMucMonHoc<HocTrinh> chuongTrinh = Input.getHeThongDaoTaoNienChe().get(i);
            if(chuyenNganh == chuongTrinh.getChuyenNganh())
		setChuongTrinhDaoTao(chuongTrinh);
	}
    }
	
    public void capNhatDanhMucHocTrinhBatBuoc(int kyHoc) {
	for(HocTrinh hocTrinh: getChuongTrinhDaoTao().getChuongTrinhDaoTao())
            if(hocTrinh.getKyHoc() == kyHoc)
		getDanhMucHocTrinhBatBuoc().add(hocTrinh);
	}
	
    public boolean coThuocChuongTrinh(HocTrinh hocTrinh) {
    	for(HocTrinh hocTrinh2: getDanhMucHocTrinhBatBuoc())
            if(hocTrinh == hocTrinh2) return true;
	return false;
    }
	
    public void inDanhSachSinhVien() {
    	System.out.println("\nDANH SÁCH SINH VIÊN LỚP " + getTenLop());
    	for(int i = 0; i < getDanhSachSinhVien().size(); i++){
            SinhVien sinhVien = getDanhSachSinhVien().get(i);
            System.out.printf("%d. %d %s\n", i + 1, sinhVien.getMssv(), sinhVien.getTen());
	}
    }
}
