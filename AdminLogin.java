/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline_reservation_system;

//import com.sun.jdi.connect.spi.Connection;
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
public class AdminLogin extends JFrame implements ActionListener {
  private JButton loginbtn, back;
   private JTextField id;
   private JPasswordField password;
   Connection con;
   ResultSet rs;
   PreparedStatement pst;  
    public AdminLogin(){
        super("AdminLogin");
        setTitle("Admin Login");
       setSize(900,600);
       setLayout(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       con=Airline_Reservation_System.connectDb();
       JLabel name= new JLabel("AdminLogin");
       name.setBounds(100,0,300,100);
       name.setFont(new Font("Verdana",Font.BOLD,20));
       add(name);
       id= new JTextField();
       id.setBounds(200,100,150,20);
       add(id);
       JLabel Id = new JLabel("Id");
       Id.setBounds(100,100,150,20);
       add(Id);
       JLabel label2=new JLabel("Password");
       label2.setBounds(100,150,150,20);
       add(label2);
       password = new JPasswordField();
       password.setBounds(200, 150, 150, 20);
       add(password);
       back = new JButton("Back");
       back.setBounds(100,200,80,20);
       add(back);
       loginbtn = new JButton("Login");
       loginbtn.setBounds(200,200,100,20);
       add(loginbtn);
       back.addActionListener(this);
       loginbtn.addActionListener(this);
    }
    @Override
   public void actionPerformed(ActionEvent ae){
       if(ae.getSource()==back){
           this.dispose();
           Login lgn= new Login();
           lgn.setVisible(true);
       }
       if(ae.getSource()==loginbtn){
           String Id=id.getText();
                  String pass=password.getText();
                  
                  String sql="select * from admin where password='"+pass+"' and id='"+Id+"'";
                  try{
                      PreparedStatement ps= con.prepareStatement(sql);
                      //pst = con.prepareStatement(sql);
                      //ps.setString(2, userid.getText());
                      //ps.setString(1, passwordField.getText());
                      rs=ps.executeQuery(sql);
                      if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        
                        this.dispose();  
                        AdminHome ah = new AdminHome();
                        ah.setVisible(true); 
                        con.close();
                      }else{
                          JOptionPane.showMessageDialog(null,"Incorrect Credentials");
                          System.out.println(rs);
                          
                      }
                      
                  }catch(Exception exc){
                      JOptionPane.showMessageDialog(null, exc);
                        exc.printStackTrace();
                  }
               }
              
   }
}
