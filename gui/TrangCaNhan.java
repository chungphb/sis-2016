package gui;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.Input;
import main.Output;
import svclass.ChuyenNganh;
import svclass.Diem;
import svclass.LopHoc;
import svclass.MonHoc;
import svnc.HocTrinh;
import svnc.SinhVienNienChe;
import svtc.HocPhan;
import svtc.SinhVienTinChi;
import users.SinhVien;

public class TrangCaNhan extends javax.swing.JFrame {
    SinhVien sinhVien;
    public TrangCaNhan(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
        initComponents();
        initThongTin();
        initCacBang();
    }
    
    public void initThongTin() {
        Input.capNhatLopDangKyCuaSinhVien(sinhVien);
        Input.capNhatBangDiemSinhVien(sinhVien);
        for(int i = 0; i < Input.getDanhSachLopInput().size(); i++) {
            LopHoc<? extends MonHoc> lopHoc = Input.getDanhSachLopInput().get(i);
            Input.capNhatSinhVienDangKyLop(lopHoc);
        }
    }
    
    public void initCacBang() {
        initTextFields(sinhVien);
        taoBangMonHoc(sinhVien);
        taoBangDiem(sinhVien);
        initDiemTungKy(sinhVien);
        initKetQuaHocTap(sinhVien);
        taoBangLopHoc(sinhVien, "");
        taoBangLopDangKy(sinhVien);
    }
    
    public void taoBangMonHoc(SinhVien sinhVien) {
        if(sinhVien instanceof SinhVienTinChi) {
            String[] deMuc = {"Mã học phần", "Tên học phần", "Số tín chỉ", "Trọng số", "Học phần học trước"};
            ArrayList<HocPhan> danhMucHocPhan = ((SinhVienTinChi)sinhVien).getDanhMucHocPhan().getChuongTrinhDaoTao();
            Object[][] duLieu = new Object[danhMucHocPhan.size()][5];
            
            for(int i = 0; i < danhMucHocPhan.size(); i++) {
                HocPhan hocPhan = danhMucHocPhan.get(i);
                duLieu[i][0] = hocPhan.getMaMonHoc();
                duLieu[i][1] = hocPhan.getTenMonHoc();
                duLieu[i][2] = hocPhan.getSoTinChi();
                duLieu[i][3] = hocPhan.getTrongSo();
                duLieu[i][4] = (hocPhan.getMonHocHocTruoc()==null)? "": hocPhan.getMonHocHocTruoc().getMaMonHoc();
            }
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangMonHoc = new JTable();
            bangMonHoc.setModel(model);
            bangMonHoc.setEnabled(false);
            bangMonHoc.setVisible(true);
            scrollBangHocPhan.setViewportView(bangMonHoc);
        } else {
            String[] deMuc = {"Mã học trình", "Tên học trình", "Số đơn vị học trình", "Trọng số", "Kỳ học"};
            ArrayList<HocTrinh> danhMucHocTrinh = Input.getInstanceOfDanhMucHocTrinh(ChuyenNganh.KSTN_CNTT).getChuongTrinhDaoTao();
            System.out.println(danhMucHocTrinh.size());
            Object[][] duLieu = new Object[danhMucHocTrinh.size()][5];
            
            for(int i = 0; i < danhMucHocTrinh.size(); i++) {
                HocTrinh hocTrinh = danhMucHocTrinh.get(i);
                duLieu[i][0] = hocTrinh.getMaMonHoc();
                duLieu[i][1] = hocTrinh.getTenMonHoc();
                duLieu[i][2] = hocTrinh.getSoDonViHocTrinh();
                duLieu[i][3] = hocTrinh.getTrongSo();
                duLieu[i][4] = hocTrinh.getKyHoc();
            }
            
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangMonHoc = new JTable();
            bangMonHoc.setModel(model);
            bangMonHoc.setEnabled(false);
            bangMonHoc.setVisible(true);
            scrollBangHocPhan.setViewportView(bangMonHoc);
        }
    }
    
