package by.nc.training.dev3.coffee.utils;

import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.dto.DetailOrderDto;
import by.nc.training.dev3.coffee.entities.Content;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.entities.Order;

import java.util.*;

/**
 * Created by Win on 30.05.2017.
 */
public class DtoBuiler {
    public static ContentDto contentDtoBuilder(Content content) {
        ContentDto contentDto = new ContentDto();
        contentDto.setId(content.getId());
        contentDto.setTitle(content.getTitle());
        contentDto.setCount(content.getCount());
        contentDto.setCost(content.getCost());
        return contentDto;
    }

    public static DetailOrderDto detailOrderDtoBuilder(Order order) {
        DetailOrderDto detailOrderDto = new DetailOrderDto();
        detailOrderDto.setId(order.getId());
        detailOrderDto.setBeverage(contentDtoBuilder(order.getBeverage()));
        detailOrderDto.setIngredients(listContentBuilder(order.getIngredientSet()));
        return detailOrderDto;
    }

    private static Set<ContentDto> listContentBuilder(Set<Ingredient> contents) {
        Set<ContentDto> list = new HashSet<ContentDto>();
        ContentDto contentDto;
        for (Content content : contents) {
            contentDto = DtoBuiler.contentDtoBuilder(content);
            list.add(contentDto);
        }
        return list;
    }
}
