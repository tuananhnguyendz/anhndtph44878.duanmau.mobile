package fpoly.anhndtph44878duanmaumobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.dao.ThuthuDAO;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edtUser, edtPassword;
    TextView txtSignUp;
    Button btnLogin;
    ThuthuDAO thuthuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnLogin.setOnClickListener(v -> {
            login();
        });
        txtSignUp.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });

    }

    private void login() {
        //lay du lieu nguoi dung
        String userName = edtUser.getText().toString();
        String passWord = edtPassword.getText().toString();

//        if (passWord.length()<=5){
//            Toast.makeText(this, "Mật khẩu nhâp tối thiểu trên 5 kí tự ", Toast.LENGTH_SHORT).show();
//        } else if (userName.isEmpty()|| passWord.isEmpty()) {
//            Toast.makeText(this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
//            if (userName.isEmpty()){
//                edtUser.setError("Vui lòng nhập tên");
//            }
//            if (passWord.isEmpty()){
//                edtPassword.setError("Vui lòng nhập mật khẩu");
//            }
//        } else {
//            boolean check = thuthuDAO.checkLogin(userName,passWord);
//            if (check){
//                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            } else {
//                Toast.makeText(this, "Đăng nhập thất bại. Xin vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
//            }
//        }
        if (thuthuDAO.checkLogin(userName, passWord)) {
            SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("matt", userName);
            editor.commit();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

        } else {
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        edtUser = findViewById(R.id.edt_user);
        edtPassword = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btn_login);
        txtSignUp = findViewById(R.id.txt_signup);
        thuthuDAO = new ThuthuDAO(this);
    }
}