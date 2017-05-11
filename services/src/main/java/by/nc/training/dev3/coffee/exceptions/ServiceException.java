package by.nc.training.dev3.coffee.exceptions;

/**
 * Created by Win on 06.05.2017.
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }



    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceException(Throwable e) {
        super(e);
    }
}
