package HTMLParser.Element;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Body {

    public ArrayList<Table> tables = new ArrayList<>();
    public String rawText;

    public Body(String string) {

        rawText = string;

        Pattern pattern = Pattern.compile("(?s)<table( [^>]*)?>(.*?)</table>");
        Matcher matcher = pattern.matcher(rawText);

        while(matcher.find()) {
            Table table = new Table(matcher.group());
            tables.add(table);
        }

    }
}
