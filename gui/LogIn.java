package gui;
import main.Input;
import javax.swing.*;
import users.QuanLy;
import users.SinhVien;
public class LogIn extends javax.swing.JFrame {
    public LogIn() {
        this.setSize(423, 192);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
	this.setVisible(true); 
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DangNhap = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1200, 600));
        setName("Login"); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        DangNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ĐĂNG NHẬP", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        DangNhap.setToolTipText("");
        DangNhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tên đăng nhập");
        DangNhap.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 100, 20));

        userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameActionPerformed(evt);
            }
        });
        DangNhap.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 60, 210, 26));

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        DangNhap.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 120, 210, 28));

        btnSubmit.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnSubmit.setText("Đăng nhập");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        DangNhap.add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 100, 33));

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        DangNhap.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 100, 33));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Mật khẩu");
        DangNhap.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 84, 28));

        getContentPane().add(DangNhap);
        DangNhap.getAccessibleContext().setAccessibleName("");

        setSize(new java.awt.Dimension(423, 259));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        try{
            for(int i = 0; i < Input.getDanhSachSinhVienInput().size(); i++) {
                SinhVien sinhVien = Input.getDanhSachSinhVienInput().get(i);
                if(sinhVien.getMssv() == Integer.parseInt(userName.getText())) {
                    JOptionPane.showMessageDialog(null, String.format("Xin chào bạn %s", sinhVien.getTen()));
                    TrangCaNhan trangCaNhan = new TrangCaNhan(sinhVien);
                    trangCaNhan.setVisible(true);
                    return;
                }
            }
            
            for(int i = 0; i < Input.getDanhSachQuanLyInput().size(); i++) {
                QuanLy quanLy = Input.getDanhSachQuanLyInput().get(i);
                if(quanLy.getMaSoQuanLy() == Integer.parseInt(userName.getText())) {
                    JOptionPane.showMessageDialog(null, String.format("Xin chào admin %s", quanLy.getTen()));
                    TrangQuanLy trangQuanLy = new TrangQuanLy(quanLy);
                    trangQuanLy.setVisible(true);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng.", "Access Denied", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DangNhap;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables
}
