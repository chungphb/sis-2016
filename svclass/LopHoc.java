package svclass;

import java.util.ArrayList;
import svtc.HocPhan;
import users.SinhVien;

public class LopHoc <T extends MonHoc> {
	
    private final int MAXSINHVIEN = 50;
    
    private int maLop, soLuongDangKy;
    private String phongHoc;
    private T monHoc;
    private String gioBatDau, gioKetThuc;
    private Day ngayHoc;
    private ArrayList<SinhVien> danhSachSinhVien;
    
    public LopHoc(int maLop, T monHoc, String phongHoc, String gioBatDau, String gioKetThuc, Day ngayHoc) {
        this.maLop = maLop; 
        this.monHoc = monHoc;
        this.phongHoc = phongHoc;
        this.gioBatDau = dieuChinhGio(gioBatDau);
        this.gioKetThuc = dieuChinhGio(gioKetThuc);
        kiemTraGioHopLe();
        this.ngayHoc = ngayHoc;
        this.soLuongDangKy = 0;
        danhSachSinhVien = new ArrayList<SinhVien>();
    }

    public int getMAXSINHVIEN() {
        return MAXSINHVIEN;
    }
    
    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
	this.maLop = maLop;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
	this.phongHoc = phongHoc;
    }

    public T getMonHoc() {
	return monHoc;
    }

    public int getSoLuongDangKy() {
	return getDanhSachSinhVien().size();
    }

    public ArrayList<SinhVien> getDanhSachSinhVien() {
	return danhSachSinhVien;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public String getGioKetThuc() {
	return gioKetThuc;
    }

    public void setThoiGianHoc(String gioBatDau, String gioKetThuc) {
	this.gioBatDau = dieuChinhGio(gioBatDau);
	this.gioKetThuc = dieuChinhGio(gioKetThuc);
	kiemTraGioHopLe();
    }

    public Day getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(Day ngayHoc) {
        this.ngayHoc = ngayHoc;
    }
    
    public void removeSinhVien(SinhVien sinhVien) {
	for(int i = 0; i < getDanhSachSinhVien().size(); i++){
            SinhVien sinhVien2 = getDanhSachSinhVien().get(i);
            if (sinhVien == sinhVien2) 
		getDanhSachSinhVien().remove(i);
        }
    }
	
    public SinhVien findSinhVien(SinhVien sinhVien) {
	for(SinhVien sinhVien2: getDanhSachSinhVien())
            if(sinhVien == sinhVien2)
		return sinhVien2;
        return null;
    }
	
    public void inDanhSachSinhVien() {
	System.out.println("\nDANH SÁCH SINH VIÊN ĐĂNG KÝ LỚP " + getMaLop());
	for(int i = 0; i < getDanhSachSinhVien().size(); i++){
            SinhVien sinhVien = getDanhSachSinhVien().get(i);
            System.out.printf("%2d. %-10d %-40s\n", i + 1, sinhVien.getMssv(), sinhVien.getTen());
        }
    }
	
    public int getGio(String thoiGian) {
        int indexOfH = thoiGian.indexOf('h');
	if(indexOfH != -1){
            return Integer.parseInt(thoiGian.substring(0, indexOfH));
        }
        return -1;
    }
    
    public int getPhut(String thoiGian) {
        int indexOfH = thoiGian.indexOf('h');
	if(indexOfH != -1){
            return Integer.parseInt(thoiGian.substring(indexOfH + 1));
        }
        return -1;
    }
    public String dieuChinhGio(String thoiGian) {
        int hour = getGio(thoiGian);
        int minute = getPhut(thoiGian);
        if((hour != -1) && (minute != -1)) {
            if(hour >= 6 && hour <= 17 && minute >= 0 && minute < 60)
		return thoiGian;
            else if (hour < 6 || hour > 17)
                System.out.println("Nhập sai giờ. Điều chỉnh về 0h00.");
            else
		System.out.println("Nhập sai phút. Điều chỉnh về 0h00.");
        }else{
            System.out.println("Nhập sai định dạng. Điều chỉnh về 0h00.");
        }
	return "0h00";
    }
	
    private void kiemTraGioHopLe() {
	int hourBatDau = getGio(getGioBatDau());
	int minuteBatDau = getPhut(getGioBatDau());
	int hourKetThuc = getGio(getGioKetThuc());
	int minuteKetThuc = getGio(getGioKetThuc());
		
	if((hourKetThuc*60+minuteKetThuc) >= (hourBatDau*60 + minuteBatDau + 45))
            return;
	else {
            System.out.println("Không đủ thời lượng một tiết học. Điều chỉnh về 0h00.");
            gioBatDau = "0h00";
            gioKetThuc = "0h00";
	}
    }
        
    public int doiThoiGianRaPhut(String thoiGian) {
        return getGio(thoiGian)*60 + getPhut(thoiGian);
    }
    @Override
    public String toString() {
        return String.format("%-8d | %-7s | %-7s | %-5s | %-5s | %-10s | %-20s", 
            getMaLop(), getMonHoc().getMaMonHoc(), 
            getPhongHoc(), getGioBatDau(), getGioKetThuc(),
            getNgayHoc().name(), (this.getMonHoc() instanceof HocPhan)? "TC": "NC");
    }
}