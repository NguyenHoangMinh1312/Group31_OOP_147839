import voltage_source.*;
import element.*;
import circuit.*;

public class CircuitSimulator {
    public static void main(String[] args){
        Source source= new Source(5, 1000);
        Circuit circuit= new ParallelCircuit(source);
        Resistor r1= new Resistor(1000);
        Resistor r2= new Resistor(2000);
        Inductor l1= new Inductor(0.001);
        Capacitor c1= new Capacitor(0.000001);
        
        circuit.addElement(r1);
        circuit.addElement(r2);
        circuit.addElement(l1);
        circuit.addElement(c1);
        
        circuit.listElementInfo();

        // System.out.println(r1.getResistance().toString());
        // System.out.println(r2.getResistance().toString());
        // System.out.println(l1.getResistance().toString());
        // System.out.println(c1.getResistance().toString());
        
        
        // System.out.println(circuit.getEqResistance().toString());
        circuit.setEqResistance();
        circuit.setElementCurrent();
        circuit.setElementVoltage();
        circuit.listElementAnalysis();
    }
    
}
