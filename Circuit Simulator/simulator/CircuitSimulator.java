package simulator;
import javax.swing.*;

public class CircuitSimulator{

    public static JFrame frame;
    public static JTabbedPane pane;

    public static void main(String[] args){
        //main frame
        frame= new JFrame("Circuit Simulator");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); 

        //Create pane for panels
        pane= new JTabbedPane();

        //add panels to pane
        pane.addTab("Serial Circuit", new SerialPanel());
        pane.addTab("Parallel Circuit", new ParallelPanel());

        frame.add(pane);
        frame.setVisible(true);

    }
    
}
