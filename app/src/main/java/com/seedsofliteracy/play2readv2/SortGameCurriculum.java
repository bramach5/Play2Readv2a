package com.seedsofliteracy.play2readv2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bramach5 on 9/9/2016.
 */
public class SortGameCurriculum {
    private int NUM_WORDS_IN_GAME = globalParameters.NUM_WORDS_IN_SORT_GAME;
    private int level;
    private ArrayList<CurriculumWord> word ;
    private Context gameContext;
    private Cursor listCursor;
    private String tempword, tempcategory;
    private CurriculumWord tempCurriculumWord;
    private int count=0;
    private int totalcount=0;


    public SortGameCurriculum(int mylevel, Context myContext) {
        level = mylevel;
        word = new ArrayList<CurriculumWord>();
        gameContext = myContext;
        SortGameCurriculumInitialize();
    }

    public void SortGameCurriculumInitialize() {
        int index;
        int i;
        ArrayList<String> wordIsPresent = new ArrayList<String>();

        DatabaseAccess dbaccess = new DatabaseAccess(gameContext);
        dbaccess.createDataBase();
        SQLiteDatabase db = dbaccess.getReadableDatabase();

        String tempword = "Word";
        listCursor = db.query("SortWords", new String[]{"Word", "Category"}, null, null, null, null, String.format("%s", tempword));
        totalcount = listCursor.getCount();
        Random r = new Random();



        for (i=0; i<NUM_WORDS_IN_GAME;) {
            index = r.nextInt(totalcount);
            listCursor.moveToPosition(index);
            tempword = listCursor.getString(0);
            tempcategory = listCursor.getString(1);

            if (wordIsPresent.contains(tempword) == false) {
                wordIsPresent.add(tempword);
                i=i+1;
                tempCurriculumWord = new CurriculumWord(tempword, tempcategory);
                word.add(tempCurriculumWord);
            }
        }


        dbaccess.close();
    }

    public ArrayList<CurriculumWord> GetCurriculumWords(){
        return word;
    }

}
