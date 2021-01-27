package passage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeePassage extends JFrame implements ActionListener{
  JButton submit;
  JTextField nameText;
    JPasswordField passwordText;
  JLabel nameLabel,passwordLabel;
  String name;
  String password;
  public EmployeePassage(){
 JFrame f = new JFrame();
 setLayout(null);

   nameLabel = new JLabel("Name:");
   passwordLabel = new JLabel("Password:");

   nameText = new JTextField(20);
   passwordText = new JPasswordField(20);
   passwordText.setEchoChar('*');
   submit = new JButton("Submit");
   nameLabel.setBounds(100,200,200,25);
   nameText.setBounds(180,200,200,25);
   passwordLabel.setBounds(100,250,200,25);
   passwordText.setBounds(180,250,200,25);
   submit.setBounds(150,300,200,25);
   add(nameLabel);
   add(nameText);

   add(passwordLabel);
   add(passwordText);

   add(submit);
   submit.addActionListener(this);
   
   setSize(500,400);
   setTitle("Employee Access:");
   setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent click){
    name = nameText.getText();
    password = passwordText.getText();
    check();
  }
  
        public void check(){
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection c = DriverManager.getConnection("jdbc:mysql://localhost/superstore","root","");
          Statement s = c.createStatement();
          ResultSet employeeAccess = s.executeQuery("Select * from employeeAccess");

          while(employeeAccess.next()){
              String checkName = employeeAccess.getString(1);
              String checkPassword = employeeAccess.getString(2);
              if(name.equals(checkName)){
                  if(password.equals(checkPassword) ){
                      System.out.println("Right");
                      EmployeePage EP = new EmployeePage();
                  }
                  else{
                      System.out.println("Wrong");
                  }
              }
          }
          
          c.close();
          
      }
      
      catch(Exception e){
          nameText.setText("Exception");
      }
  }
  


}
