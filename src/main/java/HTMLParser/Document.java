package HTMLParser;

import HTMLParser.Element.Body;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Main class of the parser
 *  Each HTML docs will be broken down into its body
 *  Then its body will be broken down into tables
 *  Since all the data we need are in those tables, there is no further parsing required
 */
public class Document {
    public Body body; // The text enclosed by body tags
    public String rawText;

    public Document(String string) throws InvalidHTMLException {

        rawText = removeBloat(string);
        Pattern pattern = Pattern.compile("(?s)<body>(.*?)</body>");
        Matcher matcher = pattern.matcher(rawText);
        if (matcher.find())
        {
            body = new Body(matcher.group(0));
        } else {
            throw new InvalidHTMLException("Body not defined");
        }

    }

    public Document(URL url) throws InterruptedException, IOException, URISyntaxException, InvalidHTMLException {

        this(HtmlGetter.getHTML(url));

    }

    /**
     * Removes bloat from the html code
     */
    public static String removeBloat (String s) {
        return s.replaceAll("(?s)<script(.*?)/script>", "") // Removes scripts
                .replaceAll("(?s)<!--(.*?)-->", "") // Removes comments
                .replaceAll("style=\"[^\"]*\"", ""); // Removes styles
    }

}
