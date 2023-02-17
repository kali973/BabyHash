package com.example.babyhash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication
public class BabyHashApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        SpringApplication.run(BabyHashApplication.class, args);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;

        do {
            System.out.println("BabyHash Menu");
            System.out.println("1. Generate hash");
            System.out.println("2. Calculate block hash");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter some data for a small hash generation");
                    System.out.println("For BabyHash, all input data is converted to lower case");
                    Date var1 = new Date();
                    long var2 = var1.getTime();
                    new GregorianCalendar(2018, 10, 28);
                    String var10 = reader.readLine();
                    var10 = var10.toLowerCase();
                    String var11 = "FFFF";

                    for (int var12 = 0; var11.compareTo("0000") != 0; ++var12) {
                        var10 = var10.concat(Integer.toString(var12));
                        String var13 = ComputeSHA_256_as_Hex_String(var10);
                        var11 = var13.substring(0, 4);
                        System.out.println(var13.toUpperCase());
                        System.out.println("iteration number:" + var12);
                    }

                    Date var14 = new Date();
                    long var7 = var14.getTime() - var2;
                    System.out.println("Mining time = " + var7 / 1000L + " sec");
                    break;
                case 2:
                    System.out.println("Calling Python program to calculate block hash...");

                    String pythonScriptPath = "calculateBlockHash.py"; // remplacer par le chemin d'accès réel au script Python
                    ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath);
                    Process p = pb.start();

                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    break;
                case 3:
                    System.out.println("Exiting BabyHash...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (choice != 3);
    }

    public static String ComputeSHA_256_as_Hex_String(String var0) {
        try {
            MessageDigest var1 = MessageDigest.getInstance("SHA-1");
            var1.update(var0.getBytes(StandardCharsets.UTF_8), 0, var0.length());
            byte[] var2 = var1.digest();
            return convertToHex(var2);
        } catch (NoSuchAlgorithmException var3) {
            System.out.println("No such algorithm exception thrown " + var3);
        }

        return null;
    }

    private static String convertToHex(byte[] var0) {
        StringBuffer var1 = new StringBuffer();

        for (int var2 = 0; var2 < var0.length; ++var2) {
            int var3 = var0[var2] >>> 4 & 15;
            int var4 = 0;

            do {
                if (0 <= var3 && var3 <= 9) {
                    var1.append((char) (48 + var3));
                } else {
                    var1.append((char) (97 + (var3 - 10)));
                }

                var3 = var0[var2] & 15;
            } while (var4++ < 1);
        }

        return var1.toString();
    }
}
