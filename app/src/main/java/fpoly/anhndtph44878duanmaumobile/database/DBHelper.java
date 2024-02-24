package fpoly.anhndtph44878duanmaumobile.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "quanlithuvien", null, 10);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tạo data
        String sqlThuthu = "CREATE TABLE THUTHU ( matt TEXT PRIMARY KEY, hotentt TEXT, matkhau TEXT)\n";
        db.execSQL(sqlThuthu);
        String sqlThanhvien = "CREATE TABLE THANHVIEN( matv INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT, namsinh TEXT)\n";
        db.execSQL(sqlThanhvien);
        String sqlLoaisach = "CREATE TABLE LOAISACH( maloai INTEGER PRIMARY KEY AUTOINCREMENT, tenloai TEXT)\n";
        db.execSQL(sqlLoaisach);
        String sqlSach = "CREATE TABLE SACH ( masach INTEGER PRIMARY KEY AUTOINCREMENT, tensach TEXT, giathue INTEGER, maloai INTEGER REFERENCES LOAISACH(maloai) )\n";
        db.execSQL(sqlSach);
        String sqlPhieumuon = "CREATE TABLE PHIEUMUON ( mapm INTEGER PRIMARY KEY AUTOINCREMENT, matv INTEGER REFERENCES THANHVIEN(matv), matt TEXT REFERENCES THUTHU(matt),masach INTEGER REFERENCES SACH(masach), ngay TEXT,trasach INTEGER,tienthue INTEGER)\n";
        db.execSQL(sqlPhieumuon);

        //fix cứng dữ liệu
        db.execSQL("INSERT INTO SACH VALUES (1,'Em là của anh',2500,1),(2,'Doraemon',3500,2),(3,'Diệp vấn',4000,3),(4,'OnePice',4500,3),(5,'Người yêu cũ',5000,2);\n");
        db.execSQL("INSERT INTO THUTHU VALUES ('thuthu01', 'Nguyễn Đình Tuấn Anh', '110804'), \n" +
                "                           ('thuthu02', 'Trần Xuân Ánh', 'ph44878'), \n" +
                "                          ('thuthu03', 'Nguyễn Đăng Hào', '12345');");
        db.execSQL("INSERT INTO THANHVIEN VALUES(1,'Nguyễn Trọng Hoàng ','2004'),(2,'Dương Đức Duy','2003'),(3,'Khổng Minh Đạt','2003'),(4,'Nguyễn Văn Thắng','2003'),(5,'Minh Quân','2003')");
        db.execSQL("INSERT INTO LOAISACH VALUES(1,'Tình Cảm'),(2,'Thiếu nhi'),(3,'Hành động')");
        //1 là trả sách, 0 là chưa trả
        db.execSQL("INSERT INTO PHIEUMUON VALUES(1,1,'thuthu01',1,'11-08-2024',1,2500),(2,2,'thuthu02',2,'21-08-2024',0,3500),(3,3,'thuthu03',3,'11-09-2024',1,4500)");
//        db.execSQL("INSERT INTO PHIEUMUON VALUES (1, 1, 'thuthu01', 1, '2024-08-11', 1, 2500)");
//        db.execSQL("INSERT INTO PHIEUMUON VALUES (2, 2, 'thuthu02', 2, '2024-08-21', 0, 3500)");
//        db.execSQL("INSERT INTO PHIEUMUON VALUES (3, 3, 'thuthu03', 3, '2024-09-11', 1, 4500)");

    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion)
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
        db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
        db.execSQL("DROP TABLE IF EXISTS LOAISACH");
        db.execSQL("DROP TABLE IF EXISTS SACH");
        db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
        onCreate(db);
    }
}
