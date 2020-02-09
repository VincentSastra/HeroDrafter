import HTMLParser.Element.Table;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class TableTest {

    @Test
    public void test1() {

        Table table = new Table(" <table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <th>Firstname</th>\n" +
                "    <th>Lastname</th>\n" +
                "    <th>Age</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Jill</td>\n" +
                "    <td>Smith</td>\n" +
                "    <td>50</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Eve</td>\n" +
                "    <td>Jackson</td>\n" +
                "    <td>94</td>\n" +
                "  </tr>\n" +
                "</table> ");

        ArrayList<String> head = new ArrayList<>();
        head.add("Firstname");
        head.add("Lastname");
        head.add("Age");

        assertThat(table.getHead()).isEqualTo(head);

        ArrayList<String> first = new ArrayList<>();
        first.add("Jill");
        first.add("Smith");
        first.add("50");

        assertThat(table.getBody().contains(first));

    }


}
