package com.svalero.phonostore;

import com.svalero.phonostore.dao.*;
import com.svalero.phonostore.domain.*;
import com.svalero.phonostore.exception.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.svalero.phonostore.util.ValidaDni.validarDNI;

public class Menu {
    private Scanner teclado;
    private Database database;
    private Connection connection;
    private Usuario usuarioActual;

    public Menu() {
        teclado = new Scanner(System.in);
    }

    public void muestraMenu() {
        connect();
        login();

        System.out.println("Bienvenido " + usuarioActual.getNombre());
        if (usuarioActual.getRol().equals("USER")) {
            menuUsuario();
        } else {
            menuAdmin();
        }
    }

    private void login() {
        System.out.println("Inicia sesion para continuar:");
        System.out.print("Nombre de usuario: ");
        String nombre = teclado.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contraseña = teclado.nextLine();

        UsuarioDao usuarioDao = new UsuarioDao(connection);
        try {
            usuarioActual = usuarioDao.getUsuario(nombre, contraseña).orElseThrow(UsuarioNoEncontrado::new);
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            System.exit(0);
        } catch (UsuarioNoEncontrado une) {
            System.out.println(une.getMessage());
            System.exit(0);
        }
        System.out.println("\n Login correcto! \n");
    }

    private void menuAdmin() {
        String opcion;
        do {
            System.out.println("Consola de Phono Store");
            System.out.println("Por favor, elige una opcion:");
            System.out.println("1. Añadir un movil");
            System.out.println("2. Ver todos los moviles");
            System.out.println("3. Añadir una venta");
            System.out.println("4. Buscar una venta");
            System.out.println("5. Ver todas las ventas");
            System.out.println("6. Modificar un movil");
            System.out.println("7. Buscar moviles");
            System.out.println("8. Eliminar movil");
            System.out.println("0. Salir");
            System.out.print("Opcion elegida: ");
            opcion = teclado.nextLine();
            System.out.println("");

            switch (opcion) {
                case "1":
                    altaMovil();
                    break;
                case "2":
                    verMoviles();
                    break;
                case "3":
                    altaVenta();
                    break;
                case "4":
                    buscaVenta();
                    break;
                case "5":
                    verVentasEnDetalle();
                    break;
                case "6":
                    modificaMovil();
                    break;
                case "7":
                    buscarMovil();
                    break;
                case "8":
                    eliminarMovil();
                    break;
                case "0":
                    close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion elegida incorrecta");
                    break;
            }
        } while (!opcion.equals("0"));
    }

