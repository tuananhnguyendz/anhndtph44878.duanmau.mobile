package fpoly.anhndtph44878duanmaumobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.database.DBHelper;
import fpoly.anhndtph44878duanmaumobile.model.Thanhvien;

public class ThanhvienDAO {
    private DBHelper dbHelper;
    Context context;

    public ThanhvienDAO(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }
    public ArrayList<Thanhvien> dsThanhvien(){
        ArrayList<Thanhvien> list_Thanhvien = new ArrayList<>();
        String truyvan = "SELECT * FROM THANHVIEN";
        Cursor ketqua = dbHelper.getReadableDatabase().rawQuery(truyvan,null);
        if (ketqua.getCount()>0){
            if (ketqua.moveToFirst()){
                do {
                    Thanhvien thanhvien = new Thanhvien();
                    thanhvien.setMatv(ketqua.getInt(0));
                    thanhvien.setHoten(ketqua.getString(1));
                    thanhvien.setNamsinh(ketqua.getString(2));
                    list_Thanhvien.add(thanhvien);
                }while (ketqua.moveToNext());
            }
        }
        return list_Thanhvien;
    }
    public boolean deleteTV(int matv){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("THANHVIEN","matv = ?",new String[]{String.valueOf(matv)});
        if (check > 0){
            Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "Xoá thất bại", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    public boolean addThanhvien(String hoten,String namsinh){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",hoten);
        values.put("namsinh",namsinh);

        long check = database.insert("THANHVIEN",null,values);
        if (check ==-1){
            return false;
        } return true;
    }

    public boolean updateTV(int matv, String hoten, String namsinh){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matv",matv);
        values.put("hoten",hoten);
        values.put("namsinh",namsinh);

        long check = database.update("THANHVIEN",values,"matv = ?",new String[]{String.valueOf(matv)});
        if (check ==-1){
            return false;
        } return true;
    }

}
