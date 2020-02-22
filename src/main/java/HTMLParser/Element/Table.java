package HTMLParser.Element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A table in HTML consist of a head and a body
 * Ex: https://www.w3schools.com/html/html_tables.asp
 *
 * When given a correct html table code, this object will correctly
 * parse its head and its body
 */
public class Table {

    String rawText;

    ArrayList<TableEntry> thead = new ArrayList<>();
    HashSet<ArrayList<TableEntry>> tbody = new HashSet<>();

    public Table(String rawText) {

        this.rawText = rawText;

        generateHead();
        generateBody();

    }

    public void generateHead() {

        Pattern pattern = Pattern.compile("(?s)<th( [^>]*)?>(.*?)</th>");
        Matcher matcher = pattern.matcher(rawText);

        while(matcher.find()) {
            TableEntry tableEntry = new TableEntry(matcher.group());
            thead.add(tableEntry);
        }

    }

    public void generateBody() {

        Pattern row = Pattern.compile("(?s)<tr( [^>]*)?>(.*?)</tr>");

        Matcher rowMatcher = row.matcher(rawText);

        while(rowMatcher.find()) {

            String rowString = rowMatcher.group();
            Pattern entry = Pattern.compile("(?s)<td( [^>]*)?>(.*?)</td>");
            Matcher colMatcher = entry.matcher(rowString);

            ArrayList<TableEntry> colList = new ArrayList<>();

            while(colMatcher.find()) {
                TableEntry tableEntry = new TableEntry(colMatcher.group());
                colList.add(tableEntry);
            }

            if(!colList.isEmpty())
                tbody.add(colList);

        }

    }

    public ArrayList<TableEntry> getHead() {
        return new ArrayList<>(thead);
    }

    public HashSet<ArrayList<TableEntry>> getBody() {
        return new HashSet<>(tbody);
    }

}
