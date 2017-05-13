package by.nc.training.dev3.coffee.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Win on 13.05.2017.
 */
@MappedSuperclass
public class Content extends AbstractEntity {
    @Column(name="title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    protected String title;

    @Column(name="cost")
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    protected double cost;

    @Column(name="count")
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    protected int count;
}
