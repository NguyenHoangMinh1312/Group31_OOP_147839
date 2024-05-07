package simulator;
import circuit.*;
import element.*;
import voltage_source.*;
import javax.swing.*;
import javax.swing.table.*;


public abstract class Panel extends JPanel{
    protected JComboBox<String> sourceDropDown;
    protected JButton addResistorButton;
    protected JButton addCapacitorButton;
    protected JButton addInductorButton;
    protected JButton removeElementButton;
    protected JButton submitButton;
    protected JTable infoTable;
    protected DefaultTableModel infoTableModel;
    protected JTable analysisTable = null;
    protected DefaultTableModel analysisTableModel;
    protected Circuit circuit;
    //Constructor
    public Panel(){
        String[] sourceOption= {"DC source", "AC source"};
        this.sourceDropDown = new JComboBox<>(sourceOption);
        this.sourceDropDown.addActionListener(e -> setSourceFunction());

        this.addResistorButton = new JButton("Add resistor");
        this.addResistorButton.addActionListener(e -> addResistorFunction());

        this.addCapacitorButton = new JButton("Add capacitor");
        this.addCapacitorButton.addActionListener(e -> addCapacitorFunction());

        this.addInductorButton = new JButton("Add inductor");
        this.addInductorButton.addActionListener(e -> addInductorFunction());

        this.removeElementButton = new JButton("Remove element");
        this.removeElementButton.addActionListener(e -> removeElementFunction());

        this.submitButton = new JButton("Submit");
        this.submitButton.addActionListener(e -> submitFunction());
        
        this.infoTableModel= new DefaultTableModel();
        this.infoTable= new JTable(infoTableModel);
        this.infoTableModel.addColumn("Type");
        this.infoTableModel.addColumn("Parameter");
        this.infoTableModel.addColumn("Unit");
        this.infoTableModel.addColumn("Name");
        this.infoTableModel.addRow(new Object[]{"Type", "Parameter", "Unit", "Name"});

        // Add elements to the panel
        this.add(this.sourceDropDown);
        this.add(this.addResistorButton);   
        this.add(this.addCapacitorButton);
        this.add(this.addInductorButton);
        this.add(this.removeElementButton);
        this.add(this.submitButton);
        this.add(this.infoTable);
    }

    public void setSourceFunction(){
        String sourceType= (String) this.sourceDropDown.getSelectedItem();
        //if DC source
        if(sourceType.equals("DC source")){
            //ask the user to enter the voltage
            while(true){
                try{
                    String voltageStr= JOptionPane.showInputDialog("Enter the voltage(V):");
                    //if the user pressed cancel
                    if(voltageStr == null)
                        return;
                    double voltage= Double.parseDouble(voltageStr);
                    if(voltage < 0){
                        JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
                        continue;
                    }
                    else{
                        this.circuit.setSource(new Source(voltage, 0));
                        break;
                    }
                    
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
                }
            }
        }
        //if AC source
        else{
            //ask the user to enter the voltage
            double voltage;
            while(true){
                try{
                    String voltageStr= JOptionPane.showInputDialog("Enter the voltage(V):");
                    //if the user pressed cancel
                    if(voltageStr == null)
                        return;
                    voltage= Double.parseDouble(voltageStr);
                    if(voltage < 0)
                        JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
                    else
                        break;
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
                }
            }

            //ask the user to enter the frequency
            while(true){
                try{
                    String frequencyStr= JOptionPane.showInputDialog("Enter the frequency(Hz):");
                    //if the user pressed cancel
                    if(frequencyStr == null)
                        return;
                    double frequency= Double.parseDouble(frequencyStr); 
                    if(frequency < 0){
                        JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
                        continue;
                    }
                    else{
                        this.circuit.setSource(new Source(voltage, frequency));
                        break;
                    }
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
                }
            }

        }
    }

