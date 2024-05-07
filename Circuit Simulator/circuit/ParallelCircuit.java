package circuit;
import element.*;
import complex_number.*;
import javax.swing.JFrame;

public class ParallelCircuit extends Circuit{
    //Constructor
    public ParallelCircuit(){
        super();
    }

    public void setEqResistance(){
        ComplexNumber sum= new ComplexNumber(0, 0);
        for(ElectricComponent i: this.elements){
            i.setResistance(this.source);
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
        
        else{
            for(ElectricComponent i: this.elements)
                i.setVoltage(new ComplexNumber(this.source.getVoltage(), 0));
        }
    }

    public void setElementCurrent(){
        setElementVoltage();
        
        if(this.isShortCircuit)
            for(ElectricComponent i: this.elements)
                i.setCurrent(new ComplexNumber(Double.POSITIVE_INFINITY, 0)); //Short Circuit
        
        else if (this.eqResistance.isInfinity())
            for(ElectricComponent i: this.elements)
                i.setCurrent(new ComplexNumber(0, 0)); //Open Circuit
        
        else
            for(ElectricComponent i: this.elements){
                if(i instanceof Capacitor)
                    i.setCurrent(new ComplexNumber(0, 0));
                else
                    i.setCurrent(new ComplexNumber(this.source.getVoltage(), 0).divide(i.getResistance()));
            }
                
    }

    public void circuitAnalysis(){
        setEqResistance();
        setElementVoltage();
        setElementCurrent();
    }

    public void paint(){
        this.circuitDiagram = new ParallelCircuitDiagram(this);
        //create a new window to add the circuit diagram
        JFrame frame = new JFrame("Parallel Circuit Diagram");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.toFront();
        
        frame.add(this.circuitDiagram);
        
        frame.setVisible(true);
    }

}