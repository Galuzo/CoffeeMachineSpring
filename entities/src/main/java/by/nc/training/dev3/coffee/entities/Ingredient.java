package by.nc.training.dev3.coffee.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Win on 03.05.2017.
 */
@Entity
@Table(name="ingredients")
public class Ingredient extends Content{

    public Ingredient(){}


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "ingredientSet")
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

        Ingredient that = (Ingredient) o;

        if (id != that.id) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        return title.equals(that.title);
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
        return "Ingredient{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
