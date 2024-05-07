package element;
import circuit.*;
import voltage_source.*;
import complex_number.*;
import simulator.*;

public class Inductor extends ElectricComponent {
    //Constructor
    public Inductor(double inductance) {
        super("Inductor", "Henry", inductance);
    }

    public void setName(Circuit circuit){
        this.name= "L" + circuit.getNbInductor();
    }

    public void setResistance(Source source) {
        if (source.getFrequency() != 0) // if AC source
            this.resistance = new ComplexNumber(0, 2 * Math.PI * source.getFrequency() * parameter);
        else
            this.resistance = new ComplexNumber(0, 0); // if DC source
    }

    public void setLabel(Panel panel, int x, int y){
        int width = 10, height = 10;
        //if in a serial circuit, draw the label horizontally
        if(panel instanceof SerialPanel)
            for (int i = 0; i < 10; i++) 
                if (i % 2 == 0) 
                    this.label.drawArc(x + i * width, y, width, height, 0, 180);
                else 
                    this.label.drawArc(x + i * width, y, width, height, 180, 180);
                
        //if in a parallel circuit, draw the label vertically
        else
            for (int i = 0; i < 10; i++) 
                if (i % 2 == 0) 
                    this.label.drawArc(x, y + i * height, width, height, 0, 180);
                else 
                    this.label.drawArc(x, y + i * height, width, height, 180, 180);
    }

    public void drawHorizontal(int x, int y){
        for(int i=0; i<5; i++){
            if(i%2 == 0)
                this.label.drawArc(x + i * 50, y, 50, 10, 0, 180);
            
            else
                this.label.drawArc(x+ i * 50, y, 50, 10, 180, 180);
            
        }
    }

    public void drawVertical(int x, int y){
        for(int i=0; i<5; i++){
            if(i%2 == 0)
                this.label.drawArc(x, y + i * 50, 10, 50, 0, 180);
            
            else
                this.label.drawArc(x, y + i * 50, 10, 50, 180, 180);
        }
    }
}
