package HTMLParser;

import java.net.URL;

public class Parser {

    private static String[] ignoredTags = {};

    public Document parse(URL url) {
        return new Document(url);
    }

}
