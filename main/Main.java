package main;

import gui.LogIn;
import java.io.FileNotFoundException;
import java.io.IOException;
import users.QuanLy;

public class Main {
    static final String[] fileName = {"src/data/sinhvien.txt", "src/data/hocphan.txt","src/data/hoctrinh.txt", 
                                      "src/data/lophoc.txt", "src/data/bangdiem","src/data/dangky_lophoc", 
                                      "src/data/dangky_sinhvien", "src/data/lop_diem"};
    
    public static void main(String args[]) throws FileNotFoundException, IOException{
        init();
        LogIn application = new LogIn();
	application.setVisible(true); 
    }
        
    public static void init() {
        Input.capNhatDanhSachQuanLyInput();
        Input.capNhatDanhSachHocPhanInput();
        Input.capNhatDanhSachHocTrinhInput();
        Input.capNhatDanhSachLopInput();
        Input.capNhatHeThongDaoTaoTinChi();
	Input.capNhatHeThongDaoTaoNienChe();
        Input.capNhatDanhSachSinhVienInput();
	Input.capNhatTinChiVaNienChe();
        for(QuanLy quanLy: Input.getDanhSachQuanLyInput()){
            quanLy.capNhatDanhSachSinhVienQuanLy();
            quanLy.capNhatDanhSachLopQuanLy();
        }
    }
    
    public static String[] getFileName() {
        return fileName;
    }
}
