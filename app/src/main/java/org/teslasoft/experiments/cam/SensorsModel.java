package org.teslasoft.experiments.cam;

public class SensorsModel {
    private double distance;

    SensorsModel(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
