package HTMLParser;

import HTMLParser.Element.Body;
import HTMLParser.Element.Head;
import javafx.scene.shape.DrawMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    public Body body;
    public Head head;
    public String rawText;

    public Document(URL url) {

        try  {

            rawText = new HtmlGetter().getHTML(url);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
