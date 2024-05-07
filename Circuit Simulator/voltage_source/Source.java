package voltage_source;

public class Source{
    private double voltage;
    private double frequency;    
    //Constructor
    public Source(double voltage, double frequency){
        this.voltage = voltage;
        this.frequency = frequency;
    }

    //Accessors
    public double getVoltage(){
        return voltage;
    }

    public double getFrequency(){
        return frequency;
    }

}
