package br.com.etecia.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InserirCordenadas extends AppCompatActivity {
    Button btnProcurar;
    EditText etLat, etLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir_cordenadas_layout);
        btnProcurar = findViewById(R.id.btn_procurar);
        etLat = findViewById(R.id.et_lat);
        etLong = findViewById(R.id.et_long);

        btnProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (etLat.getText().toString().isEmpty() || etLong.getText().toString().isEmpty()) {
                    Toast.makeText(InserirCordenadas.this, "Campo vazio!!!", Toast.LENGTH_LONG).show();
                } else if ((Double.parseDouble(etLat.getText().toString()) > 90 || Double.parseDouble(etLat.getText().toString()) < -90)) {
                    Toast.makeText(InserirCordenadas.this, "Insira uma latitude válida", Toast.LENGTH_SHORT).show();
                } else if (Double.parseDouble(etLong.getText().toString()) > 180 || Double.parseDouble(etLong.getText().toString()) < -180) {
                    Toast.makeText(InserirCordenadas.this, "Insira uma longitude válida", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(InserirCordenadas.this, MapsActivity.class);
                    i.putExtra("latitude", etLat.getText().toString());
                    i.putExtra("longitude", etLong.getText().toString());
                    startActivity(i);
                }
            }
        });
    }
}