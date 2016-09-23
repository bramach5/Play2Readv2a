package com.seedsofliteracy.play2readv2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



import com.itsrts.pptviewer.PPTViewer;
import com.olivephone.office.powerpoint.view.SlideView;
import net.sf.andpdf.pdfviewer.PdfViewerActivity;


// testing Sep 23 - 10:00 am
// saving this as a branch Sep 23- 10:10 am



public class showLesson extends AppCompatActivity   {

    private PPTViewer pptViewer;
    private int numpages;
    public static final String PPTNAME = "Lesson1.ppt";
    private String  PPTPATH = null;
    private int current_page;
    Integer pageNumber = 0;



    public static final String PDFNAME = "Lesson1.pdf";
    private String PDFPATH = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lesson);

        /*pptViewer = (PPTViewer) findViewById(R.id.pptviewer);
        pptViewer.setNext_img(R.drawable.next);
        pptViewer.setPrev_img(R.drawable.prev);
        pptViewer.setSettings_img(R.drawable.settings);
        pptViewer.setZoomin_img(R.drawable.zoomin);
        pptViewer.setZoomout_img(R.drawable.zoomout);

        PPTPATH = "/data/data/" + this.getPackageName() + "/" + "databases/" ;
        String mystring = "Printing Path - " + PPTPATH ;
        System.out.println(mystring);
       try {
            copyPowerpoint();
        } catch (IOException e) {
            throw new Error("Error copying ppt file");
        }

        pptViewer.loadPPT(this, PPTPATH + PPTNAME);*/


        PDFPATH = "/data/data/" + this.getPackageName() + "/" + "databases/" ;
        try {
            copyPdf();
        } catch (IOException e) {
            throw new Error("Error copying pdf file");
        }

        Intent intent = new Intent(this, MyPdfViewerActivity.class);
        intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, PDFPATH+PDFNAME);
        startActivity(intent);




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_show_lesson, menu);
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

    // copies ppt from the assets folder to the system folder
    private void copyPowerpoint() throws IOException {
        //Open your local ppt as the input stream


        InputStream myInput = this.getAssets().open(PPTNAME);

        // / Path to the just created empty ppt
        String outFileName = PPTPATH + PPTNAME;
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


    private void copyPdf() throws IOException {
        //Open your local ppt as the input stream

        InputStream myInput = this.getAssets().open(PDFNAME);

        // / Path to the just created empty ppt
        String outFileName = PDFPATH + PDFNAME;
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
