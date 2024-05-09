package circuit;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import element.*;

public class SerialCircuitDiagram extends JPanel{
    private SerialCircuit circuit;

    public SerialCircuitDiagram(SerialCircuit circuit){
        super();
        this.circuit= circuit;
    }

    //override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        
        int x= 100, y=100;
        //draw the components
        for(ElectricComponent i : this.circuit.getElements()){
            g2d.drawLine(x, y, x + 50, y);
            x+=50;
            if(i instanceof Resistor){
                g2d.drawRect(x, y-5, 50, 10);
                g2d.drawString(i.getName(), x + 20 , y - 35);
                g2d.drawString(i.getParameter() + " Ohm", x +2, y - 20);
                x+=50;
            }

            else if(i instanceof Capacitor){
                g2d.drawLine(x, y-15, x, y + 15);
                g2d.drawLine(x + 10, y-15, x + 10, y + 15);
                g2d.drawString(i.getName(), x, y - 35);
                g2d.drawString(i.getParameter() + " F", x-8, y - 20);

                x+=10;
            }

            else if(i instanceof Inductor){
                for(int j=0; j<6; j++){
                    if(j%2==0)
                        g2d.drawArc(x + j * 10, y-5, 10, 10, 0, 180);
                    else
                        g2d.drawArc(x + j * 10, y-5, 10, 10, 180, 180);
                }
                g2d.drawString(i.getName(), x + 20, y - 35);
                g2d.drawString(i.getParameter() + " H", x +6, y - 20);
                x+=60;
            }
        }
        g2d.drawLine(x, y, x + 30, y);
        x+=30;
        g2d.drawLine(100, 100, 100, 200);
        g2d.drawLine(x, 100, x, 200);
        g2d.drawOval((x + 100)/2 -20, 180, 40, 40);
        
        //draw the source
        if(this.circuit.source.getFrequency() == 0)
            g2d.drawString("+-", (x + 100)/2-4, 205);
        else
            g2d.drawString("~", (x + 100)/2 - 2 , 205);
        g2d.drawString("" + this.circuit.source.getVoltage() + " V", (x + 100)/2 - 10, 170);
        
        g2d.drawLine(100, 200, (x + 100)/2 -20, 200);
        g2d.drawLine(x, 200, (x + 100)/2 + 20, 200);
    }
    
}
