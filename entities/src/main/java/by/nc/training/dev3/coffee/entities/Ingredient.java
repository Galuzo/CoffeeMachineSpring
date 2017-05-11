package by.nc.training.dev3.coffee.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Win on 03.05.2017.
 */
@Entity
@Table(name="ingredients")
public class Ingredient {

    public Ingredient(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ingredient_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private int id;

    @Column(name="title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    private String title;

    @Column(name="cost")
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    private double cost;

    @Column(name="count")
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    private int count;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredientSet", cascade = CascadeType.ALL)
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
