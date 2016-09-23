package com.seedsofliteracy.play2readv2;

/**
 * Created by bramach5 on 9/10/2016.
 */
public class CurriculumWord {
    private String word;
    private String category;

    public CurriculumWord(String myword, String mycategory) {
        word = myword;
        category = mycategory;
    }

    public void setWord(String myword) {
        word = myword;
    }

    public void setCategory(String mycategory) {
        category = mycategory;
    }

    public String getWord() {
        return word;
    }

    public String getCategory() {
        return category;
    }


}

