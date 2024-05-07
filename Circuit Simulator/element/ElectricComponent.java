package element;
import circuit.*;
import voltage_source.*;
import complex_number.*;
import java.awt.Graphics2D;


public abstract class ElectricComponent{
    protected String type;
    protected String unit;
    protected String name;
    protected double parameter;     //can be resistance, capacitance, or inductance
    protected ComplexNumber resistance;
    protected ComplexNumber voltage;
    protected ComplexNumber current;
    protected Graphics2D label;
    
    //Constuctor
    public ElectricComponent(String type, String unit, double parameter){
        this.type= type;
        this.unit= unit;
        this.parameter = parameter;
    }

    //Accessors & Mutators
    public String getType(){
        return type;
    }

    public String getUnit(){
        return unit;
    }

    public String getName(){
        return name;
    }

    public double getParameter(){
        return parameter;
    }
    
    public ComplexNumber getResistance() {
        return resistance;
    }

    public ComplexNumber getVoltage() {
        return voltage;
    }

    public void setVoltage(ComplexNumber voltage){
        this.voltage= voltage;
    }

    public ComplexNumber getCurrent() {
        return current;
    }

    public void setCurrent(ComplexNumber current){
        this.current= current;
    }

    public Graphics2D getLabel(){
        return label;
    }

    public String elementInfo(){
        StringBuffer sb= new StringBuffer();
        sb.append(this.type);
        sb.append("\t");
        sb.append(this.parameter);
        sb.append("\t\t\t");
        sb.append(this.unit);
        sb.append("\t\t");
        sb.append(this.name);

        return sb.toString();
    }

    public String elementAnalysis(){
        StringBuffer sb= new StringBuffer();
        sb.append(this.name);
        sb.append("\t");
        sb.append(this.voltage.toString());
        sb.append("\t\t");
        sb.append(this.current.toString());
        sb.append("\t\t");
        sb.append(this.resistance.toString());

        return sb.toString();
    }
    
    public abstract void setName(Circuit circuit);
    public abstract void setResistance(Source source);
    // public abstract void setLabel(Panel panel, int x, int y);
    // public abstract void drawHorizontally(int x, int y);
    // public abstract void drawVertically(int x, int y);
}   