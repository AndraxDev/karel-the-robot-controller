package org.teslasoft.experiments.cam;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.teslasoft.core.api.network.RequestNetwork;
import org.teslasoft.core.api.network.RequestNetworkController;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SignalManager {
    private final ArrayList<Signal> signals;

    private final TextView distance;
    private final String apiUrl;
    private final RequestNetwork sensorsApi;
    private final RequestNetwork.RequestListener sensorsApiListener = new RequestNetwork.RequestListener() {
        @Override
        public void onResponse(@NonNull String tag, @NonNull String response) {
            Gson gson = new Gson();

            Type r = new TypeToken<SensorsModel>() {
            }.getType();
            SensorsModel sr = gson.fromJson(response, r);

            distance.setText("Distance: ".concat(Double.toString(sr.getDistance())));
        }

        @Override
        public void onErrorResponse(@NonNull String tag, @NonNull String message) { /* unused */ }
    };

    public SignalManager(Activity context, SettingsManager settings, TextView distance) {
        this.distance = distance;
        sensorsApi = new RequestNetwork(context);
        apiUrl = SettingsParser.parseSensors(settings);
        signals = new ArrayList<>();
    }

    public void startSignals(int n) {
        if (doesSignalsActive()) {
            for (Signal signal : signals) {
                signal.sendSignal();
            }
        }

        if (n == 3) {
            n = 0;
            sensorsApi.startRequestNetwork(RequestNetworkController.GET, apiUrl, "A", sensorsApiListener);
        }

        n++;

        final Handler handler = new Handler(Looper.getMainLooper());
        int finalN = n;
        handler.postDelayed(() -> startSignals(finalN), 100);
    }

    public void addSignal(Signal signal) {
        signals.add(signal);
    }

    public void removeSignal(int signalId) {
        signals.removeIf(signal -> signal.getSignalId() == signalId);
    }

    public boolean doesSignalsActive() {
        return signals.size() != 0;
    }
}
