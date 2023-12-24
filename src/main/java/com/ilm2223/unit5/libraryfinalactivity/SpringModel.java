package com.ilm2223.unit5.libraryfinalactivity;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SpringModel extends Model{

    public void insertSpringReservation(BooksClass book, UsersClass user, Date date) throws JSONException {
        HttpURLConnection conn = null;
        String jsonInputString = new JSONObject()
                    .put("book", book.getIsbn())
                    .put("borrower", user.getCode())
                    .put("date", date).toString();
        try {
           URL url = new URL("http://localhost:8080/api-rest/Reservations");
           conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("POST");
           conn.setRequestProperty("Content-Type", "application/json;utf-8");
           conn.setRequestProperty("Accept", "application/json");
           conn.setDoOutput(true);

           try(OutputStream os = conn.getOutputStream()){
               byte[] input = jsonInputString.getBytes("utf-8");
               os.write(input, 0, input.length);
           }
           if(conn.getResponseCode() == 200){
               Alert success = new Alert(Alert.AlertType.INFORMATION);
               success.setTitle("Success");
               success.setHeaderText(null);
               success.initStyle(StageStyle.UTILITY);
               success.setContentText("The operation has been successful");
               success.show();
           }
           else {
               Alert fail = new Alert(Alert.AlertType.ERROR);
               fail.setTitle("Failed to delete");
               fail.setHeaderText(null);
               fail.initStyle(StageStyle.UTILITY);
               fail.setContentText("There has been an issue inserting the reservation.");
               fail.show();
           }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally{
            if(conn != null){
                conn.disconnect();
            }
        }
    }
    public void deleteSpringReservation(ReservationsClass reservation){
        HttpURLConnection conn = null;
        try{
            URL url = new URL("http://localhost:8080/api-rest/Reservations/"+reservation.getId());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            if(conn.getResponseCode() == 200){
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success");
                success.setHeaderText(null);
                success.initStyle(StageStyle.UTILITY);
                success.setContentText("The operation has been successful");
                success.show();
            }
            else{
                Alert fail = new Alert(Alert.AlertType.ERROR);
                fail.setTitle("Failed to delete");
                fail.setHeaderText(null);
                fail.initStyle(StageStyle.UTILITY);
                fail.setContentText("There has been an issue deleting the reservation.");
                fail.show();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.disconnect();
        }
    }
    public void deleteSpringBook(BooksClass book){
        HttpURLConnection conn = null;
        try{
            URL url = new URL("http://localhost:8080/api-rest/Reservations/"+book.getIsbn());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            if(conn.getResponseCode() == 200){
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success");
                success.setHeaderText(null);
                success.initStyle(StageStyle.UTILITY);
                success.setContentText("The operation has been successful");
                success.show();
            }
            else{
                Alert fail = new Alert(Alert.AlertType.ERROR);
                fail.setTitle("Failed to delete");
                fail.setHeaderText(null);
                fail.initStyle(StageStyle.UTILITY);
                fail.setContentText("There has been an issue deleting the book.");
                fail.show();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.disconnect();
        }
    }

}
