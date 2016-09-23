
package com.seedsofliteracy.play2readv2;

/**
 * Created by bramach5 on 9/8/2016.
 */
public class Person {
    private String first_name;
    private String last_name;
    private int current_level;

    public Person(String fname, String lname, int clevel) {
        first_name = fname;
        last_name = lname;
        current_level = clevel;
    }

    public String getName() {
        return first_name+last_name;
    }

    public int getCurrent_level() {
        return current_level;
    }

}
