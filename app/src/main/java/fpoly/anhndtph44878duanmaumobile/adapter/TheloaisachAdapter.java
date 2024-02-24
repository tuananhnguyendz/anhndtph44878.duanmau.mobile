package fpoly.anhndtph44878duanmaumobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.dao.TheloaisachDAO;
import fpoly.anhndtph44878duanmaumobile.model.Loaisach;

public class TheloaisachAdapter extends RecyclerView.Adapter<TheloaisachAdapter.viewHolder> {
    ArrayList<Loaisach> list_LSACH;
    Context context;
    TheloaisachDAO theloaisachDAO;

    public TheloaisachAdapter(ArrayList<Loaisach> list_LSACH, Context context, TheloaisachDAO theloaisachDAO) {
        this.list_LSACH = list_LSACH;
        this.context = context;
        this.theloaisachDAO = theloaisachDAO;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_maloaisach.setText("Mã loại :" + String.valueOf(list_LSACH.get(position).getMaloaisach()));
        holder.txt_tenloaisach.setText("Tên loại :" + list_LSACH.get(position).getTenloaisach());

        holder.img_deleteloaisach.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Xác nhận");
            builder.setMessage("Bạn có muốn xoá thể loại này không ?");
            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int maloaisach = list_LSACH.get(position).getMaloaisach();
                    theloaisachDAO = new TheloaisachDAO(holder.itemView.getContext());
                    theloaisachDAO.deleteLoaisach(maloaisach);
                    list_LSACH.remove(position);
                    notifyItemRemoved(position);

                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        holder.txt_chinhsua.setOnClickListener(v -> {
            capnhatLoaiSach(position);
        });
    }

    public void capnhatLoaiSach(int position) {
        TextInputEditText edtMaloai, edtTenloai;
        Button btnHuy, btnCapnhat;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_bottomsheet_capnhatloaisach, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        edtMaloai = view.findViewById(R.id.edt_maloaisach);
        edtTenloai = view.findViewById(R.id.edt_tenloaisach02);
        btnHuy = view.findViewById(R.id.btn_huyLS);
        btnCapnhat = view.findViewById(R.id.btn_sualoaisach);

        edtMaloai.setText(String.valueOf(list_LSACH.get(position).getMaloaisach()));
        edtTenloai.setText(list_LSACH.get(position).getTenloaisach());

        btnCapnhat.setOnClickListener(v -> {
            String idloaisach = edtMaloai.getText().toString();
            String tenLSACH = edtTenloai.getText().toString();

            boolean check = theloaisachDAO.suaLoaisach(Integer.parseInt(idloaisach), tenLSACH);
            list_LSACH.clear();
            list_LSACH = theloaisachDAO.dsLoaiSach();
            notifyDataSetChanged();
            bottomSheetDialog.dismiss();
        });
        btnHuy.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

    }

    @Override
    public int getItemCount() {
        return list_LSACH.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_maloaisach, txt_tenloaisach, txt_chinhsua;
        ImageView img_deleteloaisach;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_maloaisach = itemView.findViewById(R.id.txt_matheloai);
            txt_tenloaisach = itemView.findViewById(R.id.txt_tentheloai);
            txt_chinhsua = itemView.findViewById(R.id.txt_update_lsach);
            img_deleteloaisach = itemView.findViewById(R.id.img_deleteLsach);
        }
    }
}
