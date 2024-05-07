package circuit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import element.*;

public class ParallelCircuitDiagram extends JPanel{
    private ParallelCircuit circuit;

    public ParallelCircuitDiagram(ParallelCircuit circuit){
        super();
        this.circuit= circuit;
    }

    //override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int x= 100, y=100;
        //draw the source
        g2d.drawLine(x, y, x, y + 30);
        g2d.drawOval(x- 20, y+ 30, 40, 40);
        if(this.circuit.source.getFrequency() == 0)
            g2d.drawString("+-", x-4, y + 55);
        else
            g2d.drawString("~", x - 2 , y + 55);
        g2d.drawString(this.circuit.source.getVoltage() + " V", x-60, y + 55);
        g2d.drawLine(x, y + 70, x, y + 100);

        //draw the components
        for(ElectricComponent i : this.circuit.getElements() ){
            g2d.drawLine(x, y, x + 100, y);
            g2d.drawLine(x, 200, x+100, 200);
            x+=100;
            
            if(i instanceof Resistor){
                g2d.drawLine(x, y, x, y+25);
                g2d.drawRect(x-5, y+ 25, 10, 50);
                g2d.drawLine(x, y+75, x, 200);
                g2d.drawString(i.getName(), x-50, y+50);
                g2d.drawString(i.getParameter() + " Ohm", x-75, y+65);
            }

            else if(i instanceof Capacitor){
                g2d.drawLine(x, y, x, y+45);
                g2d.drawLine(x-15, y+45, x+15, y+45);
                g2d.drawLine(x-15, y+55, x+15, y+55);
                g2d.drawString(i.getName(), x-50, y+55);
                g2d.drawString(i.getParameter() + " F", x-60, y+70);

                g2d.drawLine(x, y+55, x, y+100);
                
            }

            else if(i instanceof Inductor){
                g2d.drawLine(x, y, x, y+30);
                for(int j=0; j<4; j++){
                    if(j%2==0)
                        g2d.drawArc(x - 5, y + 30 + j * 10, 10, 10, 90, 180);
                    else
                    g2d.drawArc(x - 5, y +30 + j * 10, 10, 10, 270, 180);
                }
                g2d.drawString(i.getName(), x-50, y + 50);
                g2d.drawString(i.getParameter() + " H", x-60, y + 65);
                g2d.drawLine(x, y+70, x, 200);

            }

        }
    }
    
}
