import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException{
        String path = args[0];
        ConnectionDB connectionDB = new ConnectionDB();

        FileLoad load = new FileLoad();
        String[] str = load.readLines(path);
        Parser parser = new Parser();

        ArrayList<ParseData> list = parser.ParseLogs(str);
        connectionDB.InsertLogsToDataBase(list);
    }
}
