package rmi.cliente.test;

import rmi.cliente.ClienteUI;
import rmi.cliente.clase.Cliente;

import javax.swing.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        ClienteUI clienteUI = new ClienteUI();

        clienteUI.setTitle("Consultar Empleados");
        clienteUI.setSize(400, 200);
        clienteUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clienteUI.setResizable(false);
        clienteUI.setLocationRelativeTo(null);
        clienteUI.setVisible(true);

        /*String op = null;
        Scanner scanner = null;
        do {
            scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del empleado a consultar: ");
            int id = scanner.nextInt();
            String resultado = Cliente.consultar(id);
            System.out.println("Resultado: " + resultado);
            System.out.println("¿Desea realizar otra consulta? (S/N): ");
            op = scanner.next();
        } while (op.equalsIgnoreCase("S"));
        System.out.println("Fin del programa.");
        scanner.close();*/
    }
}