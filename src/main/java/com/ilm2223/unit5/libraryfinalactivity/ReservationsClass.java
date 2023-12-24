package com.ilm2223.unit5.libraryfinalactivity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reservations", schema = "public", catalog = "Library")
public class ReservationsClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "date", nullable = true)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "book", referencedColumnName = "isbn", nullable = false)
    private BooksClass book;
    @ManyToOne
    @JoinColumn(name = "borrower", referencedColumnName = "code", nullable = false)
    private UsersClass borrower;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationsClass that = (ReservationsClass) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public BooksClass getBook() {
        return book;
    }

    public void setBook(BooksClass book) {
        this.book = book;
    }

    public UsersClass getBorrower() {
        return borrower;
    }

    public void setBorrower(UsersClass borrower) {
        this.borrower = borrower;
    }
}
