package circuit;
import voltage_source.*;
import element.*;
import complex_number.*;

public class SerialCircuit extends Circuit{
    //Constructor
    public SerialCircuit(Source source){
        super(source);
    }
    
    public void setEqResistance(){
        this.eqResistance= new ComplexNumber(0, 0);
        for(ElectricComponent i: this.elements)
            this.eqResistance= this.eqResistance.add(i.getResistance());

        if(this.eqResistance.isZero())
            this.isShortCircuit= true;
        else
            this.isShortCircuit= false;
    }

    public void setElementCurrent(){
        setEqResistance();
        
        if(this.isShortCircuit)
            for(ElectricComponent i: this.elements)
                i.setCurrent(new ComplexNumber(Double.POSITIVE_INFINITY, 0)); //Short Circuit
        
        else if (this.eqResistance.isInfinity())
            for(ElectricComponent i: this.elements)
                i.setCurrent(new ComplexNumber(0, 0)); //Open Circuit
        else
            for(ElectricComponent i: this.elements)
                i.setCurrent(new ComplexNumber(this.source.getVoltage(), 0).divide(i.getResistance()));
    }

    public void setElementVoltage(){
        if(this.isShortCircuit)
            for(ElectricComponent i: this.elements)
                i.setVoltage(new ComplexNumber(0, 0)); //Short Circuit
        
        else if (this.eqResistance.isInfinity())
            for(ElectricComponent i: this.elements)
                i.setVoltage(new ComplexNumber(0, 0)); //Open Circuit
        
        else
            for(ElectricComponent i: this.elements)
                i.setVoltage(i.getResistance().multiply(i.getCurrent()));
    }
    
}