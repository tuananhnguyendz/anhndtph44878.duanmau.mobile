package fpoly.anhndtph44878duanmaumobile.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.adapter.Top10muonsachAdapter;
import fpoly.anhndtph44878duanmaumobile.dao.Top10sachmuonDAO;
import fpoly.anhndtph44878duanmaumobile.model.Sach;

public class TopsachmuonFragment extends Fragment {
    RecyclerView recyclerView;
    Top10sachmuonDAO top10sachmuonDAO;
    ArrayList<Sach> list_top10;
    Top10muonsachAdapter adapter;
    LinearLayoutManager linearLayoutManager;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topsachmuon, container, false);
        recyclerView = view.findViewById(R.id.recycler_02);
        loadData();

        return view;
    }

    private void loadData() {
        top10sachmuonDAO = new Top10sachmuonDAO(getContext()); // Khởi tạo đối tượng top10sachmuonDAO
        list_top10 = top10sachmuonDAO.top10sach(); // Gọi phương thức top10sach() sau khi đối tượng được khởi tạo
        adapter = new Top10muonsachAdapter(list_top10, getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}