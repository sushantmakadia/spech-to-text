package com.example.sushant.speachtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView resultTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTEXT=(TextView)findViewById(R.id.tvresult);
    }
    public void onButtonClick(View v)
    {
        if (v.getId()==R.id.imageButton)
        {

            promptSpeachinput();
        }

    }

    private void promptSpeachinput()
    {
        Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"say something!");
        try
        {

            startActivityForResult(i,100);
        }
        catch (ActivityNotFoundException a){
            Toast.makeText(MainActivity.this,"sorry! your device doesn,t support speach langauge!",Toast.LENGTH_LONG).show();
        }
    }
    public void onActivityResult(int request_code,int result_code,Intent i)
    {
        super.onActivityResult(request_code,result_code,i);
        switch (request_code)
        {
            case 100:if (result_code==RESULT_OK && i!=null)
            {
                ArrayList<String> result=i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                String recognizedText = result.get(0);
                recognizedText=recognizedText.replace(" times ","*");
                resultTEXT.setText(recognizedText);

              /*  .
                        //float n1 = Float.parseFloat(q1);


                        // Display the tip on the watch
                        NumberFormat m1 = NumberFormat.getNumberInstance();
                        float n2 = Float.parseFloat(q2);


                        // Display the tip on the watch
                        //Float n3=n1+n2;
                        //resultTEXT.setText(m1.format(n3));
                        //
                   .            catch(NumberFormatException ex) {
                    resultTEXT.setText("Enter a whole number!");
                }
            */}

                


            break;
        }
    }


}
