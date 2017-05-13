package by.nc.training.dev3.coffee.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Win on 11.05.2017.
 */
@MappedSuperclass
public class AbstractEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    protected int id;

    public AbstractEntity(){}
}
