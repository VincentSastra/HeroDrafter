import HTMLParser.*;

import org.junit.Before;
import org.junit.Test;


import java.lang.StringBuffer;



import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class HTMLParserTest {

    public String html1;

    @Before
    public void createHTML() {
        // Test copied from https://www.dummies.com/web-design-development/site-development/a-sample-web-page-in-html/
        html1 = "<html>\n" +
                "<!-- Text between angle brackets is an HTML tag and is not displayed.\n" +
                "Most tags, such as the HTML and /HTML tags that surround the contents of\n" +
                "a page, come in pairs; some tags, like HR, for a horizontal rule, stand \n" +
                "alone. Comments, such as the text you're reading, are not displayed when\n" +
                "the Web page is shown. The information between the HEAD and /HEAD tags is \n" +
                "not displayed. The information between the BODY and /BODY tags is displayed.-->\n" +
                "<head>\n" +
                "<title>Enter a title, displayed at the top of the window.</title>\n" +
                "</head>\n" +
                "<!-- The information between the BODY and /BODY tags is displayed.-->\n" +
                "<body>\n" +
                "<h1>Enter the main heading, usually the same as the title.</h1>\n" +
                "<p>Be <b>bold</b> in stating your key points. Put them in a list: </p>\n" +
                "<ul>\n" +
                "<li>The first item in your list</li>\n" +
                "<li>The second item; <i>italicize</i> key words</li>\n" +
                "</ul>\n" +
                "<p>Improve your image by including an image. </p>\n" +
                "<p><img src=\"http://www.mygifs.com/CoverImage.gif\" alt=\"A Great HTML Resource\"></p>\n" +
                "<p>Add a link to your favorite <a href=\"https://www.dummies.com/\">Web site</a>.\n" +
                "Break up your page with a horizontal rule or two. </p>\n" +
                "<hr>\n" +
                "<p>Finally, link to <a href=\"page2.html\">another page</a> in your own Web site.</p>\n" +
                "<!-- And add a copyright notice.-->\n" +
                "<p>&#169; Wiley Publishing, 2011</p>\n" +
                "</body>\n" +
                "</html>";
    }

    @Test
    public void removeScriptsTest() {
        String html = "<script>in script</script>out script";
        assertThat(html.replaceAll("<script(.*)/script>", "")).isEqualTo("out script");
    }
}
