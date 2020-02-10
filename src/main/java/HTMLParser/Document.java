package HTMLParser;

import HTMLParser.Element.Body;
import HTMLParser.Element.Head;
import javafx.scene.shape.DrawMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    public Body body;
    public Head head;
    public String rawText;

    public Document(String string) {
        rawText = removeBloat(string);
        Pattern pattern = Pattern.compile("(?s)<body>(.*?)</body>");
        Matcher matcher = pattern.matcher(rawText);
        if (matcher.find())
        {
            body = new Body(matcher.group(0));
        };

    }

    public Document(URL url) throws InterruptedException, IOException, URISyntaxException {

        this(new HtmlGetter().getHTML(url));

    }

    public static String removeBloat (String s) {
        return s.replaceAll("(?s)<script(.*?)/script>", "")
                .replaceAll("(?s)<!--(.*?)-->", "")
                .replaceAll("style=\"[^\"]*\"", "");
    }

}
