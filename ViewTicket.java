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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Mubarak
 */
public class ViewTicket extends JFrame implements ActionListener{
    Connection con;
   ResultSet rs;
   private JLabel tkt,fno,src,des,depdate,deptime,pass,eco,bus,cost,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11;
   private JButton view,back;
   private JTextField ticket;
   public ViewTicket(){
       super("ViewTicket");
       setTitle("View Tickets");
       setSize(900,600);
       setLayout(null);
       con=Airline_Reservation_System.connectDb();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel top=new JLabel("View Tickets");
        top.setBounds(0,0,800,50);
        top.setFont(new Font("Verdana",Font.BOLD,25));
        add(top);
        JLabel label1= new JLabel("Ticket No");
        label1.setBounds(50,100,80,20);
        add(label1);
        ticket = new JTextField();
        ticket.setBounds(150,100,80,20);
        add(ticket);
        view = new JButton("View Ticket");
        view.setBounds(250,100,150,20);
        add(view);
        view.addActionListener(this);
        back = new JButton("Back");
        back.setBounds(400,100,80,20);
        add(back);
        back.addActionListener(this);
        label2=new JLabel("Ticket No");
        label2.setBounds(300,150,100,20);
        add(label2);
        label2.setVisible(false);
        tkt= new JLabel(" ");
        tkt.setBounds(500,150,100,20);
        add(tkt);
        tkt.setVisible(false);
        label3=new JLabel("Flight no");
        label3.setBounds(300,180,100,20);
        add(label3);
        label3.setVisible(false);
        fno=new JLabel(" ");
        fno.setBounds(500,180,100,20);
        add(fno);
        fno.setVisible(false);
        label4=new JLabel("From");
        label4.setBounds(300,210,150,20);
        add(label4);
        label4.setVisible(false);
        src= new JLabel(" ");
        src.setBounds(500,210,150,20);
        add(src);
        src.setVisible(false);
        label5= new JLabel("To");
        label5.setBounds(300,240,100,20);
        add(label5);
        label5.setVisible(false);
        des= new JLabel(" ");
        des.setBounds(500,240,150,20);
        add(des);
        des.setVisible(false);
        label6=new JLabel("Passport");
        label6.setBounds(300,270,100,20);
        add(label6);
        label6.setVisible(false);
        pass= new JLabel(" ");
        pass.setBounds(500,270,150,20);
        add(pass);
        pass.setVisible(false);
        label7=new JLabel("Date");
        label7.setBounds(300,300,100,20);
        add(label7);
        label7.setVisible(false);
        depdate = new JLabel(" ");
        depdate.setBounds(500,300,150,20);
        add(depdate);
        depdate.setVisible(true);
        label8 = new JLabel("Time");
        label8.setBounds(300,330,100,20);
        add(label8);
        label8.setVisible(false);
        deptime=new JLabel(" ");
        deptime.setBounds(500,330,100,20);
        add(deptime);
        deptime.setVisible(false);
        label9=new JLabel("Economy Seats");
        label9.setBounds(300,360,150,20);
        add(label9);
        label9.setVisible(false);
        eco=new JLabel(" ");
        eco.setBounds(500,360,100,20);
        add(eco);
        eco.setVisible(false);
        label10=new JLabel("Business class seats");
        label10.setBounds(300,390,180,20);
        add(label10);
        label10.setVisible(false);
        bus=new JLabel(" ");
        bus.setBounds(500,390,100,20);
        add(bus);
        bus.setVisible(false);
        label11= new JLabel("Total Cost");
        label11.setBounds(300,420,150,20);
        add(label11);
        label11.setVisible(false);
        cost= new JLabel(" ");
        cost.setBounds(500,420,100,20);
        add(cost);
        cost.setVisible(false);
        
    }
   
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==view){
            String sql = "Select * from ticket where ticketno='"+ticket.getText()+"'";
            try{
                PreparedStatement pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                while(rs.next()){
                    tkt.setText(rs.getString("ticketno"));
                    fno.setText(rs.getString("flightno"));
                    src.setText(rs.getString("source"));
                    des.setText(rs.getString("destination"));
                    depdate.setText(rs.getString("date"));
                    deptime.setText(rs.getString("time"));
                    pass.setText(rs.getString("passport"));
                    eco.setText(rs.getString("economyseats"));
                    bus.setText(rs.getString("businessseats"));
                    cost.setText(rs.getString("totalcost"));
                    tkt.setVisible(true);
                    fno.setVisible(true);
                    src.setVisible(true);
                    des.setVisible(true);
                    depdate.setVisible(true);
                    deptime.setVisible(true);
                    pass.setVisible(true);
                    eco.setVisible(true);
                    bus.setVisible(true);
                    cost.setVisible(true);
                    label2.setVisible(true);
                    label3.setVisible(true);
                    label4.setVisible(true);
                    label5.setVisible(true);
                    label6.setVisible(true);
                    label7.setVisible(true);
                    label8.setVisible(true);
                    label9.setVisible(true);
                    label10.setVisible(true);
                    label11.setVisible(true);
                    
                }
            }catch(Exception exc){
                JOptionPane.showMessageDialog(null,exc);
            }
        }
        if(ae.getSource()==back){
            this.dispose();
            UserHome uh = new UserHome();
            uh.setVisible(true);
        }
    }
}
