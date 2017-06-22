package by.nc.training.dev3.coffee.exceptions;

/**
 * Created by Win on 05.06.2017.
 */
public class RegisterException  extends Exception{
    public RegisterException() {
        super();
    }



    public RegisterException(String message){
        super(message);
    }

    public RegisterException(String message, Throwable cause){
        super(message, cause);
    }

    public RegisterException(Throwable e) {
        super(e);
    }
}
