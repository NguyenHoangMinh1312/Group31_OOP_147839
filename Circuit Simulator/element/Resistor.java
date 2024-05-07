package element;
import circuit.*;
import voltage_source.*;
import complex_number.*;

public class Resistor extends ElectricComponent{
    //Constructor
    public Resistor(double resistance){
        super("Resistor", "Ohm", resistance);
    }

    public void setName(Circuit circuit){
        this.name = "R" + circuit.getNbResistor();
    }
    
    public void setResistance(Source source){
        this.resistance=  new ComplexNumber(parameter, 0);
    }

    public void drawHorizontal(int x, int y){
        for(int i=0; i<5; i++){
            if(i%2==0)
                this.label.drawLine(x + i * 10, y, x + (i + 1) * 10, y + 10);
            else
                this.label.drawLine(x + i * 10, y + 10, x + (i + 1) * 10, y);
        }
    }

    public void drawVertical(int x, int y){
        for(int i=0; i<5; i++){
            if(i%2==0)
                this.label.drawLine(x, y + i * 10, x + 10, y + (i + 1) * 10);
            else
                this.label.drawLine(x + 10, y + i * 10, x, y + (i + 1) * 10);
        }
    }

    
}
