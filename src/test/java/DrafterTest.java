import Main.Drafter;
import HTMLParser.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;


public class DrafterTest {


    @Test
    public void test1() throws IOException {

        Drafter drafter = new Drafter();

        drafter.addEnemy("Arc Warden");

        HashMap<String, Double> rmap = drafter.getMatchups();
        double linaWR = rmap.get("Lina");

        drafter.addEnemy("Chen");
        drafter.removeEnemy("Chen");

        assertThat(linaWR).isEqualTo(drafter.getMatchups().get("Lina"), within(0.01));
        HashMap<String, Double> matchups = drafter.getMatchups();
        assertThat(drafter.getMatchups().keySet()).doesNotContain("Arc Warden");
        assertThat(drafter.getMatchups().keySet()).contains("Chen");

        drafter.removeEnemy("Arc Warden");
        drafter.addEnemy("Arc Warden");

        assertThat(linaWR).isEqualTo(drafter.getMatchups().get("Lina"), within(0.1));

    }

}
