package element;
import voltage_source.*;
import complex_number.*;


public abstract class ElectricComponent {
    protected String type;
    protected String unit;
    protected String name;
    protected double parameter;     //can be resistance, capacitance, or inductance
    protected ComplexNumber resistance;
    protected ComplexNumber voltage;
    protected ComplexNumber current;
   
    //Constuctor
    public ElectricComponent(String type, String unit, double parameter) {
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

    public double getParameter() {
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
        sb.append("\t");
        sb.append(this.current.toString());
        sb.append("\t");
        sb.append(this.resistance.toString());

        return sb.toString();
    }
    
    public abstract void setName();
    public abstract void setResistance(Source source);

}   