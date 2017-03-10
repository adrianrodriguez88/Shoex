package mx.adrianbrito.shoex.domain;

/**
 * Created by AdrianRodriguez on 09/03/17.
 */

public class Bank {

    //@SerializedName("id")
    String uuid;
    //@SerializedName("description")
    String description;

    String img;


    public Bank(String uuid, String description, String img) {
        this.description = description;
        this.uuid = uuid;
        this.img = img;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() { return img; }

    public void setImg(String img) { this.img = img; }

}
