package android.jplas.datamahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.jplas.datamahasiswa.Model.Mahasiswa;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {
    Button tmbah;
    EditText nim, name, almt;

    public static String NIM, NAMA, ALMT;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("datamhs");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        tmbah = findViewById(R.id.tambah);
        tmbah.setOnClickListener(this);

        nim = findViewById(R.id.nim);
        name = findViewById(R.id.nama);
        almt = findViewById(R.id.alamat);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tambah:
                NIM = nim.getText().toString();
                NAMA = name.getText().toString();
                ALMT = almt.getText().toString();

                String key = myRef.push().getKey();
                Mahasiswa mhs = new Mahasiswa(key,NIM,NAMA,ALMT);

                myRef.child(key).setValue(mhs);
        }
    }
}
