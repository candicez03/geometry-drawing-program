@SuppressWarnings("serial")
public class InvalidFileNameException extends Exception {
  private String fileName;
  
  public InvalidFileNameException(String fileName) {
    super();
    this.fileName = fileName;
  }

  public String getFileName() {
    return this.fileName;
  }
}
