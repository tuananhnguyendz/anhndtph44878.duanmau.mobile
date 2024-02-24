package fpoly.anhndtph44878duanmaumobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.dao.PhieumuonDAO;
import fpoly.anhndtph44878duanmaumobile.model.Phieumuon;

public class PhieumuonAdapter extends RecyclerView.Adapter<PhieumuonAdapter.viewHolder> {
    ArrayList<Phieumuon> listPM;
    Context context;

    public PhieumuonAdapter(ArrayList<Phieumuon> listPM, Context context) {
        this.listPM = listPM;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent, false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_mapm.setText("Mã phiếu mượn :" + String.valueOf(listPM.get(position).getMapm()));
        holder.txt_matv.setText("Mã thành viên :" + String.valueOf(listPM.get(position).getMatv()));
        holder.txt_hoten.setText("Họ tên :" + listPM.get(position).getHoten());
        holder.txt_matt.setText("Mã thủ thư :" + listPM.get(position).getMatt());
        holder.txt_hotentt.setText("Họ tên thủ thư :" + listPM.get(position).getHotentt());
        holder.txt_masach.setText("Mã sách :" + String.valueOf(listPM.get(position).getMasach()));
        holder.txt_tensach.setText("Tên sách :" + listPM.get(position).getTensach());
        holder.txt_ngay.setText("Ngày :" + listPM.get(position).getNgay());

        String trangthai = "";
        if (listPM.get(position).getTrasach() == 1) {
            trangthai = "Đã trả sách";
            holder.btn_trasach.setVisibility(View.GONE);
        } else {
            trangthai = "Chưa trả sách";
            holder.btn_trasach.setVisibility(View.VISIBLE);
        }
        holder.txt_trangthai.setText("Trạng thái :" + trangthai);
        holder.txt_tienthue.setText("Tiền thuê :" + String.valueOf(listPM.get(position).getTienthue()));
        holder.btn_trasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieumuonDAO phieumuonDAO = new PhieumuonDAO(context);
                boolean check = phieumuonDAO.thaydoiTrangthai(listPM.get(holder.getAdapterPosition()).getMapm());
                if (check) {
                    listPM.clear();
                    listPM = phieumuonDAO.dsPhieumuon();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thay đổi trạng thái thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Thay đổi trạng thái ko thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mapm = listPM.get(position).getMapm();
                PhieumuonDAO phieumuonDAO = new PhieumuonDAO(holder.itemView.getContext());
                phieumuonDAO.xoaPhieumuon(mapm);
                listPM.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPM.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView txt_mapm, txt_matv, txt_hoten, txt_matt, txt_hotentt, txt_masach, txt_tensach, txt_ngay, txt_trangthai, txt_tienthue;
        Button btn_trasach;
        ImageView imgDelete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_mapm = itemView.findViewById(R.id.txt_mapm);
            txt_matv = itemView.findViewById(R.id.txt_matv);
            txt_hoten = itemView.findViewById(R.id.txt_hoten);
            txt_matt = itemView.findViewById(R.id.txt_matt);
            txt_hotentt = itemView.findViewById(R.id.txt_hotentt);
            txt_masach = itemView.findViewById(R.id.txt_masach);
            txt_tensach = itemView.findViewById(R.id.txt_tensach);
            txt_trangthai = itemView.findViewById(R.id.txt_trangthai);
            txt_ngay = itemView.findViewById(R.id.txt_ngay);
            txt_tienthue = itemView.findViewById(R.id.txt_tienthue);
            btn_trasach = itemView.findViewById(R.id.btn_trasach);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }
    }
}
