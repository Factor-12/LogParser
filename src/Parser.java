import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{3})\\s(\\[.+])\\s+(\\w+)\\s+(.+)");

    public ArrayList<ParseData> ParseLogs(String[] logs){
        ArrayList<ParseData> result = new ArrayList<>();
        for (String aStr : logs) {
            Matcher m = pattern.matcher(aStr);
            if (m.find()) {
                ParseData parseData = new ParseData(m.group(1), m.group(2), m.group(3), m.group(4));
                result.add(parseData);
            }
        }
        return result;
    }
}
