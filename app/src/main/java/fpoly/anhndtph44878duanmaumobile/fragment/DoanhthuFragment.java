package fpoly.anhndtph44878duanmaumobile.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.dao.PhieumuonDAO;
import fpoly.anhndtph44878duanmaumobile.dao.Top10sachmuonDAO;

public class DoanhthuFragment extends Fragment {

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doanhthu, container, false);
        TextInputEditText  edt_ngaybatdau = view.findViewById(R.id.edt_start);
        TextInputEditText  edt_ngayketthuc = view.findViewById(R.id.edt_end);
        Button   btn_doanhthu = view.findViewById(R.id.btn_doanhthu);
        TextView txt_ketqua = view.findViewById(R.id.txt_ketquathongke);
        Button  btn_clear = view.findViewById(R.id.btn_clear);

        Calendar calendar = Calendar.getInstance();
        edt_ngaybatdau.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String ngay = "";
                    String thang = "";
                    if (dayOfMonth < 10) {
                        ngay = "0" + dayOfMonth;
                    } else {
                        ngay = String.valueOf(dayOfMonth);
                    }
                    if ((month + 1) < 10) {
                        thang = "0" + (month + 1);
                    } else {
                        thang = String.valueOf((month + 1));
                    }
                    edt_ngaybatdau.setText(year + "/" + thang + "/" + ngay);
                }
            },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        edt_ngayketthuc.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String ngay = "";
                    String thang = "";
                    if (dayOfMonth < 10) {
                        ngay = "0" + dayOfMonth;
                    } else {
                        ngay = String.valueOf(dayOfMonth);
                    }
                    if ((month + 1) < 10) {
                        thang = "0" + (month + 1);
                    } else {
                        thang = String.valueOf((month + 1));
                    }
                    edt_ngayketthuc.setText(year + "/" + thang + "/" + ngay);
                }
            },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        btn_doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieumuonDAO phieumuonDAO = new PhieumuonDAO(getContext());
                String ngaybatdau = edt_ngaybatdau.getText().toString();
                String ngayketthuc = edt_ngayketthuc.getText().toString();


                int doanhthu = phieumuonDAO.getDoanhthu(ngaybatdau, ngayketthuc);
                txt_ketqua.setText(String.valueOf(doanhthu + "VNĐ"));
            }
        });


        btn_clear.setOnClickListener(v -> {
            txt_ketqua.setText("");
        });
        return view;
    }
}
