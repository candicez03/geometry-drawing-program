@SuppressWarnings("serial")
public class IntNotInRangeException extends Exception {
    public IntNotInRangeException() {
        super();
    }

    public IntNotInRangeException(String errorMessage) {
        super(errorMessage);
    }
}
