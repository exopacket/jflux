package com.inteliense.jflux.todash;

import com.inteliense.jflux.crypto.Rand;
import com.inteliense.jflux.crypto.builtin.SHA;
import com.inteliense.jflux.encoding.BaseX;
import com.inteliense.jflux.encoding.Hex;
import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class __ {

    // COMPARE

    public static Comparison compare(int input) {
        return new Comparison(input);
    }

    public static Comparison compare(double input) {
        return new Comparison(input);
    }

    public static Comparison compare(float input) {
        return new Comparison(input);
    }

    public static Comparison compare(long input) {
        return new Comparison(input);
    }

    public static class Comparison {

        private double input;

        public Comparison(int input) {
            this.input = input;
        }

        public Comparison(double input) {
            this.input = input;
        }

        public Comparison(float input) {
            this.input = input;
        }

        public Comparison(long input) {
            this.input = input;
        }

        public boolean gt(int input) {
            return this.input > input;
        }

        public boolean gt(double input) {
            return this.input > input;
        }

        public boolean gt(float input) {
            return this.input > input;
        }

        public boolean gt(long input) {
            return this.input > input;
        }

        public boolean lt(int input) {
            return this.input < input;
        }

        public boolean lt(double input) {
            return this.input < input;
        }

        public boolean lt(float input) {
            return this.input < input;
        }

        public boolean lt(long input) {
            return this.input < input;
        }

        public boolean gte(int input) {
            return this.input >= input;
        }

        public boolean gte(double input) {
            return this.input >= input;
        }

        public boolean gte(float input) {
            return this.input >= input;
        }

        public boolean gte(long input) {
            return this.input >= input;
        }

        public boolean lte(int input) {
            return this.input <= input;
        }

        public boolean lte(double input) {
            return this.input <= input;
        }

        public boolean lte(float input) {
            return this.input <= input;
        }

        public boolean lte(long input) {
            return this.input <= input;
        }

        public boolean eq(int input) {
            return this.input == input;
        }

        public boolean eq(double input) {
            return this.input == input;
        }

        public boolean eq(float input) {
            return this.input == input;
        }

        public boolean eq(long input) {
            return this.input == input;
        }

    }

    // NULL VALUES

    public static Object nothing() {
        return null;
    }

    public static boolean unset(Object o) {
        return empty(o);
    }

    public static boolean isset(Object o) {
        return !empty(o);
    }

    public static boolean empty(Object o) {
        return o == null;
    }

    public static boolean empty(String str) {
        return str == null || same(str, "");
    }

    // STRINGS

    public static boolean same(String str1, String str2) {
        return str1.equals(str2);
    }

    public static String trim(String str) {
        return str.replaceAll("^[\\s\\t\\r\\n]+", "").replaceAll("[\\s\\t\\r\\n]+$", "");
    }

    public static String trimLeading(String str) {
        return str.replaceAll("^[\\s\\t\\r\\n]+", "");
    }

    public static String trimTrailing(String str) {
        return str.replaceAll("[\\s\\t\\r\\n]+$", "");
    }

    public static String replaceSpaces(String str, String replacement) {
        return str.replaceAll("\\s+", replacement);
    }

    public static String replaceTabs(String str, String replacement) {
        return str.replaceAll("\\t+", replacement);
    }

    public static String replaceLines(String str, String replacement) {
        return str.replaceAll("\\n+", replacement);
    }

    public static String replaceWhitespace(String str, String replacement, boolean tabs, boolean newlines) {
        str = replaceSpaces(str, replacement);
        if(tabs) str = replaceTabs(str, replacement);
        if(newlines) str = replaceTabs(str, replacement);
        return str;
    }

    public static String camelToSnake(String str) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        str = str.replaceAll(regex, replacement).toLowerCase();
        return str;
    }

    public static String classToSnake(String str) {
        String regex = "([A-Z]+[a-z]+)";
        String replacement = "_$1";
        str = str.replaceAll(regex, replacement).toLowerCase().substring(1);
        return str;
    }

    public static String snakeToCamel(String str) {
        StringBuilder builder = new StringBuilder();
        String[] parts = str.split("_");
        for(int i=0; i<parts.length; i++) {
            if(i>0) {
                builder.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1).toLowerCase());
                continue;
            }
            builder.append(parts[i].toLowerCase());
        }
        return builder.toString();
    }

    public static String snakeToClass(String str) {
        StringBuilder builder = new StringBuilder();
        String[] parts = str.split("_");
        for(int i=0; i<parts.length; i++) {
            builder.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1).toLowerCase());
        }
        return builder.toString();
    }

    public static String hex(byte[] arr) {
        return Hex.getHex(arr);
    }

    public static String b64(byte[] arr) {
        return BaseX.encode64(arr);
    }

    // EXIT

    public static void exit(int code) {
        System.exit(code);
    }

    public static void exit(int code, String message) {
        error(message);
        exit(code);
    }

    // CONSOLE

    public static void log(String message) {
        System.out.println(message);
    }

    public static void error(String message) {
        System.err.println(message);
    }

    public static Console console() { return new Console(); }

    public enum TextColor {
        BLACK,
        RED,
        GREEN,
        YELLOW,
        BLUE,
        PURPLE,
        CYAN,
        WHITE
    }

    public static class Console {

        public void print(String message) {
            System.out.print(message);
        }

        public void println(String message) {
            System.out.println(message);
        }

        public void print(String message, TextColor color) {
            System.out.print(getAnsiColor(color) + message + ansiReset());
        }

        public void println(String message, TextColor color) {
            System.out.println(getAnsiColor(color) + message + ansiReset());
        }

        public LineCollection refreshable() {
            return new LineCollection();
        }

        public LineCollection collection() {
            return new LineCollection();
        }

        public int interactiveList(String message, String...options) {
            println(message);
            for(int i=0; i< options.length; i++) {
                int optNum = i + 1;
                String opt = options[i];
                print("[ " + optNum + " ] ➤ ", TextColor.GREEN);
                println(opt);
            }
            String res = collect("Enter the number of your selection");
            return Integer.parseInt(res);
        }

        public String collect(String prompt) {
            print(prompt + " ➤ ", TextColor.PURPLE);
            Scanner scnr = new Scanner(System.in);
            return scnr.nextLine();
        }

        public String collect(String prompt, String defaultEntry) {
            print(prompt, TextColor.PURPLE);
            print(" [" + defaultEntry + "]");
            print(" ➤ ", TextColor.PURPLE);
            Scanner scnr = new Scanner(System.in);
            String input = scnr.nextLine();
            if(input.trim().isEmpty()) return defaultEntry;
            return input;
        }

        public String collectSecure(String prompt) {
            print(prompt, TextColor.PURPLE);
            print(" ➤ ", TextColor.PURPLE);
            char[] password = System.console().readPassword();
            String input = new String(password);
            return input;
        }

        public boolean confirm(String prompt) {
            print(prompt, TextColor.PURPLE);
            print(" [y/n]");
            print(" ➤ ", TextColor.PURPLE);
            Scanner scnr = new Scanner(System.in);
            String line = scnr.nextLine().trim().toUpperCase();
            boolean yes = line.equals("Y");
            boolean no = line.equals("N");
            if(!yes && !no) return confirm(prompt);
            return yes;
        }

    }

    public static class ProgressBar extends Line {
        private int value = 0;
        public void update(String message) { }
        public void update(String message, TextColor color) { }
        public void increment(int value) {
            this.value = value;
            if(value == 100) this.color = TextColor.GREEN;
        }
        public String get() {
            return "";
        }
    }

    public static class DynamicLine extends Line {
        public DynamicLine(String message) { super(message); }
        public DynamicLine(String message, TextColor color) { super(message, color); }
        public void increment(int value) { }
        public void update(String message) {
            this.content = message;
        }
        public void update(String message, TextColor color) {
            this.content = message;
            this.color = color;
        }
        public String get() {
            return getAnsiColor(this.color) + this.content + ansiReset();
        }
    }

    public abstract static class Line {
        protected String content = "";
        protected TextColor color = TextColor.WHITE;
        public Line() { }
        public Line(String message) {
            this.content = message;
        }
        public Line(String message, TextColor color) {
            this.content = message;
            this.color = color;
        }
        public abstract void update(String message);
        public abstract void update(String message, TextColor color);
        public abstract void increment(int value);
        public abstract String get();
    }

    public static class LineCollection {

        private ArrayList<Line> lines = new ArrayList<>();

        public LineCollection append(String message) {
            this.lines.add(new DynamicLine(message));
            return this;
        }

        public LineCollection append(String message, TextColor color) {
            this.lines.add(new DynamicLine(message, color));
            return this;
        }

        public LineCollection progress() {
            this.lines.add(new ProgressBar());
            return this;
        }

        public LineCollection update(String message, int index) {
            this.lines.get(index).update(message);
            return this;
        }

        public LineCollection update(String message, TextColor color, int index) {
            this.lines.get(index).update(message, color);
            return this;
        }

        public LineCollection increment(int value, int index) {
            this.lines.get(index).increment(value);
            return this;
        }

    }

    private static String getAnsiColor(TextColor color) {
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        switch(color) {
            case BLACK: return ANSI_BLACK;
            case RED: return ANSI_RED;
            case GREEN: return ANSI_GREEN;
            case YELLOW: return ANSI_YELLOW;
            case BLUE: return ANSI_BLUE;
            case PURPLE: return ANSI_PURPLE;
            case CYAN: return ANSI_CYAN;
            default: return ANSI_WHITE;
        }
    }

    private static String ansiReset() {
        return "\u001B[0m";
    }

    //legacy
    public static String str(Object input) {
        return String.valueOf(input);
    }

    public static int num(Object input) throws Exception {
        try {
            return Integer.parseInt(str(input));
        } catch (Exception e) { throw new Exception("Integer parse error."); }
    }

    public static double dbl(Object input) throws Exception {
        try {
            return Double.parseDouble(str(input));
        } catch (Exception e) { throw new Exception("Double parse error."); }
    }

    public static void printPrettyLn(String output) {
        console().println(output, TextColor.CYAN);
    }

    public static void printPrettyLn(String output, TextColor color) {
        console().println(output, color);
    }

    public static String[] arr(String...vals) {
        return vals;
    }

    public static byte[] bites(String str) {
        final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        boolean isBase64 = textPattern.matcher(str).matches();
        try {
            byte[] arr = null;
            if(isBase64) arr = BaseX.bytesFrom64(str);
            if(arr == null) arr = Hex.fromHex(str);
            if(arr == null) arr = str.getBytes(StandardCharsets.UTF_8);
            return arr;
        } catch (Exception e) {
            return null;
        }
    }

    public static String id() {
        return id(String.valueOf(System.nanoTime()), hex(Rand.secure(32)), Rand.str(32));
    }

    public static String id(String... seeds) {
        String full = "";
        for(int i=0; i< seeds.length; i++) {
            if(i > 0) full += "_";
            full += seeds[i];
        }
        full += String.valueOf(System.currentTimeMillis());
        return Rand.randomCase(b64(SHA.Bites.getSha1(full)).replaceAll("[/=+]", ""));
    }

}
