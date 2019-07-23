package com.irwanprasetyo52gmail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bsimpan,cameraopen,sqlite;
        final EditText enpm, enama, etempatlahir, etanggallahir;
        final TextView thasil;
        final RadioGroup rgjk;

            // inisialisasi
            bsimpan = (Button) findViewById(R.id.simpan);
            enpm = (EditText) findViewById(R.id.isinpm);
            enama = (EditText) findViewById(R.id.isinama);
            thasil = (TextView) findViewById(R.id.hasil);
            rgjk = (RadioGroup) findViewById(R.id.jk);
            etempatlahir = (EditText) findViewById(R.id.tempatlahir);
            etanggallahir = (EditText) findViewById(R.id.tanggallahir);
            cameraopen=findViewById(R.id.btmcamera);
            sqlite=findViewById(R.id.SQLite);

            bsimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String inputnim = String.valueOf(enpm.getText().toString());
                    String inputnama = String.valueOf(enama.getText().toString());
                    String inputtempatlahir = String.valueOf(etempatlahir.getText().toString());
                    String inputtanggallahir = String.valueOf(etanggallahir.getText().toString());

                    int gender = rgjk.getCheckedRadioButtonId();
                    RadioButton jk = (RadioButton) findViewById(gender);
                    String inputjk = String.valueOf(jk.getText().toString());

                    thasil.setText("\n" + "NIM\t\t\t\t\t\t\t\t\t\t\t: " + inputnim + "\n" +
                            "Nama\t\t\t\t\t\t\t\t\t\t: " + inputnama + "\n" +
                            "Jenis Kelamin\t\t: " + inputjk + "\n" +
                            "tempat tgl lahir\t\t: " + inputtempatlahir + "\n" +
                            "Tanggal Lahir\t\t\t: " + inputtanggallahir + "\n");
                }
            });
            cameraopen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(i);
                }
            });
        sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(i);
            }
        });
        }
        }



