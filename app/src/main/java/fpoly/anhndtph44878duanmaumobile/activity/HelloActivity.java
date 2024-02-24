package fpoly.anhndtph44878duanmaumobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import fpoly.anhndtph44878duanmaumobile.R;

public class HelloActivity extends AppCompatActivity {
    TextView txt_hello;
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        txt_hello = findViewById(R.id.txt_hello);
        lottieAnimationView = findViewById(R.id.lottie);

        txt_hello.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        lottieAnimationView.animate().translationX(2000).setDuration(2000).setStartDelay(2900);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(HelloActivity.this, LoginActivity.class));

            }
        }.start();
    }
}