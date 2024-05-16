package simulator;
import javax.swing.JOptionPane;

import circuit.*;

public class ParallelPanel extends Panel{
    //Constructor
    public ParallelPanel(){
        super();
        this.circuit= new ParallelCircuit();

    }

    public void resetFunction(){
        int response= JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the circuit?");
        if(response == JOptionPane.YES_OPTION){
            this.circuit = new ParallelCircuit();
            if(this.infoTableModel == null)
                return;
            for(int i=this.infoTableModel.getRowCount()-1; i>0; i--)
                this.infoTableModel.removeRow(i);
        
            if(this.analysisTableModel == null)
                return;
            for(int i=this.analysisTableModel.getRowCount()-1; i>=0; i--)
                this.analysisTableModel.removeRow(i);
        
            this.infoTable.setModel(this.infoTableModel);
            this.remove(this.analysisTable);
            this.revalidate();
            this.repaint();
        }
        else
            return;
    }
}
