package by.nc.training.dev3.coffee.dto;

import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;

import java.util.List;
import java.util.Set;

/**
 * Created by Win on 30.05.2017.
 */
public class DetailOrderDto {
    private ContentDto beverage;
    private Set<ContentDto> ingredients;

    public ContentDto getBeverage() {
        return beverage;
    }

    public void setBeverage(ContentDto beverage) {
        this.beverage = beverage;
    }

    public Set<ContentDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<ContentDto> ingredients) {
        this.ingredients = ingredients;
    }
}
