package element;
import circuit.*;
import voltage_source.*;
import complex_number.*;

public class Capacitor extends ElectricComponent{
    //Constructor
    public Capacitor(double capacitance){
        super("Capacitor", "Farad", capacitance);
    }

    public void setName(Circuit circuit){
        this.name= "C" + circuit.getNbCapacitor();
    }
    
    public void setResistance(Source source){
        if(source.getFrequency() !=0)    //if AC source
            this.resistance=  new ComplexNumber(0, -1/(2 *Math.PI *source.getFrequency()*parameter));
        else
            this.resistance= new ComplexNumber(Double.POSITIVE_INFINITY, 0);    //if DC source
    }
}
