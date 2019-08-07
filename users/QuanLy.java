package users;

import svclass.MonHoc;
import svnc.HocTrinh;
import svnc.SinhVienNienChe;
import svtc.HocPhan;
import svtc.SinhVienTinChi;

import java.util.ArrayList;

import main.Input;
import svclass.BangDiem;
import svclass.Diem;
import svclass.LopHoc;

public class QuanLy extends NguoiDung{
    private ArrayList<SinhVien> danhSachSinhVien;
    private ArrayList<LopHoc<? extends MonHoc>> danhSachLopHoc;
    private int maSoQuanLy;
    private int khoaQuanLy;
    
    public QuanLy(int maSoQuanLy, String ten, String ngaySinh, boolean gioiTinh, String diaChi, int khoaQuanLy) {
        super(ten, ngaySinh, gioiTinh, diaChi);
	this.maSoQuanLy = maSoQuanLy;
	this.khoaQuanLy = khoaQuanLy;
	danhSachSinhVien = new ArrayList<SinhVien>();
        danhSachLopHoc = new ArrayList<LopHoc<? extends MonHoc>>();
    }
	
    public ArrayList<SinhVien> getDanhSachSinhVien() {
	return danhSachSinhVien;
    }

    public ArrayList<LopHoc<? extends MonHoc>> getDanhSachQuanLy() {
        return danhSachLopHoc;
    }
    
    public int getMaSoQuanLy() {
	return maSoQuanLy;
    }

    public void setMaSoQuanLy(int maSoQuanLy) {
	this.maSoQuanLy = maSoQuanLy;
    }

    public int getKhoaQuanLy() {
	return khoaQuanLy;
    }

    public void setKhoaQuanLy(int khoaQuanLy) {
	this.khoaQuanLy = khoaQuanLy;
    }

    public void capNhatDanhSachSinhVienQuanLy() {
    	for(SinhVien sinhVien: Input.getDanhSachSinhVienInput())
            if(!getDanhSachSinhVien().contains(sinhVien) && sinhVien.getKhoaHoc() == getKhoaQuanLy()) getDanhSachSinhVien().add(sinhVien);
    }
    
    public void capNhatDanhSachLopQuanLy() {
        for(LopHoc <HocPhan> lopHoc: Input.getDanhSachLopTinChiInput())
            getDanhSachQuanLy().add(lopHoc);
        for(LopHoc <HocTrinh> lopHoc: Input.getDanhSachLopNienCheInput())
            getDanhSachQuanLy().add(lopHoc);        
    }
    
    public void xetTotNghiep() {
    	System.out.println("\nXÉT TỐT NGHIỆP KHÓA " + getKhoaQuanLy());
    	for(int i = 0; i < getDanhSachSinhVien().size(); i++) {
            SinhVien sinhVien = getDanhSachSinhVien().get(i);
            if(sinhVien.dieuKienTotNghiep()) {
    		System.out.printf("%2d. | %s | %40s\n", i+1, sinhVien, "Đủ điều kiện tốt nghiệp.");
            }else {
    		System.out.printf("%2d. | %s | %40s\n", i+1, sinhVien, "Chưa đủ điều kiện tốt nghiệp.");
            }
    	}
    }
    
    public void nhapDiem(SinhVien sinhVien, MonHoc monHoc, double giuaKy, double cuoiKy, boolean nhapMoi) {
    	if(sinhVien instanceof SinhVienTinChi && monHoc instanceof HocPhan){
            BangDiem<HocPhan> bangDiem = ((SinhVienTinChi)sinhVien).getBangDiem();
            HocPhan hocPhan = (HocPhan)monHoc;
            Diem<HocPhan> diemcu = bangDiem.findDiem(hocPhan);
            Diem<HocPhan> diem = new Diem<HocPhan>(hocPhan, giuaKy, cuoiKy);
            if(nhapMoi) {
                if(diemcu == null)                                              // Neu sinh vien chua hoc mon nay lan nao.
                    bangDiem.addDiem(diem);													
                else if (diemcu.getTongKet() < diem.getTongKet())		// Neu sinh vien hoc lại hoac hoc cai thien va diem moi cao hon diem cu. (thang 4)
                    bangDiem.updateDiem(diem);
                else if (diemcu.getTongKet() == diem.getTongKet()		// Neu sinh vien hoc lại hoac hoc cai thien va diem moi cao hon diem cu. (thang 10)
                    && diemcu.tinhTongKetThangMuoi() < diem.tinhTongKetThangMuoi()) 
                    bangDiem.updateDiem(diem);
            } else {
                    bangDiem.updateDiem(diem);
            }
	}else if (sinhVien instanceof SinhVienNienChe && monHoc instanceof HocTrinh) {
            BangDiem<HocTrinh> bangDiem = ((SinhVienNienChe)sinhVien).getBangDiem();
	    HocTrinh hocTrinh = (HocTrinh)monHoc;
	    Diem<HocTrinh> diemcu = bangDiem.findDiem(hocTrinh);
	    Diem<HocTrinh> diem = new Diem<HocTrinh>(hocTrinh, giuaKy, cuoiKy);
	    if(nhapMoi) {
                if(diemcu == null) 						// Neu sinh vien chua hoc mon nay lan nao.
                    bangDiem.addDiem(diem);													
                else if (diemcu.getTongKet() < diem.getTongKet())		// Neu sinh vien hoc lại hoac hoc cai thien va diem moi cao hon diem cu. (thang 4)
                    bangDiem.updateDiem(diem);
                else if (diemcu.getTongKet() == diem.getTongKet()		// Neu sinh vien hoc lại hoac hoc cai thien va diem moi cao hon diem cu. (thang 10)
                    && diemcu.tinhTongKetThangMuoi() < diem.tinhTongKetThangMuoi()) 
                    bangDiem.updateDiem(diem);
            }else {
                bangDiem.updateDiem(diem);
            }
        }
    }
    
    public void nhapDiem(LopHoc<? extends MonHoc> lopHoc, double[][] diem, boolean nhapMoi) {
        for(int i = 0; i < lopHoc.getDanhSachSinhVien().size(); i++) {
            SinhVien sinhVien = lopHoc.getDanhSachSinhVien().get(i);
	    nhapDiem(sinhVien, lopHoc.getMonHoc(), diem[i][0], diem[i][1], nhapMoi);
	}
    }
    
    @Override
    public void inThongTin() {
       System.out.println(String.format("%s: %d\n%s: %s\n%s: %s\n%s: %s\n%s: %s",	 
            "MSQL", this.maSoQuanLy, 
            "Họ và tên", getTen(), 
            "Ngày sinh", getNgaySinh(),
            "Giới tính",((isGioiTinh())? "Nam": "Nữ"),
            "Địa chỉ", getDiaChi()));      
    }
}
