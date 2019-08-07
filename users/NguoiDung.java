package users;

public abstract class NguoiDung {
    private String ten;
    private String ngaySinh;
    private boolean gioiTinh;
    private String diaChi;
    
    public NguoiDung(String ten, String ngaySinh, boolean gioiTinh, String diaChi)
    {
        this.ten = ten;
        this.ngaySinh = dieuChinhNgay(ngaySinh);
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
    }
 
    public String getTen() {
	return ten;
    }

    public void setTen(String ten) {
	this.ten = ten;
    }

    public String getNgaySinh() {
	return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
	this.ngaySinh = dieuChinhNgay(ngaySinh);
    }

    public boolean isGioiTinh() {
	return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
	this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
	return diaChi;
    }

    public void setDiaChi(String diaChi) {
	this.diaChi = diaChi;
    }

    public String dieuChinhNgay(String date) {
        int ngay = Integer.parseInt(date.substring(0, 2));
	int thang = Integer.parseInt(date.substring(3, 5));
	int nam = Integer.parseInt(date.substring(6, 10));
	int[] ngayTrongThang = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	if(thang < 1 || thang > 12)
            System.out.println("Bạn nhập sai tháng.");
	else if(ngay <= 0 || ngay > ngayTrongThang[thang-1])
            System.out.println("Bạn nhập sai ngày.");
	else if(ngay == 29 && thang == 2 && !(((nam%4==0)&&(nam%100!=0))||(nam%400==0)))
            System.out.println("Bạn nhập sai ngày.");
	else
            return date;
	return null;
    }
    
    public abstract void inThongTin();
}
