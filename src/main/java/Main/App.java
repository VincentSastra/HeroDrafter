package Main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class App extends Application {

    @FXML
    public Button addHero;

    @FXML
    public ListView<String> heroSelect;
    public DotaHeroList heroList = DotaHeroList.getInstance();

    @FXML
    public ListView<String> enemyList;

    @FXML
    public Button resetButton;

    @FXML
    public Button sortButton;

    @FXML
    public Label systemOut;

    public Drafter drafter = new Drafter();
    public ObservableList<String> enemyNames = FXCollections.observableList(new ArrayList<>());
    private ObservableList<String> heroNames = FXCollections.observableList(heroList.getHeroNames());


    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:///C:/Users/vince/IdeaProjects/DotaApp/DotaApp/src/main/resources/App.fxml"));
        loader.setController(this);

        Parent root = loader.load();

        setHeroSelectCell();
        setEnemyListCell();
        configureButton();

        enemyList.setItems(enemyNames);
        heroSelect.setItems(heroNames);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hero Drafter");
        stage.show();

    }

    private void setHeroSelectCell() {

        heroSelect.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String heroName, boolean empty) {
                super.updateItem(heroName, empty);

                DecimalFormat winrateFormat = new DecimalFormat("#.00");

                HBox hBox = new HBox(5);
                hBox.setAlignment(Pos.CENTER_LEFT);

                hBox.getChildren().addAll(
                        new ImageView(heroList.getImage(heroName)),
                        new Label(heroName),
                        new Label( winrateFormat.format(drafter.getMatchups(heroName)) + "%")
                );

                setGraphic(hBox);
            }

        });

        heroSelect.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                addHero();
        });



    }

    private void setEnemyListCell() {

        enemyList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String heroName, boolean empty) {
                super.updateItem(heroName, empty);

                HBox hBox = new HBox(5);
                hBox.setAlignment(Pos.CENTER_LEFT);

                hBox.getChildren().addAll(
                        new ImageView(heroList.getImage(heroName))
                );

                setGraphic(hBox);
            }

        });

        enemyList.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                removeHero();
        });

    }

    private void configureButton() {

        addHero.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addHero();
            }
        });
        sortButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(sortButton.getText().equals("Sort&#10;Win-rate") || sortButton.getText().equals("Sort By\nWin-rate")) {
                    heroNames.sort(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return Double.compare(drafter.getMatchups(o2), drafter.getMatchups(o1));
                        }
                    });
                    sortButton.setText("Sort By\nNames");
                } else {
                    heroNames.sort(String::compareTo);
                    sortButton.setText("Sort By\nWin-rate");
                }
            }
        });
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<String> enemyList = drafter.getEnemyList();
                for (String s : enemyList) {
                    removeHero(s);
                }
            }
        });
    }

    private void addHero() {
        if(enemyNames.size() > 4) {
            systemOut.setText("Enemy Full");
            return;
        }

        systemOut.setText("Loading Data");

        try {
            ObservableList<String> selectedHeroes = heroSelect.getSelectionModel().getSelectedItems();
            for (String s : selectedHeroes) {
                drafter.addEnemy(s);
                enemyNames.add(s);
                heroNames.remove(s);
                systemOut.setText("Successfully Added: " + s);
            }
        } catch (IOException e) {
            systemOut.setText("Connection Error");
        }
    }

    private void removeHero() {
        ObservableList<String> selectedHeroes = enemyList.getSelectionModel().getSelectedItems();
        for (String s : selectedHeroes) {
            removeHero(s);
        }
    }

    private void removeHero(String s) {
        try {
            drafter.removeEnemy(s);
            heroNames.add(s);
            enemyNames.remove(s);
            systemOut.setText("Successfully Removed: " + s);
        } catch (IOException e) {
            systemOut.setText("Connection Error");
        }
    }

}
