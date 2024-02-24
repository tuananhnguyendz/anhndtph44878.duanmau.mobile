package fpoly.anhndtph44878duanmaumobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.fragment.DoanhthuFragment;
import fpoly.anhndtph44878duanmaumobile.fragment.DoimatkhauFragment;
import fpoly.anhndtph44878duanmaumobile.fragment.QuanlyphieumuonFragment;
import fpoly.anhndtph44878duanmaumobile.fragment.QuanlysachFragment;
import fpoly.anhndtph44878duanmaumobile.fragment.QuanlythanhvienFragment;
import fpoly.anhndtph44878duanmaumobile.fragment.QuanlytheloaiFragment;
import fpoly.anhndtph44878duanmaumobile.fragment.TopsachmuonFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar mtoolbar;
    NavigationView navigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        mtoolbar = findViewById(R.id.mToolbar);
        navigationView = findViewById(R.id.navigation_nav);
        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("LIBARY");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new QuanlysachFragment()).commit();
    }

    @Override // set toolbar
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        if (item.getItemId() == R.id.qly_phieumuon) {
            fragment = new QuanlyphieumuonFragment();
        } else if (item.getItemId() == R.id.qly_theloaisach) {
            fragment = new QuanlytheloaiFragment();
        } else if (item.getItemId() == R.id.qly_sach) {
            fragment = new QuanlysachFragment();
        } else if (item.getItemId() == R.id.qly_thanhvien) {
            fragment = new QuanlythanhvienFragment();
        } else if (item.getItemId() == R.id.topmuonsach) {
            fragment = new TopsachmuonFragment();
        } else if (item.getItemId() == R.id.doanhthu) {
            fragment = new DoanhthuFragment();
        } else if (item.getItemId() == R.id.doimatkhau) {
            fragment = new DoimatkhauFragment();
        } else if (item.getItemId() == R.id.logOut) {
            diaglogLogOut();
            return true;
        } else {
            fragment = new QuanlysachFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();

        getSupportActionBar().setTitle(item.getTitle());
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void logOut() {
        Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
    }

    private void outLogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void diaglogLogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn đăng xuất không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logOut();
                outLogin();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}