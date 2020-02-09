package HTMLParser.Element;

import java.util.ArrayList;

public interface Element {

    public ArrayList<Element> getChild();

    public String getTag();

    public String raw();

}
