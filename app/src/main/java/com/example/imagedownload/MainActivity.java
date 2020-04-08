package com.example.imagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.pic);
    }

    public void download(View view) {
        downloadback task=new downloadback();
        Bitmap bit;
        try {
            bit=task.execute("https://upload.wikimedia.org/wikipedia/en/0/02/Homer_Simpson_2006.png").get();
            imageView.setImageBitmap(bit);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public class downloadback extends AsyncTask<String,Void, Bitmap>{

    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            URL url=new URL(urls[0]);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.connect();
            InputStream in= urlConnection.getInputStream();
            Bitmap myBitmap= BitmapFactory.decodeStream(in);
            return  myBitmap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
}
