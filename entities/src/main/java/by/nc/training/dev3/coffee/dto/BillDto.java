package by.nc.training.dev3.coffee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Win on 03.06.2017.
 */
public class BillDto {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy ")
    private Date date;
    private double generalCost;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getGeneralCost() {
        return generalCost;
    }

    public void setGeneralCost(double generalCost) {
        this.generalCost = generalCost;
    }
}
