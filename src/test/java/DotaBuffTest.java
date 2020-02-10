import HTMLParser.Document;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DotaBuffTest {

    @Test
    public void test() throws IOException {
        String raw = Files.readString(Paths.get("C:\\Users\\vince\\IdeaProjects\\DotaApp\\DotaApp\\src\\test\\java\\Files\\DBRaw"));
        Document document = new Document(raw);
        assertThat(document.rawText).isEqualToIgnoringWhitespace(
                Files.readString(Paths.get("C:\\Users\\vince\\IdeaProjects\\DotaApp\\DotaApp\\src\\test\\java\\Files\\DBTrimmed"))
        );
        assertThat(document.body.rawText).isEqualToIgnoringWhitespace(
                Files.readString(Paths.get("C:\\Users\\vince\\IdeaProjects\\DotaApp\\DotaApp\\src\\test\\java\\Files\\DBBody"))
        );
    }

}