    private void eliminarMovil() {
        MovilDao movilDao = new MovilDao(connection);
        Movil movil;

        try {
            ArrayList<Movil> moviles;

            System.out.println("Selecciona el movil a eliminar: ");
            moviles = movilDao.findAll();

            int indice = 1;
            for (Movil aux : moviles) {
                System.out.println(indice + ". " + aux.toString());
                indice++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            Integer indiceMovil = Integer.parseInt(opcion);
            movil = moviles.get(indiceMovil - 1);
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            return;
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Lo que has introducido no es un numero");
            return;
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }

        try {
            System.out.println("Introduce la marca (Actual: " + movil.getMarca() + "):");
            movil.setMarca(teclado.nextLine());
            compruebaVacio(movil.getMarca());
            System.out.println("Introduce el modelo (Actual: " + movil.getModelo() + "):");
            movil.setModelo(teclado.nextLine());
            compruebaVacio(movil.getModelo());
            System.out.println("Introduce el color (Actual: " + movil.getColor() + "):");
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            return;
        }

        Integer precio;
        String color;
        try {
            color = (teclado.nextLine());
            System.out.println("Introduce el precio base (Actual: " + movil.getPrecioBase() + "):");
            precio = Integer.parseInt(teclado.nextLine());
            movil.setColor(color);
            movil.setPrecioBase(precio);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            System.out.println("Error: Movil no modificado");
            return;
        }

        try {
            boolean correcto = movilDao.eliminarMovil(String.valueOf(movil));
            if (correcto) {
                System.out.println("\n El movil se ha modificado correctamente! \n");
            } else {
                System.out.println("\n El movil no se ha podido modificar \n");
            }

        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            return;
        }

    }


    private void menuUsuario() {
        String opcion;
        do {
            System.out.println("Bienvenido a Phono Store");
            System.out.println("Por favor, elige una opcion:");
            System.out.println("1. Ver mis compras");
            System.out.println("2. Ver mis compras en detalle");
            System.out.println("3. Buscar movil por marca");
            System.out.println("4. Zona privada");
            System.out.println("0. Salir");
            System.out.print("Opcion elegida: ");
            opcion = teclado.nextLine();
            System.out.println("");

            switch (opcion) {
                case "1":
                    verVentaCliente();
                    break;
                case "2":
                    verVentasEnDetalle();
                    break;
                case "3":
                    buscarMovil();
                    break;
                case "4":
                    zonaPrivadaCliente();
                    break;
                case "0":
                    close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion elegida incorrecta");
                    break;
            }
        } while (!opcion.equals("0"));
    }

    private void zonaPrivadaCliente() {
        String opcion;
        do {
            System.out.println("Que quieres cambiar:");
            System.out.println("--Cambios de usuario--");
            System.out.println("1. Nombre");
            System.out.println("2. Telefono");
            System.out.println("3. Email");
            System.out.println("4. Contraseña");
            System.out.println("--Cambios de cliente--");
            System.out.println("5. Direccion");
            System.out.println("6. Provincia");
            System.out.println("7. Codigo Postal");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    modificaUsuario("nombre");
                    break;
                case "2":
                    modificaUsuario("telefono");
                    break;
                case "3":
                    modificaUsuario("email");
                    break;
                case "5":
                    modificaCliente("direccion");
                    break;
                case "6":
                    modificaCliente("provincia");
                    break;
                case "7":
                    modificaCliente("codigoPostal");
                    break;
                case "4":
                    modificaContraseniaUsuario("contraseña");
                    break;
                case "0":
                    menuUsuario();
                    break;
                default:
                    System.out.println("Opcion elegida incorrecta");


            }
        } while (!opcion.equals("0"));
    }

    private void modificaCliente(String campo) {
        ClienteDao clienteDao = new ClienteDao(connection);
        Cliente cliente;

        try {
            cliente = clienteDao.getCliente(usuarioActual).orElseThrow(ClienteNoEncontrado::new);
            if (campo.equals("direccion")) {
                System.out.println("La direccion actual es: " + cliente.getDireccion());
            } else if (campo.equals("provincia")) {
                System.out.println("La provincia actual es: " + cliente.getProvincia());
            } else if (campo.equals("codigoPostal")) {
                System.out.println("El codigo postal actual es:" + cliente.getCodigoPostal());
            }
            System.out.println("Introduce el nuevo valor: ");
            String valor = teclado.nextLine();
            boolean correcto;
            correcto = clienteDao.modificaCliente(campo, valor, usuarioActual.getIdUsuario());
            if (correcto) System.out.println("Modificado correctamente!");
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
        } catch (ClienteNoEncontrado cne) {
            System.out.println(cne.getMessage());
        }
    }

    private void modificaContraseniaUsuario(String contraseña) {
        UsuarioDao usuarioDao = new UsuarioDao(connection);
        Usuario usuario = usuarioActual;

        System.out.println("Introduce la contraseña actual");
        String actual = teclado.nextLine();

        try {
            usuario = usuarioDao.getUsuario(usuarioActual.getNombreUsuario(), actual).orElseThrow(UsuarioNoEncontrado::new);
            System.out.println("Introduce la nueva contraseña");
            String nueva = teclado.nextLine();
            usuario.setContraseña(nueva);

            boolean correcto;
            correcto = usuarioDao.modificaUsuario("contraseña", nueva, usuarioActual.getIdUsuario());
            if (correcto) {
                System.out.println("Contraseña cambiada correctamente!");
            }
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            return;
        } catch (UsuarioNoEncontrado une) {
            System.out.println("Contraseña incorrecta");
            return;
        }

    }

