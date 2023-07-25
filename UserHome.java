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
public class UserHome extends JFrame implements ActionListener{
    private JButton book,cancel,status,web,logout;
    public UserHome(){
        setTitle("Airline Reservation System");
        setSize(900,600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon bg=new ImageIcon(ClassLoader.getSystemResource("airline_reservation_system/images/airplane1.jpg"));
        JLabel bgimage=new JLabel(bg);
        add(bgimage);
        bgimage.setBounds(0,0,900,610);
        bgimage.setVisible(true);
        JLabel top=new JLabel("Home Page");
        top.setBounds(100,0,800,100);
        top.setFont(new Font("Verdana",Font.BOLD,30));
        bgimage.add(top);
        top.setForeground(white);
        book = new JButton("Book Tickets");
        book.setBounds(100,200,150,30);
        bgimage.add(book);
        book.addActionListener(this);
        cancel = new JButton("Cancel Tickets");
        cancel.setBounds(500,200,150,30);
        bgimage.add(cancel);
        cancel.addActionListener(this);
        status = new JButton("Flight Status");
        status.setBounds(100,300,150,30);
        bgimage.add(status);
        status.addActionListener(this);
        web = new JButton("View Tickets");
        web.setBounds(500,300,150,30);
        bgimage.add(web);
        web.addActionListener(this);
        logout = new JButton("Logout");
        logout.setBounds(300,400,150,30);
        bgimage.add(logout);
        logout.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==logout){
            this.dispose();
            Login l = new Login();
            l.setVisible(true);
        }
        if(ae.getSource()==book){
            this.dispose();
            BookTickets bt = new BookTickets();
            bt.setVisible(true);
            //System.out.println("book");
            
        }
        if(ae.getSource()==cancel){
            this.dispose();
            CancelTickets ct = new CancelTickets();
            ct.setVisible(true);
            System.out.println("cancel");
        }
        if(ae.getSource()==status){
            this.dispose();
            FlightStatus fs = new FlightStatus();
            fs.setVisible(true);
            //System.out.println("status");
        }
        if(ae.getSource()==web){
            this.dispose();
            ViewTicket wc = new ViewTicket();
            wc.setVisible(true);
            //System.out.println("web");
        }
    }
}
