/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline_reservation_system;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Mubarak
 */
public class Login extends JFrame implements ActionListener{
   // private JPanel contentPane;
    
    private JTextField userid;
    private JPasswordField passwordField;
    String userId="";
    String pass="";
    private JButton login;
    private JButton register,adminlogin;
    Connection con;
    ResultSet rs;
    private JPanel panel1;
    PreparedStatement pst;
    public Login(){
        super("Login");
        con=Airline_Reservation_System.connectDb();
        setTitle("Customer Login");
        //setExtendedState(Frame.MAXIMIZED_BOTH);
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//setBounds(100, 100, 900, 600);
	//contentPane = new JPanel();
      //  contentPane.setSize(900,600);
	//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	//contentPane.setPreferredSize(new Dimension(900,600));
	//setContentPane(contentPane);
	setLayout(null);
        userid=new JTextField();
        userid.setBounds(250,80,120,20);
      // contentPane.add(userid);
        add(userid);
        passwordField=new  JPasswordField();
        
        passwordField.setBounds(250,120,120,20);
        // contentPane.add(passwordField);
        add(passwordField);
        JLabel label1=new JLabel("EmailID");
        label1.setBounds(150,80,54,20);
        //contentPane.add(label1);
        add(label1);
        JLabel label2 = new JLabel("Password");
	label2.setBounds(150, 120, 75, 20);
	//contentPane.add(label2);
        add(label2);
        login = new JButton("Login");
        login.addActionListener(this);
        login.setBounds(250, 150, 85, 21);
       // contentPane.add(login);
       add(login);
        //login.setFocusable(false);
        register=new JButton("Register");
        register.setBounds(250,180,85,20);
       // contentPane.add(register);
       add(register);
        //register.setFocuasble(false);
        register.addActionListener(this);
        adminlogin= new JButton("Admin Login");
        adminlogin.setBounds(400,180,200,20);
        //contentPane.add(adminlogin);
        add(adminlogin);
        adminlogin.addActionListener(this);
        JLabel name=new JLabel("Airline Reservation System");
        name.setBounds(100,0,800,100);
        name.setFont(new Font("Verdana",Font.BOLD,35));
        //contentPane.add(name);
        add(name);
        //panel1=new JPanel();
        ImageIcon sk=new ImageIcon(ClassLoader.getSystemResource("airline_reservation_system/images/sk1.jpg"));
        ImageIcon mbk=new ImageIcon(ClassLoader.getSystemResource("airline_reservation_system/images/mbk1.jpg"));
        ImageIcon paa1=new ImageIcon(ClassLoader.getSystemResource("airline_reservation_system/images/paa.jpg"));
        
        JLabel skimg=new JLabel(sk);
        skimg.setBounds(600,250,240,320);        
        add(skimg);
        JLabel siva=new JLabel("R.R. Sivakumaran");
        siva.setBounds(680,220,150,20);
        add(siva);
        //panel1.setBounds(0,200,900,400);
        //panel1.add(skimg);
        //panel1.setVisible(true);
        
        //skimg.setVisible(true);
        JLabel mbkimg=new JLabel(mbk);
        mbkimg.setBounds(285,250,280,280);
        add(mbkimg);
        JLabel mubarak=new JLabel("Shaik Mubarak");
        mubarak.setBounds(350,220,100,20);
        add(mubarak);
        JLabel paa1img = new JLabel(paa1);
        paa1img.setBounds(10,250,240,320);
        add(paa1img);
        JLabel paavan = new JLabel("Y. Paavan");
        paavan.setBounds(80,220,100,20);
        add(paavan);
    }
  
    @Override
           public void actionPerformed(ActionEvent e){
               if(e.getSource()==passwordField){
                // passwordField.setEchoChar('*');
            }
               if(e.getSource()==login){
                   if(userid.getText().isEmpty()|| passwordField.getText().isEmpty()){
                       JOptionPane.showMessageDialog(null,"Enter email id and password");
                   }else{
                       String id=userid.getText();
                  String pass=passwordField.getText();
                  
                  String sql="select * from account where password='"+pass+"' and email='"+id+"'";
                  try{
                      PreparedStatement ps= con.prepareStatement(sql);
                      //pst = con.prepareStatement(sql);
                      //ps.setString(2, userid.getText());
                      //ps.setString(1, passwordField.getText());
                      rs=ps.executeQuery(sql);
                      if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        
                        this.dispose();  
                        UserHome uh = new UserHome();
                        uh.setVisible(true); 
                        con.close();
                      }else{
                          JOptionPane.showMessageDialog(null,"Incorrect Email Id or Password");
                      }
                      
                  }catch(Exception exc){
                      JOptionPane.showMessageDialog(null, exc);
                        exc.printStackTrace();
                  }
                   }
                  
               }
               if(e.getSource()==register){
                   
                
                   this.dispose();
                   Register reg =new Register();
                   
                   reg.setVisible(true);
                   //setVisible(false);
                  // contentPane.dispose();
               }
               if(e.getSource()==adminlogin){
                   this.dispose();
                   AdminLogin al=new AdminLogin();
                   al.setVisible(true);
               }
           }
        
}