    public void taoBangLopHoc(SinhVien sinhVien, String tenMonHoc) {
        if(sinhVien instanceof SinhVienTinChi) {
            String[] deMuc = {"Mã lớp", "Học phần", "Max Đăng ký", "Đã đăng ký", "Thời gian", "Ngày học", "Phòng học"};
            ArrayList<LopHoc<HocPhan>> danhMucLop = new ArrayList<LopHoc<HocPhan>>();
            if("".equals(tenMonHoc)) {
                danhMucLop = Input.getDanhSachLopTinChiInput();
            } else {
                ArrayList<LopHoc<HocPhan>> tatCaCacLop = Input.getDanhSachLopTinChiInput();
                for(LopHoc<HocPhan> lopHoc: tatCaCacLop)
                    if(lopHoc.getMonHoc().getMaMonHoc().equals(tenMonHoc))
                        danhMucLop.add(lopHoc);
            }
            Object[][] duLieu = new Object[danhMucLop.size()][7];
            
            for(int i = 0; i < danhMucLop.size(); i++) {
                LopHoc<HocPhan> lopHoc = danhMucLop.get(i);
                duLieu[i][0] = lopHoc.getMaLop();
                duLieu[i][1] = lopHoc.getMonHoc().getMaMonHoc();
                duLieu[i][2] = lopHoc.getMAXSINHVIEN();
                duLieu[i][3] = lopHoc.getSoLuongDangKy();
                duLieu[i][4] = lopHoc.getGioBatDau() + " - " + lopHoc.getGioKetThuc();
                duLieu[i][5] = lopHoc.getNgayHoc().name();
                duLieu[i][6] = lopHoc.getPhongHoc();
            }
            
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangLopHoc = new JTable();
            bangLopHoc.setModel(model);
            bangLopHoc.setEnabled(false);
            bangLopHoc.setVisible(true);
            scrollDanhSachLop.setViewportView(bangLopHoc);
        } else {
            String[] deMuc = {"Mã lớp", "Học trình", "Max Đăng ký", "Đã đăng ký", "Thời gian", "Ngày học", "Phòng học"};
            ArrayList<LopHoc<HocTrinh>> danhMucLop = new ArrayList<LopHoc<HocTrinh>>();
            if("".equals(tenMonHoc)) {
                danhMucLop = Input.getDanhSachLopNienCheInput();
            } else {
                ArrayList<LopHoc<HocTrinh>> tatCaCacLop = Input.getDanhSachLopNienCheInput();
                for(LopHoc<HocTrinh> lopHoc: tatCaCacLop)
                    if(lopHoc.getMonHoc().getMaMonHoc().equals(tenMonHoc))
                        danhMucLop.add(lopHoc);
            }
            Object[][] duLieu = new Object[danhMucLop.size()][7];
            
            for(int i = 0; i < danhMucLop.size(); i++) {
                LopHoc<HocTrinh> lopHoc = danhMucLop.get(i);
                duLieu[i][0] = lopHoc.getMaLop();
                duLieu[i][1] = lopHoc.getMonHoc().getMaMonHoc();
                duLieu[i][2] = lopHoc.getMAXSINHVIEN();
                duLieu[i][3] = lopHoc.getSoLuongDangKy();
                duLieu[i][4] = lopHoc.getGioBatDau() + " - " + lopHoc.getGioKetThuc();
                duLieu[i][5] = lopHoc.getNgayHoc().name();
                duLieu[i][6] = lopHoc.getPhongHoc();
            }
            
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangLopHoc = new JTable();
            bangLopHoc.setModel(model);
            bangLopHoc.setEnabled(false);
            bangLopHoc.setVisible(true);
            scrollDanhSachLop.setViewportView(bangLopHoc);
        }
    }
     
