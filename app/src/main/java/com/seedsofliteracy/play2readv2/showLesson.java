package com.seedsofliteracy.play2readv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
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
import java.util.Locale;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.itsrts.pptviewer.PPTViewer;
import com.olivephone.office.powerpoint.view.SlideView;
import net.sf.andpdf.pdfviewer.PdfViewerActivity;


import static com.seedsofliteracy.play2readv2.R.layout.activity_show_lesson;


// testing Sep 23 - 10:00 am
// saving this as a branch Sep 23- 10:10 am


//public class showLesson extends AppCompatActivity  implements OnPageChangeListener, OnLoadCompleteListener {
    public class showLesson extends AppCompatActivity {

    private PPTViewer pptViewer;
    private int numpages;
    public static final String PPTNAME = "Lesson1.ppt";
    private String PPTPATH = null;
    private int current_page;
    Integer pageNumber = 0;
    private Context myContext;
    PDFView pdfView;
    Handler handler;
    private TextToSpeech mytos;
    private String myString;

    //public static final String PDFNAME = "short o.pdf";
    private String PDFNAME;
    private String PDFPATH = null;



    public void startShort_a_Lesson(View view) {
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.github.barteksc.sample");
        PDFNAME = "short_a";
        LaunchIntent.putExtra("firstKeyName", PDFNAME);
        startActivity(LaunchIntent);
    }

    public void startShort_i_Lesson(View view) {
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.github.barteksc.sample");
        PDFNAME = "short_i";
        LaunchIntent.putExtra("firstKeyName", PDFNAME);
        startActivity(LaunchIntent);
    }

    public void startShort_o_Lesson(View view) {
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.github.barteksc.sample");
        PDFNAME = "short_o";
        LaunchIntent.putExtra("firstKeyName", PDFNAME);
        startActivity(LaunchIntent);
    }



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


        /*PDFPATH = "/data/data/" + this.getPackageName() + "/" + "databases/" ;
        try {
            copyPdf();
        } catch (IOException e) {
            throw new Error("Error copying pdf file");
        }*/

        /*Intent intent = new Intent(this, MyPdfViewerActivity.class);
        intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, PDFPATH+PDFNAME);
        startActivity(intent);
        */

        /*Intent intent = new Intent(this, BartekPdfViewerActivity.class);
        startActivity(intent);*/

        /*Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra(PDFPATH+PDFNAME,myString);
        startActivity(intent);*/

        //Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.github.barteksc.sample");
        //LaunchIntent.putExtra("firstKeyName",PDFNAME);
        //startActivity( LaunchIntent );

    }



    /*public void displayFromAsset(String assetFileName) {

        System.out.println("Starting File load");
        pdfView.fromAsset(PDFNAME)
                .defaultPage(pageNumber)
                .onPageChange((OnPageChangeListener) this)
                .enableAnnotationRendering(true)
                .onLoad((OnLoadCompleteListener) this)
                .scrollHandle(new DefaultScrollHandle(this))
                .enableSwipe(true)
                .swipeHorizontal(false)
                .load();

        System.out.println("Finished file load");
        myContext = getApplicationContext();

        mytos = new TextToSpeech(myContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mytos.setLanguage(Locale.US);
            }
        });

        class MyThread implements Runnable {

            public void run() {

                for (int i = 0; i < 5; i++) {

                    System.out.println("Starting File load in thread");
                    Message message = Message.obtain();
                    pdfView.fromAsset(PDFNAME)
                            .pages(i)
                            .load();
                    System.out.println("Finished File load in thread");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    message.arg1 = i + 1;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }

        Thread thread;

        thread = new Thread(new MyThread());
        thread.start();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                myString = "Page Number Bala " + String.valueOf(msg.arg1);
                mytos.speak(myString, TextToSpeech.QUEUE_FLUSH, null);
            }
        };

    }
    */


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
    /*private void copyPowerpoint() throws IOException {
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

    }*/


    /*private void copyPdf() throws IOException {
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

    }*/

    /*
    @Override
    public void loadComplete(int i) {
        //.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

    printBookmarksTree(pdfView.getTableOfContents(), "-");
}*/


    /*
    @Override
    public void onPageChanged(int i, int i1) {

    }
    */
}
