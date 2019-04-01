import java.io.Serializable;

public class BateauException extends Exception implements Serializable {
    
    public BateauException() {
        super();
    }

    public BateauException(String msg) {
        super(msg);
    }
}