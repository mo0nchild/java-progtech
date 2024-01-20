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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class MainController implements Initializable {
    private IChristmasTree tree = new ChristmasTree();
    @FXML
    private Label stateLabel;
    @FXML
    private Pane paneTree;
    private final ArrayList<String> appliedDecorators = new ArrayList<>();

    private <T extends TreeDecorator> IChristmasTree decorateTree(Class<T> decoratorType) {
        if(this.appliedDecorators.contains(decoratorType.getName())) return this.tree;
        this.appliedDecorators.add(decoratorType.getName());
        try {
            return decoratorType.getConstructor(IChristmasTree.class).newInstance(this.tree);
        }
        catch (Exception errorInfo) { throw new RuntimeException(errorInfo); }
    }
    private void attachableHandlerBehavior(Supplier<IChristmasTree> decoration) {
        this.paneTree.getChildren().clear();
        (this.tree = decoration.get()).draw(this.paneTree);
        if (this.tree instanceof TreeDecorator decorator) {
            this.stateLabel.setText(String.format("Свойства:\n%s", decorator.getState()));
        }
    }
    @FXML
    public void addPresentButtonHandler(ActionEvent actionEvent) {
        this.attachableHandlerBehavior(() -> this.decorateTree(Presents.class));
    }
    @FXML
    public void addStarButtonHandler(ActionEvent actionEvent) {
        this.attachableHandlerBehavior(() -> this.decorateTree(Star.class));
    }
    @FXML
    public void addGirlandButtonHandler(ActionEvent actionEvent) {
        this.attachableHandlerBehavior(() -> this.decorateTree(Girland.class));
    }
    @FXML
    public void clearAllButtonClickedHandler(ActionEvent actionEvent) {
        this.appliedDecorators.clear();
        this.attachableHandlerBehavior(ChristmasTree::new);

        this.stateLabel.setText("Свойства:");
    }
    @FXML
    public void addAllButtonClickedHandler(ActionEvent actionEvent) {
        this.paneTree.getChildren().clear();
        this.tree = this.decorateTree(Girland.class);
        this.tree = this.decorateTree(Presents.class);
        this.tree = this.decorateTree(Star.class);

        if (this.tree instanceof TreeDecorator decorator) {
            this.stateLabel.setText(String.format("Свойства:\n%s", decorator.getState()));
        }
        this.tree.draw(this.paneTree);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.paneTree.toFront();
        this.tree.draw(this.paneTree);
    }
}