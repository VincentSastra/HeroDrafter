package HTMLParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Parser {

    private static String[] ignoredTags = {};

    public Document parse(URL url) throws InterruptedException, IOException, URISyntaxException {
        return new Document(url);
    }

}
