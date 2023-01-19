package org.teslasoft.experiments.cam;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.teslasoft.core.api.network.RequestNetwork;
import org.teslasoft.core.api.network.RequestNetworkController;

import java.lang.reflect.Type;

public class CameraInfo {
    private CameraInfo.OnDataReceivedListener listener;
    private final RequestNetwork api;
    private final RequestNetwork.RequestListener apiListener = new RequestNetwork.RequestListener() {
        @Override
        public void onResponse(@NonNull String tag, @NonNull String response) {
            if (listener != null) {
                Gson gson = new Gson();
                Type dataType = TypeToken.getParameterized(CameraInfoModel.class).getType();
                CameraInfoModel cameraInfoModel = gson.fromJson(response, dataType);
                listener.onReceived(cameraInfoModel);
            }
        }

        @Override
        public void onErrorResponse(@NonNull String tag, @NonNull String message) {
            if (listener != null) {
                listener.onError(message);
            }
        }
    };

    public CameraInfo(Activity context) {
        this.listener = null;
        api = new RequestNetwork(context);
    }

    public void getCameraInfo(String url) {
        api.startRequestNetwork(RequestNetworkController.GET, url, "A", apiListener);
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> getCameraInfo(url), 3000);
    }

    public interface OnDataReceivedListener {
        void onReceived(CameraInfoModel cameraInfo);
        void onError(String error);
    }

    public void setOnDataReceivedListener(CameraInfo.OnDataReceivedListener listener) {
        this.listener = listener;
    }
}
