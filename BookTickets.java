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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Mubarak
 */
public class BookTickets extends JFrame implements ActionListener {
     Connection con;
   ResultSet rs;
   public String[] seats={"0","1","2","3","4"};
   private JComboBox eco,bus;
   private JTable table;
   private JLabel label3,label4,label5,label6,label7,label8,label9,label10,label11,label12,label13,label14,label15,label16,label17,label18;
    private JButton search,generate,book,back;
    private JTextField src,des,fno,pass,total,card;
    public BookTickets(){
        super("BookTickets");
        setTitle("Ticket Booking");
        setSize(900,600);
        setLayout(null);
        con=Airline_Reservation_System.connectDb();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel top=new JLabel("Ticket Booking");
        top.setBounds(0,0,800,50);
        top.setFont(new Font("Verdana",Font.BOLD,25));
        add(top);
        JLabel label1= new JLabel("From");
        label1.setBounds(50,50,90,20);
        add(label1);
        src = new JTextField();
        src.setBounds(150,50,100,20);
        add(src);
        JLabel label2= new JLabel("To");
        label2.setBounds(400,50,100,20);
        add(label2);
        des = new JTextField();
        des.setBounds(500,50,100,20);
        add(des);
        search = new JButton("Search");
        search.setBounds(700,50,100,20);
        add(search);
        search.addActionListener(this);
        label3= new JLabel("Flight no");
        label3.setBounds(50,100,100,20);
        add(label3);
        label3.setVisible(false);
        fno = new JTextField();
        fno.setBounds(150,100,100,20);
        add(fno);
        fno.setVisible(false);
        label4= new JLabel("Passport");
        label4.setBounds(50,150,100,20);
        add(label4);
        label4.setVisible(false);
        pass = new JTextField();
        pass.setBounds(150,150,100,20);
        add(pass);
        pass.setVisible(false);
        label5 = new JLabel("Economy Seats");
        label5.setBounds(50,200,100,20);
        add(label5);
        label5.setVisible(false);
        eco= new JComboBox(seats);
        eco.setBounds(150,200,100,20);
        add(eco);
        eco.setVisible(false);
        label6=new JLabel("Business Seats");
        label6.setBounds(50,250,100,20);
        add(label6);
        label6.setVisible(false);
        bus= new JComboBox(seats);
        bus.setBounds(150,250,100,20);
        add(bus);
        bus.setVisible(false);
        generate = new JButton("View Bill");
        generate.setBounds(100,270,100,20);
        add(generate);
        generate.addActionListener(this);
        generate.setVisible(false);
        label7=new JLabel("Total");
        label7.setBounds(400,150,80,20);
        add(label7);
        label7.setVisible(false);
        total = new JTextField();
        total.setBounds(500,150,100,20);
        total.setEditable(false);
        add(total);
        total.setVisible(false);
        label8 = new JLabel("Cardno");
        label8.setBounds(400,200,80,20);
        add(label8);
        label8.setVisible(false);
        card = new JTextField();
        card.setBounds(500,200,100,20);
        add(card);
        card.setVisible(false);
        book = new JButton("Book Tickets");
        book.setBounds(400,250,150,30);
        add(book);
        book.addActionListener(this);
        book.setVisible(false);
        back = new JButton("Back");
        back.setBounds(700,100,100,20);
        add(back);
        back.addActionListener(this);
        label9= new JLabel("Flight no");
        label9.setBounds(0,310,80,20);
        //label3.setFont(new Font("Verdana",Font.BOLD,15));
        add(label9);
        label9.setVisible(false);
        label10 = new JLabel("Flight name");
        label10.setBounds(90,310,80,20);
        add(label10);
        label10.setVisible(false);
        label11= new JLabel("From");
        label11.setBounds(180,310,80,20);
        add(label11);
        label11.setVisible(false);
        label12 =new JLabel("To");
        label12.setBounds(270,310,80,20);
        add(label12);
        label12.setVisible(false);
        label13 = new JLabel("Date");
        label13.setBounds(360,310,80,20);
        add(label13);
        label13.setVisible(false);
        label14 = new JLabel("Time");
        label14.setBounds(450,310,80,20);
        add(label14);
        label14.setVisible(false);
        label15 = new JLabel("EconomySeats");
        label15.setBounds(540,310,100,20);
        add(label15);
        label15.setVisible(false);
        label16= new JLabel("BusinessSeats");
        label16.setBounds(630,310,100,20);
        add(label16);
        label16.setVisible(false);
        label17= new JLabel("EcoPrice");
        label17.setBounds(720,310,80,20);
        add(label17);
        label17.setVisible(false);
        label18 = new JLabel("BusinessPrice");
        label18.setBounds(800,310,80,20);
        add(label18);
        label18.setVisible(false);
        table = new JTable();
        table.setBounds(0,330,900,300);
        add(table);
        table.setOpaque(false);
        //table.setEditable(false);
        table.setDefaultEditor(Object.class, null);
        table.setBackground(white);
        
    }
    int economyseatsupdated,businessseatsupdated;
     @Override
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==book){
            String flightno=fno.getText();
            String depdate="";
            String deptime="";
            if(card.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter Credit card no for Payment");
            }else{
                String sql2="select * from flight where flightno='"+flightno+"'";
                try{
                    PreparedStatement pst=con.prepareStatement(sql2);
                    rs=pst.executeQuery();
                    
                    while(rs.next()){
                        depdate=rs.getString("date");
                        deptime=rs.getString("departuretime");
                        economyseatsupdated=(rs.getInt("economyseats")-eco.getSelectedIndex());
                        businessseatsupdated=(rs.getInt("businessseats")-bus.getSelectedIndex());
                    }
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(null, exc);
                }
                
                String tkt="FL00"+(int)(Math.random()*(8889)+1111);
                    String pnr="PN00"+(int)(Math.random()*(8889)+1111);
                String sql4="insert into ticket values('"+tkt+"','"+flightno+"','"+src.getText()+"','"+des.getText()+"','"+depdate+"','"+deptime+"','"+pass.getText()+"','"+eco.getSelectedIndex()+"','"+bus.getSelectedIndex()+"','"+total.getText()+"','"+pnr+"')";
                try{
                    PreparedStatement pst=con.prepareStatement(sql4);
                    //String tkt="FL00"+(int)(Math.random()*(8889)+1111);
                    ///String pnr="PN00"+(int)(Math.random()*(8889)+1111);
                    //pst.setString(1, tkt);
                    //pst.setString(2, flightno);
                    //pst.setString(3, src.getText());
                    //pst.setString(4, des.getText());
                    //pst.setString(5, pass.getText());
                    //pst.setInt(6, eco.getSelectedIndex());
                    //pst.setInt(7, bus.getSelectedIndex());
                    //pst.setString(8, total.getText());
                    //pst.setString(9, pnr);
                    boolean r=pst.execute();
                    if(r){
                        String sql3="update flight set economyseats='"+economyseatsupdated+"',businessseats='"+businessseatsupdated+"' where flightno='"+flightno+"'";
                        try{
                            PreparedStatement pst1=con.prepareStatement(sql3);
                            pst1.executeUpdate(sql3);
                            
                        }catch(Exception exc){
                            JOptionPane.showMessageDialog(null, exc);
                        }                                            
                    }
                    JOptionPane.showMessageDialog(null,"Your Booking is Successful\n Your ticket no. is "+tkt+" Your payment no is "+pnr);
                          
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(null, exc);
                }
                
                
                String sql3="update flight set economyseats='"+economyseatsupdated+"',businessseats='"+businessseatsupdated+"' where flightno='"+flightno+"'";
                try{
                    PreparedStatement pst=con.prepareStatement(sql3);
                    pst.executeUpdate(sql3);
                    con.close();
                    this.dispose();
                    UserHome uh= new UserHome();
                    uh.setVisible(true);  
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(null, exc);
                }
                
            }
        }
        if(ae.getSource()==generate){
            String flightno=fno.getText();
            int economy=eco.getSelectedIndex();
            int business=bus.getSelectedIndex();
            String sql1="select * from flight where flightno='"+flightno+"'";
            try{
                //System.out.println("works");
                PreparedStatement pst= con.prepareStatement(sql1);
                rs=pst.executeQuery();
                while(rs.next()){
                    int ecocost=rs.getInt("economyprice");
                    int buscost=rs.getInt("businessprice");
                    int totalcost=economy*ecocost + business*buscost;
                    total.setText(String.valueOf(totalcost));
                    total.setVisible(true);
                    label7.setVisible(true);
                    label8.setVisible(true);
                    card.setVisible(true);
                    book.setVisible(true);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        if(ae.getSource()==back){
            this.dispose();
            UserHome uh = new UserHome();
            uh.setVisible(true);
        }
        if(ae.getSource()==search){
            String sql="";
            String src1=src.getText();
            String des1=des.getText();
            
           if(! src.getText().isEmpty() & !des.getText().isEmpty()){
                sql="select * from flight where src = '"+src1+"' and des = '"+des1+"'";
            }else{
               JOptionPane.showMessageDialog(null,"Enter from and to ");
           }
                   label3.setVisible(true);
            label4.setVisible(true);
            label5.setVisible(true);
            label6.setVisible(true);
            fno.setVisible(true);
            pass.setVisible(true);
            eco.setVisible(true);
            bus.setVisible(true);
            generate.setVisible(true);
            label9.setVisible(true);
            label10.setVisible(true);
            label11.setVisible(true);
            label12.setVisible(true);
            label13.setVisible(true);
            label14.setVisible(true);
            label15.setVisible(true);
            label16.setVisible(true);
            label17.setVisible(true);
            label18.setVisible(true);         
                //System.out.println("Works");
                try{
                    PreparedStatement pst= con.prepareStatement(sql);
                    rs=pst.executeQuery();
                    while(rs.next()){
                    
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    table.setVisible(true);
                    }
                }
                   catch(SQLException exc){
                    JOptionPane.showMessageDialog(null, exc);
                }
        }
    }
}
