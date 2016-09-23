package com.seedsofliteracy.play2readv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class showSortWordsGameController extends AppCompatActivity {

    public int points = 0; //need to be accessed globally
    public int NUM_WORDS_IN_GAME = 8; // need to be accessed globally
    public sortWordsGameView gView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startSortGame();

    }

    // create click listener
    View.OnClickListener myocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startSortGame();
        }
    };

    private void startSortGame() {
        gView = new sortWordsGameView(this);
        setContentView(gView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_show_sort_words_game_controller,menu);
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
