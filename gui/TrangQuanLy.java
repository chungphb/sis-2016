package gui;

import java.awt.Color;
import static java.awt.PageAttributes.MediaType.C;
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
import users.QuanLy;
import users.SinhVien;

public class TrangQuanLy extends javax.swing.JFrame {
    private final QuanLy quanLy;
    private JTable bangDiemLop;
    private boolean nhapMoi;
    
    public TrangQuanLy(QuanLy quanLy) {
        this.quanLy = quanLy;
        initComponents();
        initComboBox();
        taoBangThongTinSinhVien();
        taoBangThongTinPanel(quanLy);
        taoBangSinhVien(quanLy, "");
        taoBangDiemSinhVien(quanLy, "");
        taoBangXetTotNghiep(quanLy, ""); 
    }
    
    public void taoBangThongTinPanel(QuanLy quanLy) {
        textMaQuanLy.setText("" + quanLy.getMaSoQuanLy());
        textMaQuanLy.setEditable(false);
        textMaQuanLy.setBackground(Color.WHITE);
        textTenQuanLy.setText(quanLy.getTen());
        textTenQuanLy.setEditable(false);
        textTenQuanLy.setBackground(Color.WHITE);
        textNgaySinh.setText(quanLy.getNgaySinh());
        textNgaySinh.setEditable(false);
        textNgaySinh.setBackground(Color.WHITE);
        textDiaChi.setText(quanLy.getDiaChi());
        textDiaChi.setEditable(false);
        textDiaChi.setBackground(Color.WHITE);
        textKhoa.setText("" + quanLy.getKhoaQuanLy());
        textKhoa.setEditable(false);
        textKhoa.setBackground(Color.WHITE);
    }
    
    public void taoBangSinhVien(QuanLy quanLy, String tenLop) {
        String[] deMuc = {"MSSV", "Tên sinh viên", "Ngày sinh", "Giới tính", "Địa chỉ", "Khoa viện"};
        
        ArrayList<SinhVien> danhSachSinhVien = new ArrayList<SinhVien>();
        if("".equals(tenLop)) { 
            danhSachSinhVien = quanLy.getDanhSachSinhVien();
        }else {
            for(int i = 0; i < quanLy.getDanhSachSinhVien().size(); i++) {
                SinhVien sinhVien = quanLy.getDanhSachSinhVien().get(i);
                if(sinhVien.getLopSinhVien().equals(tenLop)) {
                    danhSachSinhVien.add(sinhVien);
                }
            }
        }
        
        Object[][] duLieu = new Object[danhSachSinhVien.size()][6];
        
        for(int i = 0; i < danhSachSinhVien.size(); i++) {
            SinhVien sinhVien = danhSachSinhVien.get(i);
            duLieu[i][0] = sinhVien.getMssv();
            duLieu[i][1] = sinhVien.getTen();
            duLieu[i][2] = sinhVien.getNgaySinh();
            duLieu[i][3] = sinhVien.isGioiTinh()? "Nam": "Nữ";
            duLieu[i][4] = sinhVien.getDiaChi();
            duLieu[i][5] = sinhVien.getKhoaVien();
        }
        
        JTable bang = new JTable();
        bang.setModel(new DefaultTableModel(duLieu, deMuc));
        bang.setEnabled(false);
        bang.setVisible(true);
        scrollBangSinhVien.setViewportView(bang);
    }
    
