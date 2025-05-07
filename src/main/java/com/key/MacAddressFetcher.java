package com.key;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Enumeration;

public class MacAddressFetcher {
    public static String getMacAddressOrUniqueID() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface network = networkInterfaces.nextElement();
                byte[] macAddressBytes = network.getHardwareAddress();

                if (macAddressBytes != null && macAddressBytes.length > 0) {
                    StringBuilder macAddress = new StringBuilder();
                    for (byte b : macAddressBytes) {
                        macAddress.append(String.format("%02X:", b));
                    }
                    macAddress.deleteCharAt(macAddress.length() - 1); // Remove trailing colon
                    return macAddress.toString(); // Return first valid MAC address
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If no MAC address found, return a unique Base64 identifier
        return generateUniqueID();
    }

    private static String generateUniqueID() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[12]; // Generate 12 random bytes
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}
