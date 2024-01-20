package application.domen;

import application.models.ChristmasTree;
import application.models.IChristmasTree;
import application.models.TreeDecorator;
import application.services.Girland;
import application.services.Presents;
import application.services.Star;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.util.ResourceBundle;

public final class MainController implements Initializable {
    @FXML
    private Pane paneTree;
    private IChristmasTree tree = new ChristmasTree();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.paneTree.toFront();
        this.tree.draw(this.paneTree);
    }
    private final class GenericClass<T extends TreeDecorator> {
        private final Class<T> genericType;
        public GenericClass(Class<T> type) {
            super();
            this.genericType = type;
        }
        public Class<T> getGenericType() { return this.genericType; }
    }
    private <T extends TreeDecorator> IChristmasTree decorateTree(Class<T> newDecorator) {
        var decorator = this.tree;
        while (TreeDecorator.class.isAssignableFrom(decorator.getClass())) {
            if (decorator.getClass().equals(new GenericClass<T>(newDecorator).getGenericType())) {
                return this.tree;
            }
            decorator = ((TreeDecorator)decorator).getTree();
        }
        try { return newDecorator.getConstructor(IChristmasTree.class).newInstance(this.tree); }
        catch (Exception error) { throw new RuntimeException(error); }
    }
    @FXML
    public void addPresentButtonHandler(ActionEvent actionEvent) {
        this.paneTree.getChildren().clear();
        (this.tree = new Presents(this.tree)).draw(this.paneTree);
    }
    @FXML
    public void addStarButtonHandler(ActionEvent actionEvent) {
        this.paneTree.getChildren().clear();
        (this.tree = new Star(this.tree)).draw(this.paneTree);
    }
    @FXML
    public void addGirlandButtonHandler(ActionEvent actionEvent) {
//        this.paneTree.getChildren().clear();
//        (this.tree = new Girland(this.tree)).draw(this.paneTree);
        (this.tree = this.decorateTree(Girland.class)).draw(this.paneTree);
    }
    @FXML
    public void clearAllButtonClickedHandler(ActionEvent actionEvent) {
        this.paneTree.getChildren().clear();
        (this.tree = new ChristmasTree()).draw(this.paneTree);
    }
    @FXML
    public void addAllButtonClickedHandler(ActionEvent actionEvent) {
    }
}