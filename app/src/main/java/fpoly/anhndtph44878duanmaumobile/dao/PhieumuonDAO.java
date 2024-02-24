package fpoly.anhndtph44878duanmaumobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.database.DBHelper;
import fpoly.anhndtph44878duanmaumobile.model.Phieumuon;

public class PhieumuonDAO {
    private DBHelper dbHelper;
    Context context;

    public PhieumuonDAO(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public ArrayList<Phieumuon> dsPhieumuon() {
        ArrayList<Phieumuon> list_Phieumuon = new ArrayList<>();
        String truyvan = "SELECT pm.mapm,pm.matv,tv.hoten,pm.matt,tt.hotentt,pm.masach,sc.tensach,pm.ngay,pm.trasach,pm.tienthue FROM PHIEUMUON pm , THANHVIEN tv, THUTHU tt, SACH sc WHERE pm.matv = tv.matv AND pm.matt = tt.matt AND pm.masach = sc.masach";
        Cursor ketqua = dbHelper.getReadableDatabase().rawQuery(truyvan, null);
        if (ketqua.getCount() > 0) {
            if (ketqua.moveToFirst()) {
                do {
                    Phieumuon phieumuon = new Phieumuon();
                    phieumuon.setMapm(ketqua.getInt(0));
                    phieumuon.setMatv(ketqua.getInt(1));
                    phieumuon.setHoten(ketqua.getString(2));
                    phieumuon.setMatt(ketqua.getString(3));
                    phieumuon.setHotentt(ketqua.getString(4));
                    phieumuon.setMasach(ketqua.getInt(5));
                    phieumuon.setTensach(ketqua.getString(6));
                    phieumuon.setNgay(ketqua.getString(7));
                    phieumuon.setTrasach(ketqua.getInt(8));
                    phieumuon.setTienthue(ketqua.getInt(9));
                    list_Phieumuon.add(phieumuon);

                } while (ketqua.moveToNext());
            }
        }
        return list_Phieumuon;
    }

    //update trạng thái phiếu mượn
    public boolean thaydoiTrangthai(int mapm) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("trasach", 1);
        //1 là đã trả sách
        long check = database.update("PHIEUMUON", values, "mapm = ?", new String[]{String.valueOf(mapm)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean xoaPhieumuon(int mapm) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("PHIEUMUON", "mapm = ?", new String[]{String.valueOf(mapm)});
        if (check > 0) {
            Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "Xoá thất bại", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public boolean themPhieumuon(Phieumuon phieumuon) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matv", phieumuon.getMatv());
        values.put("matt", phieumuon.getMatt());
        values.put("masach", phieumuon.getMasach());
        values.put("ngay", phieumuon.getNgay());
        values.put("trasach", phieumuon.getTrasach());
        values.put("tienthue", phieumuon.getTienthue());
        long check = database.insert("PHIEUMUON", null, values);
        if (check == -1) {
            return false;
        }
        return true;
    }

    public int getDoanhthu(String ngaybatdau, String ngayketthuc) {
        ngaybatdau = ngaybatdau.replace("/","");
        ngayketthuc = ngayketthuc.replace("/","");

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT SUM(tienthue) FROM PHIEUMUON WHERE substr(ngay,7)||substr (ngay,4,2)|| substr (ngay,1,2) BETWEEN ? AND ?", new String[]{ngaybatdau, ngayketthuc});
        int doanhthu = 0;
        if (cursor.moveToFirst()) {
            doanhthu = cursor.getInt(0);
        }
        cursor.close();
        return doanhthu;
    }

}
