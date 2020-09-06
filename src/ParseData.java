import java.text.ParseException;

public class ParseData {
    public String date;
    public String stream;
    public String log;
    public String message;

    ParseData(String date, String stream, String log, String message){
        this.date = date;
        this.stream = stream;
        this.log = log;
        this.message = message;
    }
}

