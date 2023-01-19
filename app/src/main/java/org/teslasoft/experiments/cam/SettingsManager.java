package org.teslasoft.experiments.cam;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {
    private final SharedPreferences settings;

    private String controllerProtocol;
    private String cameraProtocol;
    private String vncProtocol;
    private String controllerPort;
    private String cameraPort;
    private String vncPort;
    private String controllerAddress;
    private String cameraAddress;
    private String vncAddress;
    private String controllerPath;
    private String cameraPath;
    private String cameraSettings;
    private String cameraController;
    private String vncPath;
    private boolean vncMode;
    private String cmdLeftUp;
    private String cmdHeadLeft;
    private String cmdLeftDown;
    private String cmdRightUp;
    private String cmdHeadRight;
    private String cmdRightDown;
    private String indicatorColor0; // Red
    private String indicatorColor1; // Green
    private String indicatorColor2; // Blue
    private String indicatorColor3; // Cyan
    private String indicatorColor4; // Magenta
    private String indicatorColor5; // Yellow
    private String indicatorColor6; // White
    private String indicatorColor7; // Disabled (black)

    public SettingsManager(Activity context) {

        settings = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        controllerProtocol = settings.getString("controller_protocol", "http");
        cameraProtocol = settings.getString("camera_protocol", "http");
        vncProtocol = settings.getString("vnc_protocol", "http");
        controllerPort = settings.getString("controller_port", "-1");
        cameraPort = settings.getString("camera_port", "-1");
        vncPort = settings.getString("vnc_port", "-1");
        controllerAddress = settings.getString("controller_address", "10.3.141.1");
        cameraAddress = settings.getString("camera_address", "10.3.141.56");
        vncAddress = settings.getString("vnc_address", "10.3.141.1");
        controllerPath = settings.getString("controller_path", "/data/controller.py");
        cameraPath = settings.getString("camera_path", "/video/mjpeg");
        cameraSettings = settings.getString("camera_settings", "/sensors");
        cameraController = settings.getString("camera_controller", "/control");
        vncPath = settings.getString("vnc_path", "/vnc.html");
        vncMode = settings.getBoolean("vnc_mode", false);
        cmdLeftUp = settings.getString("cmd_left_up", "?command=controller&data=left_up");
        cmdHeadLeft = settings.getString("cmd_head_left", "?command=controller&data=head_left");
        cmdLeftDown = settings.getString("cmd_left_down", "?command=controller&data=left_down");
        cmdRightUp = settings.getString("cmd_right_up", "?command=controller&data=cmd_right_up");
        cmdHeadRight = settings.getString("cmd_head_right", "?command=controller&data=cmd_head_right");
        cmdRightDown = settings.getString("cmd_right_down", "?command=controller&data=cmd_right_down");
        indicatorColor0 = settings.getString("indicator_color_0", "?command=color&data=red");
        indicatorColor1 = settings.getString("indicator_color_1", "?command=color&data=green");
        indicatorColor2 = settings.getString("indicator_color_2", "?command=color&data=blue");
        indicatorColor3 = settings.getString("indicator_color_3", "?command=color&data=cyan");
        indicatorColor4 = settings.getString("indicator_color_4", "?command=color&data=magenta");
        indicatorColor5 = settings.getString("indicator_color_5", "?command=color&data=yellow");
        indicatorColor6 = settings.getString("indicator_color_6", "?command=color&data=white");
        indicatorColor7 = settings.getString("indicator_color_7", "?command=color&data=disabled");
    }

    public boolean hasSettings() {
        try {
            return !(settings.getString("controller_port", null) == null || settings.getString("controller_port", "-1").equals("-1"));
        } catch (Exception e) {
            return false;
        }
    }

    private void updateSettings(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getControllerProtocol() {
        return controllerProtocol;
    }

    public void setControllerProtocol(String controllerProtocol) {
        updateSettings("controller_protocol", controllerProtocol);
        this.controllerProtocol = controllerProtocol;
    }

    public String getCameraProtocol() {
        return cameraProtocol;
    }

    public void setCameraProtocol(String cameraProtocol) {
        updateSettings("camera_protocol", cameraProtocol);
        this.cameraProtocol = cameraProtocol;
    }

    public String getVncProtocol() {
        return vncProtocol;
    }

    public void setVncProtocol(String vncProtocol) {
        updateSettings("vnc_protocol", vncProtocol);
        this.vncProtocol = vncProtocol;
    }

    public String getControllerPort() {
        return controllerPort;
    }

    public void setControllerPort(String controllerPort) {
        updateSettings("controller_port", controllerPort);
        this.controllerPort = controllerPort;
    }

    public String getCameraPort() {
        return cameraPort;
    }

    public void setCameraPort(String cameraPort) {
        updateSettings("camera_port", cameraPort);
        this.cameraPort = cameraPort;
    }

    public String getVncPort() {
        return vncPort;
    }

    public void setVncPort(String vncPort) {
        updateSettings("vnc_port", vncPort);
        this.vncPort = vncPort;
    }

    public String getControllerAddress() {
        return controllerAddress;
    }

    public void setControllerAddress(String controllerAddress) {
        updateSettings("controller_address", controllerAddress);
        this.controllerAddress = controllerAddress;
    }

    public String getCameraAddress() {
        return cameraAddress;
    }

    public void setCameraAddress(String cameraAddress) {
        updateSettings("camera_address", cameraAddress);
        this.cameraAddress = cameraAddress;
    }

    public String getVncAddress() {
        return vncAddress;
    }

    public void setVncAddress(String vncAddress) {
        updateSettings("vnc_address", vncAddress);
        this.vncAddress = vncAddress;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        updateSettings("controller_path", controllerPath);
        this.controllerPath = controllerPath;
    }

    public String getCameraPath() {
        return cameraPath;
    }

    public void setCameraPath(String cameraPath) {
        updateSettings("camera_path", cameraPath);
        this.cameraPath = cameraPath;
    }

    public String getCameraSettings() {
        return cameraSettings;
    }

    public void setCameraSettings(String cameraSettings) {
        updateSettings("camera_settings", cameraSettings);
        this.cameraSettings = cameraSettings;
    }

    public String getCameraController() {
        return cameraController;
    }

    public void setCameraController(String cameraController) {
        updateSettings("camera_controller", cameraController);
        this.cameraController = cameraController;
    }

    public String getVncPath() {
        return vncPath;
    }

    public void setVncPath(String vncPath) {
        updateSettings("vnc_path", vncPath);
        this.vncPath = vncPath;
    }

    public boolean isVncMode() {
        return vncMode;
    }

    public void setVncMode(boolean vncMode) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("vnc_mode", vncMode);
        editor.apply();
        this.vncMode = vncMode;
    }

    public String getCmdLeftUp() {
        return cmdLeftUp;
    }

    public void setCmdLeftUp(String cmdLeftUp) {
        updateSettings("cmd_left_up", cmdLeftUp);
        this.cmdLeftUp = cmdLeftUp;
    }

    public String getCmdHeadLeft() {
        return cmdHeadLeft;
    }

    public void setCmdHeadLeft(String cmdHeadLeft) {
        updateSettings("cmd_head_left", cmdHeadLeft);
        this.cmdHeadLeft = cmdHeadLeft;
    }

    public String getCmdLeftDown() {
        return cmdLeftDown;
    }

    public void setCmdLeftDown(String cmdLeftDown) {
        updateSettings("cmd_left_down", cmdLeftDown);
        this.cmdLeftDown = cmdLeftDown;
    }

    public String getCmdRightUp() {
        return cmdRightUp;
    }

    public void setCmdRightUp(String cmdRightUp) {
        updateSettings("cmd_right_up", cmdRightUp);
        this.cmdRightUp = cmdRightUp;
    }

    public String getCmdHeadRight() {
        return cmdHeadRight;
    }

    public void setCmdHeadRight(String cmdHeadRight) {
        updateSettings("cmd_head_right", cmdHeadRight);
        this.cmdHeadRight = cmdHeadRight;
    }

    public String getCmdRightDown() {
        return cmdRightDown;
    }

    public void setCmdRightDown(String cmdRightDown) {
        updateSettings("cmd_right_down", cmdRightDown);
        this.cmdRightDown = cmdRightDown;
    }

    public String getIndicatorColor0() {
        return indicatorColor0;
    }

    public void setIndicatorColor0(String indicatorColor0) {
        updateSettings("indicator_color_0", indicatorColor0);
        this.indicatorColor0 = indicatorColor0;
    }

    public String getIndicatorColor1() {
        return indicatorColor1;
    }

    public void setIndicatorColor1(String indicatorColor1) {
        updateSettings("indicator_color_1", indicatorColor1);
        this.indicatorColor1 = indicatorColor1;
    }

    public String getIndicatorColor2() {
        return indicatorColor2;
    }

    public void setIndicatorColor2(String indicatorColor2) {
        updateSettings("indicator_color_2", indicatorColor2);
        this.indicatorColor2 = indicatorColor2;
    }

    public String getIndicatorColor3() {
        return indicatorColor3;
    }

    public void setIndicatorColor3(String indicatorColor3) {
        updateSettings("indicator_color_3", indicatorColor3);
        this.indicatorColor3 = indicatorColor3;
    }

    public String getIndicatorColor4() {
        return indicatorColor4;
    }

    public void setIndicatorColor4(String indicatorColor4) {
        updateSettings("indicator_color_4", indicatorColor4);
        this.indicatorColor4 = indicatorColor4;
    }

    public String getIndicatorColor5() {
        return indicatorColor5;
    }

    public void setIndicatorColor5(String indicatorColor5) {
        updateSettings("indicator_color_5", indicatorColor5);
        this.indicatorColor5 = indicatorColor5;
    }

    public String getIndicatorColor6() {
        return indicatorColor6;
    }

    public void setIndicatorColor6(String indicatorColor6) {
        updateSettings("indicator_color_6", indicatorColor6);
        this.indicatorColor6 = indicatorColor6;
    }

    public String getIndicatorColor7() {
        return indicatorColor7;
    }

    public void setIndicatorColor7(String indicatorColor7) {
        updateSettings("indicator_color_7", indicatorColor7);
        this.indicatorColor7 = indicatorColor7;
    }
}
