package fr.android.wearther.data;

public class Cloth {
    private String name;
    private BodyPart bodyPart;
    private float minTemperature;
    private float maxTemperature;

    public Cloth(String name, BodyPart bodyPart, float minTemperature, float maxTemperature) {
        this.name = name;
        this.bodyPart = bodyPart;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public String getName() {
        return name;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }
}
