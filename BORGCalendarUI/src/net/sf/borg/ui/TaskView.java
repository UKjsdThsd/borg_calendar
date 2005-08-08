/*
This file is part of BORG.
 
    BORG is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.
 
    BORG is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
 
    You should have received a copy of the GNU General Public License
    along with BORG; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 
Copyright 2003 by ==Quiet==
 */
package net.sf.borg.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.sf.borg.common.util.Errmsg;
import net.sf.borg.common.util.PrefName;
import net.sf.borg.common.util.Resource;
import net.sf.borg.common.util.Version;
import net.sf.borg.model.CategoryModel;
import net.sf.borg.model.Task;
import net.sf.borg.model.TaskModel;
import net.sf.borg.model.TaskTypes;
/*
 * taskgui.java
 *
 * Created on October 10, 2001, 3:04 PM
 */


/**
 *
 * @author  MBERGER
 * @version
 */

// taskgui is a View that allows the user to edit a single task
class TaskView extends View
{
    
    static
    {
        Version.addVersion("$Id$");
    }
    
    private static TaskView singleton = null;
    private String curtype_ = null;
    static TaskView getReference(Task task, int function) throws Exception
    {
        if( singleton == null || !singleton.isShowing())
            singleton = new TaskView(task, function);
        return( singleton );
    }
    
    private TaskView(Task task, int function) throws Exception
    {
        super();
        addModel( TaskModel.getReference() );
        
        initComponents();       // init the GUI widgets
        
        // set size of text area
        jTextArea1.setRows(15);
        jTextArea1.setColumns(40);
        
        try
        {
            Collection cats = CategoryModel.getReference().getCategories();
            Iterator it = cats.iterator();
            while( it.hasNext())
            {
                catbox.addItem( it.next());
            }
            catbox.setSelectedIndex(0);
        }
        catch( Exception e )
        {
            Errmsg.errmsg(e);
        }
        
        // display the window
        pack();
        showtask( function, task );
        
        manageMySize(PrefName.TASKVIEWSIZE);
    }
    
    public void destroy()
    {
        this.dispose();
    }
    
    // the different function values for calls to show task
    static int T_CLONE = 1;
    static int T_ADD = 2;
    static int T_CHANGE = 3;
    
    // the task editor currently does not refresh itself when the task data model changes
    // - although it should not be changing while the task editor is open
    public void refresh()
    {}
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {
        java.awt.GridBagConstraints gridBagConstraints;

        GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        itemtext = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        startdatechooser = new de.wannawork.jcalendar.JCalendarComboBox();
        duedatechooser = new de.wannawork.jcalendar.JCalendarComboBox();
        pritext = new javax.swing.JTextField();
        patext = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        statebox = new javax.swing.JComboBox();
        typebox = new javax.swing.JComboBox();
        catlabel = new javax.swing.JLabel();
        GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jTextArea1.setLineWrap(true);
        jTextArea1.setName("Description");
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane1.addTab(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Description"), jScrollPane1);

        jTextArea2.setLineWrap(true);
        jScrollPane2.setViewportView(jTextArea2);

        jTabbedPane1.addTab(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Resolution"), jScrollPane2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints38 = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0D;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Subtasks")));
        jCheckBox1.setText("jCheckBox1");
        jPanel1.add(jCheckBox1);

        jCheckBox2.setText("jCheckBox2");
        jPanel1.add(jCheckBox2);

        jCheckBox3.setText("jCheckBox3");
        jPanel1.add(jCheckBox3);

        jPanel1.add(jCheckBox4);

