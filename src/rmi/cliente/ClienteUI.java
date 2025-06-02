package rmi.cliente;

import rmi.cliente.clase.Cliente;
import rmi.servidor.clase.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClienteUI extends JFrame {
    private JTextField campoID;
    private JButton buscarEmpleadoButton;
    private JButton btnAgregar, btnActualizar, btnEliminar;
    private JTable tablaEmpleados;
    private JPanel JPanel;
    private JTextField campoNombre;
    private JTextField campoCorreo;
    private JTextField campoCargo;
    private JTextField campoSueldo;

    public ClienteUI() {
        setContentPane(JPanel);

        inicializarTabla();

        buscarEmpleadoButton.addActionListener(e -> consultarEmpleado());
        btnAgregar.addActionListener(e -> agregarEmpleado());
        btnActualizar.addActionListener(e -> actualizarEmpleado());
        btnEliminar.addActionListener(e -> eliminarEmpleado());

        listarEmpleados();
    }

    private void inicializarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(null,
                new String[]{"ID", "Nombre", "Correo", "Cargo", "Sueldo"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que las celdas sean editables
            }
        };
        tablaEmpleados.setModel(modelo);

        tablaEmpleados.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID pequeño
        tablaEmpleados.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre ancho
        tablaEmpleados.setRowHeight(25);
        tablaEmpleados.setAutoCreateRowSorter(true); // Habilita ordenamiento

        // Listener para llenar campos al seleccionar fila
        tablaEmpleados.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = tablaEmpleados.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    int filaModelo = tablaEmpleados.convertRowIndexToModel(filaSeleccionada);
                    DefaultTableModel modeloTabla = (DefaultTableModel) tablaEmpleados.getModel();

                    campoID.setText(modeloTabla.getValueAt(filaModelo, 0).toString());
                    campoNombre.setText(modeloTabla.getValueAt(filaModelo, 1).toString());
                    campoCorreo.setText(modeloTabla.getValueAt(filaModelo, 2).toString());
                    campoCargo.setText(modeloTabla.getValueAt(filaModelo, 3).toString());
                    campoSueldo.setText(modeloTabla.getValueAt(filaModelo, 4).toString());
                }
            }
        });
    }

    private void listarEmpleados() {
        try {
            List<Persona> lista = Cliente.listar();
            DefaultTableModel modelo = (DefaultTableModel) tablaEmpleados.getModel();
            modelo.setRowCount(0); // limpiar tabla

            for (Persona p : lista) {
                modelo.addRow(new Object[]{
                        p.getId(),
                        p.getNombre(),
                        p.getCorreo(),
                        p.getCargo(),
                        p.getSueldo()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al listar empleados: " + ex.getMessage());
        }
    }

    private void consultarEmpleado() {
        String idText = campoID.getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.");
            return;
        }
        try {
            int id = Integer.parseInt(idText);
            String resultado = Cliente.consultar(id);
            JOptionPane.showMessageDialog(this, "Resultado de la consulta:\n" + resultado);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID debe ser un número entero.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en consulta: " + ex.getMessage());
        }
    }

    private Persona crearPersonaDesdeFormulario() {
        String nombre = campoNombre.getText();
        String correo = campoCorreo.getText();
        String cargo = campoCargo.getText();
        String sueldoTexto = campoSueldo.getText();

        if (nombre.isEmpty() || correo.isEmpty() || cargo.isEmpty() || sueldoTexto.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos deben estar completos.");
        }

        double sueldo;
        try {
            sueldo = Double.parseDouble(sueldoTexto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El sueldo debe ser un número válido.");
        }

        int id = 0;

        return new Persona(id, nombre, correo, cargo, sueldo);
    }

    private void agregarEmpleado() {
        try {
            Persona p = crearPersonaDesdeFormulario();
            boolean exito = Cliente.agregar(p);
            JOptionPane.showMessageDialog(this, exito ? "Empleado agregado exitosamente" : "Error al agregar empleado");
            listarEmpleados();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar empleado: " + ex.getMessage());
        }
    }

    private void actualizarEmpleado() {
        try {
            // El ID ya está cargado en campoID (tras seleccionar en tabla)
            // Ya no se requiere que el usuario ingrese nada nuevo
            if (campoID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla para actualizar.");
                return;
            }
            int id = Integer.parseInt(campoID.getText());
            Persona p = crearPersonaDesdeFormulario();
            boolean exito = Cliente.actualizar(id, p);
            JOptionPane.showMessageDialog(this, exito ? "Empleado actualizado exitosamente" : "Error al actualizar empleado");
            listarEmpleados();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar empleado: " + ex.getMessage());
        }
    }

    private void eliminarEmpleado() {
        try {
            if (campoID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla para eliminar.");
                return;
            }
            int id = Integer.parseInt(campoID.getText());
            boolean exito = Cliente.eliminar(id);
            JOptionPane.showMessageDialog(this, exito ? "Empleado eliminado exitosamente" : "Error al eliminar empleado");
            listarEmpleados();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar empleado: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        campoID.setText("");
        campoNombre.setText("");
        campoCorreo.setText("");
        campoCargo.setText("");
        campoSueldo.setText("");
    }
}
