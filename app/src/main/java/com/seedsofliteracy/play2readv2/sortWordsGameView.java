package com.seedsofliteracy.play2readv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
/**
 * Created by bramach5 on 9/8/2016.
 */
public class sortWordsGameView  extends View {

    private int points; // need to be made a global variable
    private int previouspoints=0;
    private int targetpoints=0;
    private int NUM_WORDS_IN_GAME = globalParameters.NUM_WORDS_IN_SORT_GAME; // need to be accessed globally
    Person thisPerson = new Person("Joe","Blog",1);   // need to modify this constructor based on user login

    private Context myContext;
    LinearLayout viewLayout;
    private int screenW;
    private int screenH;
    private int boxWidth;
    private int boxHeight;
    private int logoWidth;
    private int logoHeight;
    private SortGameCurriculum mySortGameWordList;
    private ArrayList<CurriculumWord> wordlist;


    private List<Card> deck = new ArrayList<Card>();
    private int movingCardIdx = -1;
    private int movingX;
    private int movingY;
    private Card tempCard;
    private CurriculumWord tempCurrivulumWord;
    private String tempString;
    private int tempX;
    private int tempY;
    private int tempint;
    private String targetCategory;
    private String tempCategory;
    private String tempWord;
    private Intent myIntent;
    private TextToSpeech mytos;
    private String myvoicetext;
    private float paintTextSize;
    private int relativeX;
    private int relativeY;



