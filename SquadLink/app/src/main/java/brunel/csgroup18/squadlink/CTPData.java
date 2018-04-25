package brunel.csgroup18.squadlink;

import java.io.Serializable;

/**
 * Created by Nathan Hoy on 29/03/2018.
 */

public class CTPData implements Serializable{

    private int id;
    private String title;
    private  String text;

    CTPData(int id, String title, String text){
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