        jPanel1.add(jCheckBox5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("UserDefinedSubtasks")));
        jCheckBox6.setText("user defined subtask box");
        jPanel2.add(jCheckBox6);

        jCheckBox7.setText("jCheckBox7");
        jPanel2.add(jCheckBox7);

        jCheckBox8.setText("jCheckBox8");
        jPanel2.add(jCheckBox8);

        jCheckBox9.setText("jCheckBox9");
        jPanel2.add(jCheckBox9);

        jCheckBox10.setText("jCheckBox10");
        jPanel2.add(jCheckBox10);

        jPanel2.add(jSeparator1);

        jButton1.setFont(new java.awt.Font("Dialog", 1, 10));
        jButton1.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Set_Subtasks"));
        //jButton1.setBorder(new javax.swing.border.EtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("TaskInformation")));

        itemtext.setText("itemtext");
        
        jLabel1.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Item_#"));
        
        jLabel2.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Status"));
 
        jLabel3.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("StartDate"));

        jLabel4.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("DueDate"));
 
        jLabel5.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Pri"));

        jLabel6.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("PA"));
 
        jLabel7.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Type"));
 
        typebox.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                typeboxActionPerformed(evt);
            }
        });


        catlabel.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Category"));
 
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jPanel3, gridBagConstraints);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/Save16.gif")));
        jButton2.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Save"));
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel4.add(jButton2, jButton2.getName());
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/Stop16.gif")));
        jButton3.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Dismiss"));
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel4.add(jButton3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        jMenu1.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Menu"));
        jMenuItem1.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Save"));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                savetask(evt);
            }
        });

        jMenu1.add(jMenuItem1);

        jMenuItem2.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Dismiss"));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                disact(evt);
            }
        });

        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        this.setSize(560, 446);
        this.setContentPane(getJPanel());

        gridBagConstraints26.gridx = 1;
        gridBagConstraints26.gridy = 1;
        gridBagConstraints26.weightx = 1.0;
        gridBagConstraints26.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints27.gridx = 0;
        gridBagConstraints27.gridy = 3;
        gridBagConstraints27.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints27.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints28.gridx = 0;
        gridBagConstraints28.gridy = 4;
        gridBagConstraints28.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints28.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints29.gridx = 0;
        gridBagConstraints29.gridy = 5;
        gridBagConstraints29.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints29.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints30.gridx = 0;
        gridBagConstraints30.gridy = 6;
        gridBagConstraints30.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints30.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints31.gridx = 0;
        gridBagConstraints31.gridy = 7;
        gridBagConstraints31.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints31.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints32.gridx = 0;
        gridBagConstraints32.gridy = 8;
        gridBagConstraints32.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints32.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints33.gridx = 1;
        gridBagConstraints33.gridy = 5;
        gridBagConstraints33.weightx = 1.0;
        gridBagConstraints33.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints33.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints34.gridx = 1;
        gridBagConstraints34.gridy = 4;
        gridBagConstraints34.weightx = 1.0;
        gridBagConstraints34.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints34.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints35.gridx = 1;
        gridBagConstraints35.gridy = 3;
        gridBagConstraints35.weightx = 1.0;
        gridBagConstraints35.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints35.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints37.gridx = 0;
        gridBagConstraints37.gridy = 0;
        gridBagConstraints37.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints37.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints38.gridx = 1;
        gridBagConstraints38.gridy = 0;
        gridBagConstraints38.weightx = 1.0;
        gridBagConstraints38.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints38.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 7;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 6;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(4,4,4,4);
        gridBagConstraints11.gridx = 1;
        gridBagConstraints11.gridy = 8;
        gridBagConstraints11.weightx = 1.0;
        gridBagConstraints11.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints11.insets = new java.awt.Insets(4,4,4,4);
        jPanel3.add(jLabel7, gridBagConstraints27);
        jPanel3.add(jLabel3, gridBagConstraints28);
        jPanel3.add(jLabel4, gridBagConstraints29);
        jPanel3.add(jLabel5, gridBagConstraints30);
        jPanel3.add(jLabel6, gridBagConstraints31);
        jPanel3.add(catlabel, gridBagConstraints32);
        jPanel3.add(duedatechooser, gridBagConstraints33);
        jPanel3.add(startdatechooser, gridBagConstraints34);
        jPanel3.add(typebox, gridBagConstraints35);
        jPanel3.add(jLabel1, gridBagConstraints37);
        jPanel3.add(itemtext, gridBagConstraints38);
        jPanel3.add(jLabel2, gridBagConstraints1);
        jPanel3.add(patext, gridBagConstraints2);
        jPanel3.add(pritext, gridBagConstraints3);
        jPanel3.add(getCatbox(), gridBagConstraints11);
			gridBagConstraints26.insets = new java.awt.Insets(4,4,4,4);
			jPanel3.add(statebox, gridBagConstraints26);
 
 

    }//GEN-END:initComponents
    
    private void typeboxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_typeboxActionPerformed
    {//GEN-HEADEREND:event_typeboxActionPerformed
        String newtype = (String)typebox.getSelectedItem();
        if( curtype_ != null && !newtype.equals(curtype_))
        {
            // prompt for ok
            int ret;
            if( itemtext.getText().equals("NEW"))
            {
                // don't warn if chging type on a brand new task
                ret = JOptionPane.YES_OPTION;
            }
            else
            {
                ret = JOptionPane.showConfirmDialog(null,
                        	java.util.ResourceBundle.getBundle("resource/borg_resource").getString("chg_typ_warn"), 
                        	"", JOptionPane.YES_NO_OPTION );
            }
            
            if( ret == JOptionPane.YES_OPTION )
            {
                curtype_ = newtype;
                // fill in system controlled checkboxes
                TaskModel taskmod_ = TaskModel.getReference();
                statebox.removeAllItems();
                statebox.addItem("OPEN");                
                String cblabels[] = taskmod_.getTaskTypes().checkBoxes( newtype );
                jCheckBox1.setText(cblabels[0]);
                jCheckBox2.setText(cblabels[1]);
                jCheckBox3.setText(cblabels[2]);
                jCheckBox4.setText(cblabels[3]);
                jCheckBox5.setText(cblabels[4]);
                jCheckBox1.setSelected(false);
                jCheckBox2.setSelected(false);
                jCheckBox3.setSelected(false);
                jCheckBox4.setSelected(false);
                jCheckBox5.setSelected(false);
            }
            else
            {
                typebox.setSelectedItem(curtype_);
            }
        }
        
    }//GEN-LAST:event_typeboxActionPerformed
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        savetask( evt );
    }//GEN-LAST:event_jButton2ActionPerformed
    
    // edit user checkbox label
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // user wants to edit a user tunable check box label
        
        // prompt for which one
        String choices[] =
        {"1","2","3","4","5"};
        String inputValue = (String) JOptionPane.showInputDialog(null, Resource.getResourceString("Select_User_Checkbox_Number_to_Edit"), Resource.getResourceString("Select_Checkbox"), JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
        JCheckBox b = null;
        
        // set b depending on which checkbox they want to edit
        if( inputValue.equals("1") ) b = jCheckBox6;
        if( inputValue.equals("2") ) b = jCheckBox7;
        if( inputValue.equals("3") ) b = jCheckBox8;
        if( inputValue.equals("4") ) b = jCheckBox9;
        if( inputValue.equals("5") ) b = jCheckBox10;
        
        // get the new text
        String newVal = JOptionPane.showInputDialog( null, Resource.getResourceString("Enter_new_value"), b.getText() );
        
        // set the text value - this does no save anywhere except on the screen.
        // when the entire task is saved, this item will be saved too
        b.setText(newVal);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    // update the database fields for a task label from the labels on the screen
    private void updateUT(JCheckBox cb, Task task, String field) throws Exception
    {
        // get current screen label and label DB knows
        String newtext = cb.getText();
        
        
        String oldtext = "";
        if( field.equals("UT1") ) oldtext = task.getUserTask1();
        else if( field.equals("UT2") ) oldtext = task.getUserTask2();
        else if( field.equals("UT3") ) oldtext = task.getUserTask3();
        else if( field.equals("UT4") ) oldtext = task.getUserTask4();
        else if( field.equals("UT5") ) oldtext = task.getUserTask5();
        
        // if text was empty and still is - do nothing
        if( (newtext == null || newtext.equals("") || newtext.equals(TaskTypes.NOCBVALUE)) &&
        ( oldtext == null || oldtext.equals("") ))
            return;
        
        // if screen matches DB - do nothing
        if( newtext.equals(oldtext) )
            return;
        
        // update the field in the DB Row object
        if( field.equals("UT1") ) task.setUserTask1(newtext);
        else if( field.equals("UT2") ) task.setUserTask2(newtext);
        else if( field.equals("UT3") ) task.setUserTask3(newtext);
        else if( field.equals("UT4") ) task.setUserTask4(newtext);
        else if( field.equals("UT5") ) task.setUserTask5(newtext);
        
    }
    
    // save a task
    private void savetask(java.awt.event.ActionEvent evt)//GEN-FIRST:event_savetask
    {//GEN-HEADEREND:event_savetask
        
        // save a task from the data on the screen
        
        try
        {
            
            String num = itemtext.getText();
            
            // allocate a new task object from the task data model
            TaskModel taskmod_ = TaskModel.getReference();
            Task task = taskmod_.newMR();
            
            // set the task number to the current number for updates and
            // -1 for new tasks. task model will convert -1 to next available number
            if( num.equals("NEW") )
            {
                task.setTaskNumber(new Integer(-1));
            }else
            {
                task.setTaskNumber(new Integer(num));
            }
            
            // fill in the taks fields from the screen
            task.setState((String)statebox.getSelectedItem());  // state
            task.setType((String)typebox.getSelectedItem());  // type
            Calendar cal = startdatechooser.getCalendar();
            //cal.set(Calendar.HOUR,0);
            //cal.set(Calendar.MINUTE,0);
            //cal.set(Calendar.SECOND,0);
            task.setStartDate(cal.getTime());  // start date
            cal = duedatechooser.getCalendar();
            //cal.set(Calendar.HOUR,17);
            //cal.set(Calendar.MINUTE,0);
            //cal.set(Calendar.SECOND,1);
            task.setDueDate(cal.getTime());  // due date
            task.setPriority(pritext.getText());  // priority
            task.setPersonAssigned(patext.getText());  // person assigned
            if( jTextArea1.getText() == null || jTextArea1.getText().equals(""))
           	{
            	Errmsg.notice(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("empty_desc"));
            	return;
           	}
            task.setDescription(jTextArea1.getText());   // description
            task.setResolution(jTextArea2.getText());   // resolution
            
            //task.setCategory( cattext.getText());
            String cat = (String) catbox.getSelectedItem();
            if( cat.equals("") || cat.equals(CategoryModel.UNCATEGORIZED))
            {
                task.setCategory(null);
            }
            else
            {
                task.setCategory(cat);
            }       
            // set the subtask (todo) field by concatenating
            // the numbers of all of the checked subtask boxes
            String td = "";
            if( jCheckBox1.isSelected() == true )
                td += "1";
            if( jCheckBox2.isSelected() == true )
                td += "2";
            if( jCheckBox3.isSelected() == true )
                td += "3";
            if( jCheckBox4.isSelected() == true )
                td += "4";
            if( jCheckBox5.isSelected() == true )
                td += "5";
            if( jCheckBox6.isSelected() == true )
                td += "6";
            if( jCheckBox7.isSelected() == true )
                td += "7";
            if( jCheckBox8.isSelected() == true )
                td += "8";
            if( jCheckBox9.isSelected() == true )
                td += "9";
            if( jCheckBox10.isSelected() == true )
                td += "A";
            task.setTodoList(td);
            
            // save the user defined subtask labels
            updateUT( jCheckBox6, task, "UT1" );
            updateUT( jCheckBox7, task, "UT2" );
            updateUT( jCheckBox8, task, "UT3" );
            updateUT( jCheckBox9, task, "UT4" );
            updateUT( jCheckBox10, task, "UT5" );
            
            // save the task to the DB
            taskmod_.savetask(task);
            
            // refresh window from DB - will update task number for
            // new tasks and will set the list of available next states from
            // the task model
            showtask(T_CHANGE,task);
        }
        catch( Exception e )
        {
            Errmsg.errmsg(e);
            // JOptionPane.showMessageDialog(null, e.toString(), "Error",
            // JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_savetask
    
  private void disact(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disact
      this.dispose();
  }//GEN-LAST:event_disact
  
  private void showtask(int function, Task task) throws Exception
  {
      TaskModel taskmod_ = TaskModel.getReference();
      
      // show a task editor for changing, cloning, or add of a task
      
      // init the checkbox strings to dashes
      jCheckBox1.setText(TaskTypes.NOCBVALUE);
      jCheckBox2.setText(TaskTypes.NOCBVALUE);
      jCheckBox3.setText(TaskTypes.NOCBVALUE);
      jCheckBox4.setText(TaskTypes.NOCBVALUE);
      jCheckBox5.setText(TaskTypes.NOCBVALUE);
      jCheckBox6.setText(TaskTypes.NOCBVALUE);
      jCheckBox7.setText(TaskTypes.NOCBVALUE);
      jCheckBox8.setText(TaskTypes.NOCBVALUE);
      jCheckBox9.setText(TaskTypes.NOCBVALUE);
      jCheckBox10.setText(TaskTypes.NOCBVALUE);
      
      // if we are showing an existing task - fil; in the gui fields form it
      if( task != null )
      {
          // task number
          itemtext.setText( task.getTaskNumber().toString() );
          itemtext.setEditable(false);
          
          // window title - "Item N"
          setTitle(Resource.getResourceString("Item_") + task.getTaskNumber().toString() );
          
          // due date
          GregorianCalendar gc = new GregorianCalendar();
          Date dd = task.getDueDate();
          if( dd != null )
              gc.setTime(dd);

          duedatechooser.setCalendar(gc);
          
          GregorianCalendar gc2 = new GregorianCalendar();
          dd = task.getStartDate();
          if( dd != null )
              gc2.setTime(dd);

          startdatechooser.setCalendar(gc2);
          
          pritext.setText( task.getPriority() ); // priority
          patext.setText( task.getPersonAssigned() ); // person assigned
          
          
          // cattext.setText( task.getCategory() );
          String cat = task.getCategory();
          if( cat != null && !cat.equals(""))
          {
              catbox.setSelectedItem(cat);
          }
          else
          {
              catbox.setSelectedIndex(0);
          }
          
          jTextArea1.setText( task.getDescription() );  // description
          jTextArea2.setText( task.getResolution() );  // resolution
          
          statebox.addItem( task.getState());   // state
          statebox.setEditable(false);
          
          // type
          String type = task.getType();
          typebox.addItem( type );
          typebox.setEditable(false);
          
          // fill in system controlled checkboxes
          String cblabels[] = taskmod_.getTaskTypes().checkBoxes( type );
          jCheckBox1.setText(cblabels[0]);
          jCheckBox2.setText(cblabels[1]);
          jCheckBox3.setText(cblabels[2]);
          jCheckBox4.setText(cblabels[3]);
          jCheckBox5.setText(cblabels[4]);
          
          // set checkboxes based on digits in TD
          String td = task.getTodoList();
          if( td == null ) td = "";
          if( td.indexOf('1') != -1 )
          {
              jCheckBox1.setSelected(true);
          }
          if( td.indexOf('2') != -1 )
          {
              jCheckBox2.setSelected(true);
          }
          if( td.indexOf('3') != -1 )
          {
              jCheckBox3.setSelected(true);
          }
          if( td.indexOf('4') != -1 )
          {
              jCheckBox4.setSelected(true);
          }
          if( td.indexOf('5') != -1 )
          {
              jCheckBox5.setSelected(true);
          }
          if( td.indexOf('6') != -1 )
          {
              jCheckBox6.setSelected(true);
          }
          if( td.indexOf('7') != -1 )
          {
              jCheckBox7.setSelected(true);
          }
          if( td.indexOf('8') != -1 )
          {
              jCheckBox8.setSelected(true);
          }
          if( td.indexOf('9') != -1 )
          {
              jCheckBox9.setSelected(true);
          }
          if( td.indexOf('A') != -1 )
          {
              jCheckBox10.setSelected(true);
          }
          
          // set labels for user defined subtasks
          String s = task.getUserTask1();
          if( s != null ) jCheckBox6.setText(s);
          s = task.getUserTask2();
          if( s != null ) jCheckBox7.setText(s);
          s = task.getUserTask3();
          if( s != null ) jCheckBox8.setText(s);
          s = task.getUserTask4();
          if( s != null ) jCheckBox9.setText(s);
          s = task.getUserTask5();
          if( s != null ) jCheckBox10.setText(s);
      }
      else  // initialize new task
      {
          
          // task number = NEW
          itemtext.setText( "NEW" );
          itemtext.setEditable(false);
          
          // title
          setTitle(Resource.getResourceString("NEW_Item"));
          
          pritext.setText( "3" ); // priority default to 3
          patext.setText( "" ); // person assigned
          //cattext.setText("");
          catbox.setSelectedIndex(0);
          jTextArea1.setText( "" ); // desc
          jTextArea2.setText("" ); // resolution
          
          // state always starts with OPEN for new tasks
          statebox.addItem("OPEN");
          statebox.setEnabled(false);
          
      }
      
      Vector tv = taskmod_.getTaskTypes().getTaskTypes();
      for( int i = 0; i < tv.size(); i++ )
      {
          typebox.addItem( tv.elementAt(i) );
      }
      
      // cloning takes the fields filled in for an existing task and resets only those
      // that don't apply to the clone
      if( function == T_CLONE )
      {
          // need new task number
          itemtext.setText("NEW");
          itemtext.setEditable(false);
          
          // clone must start in OPEN state
          statebox.removeAllItems();
          statebox.addItem("OPEN");
          statebox.setEnabled(false);
          
          
      }
      // change existing task
      else if( function == T_CHANGE)
      {
          
          // determine valid next states based on task type and current state
          String stat = task.getState();
          String type = task.getType();
          Vector v = taskmod_.getTaskTypes().nextStates(stat,type);
          
          // set next state pulldown
          statebox.removeAllItems();
          for( int i = 0; i < v.size(); i++ )
          {
              statebox.addItem( v.elementAt(i) );
          }
          statebox.setEnabled(true);
          
      }
      
      curtype_ = (String)typebox.getSelectedItem();
      
  }
  
  
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel catlabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField itemtext;
    private de.wannawork.jcalendar.JCalendarComboBox startdatechooser;
    private de.wannawork.jcalendar.JCalendarComboBox duedatechooser;
    private javax.swing.JTextField pritext;
    private javax.swing.JTextField patext;
    private javax.swing.JComboBox statebox;
    private javax.swing.JComboBox typebox;
    // End of variables declaration//GEN-END:variables
    
	private JPanel jPanel = null;
	private JComboBox catbox = null;
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.gridy = 0;
			gridBagConstraints21.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints21.weightx = 1.0D;
			gridBagConstraints22.gridx = 1;
			gridBagConstraints22.gridy = 0;
			gridBagConstraints22.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints22.weightx = 1.0D;
			gridBagConstraints23.gridx = 2;
			gridBagConstraints23.gridy = 0;
			gridBagConstraints23.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints23.weightx = 1.0D;
			gridBagConstraints24.gridx = 0;
			gridBagConstraints24.gridy = 1;
			gridBagConstraints24.weightx = 1.0;
			gridBagConstraints24.weighty = 1.0;
			gridBagConstraints24.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints24.gridwidth = 3;
			gridBagConstraints25.gridx = 0;
			gridBagConstraints25.gridy = 2;
			gridBagConstraints25.gridwidth = 3;
			jPanel.add(jPanel3, gridBagConstraints21);
			jPanel.add(jPanel1, gridBagConstraints22);
			jPanel.add(jPanel2, gridBagConstraints23);
			jPanel.add(jTabbedPane1, gridBagConstraints24);
			jPanel.add(jPanel4, gridBagConstraints25);
		}
		return jPanel;
	}
	/**
	 * This method initializes catbox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	private JComboBox getCatbox() {
		if (catbox == null) {
			catbox = new JComboBox();
		}
		return catbox;
	}
  }  //  @jve:decl-index=0:visual-constraint="115,46"
