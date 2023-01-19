package org.teslasoft.experiments.cam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface SettingsParser {

    static @Nullable String parseServer(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? settingsManager.getControllerProtocol() + "://" + settingsManager.getControllerAddress() + ":" + settingsManager.getControllerPort() : null;
    }

    static @Nullable String parseCameraStream(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? settingsManager.getCameraProtocol() + "://" + settingsManager.getCameraAddress() + ":" + settingsManager.getCameraPort() + settingsManager.getCameraPath() : null;
    }

    static @Nullable String parseCameraInfo(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? settingsManager.getCameraProtocol() + "://" + settingsManager.getCameraAddress() + ":" + settingsManager.getCameraPort() + settingsManager.getCameraSettings() : null;
    }

    static @Nullable String parseFlashOn(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? settingsManager.getCameraProtocol() + "://" + settingsManager.getCameraAddress() + ":" + settingsManager.getCameraPort() + settingsManager.getCameraController() + "?torch=on" : null;
    }

    static @Nullable String parseFlashOff(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? settingsManager.getCameraProtocol() + "://" + settingsManager.getCameraAddress() + ":" + settingsManager.getCameraPort() + settingsManager.getCameraController() + "?torch=off" : null;
    }

    static @Nullable String parseController(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? settingsManager.getControllerProtocol() + "://" + settingsManager.getControllerAddress() + ":" + settingsManager.getControllerPort() + settingsManager.getControllerPath() : null;
    }

    static @Nullable String parseCmdLeftUp(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getCmdLeftUp() : null;
    }

    static @Nullable String parseCmdHeadLeft(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getCmdHeadLeft() : null;
    }

    static @Nullable String parseCmdLeftDown(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getCmdLeftDown() : null;
    }

    static @Nullable String parseCmdRightUp(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getCmdRightUp() : null;
    }

    static @Nullable String parseCmdHeadRight(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getCmdHeadRight() : null;
    }

    static @Nullable String parseCmdRightDown(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getCmdRightDown() : null;
    }

    static @Nullable String parseI0(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getIndicatorColor0() : null;
    }

    static @Nullable String parseI1(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getIndicatorColor1() : null;
    }

    static @Nullable String parseI2(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getIndicatorColor2() : null;
    }

    static @Nullable String parseI3(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getIndicatorColor3() : null;
    }

    static @Nullable String parseI4(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getIndicatorColor4() : null;
    }

    static @Nullable String parseI5(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getIndicatorColor5() : null;
    }

    static @Nullable String parseI6(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getIndicatorColor6() : null;
    }

    static @Nullable String parseI7(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseController(settingsManager) + settingsManager.getIndicatorColor7() : null;
    }

    static @Nullable String parseSensors(@NonNull SettingsManager settingsManager) {
        return settingsManager.hasSettings() ? parseServer(settingsManager) + "/api/v1/sensors": null;
    }
}
