module Main {
    requires javafx.controls;
    requires javafx.fxml;
    opens Main to javafx.graphics, javafx.fxml;
    requires org.jsoup;
    requires java.net.http;
    exports HTMLParser.Element;
    exports HTMLParser;
}