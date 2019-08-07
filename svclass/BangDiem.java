package svclass;

import java.util.ArrayList;

import svnc.HocTrinh;
import svtc.HocPhan;

public class BangDiem<T extends MonHoc>{
    private ArrayList<Diem<T>> danhSachDiem;
    
    public BangDiem() {
        this.danhSachDiem = new ArrayList<Diem<T>>();
    }    

    public ArrayList<Diem<T>> getDanhSachDiem() {
        return danhSachDiem;
    }
    
    public Diem<T> findDiem(T monHocCanTim) {
    	for(int i = 0; i < getDanhSachDiem().size(); i++) {
    		Diem<T> diem = getDanhSachDiem().get(i);
    		if(diem.getMonHoc() == monHocCanTim)
    			return diem;
    	}
    	return null;
    }
    
    public void addDiem(Diem<T> diem) {
	getDanhSachDiem().add(diem);
    }
    
    public void removeDiem(T monHoc) {
	for(int i = 0; i < getDanhSachDiem().size(); i++) {
            Diem<T> diem = getDanhSachDiem().get(i);
		if(diem.getMonHoc() == monHoc) getDanhSachDiem().remove(i);
	}
    }
    
    public void updateDiem(Diem<T> diem) {
        for(int i = 0; i < getDanhSachDiem().size(); i++) {
            Diem<T> diemCu = getDanhSachDiem().get(i);
            if(diemCu.getMonHoc() == diem.getMonHoc())
                getDanhSachDiem().set(i, diem);
        }
    }
	
    public boolean daQuaMonHoc(String maMonHoc) {
    	for(Diem<T> diem: getDanhSachDiem())
            if(diem.getMonHoc().getMaMonHoc() == maMonHoc){
                MonHoc monHoc = diem.getMonHoc();
                if((monHoc instanceof HocPhan && diem.getTongKet() >= 1.0)
                    ||(monHoc instanceof HocTrinh && diem.getTongKet() >= 5.0))
                        return true;				
            }
    	return false;
    }
    
    public void inBangDiem() {
	for(int i = 0; i < getDanhSachDiem().size(); i++){
            Diem<T> diem = getDanhSachDiem().get(i);
            System.out.printf("%2d. %s\n", i + 1, diem);
	}
    }
}
