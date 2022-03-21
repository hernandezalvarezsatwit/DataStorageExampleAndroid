package edu.wit.mobileapp.myinternalsotrage;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private String filename = "myFile";
    private TextView text;
    private Button readDataBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String string = "Hello World Android!!\n";
        FileOutputStream outputStream;

        context = getApplicationContext();
        text = findViewById(R.id.text);
        readDataBtn = findViewById(R.id.ReadBtn);

        try{
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            text.setText("Wrote data to file "+filename+ " successfully.");
            text.invalidate();
        }catch (Exception e){
            Log.v("myApp","Error: "+e);
        }

        readDataBtn.setOnClickListener(v ->{
            FileInputStream fis;
            InputStreamReader isr;
            BufferedReader bufferedReader;

            try{
                fis = context.openFileInput(filename);
                isr = new InputStreamReader(fis);
                bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();

                String line;
                while((line = bufferedReader.readLine())!= null){
                    sb.append(line);
                }
                text.setText(sb.toString());
                text.invalidate();
            }catch(Exception e){
                Log.v("myApp", "Error: "+e);
            }
        });
    }
}