import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RossmoeGrid {

    private static double calculateDistance(Point2D.Double p1, Point2D.Double p2) {
        return p1.distance(p2);
    }

    private static double rossmoeProbability(Point2D.Double point, List<Point2D.Double> crimeCoords, double b) {
        double probability = 0;
        for (Point2D.Double crime : crimeCoords) {
            double distance = calculateDistance(point, crime);
            if (distance > 5) {
                // one of the two below lines work, but I didn't make any way to test so can you do that 
                // probability += 1.0 / Math.pow(distance-5, b);
                probability += 1.0 / Math.pow(distance, b);
            } else if (distance <= 5) {
                return 0.0;
            }
        }
        return probability;
    }

    public static void main(String[] args) {
        int gridSize = 100;
        
        List<Point2D.Double> crimeLocations = new ArrayList<>();
        crimeLocations.add(new Point2D.Double(20, 30));
        crimeLocations.add(new Point2D.Double(50, 50));
        crimeLocations.add(new Point2D.Double(80, 70));

        // random exponent in the formula
        double b = 1.5;

        ArrayList<ArrayList<Double>> probabilityGrid = new ArrayList<>();

        for (int i = 0; i < gridSize; i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (int j = 0; j < gridSize; j++) {
                Point2D.Double point = new Point2D.Double(i, j);
                double probability = rossmoeProbability(point, crimeLocations, b);
                row.add(probability);
            }
            probabilityGrid.add(row);
        }

        // Output the grid for testing
        // for (int i = 0; i < gridSize; i++) {
        //    for (int j = 0; j < gridSize; j++) {
        //        System.out.printf("%.5f ", probabilityGrid.get(i).get(j));
        //    }
        //    System.out.println();
        //}
    }
}