    private void modificaUsuario(String campo) {
        UsuarioDao usuarioDao = new UsuarioDao(connection);

        if (campo.equals("nombre")) {
            System.out.println("Nombre actual:" + usuarioActual.getNombre() + " " +
                    usuarioActual.getApellidos1() + " " +
                    usuarioActual.getApellidos2());
            System.out.println("Introduce el nombre primero:");
            String nombre = teclado.nextLine();
            System.out.println("Introduce tu primer apellido:");
            String apellido1 = teclado.nextLine();
            System.out.println("Introduce tu segundo apellido:");
            String apellido2 = teclado.nextLine();
            try {
                boolean correctoNombre, correctoApellidos1, correctoApellidos2;

                connection.setAutoCommit(false);

                correctoNombre = usuarioDao.modificaUsuario("nombre", nombre, usuarioActual.getIdUsuario());
                correctoApellidos1 = usuarioDao.modificaUsuario("apellido1", apellido1, usuarioActual.getIdUsuario());
                correctoApellidos2 = usuarioDao.modificaUsuario("apellido2", apellido2, usuarioActual.getIdUsuario());

                connection.commit();
                connection.setAutoCommit(true);

                if (correctoNombre && correctoApellidos1 && correctoApellidos2) {
                    System.out.println("El nombre se ha modificado correctamente!");
                } else {
                    System.out.println("Algo ha pasado, no se ha podido modificar el nombre");
                }
            } catch (SQLException sqle) {
                System.out.println("Ha habido un error con la base de datos");
                sqle.printStackTrace();
            }
        } else {
            System.out.print("Valor anterior del " + campo + ": ");
            if (campo.equals("telefono")) {
                System.out.println(usuarioActual.getTelefono());
            } else if (campo.equals("email")) {
                System.out.println(usuarioActual.getEmail());
            }

            System.out.println("Introduce el nuevo valor:");
            String valor = teclado.nextLine();

            try {
                boolean correcto;
                correcto = usuarioDao.modificaUsuario(campo, valor, usuarioActual.getIdUsuario());
                if (correcto) System.out.println("Se ha modificado correctamente el " + campo);
            } catch (SQLException sqle) {
                System.out.println("Ha habido un error con la base de datos");
                sqle.printStackTrace();
            }
        }
    }

