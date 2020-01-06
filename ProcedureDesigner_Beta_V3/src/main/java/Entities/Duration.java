package Entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Duration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column( name = "time")
    private String time;

    public Duration(){}

    public Duration(int id, String  value) {
        this.id = id;
        this.time = value;
    }
    public Duration(Duration d) {
        this.id = d.id;
        this.time = d.time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String  getValue() {
        return time;
    }

    public void setValue(String  value) {
        this.time = value;
    }

}
