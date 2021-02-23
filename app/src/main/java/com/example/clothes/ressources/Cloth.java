package com.example.clothes.ressources;

public class Cloth {
    private String name;
    private BodyPart bodyPartCovered;
    // Interval de temperature pour mettre le vetement
    private int minTemperature;
    private int maxTemperature;

    public Cloth(String name, BodyPart bodyPartCovered, int minTemperature, int maxTemperature) {
        this.name = name;
        this.bodyPartCovered = bodyPartCovered;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public String getName() {
        return name;
    }

    public BodyPart getBodyPartCovered() {
        return bodyPartCovered;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }
}
