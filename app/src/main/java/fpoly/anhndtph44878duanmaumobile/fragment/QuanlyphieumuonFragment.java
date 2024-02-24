package fpoly.anhndtph44878duanmaumobile.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.adapter.PhieumuonAdapter;
import fpoly.anhndtph44878duanmaumobile.dao.PhieumuonDAO;
import fpoly.anhndtph44878duanmaumobile.dao.SachDAO;
import fpoly.anhndtph44878duanmaumobile.dao.ThanhvienDAO;
import fpoly.anhndtph44878duanmaumobile.model.Phieumuon;
import fpoly.anhndtph44878duanmaumobile.model.Sach;
import fpoly.anhndtph44878duanmaumobile.model.Thanhvien;

public class QuanlyphieumuonFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatt;
    ArrayList<Phieumuon> list;
    PhieumuonAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    PhieumuonDAO phieumuonDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quanlyphieumuon, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        floatt = view.findViewById(R.id.float_button_phieumuon);
        floatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themPhieumuon();
            }

        });
        loadData();
        return view;

    }
    private void themPhieumuon() {
        View view = getLayoutInflater().inflate(R.layout.layout_bottomsheet_themphieumuon,null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        Spinner spin_thanhvien = view.findViewById(R.id.spin_thanhvien);
        Spinner spin_sach = view.findViewById(R.id.spin_sach);
        EditText edt_tien = view.findViewById(R.id.edt_tienthue);
        Button btn_huy = view.findViewById(R.id.btn_huy);
        Button btn_them = view.findViewById(R.id.btn_themphieumuon);
        getDataSpiner(spin_thanhvien);
        getDataSpinerSach(spin_sach);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy mã thành viên khi người dùng chọn
                HashMap<String,Object> hsTV = (HashMap<String, Object>) spin_thanhvien.getSelectedItem();
                int matv = (int) hsTV.get("matv");
                //lấy mã sách
                HashMap<String,Object> hsSach = (HashMap<String, Object>) spin_sach.getSelectedItem();
                int masach = (int) hsSach.get("masach");

                int tien = Integer.parseInt(edt_tien.getText().toString());
                themPhieumuon(matv,masach,tien);
                bottomSheetDialog.dismiss();

            }

        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

    }
    private void themPhieumuon(int matv, int masach, int tien) {
        // lấy mã thủ thư
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt","");

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        Phieumuon phieumuon = new Phieumuon(matv,matt,masach,ngay,0,tien);
        
        boolean check= phieumuonDAO.themPhieumuon(phieumuon);
        if (check){
            Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
            loadData();
        } else {
            Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void getDataSpiner(Spinner spinnerThanhvien){
        ThanhvienDAO thanhvienDAO = new ThanhvienDAO(getContext());
        ArrayList<Thanhvien> list_TV = thanhvienDAO.dsThanhvien(); // dùng Arraylist để hứng lấy dữ liệu từ Thanhvien
        ArrayList<HashMap<String,Object>> list_hashMap = new ArrayList<>();
        //dùng for để duyệt thông tin thành viên;
        for (Thanhvien thanhvien : list_TV) {
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("matv",thanhvien.getMatv());
            hs.put("hoten",thanhvien.getHoten());
            list_hashMap.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),list_hashMap,
                android.R.layout.simple_list_item_1,new String[]{"hoten"},
                new int[]{android.R.id.text1});
        spinnerThanhvien.setAdapter(simpleAdapter);
    }
    private void getDataSpinerSach(Spinner spinerSach){
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list_Sach = sachDAO.dsSach(); // dùng Arraylist để hứng lấy dữ liệu từ Thanhvien
        ArrayList<HashMap<String,Object>> list_hashMap = new ArrayList<>();
        //dùng for để duyệt thông tin thành viên;
        for (Sach sach : list_Sach) {
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("masach",sach.getMasach());
            hs.put("tensach",sach.getTensach());
            list_hashMap.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),list_hashMap,
                android.R.layout.simple_list_item_1,new String[]{"tensach"},
                new int[]{android.R.id.text1});
        spinerSach.setAdapter(simpleAdapter);

    }

    private void loadData() {
        phieumuonDAO = new PhieumuonDAO(getContext());
        list = phieumuonDAO.dsPhieumuon();
        adapter = new PhieumuonAdapter(list,getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}