package ru.ilka.apartments.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "APARTMENTS")
public class Apartment implements IDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APRT_SEQ")
    @SequenceGenerator(sequenceName = "apartment_seq", initialValue = 1, allocationSize = 1, name = "APRT_SEQ")
    private int id;

    @Column(name = "BOOKED_FROM")
    private Timestamp bookedFrom;

    @Column(name = "BOOKED_TO")
    private Timestamp bookedTo;

    @Column(name = "COST")
    private int cost;

    @ManyToMany(mappedBy = "apartments")
    private Set<User> users;

    public Apartment() {
    }

    public Apartment(int apartmentId, Timestamp bookedFrom, Timestamp bookedTo, int cost) {
        this.id = apartmentId;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.cost = cost;
    }

    public int getApartmentId() {
        return id;
    }

    public void setApartmentId(int apartmentId) {
        this.id = apartmentId;
    }

    public Timestamp getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(Timestamp bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public Timestamp getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(Timestamp bookedTo) {
        this.bookedTo = bookedTo;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    //@ManyToMany(mappedBy = "USERS")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> lodgers) {
        this.users = lodgers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (id != apartment.id) return false;
        if (cost != apartment.cost) return false;
        if (!bookedFrom.equals(apartment.bookedFrom)) return false;
        return bookedTo.equals(apartment.bookedTo);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + bookedFrom.hashCode();
        result = 31 * result + bookedTo.hashCode();
        result = 31 * result + cost;
        return result;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + id +
                ", bookedFrom=" + bookedFrom +
                ", bookedTo=" + bookedTo +
                ", cost=" + cost +
                '}';
    }
}