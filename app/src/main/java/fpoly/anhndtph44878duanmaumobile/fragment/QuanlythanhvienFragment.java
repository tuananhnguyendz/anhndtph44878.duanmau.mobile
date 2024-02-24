package fpoly.anhndtph44878duanmaumobile.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.adapter.ThanhvienAdapter;
import fpoly.anhndtph44878duanmaumobile.dao.ThanhvienDAO;
import fpoly.anhndtph44878duanmaumobile.model.Thanhvien;


public class QuanlythanhvienFragment extends Fragment {
    RecyclerView recyclerView;
    ThanhvienDAO dao;
    ArrayList<Thanhvien> list_TV;
    LinearLayoutManager linearLayoutManager;
    Button btn_themTV;
    ThanhvienAdapter adapter;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quanlythanhvien, container, false);
        recyclerView = view.findViewById(R.id.recycler_thanhvien);
        btn_themTV = view.findViewById(R.id.btn_themTV);
        btn_themTV.setOnClickListener(v -> {
            themThanhvien();
        });
        loadData();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void themThanhvien() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_bottomsheet_themthanvien, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        TextInputEditText edt_tenTV = view.findViewById(R.id.edt_tenTV);
        TextInputEditText edt_namSinh = view.findViewById(R.id.edt_namsinhTV);
        Button btn_themTV = view.findViewById(R.id.btn_themTV);
        Button btn_huyTV = view.findViewById(R.id.btn_huyTV);

        btn_themTV.setOnClickListener(v -> {
            String tenTV = edt_tenTV.getText().toString();
            String namsinhTV = edt_namSinh.getText().toString();
            if (tenTV.isEmpty() || namsinhTV.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
            } else {
                boolean check = dao.addThanhvien(tenTV, namsinhTV);
                loadData();
                if (check) {
                    Toast.makeText(getContext(), "Thêm thành viên thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm thành viên thất bại", Toast.LENGTH_SHORT).show();

                }


            }
            bottomSheetDialog.dismiss();
        });
    }

    private void loadData() {
        dao = new ThanhvienDAO(getContext());
        list_TV = dao.dsThanhvien();
        adapter = new ThanhvienAdapter(list_TV, getContext(), dao);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);

        // Lấy item SearchView từ menu
        MenuItem searchItem = menu.findItem(R.id.search_thanhvien);

        searchView = (SearchView) searchItem.getActionView();

        // Thiết lập listener cho SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lý sự kiện khi người dùng nhấn nút tìm kiếm trên bàn phím
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý sự kiện khi người dùng nhập/chỉnh sửa nội dung tìm kiếm
//                filterThanhVien(newText);
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}