package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  Given a composition of enemy heroes, shows the winrate and advantage of other heroes against the enemy team.
 *  The composition of enemy heroes can change by using the addEnemy and removeEnemy method
 *
 *  addEnemy and removeEnemy mutates the enemy composition while
 *  getMatchups(hero) and getAdvantage(hero) returns the hero's
 *
 *
 *  winrate is how often will the selected hero wins against the enemy composition
 *  advantage is how much more likely a selected hero will win because of the enemy composition
 */
public class Drafter {

    private Map<String, Double> matchups = new HashMap<>();
    private Map<String, Double> advantage = new HashMap<>();
    public ArrayList<String> enemyList = new ArrayList<>();

    public Drafter() {}

    public void addEnemy(String enemyName) throws IOException {

        if (!enemyList.contains(enemyName)) {

            HashMap<String, Double> enemyMatchups = WinrateScraper.getWinRate(enemyName);
            enemyMatchups.forEach((k, v) -> matchups.merge(k, v, Double::sum));

            HashMap<String, Double> enemyAdvantage = WinrateScraper.getAdvantage(enemyName);
            enemyAdvantage.forEach((k, v) -> advantage.merge(k, v, Double::sum));

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

    public HashMap<String, Double> getAllMatchups() {

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

        // The default value is 50%
        if (enemyList.size() == 0) {
            return 50.0;
        }

        if (matchups.containsKey(heroName)) {
            return matchups.get(heroName) / enemyList.size();
        }

        return 50.0;

    }

    public double getAdvantage(String heroName) {

        // The default value is 0%
        if (enemyList.size() == 0) {
            return 0.0;
        }

        if (matchups.containsKey(heroName)) {
            return advantage.get(heroName) / enemyList.size();
        }

        return 0.0;

    }
}
