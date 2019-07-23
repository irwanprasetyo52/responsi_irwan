package com.irwanprasetyo52gmail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imageView;//deklarasi pada imageView dengan image view.

            Button btnCamera = (Button) findViewById(R.id.btmCamera);//pada button akan menampilkan kamera dengan berdasarkan pada ID pada button kamera.
            btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent takePictureIntent =
                            new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent, 0);//jika pada button kamera kita klik maka akan mengerjakan intent dengan capture lalu mengeksekusi pada activity.
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                ImageView imageView = null;
                imageView.setImageBitmap(imageBitmap);
                SaveImage(imageBitmap);
            } else if (resultCode == RESULT_CANCELED) {//jika pada result code ok, maka pada bitmap akan mengambil data pada image, dan jika tidak maka akan cancel atau tunda.
            }
        }
        private void SaveImage(Bitmap finalBitmap) {
            File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());
            if (!myDir.exists()) {
                myDir.mkdirs();//akan menampilkan storage file yang akan disimpan atau bisa juga dibuat saat itu juga.
            }

            Toast.makeText(this, myDir.toString(), Toast.LENGTH_LONG).show();//menampilkan storage dengan toast.

            Random generator = new Random();//memberikan nama dengan random atau acak.
            int n = 10000;
            n = generator.nextInt(n);
            File file = new File (myDir,  "Image-"+ n +".jpg");
            if (file.exists ())
                file.delete ();

//akan memasukan data gambar.
            try {
                FileOutputStream out = new FileOutputStream(file);//akan mengkonpres data dalam bentuk jpg.
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
//kita akan menutup file.
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

