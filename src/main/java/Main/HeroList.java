package Main;

import javafx.scene.image.Image;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class HeroList {

    private static final HeroList INSTANCE = new HeroList();
    private HashMap<String, Image> heroList = new HashMap<>();

    private HeroList() {

        try {
            Document heroesPage = Jsoup.connect("https://dota2.gamepedia.com/Dota_2_Wiki").get();
            Elements heroesRow = heroesPage.select(".heroentry a");

            for (Element heroRow : heroesRow) {

                String heroName = heroRow.attr("title");
                Image heroIcon = new Image(heroRow.selectFirst("img").attr("src"));

                heroList.put(heroName, heroIcon);
            }
        } catch (IOException ignored) {

        }
    }

    public static HeroList getInstance() { return INSTANCE; }

    public Image getImage(String heroName) {  return heroList.get(heroName); }

    public ArrayList<String> getHeroNames() {
        ArrayList<String> returnList = new ArrayList<>(heroList.keySet());
        Collections.sort(returnList);
        return returnList;
    }

}