    public sortWordsGameView(Context context) {
        super(context);
        myContext = context;
        viewLayout = new LinearLayout(myContext);
        viewLayout.setOrientation(LinearLayout.VERTICAL);
        mytos = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mytos.setLanguage(new Locale("en"));    // US for USA, IN for India
            }
        });
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
        boxWidth = 25 * screenW / 100;
        boxHeight = 5 * screenH / 100;
        System.out.println(screenW);
        System.out.println(screenH);
        initGameCurriculum();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint cpaint = new Paint();
        Paint bpaint = new Paint();
        cpaint.setColor(Color.CYAN);
        bpaint.setColor(Color.BLACK);
        final float scale = getContext().getResources().getDisplayMetrics().density;
        final float GESTURE_THRESHOLD_DIP = 20.0f;
        final int fontSize = (int) (GESTURE_THRESHOLD_DIP * scale + 0.5f);
        bpaint.setTextSize(fontSize);
        paintTextSize = bpaint.getTextSize();


        // bpaint.setTextSize(60);
        points=0;

        Bitmap seeds = BitmapFactory.decodeResource(getResources(), R.drawable.seedslogo);
        logoWidth = seeds.getWidth();
        logoHeight = seeds.getHeight();

        //canvas.drawBitmap(seeds,450,100,null);
        canvas.drawBitmap(seeds,(screenW-logoWidth)/2, 5*screenH/100, null);
        /*System.out.println("Screen width is " + screenW);
        System.out.println("Screen height is " + screenH);
        System.out.println("Logo width is " + seeds.getWidth());
        System.out.println("Logo height is " + seeds.getHeight());*/



        for (int i=0; i<NUM_WORDS_IN_GAME; i++) {
            if (i == movingCardIdx) {
                tempCard = deck.get(i);
                tempString = tempCard.getCardTxt();
                tempCategory = tempCard.getCategory();
                tempX = movingX;
                tempY = movingY;

                canvas.drawRect(tempX-bpaint.getTextSize(), tempY-bpaint.getTextSize(), tempX+boxWidth, tempY+boxHeight, cpaint );
                canvas.drawText(tempString, tempX, tempY, bpaint);

                // if (tempX >450 && tempX < 700 && tempY > 100 && tempY < 250) {
                if (tempX >(screenW-logoWidth)/2 && tempX < (screenW+logoWidth)/2 && tempY > 5*screenH/100 && tempY < (5*screenH/100)+logoHeight) {
                    myvoicetext = tempString;
                    mytos.speak(myvoicetext, TextToSpeech.QUEUE_FLUSH, null);
                }


            }
            else {
                tempCard = deck.get(i);
                tempString = tempCard.getCardTxt();
                tempCategory = tempCard.getCategory();
                tempX = tempCard.getCardX();
                tempY = tempCard.getCardY();

                canvas.drawRect(tempX-bpaint.getTextSize(), tempY-bpaint.getTextSize(), tempX+boxWidth, tempY+boxHeight, cpaint );
                canvas.drawText(tempString,tempX,tempY, bpaint);

                tempint = tempX;
                tempString = "X position 2 " + tempint + tempString;
                tempint = tempY;
                tempString = "Y position " + tempint;
            }


            if (tempY > screenH*60/100 && tempCategory.equals(targetCategory)) {
                points = points + 10;
            }
        }


        canvas.drawRect(screenW*5/100, screenH*70/100, screenW*95/100, screenH*71/100, bpaint);
        tempString = "Words that belong to the category: " + targetCategory;
        canvas.drawText(tempString,screenW*5/100, screenH*74/100, bpaint);
        tempString = "Points : "+ Integer.toString(points+previouspoints);
        //canvas.drawText(tempString,screenW*5/100, screenH*85/100, bpaint);
        canvas.drawText(tempString,screenW*5/100, screenH*80/100, bpaint);

        if (points+previouspoints >= targetpoints) {
            previouspoints = points+previouspoints;
            myvoicetext = "Congratulations. You won this game. Let us now go to the next game";
            mytos.speak(myvoicetext, TextToSpeech.QUEUE_FLUSH, null);
            initGameCurriculum();
        }

    }


    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        int X = (int)event.getX();
        int Y = (int)event.getY();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                movingCardIdx=-1;
                for (int i=0; i<NUM_WORDS_IN_GAME; i++) {
                    tempCard = deck.get(i);
                    tempX = tempCard.getCardX();
                    tempY = tempCard.getCardY();

                    if (X>tempX-paintTextSize && X<tempX+boxWidth && Y>tempY-paintTextSize && Y<tempY+boxHeight) {
                        movingCardIdx = i;
                        movingX=X;
                        movingY=Y;
                        /*relativeX = X - tempX;
                        relativeY = Y - tempY;*/
                    }
                }

                break;

            case MotionEvent.ACTION_MOVE:
                movingX=X;
                movingY=Y;
                /*movingX=X-relativeX;
                movingY=Y-relativeY;*/
                break;

            case MotionEvent.ACTION_UP:
                if (movingCardIdx > -1) {
                    tempCard = deck.get(movingCardIdx);
                    tempCard.setX(X);
                    tempCard.setY(Y);
                    /*tempCard.setX(X-relativeX);
                    tempCard.setY(Y-relativeY);*/

                }
                break;
        }
        invalidate();
        return true;

    }



    private void initGameCurriculum() {
        int i;
        SortGameCurriculum mySortGameWordList = new SortGameCurriculum(thisPerson.getCurrent_level(), myContext);
        wordlist = mySortGameWordList.GetCurriculumWords();

        Random r = new Random();
        int targetCategoryArgument = r.nextInt(NUM_WORDS_IN_GAME);


        if (deck.size() > 0) {
            deck.clear();
        }

        for (i=0; i<NUM_WORDS_IN_GAME; i++) {
            tempCurrivulumWord = wordlist.get(i);
            tempCategory = tempCurrivulumWord.getCategory();
            tempWord = tempCurrivulumWord.getWord();
            tempCard = new Card(tempCategory,tempWord);
            if (i<NUM_WORDS_IN_GAME/2) {
                tempCard.setX(screenW * 1 * 10 / 100);
                tempCard.setY(screenH * (i + 3) * 10 / 100);
            }
            else {
                tempCard.setX(screenW * 5 * 10 / 100);
                tempCard.setY(screenH * (i + 3 - NUM_WORDS_IN_GAME/2) * 10 / 100);
            }

            deck.add(tempCard);
            if (i == targetCategoryArgument) {
                targetCategory = tempCategory;
            }
        }

        for (i=0; i<NUM_WORDS_IN_GAME; i++) {
            tempCategory = deck.get(i).getCategory();
            if (targetCategory.equals(tempCategory)) {
                targetpoints = targetpoints + 10;
            }
        }

        tempString = "Target category is : " + targetCategory;
        System.out.println(tempString);
        tempString = "Target points = " + targetpoints;
        System.out.println(tempString);
        return;
    }


}
