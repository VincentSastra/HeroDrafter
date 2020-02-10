package HTMLParser.Element;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Body {
    public ArrayList<Element> elements;
    public String rawText;

    public Body(String string) {
        rawText = string;

    }
}
