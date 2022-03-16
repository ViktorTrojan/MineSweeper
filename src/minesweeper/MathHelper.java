package minesweeper;

import java.awt.Color;

public class MathHelper {

    public static double toDouble(String s) {
        double d = 0;
        try {
            d = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] couldn't convert String to Double!");
        }
        return d;
    }

    public static float toFloat(String s) {
        float f = 0;
        try {
            f = Float.parseFloat(s);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] couldn't convert String to Float!");
        }
        return f;
    }

    public static float toLong(String s) {
        long l = 0;
        try {
            l = Long.parseLong(s);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] couldn't convert String to Long!");
        }
        return l;
    }

    public static int toInteger(String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] couldn't convert String to Integer!");
        }
        return i;
    }

    public static double setDecimal(double zahl, int kommaStellen) {
        double potenz = Math.pow(10, kommaStellen);
        double val = Math.round(zahl * potenz) / potenz;

        return val;
    }

    public static double toDEG(double d) {
        return d * 180 / Math.PI;
    }

    public static double toRAD(double d) {
        return d * Math.PI / 180;
    }

    public static double sin(double d) {
        return Math.sin(toRAD(d));
    }

    public static double cos(double d) {
        return Math.cos(toRAD(d));
    }

    public static double tan(double d) {
        return Math.tan(toRAD(d));
    }

    public static double asin(double d) {
        return Math.toDegrees(Math.asin(d));
    }

    public static double acos(double d) {
        return Math.toDegrees(Math.acos(d));
    }

    public static double atan(double d) {
        return Math.toDegrees(Math.atan(d));
    }

    public static double pythag2(double d1, double d2) {
        return Math.sqrt(Math.abs(d1 * d1 + d2 * d2));
    }

    public static boolean inRange(double x1, double x2, double y1, double y2) {
        return (x1 < Math.abs(x2) && x1 > -Math.abs(x2) && y1 < Math.abs(y2) && y1 > -Math.abs(y2));
    }

    public static boolean inRange(double x1, double x2) {
        return (x1 < Math.abs(x2) && x1 > -Math.abs(x2));
    }

    public static double limit(double d, double min, double max) {
        return Math.min(Math.max(d, min), max);
    }
    
    public static int random(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public static double random(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    public static double fade(double t) {
        // Fade function as defined by Ken Perlin.  This eases coordinate values
        // so that they will "ease" towards integral values.  This ends up smoothing
        // the final output.
        return t * t * t * (t * (t * 6 - 15) + 10);	// 6t^5 - 15t^4 + 10t^3
    }

    // Linear interpolation function.
    public static double lerp(double x, double y, double t) {
        return x + t * (y - x);
    }

    public static Color brighten(Color c1, int amount, boolean a) {
        int red = (int) limit((double) (c1.getRed() + amount), 0, 255);
        int green = (int) limit((double) (c1.getGreen() + amount), 0, 255);
        int blue = (int) limit((double) (c1.getBlue() + amount), 0, 255);
        int alpha = a ? (int) limit((double) (c1.getAlpha() + amount), 0, 255) : c1.getAlpha();

        return new Color(red, green, blue, alpha);
    }

    public static Color darken(Color c1, int amount, boolean a) {
        int red = (int) limit((double) (c1.getRed() - amount), 0, 255);
        int green = (int) limit((double) (c1.getGreen() - amount), 0, 255);
        int blue = (int) limit((double) (c1.getBlue() - amount), 0, 255);
        int alpha = a ? (int) limit((double) (c1.getAlpha() - amount), 0, 255) : c1.getAlpha();

        return new Color(red, green, blue, alpha);
    }
}
