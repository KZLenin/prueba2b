import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalificacionForm {
    public JPanel calificacionPanel;
    private JTextField txtCedula;
    private JTextField txtName;
    private JTextField txtNota5;
    private JTextField txtNota4;
    private JTextField txtNota3;
    private JTextField txtNota2;
    private JTextField txtNota1;
    private JButton guardarButton;
    private JPanel txtN;
    private JLabel labelProm;
    private JLabel labelAprov;

    public CalificacionForm() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double nota1= Double.valueOf(txtNota1.getText());
                Double nota2= Double.valueOf(txtNota2.getText());
                Double nota3= Double.valueOf(txtNota3.getText());
                Double nota4= Double.valueOf(txtNota4.getText());
                Double nota5= Double.valueOf(txtNota5.getText());

                String url="jdbc:mysql://localhost:3306/prueba2b";
                String user="root";
                String password="123456";

                Connection con=null;
                PreparedStatement ps = null;

                if ((nota1>=0&&nota1<=20)&&(nota2>=0&&nota2<=20)&&(nota3>=0&&nota3<=20)&&(nota4>=0&&nota4<=20)&&(nota5>=0&&nota5<=20)){
                    try {
                        con = DriverManager.getConnection(url,user,password);
                        String sql = "insert into estudiantes(cedula,nombre, estudiante1, estudiante2, estudiante3, estudiante4, estudiante5) values (?,?,?,?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, txtCedula.getText());
                        ps.setString(2, txtName.getText());
                        ps.setDouble(3, nota1);
                        ps.setDouble(4, nota2);
                        ps.setDouble(5, nota3);
                        ps.setDouble(6, nota4);
                        ps.setDouble(7, nota5);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
                        Double prom = (nota1+nota2+nota3+nota4+nota5)/5;
                        labelProm.setText(String.valueOf(prom));
                        if(prom>= 60){
                            labelAprov.setText("Aprovado");
                        }  else {labelAprov.setText("Reprovado");}
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {JOptionPane.showMessageDialog(null, "Las notas deben estar completas y entre 0 y 20 ");}


            }
        });
    }
}
