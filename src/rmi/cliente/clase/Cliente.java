package rmi.cliente.clase;

import rmi.servidor.clase.Persona;
import rmi.servidor.clase.Servidor;

import java.rmi.Naming;
import java.util.List;

public class Cliente {
    private static final String RMI_OBJECT_NAME = "rmi://localhost/Datos";

    private static Servidor getServidor() throws Exception {
        return (Servidor) Naming.lookup(RMI_OBJECT_NAME);
    }

    public static String consultar(int id) throws Exception {
        return getServidor().consultar(id);
    }

    public static List<Persona> listar() throws Exception {
        return getServidor().listar();
    }

    public static boolean agregar(Persona p) throws Exception {
        return getServidor().agregar(p);
    }

    public static boolean actualizar(int id, Persona p) throws Exception {
        return getServidor().actualizar(id, p);
    }

    public static boolean eliminar(int id) throws Exception {
        return getServidor().eliminar(id);
    }
}
