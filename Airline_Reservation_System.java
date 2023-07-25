/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline_reservation_system;

import java.awt.EventQueue;
import java.sql.*;

/**
 *
 * @author Mubarak
 */
public class Airline_Reservation_System {

    /**
     * @param args the command line arguments
     */
    Connection con=null;
    public static Connection connectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "Mysqlpassword@Mubarakmbk1");
            return con;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
		try {
                    Login frame = new Login();
                    frame.setVisible(true);
                    
		} catch (Exception e) {
                    e.printStackTrace();
		}
	}
		});
    }
    
}
