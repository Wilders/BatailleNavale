import java.io.Serializable;

public class GrilleException extends Exception implements Serializable {

    public GrilleException() {
        super();
    }

    public GrilleException(String msg) {
        super(msg);
    }
}