package fpoly.anhndtph44878duanmaumobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.database.DBHelper;
import fpoly.anhndtph44878duanmaumobile.model.Loaisach;

public class TheloaisachDAO {
    private DBHelper dbHelper;
    Context context;

    public TheloaisachDAO(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }
    public ArrayList<Loaisach> dsLoaiSach(){
        ArrayList<Loaisach> list_LS = new ArrayList<>();
        String truyvan = "SELECT * FROM LOAISACH";
        Cursor ketqua = dbHelper.getReadableDatabase().rawQuery(truyvan,null);
        if (ketqua.getCount()>0){
            if (ketqua.moveToFirst()){
                do {
                    Loaisach loaisach = new Loaisach();
                    loaisach.setMaloaisach(ketqua.getInt(0));
                    loaisach.setTenloaisach(ketqua.getString(1));
                    list_LS.add(loaisach);
                }while (ketqua.moveToNext());
            }
        }
        return list_LS;
    }
    public boolean themLoaisach(String tenloaisach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",tenloaisach);
        long check = database.insert("LOAISACH",null,values);
        if (check ==-1){
            return false;
        } return true;

    }

    public boolean suaLoaisach(int maloaisach, String tenloaisach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maloai",maloaisach);
        values.put("tenloai",tenloaisach);
        long check = database.update("LOAISACH",values,"maloai = ?",new String[]{String.valueOf(maloaisach)});
        if (check ==-1){
            return false;
        } return true;
    }

    //xoá loại sách
    public boolean deleteLoaisach(int maloaisach ){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("LOAISACH","maloai =?",new String[]{String.valueOf(maloaisach)});
        if (check > 0){
            Toast.makeText(context, "Xoá loại sách thành công", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "Xoá loại sách thất bại", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
