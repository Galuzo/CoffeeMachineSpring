package by.nc.training.dev3.coffee.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Win on 05.05.2017.
 */
@Entity
@Table(name="bills")
public class Bill extends AbstractEntity{
    public Bill(){}

    @Column(name ="generalCost")
    public double getGeneralCost() {
        return generalCost;
    }
    public void setGeneralCost(double generalCost) {
        this.generalCost = generalCost;
    }
    private double generalCost;

    @Column(name="date")
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    private Date date;

    @OneToOne()
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
    public Set<Order> getOrderSet() {
        return orderSet;
    }
    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }
    private Set<Order> orderSet = new HashSet<Order>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (id != bill.id) return false;
        if (Double.compare(bill.generalCost, generalCost) != 0) return false;
        if (date != null ? !date.equals(bill.date) : bill.date != null) return false;
        return user.equals(bill.user);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(generalCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", generalCost=" + generalCost +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
