/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.pmp.pmp202203;

import java.util.Scanner;
import java.util.ArrayList;
import com.pmp.pmp202203.dao.CitaTallerDao;
/**
 *
 * @author obetancourth
 */
public class Pmp202203 {
    public static Scanner input;
    public static ArrayList<CitaTaller> arrCitasTaller;
    public static CitaTallerDao tallerDao;
    public static void main(String[] args) {
        // Crear una instancia de scanner
        input = new Scanner(System.in);
        arrCitasTaller = new ArrayList();
        
        tallerDao = new CitaTallerDao();
        
        PmpUX.header("CRUD Taller OJBA");
        String optionSelected = "";
        // equalsIgnoreCase  == "S" || = "s"
        while (!optionSelected.equalsIgnoreCase("S")) {
            PmpUX.generateMenu();
            optionSelected = input.nextLine();
            mainController(optionSelected);
        }
    }
    
    private static void mainController( String optionSelected){
        arrCitasTaller = tallerDao.obtenerCitas();
        switch (optionSelected.toUpperCase()) {
        case "M":
            // llamar a la funcion que maneje la opción
            mostrarCitas();
            break;
        case "N":
            newCitaTaller();
            break;
        case "A":
            actualizarCita();
            break;
        case "E":
            eliminarCita();
            break;
        case "V":
            System.out.println("Opcion V Seleccionada");
            break;
        case "S":
            System.out.println("Opcion S Seleccionada");
            break;
        default:
            PmpUX.header("Opción no Válida");
        }
    }
    
    // Refactored Functions
    private static CitaTaller inputForm (CitaTaller baseCita){
        baseCita.setNombreCliente(
            PmpUX.getFieldInput(input, "Nombre del Cliente", baseCita.getNombreCliente())
        );
        baseCita.setPlacaCarro(
            PmpUX.getFieldInput(input, "Placa del Carro", baseCita.getPlacaCarro())
        );
        baseCita.setAnioCita(
            Integer.parseInt(
                PmpUX.getFieldInput(input, "Año", baseCita.getAnioCita().toString())
            )
        );
        baseCita.setMesCita(
            Integer.parseInt(
                PmpUX.getFieldInput(input, "Mes", baseCita.getMesCita().toString())
            )
        );
        baseCita.setDiaCita(
            Integer.parseInt(
                PmpUX.getFieldInput(input, "Dia", baseCita.getDiaCita().toString())
            )
        );
        baseCita.setHora24Cita(
            Integer.parseInt(
                PmpUX.getFieldInput(input, "Hora", baseCita.getHora24Cita().toString())
            )
        );
        return baseCita;
    }
    
    private static CitaTaller validarEntradaRegistro() {
        if (arrCitasTaller.isEmpty()) {
            System.out.println("No Hay Datos!");
            return null;
        }
        int index = Integer.parseInt(
            PmpUX.getFieldInput(input, "Número de Linea: ", "1")
        );
        for (int i = 0; i < arrCitasTaller.size(); i++){
            if (index == arrCitasTaller.get(i).getId()) {
                return arrCitasTaller.get(i);
            }
        }
        return null;
    }
    
    // Crud Functions
    private static void newCitaTaller(){
        CitaTaller newCitaTallerIns = new CitaTaller();
        newCitaTallerIns.setNombreCliente("Fulanito de Tal");
        newCitaTallerIns.setPlacaCarro("HAC0801");
        newCitaTallerIns.setAnioCita(2022);
        newCitaTallerIns.setMesCita(9);
        newCitaTallerIns.setDiaCita(8);
        newCitaTallerIns.setHora24Cita(17);
        newCitaTallerIns.setTelefonoCliente("0000000");
        newCitaTallerIns.setMotivoCita("Se me arruino la maquina");
        newCitaTallerIns.setEstadoCita("ACT");
        newCitaTallerIns.setModeloCarro("Toyotita Mil");
        newCitaTallerIns = inputForm(newCitaTallerIns);
        tallerDao.insertCita(newCitaTallerIns);
        arrCitasTaller.add(newCitaTallerIns);
    }
    private static void mostrarCitas() {
        arrCitasTaller = tallerDao.obtenerCitas();
        if (arrCitasTaller.size() > 0 ) {
            for ( int i = 0; i < arrCitasTaller.size(); i++) {
                CitaTaller cita = arrCitasTaller.get(i);
                System.out.println(
                        String.valueOf(i+ 1) + " -- " +
                        cita.getNombreCliente() +
                        " " +
                        cita.getAnioCita().toString() + "/" + 
                        cita.getMesCita().toString() + "/" +
                        cita.getDiaCita().toString() + " " + 
                        cita.getHora24Cita().toString() + " horas"
                );
            }
        } else {
            System.out.println("No hay datos que mostrar.");
        }
    }
    private static void actualizarCita() {
        CitaTaller updateCitaTaller = validarEntradaRegistro();
        if(updateCitaTaller!=null){
            updateCitaTaller = inputForm(updateCitaTaller);
            tallerDao.updateCita(updateCitaTaller);
            System.out.println("Registro Modificado");
        }
    }
    private static void eliminarCita(){
        CitaTaller deleteCitaTaller = validarEntradaRegistro();
        if (deleteCitaTaller != null) {
            tallerDao.deleteCita(deleteCitaTaller);
            System.out.println("Registro Modificado");
        }
    }
    
    // TODO Ver Detalle
}