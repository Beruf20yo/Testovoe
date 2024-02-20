package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static final String splitBy = " ";
    private static double RADIUS;
    private static double X_CENTER;
    private static double Y_CENTER;


    public static void main(String[] args) {
        String circleUrl = args[0];
        String pointsUrl = args[1];
        parseCircle(circleUrl);
        parsePoints(pointsUrl);
    }

    private static void parseCircle(String circleUrl) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(circleUrl))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                if (parts.length == 1) {
                    RADIUS = Double.parseDouble(parts[0]);
                    break;
                }
                X_CENTER = Double.parseDouble(parts[0]);
                Y_CENTER = Double.parseDouble(parts[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parsePoints(String pointsUrl) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(pointsUrl))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(splitBy);
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                System.out.print(checkPoints(x, y));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String checkPoints(double x, double y) {
        //(x - h)^2 + (y - k)^2 = r^2;
        StringBuilder sb = new StringBuilder();
        double answer = Math.pow((X_CENTER - x), 2) + Math.pow((Y_CENTER - y), 2);
        double radius2X = Math.pow(RADIUS, 2);
        if (answer < radius2X) {
            sb.append(1);
        }
        if (answer == radius2X) {
            sb.append(0);
        }
        if (answer > radius2X) {
            sb.append(2);
        }
        return sb.append("\n").toString();
    }
}
