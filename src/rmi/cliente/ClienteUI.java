package rmi.cliente;

import rmi.cliente.clase.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteUI extends JFrame{
    private JTextField campoID;
    private JButton buscarEmpleadoButton;
    private JPanel JPanel;

    public ClienteUI() {
        setContentPane(JPanel);
        buscarEmpleadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = campoID.getText();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
                    return;
                }
                try {
                    int empleadoId = Integer.parseInt(id);
                    String resultado = Cliente.consultar(empleadoId);
                    JOptionPane.showMessageDialog(null, "Empleado Encontrado:\n" + resultado);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID debe ser un número entero.");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
