/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline_reservation_system;
import static java.awt.Color.white;
import java.awt.Font;       
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mubarak
 */
public class FlightStatus extends JFrame implements ActionListener {
    Connection con;
   ResultSet rs;
   
   private JTable table;
   private JTextField from,to;
   private JButton view,back;
   private DefaultTableModel model;
    public FlightStatus(){
        super("FlightStatus");
        setTitle("Flight Status");
        setSize(900,600);
        setLayout(null);
        setVisible(true);
        //table = new JTable();
        con=Airline_Reservation_System.connectDb();
        //model = new DefaultTableModel(new String[]{"Flightno", "Flightname", "From","To","Date","Time","EconomySeats","BusinessClassSeats","EconomyPrice","BusinessClassPrice"}, 0);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel top=new JLabel("Flight Status");
        top.setBounds(100,0,800,100);
        top.setFont(new Font("Verdana",Font.BOLD,25));
        add(top);
        JLabel label1=new JLabel("From");
        label1.setBounds(50,100,100,20);
        add(label1);
        from = new JTextField();
        from.setBounds(50,150,100,20);
        add(from);
        JLabel label2= new JLabel("To");
        label2.setBounds(300,100,100,20);
        add(label2);
        to = new JTextField();
        to.setBounds(300,150,100,20);
        add(to);
        back=new JButton("Back");
        back.setBounds(50,200,80,20);
        add(back);
        back.addActionListener(this);
        view = new JButton("View FLights");
        view.setBounds(150,200,150,20);
        add(view);
        view.addActionListener(this);
        table = new JTable();
        table.setBounds(0,250,900,300);
        add(table);
        JLabel label3= new JLabel("Flight no");
        label3.setBounds(0,230,80,20);
        //label3.setFont(new Font("Verdana",Font.BOLD,15));
        add(label3);
        JLabel label4 = new JLabel("Flight name");
        label4.setBounds(90,230,80,20);
        add(label4);
        JLabel label5= new JLabel("From");
        label5.setBounds(180,230,80,20);
        add(label5);
        JLabel label6 =new JLabel("To");
        label6.setBounds(270,230,80,20);
        add(label6);
        JLabel label7 = new JLabel("Date");
        label7.setBounds(360,230,80,20);
        add(label7);
        JLabel label8 = new JLabel("Time");
        label8.setBounds(450,230,80,20);
        add(label8);
        JLabel label9 = new JLabel("EconomySeats");
        label9.setBounds(540,230,100,20);
        add(label9);
        JLabel label10= new JLabel("BusinessSeats");
        label10.setBounds(630,230,100,20);
        add(label10);
        JLabel label11= new JLabel("EcoPrice");
        label11.setBounds(720,230,80,20);
        add(label11);
        JLabel label12 = new JLabel("BusinessPrice");
        label12.setBounds(800,230,80,20);
        add(label12);
        table.setOpaque(false);
        //table.setEditable(false);
        table.setDefaultEditor(Object.class, null);
        table.setBackground(white);
    }
    
    @Override
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            this.dispose();
            UserHome uh=new UserHome();
            uh.setVisible(true);
        }
        if(ae.getSource()==view){
            String sql;
            String src=from.getText();
            String des=to.getText();
            sql="select * from flight where src = '"+src+"' and des = '"+des+"'";
           if(from.getText().isEmpty() & to.getText().isEmpty()){
               sql="select * from flight";
            }else if(from.getText().isEmpty()){
               sql="select * from flight where des= '"+des+"'";
            }else if(to.getText().isEmpty()){
                sql="select * from flight where src = '"+src+"'";
            }
                            
                //System.out.println("Works");
                try{
                    PreparedStatement pst= con.prepareStatement(sql);
                    rs=pst.executeQuery();
                    if(rs.next()){
                    while(rs.next()){
                    
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    table.setVisible(true);
                    }
                    }else{
                        JOptionPane.showMessageDialog(null,"No Flights available");
                    }
                }
                   catch(SQLException exc){
                    JOptionPane.showMessageDialog(null, exc);
                }
            }
        }
}
