package android.jplas.datamahasiswa.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.jplas.datamahasiswa.R;
import android.jplas.datamahasiswa.ReadActivity;
import android.jplas.datamahasiswa.UpdateActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MahasiswaViewHolder extends RecyclerView.Adapter<MahasiswaViewHolder.ViewHolder> {
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private Context context;

//    public TextView btnOpen;

    public MahasiswaViewHolder(ArrayList<Mahasiswa> mahasiswas, Context ctx) {
        daftarMahasiswa = mahasiswas;
        context = ctx;
        listener = (ReadActivity)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdata, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String nim = daftarMahasiswa.get(position).getNIM();
        final String name = daftarMahasiswa.get(position).getNama();
        final String alamt = daftarMahasiswa.get(position).getAlamat();

//        holder.tvNIM.setText("NIM :"+nim);
//        holder.tvNama.setText("Nama :"+name);
//        holder.tvAlamat.setText("Alamat :"+alamt);

        holder.tvNIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder.tvNIM.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String[] action = {"Update", "Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                        /*
                          Berpindah Activity pada halaman layout updateData
                          dan mengambil data pada listMahasiswa, berdasarkan posisinya
                          untuk dikirim pada activity selanjutnya
                        */
                                Bundle bundle = new Bundle();
                                bundle.putString("dataNIM", daftarMahasiswa.get(position).getNIM());
                                bundle.putString("dataNama", daftarMahasiswa.get(position).getNama());
                                bundle.putString("dataAlamat", daftarMahasiswa.get(position).getAlamat());
                                bundle.putString("getPrimaryKey", daftarMahasiswa.get(position).getUid());
                                Intent intent = new Intent(view.getContext(), UpdateActivity.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;
                            case 1:
                                listener.onDeleteData(daftarMahasiswa.get(position), position);
                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });
        holder.tvNIM.setText(nim);
        holder.tvNama.setText(name);
        holder.tvAlamat.setText(alamt);
    }

    @Override
    public int getItemCount() {
        return daftarMahasiswa.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNIM;
        TextView tvNama;
        TextView tvAlamat;
//        LinearLayout listItem;

        ViewHolder(View v){
            super(v);
            tvNIM = v.findViewById(R.id.getNIM);
            tvNama = v.findViewById(R.id.getNAMA);
            tvAlamat = v.findViewById(R.id.getAlamat);
//            listItem = v.findViewById(R.id.rvdata);
        }


    }

    public interface dataListener{
        void onDeleteData(Mahasiswa data, int position);
    }

    dataListener listener;

//    public void bindToPerusahaan(Mahasiswa mahasiswa, View.OnClickListener onClickListener){
//        tvNIM.setText(mahasiswa.getNIM());
//        tvNama.setText(mahasiswa.getNama());
//        tvAlamat.setText(mahasiswa.getAlamat());
//    }
}
