import java.io.Serializable;

public class CaseException extends Exception implements Serializable {
    
    public CaseException() {
        super();
    }

    public CaseException(String msg) {
        super(msg);
    }
}