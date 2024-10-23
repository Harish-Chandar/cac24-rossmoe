import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MyCanvas extends Canvas {

    Frame frame = new Frame("Rossmoes-Grid");
    Canvas canvas = new Canvas();
    private ArrayList<ArrayList<Double>> grid; // Store the pixel values

    public MyCanvas(ArrayList<ArrayList<Double>> inpGrid) {
        // Create an instance of the canvas
        frame.add(this);
        frame.setSize(400, 400);
        
        grid = inpGrid;
        System.out.println(grid);

        // Add a window listener to handle the close event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);

        repaint();
    }

    // Override the paint method to draw on the canvas
    @Override
    public void paint(Graphics g) {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                double val = grid.get(x).get(y);
                int colorVal = (int) (val * 250);
                if (colorVal > 255) {
                    colorVal = 255;
                }
                g.setColor(new Color(colorVal, 0, 0));
                g.fillRect(x*4,y*4, 4,4);
            }
        }
    }
}
