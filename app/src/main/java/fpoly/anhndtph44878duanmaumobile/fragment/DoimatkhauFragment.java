package fpoly.anhndtph44878duanmaumobile.fragment;

import static java.lang.String.valueOf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.activity.LoginActivity;
import fpoly.anhndtph44878duanmaumobile.dao.ThuthuDAO;

public class DoimatkhauFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doimatkhau, container, false);
        EditText edt_oldpass = view.findViewById(R.id.edt_oldPass);
        EditText edt_newPass = view.findViewById(R.id.edt_newPass);
        EditText edt_repass = view.findViewById(R.id.edt_rePass);
        Button btn_doiMK = view.findViewById(R.id.btn_doimk);
        btn_doiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass =edt_oldpass.getText().toString();
                String newPass =edt_newPass.getText().toString();
                String rePass =edt_repass.getText().toString();
                if (oldPass.isEmpty()||newPass.isEmpty()||rePass.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng không để trống ", Toast.LENGTH_SHORT).show();
                } else {
                    if (!newPass.equals(rePass)){
                        Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                        String mtt = sharedPreferences.getString("matt","");
                        ThuthuDAO thuthuDAO = new ThuthuDAO(getContext());
                        boolean check = thuthuDAO.doiMatkhau(mtt,oldPass,newPass);
                        if (check){
                            Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                          getActivity().startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        return view;
    }

}