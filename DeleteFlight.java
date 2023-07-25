/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline_reservation_system;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mubarak
 */
public class DeleteFlight extends JFrame implements ActionListener {
    private JTextField fno;
    Connection con;
   ResultSet rs;
   JButton del,back;
    public DeleteFlight(){
        super("DeleteFlight");
        setTitle("Delete Flights");
        setSize(900,600);
        setLayout(null);
        con=Airline_Reservation_System.connectDb();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel top=new JLabel("Delete Flights");
        top.setBounds(100,0,800,100);
        top.setFont(new Font("Verdana",Font.BOLD,25));
        add(top);
        JLabel label1 = new JLabel("Flight no");
        label1.setBounds(250,200,150,20);
        add(label1);
        fno= new JTextField();
        fno.setBounds(500,200,150,20);
        add(fno);
        del=new JButton("Delete Flight");
        del.setBounds(500,300,150,20);
        add(del);
        del.addActionListener(this);
        back=new JButton("Back");
        back.setBounds(250,300,150,20);
        add(back);
        back.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==del){
            String sql1="select * from flight where flightno=?";
            String sql="delete from flight where flightno=?";
            try{
                PreparedStatement pst1=con.prepareStatement(sql1);
                pst1.setString(1, fno.getText());
                rs=pst1.executeQuery();
                if(rs.next()){
                    try{
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, fno.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Flight Deleted Successfully");
                        con.close();
                        this.dispose();
                        AdminHome ah= new AdminHome();
                        ah.setVisible(true);
            }catch(Exception exc){
                JOptionPane.showMessageDialog(null, exc);
                        exc.printStackTrace();
            }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect flight no");
                }
                
            }catch(Exception exc){
                JOptionPane.showMessageDialog(null, exc);
                        exc.printStackTrace();
            }
            
        }
        if(ae.getSource()==back){
            this.dispose();
            AdminHome ah=new AdminHome();
            ah.setVisible(true);
        }
    }
}
