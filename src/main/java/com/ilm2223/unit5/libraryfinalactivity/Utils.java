package com.ilm2223.unit5.libraryfinalactivity;

import java.util.Date;
import java.util.regex.*;

public class Utils {
    public static boolean validateUserCode(String code){
        if(!Pattern.matches("^[0-9]{6}[A-z]?$", code) || code.length() != 7)
            return false;
        return true;
    }

    public static boolean validateUserName(String name){
        if(!Pattern.matches("^[A-Za-z]*$", name) || name.length() > 25)
            return false;
        return true;
    }
    public static boolean validateUserSurname(String surname){
        if(!Pattern.matches("^[A-Za-z]*$", surname) || surname.length() > 25)
            return false;
        return true;
    }

    public static boolean validateUserData(String c, String n, String s){
        if(validateUserCode(c) && validateUserName(n) && validateUserSurname(s))
            return true;
        return false;
    }

    public static boolean validateBookISBN(String isbn){
        return Pattern.matches("\\d{13}", isbn);
    }

    public static boolean validateBookTitle(String title){
        return title.length() <= 90;
    }

    public static boolean validateBookCopies(String copies){
        try{
            Integer.parseInt(copies);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean validateBookOutline(String outline){
        return outline.length() <= 255;
    }

    public static boolean validateBookPublisher(String publisher){
        return publisher.length() <= 60;
    }

    public static boolean validateBookData(String isbn, String t, String c, String o, String p){
        if(validateBookISBN(isbn) && validateBookCopies(c) && validateBookPublisher(p) && validateBookTitle(t) && validateBookOutline(o))
            return true;
        return false;
    }
}
