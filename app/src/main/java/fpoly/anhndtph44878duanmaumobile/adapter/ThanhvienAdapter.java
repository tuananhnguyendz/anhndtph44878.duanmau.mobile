package fpoly.anhndtph44878duanmaumobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import fpoly.anhndtph44878duanmaumobile.R;
import fpoly.anhndtph44878duanmaumobile.dao.ThanhvienDAO;
import fpoly.anhndtph44878duanmaumobile.dao.TheloaisachDAO;
import fpoly.anhndtph44878duanmaumobile.model.Thanhvien;

public class ThanhvienAdapter extends RecyclerView.Adapter<ThanhvienAdapter.viewHolder> implements Filterable {
    ArrayList<Thanhvien> list_TV;
    ArrayList<Thanhvien> list_backUp;
    Context context;
    ThanhvienDAO dao;

    public ThanhvienAdapter(ArrayList<Thanhvien> list_TV, Context context, ThanhvienDAO dao) {
        this.list_TV = list_TV;
        this.list_backUp = list_TV;
        this.context = context;
        this.dao = dao;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_idTV.setText("ID:"+String.valueOf(list_TV.get(position).getMatv()));
        holder.txt_nameTV.setText(list_TV.get(position).getHoten());
        holder.txt_namsinhTV.setText(list_TV.get(position).getNamsinh());


        holder.img_deleteTV.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Xác nhận");
            builder.setMessage("Bạn có muốn xoá thành viên này không ?");
            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int matv = list_TV.get(position).getMatv();
                    dao = new ThanhvienDAO(holder.itemView.getContext());
                    dao.deleteTV(matv);
                    list_TV.remove(position);
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

        holder.img_editTV.setOnClickListener(v -> {
            capnhatTV(position);
        });

    }

    private void capnhatTV(int position) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_bottomsheet_capnhatthanhvien,null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        EditText edt_maTV = view.findViewById(R.id.edt_matv);
        EditText edt_tenTV = view.findViewById(R.id.edt_tenTV);
        EditText edt_nsTV = view.findViewById(R.id.edt_namsinhTV02);
        Button btn_capnhatTV = view.findViewById(R.id.btn_capnhatTV);
        Button btn_huyCapnhat = view.findViewById(R.id.btn_huyCapnhat);

        edt_maTV.setText(String.valueOf(list_TV.get(position).getMatv()));
        edt_tenTV.setText(list_TV.get(position).getHoten());
        edt_nsTV.setText(list_TV.get(position).getNamsinh());


        btn_capnhatTV.setOnClickListener(v -> {
            String matv = edt_maTV.getText().toString();
            String hoten = edt_tenTV.getText().toString();
            String namsinh = edt_nsTV.getText().toString();
            boolean check = dao.updateTV(Integer.parseInt(matv),hoten,namsinh);
            if (check){
                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
            list_TV.clear();
            list_TV = dao.dsThanhvien();
            notifyDataSetChanged();
            bottomSheetDialog.dismiss();
        });
        btn_huyCapnhat.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return list_TV.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();

                //lọc dữ liệu phù hợp vs key
                if (key.isEmpty()){ //khi chưa điền dữ liệu thì nó bằng dữ liệu ban đầu
                    list_TV = list_backUp;
                } else {
                    List<Thanhvien> list = new ArrayList<>();
                    for (Thanhvien thanhvien : list_backUp){
                        if (thanhvien.getHoten().toLowerCase().contains(key.toLowerCase())){
                            list.add(thanhvien);
                        }
                    }
                    list_TV = (ArrayList<Thanhvien>) list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list_TV;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
            list_TV = (ArrayList<Thanhvien>) results.values;
            notifyDataSetChanged();
            }
        };
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_idTV, txt_nameTV, txt_namsinhTV;
        ImageView img_deleteTV, img_editTV;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_idTV = itemView.findViewById(R.id.txt_IDthanhvien);
            txt_nameTV = itemView.findViewById(R.id.txt_nameTV);
            txt_namsinhTV = itemView.findViewById(R.id.txt_namsinhTV);
            img_deleteTV = itemView.findViewById(R.id.img_deletethanhvien);
            img_editTV = itemView.findViewById(R.id.img_editthanhvien);
        }
    }
}
