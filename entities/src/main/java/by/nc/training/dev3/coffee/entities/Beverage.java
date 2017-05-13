package by.nc.training.dev3.coffee.entities;

import javax.persistence.*;
import java.util.HashSet;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by Win on 03.05.2017.
 */
@Entity
@Table(name="beverages")
public class Beverage extends Content {
    public Beverage(){}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "beverage")
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

        Beverage beverage = (Beverage) o;

        if (id != beverage.id) return false;
        if (Double.compare(beverage.cost, cost) != 0) return false;
        return title.equals(beverage.title);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + title.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
