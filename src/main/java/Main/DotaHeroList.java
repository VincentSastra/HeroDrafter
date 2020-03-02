package Main;

import HTMLParser.Document;
import HTMLParser.Element.Table;
import HTMLParser.Element.TableEntry;
import HTMLParser.InvalidHTMLException;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *  A list that stores all the hero names and image.
 *
 */
public class DotaHeroList {

    private static final DotaHeroList INSTANCE = new DotaHeroList();
    private HashMap<String, Image> heroList = new HashMap<>();

    private DotaHeroList() {

        try {
            Document heroesPage = new Document(new URL("https://www.dotabuff.com/heroes/winning"));
            Table heroesTable = heroesPage.body.tables.get(0);

            for (ArrayList<TableEntry> tableRow : heroesTable.getBody()) {

                String heroName = tableRow.get(0).getDataValue();
                Image heroIcon = new Image("https://www.dotabuff.com" + tableRow.get(0).getImage());

                heroList.put(heroName, heroIcon);
            }
        } catch (IOException ignored) {

        } catch (InvalidHTMLException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static DotaHeroList getInstance() { return INSTANCE; }

    public Image getImage(String heroName) {  return heroList.get(heroName); }

    public ArrayList<String> getHeroNames() {
        ArrayList<String> returnList = new ArrayList<>(heroList.keySet());
        Collections.sort(returnList);
        return returnList;
    }

}
