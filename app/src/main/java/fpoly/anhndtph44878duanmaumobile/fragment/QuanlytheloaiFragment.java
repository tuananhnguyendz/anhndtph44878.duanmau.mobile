package fpoly.anhndtph44878duanmaumobile.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.adapter.TheloaisachAdapter;
import fpoly.anhndtph44878duanmaumobile.dao.TheloaisachDAO;
import fpoly.anhndtph44878duanmaumobile.model.Loaisach;


public class QuanlytheloaiFragment extends Fragment {
    RecyclerView recyclerView;
    Button btn_themloaisach;
    TheloaisachAdapter adapter;
    TheloaisachDAO dao;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Loaisach> list;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quanlytheloai, container, false);
        recyclerView = view.findViewById(R.id.recycler_theloai);
        btn_themloaisach= view.findViewById(R.id.btn_themloaisach);

        btn_themloaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themloaisach();
            }
        });

        loadData();
        return view;
    }
    private void themloaisach() {
        View view = getLayoutInflater().inflate(R.layout.layout_bottomsheet_themloaisach,null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        TextInputEditText edtTenloaisach = view.findViewById(R.id.edt_tenloaisach);
        Button btnHuyloaisach = view.findViewById(R.id.btn_huyLS);
        Button btnThemloaisach = view.findViewById(R.id.btn_themloaisach);

        btnThemloaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLSach = edtTenloaisach.getText().toString();
                if (tenLSach.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = dao.themLoaisach(tenLSach);
                    loadData();
                    if (check){
                        Toast.makeText(getContext(), "Thêm loại sách thành công ", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm loại sách thất bại ", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        btnHuyloaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void loadData() {
        dao = new TheloaisachDAO(getContext());
        list = dao.dsLoaiSach();
        adapter = new TheloaisachAdapter(list,getContext(),dao);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}