package element;
import circuit.*;
import voltage_source.*;
import complex_number.*;

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
}
