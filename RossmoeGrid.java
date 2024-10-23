import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class RossmoeGrid{

    private static double calculateDistance(Point2D.Double p1, Point2D.Double p2) {
        return p1.distance(p2);
    }

    private static double rossmoeProbability(Point2D.Double point, List<Point2D.Double> crimeCoords, double f, double g) {
        double probability = 0;
        for (Point2D.Double crime : crimeCoords) {
            double distance = calculateDistance(point, crime);
            int buffer = 8;
            if (distance > buffer) {
                probability += 1.0 / Math.pow(distance, f);
            } else if (distance <= buffer) {
                probability += (Math.pow(buffer, (g-f)))/(Math.pow(2*buffer-distance,g));
            }
        }
        return probability;
        //hola
    }

    public static void main(String[] args) {
        int gridSize = 100;
        
        List<Point2D.Double> crimeLocations = new ArrayList<>();
        crimeLocations.add(new Point2D.Double(34, 32));
        crimeLocations.add(new Point2D.Double(21, 55));
        crimeLocations.add(new Point2D.Double(48, 58));

        // random exponents in the formula (wikipedia)
        // f determines how quickly the probability of a criminal living there decays
        // g determines how sharply the probability is cut off at the edge of the buffer zone
        double f = 1.1;
        double g = 1.9;

        double k = 5; // positive correction constant

        ArrayList<ArrayList<Double>> probabilityGrid = new ArrayList<>();

        for (int i = 0; i < gridSize; i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (int j = 0; j < gridSize; j++) {
                Point2D.Double point = new Point2D.Double(i, j);
                double probability = rossmoeProbability(point, crimeLocations, f, g);
                row.add(probability*k);
            }
            probabilityGrid.add(row);
        }


        MyCanvas ctx = new MyCanvas(probabilityGrid);
    }

}

