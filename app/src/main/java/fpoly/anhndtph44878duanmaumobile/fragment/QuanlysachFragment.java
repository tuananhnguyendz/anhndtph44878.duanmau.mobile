package fpoly.anhndtph44878duanmaumobile.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.adapter.SachAdapter;
import fpoly.anhndtph44878duanmaumobile.adapter.TheloaisachAdapter;
import fpoly.anhndtph44878duanmaumobile.dao.SachDAO;
import fpoly.anhndtph44878duanmaumobile.dao.TheloaisachDAO;
import fpoly.anhndtph44878duanmaumobile.model.Sach;


public class QuanlysachFragment extends Fragment {
    RecyclerView recyclerView;
    SachDAO sachDAO;
    ArrayList<Sach> list;
    LinearLayoutManager linearLayoutManager;
    SachAdapter adapter;
    Button btn_themsach;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quanlysach, container, false);
        recyclerView = view.findViewById(R.id.recycler_sach);
        btn_themsach = view.findViewById(R.id.btn_themsach);

        btn_themsach.setOnClickListener(v -> {
            themSach();
        });

        loadData();
        return view;
    }

    private void themSach() {
        TextInputEditText edt_tensach, edt_giathuesach, edt_tenloaisach;
        Button btn_huySach, btn_themSach;
        
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_bottomsheet_themsach, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        
        edt_tensach = view.findViewById(R.id.edt_tensach);
        edt_giathuesach = view.findViewById(R.id.edt_giathue);
        edt_tenloaisach = view.findViewById(R.id.edt_tenloaisach_sach);
        
        btn_themSach = view.findViewById(R.id.btn_themtensach);
        btn_huySach = view.findViewById(R.id.btn_huySach);
        
        btn_themSach.setOnClickListener(v -> {
            String tensach = edt_tensach.getText().toString();
            String giathuesach = edt_giathuesach.getText().toString();
            String maloai = edt_tenloaisach.getText().toString();
            if (tensach.isEmpty()||giathuesach.isEmpty()||maloai.isEmpty()){
                Toast.makeText(getContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
            }else {
              boolean check =  sachDAO.addSach(tensach,Integer.parseInt(giathuesach),Integer.parseInt(maloai));
              loadData();
              if (check){
                  Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
              } else {
                  Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
              }
            }
            bottomSheetDialog.dismiss();
        });

        btn_huySach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

    }

    private void loadData() {
        sachDAO = new SachDAO(getContext());
        list = sachDAO.dsSach();
        adapter = new SachAdapter(list, getContext(),sachDAO);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}