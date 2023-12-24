package com.ilm2223.unit5.libraryfinalactivity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public void createUser(String code, String name, String surname, Date birthdate) throws Exception {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            UsersClass user = new UsersClass();
            user.setCode(code);
            user.setName(name);
            user.setSurname(surname);
            user.setBirthdate(birthdate);
            session.save(user);
            transaction.commit();
        }
    }

    public UsersClass readUser(String code) throws Exception {
        try(Session session = openSession()){
            Query<UsersClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity.UsersClass");
            List<UsersClass> Users = myQuery.list();
            ArrayList<UsersClass> searchedUsers = new ArrayList<>();
            for(UsersClass u : Users){
                if(u.getCode().equals(code))
                    searchedUsers.add(u);
            }
            if(searchedUsers.size() == 0){
                return null;
            }
            else
                return searchedUsers.get(0);
        }
    }

    public boolean updateUser(String code, String name, String surname, Date birthdate, Date fined) throws Exception {
        try ( Session session = openSession() ) {
            Query<UsersClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity." + "UsersClass where code='" + code+ "' ");
            List<UsersClass> users = myQuery.list();
            if(users.size() >= 1) {
                Transaction transaction = session.beginTransaction();
                UsersClass user = users.get(0);
                user.setName(name);
                user.setSurname(surname);
                user.setBirthdate(birthdate);
                user.setFined(fined);
                session.update(user);
                transaction.commit();
                return true;
            }
            else{
                return false;
            }
        }
    }
    public void deleteUser(UsersClass user) throws Exception {
        try(Session session = openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }

    public void createBook(String isbn, String title, int copies, String outline, String publisher) throws Exception {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            BooksClass book = new BooksClass();
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setCopies(copies);
            book.setOutline(outline);
            book.setPublisher(publisher);
            session.save(book);
            transaction.commit();
        }
    }

    public BooksClass readBook(String isbn) throws Exception {
        try(Session session = openSession()){
            Query<BooksClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity.BooksClass");
            List<BooksClass> Books = myQuery.list();
            ArrayList<BooksClass> searchedBooks = new ArrayList<>();
            for(BooksClass u : Books){
                if(u.getIsbn().equals(isbn))
                    searchedBooks.add(u);
            }
            return searchedBooks.get(0);
        }
    }
    public BooksClass readBookByTitle(String title) throws Exception {
        try(Session session = openSession()){
            Query<BooksClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity.BooksClass");
            List<BooksClass> Books = myQuery.list();
            for(BooksClass u : Books){
                if(u.getTitle().equals(title))
                    return u;
            }
            return null;
        }
    }


    public boolean updateBook(String isbn, String title, int copies, String outline, String publisher) throws Exception {
        try ( Session session = openSession() ) {
            Query<BooksClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity." + "BooksClass where isbn='" + isbn+ "' ");
            List<BooksClass> books = myQuery.list();
            if(books.size() >= 1) {
                Transaction transaction = session.beginTransaction();
                BooksClass book = books.get(0);
                book.setTitle(title);
                book.setCopies(copies);
                book.setOutline(outline);
                book.setPublisher(publisher);
                session.update(book);
                transaction.commit();
                return true;
            }
            else{
                return false;
            }
        }
    }

    public void createLending(UsersClass borrower, BooksClass book, Date lend, Date returning) throws Exception {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            LendingClass lending = new LendingClass();
            lending.setBook(book);
            lending.setBorrower(borrower);
            lending.setLendingdate(lend);
            lending.setReturningdate(returning);
            updateBook(book.getIsbn(), book.getTitle(), book.getCopies()-1, book.getOutline(), book.getPublisher());
            session.save(lending);
            transaction.commit();
        }
    }

    public List<LendingClass> readLendings(String user) throws Exception {
        try(Session session = openSession()){
            Query<LendingClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity.LendingClass where returningdate is null");
            ArrayList<LendingClass> searchedLendings = new ArrayList<>();
            List<LendingClass> Lendings = myQuery.list();
            for(LendingClass l : Lendings){
                if(l.getBorrower().getCode().equals(user))
                    searchedLendings.add(l);
            }
            return searchedLendings;
        }
    }
    public List<LendingClass> readLendingsByBook(String isbn) throws Exception {
        try(Session session = openSession()){
            Query<LendingClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity.LendingClass where returningdate is null");
            ArrayList<LendingClass> searchedLendings = new ArrayList<>();
            List<LendingClass> Lendings = myQuery.list();
            for(LendingClass l : Lendings){
                if(l.getBook().getIsbn().equals(isbn))
                    searchedLendings.add(l);
            }
            return searchedLendings;
        }
    }

    public boolean updateLending(int id, Date day, BooksClass book, UsersClass user, Date returning) throws Exception {
        try (Session session = openSession()) {
            Query<LendingClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity." + "LendingClass where id ='" + id + "' ");
            List<LendingClass> lendings = myQuery.list();
            if (lendings.size() >= 1) {
                Transaction transaction = session.beginTransaction();
                LendingClass lending = lendings.get(0);
                lending.setBorrower(user);
                lending.setBook(book);
                lending.setLendingdate(day);
                lending.setReturningdate(returning);
                updateBook(book.getIsbn(), book.getTitle(), book.getCopies()+1, book.getOutline(), book.getPublisher());
                session.update(lending);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        }
    }
    public List<ReservationsClass> readReservations(String isbn) throws Exception {
        try(Session session = openSession()){
            Query<ReservationsClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity.ReservationsClass");
            List<ReservationsClass> Reservations = myQuery.list();
            ArrayList<ReservationsClass> searchedReservations = new ArrayList<>();
            for(ReservationsClass r : Reservations){
                if(r.getBook().getIsbn().equals(isbn))
                    searchedReservations.add(r);
            }
            return searchedReservations;
        }

    }
    public List<ReservationsClass> readReservationsByUser(String code) throws Exception {
        try(Session session = openSession()){
            Query<ReservationsClass> myQuery = session.createQuery("from com.ilm2223.unit5.libraryfinalactivity.ReservationsClass");
            List<ReservationsClass> Reservations = myQuery.list();
            ArrayList<ReservationsClass> searchedReservations = new ArrayList<>();
            for(ReservationsClass r : Reservations){
                if(r.getBorrower().getCode().equals(code))
                    searchedReservations.add(r);
            }
            return searchedReservations;
        }

    }


    public void createReservation(BooksClass book, UsersClass borr, Date date) throws Exception {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            ReservationsClass rv = new ReservationsClass();
            rv.setBook(book);
            rv.setBorrower(borr);
            rv.setDate(date);
            session.save(rv);
            transaction.commit();
        }
    }
    public void deleteReservation(ReservationsClass reservation) throws Exception {
        try(Session session = openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(reservation);
            transaction.commit();
        }
    }

    public Session openSession() throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            throw new Exception("Error opening session!");
        }
        return session;
    }
}
