
package com.seedsofliteracy.play2readv2;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by bramach5 on 9/9/2016.
 */
public class DatabaseAccess extends SQLiteOpenHelper {
    public static final String DBNAME = "sortgamewords.db";
    public static final int VERSION =1;
    private SQLiteDatabase myDataBase;
    private Context myContext;
    String DBPATH = null;

    public DatabaseAccess(Context context) {
        super(context,DBNAME,null,VERSION);
        this.myContext = context;
        DBPATH="/data/data/"+context.getPackageName()+"/"+"databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int i1, int i2) {
    }


    public void createDataBase() {
        this.getReadableDatabase();
        try {
            copyDataBase();
        } catch (IOException e) {
            throw new Error("Error copying database");
        }
    }

    // return true if db exists, else return false
    private boolean checkDataBase(){
        SQLiteDatabase mycheckDB = null;
        try{
            String myPath = DBPATH + DBNAME;
            mycheckDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }  catch(SQLiteException e){ }
        if(mycheckDB != null){
            mycheckDB.close();
        }
        return mycheckDB != null ? true : false;
    }


    // copies db from the assets folder to the system folder
    private void copyDataBase() throws IOException{
        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DBNAME);

        // / Path to the just created empty db
        String outFileName = DBPATH + DBNAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte [1000000];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

}
