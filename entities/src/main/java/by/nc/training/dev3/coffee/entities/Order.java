package by.nc.training.dev3.coffee.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Win on 07.05.2017.
 */
@Entity
@Table(name="orders")
public class Order extends AbstractEntity {

    public Order(){}

    @ManyToOne
    @JoinColumn(name="bill_id")
    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }
    private Bill bill;

    @ManyToOne
    @JoinColumn(name="beverage_id")
    public Beverage getBeverage() {
        return beverage;
    }
    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }
    private Beverage beverage;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_ingredient", joinColumns = {
            @JoinColumn(name = "order_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id",
                    nullable = false, updatable = false)})
    public Set<Ingredient> getIngredientSet() {
        return ingredientSet;
    }
    public void setIngredientSet(Set<Ingredient> ingredientSet) {
        this.ingredientSet = ingredientSet;
    }
    private Set<Ingredient> ingredientSet = new HashSet<Ingredient>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!bill.equals(order.bill)) return false;
        return beverage != null ? beverage.equals(order.beverage) : order.beverage == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bill.hashCode();
        result = 31 * result + (beverage != null ? beverage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", bill=" + bill +
                ", beverage=" + beverage +
                '}';
    }
}
