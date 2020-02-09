package HTMLParser.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Table implements Element {

    ArrayList<Element> children;
    String rawText;

    public Table(String rawText) {
        this.rawText = rawText;
    }

    @Override
    public ArrayList<Element> getChild() {
        return children;
    }

    @Override
    public String getTag() {
        return null;
    }

    @Override
    public String raw() {
        return rawText;
    }

    public ArrayList<String> getHead() {
        return new ArrayList<>();
    }

    public HashSet<ArrayList<String>> getBody() {
        return null;
    }


}