    public void taoBangLopDangKy(SinhVien sinhVien) {
        if(sinhVien instanceof SinhVienTinChi) {
            String[] deMuc = {"Mã lớp", "Học phần", "Max Đăng ký", "Đã đăng ký", "Thời gian", "Ngày học", "Phòng học"};
            ArrayList<LopHoc<HocPhan>> danhMucDangKy = ((SinhVienTinChi) sinhVien).getDanhSachDangKy().getDanhSachLopHoc();
            Object[][] duLieu = new Object[danhMucDangKy.size()][7];
            
            for(int i = 0; i < danhMucDangKy.size(); i++) {
                LopHoc<HocPhan> lopHoc = danhMucDangKy.get(i);
                duLieu[i][0] = lopHoc.getMaLop();
                duLieu[i][1] = lopHoc.getMonHoc().getMaMonHoc();
                duLieu[i][2] = lopHoc.getMAXSINHVIEN();
                duLieu[i][3] = lopHoc.getSoLuongDangKy();
                duLieu[i][4] = lopHoc.getGioBatDau() + " - " + lopHoc.getGioKetThuc();
                duLieu[i][5] = lopHoc.getNgayHoc().name();
                duLieu[i][6] = lopHoc.getPhongHoc();
            }
            
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangDangKy = new JTable();
            bangDangKy.setModel(model);
            bangDangKy.setEnabled(false);
            bangDangKy.setVisible(true);
            scrollDanhSachDangKy.setViewportView(bangDangKy);
        } else {
            String[] deMuc = {"Mã lớp", "Học phần", "Max Đăng ký", "Đã đăng ký", "Thời gian", "Ngày học", "Phòng học"};
            ArrayList<LopHoc<HocTrinh>> danhMucDangKy = ((SinhVienNienChe) sinhVien).getDanhSachDangKy().getDanhSachLopHoc();
            Object[][] duLieu = new Object[danhMucDangKy.size()][7];
            
            for(int i = 0; i < danhMucDangKy.size(); i++) {
                LopHoc<HocTrinh> lopHoc = danhMucDangKy.get(i);
                duLieu[i][0] = lopHoc.getMaLop();
                duLieu[i][1] = lopHoc.getMonHoc().getMaMonHoc();
                duLieu[i][2] = lopHoc.getMAXSINHVIEN();
                duLieu[i][3] = lopHoc.getSoLuongDangKy();
                duLieu[i][4] = lopHoc.getGioBatDau() + " - " + lopHoc.getGioKetThuc();
                duLieu[i][5] = lopHoc.getNgayHoc().name();
                duLieu[i][6] = lopHoc.getPhongHoc();
            }
            
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangDangKy = new JTable();
            bangDangKy.setModel(model);
            bangDangKy.setEnabled(false);
            bangDangKy.setVisible(true);
            scrollDanhSachDangKy.setViewportView(bangDangKy);
        }
    }
    
    public void taoBangDiem(SinhVien sinhVien) {
        if(sinhVien instanceof SinhVienTinChi) {
            String[] deMuc = {"Mã học phần", "Tên học phần", "Giữa kỳ", "Cuối kỳ", "Tổng kết"};
            ArrayList<Diem<HocPhan>> bangDiem = ((SinhVienTinChi)sinhVien).getBangDiem().getDanhSachDiem();
            Object[][] duLieu = new Object[bangDiem.size()][5];
            
            for(int i = 0; i < bangDiem.size(); i++) {
                Diem diem = bangDiem.get(i);
                duLieu[i][0] = diem.getMonHoc().getMaMonHoc();
                duLieu[i][1] = diem.getMonHoc().getTenMonHoc();
                duLieu[i][2] = diem.getGiuaKy();
                duLieu[i][3] = diem.getCuoiKy();
                diem.tinhTongKet();
                duLieu[i][4] = diem.getDiemBangChu();
            }
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangDuLieuDiem = new JTable();
            bangDuLieuDiem.setModel(model);
            bangDuLieuDiem.setVisible(true);
            bangDuLieuDiem.setEnabled(false);
            scrollBangDiem.setViewportView(bangDuLieuDiem);
        } else {
            String[] deMuc = {"Mã học trình", "Tên học trình", "Giữa kỳ", "Cuối kỳ", "Tổng kết"};
            ArrayList<Diem<HocTrinh>> bangDiem = ((SinhVienNienChe)sinhVien).getBangDiem().getDanhSachDiem();
            Object[][] duLieu = new Object[bangDiem.size()][5];
            
            for(int i = 0; i < bangDiem.size(); i++) {
                Diem diem = bangDiem.get(i);
                duLieu[i][0] = diem.getMonHoc().getMaMonHoc();
                duLieu[i][1] = diem.getMonHoc().getTenMonHoc();
                duLieu[i][2] = diem.getGiuaKy();
                duLieu[i][3] = diem.getCuoiKy();
                duLieu[i][4] = diem.tinhTongKet();
            }
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangDuLieuDiem = new JTable();
            bangDuLieuDiem.setModel(model);
            bangDuLieuDiem.setEnabled(false);
            bangDuLieuDiem.setVisible(true);
            scrollBangDiem.setViewportView(bangDuLieuDiem);
        }
    }
    
