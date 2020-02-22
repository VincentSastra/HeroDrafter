import HTMLParser.Document;

import HTMLParser.HtmlGetter;
import HTMLParser.InvalidHTMLException;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class HTMLDocumentTest {

    @Test
    public void getHTML() throws Exception {
        System.out.println(HtmlGetter.getHTML(new URL("https://u.gg/lol/tier-list?rank=overall")));
    }

    @Test
    public void removeScriptsTest() throws IOException, InterruptedException, URISyntaxException, InvalidHTMLException {
        Document document = new Document(new URL("https://u.gg/lol/tier-list?rank=overall"));
        System.out.println(document.body.rawText);
    }
}
