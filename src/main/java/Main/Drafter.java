package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  Given a composition of enemy heroes, shows the winrate of other heroes againt the enemy team.
 *  The composition of enemy heroes can change by using the addEnemy and removeEnemy method
 */
public class Drafter {

    private Map<String, Double> matchups = new HashMap<>();
    public ArrayList<String> enemyList = new ArrayList<>();

    public Drafter() {}

    public void addEnemy(String enemyName) throws IOException {

        if (!enemyList.contains(enemyName)) {

            HashMap<String, Double> enemyMatchups = WinrateScraper.getWinRate(enemyName);
            enemyMatchups.forEach((k, v) -> matchups.merge(k, v, Double::sum));
            enemyList.add(enemyName);

        }
    }

    public void removeEnemy(String enemyName) throws IOException {

        if (enemyList.contains(enemyName)) {
            HashMap<String, Double> enemyMatchups = WinrateScraper.getWinRate(enemyName);
            enemyMatchups.forEach((k, v) -> matchups.merge(k, v, (v1, v2) ->
                    v1 - v2
            ));
            enemyList.remove(enemyName);
        }
    }

    public HashMap<String, Double> getMatchups() {

        return new HashMap<>(matchups.entrySet().stream()
                .filter(x -> !enemyList.contains(x.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue() / enemyList.size()
                )));
    }

    public ArrayList<String> getEnemyList() {

        return new ArrayList<>(enemyList);

    }

    public double getMatchups(String heroName) {

        if (enemyList.size() == 0) {
            return 50.0;
        }

        if (matchups.containsKey(heroName)) {
            return matchups.get(heroName) / enemyList.size();
        }

        return 50.0;

    }

}
