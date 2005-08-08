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
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JPanel;

import net.sf.borg.common.util.Errmsg;
import net.sf.borg.common.util.PrefName;
import net.sf.borg.common.util.Resource;
import net.sf.borg.common.util.Version;
import net.sf.borg.model.Address;
import net.sf.borg.model.AddressModel;
/**
 *
 * @author  MBERGER
 * @version
 */

class AddressView extends View
{
    
    static
    {
        Version.addVersion("$Id$");
    }
    
    private Address addr_;
    
    public AddressView(Address addr)
    {
        super();
        addr_ = addr;
        addModel( AddressModel.getReference() );
        
        initComponents();       // init the GUI widgets
        
        // display the window
        pack();
        showaddr();
        
        manageMySize(PrefName.ADDRVIEWSIZE);
    }
    
    public void destroy()
    {
        this.dispose();
    }
    
    public void refresh()
    {}
    
    private void showaddr()
    {
        fntext.setText( addr_.getFirstName() );
        lntext.setText( addr_.getLastName() );
        nntext.setText( addr_.getNickname() );
        emtext.setText( addr_.getEmail() );
        sntext.setText( addr_.getScreenName() );
        wptext.setText( addr_.getWorkPhone() );
        hptext.setText( addr_.getHomePhone() );
        fxtext.setText( addr_.getFax() );
        pgtext.setText( addr_.getPager() );
        wbtext.setText( addr_.getWebPage() );
        notestext.setText( addr_.getNotes() );
        satext1.setText( addr_.getStreetAddress() );
        cttext1.setText( addr_.getCity() );
        sttext1.setText( addr_.getState() );
        cntext1.setText( addr_.getCountry() );
        zctext1.setText( addr_.getZip() );
        satext.setText( addr_.getWorkStreetAddress() );
        cttext.setText( addr_.getWorkCity() );
        sttext.setText( addr_.getWorkState() );
        cntext.setText( addr_.getWorkCountry() );
        zctext.setText( addr_.getWorkZip() );
        comptext.setText( addr_.getCompany() );
        Date bd = addr_.getBirthday();
        if( bd != null )
        {
            DateFormat sdf = DateFormat.getDateInstance(DateFormat.SHORT);
            bdtext.setText( sdf.format(bd));
        }
        else
            bdtext.setText("");
    }
    
    private void saveaddr()
    {
        if( fntext.getText().equals("") || lntext.getText().equals("") )
        {
            Errmsg.notice(Resource.getResourceString("First_and_Last_name_are_Required") );
            return;
        }
        
        addr_.setFirstName( fntext.getText() );
        addr_.setLastName( lntext.getText() );
        addr_.setNickname( nntext.getText() );
        addr_.setEmail( emtext.getText() );
        addr_.setScreenName( sntext.getText() );
        addr_.setWorkPhone( wptext.getText() );
        addr_.setHomePhone( hptext.getText() );
        addr_.setFax( fxtext.getText() );
        addr_.setPager( pgtext.getText() );
        addr_.setWebPage( wbtext.getText() );
        addr_.setNotes( notestext.getText() );
        addr_.setStreetAddress( satext1.getText() );
        addr_.setCity( cttext1.getText() );
        addr_.setState( sttext1.getText() );
        addr_.setCountry( cntext1.getText() );
        addr_.setZip( zctext1.getText() );
        addr_.setWorkStreetAddress( satext.getText() );
        addr_.setWorkCity( cttext.getText() );
        addr_.setWorkState( sttext.getText() );
        addr_.setWorkCountry( cntext.getText() );
        addr_.setWorkZip( zctext.getText() );
        addr_.setCompany( comptext.getText() );
        
        Date bd = null;
        String bdt = bdtext.getText();
        if( bdt == null || bdt.equals(""))
        {
            addr_.setBirthday(null);
        }
        else
        {
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
            try
            {
                bd = df.parse(bdt);
            }
            catch( Exception e )
            {
                Errmsg.notice(Resource.getResourceString("invdate"));
                return;
            }
            
            if( bd != null )
                addr_.setBirthday(bd);
        }
        
        try
        {
            AddressModel.getReference().saveAddress( addr_ );
            this.dispose();
        }
        catch( Exception e )
        {
            Errmsg.errmsg(e);
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fntext = new javax.swing.JTextField();
        lntext = new javax.swing.JTextField();
        nntext = new javax.swing.JTextField();
        sntext = new javax.swing.JTextField();
        hptext = new javax.swing.JTextField();
        wptext = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pgtext = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fxtext = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        emtext = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        wbtext = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        comptext = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        bdtext = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        satext = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cttext = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        sttext = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cntext = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        zctext = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        zctext1 = new javax.swing.JTextField();
        cntext1 = new javax.swing.JTextField();
        sttext1 = new javax.swing.JTextField();
        cttext1 = new javax.swing.JTextField();
        satext1 = new javax.swing.JTextField();
        GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
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
        GridBagConstraints gridBagConstraints36 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints38 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints39 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints43 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints45 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints46 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints47 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints49 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints52 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints53 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints54 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints55 = new GridBagConstraints();
        notestext = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Address_Book_Entry"));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(540, 400));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("First_Name:"));

        jLabel2.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Last_Name:"));

        jLabel3.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Nickname:"));

        jLabel4.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Screen_Name:"));

        jLabel5.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Home_Phone:"));

        jLabel6.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Work_Phone:"));


        jLabel7.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Pager:"));


        jLabel8.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Fax:"));


        jLabel9.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Email:"));


        jLabel14.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Web_Page:"));


        jLabel21.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Company"));


        jLabel22.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Birthday"));


        jTabbedPane1.addTab(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("contact"), jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("HomeAddress")));
        jLabel10.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Street_Address"));


        jLabel11.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("City:"));


