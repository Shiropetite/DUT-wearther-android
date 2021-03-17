package fr.android.wearther.data;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Cloth> clothes;

    public User() {}

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;

        this.clothes = new ArrayList<>();
        this.clothes.add(new Cloth("bonnet",BodyPart.tete,-10,5));
        this.clothes.add(new Cloth("echarpe",BodyPart.cou,-10,5));
        this.clothes.add(new Cloth("manteau",BodyPart.haut,-10,10));
        this.clothes.add(new Cloth("pull",BodyPart.haut,-10,9));
        this.clothes.add(new Cloth("shirt",BodyPart.haut,10,29));
        this.clothes.add(new Cloth("debardeur",BodyPart.haut,30,40));
        this.clothes.add(new Cloth("gants",BodyPart.main,-10,5));
        this.clothes.add(new Cloth("pantalon",BodyPart.bas,-10,25));
        this.clothes.add(new Cloth("short",BodyPart.bas,26,40));
        this.clothes.add(new Cloth("bottes",BodyPart.pieds,-10,9));
        this.clothes.add(new Cloth("baskets",BodyPart.pieds,10,40));
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Cloth> getClothes() {
        return clothes;
    }
}
