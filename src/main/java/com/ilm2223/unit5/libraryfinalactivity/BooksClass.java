package com.ilm2223.unit5.libraryfinalactivity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books", schema = "public", catalog = "Library")
public class BooksClass {
    @Id
    @Column(name = "isbn", nullable = false, length = 13)
    private String isbn;
    @Basic
    @Column(name = "title", nullable = false, length = 90)
    private String title;
    @Basic
    @Column(name = "copies", nullable = true)
    private Integer copies;
    @Basic
    @Column(name = "cover", nullable = true, length = 255)
    private String cover;
    @Basic
    @Column(name = "outline", nullable = false, length = 255)
    private String outline;
    @Basic
    @Column(name = "publisher", nullable = true, length = 60)
    private String publisher;
    @OneToMany(mappedBy = "book")
    private Set<LendingClass> lendings;
    @OneToMany(mappedBy = "book")
    private Set<ReservationsClass> reservations;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooksClass that = (BooksClass) o;

        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (copies != null ? !copies.equals(that.copies) : that.copies != null) return false;
        if (cover != null ? !cover.equals(that.cover) : that.cover != null) return false;
        if (outline != null ? !outline.equals(that.outline) : that.outline != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (copies != null ? copies.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (outline != null ? outline.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        return result;
    }

    public Set<LendingClass> getLendings() {
        return lendings;
    }

    public void setLendings(Set<LendingClass> lendings) {
        this.lendings = lendings;
    }

    public Set<ReservationsClass> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationsClass> reservations) {
        this.reservations = reservations;
    }
}
