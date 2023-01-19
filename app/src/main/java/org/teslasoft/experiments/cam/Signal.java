package org.teslasoft.experiments.cam;

import android.app.Activity;

import androidx.annotation.NonNull;

import org.teslasoft.core.api.network.RequestNetwork;
import org.teslasoft.core.api.network.RequestNetworkController;

public class Signal {
    private final int signalId;
    private final String cmd;

    private final RequestNetwork api;
    private final RequestNetwork.RequestListener apiListener = new RequestNetwork.RequestListener() {
        @Override
        public void onResponse(@NonNull String tag, @NonNull String response) { /* unused */ }

        @Override
        public void onErrorResponse(@NonNull String tag, @NonNull String message) { /* unused */ }
    };

    public Signal(Activity context, int signalId, String cmd) {
        this.signalId = signalId;
        this.cmd = cmd;

        api = new RequestNetwork(context);
    }

    public void sendSignal() {
        api.startRequestNetwork(RequestNetworkController.GET, cmd, "A", apiListener);
    }

    public int getSignalId() {
        return signalId;
    }
}
