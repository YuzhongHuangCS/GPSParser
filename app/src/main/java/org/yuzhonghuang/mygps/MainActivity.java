package org.yuzhonghuang.mygps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import SunGps.SunGpsInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent().setType("*/*").setAction(Intent.ACTION_OPEN_DOCUMENT );
        startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
            String path = getPath(getApplicationContext(), selectedfile);
            System.out.println(path);

            if (path != null) {
                Toast.makeText(getApplicationContext(), "Read: "+ path, Toast.LENGTH_LONG).show();

                SunGpsInterface sunGpsInterface = new SunGpsInterface();
                sunGpsInterface.sunGpsInit();
                sunGpsInterface.sunGpsSetDecodeType(3);
                String decoded = sunGpsInterface.sunGpsDecode(path, 0);
                sunGpsInterface.sunGpsExit();

                String ext = path.substring(path.lastIndexOf("."));
                String outPath = path.replace(ext, "_decoded"+ext);
                System.out.println(outPath);

                File file = new File(outPath);
                try {
                    FileOutputStream f = new FileOutputStream(file);
                    PrintWriter pw = new PrintWriter(f);
                    String[] split = decoded.split(";");
                    for (String line : split) {
                        pw.println(line);
                    }
                    pw.close();
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Can't Write Decoded File", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Save: " + outPath, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "InValid File Selected", Toast.LENGTH_LONG).show();
            }
            }
    }

    public static String getPath(Context context, Uri uri) {
        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                // TODO handle non-primary volumes
            }
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
}