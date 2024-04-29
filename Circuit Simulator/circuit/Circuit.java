package circuit;
import element.*;
import voltage_source.*;
import complex_number.*;
import java.util.ArrayList;

public abstract class Circuit{
    protected ArrayList<ElectricComponent> elements= new ArrayList<>();
    protected Source source;
    public static int nbResistor=0;
    public static int nbCapacitor=0;
    public static int nbInductor=0;
    protected boolean isShortCircuit;
    protected ComplexNumber eqResistance;

    //Constructor
    public Circuit(Source source){
        this.source = source;
    }

    public ComplexNumber getEqResistance(){
        return eqResistance;
    }

    public void addElement(ElectricComponent element){
        if(nbCapacitor + nbInductor + nbResistor >= 5){
            System.out.println("Maximum number of elements reached!");
            return;
        }
        
        elements.add(element);
        if(element instanceof Resistor)
            nbResistor++;
        else if(element instanceof Capacitor)
            nbCapacitor++;
        else if(element instanceof Inductor)
            nbInductor++;

        //set the name and calculate the resistance
        element.setName();
        element.setResistance(source);
    }

    public void removeElement(ElectricComponent element){
        if(elements.isEmpty()){
            System.out.println("No elements to remove!");
            return;
        }

        if(!elements.contains(element)){
            System.out.println("Element not found!");
            return;
        }
        
        elements.remove(element);
        for(int i=0; i<elements.size(); i++)
            elements.get(i).setName();
        if(element instanceof Resistor)
            nbResistor--;
        else if(element instanceof Capacitor)
            nbCapacitor--;
        else if(element instanceof Inductor)
            nbInductor--;

        //set the name again for the left elements
        for(ElectricComponent i: elements)
            i.setName();
    }


    public void listElementInfo(){
        System.out.println("********************************LIST OF ELEMENTS*************************************************");
        System.out.println("Type\t\tParameter\t\tUnit\t\tName");
        for(ElectricComponent i: elements)
            System.out.println(i.elementInfo());
        
        System.out.println("*************************************************************************************************");
    }

    public void listElementAnalysis(){
        if(this.isShortCircuit){
            System.out.println("Short circuit detected! No analysis possible!");
            return;
        }

        System.out.println("********************************ELEMENTS ANALYSIS************************************************");
        System.out.println("\tVoltage\t\tCurrent\t\tResistance");
        for(ElectricComponent i: elements)
            System.out.println(i.elementAnalysis());
        
        System.out.println("*************************************************************************************************");
    }

    public abstract void setEqResistance();
    public abstract void setElementVoltage();
    public abstract void setElementCurrent();
}
