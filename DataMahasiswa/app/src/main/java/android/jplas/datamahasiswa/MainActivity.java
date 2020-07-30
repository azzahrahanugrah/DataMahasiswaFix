package android.jplas.datamahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button add, read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.insert);
        add.setOnClickListener(this);

        read = findViewById(R.id.read);
        read.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.insert:
                Intent baru = new Intent(MainActivity.this,InsertActivity.class);
                startActivity(baru);
                break;

            case R.id.read:
                Intent baca = new Intent(MainActivity.this,ReadActivity.class);
                startActivity(baca);
                break;

        }
    }
}
