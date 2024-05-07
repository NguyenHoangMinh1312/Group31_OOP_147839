package voltage_source;
import java.awt.Graphics2D;

public class Source {
    private double voltage;
    private double frequency;
    private Graphics2D label;
    
    //Constructor
    public Source(double voltage, double frequency){
        this.voltage = voltage;
        this.frequency = frequency;
    }

    //Accessors
    public double getVoltage(){
        return voltage;
    }

    public double getFrequency(){
        return frequency;
    }

    public Graphics2D getLabel(){
        return label;
    }

    public void setLabel(int x, int y){
        this.label.drawOval(x, y, 10, 10);
        if(this.frequency ==0)
            this.label.drawString("+/-", x + 5, y + 5);
        else
            this.label.drawString("~", x + 5, y + 5);
    }

    
}
