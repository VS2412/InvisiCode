package com.key;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseConfig {
    private static FirebaseDatabase database;

    public static void initialize() {
        try {
            InputStream serviceAccount = Main.class.getClassLoader()
                    .getResourceAsStream("ivcde-9b5d7-firebase-adminsdk-fbsvc-c317f1d2d8.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://ivcde-9b5d7-default-rtdb.asia-southeast1.firebasedatabase.app") 
                                                                                                              
                    .build();
            FirebaseApp.initializeApp(options);
            database = FirebaseDatabase.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseReference getDatabaseReference() {
        return database.getReference(MacAddressFetcher.getMacAddressOrUniqueID());
    }
}
    