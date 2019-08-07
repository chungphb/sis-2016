package svtc;

import svclass.MonHoc;

public class HocPhan extends MonHoc{
    int soTinChi;
    String chuongTrinh;
	
    public HocPhan(String maMonHoc, String tenMonHoc, int soTinChi, double trongSo, MonHoc MonHocHocTruoc, String chuongTrinh) {
	super(maMonHoc, tenMonHoc, trongSo, MonHocHocTruoc);
	this.soTinChi = soTinChi;
	this.chuongTrinh = chuongTrinh;
    }

    public int getSoTinChi() {
	return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
	this.soTinChi = soTinChi;
    }

    public String getChuongTrinh() {
    	return chuongTrinh;
    }
    
    @Override
    public String toString() {
        return String.format("%-10s | %-40s | %-2d | %-2.1f | %-10s | %-10s", 
            getMaMonHoc(), getTenMonHoc(), getSoTinChi(), getTrongSo(),
            (getMonHocHocTruoc() != null)? getMonHocHocTruoc().getMaMonHoc(): "null", 
            getChuongTrinh());
    }
}
