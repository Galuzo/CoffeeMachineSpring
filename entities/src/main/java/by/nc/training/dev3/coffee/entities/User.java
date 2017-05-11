package by.nc.training.dev3.coffee.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Win on 03.05.2017.
 */
@Entity
@Table(name="users")
public class User{

    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }
    private int id;

    @Column(name="login")
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    private String login;

    @Column(name ="password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id")
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }
    private Bill bill;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (!role.equals(user.role)) return false;
        return bill.equals(user.bill);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + bill.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
