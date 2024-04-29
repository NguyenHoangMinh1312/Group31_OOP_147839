package element;
import circuit.*;
import voltage_source.*;
import complex_number.*;

public class Resistor extends ElectricComponent {
    //Constructor
    public Resistor(double resistance){
        super("Resistor", "Ohm", resistance);
    }

    public void setName() {
        this.name = "R" + Circuit.nbResistor;
    }
    
    public void setResistance(Source source) {
        this.resistance=  new ComplexNumber(parameter, 0);
    }

}
