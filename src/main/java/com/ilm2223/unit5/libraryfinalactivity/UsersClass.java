package com.ilm2223.unit5.libraryfinalactivity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users", schema = "public", catalog = "Library")
public class UsersClass {
    @Id
    @Column(name = "code", nullable = false, length = 8)
    private String code;
    @Basic
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 25)
    private String surname;
    @Basic
    @Column(name = "birthdate", nullable = true)
    private Date birthdate;
    @Basic
    @Column(name = "fined", nullable = true)
    private Date fined;
    @OneToMany(mappedBy = "borrower")
    private Set<LendingClass> lendings;
    @OneToMany(mappedBy = "borrower")
    private Set<ReservationsClass> reservations;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getFined() {
        return fined;
    }

    public void setFined(Date fined) {
        this.fined = fined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersClass that = (UsersClass) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (fined != null ? !fined.equals(that.fined) : that.fined != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (fined != null ? fined.hashCode() : 0);
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

    public boolean isFined(){
        if(fined != null){
            if(LocalDate.now().isBefore(fined.toLocalDate())){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
}
