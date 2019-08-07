package svtc;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import users.SinhVien;
import main.Input;
import svclass.BangDiem;
import svclass.ChuyenNganh;
import svclass.DanhMucMonHoc;
import svclass.DanhSachDangKy;
import svclass.LopHoc;
import svclass.TrangThaiHocTap;

public class SinhVienTinChi extends SinhVien {
    private double[] GPA = new double[10];
    private double CPA;
    private BangDiem<HocPhan> bangDiem;
    private int soTinChiTichLuy;
    private DanhMucMonHoc<HocPhan> danhMucHocPhan;
    private DanhSachDangKy<HocPhan> danhSachDangKy;
        
    public SinhVienTinChi(int mssv, String ten, String ngaySinh, boolean gioiTinh, String diaChi, String khoaVien, ChuyenNganh chuyenNganh, String lopSinhVien) {
	super(mssv, ten, ngaySinh, gioiTinh, diaChi, khoaVien, chuyenNganh, lopSinhVien);
	bangDiem = new BangDiem<HocPhan>();
	danhSachDangKy = new DanhSachDangKy<HocPhan>();
	danhMucHocPhan = Input.getInstanceOfDanhMucHocPhan(chuyenNganh);       
    }

    public double[] getGPA() {
	return GPA;
    }

    public void setGPA(double[] gPA) {
	GPA = gPA;
    }

    public double getCPA() {
	return CPA;
    }

    public void setCPA(double cPA) {
	CPA = cPA;
    }

    public BangDiem<HocPhan> getBangDiem() {
	return bangDiem;
    }

    public void setBangDiem(BangDiem<HocPhan> bangDiem) {
	this.bangDiem = bangDiem;
    }

    public int getSoTinChiTichLuy() {
	return soTinChiTichLuy;
    }

    public void setSoTinChiTichLuy(int soTinChiTichLuy) {
    	this.soTinChiTichLuy = soTinChiTichLuy;
    }

    public DanhSachDangKy<HocPhan> getDanhSachDangKy() {
	return danhSachDangKy;
    }

    public DanhMucMonHoc<HocPhan> getDanhMucHocPhan() {
	return danhMucHocPhan;
    }
    
    @Override
    public boolean dieuKienTotNghiep() {
    	int soTinChiTotNghiep = this.getDanhMucHocPhan().getSoDonViHocTap();
    	if(getTrangThaiHoc() != TrangThaiHocTap.DANGHOC) {
            setLyDoChuaTotNghiep("Sinh viên đang bảo lưu hoặc đã thôi học");
            return false;
	}else if(soTinChiTichLuy < soTinChiTotNghiep) {
            setLyDoChuaTotNghiep("Chưa đủ số tín chỉ tích lũy");
            return false;
	}else if(getCPA() < 2.0) {
            setLyDoChuaTotNghiep("Điểm CPA < 2.00");
            return false;
	}else if(getDiemTOEIC() < 450) {
            setLyDoChuaTotNghiep("Điểm TOEIC < 450");
            return false;
	}else if(!daQuaTheChatVaQuocPhong()) {
            setLyDoChuaTotNghiep("Chưa đủ chứng chỉ Thể dục và Quốc phòng");
            return false;
	}else {
            setLyDoChuaTotNghiep("");
            return true;
	}
    }
	
    public boolean daQuaTheChatVaQuocPhong(){
	String[] theChatQuocPhong = {"MIL1110", "MIL1120", "MIL1130", 
                "PE1010", "PE1020", "PE1030", "PE2010", "PE2020"};
	for(int i = 0; i < theChatQuocPhong.length; i++){
            String maMonHoc = theChatQuocPhong[i];
            if(!getBangDiem().daQuaMonHoc(maMonHoc)) return false;
	}
	return true;
    }
	
    public void dangKy(LopHoc<HocPhan> lopHoc){
    	HocPhan hocPhan = (HocPhan)(lopHoc.getMonHoc());
    	HocPhan hocPhanHocTruoc = (HocPhan)(hocPhan.getMonHocHocTruoc());
    	int soLuongDangKy = lopHoc.getSoLuongDangKy();
		
    	if(this.getTrangThaiHoc() != TrangThaiHocTap.DANGHOC)
            JOptionPane.showMessageDialog(null, "Không được phép đăng ký.");
	else if(this.getDanhSachDangKy().daFullChua(lopHoc))
            JOptionPane.showMessageDialog(null, "Không đủ số tín chỉ để đăng ký thêm.");
	else if(!this.getDanhMucHocPhan().coThuocChuongTrinh(hocPhan))
            JOptionPane.showMessageDialog(null, "Học phần không thuộc chương trình đào tạo.");
	else if(hocPhanHocTruoc!= null && this.getBangDiem().findDiem(hocPhanHocTruoc) == null)
            JOptionPane.showMessageDialog(null, "Chưa học học phần học trước ứng với học phần của lớp học này.");
	else if(soLuongDangKy == lopHoc.getMAXSINHVIEN())
            JOptionPane.showMessageDialog(null, "Lớp học đã đủ chỗ.");
	else if(danhSachDangKy.daDangKy(hocPhan))
            JOptionPane.showMessageDialog(null, "Đã đăng ký một lớp học khác của học phần này.");
        else if(getDanhSachDangKy().trungLich(lopHoc))
            JOptionPane.showMessageDialog(null, "Trùng thời gian học.");
        else{
            this.getDanhSachDangKy().addLopHoc(lopHoc);
            lopHoc.getDanhSachSinhVien().add(this);
	}
    }
	
    public void huyDangKy(LopHoc<HocPhan> lopHoc){
	DanhSachDangKy<HocPhan> danhSachDangKy = getDanhSachDangKy();
	if(danhSachDangKy.getDanhSachLopHoc().contains(lopHoc)) {
            this.getDanhSachDangKy().removeLopHoc(lopHoc);
            lopHoc.removeSinhVien(this);
	}else {
            JOptionPane.showMessageDialog(null, "Bạn chưa đăng ký lớp này.");
	}
    }
}
