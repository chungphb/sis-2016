package users;

import java.util.ArrayList;
import java.util.Calendar;

import svclass.ChuyenNganh;
import svclass.LoaiTotNghiep;
import svclass.LopHoc;
import svclass.MonHoc;
import svclass.TrangThaiHocTap;
 
public abstract class SinhVien extends NguoiDung{
    private final int MSSV;
    private String khoaVien;
    private ChuyenNganh chuyenNganh;
    private LoaiTotNghiep xepLoaiTotNghiep;
    private String lopSinhVien;
    private int diemTOEIC;
    private String lyDoChuaTotNghiep;
    private TrangThaiHocTap trangThaiHoc;
    
    public SinhVien(int MSSV, String ten, String ngaySinh, boolean gioiTinh, String diaChi, String khoaVien, ChuyenNganh chuyenNganh, String lopSinhVien) {
    	super(ten, ngaySinh, gioiTinh, diaChi);
	this.MSSV = MSSV;
	this.khoaVien = khoaVien;
	this.chuyenNganh = chuyenNganh;
	this.lopSinhVien = lopSinhVien;
        trangThaiHoc = TrangThaiHocTap.DANGHOC;
    }

    public int getMssv() {
	return MSSV;
    }

    public String getKhoaVien() {
	return khoaVien;
    }

    public ChuyenNganh getChuyenNganh() {
	return chuyenNganh;
    }

    public void setChuyenNganh(ChuyenNganh chuyenNganh) {
	this.chuyenNganh = chuyenNganh;
    }

    public LoaiTotNghiep getXepLoaiTotNghiep() {
	return xepLoaiTotNghiep;
    }

    public void setXepLoaiTotNghiep(LoaiTotNghiep xepLoaiTotNghiep) {
	this.xepLoaiTotNghiep = xepLoaiTotNghiep;
    }

    public String getLopSinhVien() {
	return lopSinhVien;
    }

    public void setLopSinhVien(String lopSinhVien) {
	this.lopSinhVien = lopSinhVien;
    }

    public int getDiemTOEIC() {
	return diemTOEIC;
    }

    public void setDiemTOEIC(int diemTOEIC) {
	if(diemTOEIC >= 0 && diemTOEIC <= 990)
            this.diemTOEIC = diemTOEIC;
	else{
            System.out.println("Giá trị không thuộc [0, 990]. Giữ nguyên giá trị trước khi nhập.");
	}
    }
    
    public String getLyDoChuaTotNghiep() {
        return lyDoChuaTotNghiep;
    }
	
    public int getKhoaHoc() {
	return getMssv()/10000 - 1955;
    }
	
    public int getKyHoc() {
	int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
	int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	if((currentMonth >=1) && (currentMonth <= 5))
            return 2*(currentYear - getMssv()/10000);
        else if((currentMonth >= 8) && (currentMonth <=12))
            return 2*(currentYear - getMssv()/10000) + 1;
	else
            return -1;
    }
	
    public void setLyDoChuaTotNghiep(String lyDo) {
        this.lyDoChuaTotNghiep = lyDo;
    }
    
    public TrangThaiHocTap getTrangThaiHoc() {
	return trangThaiHoc;
    }
    
    public void inThongTin() {
	System.out.println(String.format("1. %s: %d\n2. %s: %s\n3. %s: %s\n4. %s: %s\n5. %s: %s\n6. %s: %s\n7. %s: %s\n8. %s: %s",	 
    	   	"MSSV", this.getMssv(), 
                "Họ và tên", getTen(), 
                "Ngày sinh", getNgaySinh(),
                "Giới tính",((isGioiTinh())? "Nam": "Nữ"),
                "Địa chỉ", getDiaChi(),
                "Khoa Viện", getKhoaVien(),
                "Chuyên ngành", getChuyenNganh().name(),
		"Lớp sinh viên", getLopSinhVien()));
    }
    
    @Override
    public String toString() {
	return String.format("%-10d | %-20s | %-10s | %-3s | %-40s | %-35s | %-10s | %-25s", 
                getMssv(), getTen(), getNgaySinh(), isGioiTinh()? "Nam": "Nữ", 
                getDiaChi(), getKhoaVien(), getChuyenNganh().name(), getLopSinhVien());
    }
	
    public abstract boolean dieuKienTotNghiep();
}
