package org.teslasoft.experiments.cam;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private WebView content;
    private LinearLayout ui;
    private boolean uiToggle = true;
    private boolean flashToggle;
    private boolean indicatorsToggle;

    private LinearLayout btnLeftUp;
    private LinearLayout btnHeadLeft;
    private LinearLayout btnLeftDown;
    private LinearLayout btnRightUp;
    private LinearLayout btnHeadRight;
    private LinearLayout btnRightDown;

    private SettingsManager settingsManager;
    private SignalManager signalManager;
    private Signal leftUp;
    private Signal headLeft;
    private Signal leftDown;
    private Signal rightUp;
    private Signal headRight;
    private Signal rightDown;

    private TextView status;
    private TextView batteryInfo;
    private TextView distance;
    private CameraInfoModel cameraInfoModel;

    private String urlCameraStream;
    private String urlCmdLeftUp;
    private String urlCmdHeadLeft;
    private String urlCmdLeftDown;
    private String urlCmdRightUp;
    private String urlCmdHeadRight;
    private String urlCmdRightDown;
    private String urlFlashOn;
    private String urlFlashOff;
    private String urlCameraInfo;
    private String urlIndicator0;
    private String urlIndicator1;
    private String urlIndicator2;
    private String urlIndicator3;
    private String urlIndicator4;
    private String urlIndicator5;
    private String urlIndicator6;
    private String urlIndicator7;

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) loadSettings();
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setDecorFitsSystemWindows(false);

        String processName = getProcessName(this);
        try {
            WebView.setDataDirectorySuffix(processName);
        } catch (Exception ignored) { /* unused */ }

        loadSettings();
        initializeUi();
        initializeFlash();
        initializeLed();
        initializeSignals();
        initializeControls();
        initializeCamera();
        initializeCameraApi();
    }

    private void loadSettings() {
        settingsManager = new SettingsManager(this);

        if (settingsManager.hasSettings()) {
            urlCameraStream = SettingsParser.parseCameraStream(settingsManager);
            urlCmdLeftUp = SettingsParser.parseCmdLeftUp(settingsManager);
            urlCmdHeadLeft = SettingsParser.parseCmdHeadLeft(settingsManager);
            urlCmdLeftDown = SettingsParser.parseCmdLeftDown(settingsManager);
            urlCmdRightUp = SettingsParser.parseCmdRightUp(settingsManager);
            urlCmdHeadRight = SettingsParser.parseCmdHeadRight(settingsManager);
            urlCmdRightDown = SettingsParser.parseCmdRightDown(settingsManager);
            urlFlashOn = SettingsParser.parseFlashOn(settingsManager);
            urlFlashOff = SettingsParser.parseFlashOff(settingsManager);
            urlCameraInfo = SettingsParser.parseCameraInfo(settingsManager);
            urlIndicator0 = SettingsParser.parseI0(settingsManager);
            urlIndicator1 = SettingsParser.parseI1(settingsManager);
            urlIndicator2 = SettingsParser.parseI2(settingsManager);
            urlIndicator3 = SettingsParser.parseI3(settingsManager);
            urlIndicator4 = SettingsParser.parseI4(settingsManager);
            urlIndicator5 = SettingsParser.parseI5(settingsManager);
            urlIndicator6 = SettingsParser.parseI6(settingsManager);
            urlIndicator7 = SettingsParser.parseI7(settingsManager);
        } else {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            activityResultLauncher.launch(new Intent(this, SettingsActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    private void initializeFlash() {
        Button btnFlash = findViewById(R.id.btn_flash);

        Signal flashOn = new Signal(this, 7, urlFlashOn);
        Signal flashOff = new Signal(this, 8, urlFlashOff);

        btnFlash.setOnClickListener(v -> {
            if (flashToggle) {
                flashToggle = false;
                flashOff.sendSignal();
            } else {
                flashToggle = true;
                flashOn.sendSignal();
            }
        });
    }

    private void initializeLed() {
        Button btnIndicator = findViewById(R.id.btn_indicator);
        LinearLayout indicators = findViewById(R.id.indicators);
        LinearLayout indicatorsDialog = findViewById(R.id.indicators_dialog);
        indicators.setVisibility(View.GONE);

        indicatorsToggle = false;

        Button btnI0 = findViewById(R.id.btn_i0);
        Button btnI1 = findViewById(R.id.btn_i1);
        Button btnI2 = findViewById(R.id.btn_i2);
        Button btnI3 = findViewById(R.id.btn_i3);
        Button btnI4 = findViewById(R.id.btn_i4);
        Button btnI5 = findViewById(R.id.btn_i5);
        Button btnI6 = findViewById(R.id.btn_i6);
        Button btnI7     = findViewById(R.id.btn_i7);

        Signal indicator0 = new Signal(this, 10, urlIndicator0);
        Signal indicator1 = new Signal(this, 11, urlIndicator1);
        Signal indicator2 = new Signal(this, 12, urlIndicator2);
        Signal indicator3 = new Signal(this, 13, urlIndicator3);
        Signal indicator4 = new Signal(this, 14, urlIndicator4);
        Signal indicator5 = new Signal(this, 15, urlIndicator5);
        Signal indicator6 = new Signal(this, 16, urlIndicator6);
        Signal indicator7 = new Signal(this, 17, urlIndicator7);

        btnI0.setOnClickListener(v -> indicator0.sendSignal());
        btnI1.setOnClickListener(v -> indicator1.sendSignal());
        btnI2.setOnClickListener(v -> indicator2.sendSignal());
        btnI3.setOnClickListener(v -> indicator3.sendSignal());
        btnI4.setOnClickListener(v -> indicator4.sendSignal());
        btnI5.setOnClickListener(v -> indicator5.sendSignal());
        btnI6.setOnClickListener(v -> indicator6.sendSignal());
        btnI7.setOnClickListener(v -> indicator7.sendSignal());

        indicatorsDialog.setOnClickListener(v -> {});

        indicators.setOnClickListener(v -> {
            if (indicatorsToggle) {
                indicatorsToggle = false;
                indicators.setVisibility(View.GONE);
            } else {
                indicatorsToggle = true;
                indicators.setVisibility(View.VISIBLE);
            }
        });

        btnIndicator.setOnClickListener(v -> {
            if (indicatorsToggle) {
                indicatorsToggle = false;
                indicators.setVisibility(View.GONE);
            } else {
                indicatorsToggle = true;
                indicators.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeUi() {
        ui = findViewById(R.id.ui);
        Button btnToggleUi = findViewById(R.id.btn_toggle_ui);
        Button btnReload = findViewById(R.id.btn_reload);
        Button btnSettings = findViewById(R.id.btn_settings);

        btnLeftUp = findViewById(R.id.btn_left_up);
        btnHeadLeft = findViewById(R.id.btn_head_left);
        btnLeftDown = findViewById(R.id.btn_left_down);
        btnRightUp = findViewById(R.id.btn_right_up);
        btnHeadRight = findViewById(R.id.btn_head_right);
        btnRightDown = findViewById(R.id.btn_right_down);

        status = findViewById(R.id.status);
        batteryInfo = findViewById(R.id.battery_info);
        distance = findViewById(R.id.distance);

        btnToggleUi.setOnClickListener(v -> {
            if (uiToggle) {
                uiToggle = false;
                ui.setVisibility(View.GONE);
                btnToggleUi.setAlpha(0.3f);
            } else {
                uiToggle = true;
                ui.setVisibility(View.VISIBLE);
                btnToggleUi.setAlpha(1.0f);
            }
        });

        btnReload.setOnClickListener(v -> {
            loadVideo(urlCameraStream);
            status.setText("");
        });

        btnSettings.setOnClickListener(v -> {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            activityResultLauncher.launch(new Intent(MainActivity.this, SettingsActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeControls() {
        btnLeftUp.setOnTouchListener((v, event) -> {
            v.performClick();
            drawButtonState(event, btnLeftUp, 1);
            return true;
        });

        btnHeadLeft.setOnTouchListener((v, event) -> {
            v.performClick();
            drawButtonState(event, btnHeadLeft, 2);
            return true;
        });

        btnLeftDown.setOnTouchListener((v, event) -> {
            v.performClick();
            drawButtonState(event, btnLeftDown, 3);
            return true;
        });

        btnRightUp.setOnTouchListener((v, event) -> {
            v.performClick();
            drawButtonState(event, btnRightUp, 4);
            return true;
        });

        btnHeadRight.setOnTouchListener((v, event) -> {
            v.performClick();
            drawButtonState(event, btnHeadRight, 5);
            return true;
        });

        btnRightDown.setOnTouchListener((v, event) -> {
            v.performClick();
            drawButtonState(event, btnRightDown, 6);
            return true;
        });
    }

    private void drawButtonState(MotionEvent event, LinearLayout view, int uiElement) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(0x4DFFFFFF);

            if (uiElement == 1) {
                signalManager.addSignal(leftUp);
                btnLeftDown.setEnabled(false);
            } else if (uiElement == 2) {
                signalManager.addSignal(headLeft);
                btnHeadRight.setEnabled(false);
            } else if (uiElement == 3) {
                signalManager.addSignal(leftDown);
                btnLeftUp.setEnabled(false);
            } else if (uiElement == 4) {
                signalManager.addSignal(rightUp);
                btnRightDown.setEnabled(false);
            } else if (uiElement == 5) {
                signalManager.addSignal(headRight);
                btnHeadLeft.setEnabled(false);
            } else if (uiElement == 6) {
                signalManager.addSignal(rightDown);
                btnRightUp.setEnabled(false);
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            view.setBackgroundColor(0x11FFFFFF);
            signalManager.removeSignal(uiElement);

            if (uiElement == 1) {
                btnLeftDown.setEnabled(true);
            } else if (uiElement == 2) {
                btnHeadRight.setEnabled(true);
            } else if (uiElement == 3) {
                btnLeftUp.setEnabled(true);
            } else if (uiElement == 4) {
                btnRightDown.setEnabled(true);
            } else if (uiElement == 5) {
                btnHeadLeft.setEnabled(true);
            } else if (uiElement == 6) {
                btnRightUp.setEnabled(true);
            }
        }
    }

    private void initializeSignals() {
        signalManager = new SignalManager(this, settingsManager, distance);
        leftUp = new Signal(this, 1, urlCmdLeftUp);
        headLeft = new Signal(this, 2, urlCmdHeadLeft);
        leftDown = new Signal(this, 3, urlCmdLeftDown);
        rightUp = new Signal(this, 4, urlCmdRightUp);
        headRight = new Signal(this, 5, urlCmdHeadRight);
        rightDown = new Signal(this, 6, urlCmdRightDown);
        signalManager.startSignals(0);
    }

    private void initializeCamera() {
        content = findViewById(R.id.content);

        content.setBackgroundColor(0x00000000);
        content.getSettings().setLoadWithOverviewMode(true);
        content.getSettings().setUseWideViewPort(true);
        content.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        content.getSettings().setBuiltInZoomControls(true);
        content.getSettings().setDisplayZoomControls(false);
        content.setVisibility(View.VISIBLE);

        content.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                content.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });

        loadVideo(urlCameraStream);
    }

    private void initializeCameraApi() {
        CameraInfo cameraInfo = new CameraInfo(this);
        cameraInfo.setOnDataReceivedListener(new CameraInfo.OnDataReceivedListener() {
            @Override
            public void onReceived(CameraInfoModel cameraInfo) {
                content.setVisibility(View.VISIBLE);
                cameraInfoModel = cameraInfo;
                batteryInfo.setText("Battery level: ".concat(Integer.toString(cameraInfoModel.getBattery().getLevel())).concat(" %"));
            }

            @Override
            public void onError(String error) {
                content.setVisibility(View.GONE);
                status.setText("Camera disabled");
                batteryInfo.setText("");
            }
        });

        // camera_info.getCameraInfo(url_camera_info);
    }

    private void loadVideo(String url) {
        content.clearCache(true);
        content.loadUrl(url);
    }

    private String getProcessName(Context context) {
        if (context == null) return null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) return processInfo.processName;
        } return null;
    }
}