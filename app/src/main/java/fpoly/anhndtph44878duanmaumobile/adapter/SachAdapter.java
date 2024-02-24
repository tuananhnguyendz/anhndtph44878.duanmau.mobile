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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.dao.SachDAO;
import fpoly.anhndtph44878duanmaumobile.dao.TheloaisachDAO;
import fpoly.anhndtph44878duanmaumobile.model.Sach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.viewHolder> {
    ArrayList<Sach> list_SACH;
    Context context;
    SachDAO dao;

    public SachAdapter(ArrayList<Sach> list_SACH, Context context, SachDAO dao) {
        this.list_SACH = list_SACH;
        this.context = context;
        this.dao = dao;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtIDS.setText("ID :" + list_SACH.get(position).getMasach());
        holder.txtTenSach.setText(list_SACH.get(position).getTensach());
        holder.txtGiaSach.setText(String.valueOf(list_SACH.get(position).getGiathue()));
        holder.txtMaLoai.setText(String.valueOf(list_SACH.get(position).getMaloai()));
        holder.txtTenloai.setText(list_SACH.get(position).getTenloai());

        holder.img_deleteSach.setOnClickListener(v -> {


            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Xác nhận");
            builder.setMessage("Bạn có muốn xoá sách này không ?");
            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int masach = list_SACH.get(position).getMasach();
                    dao = new SachDAO(holder.itemView.getContext());
                    dao.deleteSach(masach);
                    list_SACH.remove(position);
                    notifyItemRemoved(position);
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
        });

        holder.img_capnhatSach.setOnClickListener(v -> {
            capNhatsachDiagLog(position);
        });

    }

    private void capNhatsachDiagLog(int position) {
        TextInputEditText edt_idsach, edt_tensach, edt_giathue, edt_maloaisach, edt_tenloaisach;
        Button btn_suasach, btn_huysach;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_bottomsheet_suasach, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        edt_idsach = view.findViewById(R.id.edt_idSach);
        edt_tensach = view.findViewById(R.id.edt_tensach);
        edt_giathue = view.findViewById(R.id.edt_giathue);
        edt_maloaisach = view.findViewById(R.id.edt_maloaisach02);
        edt_tenloaisach = view.findViewById(R.id.edt_tenloaisach_sach);
        btn_suasach = view.findViewById(R.id.btn_suatensach);
        btn_huysach = view.findViewById(R.id.btn_huySach);

        edt_idsach.setText(String.valueOf(list_SACH.get(position).getMasach()));
        edt_tensach.setText(list_SACH.get(position).getTensach());
        edt_giathue.setText(String.valueOf(list_SACH.get(position).getGiathue()));
        edt_tenloaisach.setText(list_SACH.get(position).getTenloai());
        edt_maloaisach.setText(String.valueOf(list_SACH.get(position).getMaloai()));

        btn_suasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idsach = edt_idsach.getText().toString();
                String tensach = edt_tensach.getText().toString();
                String giathue = edt_giathue.getText().toString();
//                String tenloaisach = edt_tenloaisach.getText().toString();
                String maloaisach = edt_maloaisach.getText().toString();


                boolean check = dao.updataSach(Integer.parseInt(idsach),tensach,Integer.parseInt(giathue),Integer.parseInt(maloaisach));
                if (check) {
                    Toast.makeText(context, "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập nhật sách thất bại", Toast.LENGTH_SHORT).show();
                }
                list_SACH.clear();
                list_SACH = dao.dsSach();
                notifyDataSetChanged();
                bottomSheetDialog.dismiss();
            }
        });
        btn_huysach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list_SACH.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtIDS, txtTenSach, txtGiaSach, txtMaLoai, txtTenloai;
        ImageView img_deleteSach, img_capnhatSach;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtIDS = itemView.findViewById(R.id.txt_idSach);
            txtTenSach = itemView.findViewById(R.id.txt_nameSach);
            txtGiaSach = itemView.findViewById(R.id.txt_maloaigiathue);
            txtMaLoai = itemView.findViewById(R.id.txt_maloai);
            txtTenloai = itemView.findViewById(R.id.txt_tenloaiSach);
            img_deleteSach = itemView.findViewById(R.id.img_deleteSach);
            img_capnhatSach = itemView.findViewById(R.id.img_editSach);
        }
    }
}
