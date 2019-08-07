package svnc;

import svclass.ChuyenNganh;
import svclass.MonHoc;

public class HocTrinh extends MonHoc {
    private int soDonViHocTrinh;
    private int kyHoc;
    private ChuyenNganh chuyenNganh;
	
    public HocTrinh(String maMonHoc, String tenMonHoc, int soDonViHocTrinh, double trongSo, MonHoc MonHocHocTruoc, ChuyenNganh chuyenNganh, int kyHoc) {
    	super(maMonHoc, tenMonHoc, trongSo, MonHocHocTruoc);
	this.soDonViHocTrinh = soDonViHocTrinh;
    	this.kyHoc = kyHoc;
	this.chuyenNganh = chuyenNganh;
    }

    public int getSoDonViHocTrinh() {
	return soDonViHocTrinh;
    }

    public int getKyHoc() {
	return kyHoc;
    }

    public ChuyenNganh getChuyenNganh() {
	return chuyenNganh;
    }
    
    @Override
    public String toString() {
        return String.format("%-10s | %-40s | %-2d | %-2.1f | %-10s | %-10s | %2d", 
            getMaMonHoc(), getTenMonHoc(), getSoDonViHocTrinh(), getTrongSo(),
            (getMonHocHocTruoc() != null)? getMonHocHocTruoc().getMaMonHoc(): "null", 
            getChuyenNganh().name(), getKyHoc());
    }
}
