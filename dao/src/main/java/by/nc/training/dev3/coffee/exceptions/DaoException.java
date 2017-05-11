package by.nc.training.dev3.coffee.exceptions;

/**
 * Created by Win on 04.05.2017.
 */
public class DaoException extends Exception {
    public DaoException() {
        super();
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Throwable cause){
        super(message, cause);
    }
}
