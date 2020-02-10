import HTMLParser.Document;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class HTMLDocumentTest {

    @Test
    public void removeScriptsTest() throws IOException, InterruptedException, URISyntaxException {
        Document document = new Document(new URL("https://www.dotabuff.com/heroes/underlord/counters?date=week"));
        System.out.println(document.rawText);
    }
}
