import HTMLParser.Document;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class HTMLDocumentTest {

    @Test
    public void removeScriptsTest() throws MalformedURLException {
        Document document = new Document(new URL("https://www.dotabuff.com/heroes/lina"));
        System.out.println(document.rawText);
    }
}