    public void taoBangDiemSinhVien(QuanLy quanLy, String tenLop) {
        String[] deMuc = {"MSSV", "Tên sinh viên", "Học phần", "Điểm giữa kỳ", "Điểm cuối kỳ"};
       
        if("".equals(tenLop)){
            bangDiemLop = new JTable();
            return;
        }
        LopHoc <? extends MonHoc> lopHoc = Input.getInstanceOfLopHoc(Integer.parseInt(tenLop));
        if(lopHoc==null) return;
        ArrayList<SinhVien> danhSachSinhVien = lopHoc.getDanhSachSinhVien();
        
        Object[][] duLieu = new Object[danhSachSinhVien.size()][5];
        for(int i = 0; i < danhSachSinhVien.size(); i++) {
            SinhVien sinhVien = danhSachSinhVien.get(i);
            duLieu[i][0] = sinhVien.getMssv();
            duLieu[i][1] = sinhVien.getTen();
            duLieu[i][2] = (lopHoc==null)? "": lopHoc.getMonHoc().getMaMonHoc();
            if(sinhVien instanceof SinhVienTinChi) {
                Diem<HocPhan> diem = ((SinhVienTinChi) sinhVien).getBangDiem().findDiem((HocPhan) lopHoc.getMonHoc());
                nhapMoi = (diem == null)? true: false;
                duLieu[i][3] = (diem == null)? "": diem.getGiuaKy();
                duLieu[i][4] = (diem == null)? "": diem.getCuoiKy();
            } else {
                Diem<HocTrinh> diem = ((SinhVienNienChe) sinhVien).getBangDiem().findDiem((HocTrinh) lopHoc.getMonHoc());
                nhapMoi = (diem == null)? true: false;
                duLieu[i][3] = (diem == null)? "": diem.getGiuaKy();
                duLieu[i][4] = (diem == null)? "": diem.getCuoiKy();
            }
        }
        
        bangDiemLop.setModel(new DefaultTableModel(duLieu, deMuc) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if(col == 3|| col == 4)
                    return true;
                return false;
            }
        });
        
        bangDiemLop.setVisible(true);
        scrollDanhSachSinhVienMotLop.setViewportView(bangDiemLop);
        
    }
    
    public void initComboBox() {
        String[] chuyenNganh = {ChuyenNganh.CHUA_PHAN.name(), ChuyenNganh.KHMT.name(), ChuyenNganh.HTTT.name(), ChuyenNganh.CNPM.name(), ChuyenNganh.KTMT.name(), 
                    ChuyenNganh.TTM.name(), ChuyenNganh.ATTT.name(), ChuyenNganh.CNTT.name(), ChuyenNganh.TA_KHKTVCN.name(), ChuyenNganh.TACNQT.name(),
                    ChuyenNganh.CNTP.name(), ChuyenNganh.KTCDT.name(), ChuyenNganh.KSTN_CNTT.name(), ChuyenNganh.KSCLC_HTTT.name(), ChuyenNganh.KSTN_CDT.name(), ChuyenNganh.CTTT_CDT.name()};
        for(int i = 0; i < chuyenNganh.length; i++)
            comboDanhSachChuyenNganh.addItem(chuyenNganh[i]);
        comboGioiTinh.addItem("Nam");
        comboGioiTinh.addItem("Nữ");
    }
    
    public void taoBangThongTinSinhVien() {
        textMSSV.setText("");
        textHoTenSV.setText("");
        textNgaySinhSV.setText("");
        textDiaChiSV.setText("");
        textKhoaVienSV.setText("");
        textLopHocSV.setText("");
    }
    
    public void taoBangXetTotNghiep(QuanLy quanLy, String mssv) {
        String[] deMuc = {"MSSV", "Tên sinh viên", "Được tốt nghiệp", "Ghi chú"};
        ArrayList<SinhVien> danhSachSinhVien = new ArrayList<SinhVien>();
        if("".equals(mssv)) {
            danhSachSinhVien = quanLy.getDanhSachSinhVien();
        }else {
            for(int i = 0; i < quanLy.getDanhSachSinhVien().size(); i++) {
                SinhVien sinhVien = quanLy.getDanhSachSinhVien().get(i);
                if(mssv.equals("" + sinhVien.getMssv())) {
                    danhSachSinhVien.add(sinhVien);
                    break;
                }
            }
        }
        Object[][] duLieu = new Object[danhSachSinhVien.size()][4];
        for(int i = 0; i < danhSachSinhVien.size(); i++) {
            SinhVien sinhVien = danhSachSinhVien.get(i);
            duLieu[i][0] = sinhVien.getMssv();
            duLieu[i][1] = sinhVien.getTen();
            duLieu[i][2] = sinhVien.dieuKienTotNghiep()? "Đủ điều kiện tốt nghiệp": "Chưa đủ điều kiện tốt nghiệp";
            duLieu[i][3] = sinhVien.getLyDoChuaTotNghiep();
        }
        JTable bang = new JTable();
        bang.setModel(new DefaultTableModel(duLieu, deMuc));
        bang.setEnabled(false);
        bang.setVisible(true);
        scrollXetTotNghiep.setViewportView(bang);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chuongTrinhPanel = new javax.swing.JTabbedPane();
        thongTinPanel = new javax.swing.JPanel();
        panelThongTinQuanLy = new javax.swing.JPanel();
        labelMaQuanLy = new javax.swing.JLabel();
        labelHoTen = new javax.swing.JLabel();
        labelNgaySinh = new javax.swing.JLabel();
        labelDiaChi = new javax.swing.JLabel();
        labelKhoa = new javax.swing.JLabel();
        labelHeader = new javax.swing.JLabel();
        textMaQuanLy = new javax.swing.JTextField();
        textTenQuanLy = new javax.swing.JTextField();
        textNgaySinh = new javax.swing.JTextField();
        textDiaChi = new javax.swing.JTextField();
        textKhoa = new javax.swing.JTextField();
        quanLySinhVienPanel = new javax.swing.JPanel();
        panelThongTinSinhVien = new javax.swing.JPanel();
        panelCapNhat = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        labelMSSV = new javax.swing.JLabel();
        textMSSV = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        labelHoTenSV = new javax.swing.JLabel();
        textHoTenSV = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        labelNgaySinhSV = new javax.swing.JLabel();
        textNgaySinhSV = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        labelGioiTinhSV = new javax.swing.JLabel();
        comboGioiTinh = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        labelDiaChiSV = new javax.swing.JLabel();
        textDiaChiSV = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        labelKhoaVienSV = new javax.swing.JLabel();
        textKhoaVienSV = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        labelChuyenNganhSV = new javax.swing.JLabel();
        comboDanhSachChuyenNganh = new javax.swing.JComboBox<>();
        jLabel66 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        labelLopHocSV = new javax.swing.JLabel();
        textLopHocSV = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        panelNutCapNhat = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        btnThemSinhVien = new javax.swing.JButton();
        btnChon = new javax.swing.JButton();
        btnCapNhatSV = new javax.swing.JButton();
        btnXoaSV = new javax.swing.JButton();
        btnKhoiTao = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        panelDanhSachSinhVien = new javax.swing.JPanel();
        scrollBangSinhVien = new javax.swing.JScrollPane();
        timKiemPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textTimKiem = new javax.swing.JTextField();
        btnTimKiemSinhVien = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        nhapDiemPanel = new javax.swing.JPanel();
        danhSachLopPanel = new javax.swing.JPanel();
        scrollDanhSachSinhVienMotLop = new javax.swing.JScrollPane();
        chonLopPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textLopTimKiem = new javax.swing.JTextField();
        btnTimKiemLop = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        panelHoanTat = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        btnHoanTat = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        xetTotNghiepPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        scrollXetTotNghiep = new javax.swing.JScrollPane();
        timKiemPanel1 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        textTimKiemSVTN = new javax.swing.JTextField();
        btnXetTotNghiep = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        dangXuatPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        thongTinPanel.setLayout(new java.awt.GridBagLayout());

        panelThongTinQuanLy.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelMaQuanLy.setText("Mã quản lý");

        labelHoTen.setText("Họ và tên");

        labelNgaySinh.setText("Ngày sinh");

        labelDiaChi.setText("Địa chỉ");

        labelKhoa.setText("Quản lý khóa");

        labelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeader.setText("THÔNG TIN QUẢN LÝ");

        textMaQuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textMaQuanLyActionPerformed(evt);
            }
        });

        textDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDiaChiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelThongTinQuanLyLayout = new javax.swing.GroupLayout(panelThongTinQuanLy);
        panelThongTinQuanLy.setLayout(panelThongTinQuanLyLayout);
        panelThongTinQuanLyLayout.setHorizontalGroup(
            panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinQuanLyLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThongTinQuanLyLayout.createSequentialGroup()
                        .addComponent(labelKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongTinQuanLyLayout.createSequentialGroup()
                        .addGroup(panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(78, 78, 78)
                        .addGroup(panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongTinQuanLyLayout.createSequentialGroup()
                        .addComponent(labelHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(textTenQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongTinQuanLyLayout.createSequentialGroup()
                        .addComponent(labelMaQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(textMaQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongTinQuanLyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        panelThongTinQuanLyLayout.setVerticalGroup(
            panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinQuanLyLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(labelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMaQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textMaQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTenQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelThongTinQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        thongTinPanel.add(panelThongTinQuanLy, new java.awt.GridBagConstraints());

        chuongTrinhPanel.addTab("Thông tin cá nhân", thongTinPanel);

        quanLySinhVienPanel.setLayout(new java.awt.GridLayout(1, 2));

        panelThongTinSinhVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CẬP NHẬT THÔNG TIN SINH VIÊN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        panelThongTinSinhVien.setLayout(new java.awt.BorderLayout());

        panelCapNhat.setLayout(new java.awt.GridLayout(14, 4));
        panelCapNhat.add(jLabel96);
        panelCapNhat.add(jLabel95);
        panelCapNhat.add(jLabel98);
        panelCapNhat.add(jLabel97);
        panelCapNhat.add(jLabel92);
        panelCapNhat.add(jLabel91);
        panelCapNhat.add(jLabel94);
        panelCapNhat.add(jLabel93);
        panelCapNhat.add(jLabel77);
        panelCapNhat.add(jLabel76);
        panelCapNhat.add(jLabel78);
        panelCapNhat.add(jLabel74);
        panelCapNhat.add(jLabel75);

        labelMSSV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMSSV.setText("MSSV");
        panelCapNhat.add(labelMSSV);

        textMSSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textMSSVActionPerformed(evt);
            }
        });
        panelCapNhat.add(textMSSV);
        panelCapNhat.add(jLabel71);
        panelCapNhat.add(jLabel60);

        labelHoTenSV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelHoTenSV.setText("Họ và tên");
        panelCapNhat.add(labelHoTenSV);
        panelCapNhat.add(textHoTenSV);
        panelCapNhat.add(jLabel61);
        panelCapNhat.add(jLabel67);

        labelNgaySinhSV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNgaySinhSV.setText("Ngày sinh");
        panelCapNhat.add(labelNgaySinhSV);
        panelCapNhat.add(textNgaySinhSV);
        panelCapNhat.add(jLabel62);
        panelCapNhat.add(jLabel81);

        labelGioiTinhSV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelGioiTinhSV.setText("Giới tính");
        panelCapNhat.add(labelGioiTinhSV);

        panelCapNhat.add(comboGioiTinh);
        panelCapNhat.add(jLabel63);
        panelCapNhat.add(jLabel70);

        labelDiaChiSV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDiaChiSV.setText("Địa chỉ");
        panelCapNhat.add(labelDiaChiSV);
        panelCapNhat.add(textDiaChiSV);
        panelCapNhat.add(jLabel64);
        panelCapNhat.add(jLabel69);

        labelKhoaVienSV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKhoaVienSV.setText("Khoa Viện");
        panelCapNhat.add(labelKhoaVienSV);
        panelCapNhat.add(textKhoaVienSV);
        panelCapNhat.add(jLabel65);
        panelCapNhat.add(jLabel80);

        labelChuyenNganhSV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChuyenNganhSV.setText("Chuyên ngành");
        panelCapNhat.add(labelChuyenNganhSV);

        panelCapNhat.add(comboDanhSachChuyenNganh);
        panelCapNhat.add(jLabel66);
        panelCapNhat.add(jLabel79);

        labelLopHocSV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelLopHocSV.setText("Lớp học");
        panelCapNhat.add(labelLopHocSV);
        panelCapNhat.add(textLopHocSV);
        panelCapNhat.add(jLabel68);
        panelCapNhat.add(jLabel72);
        panelCapNhat.add(jLabel73);
        panelCapNhat.add(jLabel82);
        panelCapNhat.add(jLabel83);
        panelCapNhat.add(jLabel84);
        panelCapNhat.add(jLabel85);
        panelCapNhat.add(jLabel86);
        panelCapNhat.add(jLabel87);
        panelCapNhat.add(jLabel88);
        panelCapNhat.add(jLabel89);
        panelCapNhat.add(jLabel90);

        panelThongTinSinhVien.add(panelCapNhat, java.awt.BorderLayout.CENTER);

        panelNutCapNhat.setLayout(new java.awt.GridLayout(3, 5));
        panelNutCapNhat.add(jLabel42);
        panelNutCapNhat.add(jLabel43);
        panelNutCapNhat.add(jLabel49);
        panelNutCapNhat.add(jLabel44);
        panelNutCapNhat.add(jLabel45);

        btnThemSinhVien.setText("Thêm");
        btnThemSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSinhVienActionPerformed(evt);
            }
        });
        panelNutCapNhat.add(btnThemSinhVien);

        btnChon.setText("Chọn ");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });
        panelNutCapNhat.add(btnChon);

        btnCapNhatSV.setText("Cập nhật");
        btnCapNhatSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSVActionPerformed(evt);
            }
        });
        panelNutCapNhat.add(btnCapNhatSV);

        btnXoaSV.setText("Xóa");
        btnXoaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSVActionPerformed(evt);
            }
        });
        panelNutCapNhat.add(btnXoaSV);

        btnKhoiTao.setText("Khởi tạo");
        btnKhoiTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiTaoActionPerformed(evt);
            }
        });
        panelNutCapNhat.add(btnKhoiTao);
        panelNutCapNhat.add(jLabel46);
        panelNutCapNhat.add(jLabel47);
        panelNutCapNhat.add(jLabel48);
        panelNutCapNhat.add(jLabel51);
        panelNutCapNhat.add(jLabel54);

        panelThongTinSinhVien.add(panelNutCapNhat, java.awt.BorderLayout.PAGE_END);

        quanLySinhVienPanel.add(panelThongTinSinhVien);

        panelDanhSachSinhVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH SINH VIÊN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        panelDanhSachSinhVien.setLayout(new java.awt.BorderLayout());
        panelDanhSachSinhVien.add(scrollBangSinhVien, java.awt.BorderLayout.CENTER);

        timKiemPanel.setLayout(new java.awt.GridLayout(3, 5));
        timKiemPanel.add(jLabel5);
        timKiemPanel.add(jLabel6);
        timKiemPanel.add(jLabel7);
        timKiemPanel.add(jLabel8);
        timKiemPanel.add(jLabel9);
        timKiemPanel.add(jLabel10);

        jLabel11.setText("Lớp sinh viên");
        timKiemPanel.add(jLabel11);

        textTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTimKiemActionPerformed(evt);
            }
        });
        timKiemPanel.add(textTimKiem);

        btnTimKiemSinhVien.setText("Tìm kiếm");
        btnTimKiemSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSinhVienActionPerformed(evt);
            }
        });
        timKiemPanel.add(btnTimKiemSinhVien);
        timKiemPanel.add(jLabel35);
        timKiemPanel.add(jLabel36);
        timKiemPanel.add(jLabel38);
        timKiemPanel.add(jLabel40);
        timKiemPanel.add(jLabel41);

        panelDanhSachSinhVien.add(timKiemPanel, java.awt.BorderLayout.PAGE_START);

        quanLySinhVienPanel.add(panelDanhSachSinhVien);

        chuongTrinhPanel.addTab("Quản lý sinh viên", quanLySinhVienPanel);

        nhapDiemPanel.setLayout(new java.awt.BorderLayout());

        danhSachLopPanel.setBackground(new java.awt.Color(102, 255, 0));
        danhSachLopPanel.setLayout(new java.awt.BorderLayout());
        danhSachLopPanel.add(scrollDanhSachSinhVienMotLop, java.awt.BorderLayout.CENTER);

        chonLopPanel.setLayout(new java.awt.GridLayout(3, 4));
        chonLopPanel.add(jLabel12);
        chonLopPanel.add(jLabel13);
        chonLopPanel.add(jLabel14);
        chonLopPanel.add(jLabel15);
        chonLopPanel.add(jLabel16);

        textLopTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLopTimKiemActionPerformed(evt);
            }
        });
        chonLopPanel.add(textLopTimKiem);

        btnTimKiemLop.setText("Tìm kiếm lớp");
        btnTimKiemLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemLopActionPerformed(evt);
            }
        });
        chonLopPanel.add(btnTimKiemLop);
        chonLopPanel.add(jLabel17);
        chonLopPanel.add(jLabel22);
        chonLopPanel.add(jLabel23);
        chonLopPanel.add(jLabel25);
        chonLopPanel.add(jLabel26);

        danhSachLopPanel.add(chonLopPanel, java.awt.BorderLayout.PAGE_START);

        panelHoanTat.setLayout(new java.awt.GridLayout(3, 5));
        panelHoanTat.add(jLabel18);
        panelHoanTat.add(jLabel19);
        panelHoanTat.add(jLabel20);
        panelHoanTat.add(jLabel21);
        panelHoanTat.add(jLabel24);
        panelHoanTat.add(jLabel27);
        panelHoanTat.add(jLabel32);

        btnHoanTat.setText("Hoàn tất nhập điểm");
        btnHoanTat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanTatActionPerformed(evt);
            }
        });
        panelHoanTat.add(btnHoanTat);
        panelHoanTat.add(jLabel28);
        panelHoanTat.add(jLabel29);
        panelHoanTat.add(jLabel30);
        panelHoanTat.add(jLabel31);
        panelHoanTat.add(jLabel33);

        danhSachLopPanel.add(panelHoanTat, java.awt.BorderLayout.PAGE_END);

        nhapDiemPanel.add(danhSachLopPanel, java.awt.BorderLayout.CENTER);

        chuongTrinhPanel.addTab("Nhập điểm theo lớp", nhapDiemPanel);

        xetTotNghiepPanel.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 102, 51));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jPanel2.add(scrollXetTotNghiep);

        xetTotNghiepPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        timKiemPanel1.setLayout(new java.awt.GridLayout(3, 5));
        timKiemPanel1.add(jLabel34);
        timKiemPanel1.add(jLabel37);
        timKiemPanel1.add(jLabel39);
        timKiemPanel1.add(jLabel50);
        timKiemPanel1.add(jLabel52);
        timKiemPanel1.add(jLabel53);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("Mã số sinh viên");
        timKiemPanel1.add(jLabel55);

        textTimKiemSVTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTimKiemSVTNActionPerformed(evt);
            }
        });
        timKiemPanel1.add(textTimKiemSVTN);

        btnXetTotNghiep.setText("Xét tốt nghiệp");
        btnXetTotNghiep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXetTotNghiepActionPerformed(evt);
            }
        });
        timKiemPanel1.add(btnXetTotNghiep);
        timKiemPanel1.add(jLabel56);
        timKiemPanel1.add(jLabel57);
        timKiemPanel1.add(jLabel58);
        timKiemPanel1.add(jLabel59);
        timKiemPanel1.add(jLabel99);

        xetTotNghiepPanel.add(timKiemPanel1, java.awt.BorderLayout.PAGE_START);

        chuongTrinhPanel.addTab("Xét tốt nghiệp", xetTotNghiepPanel);

        javax.swing.GroupLayout dangXuatPanelLayout = new javax.swing.GroupLayout(dangXuatPanel);
        dangXuatPanel.setLayout(dangXuatPanelLayout);
        dangXuatPanelLayout.setHorizontalGroup(
            dangXuatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1102, Short.MAX_VALUE)
        );
        dangXuatPanelLayout.setVerticalGroup(
            dangXuatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );

        chuongTrinhPanel.addTab("Đăng xuất", dangXuatPanel);

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
                .addComponent(chuongTrinhPanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chuongTrinhPanel)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textMaQuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMaQuanLyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textMaQuanLyActionPerformed

    private void textDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textDiaChiActionPerformed

    private void btnTimKiemSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSinhVienActionPerformed
        // TODO add your handling code here:
        if("".equals(textTimKiem.getText())) {
            taoBangSinhVien(quanLy, "");
            return;
        } else {
            try{
                taoBangSinhVien(quanLy, textTimKiem.getText());
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }                                                                                  
    }//GEN-LAST:event_btnTimKiemSinhVienActionPerformed

    private void textMSSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMSSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textMSSVActionPerformed

    private void btnThemSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSinhVienActionPerformed
        // TODO add your handling code here:
        if("".equals(textMSSV.getText())||"".equals(textHoTenSV.getText())||"".equals(textNgaySinhSV.getText())||"".equals(comboGioiTinh.getSelectedItem())||
           "".equals(textDiaChiSV.getText())||"".equals(textKhoaVienSV.getText())||"".equals(C)||"".equals(textLopHocSV.getText())) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin.");
            return;
        } else {
            for(int i = 0; i < Input.getDanhSachSinhVienInput().size(); i++) {
                SinhVien sinhVien = Input.getDanhSachSinhVienInput().get(i);
                if(sinhVien.getMssv() == Integer.parseInt(textMSSV.getText())) {
                    JOptionPane.showMessageDialog(null, "Bị trùng MSSV.");
                    return;
                }
            }

            if( comboDanhSachChuyenNganh.getSelectedItem().equals(ChuyenNganh.KSTN_CDT.name())||
                comboDanhSachChuyenNganh.getSelectedItem().equals(ChuyenNganh.KSTN_CNTT.name())||
                comboDanhSachChuyenNganh.getSelectedItem().equals(ChuyenNganh.KSCLC_HTTT.name())||
                comboDanhSachChuyenNganh.getSelectedItem().equals(ChuyenNganh.CTTT_CDT.name())) {
                int mssv = Integer.parseInt(textMSSV.getText());
                boolean gioiTinh;
                if(comboGioiTinh.getSelectedItem().equals("Nam")) gioiTinh = true; else gioiTinh = false;
                ChuyenNganh chuyenNganh = ChuyenNganh.CHUA_PHAN;
                for(int i = 0; i < Input.getDanhMucChuyenNganh().length; i++) {
                    if(Input.getDanhMucChuyenNganh()[i].name().equals(comboDanhSachChuyenNganh.getSelectedItem()))
                        chuyenNganh = Input.getDanhMucChuyenNganh()[i];
                }
                
                SinhVien sinhVien = new SinhVienNienChe(mssv, textHoTenSV.getText(), textNgaySinhSV.getText(), gioiTinh, textDiaChiSV.getText(), 
                                                        textKhoaVienSV.getText(), chuyenNganh, textLopHocSV.getText());
                Input.getDanhSachSinhVienInput().add(sinhVien);
            } else {
                int mssv = Integer.parseInt(textMSSV.getText());
                boolean gioiTinh;
                if(comboGioiTinh.getSelectedItem().equals("Nam")) gioiTinh = true; else gioiTinh = false;
                ChuyenNganh chuyenNganh = ChuyenNganh.CHUA_PHAN;
                for(int i = 0; i < Input.getDanhMucChuyenNganh().length; i++) {
                    if(Input.getDanhMucChuyenNganh()[i].name().equals(comboDanhSachChuyenNganh.getSelectedItem()))
                        chuyenNganh = Input.getDanhMucChuyenNganh()[i];
                }
                SinhVien sinhVien = new SinhVienTinChi(mssv, textHoTenSV.getText(), textNgaySinhSV.getText(), gioiTinh, textDiaChiSV.getText(), 
                                                        textKhoaVienSV.getText(), chuyenNganh, textLopHocSV.getText());
                Input.getDanhSachSinhVienInput().add(sinhVien);
            }
            
            Input.capNhatTinChiVaNienChe();
            quanLy.capNhatDanhSachSinhVienQuanLy();
            Output.outputSinhVien();
            taoBangSinhVien(quanLy, "");
        }
    }//GEN-LAST:event_btnThemSinhVienActionPerformed

    private void btnTimKiemLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemLopActionPerformed
        // TODO add your handling code here:
        if("".equals(textLopTimKiem.getText())) {
            return;
        } else {
            try{
                LopHoc<? extends MonHoc> lopHoc = Input.getInstanceOfLopHoc(Integer.parseInt(textLopTimKiem.getText().trim()));
                if(lopHoc != null) {
                    Input.capNhatSinhVienDangKyLop(lopHoc);
                    for(SinhVien sinhVien: lopHoc.getDanhSachSinhVien()) {
                        Input.capNhatLopDangKyCuaSinhVien(sinhVien);
                        Input.capNhatBangDiemSinhVien(sinhVien);
                    }
                    taoBangDiemSinhVien(quanLy, textLopTimKiem.getText());
                }
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }        
    }//GEN-LAST:event_btnTimKiemLopActionPerformed

    private void textLopTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLopTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textLopTimKiemActionPerformed

    private void btnCapNhatSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSVActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn muốn cập nhật thông tin sinh viên này?");
        if(dialogResult == JOptionPane.YES_OPTION) {
            if("".equals(textHoTenSV.getText())||"".equals(textNgaySinhSV.getText())||"".equals(comboGioiTinh.getSelectedItem())||
                "".equals(textDiaChiSV.getText())||"".equals(textKhoaVienSV.getText())||"".equals(textLopHocSV.getText())) {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin.");
                return;
            } else {
                int mssv = Integer.parseInt(textMSSV.getText());
                SinhVien sinhVien = Input.getInstanceOfSinhVien(mssv);
                if(sinhVien != null) {
                    sinhVien.setTen(textHoTenSV.getText());
                    sinhVien.setNgaySinh(textNgaySinhSV.getText());
                    sinhVien.setGioiTinh("Nam".equals(comboGioiTinh.getSelectedItem())? true: false);
                    sinhVien.setDiaChi(textDiaChiSV.getText());
                    sinhVien.setDiaChi(textKhoaVienSV.getText());
                    sinhVien.setLopSinhVien(textLopHocSV.getText());
                    
                    Output.outputSinhVien();
                    if(sinhVien instanceof SinhVienTinChi) {
                        for(LopHoc<HocPhan> lopHoc: ((SinhVienTinChi)sinhVien).getDanhSachDangKy().getDanhSachLopHoc())
                            Output.outputDangKy(lopHoc);
                    }else {
                        for(LopHoc<HocTrinh> lopHoc: ((SinhVienNienChe)sinhVien).getDanhSachDangKy().getDanhSachLopHoc())
                            Output.outputDangKy(lopHoc);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công.");
                }
                taoBangSinhVien(quanLy, "");
            }
        textMSSV.setEditable(true);
        comboDanhSachChuyenNganh.setEditable(true);
        taoBangThongTinSinhVien();
        }
    }//GEN-LAST:event_btnCapNhatSVActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        // TODO add your handling code here:
        String mssv = JOptionPane.showInputDialog("Nhập MSSV");
        if("".equals(mssv)) {
            taoBangThongTinSinhVien();
            return;
        } else {
            int MSSV = Integer.parseInt(mssv);
            SinhVien sinhVien = Input.getInstanceOfSinhVien(MSSV);
            if(sinhVien != null) {
                textMSSV.setText("" + sinhVien.getMssv());
                textMSSV.setEditable(false);
                textHoTenSV.setText(sinhVien.getTen());
                textNgaySinhSV.setText(sinhVien.getNgaySinh());
                textDiaChiSV.setText(sinhVien.getDiaChi());
                textKhoaVienSV.setText(sinhVien.getKhoaVien());
                textLopHocSV.setText(sinhVien.getLopSinhVien());
                comboGioiTinh.setSelectedItem(sinhVien.isGioiTinh()? "Nam": "Nữ");
                comboDanhSachChuyenNganh.setSelectedItem(sinhVien.getChuyenNganh().name());
                comboDanhSachChuyenNganh.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(null, "Không có sinh viên này");
            }     
        }
    }//GEN-LAST:event_btnChonActionPerformed

    private void btnXoaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSVActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa thông tin sinh viên này?");
        if(dialogResult == JOptionPane.YES_OPTION) {
            int mssv = Integer.parseInt(textMSSV.getText());
            SinhVien sinhVien = Input.getInstanceOfSinhVien(mssv);
            if(sinhVien != null){
                Input.getDanhSachSinhVienInput().remove(sinhVien);
                Input.capNhatTinChiVaNienChe();
                quanLy.getDanhSachSinhVien().remove(sinhVien);
                taoBangSinhVien(quanLy, "");
                
                Output.outputSinhVien();
                if(sinhVien instanceof SinhVienTinChi) {
                    for(LopHoc<HocPhan> lopHoc: ((SinhVienTinChi)sinhVien).getDanhSachDangKy().getDanhSachLopHoc())
                        Output.outputDangKy(lopHoc);
                }else {
                    for(LopHoc<HocTrinh> lopHoc: ((SinhVienNienChe)sinhVien).getDanhSachDangKy().getDanhSachLopHoc())
                        Output.outputDangKy(lopHoc);
                }
                    
                JOptionPane.showMessageDialog(null, "Xóa thành công.");
            }
            textMSSV.setEditable(true);
            comboDanhSachChuyenNganh.setEditable(true);
            taoBangThongTinSinhVien();
        }
    }//GEN-LAST:event_btnXoaSVActionPerformed

    private void textTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTimKiemActionPerformed

    private void textTimKiemSVTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTimKiemSVTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTimKiemSVTNActionPerformed

    private void btnXetTotNghiepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXetTotNghiepActionPerformed
        // TODO add your handling code here:
        if("".equals(textTimKiemSVTN.getText())) {
            taoBangXetTotNghiep(quanLy, "");
        } else {
            try{
                taoBangXetTotNghiep(quanLy, textTimKiemSVTN.getText());
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }        
    }//GEN-LAST:event_btnXetTotNghiepActionPerformed

    private void btnHoanTatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanTatActionPerformed
        // TODO add your handling code here:
        if("".equals(textLopTimKiem.getText()) || bangDiemLop==null) {
            return;
        } else {
            if (bangDiemLop.isEditing())
                bangDiemLop.getCellEditor().stopCellEditing();
            else 
                return;
            
            for(int i = 0; i < quanLy.getDanhSachQuanLy().size(); i++) {
                LopHoc<? extends MonHoc> lopHoc = quanLy.getDanhSachQuanLy().get(i);
                if(textLopTimKiem.getText().equals("" + lopHoc.getMaLop())) {
                    for(int j = 0; j < lopHoc.getDanhSachSinhVien().size(); j++) {
                        if( bangDiemLop.getValueAt(j, 3) == null || bangDiemLop.getValueAt(j, 4) == null ||
                         "".equals(bangDiemLop.getValueAt(j, 3)) || "".equals(bangDiemLop.getValueAt(j, 4))) {
                            JOptionPane.showMessageDialog(null, "Chưa nhập hết điểm");
                            return;
                        }
                    }
                        
                    double[][] bangDiem = new double[lopHoc.getDanhSachSinhVien().size()][2];
                    for(int j = 0; j < lopHoc.getDanhSachSinhVien().size(); j++) {
                        bangDiem[j][0] = Double.parseDouble(bangDiemLop.getModel().getValueAt(j, 3).toString());
                        bangDiem[j][1] = Double.parseDouble(bangDiemLop.getModel().getValueAt(j, 4).toString());
                    }
                    quanLy.nhapDiem(lopHoc, bangDiem, nhapMoi);
                    Output.outputDiemTungLop(lopHoc, bangDiem);
                    for(SinhVien sinhVien: lopHoc.getDanhSachSinhVien()) 
                        Output.outputBangDiem(sinhVien);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Không có lớp này");    
        }
    }//GEN-LAST:event_btnHoanTatActionPerformed

    private void btnKhoiTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiTaoActionPerformed
        // TODO add your handling code here:
        textMSSV.setEditable(true);
        comboDanhSachChuyenNganh.setEditable(true);
        taoBangThongTinSinhVien();
    }//GEN-LAST:event_btnKhoiTaoActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatSV;
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnHoanTat;
    private javax.swing.JButton btnKhoiTao;
    private javax.swing.JButton btnThemSinhVien;
    private javax.swing.JButton btnTimKiemLop;
    private javax.swing.JButton btnTimKiemSinhVien;
    private javax.swing.JButton btnXetTotNghiep;
    private javax.swing.JButton btnXoaSV;
    private javax.swing.JPanel chonLopPanel;
    private javax.swing.JTabbedPane chuongTrinhPanel;
    private javax.swing.JComboBox<String> comboDanhSachChuyenNganh;
    private javax.swing.JComboBox<String> comboGioiTinh;
    private javax.swing.JPanel dangXuatPanel;
    private javax.swing.JPanel danhSachLopPanel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelChuyenNganhSV;
    private javax.swing.JLabel labelDiaChi;
    private javax.swing.JLabel labelDiaChiSV;
    private javax.swing.JLabel labelGioiTinhSV;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JLabel labelHoTen;
    private javax.swing.JLabel labelHoTenSV;
    private javax.swing.JLabel labelKhoa;
    private javax.swing.JLabel labelKhoaVienSV;
    private javax.swing.JLabel labelLopHocSV;
    private javax.swing.JLabel labelMSSV;
    private javax.swing.JLabel labelMaQuanLy;
    private javax.swing.JLabel labelNgaySinh;
    private javax.swing.JLabel labelNgaySinhSV;
    private javax.swing.JPanel nhapDiemPanel;
    private javax.swing.JPanel panelCapNhat;
    private javax.swing.JPanel panelDanhSachSinhVien;
    private javax.swing.JPanel panelHoanTat;
    private javax.swing.JPanel panelNutCapNhat;
    private javax.swing.JPanel panelThongTinQuanLy;
    private javax.swing.JPanel panelThongTinSinhVien;
    private javax.swing.JPanel quanLySinhVienPanel;
    private javax.swing.JScrollPane scrollBangSinhVien;
    private javax.swing.JScrollPane scrollDanhSachSinhVienMotLop;
    private javax.swing.JScrollPane scrollXetTotNghiep;
    private javax.swing.JTextField textDiaChi;
    private javax.swing.JTextField textDiaChiSV;
    private javax.swing.JTextField textHoTenSV;
    private javax.swing.JTextField textKhoa;
    private javax.swing.JTextField textKhoaVienSV;
    private javax.swing.JTextField textLopHocSV;
    private javax.swing.JTextField textLopTimKiem;
    private javax.swing.JTextField textMSSV;
    private javax.swing.JTextField textMaQuanLy;
    private javax.swing.JTextField textNgaySinh;
    private javax.swing.JTextField textNgaySinhSV;
    private javax.swing.JTextField textTenQuanLy;
    private javax.swing.JTextField textTimKiem;
    private javax.swing.JTextField textTimKiemSVTN;
    private javax.swing.JPanel thongTinPanel;
    private javax.swing.JPanel timKiemPanel;
    private javax.swing.JPanel timKiemPanel1;
    private javax.swing.JPanel xetTotNghiepPanel;
    // End of variables declaration//GEN-END:variables
    
}
