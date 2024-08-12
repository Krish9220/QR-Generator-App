package com.example.qrgenerator;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    ImageView QR;
    TextInputEditText input;
    Button btn;
    QRGEncoder encoder;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QR = findViewById(R.id.qr);
        input = findViewById(R.id.input_et);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = input.getText().toString();
                if(TextUtils.isEmpty(data))
                {
                    Toast.makeText(MainActivity.this, "Please Write Something", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

                    Display display = manager.getDefaultDisplay();

                    Point point = new Point();
                    display.getSize(point);

                    int width = point.x;
                    int height = point.y;

                    int demin = width < height ? width:height;

                    demin = height*3/4;

                    encoder = new QRGEncoder(data,null, QRGContents.Type.TEXT,demin);

                    try {
                        bitmap = encoder.getBitmap();
                        QR.setImageBitmap(bitmap);
                    }catch (Exception e){
                        Log.e("Tag",e.toString());
                    }
                }
            }
        });
    }
}