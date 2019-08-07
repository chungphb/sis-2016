package svclass;

public class MonHoc {
    private String maMonHoc;
    private String tenMonHoc;
    private double trongSo;
    private MonHoc MonHocHocTruoc;
    
    public MonHoc(String maMonHoc, String tenMonHoc, double trongSo, MonHoc MonHocHocTruoc) {
	this.maMonHoc = maMonHoc;
	this.tenMonHoc = tenMonHoc;
	this.trongSo = dieuChinhTrongSo(trongSo);
	this.MonHocHocTruoc = MonHocHocTruoc;
    }

    public String getMaMonHoc() {
    	return maMonHoc;
    }
    
    public void setMaMonHoc(String maMonHoc) {
    	this.maMonHoc = maMonHoc;
    }
    
    public String getTenMonHoc() {
    	return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
    	this.tenMonHoc = tenMonHoc;
    }
    
    public double getTrongSo() {
	return trongSo;
    }
    
    public void setTrongSo(double trongSo) {
	this.trongSo = dieuChinhTrongSo(trongSo);
    }
    
    public MonHoc getMonHocHocTruoc() {
	return MonHocHocTruoc;
    }

    public void setMonHocHocTruoc(MonHoc MonHocHocTruoc) {
	this.MonHocHocTruoc = MonHocHocTruoc;
    }
	
    public double dieuChinhTrongSo(double trongSo) {
    	if(trongSo >= 0.0 && trongSo <= 1.0)
    		return trongSo;
    	else{
    		System.out.println("Nhap sai trong so. Dieu chinh ve 0.7.");
    		return 0.7;
    	}
    }
	
    public String toString() {
	return String.format("%-7s | %-40s | %-5.1f | %-10s", 
            getMaMonHoc(), getTenMonHoc(), getTrongSo(), (getMonHocHocTruoc()==null)? "": getMonHocHocTruoc().getMaMonHoc());
    }
}
