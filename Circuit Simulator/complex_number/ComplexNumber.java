package complex_number;

public class ComplexNumber {
    private double real;
    private double imaginary;

    //Constructor
    public ComplexNumber(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal(){
        return real;
    }

    public double getImaginary(){
        return imaginary;
    }

    public String toString(){
        if(imaginary == 0)
            return real + "";
        else
            return real + " + " + imaginary + "i";
    }

    public ComplexNumber add(ComplexNumber c){
        return new ComplexNumber(this.real + c.getReal(), this.imaginary + c.getImaginary());
    }

    public ComplexNumber multiply(ComplexNumber c){
        return new ComplexNumber(this.real * c.getReal() - this.imaginary * c.getImaginary(), this.real * c.getImaginary() + this.imaginary * c.getReal());
    }

    public ComplexNumber divide(ComplexNumber c){
        //if divide by infinity
        if(c.isInfinity())
            return new ComplexNumber(0, 0);
        
        double denominator = c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary();
        return new ComplexNumber((this.real * c.getReal() + this.imaginary * c.getImaginary()) / denominator, (this.imaginary * c.getReal() - this.real * c.getImaginary()) / denominator);
    }
    
    public boolean isZero(){
        return this.real == 0 && this.imaginary == 0;
    }

    public boolean isInfinity(){
        return this.real == Double.POSITIVE_INFINITY;
    }

    
    
}
