/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline_reservation_system;

import static java.awt.Color.white;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Mubarak
 */
public class AdminHome extends JFrame implements ActionListener{
    private JButton addflights,modify,delete,logout;
    
    
    public AdminHome(){
        
        super("AdminHome");
        //setForeground(white);
        setTitle("Admin Home");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon bg=new ImageIcon(ClassLoader.getSystemResource("airline_reservation_system/images/airplane1.jpg"));
        JLabel bgimage=new JLabel(bg);
        add(bgimage);
        bgimage.setBounds(0,0,900,610);
        bgimage.setVisible(true);
        
        JLabel top=new JLabel("Admin Home");
        top.setBounds(100,0,800,100);
        top.setFont(new Font("Verdana",Font.BOLD,30));
        bgimage.add(top);
        top.setForeground(white);
        top.setVisible(true);
        addflights = new JButton("Add Flights");
        addflights.setBounds(100,200,150,20);
        bgimage.add(addflights);
        addflights.addActionListener(this);
        modify = new JButton("View Flights");
        modify.setBounds(300,200,150,20);
        bgimage.add(modify);
        modify.addActionListener(this);
        delete = new JButton("Delete Flights");
        delete.setBounds(100,300,150,20);
        bgimage.add(delete);
        delete.addActionListener(this);
        setLayout(null);
        logout = new JButton("logout");
        logout.setBounds(300,300,150,20);
        bgimage.add(logout);
        logout.addActionListener(this);
        
        
    }
    
    @Override
        public void actionPerformed(ActionEvent ae){
            if(ae.getSource()==addflights){
                this.dispose();
                AddFlights af=new AddFlights();
                af.setVisible(true);
            }
            if(ae.getSource()==logout){
                this.dispose();
                Login l=new Login();
                l.setVisible(true);
            }
            if(ae.getSource()==modify){
                this.dispose();
                ViewFlights v=new ViewFlights();
                v.setVisible(true);
            }
            if(ae.getSource()==delete){
                this.dispose();
                DeleteFlight df=new DeleteFlight();
                df.setVisible(true);
            }
        }
}
