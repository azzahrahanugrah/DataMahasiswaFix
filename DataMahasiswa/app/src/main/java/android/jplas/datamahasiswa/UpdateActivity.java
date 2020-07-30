package android.jplas.datamahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.jplas.datamahasiswa.Model.Mahasiswa;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nimBaru, namaBaru, alamatBaru;
    private Button update;
    private DatabaseReference database;
//    private FirebaseAuth auth;
    private String cekNIM, cekNama, cekAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setTitle("Update Data");

        nimBaru = findViewById(R.id.ednim);
        namaBaru = findViewById(R.id.ednama);
        alamatBaru = findViewById(R.id.edalamat);

        update = findViewById(R.id.edit);
        update.setOnClickListener(this);

        database = FirebaseDatabase.getInstance().getReference();
        getData();
    }

    private void getData() {
        final String getNIM = getIntent().getExtras().getString("dataNIM");
        final String getNama = getIntent().getExtras().getString("dataNama");
        final String getJurusan = getIntent().getExtras().getString("dataAlamat");
        nimBaru.setText(getNIM);
        namaBaru.setText(getNama);
        alamatBaru.setText(getJurusan);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit:
                cekNIM = nimBaru.getText().toString();
                cekNama = namaBaru.getText().toString();
                cekAlamat = alamatBaru.getText().toString();

                Mahasiswa setMahasiswa = new Mahasiswa();
                setMahasiswa.setNIM(nimBaru.getText().toString());
                setMahasiswa.setNama(namaBaru.getText().toString());
                setMahasiswa.setAlamat(alamatBaru.getText().toString());
                updateMahasiswa(setMahasiswa);

                Intent ubah = new Intent(UpdateActivity.this, ReadActivity.class);
                startActivity(ubah);

                break;
        }
    }

    private void updateMahasiswa(Mahasiswa setMahasiswa) {
//        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("datamhs").child(getKey).setValue(setMahasiswa)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nimBaru.setText("");
                        namaBaru.setText("");
                        alamatBaru.setText("");
                        Toast.makeText(UpdateActivity.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }
}
