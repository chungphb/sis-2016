package svclass;

import svtc.HocPhan;

public class Diem <T extends MonHoc> {
    private T monHoc;
    private double giuaKy;
    private double cuoiKy;
    private double tongKet;
    private String diemBangChu;
    
    public Diem(T monHoc, double giuaKy, double cuoiKy) {
    	this.giuaKy = dieuChinhDiem(giuaKy);
    	this.cuoiKy = dieuChinhDiem(cuoiKy);
    	this.monHoc = monHoc;
    	this.tongKet = tinhTongKet();
	}
    
    public T getMonHoc() {
    	return monHoc;
    }
    
    public double getGiuaKy() {
	return giuaKy;
    }
    
    public void setGiuaKy(double giuaKy) {
	this.giuaKy = dieuChinhDiem(giuaKy);
    }
    
    public double getCuoiKy() {
	return cuoiKy;
    }
    
    public void setCuoiKy(double cuoiKy) {
    	this.cuoiKy = dieuChinhDiem(cuoiKy);
    }
    
    public double getTongKet() {
	return tongKet;
    }
    
    public void setTongKet(double tongKet) {
	this.tongKet = tongKet;
    }
    
    public String getDiemBangChu() {
    	return diemBangChu;
    }
    
    public double tinhTongKet() {
    	double heso = getMonHoc().getTrongSo();
    	double diem = getCuoiKy()*heso + getGiuaKy()*(1 - heso);
        
    	if(getMonHoc() instanceof HocPhan){
            if(diem >= 0.0 && diem < 4.0) { 
	       	diemBangChu = "F";
	       	return 0.0;     
	    }else if(diem >= 4.0 && diem < 5.0) {
	       	diemBangChu = "D";
	       	return 1.0;
	    }else if(diem >= 5.0 && diem < 5.5)	{
	       	diemBangChu = "D+";
	       	return 1.5;
            }else if(diem >= 5.5 && diem < 6.5)	{
	       	diemBangChu = "C";
	       	return 2.0;
	    }else if(diem >= 6.5 && diem < 7.0) {
	       	diemBangChu = "C+";
	       	return 2.5;
	    }else if(diem >= 7.0 && diem < 8.0)	{
	       	diemBangChu = "B";
	       	return 3.0;
	    }else if(diem >= 8.0 && diem < 8.5) {
	       	diemBangChu = "B+";
	       	return 3.5;
	    }else if(diem >= 8.5 && diem < 9.5){
	       	diemBangChu = "A";
	       	return 4.0;
	    }else {
	      	diemBangChu = "A+";
	       	return 4.0;
            }
        }else{
            return diem;
        }
    }
    
    public double tinhTongKetThangMuoi() {
    	double heso = getMonHoc().getTrongSo();
    	return getCuoiKy()*heso + getGiuaKy()*(1 - heso);
    }
    
    public double dieuChinhDiem(double diem) {
    	if(diem >= 0 && diem <= 10){							
            String diemString = Double.toString(diem);
            int viTriDauPhay = diemString.indexOf('.');
            String phanThapPhan = diemString.substring(viTriDauPhay + 1);	
			
            if("0".equals(phanThapPhan) || "5".equals(phanThapPhan))
		return diem;
            else
		System.out.println("Điểm nhập vào không có dạng .0 hoặc .5. Điều chỉnh về 0.");
        }else												
            System.out.println("Điểm nhập vào nằm ngoài đoạn [0, 10]. Điều chỉnh về 0.");
	return 0;	
    }
    
    public String toString() {
    	return String.format("%-7s | %-40s | %-10.1f | %-10.1f | %-10s |", 
            getMonHoc().getMaMonHoc(), getMonHoc().getTenMonHoc(), 
            getGiuaKy(), getCuoiKy(), getDiemBangChu());
    }
}
