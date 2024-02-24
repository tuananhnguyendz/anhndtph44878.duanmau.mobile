package fpoly.anhndtph44878duanmaumobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.anhndtph44878duanmaumobile.database.DBHelper;

public class ThuthuDAO {
    private DBHelper dbHelper;
    Context context;

    public ThuthuDAO(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public boolean checkLogin(String name, String password) {
        //getReadableDatabase sử dụng khi chức năng đọc dữ liệu, lấy danh sách ...(truy vấn thông tin)
        //getWritableDatabase s dụng chức năng khi thêm sửa xoá dữ liueeuj trong database
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        // truy van
        Cursor cursor = database.rawQuery("SELECT * FROM THUTHU Where matt = ? AND matkhau = ?", new String[]{name, password});
        if (cursor.getCount() > 0) {
            //có đúng dữ liệu sẽ tra ve true nguoc lai ve false

            //Lưu role account khi ng dùng đăng nhập : 1 ng dùng, 2 thủ thư, 3 admin
//            cursor.moveToFirst();
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt("role",cursor.getInt(6));
//            editor.apply();
            return true;
        }
        return false;
    }

    public boolean doiMatkhau(String userName, String oldPass, String newPass) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{userName, oldPass});
        if (cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("matkhau", newPass);
            long check = database.update("THUTHU", values, "matt = ?", new String[]{userName});
            if (check != -1)
                return true;
        }
        return false;
    }
}
