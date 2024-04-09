package me.sfclog.voteplus.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");



    public static String tran(String msg) {
        if (msg != null) {
            Matcher matcher = pattern.matcher(msg);
            while (matcher.find()) {
                String color = msg.substring(matcher.start(), matcher.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                matcher = pattern.matcher(msg);
            }
            String msgok = ChatColor.translateAlternateColorCodes('&', msg);
            return msgok.replace("&", "");
        }
        return "...";
    }
    private static Pattern rgbPat = Pattern.compile("(?:#|0x)(?:[a-f0-9]{3}|[a-f0-9]{6})\\b|(?:rgb|hsl)a?\\([^\\)]*\\)");

    public static String getRGB(String msg) {
        String temp = msg;
        try {
            String status = "none";
            String r = "";
            String g = "";
            String b = "";
            Matcher match = rgbPat.matcher(msg);
            while (match.find()) {
                String color = msg.substring(match.start(), match.end());
                for (char character : msg.substring(match.start(), match.end()).toCharArray()) {
                    switch (character) {
                        case '(':
                            status = "r";
                            break;
                        case ',':
                            switch (status) {
                                case "r":
                                    status = "g";
                                    break;
                                case "g":
                                    status = "b";
                                    break;
                            }
                        default:
                            switch (status) {
                                case "r":
                                    r = r + character;
                                    break;
                                case "g":
                                    g = g + character;
                                    break;
                                case "b":
                                    b = b + character;
                                    break;
                            }
                            break;
                    }
                }
                b = b.replace(")", "");


                java.awt.Color col = new java.awt.Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
                temp = temp.replaceFirst("(?:#|0x)(?:[a-f0-9]{3}|[a-f0-9]{6})\\b|(?:rgb|hsl)a?\\([^\\)]*\\)", ChatColor.of(col) + "");
                r = "";
                g = "";
                b = "";
                status = "none";
            }
        } catch (Exception e) {
            return msg;
        }
        return temp;
    }
}