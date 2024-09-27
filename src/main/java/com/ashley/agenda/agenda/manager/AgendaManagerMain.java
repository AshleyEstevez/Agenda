/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ashley.agenda.agenda.manager;

import com.ashley.agenda.AgendaManager;
import com.ashley.agenda.entity.Contacto;
import com.ashley.agenda.impl.memoria.AgendaArchivoimpl;
import com.ashley.agenda.impl.memoria.AgendaListImpl;
import com.ashley.agenda.impl.memoria.AgendaSetImpl;
import com.ashley.agenda.impl.memoria.AgendaMapImpl;
import com.ashley.agenda.impl.memoria.AgendaDataBaseImplm;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class AgendaManagerMain {

    public static void main(String[] args) {
        AgendaManager agenda = null;

        Scanner sc = new Scanner(System.in);

        boolean salir = false;

        while (!salir) {
            System.out.println("Implementacion Agenda");
            System.out.println("1- AgendaListImpl");
            System.out.println("2- AgendaSetImpl");
            System.out.println("3- AgendaMapImpl");
            System.out.println("4-AgendaArchivoimpl");
            System.out.println("5- AgendaDataBaseImplm");
            System.out.println("6- Salir");
            System.out.print("Introduzca la opcion:");
            System.out.println();
            int resp = sc.nextInt();
            int nextId = 1;

            switch (resp) {
                case 1:
                    agenda = new AgendaListImpl();
                    menu(sc, agenda, 1);
                    break;

                case 2:
                    agenda = new AgendaSetImpl();
                    menu(sc, agenda, 1);
                    break;

                case 3:
                    agenda = new AgendaMapImpl();
                    menu(sc, agenda, 1);
                    break;

                case 4:
                    agenda = new AgendaArchivoimpl();
                    menuArchivoCsv(sc, agenda, 1);
                    break;

                case 5:
                    agenda = new AgendaDataBaseImplm();
                    menu(sc, agenda, 1);

                default:
                    System.out.println("Opcion no correcta");

            }

        }
    }

    public static void menu(Scanner sc, AgendaManager agenda, int nextId) {

        while (true) {
            System.out.println("1-Agregar contacto");
            System.out.println("2-Eliminar contacto");
            System.out.println("3-Buscar contacto");
            System.out.println("4- Exportar contacto");
            System.out.println("5-Actualizar contacto");
            System.out.println("6-Salir");
            System.out.println("................");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:

                    agregarContacto(sc, agenda, nextId);

                    break;

                case 2:

                    eliminarContacto(sc, agenda);

                    break;

                case 3:

                    buscarContacto(sc, agenda);

                    break;

                case 4:
                    
                    

                    break;

                case 5:
                    actualizarContacto(sc, agenda);
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    return;

                default:
                    System.out.println("Opcion no valida");

            }

        }
    }

    public static void agregarContacto(Scanner sc, AgendaManager agenda, int nextId) {
        System.out.println("Agregar contacto...");
        System.out.print("Insertar nombre: ");
        String name = sc.nextLine();
        System.out.print("Insertar apellido: ");
        String lastName = sc.nextLine();

        Contacto contactoExistente = agenda.buscarContacto(name, lastName);
        if (contactoExistente != null) {
            System.out.println("El contacto ya existe en la agenda.");
        } else {
            System.out.print("Insertar teléfono: ");
            String phone = sc.nextLine();
            System.out.print("Insertar dirección: ");
            String address = sc.nextLine();
            System.out.print("Insertar edad: ");
            int age = sc.nextInt();
            sc.nextLine();

            Contacto contacto = new Contacto(nextId, name, lastName, phone, address, age);
            agenda.agregarContacto(contacto);
            System.out.println("Contacto agregado con éxito.");
        }
    }

    public static void eliminarContacto(Scanner sc, AgendaManager agenda) {
        System.out.println("Eliminar contacto...");
        System.out.print("Insertar nombre del contacto a eliminar: ");
        String name = sc.nextLine();
        System.out.print("Insertar apellido del contacto a eliminar: ");
        String lastName = sc.nextLine();

        Contacto contacto = new Contacto(name, lastName);
        if (agenda.eliminarContacto(contacto)) {
            System.out.println("Contacto eliminado con éxito.");
        } else {
            System.out.println("El contacto no existe en la agenda.");
        }
    }

    public static void buscarContacto(Scanner sc, AgendaManager agenda) {
        System.out.println("Buscar contacto...");
        System.out.print("Insertar nombre: ");
        String name = sc.nextLine();
        System.out.print("Insertar apellido: ");
        String lastName = sc.nextLine();

        Contacto contacto = agenda.buscarContacto(name, lastName);
        if (contacto != null) {
            System.out.println("Contacto encontrado: " + contacto.getName() + " " + contacto.getLastName() + " " + contacto.getPhone());
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    public static void exportarContactosCSV(AgendaManager agenda) {

    }

    public static void actualizarContacto(Scanner sc, AgendaManager agenda) {
        System.out.println("Actualizar contacto:");
        System.out.println("Ingrese el nombre del contacto que quiere actualizar:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el apellido del contacto que quiere actualizar:");
        String apellido = sc.nextLine();

        Contacto contacto = agenda.buscarContacto(nombre, apellido);

        if (contacto != null) {
            System.out.println("Contacto encontrado: " + contacto.getName() + " " + contacto.getLastName());
            
            System.out.println("Ingrese el nuevo nombre");
            String newName = sc.nextLine();
            if(!newName.isEmpty()){
            contacto.setName(newName);
            }
            
            System.out.println("Ingrese el nuevo apellido");
            String newLastName = sc.nextLine();
            if (!newLastName.isEmpty()){
            contacto.setLastName(newLastName);
            }

            System.out.println("Ingrese el nuevo teléfono:");
            String nuevoTelefono = sc.nextLine();
            if (!nuevoTelefono.isEmpty()) {
                contacto.setPhone(nuevoTelefono);
            }

            System.out.println("Ingrese la nueva dirección:");
            String nuevaDireccion = sc.nextLine();
            if (!nuevaDireccion.isEmpty()) {
                contacto.setAddress(nuevaDireccion);
            }

            System.out.println("Ingrese la nueva edad]:");
            int nuevaEdad = sc.nextInt();
            sc.nextLine();
            if (nuevaEdad > 0) {
                contacto.setAge(nuevaEdad);
            }

            System.out.println("Contacto actualizado exitosamente.");
        } else {
            System.out.println("El contacto no fue encontrado.");
        }
    }

    public static void menuArchivoCsv(Scanner sc, AgendaManager agenda, int nextId) {

        while (true) {
            System.out.println("1-Agregar contacto");
            System.out.println("2-Eliminar contacto");
            System.out.println("3-Buscar contacto");
            System.out.println("4-Exportar contactos");
            System.out.println("5-Actualizar contacto");
            System.out.println("6-Salir");
            System.out.println("................");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Agregar contacto....");
                    System.out.println("Insert name:");
                    String name = sc.nextLine();
                    System.out.println("Insert lastname:");
                    String lastName = sc.nextLine();

                    Contacto contactoExistente = agenda.buscarContacto(name, lastName);
                    if (contactoExistente != null) {
                        System.out.println("El contacto ya existe en la agenda.");
                    } else {

                        System.out.println("Insert phone:");
                        String phone = sc.nextLine();
                        System.out.println("Insert address:");
                        String address = sc.nextLine();
                        System.out.println("Insert age:");
                        int age = sc.nextInt();
                        sc.nextLine();
                        Contacto contactoadd = new Contacto(nextId++, name, lastName, phone, address, age);
                        agenda.agregarContacto(contactoadd);

                    }
                    break;

                case 2:
                    System.out.println("Eliminar contacto");
                    System.out.println("Inserta el nombre del contacto a eliminar");
                    String nameToDelete = sc.nextLine();
                    System.out.println("Inserte el apellido del contacto a eliminar");
                    String lastNameToDelete = sc.nextLine();
                    Contacto contactodelete = new Contacto(0, nameToDelete, lastNameToDelete, "", "", 0);
                    if (agenda.eliminarContacto(contactodelete)) {
                        System.out.println("Contacto eliminado: " + contactodelete.getName() + " " + contactodelete.getLastName());
                    } else {
                        System.out.println("El contacto no existe en la agenda");
                    }
                    break;

                case 3:
                    System.out.println("Buscar contacto:");
                    System.out.println("Ingrese el nombre");
                    String nameToSearch = sc.nextLine();
                    System.out.println("Ingrese el apellido");
                    String lastNameToSearch = sc.nextLine();
                    Contacto contactoBuscar = agenda.buscarContacto(nameToSearch, lastNameToSearch);
                    if (contactoBuscar != null) {
                        System.out.println("Contacto encontrado: " + contactoBuscar.getName() + " " + contactoBuscar.getLastName());
                    } else {
                        System.out.println("Contacto no encontrado");
                    }
                    break;

                case 4:
                    System.out.println("Exportar contactos");
                    System.out.println("Ingrese el nombre del archivo (sin extensión):");
                    String fileName = sc.nextLine();
                    agenda.exportarContacto(fileName);
                    System.out.println("Contactos exportados a " + fileName + ".csv");
                    break;

                case 5:
                    System.out.println("Actualizar contacto");
                    System.out.println("Ingrese el ID del contacto a actualizar:");
                    int idToUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Insert name:");
                    String newName = sc.nextLine();
                    System.out.println("Insert lastname:");
                    String newLastName = sc.nextLine();
                    System.out.println("Insert phone:");
                    String newPhone = sc.nextLine();
                    System.out.println("Insert address:");
                    String newAddress = sc.nextLine();
                    System.out.println("Insert age:");
                    int newAge = sc.nextInt();
                    sc.nextLine();
                    Contacto contactoUpdate = new Contacto(idToUpdate, newName, newLastName, newPhone, newAddress, newAge);
                    try {
                        agenda.actualizarContacto(contactoUpdate);
                        System.out.println("Contacto actualizado");
                    } catch (Exception e) {
                        System.out.println("Error al actualizar el contacto: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del menú de archivo");
                    return;

                default:
                    System.out.println("Opcion no correcta");
                    break;
            }
        }

    }
}
