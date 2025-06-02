package rmi.cliente.test;

import rmi.cliente.ClienteUI;
import rmi.cliente.clase.Cliente;

import javax.swing.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        ClienteUI clienteUI = new ClienteUI();

        clienteUI.setTitle("Gestionar Empleados");
        clienteUI.setSize(800, 700);
        clienteUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clienteUI.setResizable(false);
        clienteUI.setLocationRelativeTo(null);
        clienteUI.setVisible(true);

    }
}