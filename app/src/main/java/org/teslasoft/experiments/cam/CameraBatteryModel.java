package org.teslasoft.experiments.cam;

public class CameraBatteryModel {
    private String connection;
    private int level;

    public CameraBatteryModel(String connection, int level) {
        this.connection = connection;
        this.level = level;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
