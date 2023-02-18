package com.example.babyhash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;

public class BabyHashApplicationSwing {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        JFrame frame = new JFrame("BabyHash");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = 500;
        int frameHeight = 300;
        frame.setSize(frameWidth, frameHeight);
        frame.setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton button1 = new JButton("Generate hash");
        panel.add(button1);

        JButton button2 = new JButton("Calculate block hash");
        panel.add(button2);

        JButton button3 = new JButton("Exit");
        panel.add(button3);

        frame.add(panel);
        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date var1 = new Date();
                long var2 = var1.getTime();
                new GregorianCalendar(2018, 10, 28);
                String var10 = JOptionPane.showInputDialog(frame, "Enter input data", "BabyHash", JOptionPane.INFORMATION_MESSAGE);
                var10 = var10.toLowerCase();
                String var11 = "FFFF";
                String result = "";
                for(int var12 = 0; var11.compareTo("0000") != 0; ++var12) {
                    var10 = var10.concat(Integer.toString(var12));
                    String var13 = ComputeSHA_256_as_Hex_String(var10);
                    var11 = var13.substring(0, 4);
                    result += var13.toUpperCase() + "\n";
                    result += "iteration number:" + var12 + "\n";
                }
                Date var14 = new Date();
                long var7 = var14.getTime() - var2;
                result += "Mining time = " + var7 / 1000L + " sec";
                JTextArea textArea = new JTextArea(result);
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(700, 550));
                JOptionPane.showMessageDialog(frame, scrollPane, "Result", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Calling Python program to calculate block hash...");
                String pythonScriptPath = "calculateBlockHash.py"; // remplacer par le chemin d'accès réel au script Python
                ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath);
                try {
                    Process p = pb.start();
                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line;
                    StringBuilder result = new StringBuilder();
                    while ((line = in.readLine()) != null) {
                        result.append(line).append("\n");
                    }
                    JTextArea textArea = new JTextArea(result.toString());
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    scrollPane.setPreferredSize(new Dimension(500, 100));
                    JOptionPane.showMessageDialog(frame, scrollPane, "Result", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting BabyHash...");
                System.exit(0);
            }
        });
    }

    public static String ComputeSHA_256_as_Hex_String(String var0) {
        try {
            MessageDigest var1 = MessageDigest.getInstance("SHA-256");
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