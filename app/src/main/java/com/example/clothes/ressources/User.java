package com.example.clothes.ressources;

import java.util.ArrayList;

public class User {
    private ArrayList<Cloth> clothes;

    public User() {
        this.clothes = new ArrayList<Cloth>();
    }

    public ArrayList<Cloth> getClothes() {
        return clothes;
    }
}
