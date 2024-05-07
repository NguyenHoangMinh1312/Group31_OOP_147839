package circuit;
import element.*;

import javax.swing.JPanel;
import javax.swing.JFrame;

import complex_number.*;

public class SerialCircuit extends Circuit{
    //Constructor
    public JPanel circuitDiagram;
    public boolean shouldPaint = false; 
    public SerialCircuit(){
        super();
    }
    
    public void setEqResistance(){
        this.eqResistance= new ComplexNumber(0, 0);
        for(ElectricComponent i: this.elements){
            i.setResistance(this.source);
            this.eqResistance= this.eqResistance.add(i.getResistance());
        }
        if(this.eqResistance.isZero())
            this.isShortCircuit= true;
        else
            this.isShortCircuit= false;
    }

    public void setElementCurrent(){
        if(this.isShortCircuit)
            for(ElectricComponent i: this.elements)
                i.setCurrent(new ComplexNumber(Double.POSITIVE_INFINITY, 0)); //Short Circuit
        
        else if (this.eqResistance.isInfinity())
            for(ElectricComponent i: this.elements)
                i.setCurrent(new ComplexNumber(0, 0)); //Open Circuit
        else
            for(ElectricComponent i: this.elements)
                i.setCurrent(new ComplexNumber(this.source.getVoltage(), 0).divide(this.eqResistance));
    }

    public void setElementVoltage(){
        if(this.isShortCircuit)
            for(ElectricComponent i: this.elements)
                i.setVoltage(new ComplexNumber(0, 0)); //Short Circuit
        
        else if (this.eqResistance.isInfinity())
            for(ElectricComponent i: this.elements)
                if(i instanceof Capacitor)
                    i.setVoltage(new ComplexNumber(this.source.getVoltage(), 0));
                else
                    i.setVoltage(new ComplexNumber(0, 0)); 
        else
            for(ElectricComponent i: this.elements)
                i.setVoltage(i.getResistance().multiply(i.getCurrent()));
    }

    public void circuitAnalysis(){
        setEqResistance();
        setElementCurrent();
        setElementVoltage();
    }

    public void paint(){
        this.circuitDiagram = new SerialCircuitDiagram(this);
        //create a new window to add the circuit diagram
        JFrame frame = new JFrame("Serial Circuit Diagram");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.toFront();
        frame.setLocationRelativeTo(null);
        
        frame.add(this.circuitDiagram);
        
        frame.setVisible(true);
        
    }
}