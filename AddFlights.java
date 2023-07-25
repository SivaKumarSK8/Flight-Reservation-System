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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Mubarak
 */
public class AddFlights extends JFrame implements ActionListener {
    private JTextField fname,fno,src,des,deptime,economy,business,ecoprice,busprice;
    private JButton back,clear,af;
    Connection con;
   ResultSet rs;
   private JTextField date;
    private DateFormat dateformat;
    public AddFlights(){
        super("AddFlights");
        setTitle("Add Flights");
        setSize(900,600);
        setLayout(null);
        con=Airline_Reservation_System.connectDb();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel top=new JLabel("Add Flights");
        top.setBounds(100,0,800,100);
        top.setFont(new Font("Verdana",Font.BOLD,25));
        add(top);
        JLabel label1=new JLabel("Flight no");
        label1.setBounds(50,100,150,20);
        add(label1);
        fno=new JTextField();
        fno.setBounds(200,100,150,20);
        add(fno);
        JLabel label2=new JLabel("Flight name");
        label2.setBounds(50,200,150,20);
        add(label2);
        fname=new JTextField();
        fname.setBounds(200,200,150,20);
        add(fname);
        JLabel label3=new JLabel("From");
        label3.setBounds(50,300,150,20);
        add(label3);
        src=new JTextField();
        src.setBounds(200,300,150,20);
        add(src);
        JLabel label4=new JLabel("To");
        label4.setBounds(50,400,150,20);
        add(label4);
        des = new JTextField();
        des.setBounds(200,400,150,20);
        add(des);
        JLabel dt=new JLabel("Date");
        dt.setBounds(50,450,150,20);
        add(dt);
        //dateformat=new SimpleDateFormat("dd MM YYYY");
        date=new JTextField();
        date.setBounds(200,450,150,20);
        add(date);
        JLabel label5=new JLabel("Departure time");
        label5.setBounds(50,500,150,20);
        add(label5);
        deptime=new JTextField();
        deptime.setBounds(200,500,150,20);
        add(deptime);
        JLabel label6= new JLabel("Economy Seats");
        label6.setBounds(500,100,150,20);
        add(label6);
        economy=new JTextField();
        economy.setBounds(700,100,150,20);
        add(economy);
        JLabel label7=new JLabel("Business Class Seats");
        label7.setBounds(500,200,150,20);
        add(label7);
        business=new JTextField();
        business.setBounds(700,200,150,20);
        add(business);
        JLabel label8 = new JLabel("Economy Price");
        label8.setBounds(500,300,150,20);
        add(label8);
        ecoprice = new JTextField();
        ecoprice.setBounds(700,300,150,20);
        add(ecoprice);
        JLabel label9= new JLabel("Business Class Price");
        label9.setBounds(500,400,150,20);
        add(label9);
        busprice=new JTextField();
        busprice.setBounds(700,400,150,20);
        add(busprice);
        back=new JButton("Back");
        back.setBounds(500,500,100,20);
        add(back);
        back.addActionListener(this);
        clear = new JButton("Clear");
        clear.setBounds(600,500,100,20);
        add(clear);
        clear.addActionListener(this);
        af=new JButton("Add Flight");
        af.setBounds(700,500,150,20);
        add(af);
        af.addActionListener(this);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==clear){
            fno.setText("");
            fname.setText("");
            src.setText("");
            des.setText("");
            date.setText("");
            deptime.setText("");
            economy.setText("");
            business.setText("");
            ecoprice.setText("");
            busprice.setText("");
        }
        if(ae.getSource()==back){
            this.dispose();
            AdminHome ah=new AdminHome();
            ah.setVisible(true);
        }
        if(ae.getSource()==af){
            if(fno.getText().isEmpty()||fname.getText().isEmpty()||src.getText().isEmpty()||des.getText().isEmpty()||date.getText().isEmpty()||deptime.getText().isEmpty()||economy.getText().isEmpty()||business.getText().isEmpty()||ecoprice.getText().isEmpty()||busprice.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter all the fields");
            }
            else{
            String sql="insert into flight values('"+fno.getText()+"','"+fname.getText()+"','"+src.getText()+"','"+des.getText()+"','"+date.getText()+"','"+deptime.getText()+"','"+Integer.parseInt(economy.getText())+"','"+Integer.parseInt(business.getText())+"','"+Integer.parseInt(ecoprice.getText())+"','"+Integer.parseInt(busprice.getText())+"')";
            try{
                 PreparedStatement pst= con.prepareStatement(sql);
                        /*pst.setString(1, fno.getText());
                        pst.setString(2, fname.getText());
                        pst.setString(3, src.getText());
                        pst.setString(4, des.getText());
                        pst.setString(5, deptime.getText());
                        pst.setInt(6, Integer.parseInt(economy.getText()));
                        pst.setInt(7, Integer.parseInt(business.getText()));
                        pst.setInt(8, Integer.parseInt(ecoprice.getText()));
                        pst.setInt(9, Integer.parseInt(busprice.getText()));*/
                        pst.execute();
                        JOptionPane.showMessageDialog(null,"Flight Added Successfully");
                        con.close();
                        this.dispose();
                        AdminHome ah= new AdminHome();
                        ah.setVisible(true);
            }catch(Exception exc){
                JOptionPane.showMessageDialog(null, exc);
                        exc.printStackTrace();
            }
            }
        }
    }
}
