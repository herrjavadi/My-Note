package mehdi.android.com.notapp;

import java.io.Serializable;

public class MyNot implements Serializable {
    private String title;
    private String writeBox;

  public MyNot() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriteBox() {
        return writeBox;
    }

    public void setWriteBox(String writeBox) {
        this.writeBox = writeBox;
    }
}
