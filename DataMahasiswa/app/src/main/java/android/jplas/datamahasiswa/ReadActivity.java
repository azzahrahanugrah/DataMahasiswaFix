package android.jplas.datamahasiswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.jplas.datamahasiswa.Model.Mahasiswa;
import android.jplas.datamahasiswa.Model.MahasiswaViewHolder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity implements MahasiswaViewHolder.dataListener {
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Mahasiswa> daftarMahasiswa;
//    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        rvView = (RecyclerView) findViewById(R.id.rvdata);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
//        getSupportActionBar().setTitle("Data Mahasiswa");
//        auth = FirebaseAuth.getInstance();
//        MyRecyclerView();
//        GetData();



        database = FirebaseDatabase.getInstance().getReference();

        database.child("datamhs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftarMahasiswa = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object Barang
                     * Dan juga menyimpan primary key pada object Barang
                     * untuk keperluan Edit dan Delete data
                     */
                    Mahasiswa barang = noteDataSnapshot.getValue(Mahasiswa.class);
                    barang.setUid(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object Barang yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarMahasiswa.add(barang);
                }
                adapter = new MahasiswaViewHolder(daftarMahasiswa, ReadActivity.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());

            }
        });

    }

    @Override
    public void onDeleteData(Mahasiswa data, int position) {
//        String userID = auth.getUid();
        if(database != null){
            database.child("datamhs")
                    .child(data.getUid())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(ReadActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
//
//    private void GetData() {
//        Toast.makeText(getApplicationContext(),"Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show();
//        database = FirebaseDatabase.getInstance().getReference();
//        database.child("datamhs").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Inisialisasi ArrayList
//                daftarMahasiswa = new ArrayList<>();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    //Mapping data pada DataSnapshot ke dalam objek mahasiswa
//                    Mahasiswa mahasiswa = snapshot.getValue(Mahasiswa.class);
//
//                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
//                    mahasiswa.setUid(snapshot.getKey());
//                    daftarMahasiswa.add(mahasiswa);
//                }
//
//                //Inisialisasi Adapter dan data Mahasiswa dalam bentuk Array
//                adapter = new MahasiswaViewHolder(daftarMahasiswa, ReadActivity.this);
//
//                //Memasang Adapter pada RecyclerView
//                rvView.setAdapter(adapter);
//
//                Toast.makeText(getApplicationContext(),"Data Berhasil Dimuat", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//              /*
//                Kode ini akan dijalankan ketika ada error dan
//                pengambilan data error tersebut lalu memprint error nya
//                ke LogCat
//               */
//                Toast.makeText(getApplicationContext(),"Data Gagal Dimuat", Toast.LENGTH_LONG).show();
//                Log.e("MyListActivity", databaseError.getDetails()+" "+databaseError.getMessage());
//            }
//        });
//        }
//
//    private void MyRecyclerView() {
//        layoutManager = new LinearLayoutManager(this);
//        rvView.setLayoutManager(layoutManager);
//        rvView.setHasFixedSize(true);
//
//        //Membuat Underline pada Setiap Item Didalam List
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
//        rvView.addItemDecoration(itemDecoration);
//    }
}