        jLabel12.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("State:"));


        jLabel13.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Country:"));


        jLabel15.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Zip_Code:"));



        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("WorkAddress")));
        jLabel16.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Street_Address"));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("City:"));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("State:"));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Zip_Code:"));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Country:"));





        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        satext1.setMinimumSize(new java.awt.Dimension(4, 50));


        jTabbedPane1.addTab(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Address"), jPanel2);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/Save16.gif")));
        jButton2.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Save"));
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel4.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/Stop16.gif")));
        jButton3.setText(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Dismiss"));
        this.setContentPane(getJPanel());
        gridBagConstraints8.gridx = 0;
        gridBagConstraints8.gridy = 0;
        gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints8.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints9.gridx = 0;
        gridBagConstraints9.gridy = 1;
        gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints9.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints10.gridx = 0;
        gridBagConstraints10.gridy = 2;
        gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints10.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints11.gridx = 0;
        gridBagConstraints11.gridy = 3;
        gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints11.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints12.gridx = 0;
        gridBagConstraints12.gridy = 4;
        gridBagConstraints12.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints12.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints13.gridx = 0;
        gridBagConstraints13.gridy = 5;
        gridBagConstraints13.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints13.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints14.gridx = 0;
        gridBagConstraints14.gridy = 6;
        gridBagConstraints14.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints14.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints15.gridx = 0;
        gridBagConstraints15.gridy = 7;
        gridBagConstraints15.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints15.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints16.gridx = 0;
        gridBagConstraints16.gridy = 8;
        gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints16.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints17.gridx = 0;
        gridBagConstraints17.gridy = 9;
        gridBagConstraints17.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints17.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints18.gridx = 0;
        gridBagConstraints18.gridy = 10;
        gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints18.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints19.gridx = 0;
        gridBagConstraints19.gridy = 11;
        gridBagConstraints19.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints19.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints20.gridx = 1;
        gridBagConstraints20.gridy = 0;
        gridBagConstraints20.weightx = 1.0;
        gridBagConstraints20.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints21.gridx = 1;
        gridBagConstraints21.gridy = 9;
        gridBagConstraints21.weightx = 1.0;
        gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints22.gridx = 1;
        gridBagConstraints22.gridy = 6;
        gridBagConstraints22.weightx = 1.0;
        gridBagConstraints22.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints23.gridx = 1;
        gridBagConstraints23.gridy = 10;
        gridBagConstraints23.weightx = 1.0;
        gridBagConstraints23.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints24.gridx = 1;
        gridBagConstraints24.gridy = 1;
        gridBagConstraints24.weightx = 1.0;
        gridBagConstraints24.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints25.gridx = 1;
        gridBagConstraints25.gridy = 2;
        gridBagConstraints25.weightx = 1.0;
        gridBagConstraints25.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints26.gridx = 1;
        gridBagConstraints26.gridy = 5;
        gridBagConstraints26.weightx = 1.0;
        gridBagConstraints26.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints27.gridx = 1;
        gridBagConstraints27.gridy = 7;
        gridBagConstraints27.weightx = 1.0;
        gridBagConstraints27.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints28.gridx = 1;
        gridBagConstraints28.gridy = 4;
        gridBagConstraints28.weightx = 1.0;
        gridBagConstraints28.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints29.gridx = 1;
        gridBagConstraints29.gridy = 11;
        gridBagConstraints29.weightx = 1.0;
        gridBagConstraints29.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints30.gridx = 1;
        gridBagConstraints30.gridy = 8;
        gridBagConstraints30.weightx = 1.0;
        gridBagConstraints30.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints31.gridx = 1;
        gridBagConstraints31.gridy = 3;
        gridBagConstraints31.weightx = 1.0;
        gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints32.gridx = 0;
        gridBagConstraints32.gridy = 1;
        gridBagConstraints32.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints32.weightx = 1.0D;
        gridBagConstraints32.weighty = 1.0D;
        gridBagConstraints33.gridx = 0;
        gridBagConstraints33.gridy = 0;
        gridBagConstraints33.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints33.weightx = 1.0D;
        gridBagConstraints33.weighty = 1.0D;
        gridBagConstraints34.gridx = 0;
        gridBagConstraints34.gridy = 0;
        gridBagConstraints34.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints35.gridx = 0;
        gridBagConstraints35.gridy = 1;
        gridBagConstraints35.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints36.gridx = 0;
        gridBagConstraints36.gridy = 2;
        gridBagConstraints36.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints36.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints37.gridx = 0;
        gridBagConstraints37.gridy = 3;
        gridBagConstraints37.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints38.gridx = 0;
        gridBagConstraints38.gridy = 4;
        gridBagConstraints38.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints39.gridx = 0;
        gridBagConstraints39.gridy = 0;
        gridBagConstraints39.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints40.gridx = 0;
        gridBagConstraints40.gridy = 1;
        gridBagConstraints40.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints41.gridx = 0;
        gridBagConstraints41.gridy = 2;
        gridBagConstraints41.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints42.gridx = 0;
        gridBagConstraints42.gridy = 3;
        gridBagConstraints42.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints43.gridx = 0;
        gridBagConstraints43.gridy = 4;
        gridBagConstraints43.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints45.gridx = 1;
        gridBagConstraints45.gridy = 2;
        gridBagConstraints45.weightx = 1.0;
        gridBagConstraints45.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints46.gridx = 1;
        gridBagConstraints46.gridy = 1;
        gridBagConstraints46.weightx = 1.0;
        gridBagConstraints46.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints47.gridx = 1;
        gridBagConstraints47.gridy = 4;
        gridBagConstraints47.weightx = 1.0;
        gridBagConstraints47.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints49.gridx = 1;
        gridBagConstraints49.gridy = 2;
        gridBagConstraints49.weightx = 1.0;
        gridBagConstraints49.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints50.gridx = 1;
        gridBagConstraints50.gridy = 1;
        gridBagConstraints50.weightx = 1.0;
        gridBagConstraints50.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints51.gridx = 1;
        gridBagConstraints51.gridy = 3;
        gridBagConstraints51.weightx = 1.0;
        gridBagConstraints51.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints52.gridx = 1;
        gridBagConstraints52.gridy = 3;
        gridBagConstraints52.weightx = 1.0;
        gridBagConstraints52.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints53.gridx = 1;
        gridBagConstraints53.gridy = 4;
        gridBagConstraints53.weightx = 1.0;
        gridBagConstraints53.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints54.gridx = 1;
        gridBagConstraints54.gridy = 0;
        gridBagConstraints54.weightx = 1.0;
        gridBagConstraints54.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints55.gridx = 1;
        gridBagConstraints55.gridy = 0;
        gridBagConstraints55.weightx = 1.0;
        gridBagConstraints55.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel1, gridBagConstraints8);
        jPanel2.add(jPanel5, gridBagConstraints32);
        jPanel3.add(jLabel16, gridBagConstraints34);
        jPanel5.add(jLabel10, gridBagConstraints39);
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel4.add(jButton3);


        jPanel1.add(jLabel2, gridBagConstraints9);
        jPanel2.add(jPanel3, gridBagConstraints33);
        jPanel3.add(jLabel17, gridBagConstraints35);
        jPanel5.add(jLabel11, gridBagConstraints40);
        jPanel1.add(jLabel3, gridBagConstraints10);
        jPanel1.add(jLabel4, gridBagConstraints11);
        jPanel5.add(jLabel18, gridBagConstraints41);
        jTabbedPane1.addTab(java.util.ResourceBundle.getBundle("resource/borg_resource").getString("Notes"), getJPanel6());
        //jTabbedPane1.addTab(null, null, getJPanel6(), null);
        jPanel3.add(jLabel12, gridBagConstraints36);
        jPanel3.add(jLabel13, gridBagConstraints37);
        jPanel5.add(jLabel20, gridBagConstraints42);
        jPanel1.add(jLabel5, gridBagConstraints12);
        jPanel3.add(jLabel15, gridBagConstraints38);
        jPanel5.add(jLabel19, gridBagConstraints43);
        jPanel1.add(jLabel6, gridBagConstraints13);
        jPanel3.add(zctext1, gridBagConstraints47);
        jPanel5.add(sttext, gridBagConstraints45);
        jPanel1.add(jLabel7, gridBagConstraints14);
        jPanel3.add(sttext1, gridBagConstraints49);
        jPanel5.add(cttext, gridBagConstraints46);
        jPanel1.add(jLabel8, gridBagConstraints15);
        jPanel3.add(cttext1, gridBagConstraints50);
        jPanel5.add(cntext, gridBagConstraints51);
        jPanel1.add(jLabel9, gridBagConstraints16);
        jPanel3.add(cntext1, gridBagConstraints52);
        jPanel5.add(zctext, gridBagConstraints53);
        jPanel1.add(jLabel14, gridBagConstraints17);
        jPanel3.add(satext1, gridBagConstraints54);
        jPanel5.add(satext, gridBagConstraints55);
        jPanel1.add(jLabel21, gridBagConstraints18);
        jPanel1.add(jLabel22, gridBagConstraints19);
        jPanel1.add(fntext, gridBagConstraints20);
        jPanel1.add(wbtext, gridBagConstraints21);
        jPanel1.add(pgtext, gridBagConstraints22);
        jPanel1.add(comptext, gridBagConstraints23);
        jPanel1.add(lntext, gridBagConstraints24);
        jPanel1.add(nntext, gridBagConstraints25);
        jPanel1.add(wptext, gridBagConstraints26);
        jPanel1.add(fxtext, gridBagConstraints27);
        jPanel1.add(hptext, gridBagConstraints28);
        jPanel1.add(bdtext, gridBagConstraints29);
        jPanel1.add(emtext, gridBagConstraints30);
        jPanel1.add(sntext, gridBagConstraints31);
    }//GEN-END:initComponents
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        saveaddr();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    // save a task
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bdtext;
    private javax.swing.JTextField cntext;
    private javax.swing.JTextField cntext1;
    private javax.swing.JTextField comptext;
    private javax.swing.JTextField cttext;
    private javax.swing.JTextField cttext1;
    private javax.swing.JTextField emtext;
    private javax.swing.JTextField fntext;
    private javax.swing.JTextField fxtext;
    private javax.swing.JTextField hptext;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;  //  @jve:decl-index=0:visual-constraint="57,1330"
    private javax.swing.JTextField lntext;
    private javax.swing.JTextField nntext;
    private javax.swing.JTextArea notestext;
    private javax.swing.JTextField pgtext;
    private javax.swing.JTextField satext;
    private javax.swing.JTextField satext1;
    private javax.swing.JTextField sntext;
    private javax.swing.JTextField sttext;
    private javax.swing.JTextField sttext1;
    private javax.swing.JTextField wbtext;
    private javax.swing.JTextField wptext;
    private javax.swing.JTextField zctext;
    private javax.swing.JTextField zctext1;
    // End of variables declaration//GEN-END:variables
    
	private JPanel jPanel = null;
	private JPanel jPanel6 = null;
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 1;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.weighty = 1.0;
			gridBagConstraints7.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 0;
			jPanel.add(jPanel4, gridBagConstraints6);
			jPanel.add(jTabbedPane1, gridBagConstraints7);
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanel6	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			GridBagConstraints gridBagConstraints56 = new GridBagConstraints();
			jPanel6 = new JPanel();
			jPanel6.setLayout(new GridBagLayout());
			gridBagConstraints56.gridx = 1;
			gridBagConstraints56.gridy = 0;
			gridBagConstraints56.gridwidth = 0;
			gridBagConstraints56.weightx = 1.0;
			gridBagConstraints56.weighty = 1.0;
			gridBagConstraints56.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints56.insets = new java.awt.Insets(4,4,4,4);
			jPanel6.add(notestext, gridBagConstraints56);
		}
		return jPanel6;
	}
  }
