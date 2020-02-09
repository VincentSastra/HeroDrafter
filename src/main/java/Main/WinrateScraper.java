package Main;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class WinrateScraper {

    private WinrateScraper() {}

    /**
     *
     * @param heroName
     * @return a HashMap of every other hero and its winrate against @param heroName
     * @throws IOException if connection goes wrong
     */
    public static HashMap<String, Double> getWinRate(String heroName) throws IOException {

        Document heroPage = getPage(heroName);

        Elements heroRow = heroPage.select("tr[data-link-to^=/heroes/]");

        HashMap<String, Double> winRateMap = new HashMap<>();

        for(Element row : heroRow) {
            String name = row.selectFirst(".cell-icon").attr("data-value");
            Element winRateElement = row.selectFirst("td:has(.bar):has(.segment-win)");
            Double winRate = 100.0 - Double.parseDouble(winRateElement.attr("data-value"));

            winRateMap.put(name, winRate);
        }

        return winRateMap;

    }

    /**
     * @param heroName The hero name to lookup
     * @return the url
     * https://www.dotabuff.com/heroes/<heroName>/counters?date=week
     */
    private static String getURL(String heroName) {
        return "http://www.dotabuff.com/heroes/" + heroName.toLowerCase().replace(" ", "-") + "/counters?date=week";
    }

    private static Document getPage(String heroName) throws IOException {
        int count = 0;
        int maxTries = 10;
        while(true) {
            try {
                return Jsoup.connect(getURL(heroName)).get();
            } catch (IOException e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {

                }
                if (++count == maxTries) throw e;
            }
        }
    }

}
