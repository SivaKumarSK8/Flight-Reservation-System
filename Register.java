/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline_reservation_system;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Mubarak
 */
public class Register extends JFrame implements ActionListener{
   private  JTextField nameField,emailField,mobileField;
    private JButton regbtn,back,clear;
    private JPasswordField passwordField,confirmpasswordField;
    
   // private JPanel contentPane;
   Connection con;
   ResultSet rs;
   PreparedStatement pst;
    public Register(){
        super("Register");
        setTitle("Register");
        setSize(900,600);
        con=Airline_Reservation_System.connectDb();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel top=new JLabel("Register Page");
        top.setBounds(100,0,800,100);
        top.setFont(new Font("Verdana",Font.BOLD,20));
        add(top);
       // contentPane= new JPanel();
        //contentPane.setSize(900,600);
        //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	//contentPane.setPreferredSize(new Dimension(900,600));
	//setContentPane(contentPane);
	setLayout(null);
        //contentPane.setVisible(true);
        JLabel name= new JLabel("Name");
        name.setBounds(100,100,80,20);
        add(name);
        nameField = new JTextField();
        nameField.setBounds(300,100,150,20);
        nameField.setVisible(true);
        add(nameField);
        JLabel label2= new JLabel("Email ID");
        label2.setBounds(100,150,150,20);
        add(label2);
        emailField = new JTextField();
        emailField.setBounds(300,150,150,20);
        add(emailField);
        JLabel label3 = new JLabel("Mobile Number");
        label3.setBounds(100,200,150,20);
        add(label3);
        mobileField = new JTextField();
        mobileField.setBounds(300,200,150,20);
        add(mobileField);
        JLabel label4 = new JLabel("Password");
        label4.setBounds(100,250,150,20);
        add(label4);
        passwordField = new JPasswordField();
        passwordField.setBounds(300,250,150,20);
        add(passwordField);
        JLabel label5 = new JLabel("Confirm Password");
        label5.setBounds(100,300,150,20);
        add(label5);
        confirmpasswordField= new JPasswordField();
        confirmpasswordField.setBounds(300,300,150,20);
        add(confirmpasswordField);
        regbtn= new JButton("Register");
        regbtn.setBounds(300,350,150,20);
        add(regbtn);
        regbtn.addActionListener(this);
        back = new JButton("Back");
        back.setBounds(100,350,80,20);
        add(back);
        back.addActionListener(this);
        clear = new JButton("Clear");
        clear.setBounds(200,350,80,20);
        add(clear);
        clear.addActionListener(this);
               
    }
    
    
   @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==regbtn){
                if(passwordField.getText().isEmpty() || confirmpasswordField.getText().isEmpty()||nameField.getText().isEmpty()||emailField.getText().isEmpty()||mobileField.getText().isEmpty()){
                  JOptionPane.showMessageDialog(null,"Enter All the Fields ");
              }
              if(passwordField.getText().equals(confirmpasswordField.getText())){
                    
                    String sql="insert into account(email,name,mobile,password) values(?,?,?,?)";
                    try{
                        pst=con.prepareStatement(sql);
                        pst.setString(1, emailField.getText());
                        pst.setString(2, nameField.getText());
                        pst.setString(3, mobileField.getText());
                        pst.setString(4, passwordField.getText());
                        pst.execute();
                        JOptionPane.showMessageDialog(null,"Registration Successful");
                        con.close();
                        this.dispose();
                        Login lgn= new Login();
                        lgn.setVisible(true);
                           
                        
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(null, exc);
                        exc.printStackTrace();
                    }
              }else{
                  JOptionPane.showMessageDialog(null,"Password and confirm password must be same");
              }
              
            }
            if(e.getSource()==back){
                this.dispose();
                Login lgn= new Login();
                lgn.setVisible(true);
            }
            if(e.getSource()==clear){
                nameField.setText("");
                emailField.setText("");
                mobileField.setText("");
                passwordField.setText("");
                confirmpasswordField.setText("");
            }
        }
}