    private void buscarMovil() {
        MovilDao movilDao = new MovilDao(connection);
        String marca;

        System.out.println("Selecciona la marca de la que buscar un movil: ");
        try {
            ArrayList<String> marcas;
            ArrayList<Movil> moviles;

            marcas = movilDao.getMarcas();

            int index = 1;
            for (String str : marcas) {
                System.out.println(index + ". " + str);
                index++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            Integer indiceMarca = Integer.parseInt(opcion);
            marca = marcas.get(indiceMarca - 1);

            moviles = movilDao.findByMarca(marca);
            index = 1;
            for (Movil mov : moviles) {
                System.out.println(index + ". " + mov.toString());
                index++;
            }


        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            return;
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Lo que has introducido no es un numero");
            return;
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }

    }

    private void modificaMovil() {
        MovilDao movilDao = new MovilDao(connection);
        Movil movil;

        try {
            ArrayList<Movil> moviles;

            System.out.println("Selecciona el movil a modificar: ");
            moviles = movilDao.findAll();

            int indice = 1;
            for (Movil aux : moviles) {
                System.out.println(indice + ". " + aux.toString());
                indice++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            Integer indiceMovil = Integer.parseInt(opcion);
            movil = moviles.get(indiceMovil - 1);
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            return;
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Lo que has introducido no es un numero");
            return;
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }

        try {
            System.out.println("Introduce la marca (Actual: " + movil.getMarca() + "):");
            movil.setMarca(teclado.nextLine());
            compruebaVacio(movil.getMarca());
            System.out.println("Introduce el modelo (Actual: " + movil.getModelo() + "):");
            movil.setModelo(teclado.nextLine());
            compruebaVacio(movil.getModelo());
            System.out.println("Introduce el color (Actual: " + movil.getColor() + "):");
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            return;
        }

        Integer precio;
        String color;
        try {
            color = (teclado.nextLine());
            System.out.println("Introduce el precio base (Actual: " + movil.getPrecioBase() + "):");
            precio = Integer.parseInt(teclado.nextLine());
            movil.setColor(color);
            movil.setPrecioBase(precio);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            System.out.println("Error: Vehiculo no modificado");
            return;
        }

        try {
            boolean correcto = movilDao.modificaMovil(movil);
            if (correcto) {
                System.out.println("\n El movil se ha modificado correctamente! \n");
            } else {
                System.out.println("\n El movil no se ha podido modificar \n");
            }

        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            return;
        }

    }

    private void compruebaVacio(String campo) throws IllegalArgumentException {
        if (campo.isEmpty()) {
            throw new IllegalArgumentException("El campo no puede estar vacio");
        }
    }

    private void verVentasEnDetalle() {
        Venta venta;
        Empleado empleado;
        Cliente cliente;
        Movil movil;

        try {
            Integer indiceVenta;
            ArrayList<Venta> ventas;
            VentaDao ventaDao = new VentaDao(connection);
            ClienteDao clienteDao = new ClienteDao(connection);

            if (usuarioActual.getRol().equals("USER")) {
                cliente = clienteDao.getCliente(usuarioActual).orElseThrow(ClienteNoEncontrado::new);
                ventas = ventaDao.findVenta(cliente.getDni());
            } else {
                ventas = ventaDao.findAll();
            }

            if (ventas.isEmpty()) {
                System.out.println("No hay ninguna venta, revisa la BD");
                return;
            } else {
                int index = 1;
                for (Venta aux : ventas) {
                    System.out.println(index + ". " + aux.toString());
                    index++;
                }

                System.out.print("Seleccione una venta para ver en mas detalle: ");
                String opcion = teclado.nextLine();
                indiceVenta = Integer.parseInt(opcion);
                venta = ventas.get(indiceVenta - 1);
            }
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            return;
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Lo que has introducido no es un numero");
            return;
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        } catch (ClienteNoEncontrado cne) {
            System.out.println(cne.getMessage());
            return;
        }

        try {
            ClienteDao clienteDao = new ClienteDao(connection);
            EmpleadoDao empleadoDao = new EmpleadoDao(connection);
            MovilDao movilDao = new MovilDao(connection);

            cliente = clienteDao.getCliente(venta.getDni_cliente());
            empleado = empleadoDao.getEmpleado(venta.getDni_empleado());
            movil = movilDao.getMovil(venta.getReferencia());
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            return;
        }

        System.out.println("Detalles de la venta seleccionada: ");
        System.out.println(venta.toString());
        System.out.println("\n Empleado que hizo la venta: ");
        System.out.println(empleado.toString());
        System.out.println("\n El cliente que hizo la compra: ");
        System.out.println(cliente.toString());
        System.out.println("\n Movil comprado por el cliente: ");
        System.out.println(movil.toString());

        if (!usuarioActual.getRol().equals("USER")) {
            System.out.println("Quieres eliminar la venta seleccionada? S/N");
            String eleccion = teclado.nextLine();
            if (eleccion.toLowerCase().equals("s")) {
                System.out.println("Confirma la eleccion S/N");
                eleccion = teclado.nextLine();
                boolean correcto;
                if (eleccion.toLowerCase().equals("s")) {
                    try {
                        VentaDao ventaDao = new VentaDao(connection);
                        correcto = ventaDao.borraVenta(venta.getIdVenta());
                    } catch (SQLException sqle) {
                        System.out.println("Ha habido un error con la base de datos");
                        return;
                    }
                    if (correcto) System.out.println("Venta eliminada!");
                }
            }
        }
    }

    private void verVentaCliente() {
        VentaDao ventaDao = new VentaDao(connection);
        ClienteDao clienteDao = new ClienteDao(connection);
        ArrayList<Venta> ventas;

        try {
            Cliente cliente = clienteDao.getCliente(usuarioActual).orElseThrow(ClienteNoEncontrado::new);
            ventas = ventaDao.findVenta(cliente.getDni());
            if (ventas.isEmpty()) {
                System.out.println("No tienes ninguna compra realizada en la tienda");
            } else {
                for (Venta venta : ventas) {
                    System.out.println(venta.toString());
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
        } catch (ClienteNoEncontrado cne) {
            System.out.println(cne.getMessage());
        }
    }

    private void buscaVenta() {
        VentaDao ventaDao = new VentaDao(connection);
        ArrayList<Venta> ventas;

        System.out.print("Introduce el DNI del ciente para buscar la venta: ");
        String dni = teclado.nextLine().trim();

        try {
            validarDNI(dni);
            ventas = ventaDao.findVenta(dni);
        } catch (DniNoValido dnv) {
            System.out.println(dnv.getMessage());
            return;
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            return;
        }

        if (ventas.isEmpty()) {
            System.out.println("No hay ninguna venta registrada con el DNI introducido.");
        } else {
            int index = 1;
            for (Venta venta : ventas) {
                System.out.println(index + ".- " + venta.toString());
                index++;
            }
        }
        System.out.println("");
    }

    private void altaVenta() {
        VentaDao ventaDao = new VentaDao(connection);
        EmpleadoDao empleadoDao = new EmpleadoDao(connection);
        ClienteDao clienteDao = new ClienteDao(connection);
        MovilDao movilDao = new MovilDao(connection);

        Empleado empleado;
        Cliente cliente;
        Movil movil;

        System.out.println("Por favor, selecciona el empleado que ha hecho la venta:");
        try {
            ArrayList<Empleado> empleados;
            Integer indiceEmpleado;
            Integer auxEmpleado = 0;

            empleados = empleadoDao.findAll();

            for (Empleado emp : empleados) {
                System.out.println((auxEmpleado + 1) + ". " + emp.toString());
                auxEmpleado++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            indiceEmpleado = Integer.parseInt(opcion);
            empleado = empleados.get(indiceEmpleado - 1);
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            return;
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Lo que has introducido no es un numero");
            System.out.println("Venta no añadida");
            return;
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }

        System.out.println("Por favor, selecciona al cliente:");
        try {
            ArrayList<Cliente> clientes;
            Integer auxCliente = 0;
            Integer indiceCliente;

            clientes = clienteDao.findAll();

            for (Cliente cl : clientes) {
                System.out.println((auxCliente + 1) + ". " + cl.toString());
                auxCliente++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            indiceCliente = Integer.parseInt(opcion);
            cliente = clientes.get(indiceCliente - 1);
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            return;
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Lo que has introducido no es un numero");
            System.out.println("Venta no añadida");
            return;
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }

        System.out.println("Por favor, selecciona el modelo de movil que se ha vendido:");
        try {
            ArrayList<Movil> moviles;
            Integer auxMovil = 0;
            Integer indiceMovil;

            moviles = movilDao.findAll();

            for (Movil cl : moviles) {
                System.out.println((auxMovil + 1) + ". " + cl.toString());
                auxMovil++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            indiceMovil = Integer.parseInt(opcion);
            movil = moviles.get(indiceMovil - 1);
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            return;
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Lo que has introducido no es un numero");
            System.out.println("Venta no añadida");
            return;
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }
        System.out.println("Introduce el imei asignado:");
        String imei = teclado.nextLine();
        System.out.println("Introduce el color:");
        String color = teclado.nextLine();
        System.out.println("Introduce el precio total:");
        String precioTotal = teclado.nextLine();
        Integer precio;
        try {
            precio = Integer.parseInt(precioTotal);
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Lo que has introducido no es un numero");
            return;
        }

        Venta venta = new Venta(empleado.getDni(), cliente.getDni(), movil.getReferencia(), imei, color, precio);

        try {
            ventaDao.altaVenta(venta);
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
        }
        System.out.println("");
        System.out.println("Venta añadida!");
        System.out.println("");

    }

    private void verMoviles() {
        MovilDao movilDao = new MovilDao(connection);

        System.out.println("Lista de moviles:");
        try {
            ArrayList<Movil> moviles = movilDao.findAll();
            for (Movil movil : moviles) {
                System.out.println(movil.toString());
            }
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos.");
            sqle.printStackTrace();
        }
        System.out.println("");
    }

    private void altaMovil() {
        MovilDao movilDao = new MovilDao(connection);

        System.out.println("Introduce la marca:");
        String marca = teclado.nextLine();
        System.out.println("Introduce el modelo:");
        String modelo = teclado.nextLine();
        System.out.println("Introduce el color:");
        Integer precio;
        String color;
        try {
            color = (teclado.nextLine());
            System.out.println("Introduce el precio base:");
            precio = Integer.parseInt(teclado.nextLine());

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            System.out.println("Error: movil no introducido");
            return;
        }

        Movil movil = new Movil(marca.trim(), modelo.trim(), color, precio);
        try {
            movilDao.altaMovil(movil);
            System.out.println("El movil se ha añadido correctamente.");
        } catch (YaExisteMovil yem) {
            System.out.println(yem.getMessage());
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
        }
        System.out.println("");
    }

    public void connect() {
        database = new Database();
        connection = database.getConnection();
    }

    public void close() {
        database.close();
        System.out.println("Base de datos desconectada.");
    }
}
