package Main;

import HTMLParser.Document;
import HTMLParser.Element.Table;
import HTMLParser.Element.TableEntry;
import HTMLParser.HtmlGetter;
import HTMLParser.InvalidHTMLException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
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

        Table winrateTable = heroPage.body.tables.get(3);

        HashMap<String, Double> winRateMap = new HashMap<>();

        for(ArrayList<TableEntry> tableRow: winrateTable.getBody()) {
            String name = tableRow.get(0).getDataValue();
            Double winRate = 100.0 - Double.parseDouble(tableRow.get(3).getDataValue());

            winRateMap.put(name, winRate);
        }

        return winRateMap;

    }

    /**
     *
     * @param heroName
     * @return a HashMap of every other hero and its advantage against @param heroName
     * @throws IOException if connection goes wrong
     */
    public static HashMap<String, Double> getAdvantage(String heroName) throws IOException {

        Document heroPage = getPage(heroName);

        Table winrateTable = heroPage.body.tables.get(3);

        HashMap<String, Double> winRateMap = new HashMap<>();

        for(ArrayList<TableEntry> tableRow: winrateTable.getBody()) {
            String name = tableRow.get(0).getDataValue();
            Double winRate = 0 - Double.parseDouble(tableRow.get(2).getDataValue());

            winRateMap.put(name, winRate);
        }

        return winRateMap;

    }

    /**
     * @param heroName The hero name to lookup
     * @return the url
     * https://www.dotabuff.com/heroes/<heroName>/counters?date=week
     * https://www.dotabuff.com/heroes/lina/counters?date=week
     */
    private static URL getURL(String heroName) throws MalformedURLException {
        return new URL("https://www.dotabuff.com/heroes/" + heroName.toLowerCase().replace(" ", "-").replace("'", "") + "/counters?date=week");
    }

    private static Document getPage(String heroName) throws IOException {
        int count = 0;
        int maxTries = 10;
        while(true) {
            try {
                return new Document(getURL(heroName));
            } catch (IOException e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {

                }
                if (++count == maxTries) throw e;
            } catch (InvalidHTMLException | URISyntaxException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
