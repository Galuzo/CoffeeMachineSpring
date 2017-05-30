package by.nc.training.dev3.coffee.dto;

/**
 * Created by Win on 30.05.2017.
 */
public class OrderDto {
    private int userId;
    private int beverageId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }
}
