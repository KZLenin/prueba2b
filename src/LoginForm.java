import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm {
    private JTextField txtUsername;
    private JPasswordField pswPassw;
    public JPanel loginPanel;
    private JLabel Login;
    private JButton btnLogin;

    public LoginForm() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String passwrd = pswPassw.getText();

                String url = "jdbc:mysql://localhost:3306/prueba2b";
                String user = "root";
                String password = "123456";

                Connection con = null;
                ResultSet rs = null;

                try {
                    con = DriverManager.getConnection(url, user, password);
                    Statement st = con.createStatement();
                    String sql = "select * from usuarios";
                    rs = st.executeQuery(sql);
                    String bd_Username;
                    String bd_Password;
                    while (rs.next()) {
                        bd_Username = rs.getString("username");
                        bd_Password = rs.getString("password");

                        if (bd_Username.equals(username) && bd_Password.equals(passwrd)) {
                            JFrame frame1 = new JFrame("Calificaciones");
                            frame1.setSize(1000,400);
                            frame1.setContentPane(new CalificacionForm().calificacionPanel);
                            frame1.pack();
                            frame1.setVisible(true);
                            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            break;
                        } else {JOptionPane.showMessageDialog(null, "Usuario no encontrado"); break;}
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            });
    }
}
