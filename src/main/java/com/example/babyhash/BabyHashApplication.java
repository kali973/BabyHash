package com.example.babyhash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

@SpringBootApplication
public class BabyHashApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        SpringApplication.run(BabyHashApplication.class, args);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("BabyHash Menu:");
            System.out.println("1. Generate hash for input data");
            System.out.println("2. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    generateHash(scanner);
                    break;
                case 2:
                    System.out.println("Goodbye!");
                    System.exit(0);
                case 3:
                    runPythonProgram();
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void runPythonProgram() {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "/chemin/vers/le/programme.py");
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateHash(Scanner scanner) {
        System.out.println("Enter some data for a small hash generation");
        System.out.println("For BabyHash, all input data is converted to lower case");
        Date var1 = new Date();
        long var2 = var1.getTime();
        new GregorianCalendar(2018, 10, 28);
        String var10 = scanner.nextLine().toLowerCase();
        String var11 = "FFFF";

        for(int var12 = 0; !var11.equals("0000"); ++var12) {
            String var13 = var10.concat(Integer.toString(var12));
            String var14 = ComputeSHA_256_as_Hex_String(var13);
            var11 = var14.substring(0, 4);
            System.out.println(var14.toUpperCase());
            System.out.println("iteration number:" + var12);
        }

        Date var15 = new Date();
        long var7 = var15.getTime() - var2;
        System.out.println("Mining time = " + var7 / 1000L + " sec");
    }

    public static String ComputeSHA_256_as_Hex_String(String var0) {
        try {
            MessageDigest var1 = MessageDigest.getInstance("SHA-256"); // Correction : utiliser SHA-256 au lieu de SHA-1
            var1.update(var0.getBytes("UTF-8"), 0, var0.length());
            byte[] var2 = var1.digest();
            return convertToHex(var2);
        } catch (NoSuchAlgorithmException var3) {
            System.out.println("No such algorithm exception thrown " + var3);
        } catch (UnsupportedEncodingException var4) {
            System.out.println("Unsupported encoding exception thrown " + var4);
        }

        return null;
    }

    private static String convertToHex(byte[] var0) {
        StringBuffer var1 = new StringBuffer();

        for(int var2 = 0; var2 < var0.length; ++var2) {
            int var3 = var0[var2] >>> 4 & 15;
            int var4 = 0;

            do {
                if (0 <= var3 && var3 <= 9) {
                    var1.append((char)(48 + var3));
                } else {
                    var1.append((char)(97 + (var3 - 10)));
                }

                var3 = var0[var2] & 15;
            } while(var4++ < 1);
        }

        return var1.toString();
    }
}
