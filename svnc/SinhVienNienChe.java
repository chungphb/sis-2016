package svnc;

import javax.swing.JOptionPane;
import main.Input;
import svclass.BangDiem;
import svclass.ChuyenNganh;
import svclass.DanhMucMonHoc;
import svclass.DanhSachDangKy;
import svclass.LopHoc;
import svclass.TrangThaiHocTap;
import users.SinhVien;

public class SinhVienNienChe extends SinhVien{
    private double[] diemTrungBinhKy = new double[5];
    private double diemTrungBinh;
    private BangDiem<HocTrinh> bangDiem;
    private int soHocPhanDaQua;
    private LopSinhVien lop;
    private DanhSachDangKy<HocTrinh> danhSachDangKy;
    private DanhMucMonHoc<HocTrinh> danhMucHocTrinh;
   
    public SinhVienNienChe(int mssv, String ten, String ngaySinh, boolean gioiTinh, String diaChi, String khoaVien, ChuyenNganh chuyenNganh, String tenLop) {
        super(mssv, ten, ngaySinh, gioiTinh, diaChi, khoaVien, chuyenNganh, tenLop);
	lop = new LopSinhVien(tenLop);
	danhMucHocTrinh = Input.getInstanceOfDanhMucHocTrinh(chuyenNganh);
    }

    public double[] getDiemTrungBinhKy() {
        return diemTrungBinhKy;
    }

    public void setDiemTrungBinhKy(double[] diemTrungBinhKy) {
	this.diemTrungBinhKy = diemTrungBinhKy;
    }
	
    public double getDiemTrungBinh() {
	return diemTrungBinh;
    }
	
    public void setDiemTrungBinh(double diemTrungBinh) {
	this.diemTrungBinh = diemTrungBinh;
    }
	
    public BangDiem<HocTrinh> getBangDiem() {
	return bangDiem;
    }

    public void setBangDiem(BangDiem<HocTrinh> bangDiem) {
	this.bangDiem = bangDiem;
    }

    public int getSoHocPhanDaQua() {
	return soHocPhanDaQua;
    }

    public void setSoHocPhanDaQua(int soHocPhanDaQua) {
	this.soHocPhanDaQua = soHocPhanDaQua;
    }
	
    public LopSinhVien getLop() {
	return lop;
    }

    public void setLop(LopSinhVien lop) {
	this.lop = lop;
    }

    public DanhSachDangKy<HocTrinh> getDanhSachDangKy() {
	return danhSachDangKy;
    }
	
    public void setDanhSachDangKy(DanhSachDangKy<HocTrinh> danhSachDangKy) {
	this.danhSachDangKy = danhSachDangKy;
    }
	
    public DanhMucMonHoc<HocTrinh> getDanhMucHocTrinh() {
	return danhMucHocTrinh;
    }

    @Override
    public boolean dieuKienTotNghiep() {
        int soHocPhan = this.getDanhMucHocTrinh().getChuongTrinhDaoTao().size();
	if(getTrangThaiHoc() != TrangThaiHocTap.DANGHOC) {
            setLyDoChuaTotNghiep("Sinh viên đang bảo lưu hoặc đã thôi học");
            return false;
	}else if(getSoHocPhanDaQua() < soHocPhan) {
            setLyDoChuaTotNghiep("Chưa đủ số học phần của chương trình");
            return false;
	}else if(getDiemTrungBinh() < 5.0) {
            setLyDoChuaTotNghiep("Điểm trung bình < 5.0");
            return false;
	}else if(getDiemTOEIC() < 600) {
            setLyDoChuaTotNghiep("Điểm TOEIC < 600");
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
            String maHocTrinh = theChatQuocPhong[i];
            if(!getBangDiem().daQuaMonHoc(maHocTrinh)) return false;
        }
	return true;
    }

    public void dangKy(LopHoc<HocTrinh> lopHoc) {
	HocTrinh hocTrinh = (HocTrinh)(lopHoc.getMonHoc());		
	if(this.getTrangThaiHoc() != TrangThaiHocTap.DANGHOC)
            JOptionPane.showMessageDialog(null, "Không được phép đăng ký.");
	else if(this.getDanhSachDangKy().daFullChua(lopHoc))
            JOptionPane.showMessageDialog(null, "Không đủ số đơn vị học trình.");
	else if(!this.getLop().coThuocChuongTrinh(hocTrinh) || 
            (this.getBangDiem().findDiem(lopHoc.getMonHoc()) == null 
                && ((HocTrinh)lopHoc.getMonHoc()).getKyHoc() > this.getKyHoc() + 1))
            JOptionPane.showMessageDialog(null, "Học trình không được phép đăng ký.");
	else if(lopHoc.getSoLuongDangKy() == lopHoc.getMAXSINHVIEN())
            JOptionPane.showMessageDialog(null, "Lớp học đã đủ chỗ.");
	else if(danhSachDangKy.daDangKy(hocTrinh))
            JOptionPane.showMessageDialog(null, "Đã đăng ký một lớp học khác của học trình này.");
	else if(getDanhSachDangKy().trungLich(lopHoc))
            JOptionPane.showMessageDialog(null,"Trùng thời gian học.");
        else{
            this.getDanhSachDangKy().addLopHoc(lopHoc);
            lopHoc.getDanhSachSinhVien().add(this);
	}
    }
	
    public void huyDangKy(LopHoc<HocTrinh> lopHoc){
	DanhSachDangKy<HocTrinh> danhSachDangKy = getDanhSachDangKy();
	if(danhSachDangKy.getDanhSachLopHoc().contains(lopHoc)) {
            this.getDanhSachDangKy().removeLopHoc(lopHoc);
            lopHoc.removeSinhVien(this);
	}
    }
}
