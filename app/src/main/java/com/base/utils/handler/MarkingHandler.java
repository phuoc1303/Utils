package com.base.utils.handler;

import android.os.Handler;
import android.os.Looper;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MarkingHandler extends Handler {
    public MarkingHandler(Looper looper) {
        super(looper, null);
    }
    private final ConcurrentHashMap<Integer, Runnable> markedCallbacks =  new ConcurrentHashMap<>();

    public void postMarkedDelayed(int id, long delayMillis, MarkingHandlerCallback callback) {
        if (hasMarkedCallback(id)) {
            return;
        }
        markedCallbacks.put(id,(Runnable) () -> {
            markedCallbacks.remove(id);
            callback.callback();
        });
        postDelayed(Objects.requireNonNull(markedCallbacks.get(id)), delayMillis);
    }

    public void removeMarkedCallback(int id) {
        if (hasMarkedCallback(id)) {
            Runnable runnable = markedCallbacks.get(id);
            if (runnable != null) {
                removeCallbacks(runnable);
            }
            markedCallbacks.remove(id);
        }
    }

    public boolean hasMarkedCallback(int id) {
        return markedCallbacks.containsKey(id);
    }

    public interface MarkingHandlerCallback{
        void callback();
    }
}
