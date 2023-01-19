package org.teslasoft.experiments.cam;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SettingsActivity extends FragmentActivity {

    private TextInputEditText fieldControllerProtocol;
    private TextInputEditText fieldControllerPort;
    private TextInputEditText fieldControllerAddress;
    private TextInputEditText fieldCameraProtocol;
    private TextInputEditText fieldCameraPort;
    private TextInputEditText fieldCameraAddress;
    private TextInputEditText fieldVncProtocol;
    private TextInputEditText fieldVncPort;
    private TextInputEditText fieldVncAddress;
    private TextInputEditText fieldControllerPath;
    private TextInputEditText fieldCmdLeftUp;
    private TextInputEditText fieldCmdHeadLeft;
    private TextInputEditText fieldCmdLeftDown;
    private TextInputEditText fieldCmdRightUp;
    private TextInputEditText fieldCmdHeadRight;
    private TextInputEditText fieldCmdRightDown;
    private TextInputEditText fieldCameraPath;
    private TextInputEditText fieldCameraSettings;
    private TextInputEditText fieldCameraController;
    private TextInputEditText fieldVncPath;
    private TextInputEditText fieldIndicator0;
    private TextInputEditText fieldIndicator1;
    private TextInputEditText fieldIndicator2;
    private TextInputEditText fieldIndicator3;
    private TextInputEditText fieldIndicator4;
    private TextInputEditText fieldIndicator5;
    private TextInputEditText fieldIndicator6;
    private TextInputEditText fieldIndicator7;
    private CheckBox checkboxVncMode;

    private SettingsManager settingsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setDecorFitsSystemWindows(false);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                closeWindow();
            }
        });

        initializeUi();
    }

    private void initializeUi() {
        fieldControllerProtocol = findViewById(R.id.field_controller_protocol);
        fieldControllerPort = findViewById(R.id.field_controller_port);
        fieldControllerAddress = findViewById(R.id.field_controller_address);
        fieldCameraProtocol = findViewById(R.id.field_camera_protocol);
        fieldCameraPort = findViewById(R.id.field_camera_port);
        fieldCameraAddress = findViewById(R.id.field_camera_address);
        fieldVncProtocol = findViewById(R.id.field_vnc_protocol);
        fieldVncPort = findViewById(R.id.field_vnc_port);
        fieldVncAddress = findViewById(R.id.field_vnc_address);
        fieldControllerPath = findViewById(R.id.field_controller_path);
        fieldCmdLeftUp = findViewById(R.id.field_cmd_left_up);
        fieldCmdHeadLeft = findViewById(R.id.field_cmd_head_left);
        fieldCmdLeftDown = findViewById(R.id.field_cmd_left_down);
        fieldCmdRightUp = findViewById(R.id.field_cmd_right_up);
        fieldCmdHeadRight = findViewById(R.id.field_cmd_head_right);
        fieldCmdRightDown = findViewById(R.id.field_cmd_right_down);
        fieldCameraPath = findViewById(R.id.field_camera_path);
        fieldCameraSettings = findViewById(R.id.field_camera_settings);
        fieldCameraController = findViewById(R.id.field_camera_controller);
        fieldVncPath = findViewById(R.id.field_vnc_path);
        fieldIndicator0 = findViewById(R.id.field_indicator_0);
        fieldIndicator1 = findViewById(R.id.field_indicator_1);
        fieldIndicator2 = findViewById(R.id.field_indicator_2);
        fieldIndicator3 = findViewById(R.id.field_indicator_3);
        fieldIndicator4 = findViewById(R.id.field_indicator_4);
        fieldIndicator5 = findViewById(R.id.field_indicator_5);
        fieldIndicator6 = findViewById(R.id.field_indicator_6);
        fieldIndicator7 = findViewById(R.id.field_indicator_7);
        checkboxVncMode = findViewById(R.id.checkbox_vnc_mode);
        Button btnCancel = findViewById(R.id.btn_cancel);
        Button btnSave = findViewById(R.id.btn_save);

        settingsManager = new SettingsManager(this);

        if (settingsManager.hasSettings()) {
            fieldControllerProtocol.setText(settingsManager.getControllerProtocol());
            fieldControllerPort.setText(settingsManager.getControllerPort());
            fieldControllerAddress.setText(settingsManager.getControllerAddress());
            fieldCameraProtocol.setText(settingsManager.getCameraProtocol());
            fieldCameraPort.setText(settingsManager.getCameraPort());
            fieldCameraAddress.setText(settingsManager.getCameraAddress());
            fieldVncProtocol.setText(settingsManager.getVncProtocol());
            fieldVncPort.setText(settingsManager.getVncPort());
            fieldVncAddress.setText(settingsManager.getVncAddress());
            fieldControllerPath.setText(settingsManager.getControllerPath());
            fieldCmdLeftUp.setText(settingsManager.getCmdLeftUp());
            fieldCmdHeadLeft.setText(settingsManager.getCmdHeadLeft());
            fieldCmdLeftDown.setText(settingsManager.getCmdLeftDown());
            fieldCmdRightUp.setText(settingsManager.getCmdRightUp());
            fieldCmdHeadRight.setText(settingsManager.getCmdHeadRight());
            fieldCmdRightDown.setText(settingsManager.getCmdRightDown());
            fieldCameraPath.setText(settingsManager.getCameraPath());
            fieldCameraSettings.setText(settingsManager.getCameraSettings());
            fieldCameraController.setText(settingsManager.getCameraController());
            fieldVncPath.setText(settingsManager.getVncPath());
            fieldIndicator0.setText(settingsManager.getIndicatorColor0());
            fieldIndicator1.setText(settingsManager.getIndicatorColor1());
            fieldIndicator2.setText(settingsManager.getIndicatorColor2());
            fieldIndicator3.setText(settingsManager.getIndicatorColor3());
            fieldIndicator4.setText(settingsManager.getIndicatorColor4());
            fieldIndicator5.setText(settingsManager.getIndicatorColor5());
            fieldIndicator6.setText(settingsManager.getIndicatorColor6());
            fieldIndicator7.setText(settingsManager.getIndicatorColor7());
            checkboxVncMode.setChecked(settingsManager.isVncMode());
        }

        btnCancel.setOnClickListener(v -> closeWindow());
        btnSave.setOnClickListener(v -> saveSettings());
    }

    private void saveSettings() {
        if (settingsManager != null) {
            settingsManager.setControllerProtocol(Objects.requireNonNull(fieldControllerProtocol.getText()).toString());
            settingsManager.setCameraProtocol(Objects.requireNonNull(fieldCameraProtocol.getText()).toString());
            settingsManager.setVncProtocol(Objects.requireNonNull(fieldVncProtocol.getText()).toString());
            settingsManager.setControllerPort(Objects.requireNonNull(fieldControllerPort.getText()).toString());
            settingsManager.setCameraPort(Objects.requireNonNull(fieldCameraPort.getText()).toString());
            settingsManager.setVncPort(Objects.requireNonNull(fieldVncPort.getText()).toString());
            settingsManager.setControllerAddress(Objects.requireNonNull(fieldControllerAddress.getText()).toString());
            settingsManager.setCameraAddress(Objects.requireNonNull(fieldCameraAddress.getText()).toString());
            settingsManager.setVncAddress(Objects.requireNonNull(fieldVncAddress.getText()).toString());
            settingsManager.setControllerPath(Objects.requireNonNull(fieldControllerPath.getText()).toString());
            settingsManager.setCameraPath(Objects.requireNonNull(fieldCameraPath.getText()).toString());
            settingsManager.setCameraSettings(Objects.requireNonNull(fieldCameraSettings.getText()).toString());
            settingsManager.setCameraController(Objects.requireNonNull(fieldCameraController.getText()).toString());
            settingsManager.setVncPath(Objects.requireNonNull(fieldVncPath.getText()).toString());
            settingsManager.setCmdLeftUp(Objects.requireNonNull(fieldCmdLeftUp.getText()).toString());
            settingsManager.setCmdHeadLeft(Objects.requireNonNull(fieldCmdHeadLeft.getText()).toString());
            settingsManager.setCmdLeftDown(Objects.requireNonNull(fieldCmdLeftDown.getText()).toString());
            settingsManager.setCmdRightUp(Objects.requireNonNull(fieldCmdRightUp.getText()).toString());
            settingsManager.setCmdHeadRight(Objects.requireNonNull(fieldCmdHeadRight.getText()).toString());
            settingsManager.setCmdRightDown(Objects.requireNonNull(fieldCmdRightDown.getText()).toString());
            settingsManager.setIndicatorColor0(Objects.requireNonNull(fieldIndicator0.getText()).toString());
            settingsManager.setIndicatorColor1(Objects.requireNonNull(fieldIndicator1.getText()).toString());
            settingsManager.setIndicatorColor2(Objects.requireNonNull(fieldIndicator2.getText()).toString());
            settingsManager.setIndicatorColor3(Objects.requireNonNull(fieldIndicator3.getText()).toString());
            settingsManager.setIndicatorColor4(Objects.requireNonNull(fieldIndicator4.getText()).toString());
            settingsManager.setIndicatorColor5(Objects.requireNonNull(fieldIndicator5.getText()).toString());
            settingsManager.setIndicatorColor6(Objects.requireNonNull(fieldIndicator6.getText()).toString());
            settingsManager.setIndicatorColor7(Objects.requireNonNull(fieldIndicator7.getText()).toString());
            settingsManager.setVncMode(checkboxVncMode.isChecked());

            Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            close();
        } else {
            closeWindow();
        }
    }

    private void closeWindow() {
        this.setResult(RESULT_CANCELED);
        close();
    }

    private void close() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
