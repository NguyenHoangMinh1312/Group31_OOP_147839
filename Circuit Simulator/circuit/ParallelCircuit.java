package circuit;
import voltage_source.*;
import element.*;
import complex_number.*;

public class ParallelCircuit extends Circuit{

    //Constructor
    public ParallelCircuit(Source source){
        super(source);
    }

    public void setEqResistance(){
        ComplexNumber sum= new ComplexNumber(0, 0);
        for(ElectricComponent i: this.elements){
            if(i.getResistance().isZero()){
                this.isShortCircuit= true;
                return;
            }
            else if(i.getResistance().isInfinity())
                sum= sum.add(new ComplexNumber(0, 0));
            
            else
                sum= sum.add(new ComplexNumber(1, 0).divide(i.getResistance()));
        }
        
        this.eqResistance= new ComplexNumber(1, 0).divide(sum);
    }

    public void setElementVoltage(){
        setEqResistance();

        if(this.isShortCircuit)
            for(ElectricComponent i: this.elements)
                i.setVoltage(new ComplexNumber(0, 0)); //Short Circuit

        else if(this.eqResistance.isInfinity())
            for(ElectricComponent i: this.elements)
                i.setVoltage(new ComplexNumber(0, 0)); //Open Circuit
        
        else{
            for(ElectricComponent i: this.elements)
                i.setVoltage(new ComplexNumber(this.source.getVoltage(), 0));
        }
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
}