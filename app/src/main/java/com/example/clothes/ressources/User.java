package com.example.clothes.ressources;

import java.util.ArrayList;

public class User {
    private ArrayList<Cloth> clothes;

    public User() {
        this.clothes = new ArrayList<Cloth>();
        this.clothes.add(new Cloth("bonnet",BodyPart.tete,-10,10));
        this.clothes.add(new Cloth("echarpe",BodyPart.tete,-10,10));
        this.clothes.add(new Cloth("tshirt", BodyPart.haut,20,40));
        this.clothes.add(new Cloth("pull", BodyPart.haut,0,20));
        this.clothes.add(new Cloth("gant", BodyPart.mains,-10,10));
        this.clothes.add(new Cloth("pantalon", BodyPart.jambes,0,30));
        this.clothes.add(new Cloth("chaussure", BodyPart.pieds,10,20));
        this.clothes.add(new Cloth("botte", BodyPart.pieds,-10,10));
    }

    public ArrayList<Cloth> getClothes() {
        return clothes;
    }
}
