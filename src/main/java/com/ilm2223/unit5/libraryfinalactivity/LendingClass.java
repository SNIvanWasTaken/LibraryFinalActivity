package com.ilm2223.unit5.libraryfinalactivity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "lending", schema = "public", catalog = "Library")
public class LendingClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "lendingdate", nullable = false)
    private Date lendingdate;
    @Basic
    @Column(name = "returningdate", nullable = true)
    private Date returningdate;
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

    public Date getLendingdate() {
        return lendingdate;
    }

    public void setLendingdate(Date lendingdate) {
        this.lendingdate = lendingdate;
    }

    public Date getReturningdate() {
        return returningdate;
    }

    public void setReturningdate(Date returningdate) {
        this.returningdate = returningdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LendingClass that = (LendingClass) o;

        if (id != that.id) return false;
        if (lendingdate != null ? !lendingdate.equals(that.lendingdate) : that.lendingdate != null) return false;
        if (returningdate != null ? !returningdate.equals(that.returningdate) : that.returningdate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lendingdate != null ? lendingdate.hashCode() : 0);
        result = 31 * result + (returningdate != null ? returningdate.hashCode() : 0);
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