    public void initDiemTungKy(SinhVien sinhVien) {
        if(sinhVien instanceof SinhVienTinChi) {
            String[] deMuc = {"Kỳ", "GPA"};
            double[] gpa = ((SinhVienTinChi)sinhVien).getGPA();
            Object[][] duLieu = new Object[gpa.length][2];
            for(int i = 0; i < gpa.length; i++) {
                duLieu[i][0] = i + 1;
                duLieu[i][1] = gpa[i];
            }
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangDuLieuDiem = new JTable(model);
            bangDuLieuDiem.setVisible(true);
            bangDuLieuDiem.setEnabled(false);
            scrollDiemTheoKy.setViewportView(bangDuLieuDiem);
        } else {
            String[] deMuc = {"Kỳ", "Điểm tổng kết"};
            double[] diemTrungBinhKy = ((SinhVienNienChe)sinhVien).getDiemTrungBinhKy();
            Object[][] duLieu = new Object[diemTrungBinhKy.length][2];
            for(int i = 0; i < diemTrungBinhKy.length; i++) {
                duLieu[i][0] = i + 1;
                duLieu[i][1] = diemTrungBinhKy[i];
            }
            DefaultTableModel model = new DefaultTableModel(duLieu, deMuc);
            JTable bangDuLieuDiem = new JTable(model);
            bangDuLieuDiem.setVisible(true);
            bangDuLieuDiem.setEnabled(false);
            scrollDiemTheoKy.setViewportView(bangDuLieuDiem);
        }
    }
    
    public void initKetQuaHocTap(SinhVien sinhVien) {
        if(sinhVien instanceof SinhVienTinChi) {
            jLabel1.setText("CPA");
            jLabel2.setText("Điểm TOEIC");
            jLabel3.setText("Số tín chỉ tích lũy");
            jLabel4.setText("Được tốt nghiệp?");
            jTextField1.setText("" + ((SinhVienTinChi) sinhVien).getCPA());
            jTextField1.setEditable(false);
            jTextField2.setText("" + sinhVien.getDiemTOEIC());
            jTextField2.setEditable(false);
            jTextField3.setText("" + ((SinhVienTinChi) sinhVien).getSoTinChiTichLuy());
            jTextField3.setEditable(false);
            jTextField4.setText("" + sinhVien.dieuKienTotNghiep());
            jTextField4.setEditable(false);
        }else {
            jLabel1.setText("Điểm tổng kết");
            jLabel2.setText("Điểm TOEIC");
            jLabel3.setText("Số học trình đã qua");
            jLabel4.setText("Được tốt nghiệp?");
            jTextField1.setText("" + ((SinhVienNienChe)sinhVien).getDiemTrungBinh());
            jTextField1.setEditable(false);
            jTextField2.setText("" + sinhVien.getDiemTOEIC());
            jTextField2.setEditable(false);
            jTextField3.setText("" + ((SinhVienNienChe) sinhVien).getSoHocPhanDaQua());
            jTextField3.setEditable(false);
            jTextField4.setText("" + sinhVien.dieuKienTotNghiep());
            jTextField4.setEditable(false);
        }
    }
    
