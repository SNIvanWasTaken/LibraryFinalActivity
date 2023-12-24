package com.ilm2223.unit5.libraryfinalactivity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "lending", schema = "public", catalog = "Library")
public class LendingClass {
    private int id;
    private Date lendingdate;
    private Date returningdate;
    private UsersClass borrowe;
    private BooksClass book;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lendingdate", nullable = false)
    public Date getLendingdate() {
        return lendingdate;
    }

    public void setLendingdate(Date lendingdate) {
        this.lendingdate = lendingdate;
    }

    @Basic
    @Column(name = "returningdate", nullable = true)
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
        return id == that.id && Objects.equals(lendingdate, that.lendingdate) && Objects.equals(returningdate, that.returningdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lendingdate, returningdate);
    }

    @ManyToOne
    @JoinColumn(name = "borrower", referencedColumnName = "code", nullable = false)
    public UsersClass getBorrowe() {
        return borrowe;
    }

    public void setBorrowe(UsersClass borrowe) {
        this.borrowe = borrowe;
    }

    @ManyToOne
    @JoinColumn(name = "book", referencedColumnName = "isbn", nullable = false)
    public BooksClass getBook() {
        return book;
    }

    public void setBook(BooksClass book) {
        this.book = book;
    }
}
