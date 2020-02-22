package HTMLParser.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableEntry {

    String rawText;
    String dataValue = "";
    String tag;
    String image = "";

    public TableEntry(String rawText) {
        this.rawText = rawText;

        Pattern pattern = Pattern.compile("(?s)<td( [^>]*)?>");
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            tag = matcher.group();
            Pattern dataValuePattern = Pattern.compile("data-value=\"[^\"]*\"");
            Matcher dataValueMatcher = dataValuePattern.matcher(matcher.group());

            if (dataValueMatcher.find()) {
                dataValue = dataValueMatcher.group().substring(12, dataValueMatcher.group().length() - 1);
            }
        }

        Pattern imgPattern = Pattern.compile("(?s)<img( [^>]*)?>");
        Matcher imgMatcher = imgPattern.matcher(rawText);

        if (imgMatcher.find()) {
            Pattern srcPattern = Pattern.compile("src=\"[^\"]*\"");
            Matcher srcMatcher = srcPattern.matcher(imgMatcher.group());

            if (srcMatcher.find()) {
                image = srcMatcher.group().substring(5, srcMatcher.group().length() - 1);
            }
        }

    }

    public String getDataValue() {
        return dataValue;
    }

    public String getTag() {
        return tag;
    }

    public String getImage() {
        return image;
    }
}