    public void initTextFields(SinhVien sinhVien) {
        mssvText.setText("" + sinhVien.getMssv());
        mssvText.setEditable(false);
        mssvText.setBackground(Color.WHITE);
        tenText.setText(sinhVien.getTen());
        tenText.setEditable(false);
        tenText.setBackground(Color.WHITE);
        ngaySinhText.setText(sinhVien.getNgaySinh());
        ngaySinhText.setEditable(false);
        ngaySinhText.setBackground(Color.WHITE);
        diaChiText.setText(sinhVien.getDiaChi());
        diaChiText.setEditable(false);
        diaChiText.setBackground(Color.WHITE);
        chuyenNganhText.setText(sinhVien.getChuyenNganh().name());
        chuyenNganhText.setEditable(false);
        chuyenNganhText.setBackground(Color.WHITE);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chuongtrinhPanel = new javax.swing.JTabbedPane();
        thongTinPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        mssvLabel = new javax.swing.JLabel();
        hoTenLabel = new javax.swing.JLabel();
        ngaySinhLabel = new javax.swing.JLabel();
        diaChiLabel = new javax.swing.JLabel();
        chuyenNganhLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        mssvText = new javax.swing.JTextField();
        tenText = new javax.swing.JTextField();
        ngaySinhText = new javax.swing.JTextField();
        diaChiText = new javax.swing.JTextField();
        chuyenNganhText = new javax.swing.JTextField();
        hocphanPanel = new javax.swing.JPanel();
        scrollBangHocPhan = new javax.swing.JScrollPane();
        dangKyPanel = new javax.swing.JPanel();
        danhSachLopPanel = new javax.swing.JPanel();
        scrollDanhSachLop = new javax.swing.JScrollPane();
        chonLopPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tenLopText = new javax.swing.JTextField();
        btnDangKy = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        tenHocPhanText = new javax.swing.JTextField();
        btnTimKiemLop = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        danhSachDangKyPanel = new javax.swing.JPanel();
        scrollDanhSachDangKy = new javax.swing.JScrollPane();
        guiDangKyPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lopXoaText = new javax.swing.JTextField();
        btnHuyLop = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        btnGuiDangKy = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        ketQuaPanel = new javax.swing.JPanel();
        scrollBangDiem = new javax.swing.JScrollPane();
        panelChung = new javax.swing.JPanel();
        panelThongTinDiem = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        scrollDiemTheoKy = new javax.swing.JScrollPane();
        dangXuatPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        thongTinPanel.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        mssvLabel.setText("MSSV");

        hoTenLabel.setText("Họ và tên");

        ngaySinhLabel.setText("Ngày sinh");

        diaChiLabel.setText("Địa chỉ");

        chuyenNganhLabel.setText("Chuyên ngành");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("THÔNG TIN SINH VIÊN");

        mssvText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mssvTextActionPerformed(evt);
            }
        });

        diaChiText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaChiTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chuyenNganhLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chuyenNganhText, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ngaySinhLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(diaChiLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ngaySinhText, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(diaChiText, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(hoTenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(tenText, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(mssvLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(mssvText, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mssvLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mssvText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hoTenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngaySinhLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngaySinhText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaChiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diaChiText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chuyenNganhLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chuyenNganhText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        thongTinPanel.add(jPanel1, new java.awt.GridBagConstraints());

        chuongtrinhPanel.addTab("Thông tin cá nhân", thongTinPanel);

        javax.swing.GroupLayout hocphanPanelLayout = new javax.swing.GroupLayout(hocphanPanel);
        hocphanPanel.setLayout(hocphanPanelLayout);
        hocphanPanelLayout.setHorizontalGroup(
            hocphanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hocphanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollBangHocPhan, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                .addContainerGap())
        );
        hocphanPanelLayout.setVerticalGroup(
            hocphanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hocphanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollBangHocPhan, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );

        chuongtrinhPanel.addTab("Chương trình đào tạo", hocphanPanel);

        dangKyPanel.setLayout(new java.awt.GridLayout(1, 2));

        danhSachLopPanel.setBackground(new java.awt.Color(102, 255, 0));
        danhSachLopPanel.setLayout(new java.awt.BorderLayout());
        danhSachLopPanel.add(scrollDanhSachLop, java.awt.BorderLayout.CENTER);

        chonLopPanel.setLayout(new java.awt.GridLayout(3, 6));
        chonLopPanel.add(jLabel12);
        chonLopPanel.add(jLabel13);
        chonLopPanel.add(jLabel14);
        chonLopPanel.add(jLabel15);
        chonLopPanel.add(jLabel16);
        chonLopPanel.add(jLabel17);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Lớp");
        chonLopPanel.add(jLabel18);

        tenLopText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenLopTextActionPerformed(evt);
            }
        });
        chonLopPanel.add(tenLopText);

        btnDangKy.setText("Đăng ký");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });
        chonLopPanel.add(btnDangKy);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Học phần");
        chonLopPanel.add(jLabel19);

        tenHocPhanText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenHocPhanTextActionPerformed(evt);
            }
        });
        chonLopPanel.add(tenHocPhanText);

        btnTimKiemLop.setText("Tìm kiếm");
        btnTimKiemLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemLopActionPerformed(evt);
            }
        });
        chonLopPanel.add(btnTimKiemLop);
        chonLopPanel.add(jLabel21);
        chonLopPanel.add(jLabel22);
        chonLopPanel.add(jLabel23);
        chonLopPanel.add(jLabel24);
        chonLopPanel.add(jLabel25);
        chonLopPanel.add(jLabel26);

        danhSachLopPanel.add(chonLopPanel, java.awt.BorderLayout.PAGE_START);

        dangKyPanel.add(danhSachLopPanel);

        danhSachDangKyPanel.setBackground(new java.awt.Color(102, 255, 0));
        danhSachDangKyPanel.setLayout(new java.awt.BorderLayout());
        danhSachDangKyPanel.add(scrollDanhSachDangKy, java.awt.BorderLayout.CENTER);

        guiDangKyPanel.setLayout(new java.awt.GridLayout(3, 5));
        guiDangKyPanel.add(jLabel20);
        guiDangKyPanel.add(jLabel27);
        guiDangKyPanel.add(jLabel28);
        guiDangKyPanel.add(jLabel29);
        guiDangKyPanel.add(jLabel30);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Lớp");
        guiDangKyPanel.add(jLabel39);
        guiDangKyPanel.add(lopXoaText);

        btnHuyLop.setText("Hủy lớp");
        btnHuyLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyLopActionPerformed(evt);
            }
        });
        guiDangKyPanel.add(btnHuyLop);
        guiDangKyPanel.add(jLabel31);

        btnGuiDangKy.setText("Gửi đăng ký");
        btnGuiDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiDangKyActionPerformed(evt);
            }
        });
        guiDangKyPanel.add(btnGuiDangKy);
        guiDangKyPanel.add(jLabel32);
        guiDangKyPanel.add(jLabel33);
        guiDangKyPanel.add(jLabel34);

        danhSachDangKyPanel.add(guiDangKyPanel, java.awt.BorderLayout.PAGE_START);

        dangKyPanel.add(danhSachDangKyPanel);

        chuongtrinhPanel.addTab("Đăng ký lớp học", dangKyPanel);

        ketQuaPanel.setLayout(new java.awt.GridLayout(1, 2));

        scrollBangDiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BẢNG ĐIỂM", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        ketQuaPanel.add(scrollBangDiem);

        panelChung.setLayout(new java.awt.GridLayout(4, 1));

        panelThongTinDiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "KẾT QUẢ HỌC TẬP", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        panelThongTinDiem.setLayout(new java.awt.GridLayout(1, 1));

        jPanel2.setLayout(new java.awt.GridLayout(4, 2));

        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1);

        jTextField1.setText("jTextField1");
        jPanel2.add(jTextField1);

        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2);

        jTextField2.setText("jTextField2");
        jPanel2.add(jTextField2);

        jLabel3.setText("jLabel3");
        jPanel2.add(jLabel3);

        jTextField3.setText("jTextField3");
        jPanel2.add(jTextField3);

        jLabel4.setText("jLabel4");
        jPanel2.add(jLabel4);

        jTextField4.setText("jTextField4");
        jPanel2.add(jTextField4);

        panelThongTinDiem.add(jPanel2);

        panelChung.add(panelThongTinDiem);

        scrollDiemTheoKy.setBorder(null);
        panelChung.add(scrollDiemTheoKy);

        ketQuaPanel.add(panelChung);

        chuongtrinhPanel.addTab("Kết quả học tập", ketQuaPanel);

        javax.swing.GroupLayout dangXuatPanelLayout = new javax.swing.GroupLayout(dangXuatPanel);
        dangXuatPanel.setLayout(dangXuatPanelLayout);
        dangXuatPanelLayout.setHorizontalGroup(
            dangXuatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        dangXuatPanelLayout.setVerticalGroup(
            dangXuatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );

        chuongtrinhPanel.addTab("Đăng xuất", dangXuatPanel);

        fileMenu.setText("File");
        jMenuBar1.add(fileMenu);

        helpMenu.setText("Help");
        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chuongtrinhPanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chuongtrinhPanel)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void diaChiTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaChiTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diaChiTextActionPerformed

    private void mssvTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mssvTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mssvTextActionPerformed

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        // TODO add your handling code here:
        if("".equals(tenLopText.getText())) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập lớp nào cả");
        }else {
            if(sinhVien instanceof SinhVienTinChi) {
                ArrayList<LopHoc<HocPhan>> danhSachLop = Input.getDanhSachLopTinChiInput();
                for(LopHoc<HocPhan> lopHoc: danhSachLop) {
                    if(lopHoc.getMaLop() == Integer.parseInt(tenLopText.getText())) {
                        ((SinhVienTinChi)sinhVien).dangKy(lopHoc);
                        taoBangLopDangKy(sinhVien);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Không có lớp " + tenLopText.getText());
            }else {
                ArrayList<LopHoc<HocTrinh>> danhSachLop = Input.getDanhSachLopNienCheInput();
                for(LopHoc<HocTrinh> lopHoc: danhSachLop) {
                    if(lopHoc.getMaLop() == Integer.parseInt(tenLopText.getText())) {
                        ((SinhVienNienChe)sinhVien).dangKy(lopHoc);
                        taoBangLopDangKy(sinhVien);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Không có lớp " + tenLopText.getText());
            }
        }        
    }//GEN-LAST:event_btnDangKyActionPerformed

    private void tenLopTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenLopTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenLopTextActionPerformed

    private void btnTimKiemLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemLopActionPerformed
        // TODO add your handling code here:
        if("".equals(tenHocPhanText.getText())) {
            taoBangLopHoc(sinhVien, "");
            return;
        }
        
        if(sinhVien instanceof SinhVienTinChi) {
            try{
                ArrayList<HocPhan> danhSachHocPhan = Input.getDanhSachHocPhanInput();
                for(int i = 0; i < danhSachHocPhan.size(); i++) {
                    HocPhan hocPhan = danhSachHocPhan.get(i);
                    if(hocPhan.getMaMonHoc().equals(tenHocPhanText.getText())) {
                        taoBangLopHoc(sinhVien, tenHocPhanText.getText());
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Không có học phần này.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            ArrayList<HocTrinh> danhSachHocTrinh = Input.getDanhSachHocTrinhInput();
            for(int i = 0; i < danhSachHocTrinh.size(); i++) {
                    HocTrinh hocTrinh = danhSachHocTrinh.get(i);
                    if(hocTrinh.getMaMonHoc().equals(tenHocPhanText.getText())) {
                        taoBangLopHoc(sinhVien, tenHocPhanText.getText());
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Không có học phần này.", "Access Denied", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTimKiemLopActionPerformed

    private void tenHocPhanTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenHocPhanTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenHocPhanTextActionPerformed

    private void btnHuyLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyLopActionPerformed
        // TODO add your handling code here:
        if("".equals(lopXoaText.getText())) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập lớp nào cả");
        }else {
            if(sinhVien instanceof SinhVienTinChi) {
                ArrayList<LopHoc<HocPhan>> danhSachLop = Input.getDanhSachLopTinChiInput();
                for(LopHoc<HocPhan> lopHoc: danhSachLop) {
                    if(lopHoc.getMaLop() == Integer.parseInt(lopXoaText.getText())) {
                        ((SinhVienTinChi)sinhVien).huyDangKy(lopHoc);
                        taoBangLopDangKy(sinhVien);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Không có lớp " + lopXoaText.getText());
            }else {
                ArrayList<LopHoc<HocTrinh>> danhSachLop = Input.getDanhSachLopNienCheInput();
                for(LopHoc<HocTrinh> lopHoc: danhSachLop) {
                    if(lopHoc.getMaLop() == Integer.parseInt(lopXoaText.getText())) {
                        ((SinhVienNienChe)sinhVien).huyDangKy(lopHoc);
                        taoBangLopDangKy(sinhVien);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Không có lớp " + lopXoaText.getText());
            }
        }        
    }//GEN-LAST:event_btnHuyLopActionPerformed

    private void btnGuiDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiDangKyActionPerformed
        // TODO add your handling code here:
        Output.outputDangKy(sinhVien);
        if(sinhVien instanceof SinhVienTinChi) {
            for(int i = 0; i < ((SinhVienTinChi)sinhVien).getDanhSachDangKy().getDanhSachLopHoc().size(); i++) {
                LopHoc<HocPhan> lopHoc = ((SinhVienTinChi)sinhVien).getDanhSachDangKy().getDanhSachLopHoc().get(i);
                Output.outputDangKy(lopHoc);
            }
        } else {
            for(int i = 0; i < ((SinhVienNienChe)sinhVien).getDanhSachDangKy().getDanhSachLopHoc().size(); i++) {
                LopHoc<HocTrinh> lopHoc = ((SinhVienNienChe)sinhVien).getDanhSachDangKy().getDanhSachLopHoc().get(i);
                Output.outputDangKy(lopHoc);
            }
        }
        JOptionPane.showMessageDialog(null, "Gửi đăng ký thành công");
        
    }//GEN-LAST:event_btnGuiDangKyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btnGuiDangKy;
    private javax.swing.JButton btnHuyLop;
    private javax.swing.JButton btnTimKiemLop;
    private javax.swing.JPanel chonLopPanel;
    private javax.swing.JTabbedPane chuongtrinhPanel;
    private javax.swing.JLabel chuyenNganhLabel;
    private javax.swing.JTextField chuyenNganhText;
    private javax.swing.JPanel dangKyPanel;
    private javax.swing.JPanel dangXuatPanel;
    private javax.swing.JPanel danhSachDangKyPanel;
    private javax.swing.JPanel danhSachLopPanel;
    private javax.swing.JLabel diaChiLabel;
    private javax.swing.JTextField diaChiText;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel guiDangKyPanel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel hoTenLabel;
    private javax.swing.JPanel hocphanPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JPanel ketQuaPanel;
    private javax.swing.JTextField lopXoaText;
    private javax.swing.JLabel mssvLabel;
    private javax.swing.JTextField mssvText;
    private javax.swing.JLabel ngaySinhLabel;
    private javax.swing.JTextField ngaySinhText;
    private javax.swing.JPanel panelChung;
    private javax.swing.JPanel panelThongTinDiem;
    private javax.swing.JScrollPane scrollBangDiem;
    private javax.swing.JScrollPane scrollBangHocPhan;
    private javax.swing.JScrollPane scrollDanhSachDangKy;
    private javax.swing.JScrollPane scrollDanhSachLop;
    private javax.swing.JScrollPane scrollDiemTheoKy;
    private javax.swing.JTextField tenHocPhanText;
    private javax.swing.JTextField tenLopText;
    private javax.swing.JTextField tenText;
    private javax.swing.JPanel thongTinPanel;
    // End of variables declaration//GEN-END:variables
}