    public void addResistorFunction(){
        //ask the user to enter the resistance
        while(true){
            try{
                String resistorStr = JOptionPane.showInputDialog("Enter the resistance(Ohm):");
                //if user pressed canceled
                if(resistorStr == null)
                    return;
                double resistance= Double.parseDouble(resistorStr);
                if(resistance < 0){
                    JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
                    continue;
                }
                else{
                    Resistor resistor= new Resistor(resistance);
                    if(!this.circuit.addElement(resistor))
                        JOptionPane.showMessageDialog(null, "Maximum number of elements reached!");
                    else{
                        //add to the last row of info table
                        this.infoTableModel.addRow(new Object[]{"Resistor", resistance, "Ohm", resistor.getName()});
                        this.revalidate();
                    }
                    break;
                    
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
            }
        }
    }
    
    public void addCapacitorFunction(){
        //ask the user to enter the capacitance
        while(true){
            try{
                String capacitorStr = JOptionPane.showInputDialog("Enter the capacitance(Farad):");
                //if user pressed canceled
                if(capacitorStr == null)
                    return;
                double capacitance= Double.parseDouble(capacitorStr);
                if(capacitance < 0){
                    JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
                    continue;
                }
                else{
                    Capacitor capacitor= new Capacitor(capacitance);
                    if(!this.circuit.addElement(capacitor))
                        JOptionPane.showMessageDialog(null, "Maximum number of elements reached!");
                    else{
                        //add to the last row of info table
                        this.infoTableModel.addRow(new Object[]{"Capacitor", capacitance, "Farad", capacitor.getName()});
                        this.revalidate();
                    }
                    break;
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
            }
        }

    }
    
    public void addInductorFunction(){
        //ask the user to enter the inductance
        while(true){
            try{
                String inductanceStr = JOptionPane.showInputDialog("Enter the inductance(Henry):");
                //if user pressed canceled
                if(inductanceStr == null)
                    return;
                
                double inductance= Double.parseDouble(inductanceStr);
                if(inductance < 0){
                    JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
                    continue;
                }
                else{
                    Inductor inductor= new Inductor(inductance);
                    if(!this.circuit.addElement(inductor))
                        JOptionPane.showMessageDialog(null, "Maximum number of elements reached!");
                    else{
                        //add to the last row of info table
                        this.infoTableModel.addRow(new Object[]{"Inductor", inductance, "Henry", inductor.getName()});
                        // this.revalidate();
                    }
                    break;
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
            }
        }
    }
            
    public void removeElementFunction(){
        //ask the user to enter the name of the element
        String name= JOptionPane.showInputDialog("Enter the name of the element:");
        //if the user clicked cancel
        if(name == null)
            return;
        
        if(!this.circuit.removeElement(name))
            JOptionPane.showMessageDialog(null, "Element not found!");
        else {
            // Find the info row with the matching name and remove it
            for (int i = 0; i < this.infoTableModel.getRowCount(); i++) 
                if(this.infoTableModel.getValueAt(i, 3).equals(name)){
                    this.infoTableModel.removeRow(i);
                    break;
                }
            //Find the analysis row with the matching name and remove it
            if (analysisTableModel != null)
                for (int i = 0; i < this.analysisTableModel.getRowCount(); i++) 
                    if(this.analysisTableModel.getValueAt(i, 0).equals(name)){
                        this.analysisTableModel.removeRow(i);
                        break;
                    }
                
            JOptionPane.showMessageDialog(null, name + " removed successfully!");
        }
    }
    
    public void submitFunction(){
        if(analysisTable != null)
            this.remove(analysisTable);
        //Analyse the circuit
        this.circuit.circuitAnalysis();

        //show the results
        if(this.circuit.getIsShortCircuit()){
            JOptionPane.showMessageDialog(null, "Short circuit detected!");
            return;
        }
        analysisTableModel= new DefaultTableModel();
        analysisTableModel.addColumn("Name");
        analysisTableModel.addColumn("Voltage(V)");
        analysisTableModel.addColumn("Current(A)");
        analysisTableModel.addColumn("Resistance(Ohm)");
        analysisTableModel.addRow(new Object[]{"Name", "Voltage (V)", "Current (A)", "Resistance (Ohm)"});
        
        for(ElectricComponent i: this.circuit.getElements())
            analysisTableModel.addRow(new Object[]{i.getName(), i.getVoltage().toString(), i.getCurrent().toString(), i.getResistance().toString()});
        
        // Create a new analysisTable and add it to the panel
        analysisTable = new JTable(analysisTableModel);
        //make the columns wider
        for (int i = 0; i < analysisTable.getColumnCount(); i++) {
            TableColumn column = analysisTable.getColumnModel().getColumn(i);
            int currentWidth = column.getPreferredWidth();
            column.setPreferredWidth(currentWidth * 2);
        }
        //add the table to the panel
        this.add(analysisTable);
        this.revalidate();
        
        //draw the circuit diagram
        this.circuit.paint();
        
    }

    
}

    
    
