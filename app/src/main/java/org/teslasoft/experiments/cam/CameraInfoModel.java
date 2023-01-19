package org.teslasoft.experiments.cam;

import java.util.ArrayList;

public class CameraInfoModel {
    private CameraBatteryModel battery;
    private boolean torch;
    private int temperature;
    private int humidity;
    private int pressure;
    private int light;
    private ArrayList<Integer> gravity;
    private ArrayList<Integer> rotation;

    public CameraInfoModel(CameraBatteryModel battery, boolean torch, int temperature, int humidity, int pressure, int light, ArrayList<Integer> gravity, ArrayList<Integer> rotation) {
        this.battery = battery;
        this.torch = torch;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.light = light;
        this.gravity = gravity;
        this.rotation = rotation;
    }

    public CameraBatteryModel getBattery() {
        return battery;
    }

    public void setBattery(CameraBatteryModel battery) {
        this.battery = battery;
    }

    public boolean isTorch() {
        return torch;
    }

    public void setTorch(boolean torch) {
        this.torch = torch;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public ArrayList<Integer> getGravity() {
        return gravity;
    }

    public void setGravity(ArrayList<Integer> gravity) {
        this.gravity = gravity;
    }

    public ArrayList<Integer> getRotation() {
        return rotation;
    }

    public void setRotation(ArrayList<Integer> rotation) {
        this.rotation = rotation;
    }
}
