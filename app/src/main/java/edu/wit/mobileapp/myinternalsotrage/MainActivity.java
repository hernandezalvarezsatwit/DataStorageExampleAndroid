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

    //Notice no additional permissions are needed
    private String filename = "myFile";
    private TextView text;
    private Button readDataBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Data that will be written
        String string = "Sample written data\n";
        FileOutputStream outputStream;

        context = getApplicationContext();
        text = findViewById(R.id.text);
        readDataBtn = findViewById(R.id.ReadBtn);

        try{
            //pass file name
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            //Write as bytes.
            outputStream.write(string.getBytes());
            //Close right away
            outputStream.close();
            //Confirm everything went well
            text.setText("Wrote data to file "+filename+ " successfully.");
            //Update UI
            text.invalidate();
        }catch (Exception e){
            Log.v("myApp","Error: "+e);
        }

        //Button action to read data and show it on screen
        readDataBtn.setOnClickListener(v ->{
            FileInputStream fis;
            InputStreamReader isr;
            BufferedReader bufferedReader;

            try{
                //Get the file
                fis = context.openFileInput(filename);
                //Get the stream
                isr = new InputStreamReader(fis);
                //Use buffer reader to read stream
                bufferedReader = new BufferedReader(isr);
                //To hold the data
                StringBuilder sb = new StringBuilder();

                //Buffer read contents and append to sb
                String line;
                while((line = bufferedReader.readLine())!= null){
                    sb.append(line);
                }

                //Set to text to show in screen
                text.setText(sb.toString());

                //Update UI
                text.invalidate();
            }catch(Exception e){
                Log.v("myApp", "Error: "+e);
            }
        });
    }
}