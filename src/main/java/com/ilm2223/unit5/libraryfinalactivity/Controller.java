package com.ilm2223.unit5.libraryfinalactivity;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Controller {
    private Tab currentTab = Tab.USER;

    private final Model myModel = new Model();

    private final SpringModel sModel = new SpringModel();

    private final int maxLendings = 5;

    private final int daysFined = 7;

    private final long deadline = 15;

    @FXML
    private ImageView userButton;

    @FXML
    private ImageView bookButton;

    @FXML
    private ImageView lendButton;

    @FXML
    private ImageView returnButton;

    @FXML
    private ImageView exitButton;

    @FXML
    private ImageView searchButton;

    @FXML
    private ImageView addButton;

    @FXML
    private ImageView editButton;

    @FXML
    private ImageView deleteButton;

    @FXML
    private GridPane headerGrid;

    @FXML
    private GridPane bodyGrid;

    @FXML
    private GridPane footerGrid;

    @FXML
    private GridPane lendBodyGrid;

    @FXML
    private GridPane lendFooterGrid;

    @FXML
    private GridPane bookBodyGrid;

    @FXML
    private GridPane returnBodyGrid;

    @FXML
    private ImageView confirmButton;

    @FXML
    private ImageView lendUserSearchButton;

    @FXML
    private ImageView lendBookSearchButton;

    @FXML
    private ImageView returnSearchButton;

    @FXML
    private ImageView returnBookSearchButton;

    @FXML
    private ImageView cancelButton;

    @FXML
    private TextField userCodeText;

    @FXML
    private TextField userNameText;

    @FXML
    private TextField userSurnameText;

    @FXML
    private DatePicker userBirthdayDate;

    @FXML
    private TextField lendUserCodeText;

    @FXML
    private TextField lendUserNameText;

    @FXML
    private TextField lendTitleText;

    @FXML
    private TextField lendBookTitleText;

    @FXML
    private TextField returnBookUser;

    @FXML
    private TextField bookTitleText;

    @FXML
    private TextField bookIsbnText;

    @FXML
    private TextField returnUserNameText;

    @FXML
    private TextField returnTitleText;

    @FXML
    private TextField returnBookTitleText;

    @FXML
    private TextField bookOutlineText;

    @FXML
    private TextField bookCopiesText;

    @FXML
    private TextField bookPublisherText;

    public void bookButtonPressed() {
        bookButton.setVisible(false);
    }
    public void bookButtonReleased() {
        bookButton.setVisible(true);
        hide();
        bookBodyGrid.setVisible(true);
        bookBodyGrid.setDisable(false);
        footerGrid.setVisible(true);
        footerGrid.setDisable(false);
        currentTab = Tab.BOOK;
        enableCodes();

    }
    public void bookButtonClicked() {

    }

    public void userButtonPressed() {
        userButton.setVisible(false);
    }
    public void userButtonReleased() {
        userButton.setVisible(true);
        hide();
        bodyGrid.setVisible(true);
        bodyGrid.setDisable(false);
        footerGrid.setVisible(true);
        footerGrid.setDisable(false);
        currentTab = Tab.USER;
        enableCodes();

    }
    public void userButtonClicked(){

    }

    public void lendButtonPressed() {
        lendButton.setVisible(false);
    }
    public void lendButtonReleased() {
        lendButton.setVisible(true);
        hide();
        lendBodyGrid.setVisible(true);
        lendBodyGrid.setDisable(false);
        lendFooterGrid.setVisible(true);
        lendFooterGrid.setDisable(false);
        currentTab = Tab.LEND;
        enableCodes();

    }
    public void lendButtonClicked(){

    }

    public void returnButtonPressed() {
        returnButton.setVisible(false);
    }
    public void returnButtonReleased() {
        returnButton.setVisible(true);
        hide();
        returnBodyGrid.setVisible(true);
        returnBodyGrid.setDisable(false);
        lendFooterGrid.setVisible(true);
        lendFooterGrid.setDisable(false);
        currentTab = Tab.RETURN;
        enableCodes();

    }
    public void returnButtonClicked(){

    }

    public void exitButtonPressed() {
        exitButton.setVisible(false);
    }
    public void exitButtonReleased() {
        exitButton.setVisible(true);
        Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
        exit.setTitle("Exit");
        exit.setHeaderText(null);
        exit.initStyle(StageStyle.UTILITY);
        exit.setContentText("Are sure you want to exit?");
        Optional<ButtonType> result = exit.showAndWait();
        if(result.get() == ButtonType.OK)
            App.closeApp();
    }
    public void exitButtonClicked(){

    }

    public void addButtonPressed() {
        addButton.setVisible(false);
    }
    public void addButtonReleased() {
        addButton.setVisible(true);

        if(currentTab == Tab.USER){
            if(Utils.validateUserData(userCodeText.getText(), userNameText.getText(), userSurnameText.getText())){
                try {
                    myModel.createUser(userCodeText.getText().toUpperCase(), userNameText.getText(), userSurnameText.getText(), Date.valueOf(userBirthdayDate.getValue()));

                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.initStyle(StageStyle.UTILITY);
                    success.setContentText("The operation has been successful");
                    success.show();
                    cleanupView();
                }
                catch (Exception ex) {
                    Alert fail = new Alert(Alert.AlertType.ERROR);
                    fail.setTitle("Failed to add");
                    fail.setHeaderText(null);
                    fail.initStyle(StageStyle.UTILITY);
                    fail.setContentText("There has been an issue adding the user.");
                    fail.show();
                }
            }
            else{
                validateUser();
            }
        }
        else if(currentTab == Tab.BOOK){
            if(Utils.validateBookData(bookIsbnText.getText(), bookTitleText.getText(), bookCopiesText.getText(), bookOutlineText.getText(), bookPublisherText.getText())){
                try {
                    myModel.createBook(bookIsbnText.getText().toUpperCase(), bookTitleText.getText(), Integer.parseInt(bookCopiesText.getText()), bookOutlineText.getText(), bookPublisherText.getText());
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.initStyle(StageStyle.UTILITY);
                    success.setContentText("The operation has been successful");
                    success.show();
                    cleanupView();
                }
                catch(Exception ex){
                    Alert fail = new Alert(Alert.AlertType.ERROR);
                    fail.setTitle("Failed to add");
                    fail.setHeaderText(null);
                    fail.initStyle(StageStyle.UTILITY);
                    fail.setContentText("There was an issue adding the book.");
                    fail.show();
                }
            }
            else{
                validateBook();
            }
        }
    }
    public void addButtonClicked(){

    }

    public void searchButtonPressed() {
        searchButton.setVisible(false);
    }
    public void searchButtonReleased() throws Exception {
        searchButton.setVisible(true);
        if(currentTab == Tab.USER){
            if(myModel.readUser(userCodeText.getText()) != null){
                UsersClass users = myModel.readUser(userCodeText.getText());
                userSurnameText.setText(users.getSurname());
                userNameText.setText(users.getName());
                userBirthdayDate.setValue(users.getBirthdate().toLocalDate());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Not found");
                alert.setHeaderText(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.setContentText("No users found with such code");
                alert.show();
            }
        }
        else if(currentTab == Tab.BOOK){
            if(myModel.readBook(bookIsbnText.getText()) != null){
                BooksClass books = myModel.readBook(bookIsbnText.getText());
                bookPublisherText.setText(books.getPublisher());
                bookTitleText.setText(books.getTitle());
                bookCopiesText.setText(books.getCopies().toString());
                bookOutlineText.setText(books.getOutline());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Not found");
                alert.setHeaderText(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.setContentText("No books found with such ISBN");
                alert.show();
            }
        }
    }
    public void searchButtonClicked(){

    }

    public void editButtonPressed() {
        editButton.setVisible(false);
    }
    public void editButtonReleased() throws Exception {
        editButton.setVisible(true);
        if(currentTab == Tab.USER){
            searchButtonReleased();
            if(userBirthdayDate.getValue() != null && myModel.updateUser(userCodeText.getText(), userNameText.getText(), userSurnameText.getText(), Date.valueOf(userBirthdayDate.getValue()), null)){
                userCodeText.setDisable(true);
                footerGrid.setVisible(false);
                footerGrid.setDisable(true);
                lendFooterGrid.setVisible(true);
                lendFooterGrid.setDisable(false);
            }

            else{
                validateUser();
            }
        }
        else if(currentTab == Tab.BOOK){
            searchButtonReleased();
            if(myModel.updateBook(bookIsbnText.getText(), bookTitleText.getText(), Integer.parseInt(bookCopiesText.getText()), bookOutlineText.getText(), bookPublisherText.getText())){
                bookIsbnText.setDisable(true);
                footerGrid.setVisible(false);
                footerGrid.setDisable(true);
                lendFooterGrid.setVisible(true);
                lendFooterGrid.setDisable(false);
            }

            else{
                validateBook();
            }
        }
    }
    public void editButtonClicked(){

    }

    public void confirmButtonClicked() {

    }
    public void confirmButtonPressed() {
        confirmButton.setVisible(false);
    }
    public void confirmButtonReleased() throws Exception {
        confirmButton.setVisible(true);
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        switch (currentTab){
            case BOOK:
                myModel.updateBook(bookIsbnText.getText(), bookTitleText.getText(), Integer.parseInt(bookCopiesText.getText()), bookOutlineText.getText(), bookPublisherText.getText());
                success.setTitle("Success");
                success.setHeaderText(null);
                success.initStyle(StageStyle.UTILITY);
                success.setContentText("The operation has been successful");
                success.show();
                cleanupView();
                cancelButtonReleased();
                break;

            case USER:
                myModel.updateUser(userCodeText.getText(), userNameText.getText(), userSurnameText.getText(), Date.valueOf(userBirthdayDate.getValue()), null);
                success.setTitle("Success");
                success.setHeaderText(null);
                success.initStyle(StageStyle.UTILITY);
                success.setContentText("The operation has been successful");
                success.show();
                cleanupView();
                cancelButtonReleased();
                break;

            case LEND:
                if(myModel.readUser(lendUserCodeText.getText()) != null){
                    UsersClass users = myModel.readUser(lendUserCodeText.getText());
                    if(myModel.readBookByTitle(lendTitleText.getText()) != null){
                        BooksClass books = myModel.readBookByTitle(lendTitleText.getText());
                        if(!users.isFined()){
                            if(myModel.readLendings(users.getCode()).size() < maxLendings){
                                if(books.getCopies() != 0){
                                    List<ReservationsClass> resList = myModel.readReservations(books.getIsbn());
                                    if(resList.size() == 0){
                                        myModel.createLending(users, books, Date.valueOf(LocalDate.now()), null);
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Success");
                                        alert.setHeaderText(null);
                                        alert.initStyle(StageStyle.UTILITY);
                                        alert.setContentText("The book has been lent.");
                                        alert.show();

                                    }
                                    else{
                                        if(resList.get(0).getBorrower().getCode().equals(users.getCode())){
                                            myModel.createLending(users, books, Date.valueOf(LocalDate.now()), null);
                                            //myModel.deleteReservation(resList.get(0));
                                            sModel.deleteSpringReservation(resList.get(0));
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("Success");
                                            alert.setHeaderText(null);
                                            alert.initStyle(StageStyle.UTILITY);
                                            alert.setContentText("The book has been lent and reservation deleted.");
                                            alert.show();
                                        }
                                        else{
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Cannot be lended");
                                            alert.setHeaderText(null);
                                            alert.initStyle(StageStyle.UTILITY);
                                            alert.setContentText("There are reservations before this user.");
                                            alert.show();
                                        }
                                    }
                                }
                                else{
                                    Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
                                    exit.setTitle("Reservation");
                                    exit.setHeaderText(null);
                                    exit.initStyle(StageStyle.UTILITY);
                                    exit.setContentText("There are no copies available, make reservation?");
                                    Optional<ButtonType> result = exit.showAndWait();
                                    if(result.get() == ButtonType.OK) {
                                        myModel.createReservation(books, users, Date.valueOf(LocalDate.now()));
                                        //sModel.insertSpringReservation(books, users, Date.valueOf(LocalDate.now()));
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Reservation");
                                        alert.setHeaderText(null);
                                        alert.initStyle(StageStyle.UTILITY);
                                        alert.setContentText("This the reservation has been done successfully.");
                                        alert.show();
                                    }
                                }
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("User reached maximum lendings");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText("This user has reached the cap and can't lend more books.");
                                alert.show();
                            }
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("User fined");
                            alert.setHeaderText(null);
                            alert.initStyle(StageStyle.UTILITY);
                            alert.setContentText("This user is fined and can't lend more books.");
                            alert.show();
                        }
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not found");
                        alert.setHeaderText(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setContentText("No book found with such title");
                        alert.show();
                    }

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Not found");
                    alert.setHeaderText(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setContentText("No users found with such code");
                    alert.show();
                }
                break;
            case RETURN:
                if(myModel.readUser(returnBookUser.getText()) != null){
                    UsersClass users = myModel.readUser(returnBookUser.getText());
                    if(myModel.readBookByTitle(returnTitleText.getText())!= null) {
                        BooksClass books = myModel.readBookByTitle(returnTitleText.getText());
                        if(myModel.readLendings(users.getCode()).size() != 0){
                            List<LendingClass> lendings = myModel.readLendings(users.getCode());
                            LendingClass lending = null;
                            for(LendingClass l : lendings){
                                if(l.getBook().getIsbn().equals(books.getIsbn())){
                                    lending = l;
                                }
                            }
                            myModel.updateLending(lending.getId(), lending.getLendingdate(), books, users, Date.valueOf(LocalDate.now()));
                            if(LocalDate.now().isBefore(lending.getLendingdate().toLocalDate().plusDays(deadline))){
                                if(myModel.readReservations(books.getIsbn()).size() > 0) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Next reservation");
                                    alert.setHeaderText(null);
                                    alert.initStyle(StageStyle.UTILITY);
                                    alert.setContentText(books.getTitle() + " is available for lending." + myModel.readReservations(books.getIsbn()).get(0).getBorrower() + " is the next reservation.");
                                    alert.show();
                                }
                            }
                            else {
                                myModel.updateUser(users.getCode(), users.getName(), users.getSurname(), users.getBirthdate(), Date.valueOf(LocalDate.now().plusDays(daysFined)));
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Fined");
                                alert.setHeaderText(null);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setContentText("User has been fined for not returning the book before the specified deadline");
                                alert.show();
                                if(myModel.readReservations(books.getIsbn()).size() > 0) {
                                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                    alert2.setTitle("Next reservation");
                                    alert2.setHeaderText(null);
                                    alert2.initStyle(StageStyle.UTILITY);
                                    alert2.setContentText(books.getTitle() + " is available for lending." + myModel.readReservations(books.getIsbn()).get(0).getBorrower() + " is the next reservation.");
                                    alert2.show();
                                }
                            }
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Not found");
                            alert.setHeaderText(null);
                            alert.initStyle(StageStyle.UTILITY);
                            alert.setContentText("Lending not found");
                            alert.show();
                        }
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not found");
                        alert.setHeaderText(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setContentText("No users found with such code");
                        alert.show();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Not found");
                    alert.setHeaderText(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setContentText("No users found with such code");
                    alert.show();
                }
        }
    }

    public void deleteButtonPressed() {
        deleteButton.setVisible(false);
    }
    public void deleteButtonReleased() {
        deleteButton.setVisible(true);

        if (currentTab == Tab.USER) {
            try {
                if (myModel.readUser(userCodeText.getText()) != null) {
                    UsersClass users = myModel.readUser(userCodeText.getText());
                    if(myModel.readLendings(users.getCode()).size() == 0){
                        if(myModel.readReservationsByUser(users.getCode()).size() != 0){
                            List<ReservationsClass> reservs = myModel.readReservationsByUser(users.getCode());
                            for(ReservationsClass r : reservs){
                                myModel.deleteReservation(r);
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Reservations removed");
                            alert.setHeaderText(null);
                            alert.initStyle(StageStyle.UTILITY);
                            alert.setContentText("This user's reservation has been removed");
                            alert.show();
                        }
                        myModel.deleteUser(users);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("User removed");
                        alert.setHeaderText(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setContentText("This user has been removed");
                        alert.show();

                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Deletion aborted");
                        alert.setHeaderText(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setContentText("This user cannot be deleted because they have pending lendings.");
                        alert.show();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Not found");
                    alert.setHeaderText(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setContentText("No users found with such code");
                    alert.show();
                }
            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.setContentText("There has been an issue deleting this user.");
                alert.show();
            }
        }
        else{
            try {
                if(myModel.readBook(bookIsbnText.getText()) != null) {
                    BooksClass books = myModel.readBook(bookIsbnText.getText());
                    if (myModel.readLendingsByBook(books.getIsbn()).size() == 0) {
                        if(myModel.readReservations(books.getIsbn()).size() != 0){
                            List<ReservationsClass> reservs = myModel.readReservations(books.getIsbn());
                            for(ReservationsClass r : reservs){
                                myModel.deleteReservation(r);
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Reservations removed");
                            alert.setHeaderText(null);
                            alert.initStyle(StageStyle.UTILITY);
                            alert.setContentText("This book's reservations have been removed");
                            alert.show();
                        }
                        sModel.deleteSpringBook(books);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Book removed");
                        alert.setHeaderText(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setContentText("This book has been removed");
                        alert.show();

                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Deletion aborted");
                        alert.setHeaderText(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setContentText("This book cannot be deleted because it has pending lendings.");
                        alert.show();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Not found");
                    alert.setHeaderText(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setContentText("No books found with such isbn");
                    alert.show();
                }
            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.setContentText("There has been an issue deleting this book.");
                alert.show();
            }
        }
    }

    public void lendUserSearchButtonPressed() {
        lendUserSearchButton.setVisible(false);
    }
    public void lendUserSearchButtonReleased() throws Exception {
        lendUserSearchButton.setVisible(true);
        if(myModel.readUser(lendUserCodeText.getText()) != null){
            UsersClass users = myModel.readUser(lendUserCodeText.getText());
            lendUserNameText.setText(users.getName());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not found");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("No users found with such code");
            alert.show();
        }
    }

    public void lendBookSearchButtonPressed() throws Exception {
        lendBookSearchButton.setVisible(false);

        if(myModel.readBookByTitle(lendTitleText.getText()) != null){
            BooksClass books = myModel.readBookByTitle(lendTitleText.getText());
            lendBookTitleText.setText(books.getIsbn());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not found");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("No books found with such Title");
            alert.show();
        }

    }
    public void lendBookSearchButtonReleased() {
        lendBookSearchButton.setVisible(true);
    }

    public void returnSearchButtonPressed() {
        returnSearchButton.setVisible(false);
    }
    public void returnSearchButtonReleased() throws Exception {
        returnSearchButton.setVisible(true);

        if(myModel.readUser(returnBookUser.getText()) != null){
            UsersClass users = myModel.readUser(returnBookUser.getText());
            returnUserNameText.setText(users.getName());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not found");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("No users found with such code");
            alert.show();
        }

    }

    public void returnBookSearchButtonPressed(){
        returnBookSearchButton.setVisible(false);
    }
    public void returnBookSearchButtonReleased() throws Exception {
        returnBookSearchButton.setVisible(true);

        if(myModel.readBookByTitle(returnTitleText.getText()) != null){
            BooksClass books = myModel.readBookByTitle(returnBookTitleText.getText());
            returnBookTitleText.setText(books.getIsbn());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not found");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("No books found with such Title");
            alert.show();
        }
    }

    public void cancelButtonPressed(){
        cancelButton.setVisible(false);
    }
    public void cancelButtonReleased(){
        cancelButton.setVisible(true);
        enableCodes();
        if(currentTab == Tab.BOOK || currentTab == Tab.USER) {
            footerGrid.setVisible(true);
            footerGrid.setDisable(false);
            lendFooterGrid.setVisible(false);
        }
    }
    private void hide(){
        bodyGrid.setVisible(false);
        bookBodyGrid.setVisible(false);
        lendBodyGrid.setVisible(false);
        returnBodyGrid.setVisible(false);
        footerGrid.setVisible(false);
        lendFooterGrid.setVisible(false);
        bodyGrid.setDisable(true);
        bookBodyGrid.setDisable(true);
        lendBodyGrid.setDisable(true);
        returnBodyGrid.setDisable(true);
        footerGrid.setDisable(true);
        lendFooterGrid.setDisable(true);
    }
    private void validateUser(){
        if(!Utils.validateUserCode(userCodeText.getText())){
            Alert code = new Alert(Alert.AlertType.ERROR);
            code.setTitle("Fail");
            code.setHeaderText(null);
            code.initStyle(StageStyle.UTILITY);
            code.setContentText("The code isn't valid. Format: 123456X");
            code.show();
        }
        if(!Utils.validateUserName(userNameText.getText())){
            Alert name = new Alert(Alert.AlertType.ERROR);
            name.setTitle("Fail");
            name.setHeaderText(null);
            name.initStyle(StageStyle.UTILITY);
            name.setContentText("The name isn't valid. Format: Ivan");
            name.show();
        }
        if(!Utils.validateUserSurname(userSurnameText.getText())){
            Alert surname = new Alert(Alert.AlertType.ERROR);
            surname.setTitle("Fail");
            surname.setHeaderText(null);
            surname.initStyle(StageStyle.UTILITY);
            surname.setContentText("The surname isn't valid. Format: Laguia");
            surname.show();
        }
    }
    private void validateBook(){
        if(!Utils.validateBookISBN(bookIsbnText.getText())){
            Alert code = new Alert(Alert.AlertType.ERROR);
            code.setTitle("Fail");
            code.setHeaderText(null);
            code.initStyle(StageStyle.UTILITY);
            code.setContentText("The ISBN isn't valid. Format: 1234567890123");
            code.show();
        }
        if(!Utils.validateBookCopies(bookCopiesText.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("The copies aren't valid. Only single digits are allowed");
            alert.show();

        }
        if(!Utils.validateBookTitle(bookTitleText.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("The title isn't valid. Format: 'Ruined King'");
            alert.show();

        }
        if(!Utils.validateBookOutline(bookOutlineText.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("The outline isn't valid. Outline can't be longer than 255 characters");
            alert.show();

        }
        if(!Utils.validateBookPublisher(bookPublisherText.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setContentText("The publisher isn't valid. Publisher can't be longer than 60 characters Format: 'Riot'");
            alert.show();
        }
    }
    private void cleanupView(){
        switch (currentTab){
            case BOOK:
                bookCopiesText.clear();
                bookIsbnText.clear();
                bookCopiesText.clear();
                bookOutlineText.clear();
                bookPublisherText.clear();
                bookTitleText.clear();
            case USER:
                userCodeText.clear();
                userNameText.clear();
                userSurnameText.clear();
                userBirthdayDate.setValue(null);
            case LEND:
                lendTitleText.clear();
                lendBookTitleText.clear();
                lendUserCodeText.clear();
                lendUserNameText.clear();
            default:
                returnTitleText.clear();
                returnBookTitleText.clear();
                returnUserNameText.clear();
        }
    }
    private void enableCodes(){
        userCodeText.setDisable(false);
        bookIsbnText.setDisable(false);
    }
}

/*                    if(myModel.readReservationsByUser(users.getCode()) != null){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Reservations removed");
                        alert.setHeaderText(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setContentText("This user's reservation has been removed");
                        alert.show();

                    }
*/