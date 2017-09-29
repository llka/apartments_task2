package ru.ilka.apartments.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User implements IDatabaseEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(sequenceName = "user_seq", initialValue = 1, allocationSize = 1, name = "USER_SEQ")
    private int id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;


    @Column(name = "BAN")
    private boolean ban;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USERS_HAS_APARTMENTS",
            joinColumns = @JoinColumn(name = "USERS_FK", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "APARTMENTS_FK", referencedColumnName = "ID"))
    private Set<Apartment> apartments;

    public User() {
    }

    public User(int id, String login, String password, boolean ban) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.ban = ban;
    }

    public User(User user) {
        this.id = user.id;
        this.login = user.login;
        this.password = user.password;
        this.ban = user.ban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "USERS_HAS_APARTMENTS",
//            joinColumns = @JoinColumn(name = "USERS_FK", referencedColumnName = "ID"),
//            inverseJoinColumns = @JoinColumn(name = "APARTMENTS_FK", referencedColumnName = "ID"))
    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (ban != user.ban) return false;
        if (!login.equals(user.login)) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + login.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (ban ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", ban=" + ban +
                '}';
    }
}
