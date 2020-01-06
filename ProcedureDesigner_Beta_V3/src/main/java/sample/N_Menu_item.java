package sample;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;

import java.io.Serializable;

public class N_Menu_item extends MenuItem implements Serializable {


    public String path;
    public String getPath() {
        return path;
    }
    public N_Menu_item(String text) {
        this(text, null);
    }

    public N_Menu_item(String text, Node graphic) {
        setText(text);
        setGraphic(graphic);
    }
    public void set(String s){
        path=s;

    }
}
