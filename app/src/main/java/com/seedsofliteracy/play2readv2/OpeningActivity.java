package com.seedsofliteracy.play2readv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;


public class OpeningActivity extends AppCompatActivity  {

    private TextView mysentenceview;
    private String mysentence;
    private TextToSpeech mytos;



    public void startGame(View view)  {
        mytos.speak(mysentence, TextToSpeech.QUEUE_FLUSH, null);
        Intent intent = new Intent(this,showSortWordsGameController.class);
        startActivity(intent);
    }

    public void startLesson(View view) {
        Intent intent = new Intent(this, showLesson.class);
        startActivity(intent);
    }

    public void startRead(View view) {
        Intent intent = new Intent(this, readToMe.class);
        startActivity(intent);
    }


    public void getVirtualHelp(View view) {
        Intent intent = new Intent(this, virtualTutor.class);
        startActivity(intent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        //setContentView(R.layout.activity_opening_view);

        mysentenceview = (TextView) findViewById(R.id.hello);
        mysentence = mysentenceview.getText().toString();

        mytos = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mytos.setLanguage(new Locale("en","us")); // US for USA, IN for India
            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_opening, menu);


        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
