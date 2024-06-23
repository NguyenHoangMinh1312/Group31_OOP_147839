package circuit;
import element.*;
import voltage_source.*;
import complex_number.*;
import java.util.ArrayList;
import javax.swing.JPanel;


public abstract class Circuit{
    protected ArrayList<ElectricComponent> elements= new ArrayList<>();
    protected Source source= new Source(0, 0);     //default source
    protected int nbResistor=0;
    protected int nbCapacitor=0;
    protected int nbInductor=0;
    protected boolean isShortCircuit;
    protected ComplexNumber eqResistance;
    protected JPanel circuitDiagram;

    //Accessors and Mutators
    public ArrayList<ElectricComponent> getElements(){
        return this.elements;
    }

    public Source getSource(){
        return this.source;
    }

    public void setSource(Source source){
        this.source= source;
    }

    public int getNbResistor(){
        return this.nbResistor;
    }

    public int getNbCapacitor(){
        return this.nbCapacitor;
    }

    public int getNbInductor(){
        return this.nbInductor;
    }

    public boolean getIsShortCircuit(){
        return this.isShortCircuit;
    }

    public ComplexNumber getEqResistance(){
        return this.eqResistance;
    }

    public JPanel getCircuitDiagram(){
        return this.circuitDiagram;
    }

    //return true if the element is added, false otherwise
    public boolean addElement(ElectricComponent element){
        if(nbCapacitor + nbInductor + nbResistor >= 5)
            return false;
        
        elements.add(element);
        if(element instanceof Resistor)
            this.nbResistor++;
        else if(element instanceof Capacitor)
            this.nbCapacitor++;
        else if(element instanceof Inductor)
            this.nbInductor++;
        
        //set the name for the new element
        element.setName(this);
        return true;
    }

    //return true if the element is removed, false otherwise
    public boolean removeElement(String name){
        for(ElectricComponent i : elements){
            if(i.getName().equals(name)){
                elements.remove(i);
                if(i instanceof Resistor)
                    nbResistor--;
                else if(i instanceof Capacitor)
                    nbCapacitor--;
                else if(i instanceof Inductor)
                    nbInductor--;
                return true;
            }
        }
        return false;
    }

    public abstract void setEqResistance();
    public abstract void setElementVoltage();
    public abstract void setElementCurrent();
    public abstract void circuitAnalysis();
    public abstract void paint();
}
