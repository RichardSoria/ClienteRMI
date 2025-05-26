package rmi.cliente.test;

import rmi.cliente.ClienteUI;
import rmi.cliente.clase.Cliente;

import javax.swing.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {

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
        ClienteUI clienteUI = new ClienteUI();
        clienteUI.setVisible(true);
    }
}