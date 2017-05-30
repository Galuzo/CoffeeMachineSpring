package by.nc.training.dev3.coffee.dto;

import by.nc.training.dev3.coffee.entities.User;

/**
 * Created by Win on 30.05.2017.
 */
public class IngredientInOrderDto {
    private int userId;
    private int orderId;
    private int ingredientId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
}
