package by.nc.training.dev3.coffee.entities;

import javax.persistence.*;
import java.util.Set;
import javax.persistence.Entity;


/**
 * Created by Win on 03.05.2017.
 */
@Entity
@Table(name="roles")
public class Role extends AbstractEntity{

    public Role(){}

    @Column(name="title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    private String title;

    @OneToMany(fetch = FetchType.LAZY,mappedBy ="role")
    public Set<Account> getUsers() {
        return users;
    }
    public void setUsers(Set<Account> users) {
        this.users = users;
    }
    private Set<Account> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        return title.equals(role.title);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
