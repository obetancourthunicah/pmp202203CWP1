/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.pmp202203.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.lang.Exception;
import com.pmp.pmp202203.CitaTaller;
/**
 *
 * @author obetancourth
 */
public class CitaTallerDao {
    /*
    nombreCliente;
    telefonoCliente;
    placaCarro;
    modeloCarro;
    motivoCita;
    anioCita;
    mesCita;
    diaCita;
    hora24Cita;
    estadoCita
    */
    private Connection conn = null;
    public CitaTallerDao(){
        try {
        conn = Conexion.getConnection();
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS CITATALLER ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "nombreCliente TEXT ,"
                + "telefonoCliente TEXT ,"
                + "placaCarro TEXT ,"
                + "modeloCarro TEXT ,"
                + "motivoCita TEXT ,"
                + "anioCita INTEGER ,"
                + "mesCita INTEGER ,"
                + "diaCita INTEGER ,"
                + "hora24Cita INTEGER ,"
                + "estadoCita TEXT"
                + ");";
        Statement comandoSQLCreate = conn.createStatement();
        comandoSQLCreate.executeUpdate(sqlCreateTable);
        comandoSQLCreate.close();
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    public void insertCita(CitaTaller newCita){
        try{
            String sqlInsert = "INSERT INTO CITATALLER (nombreCliente, " +
                    "telefonoCliente, placaCarro, modeloCarro, motivoCita, " +
                    "anioCita, mesCita, diaCita, hora24Cita, estadoCita) values (" +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement comandoInsert = conn.prepareStatement(sqlInsert);
            
            comandoInsert.setString(1, newCita.getNombreCliente());
            comandoInsert.setString(2, newCita.getTelefonoCliente());
            comandoInsert.setString(3, newCita.getPlacaCarro());
            comandoInsert.setString(4, newCita.getModeloCarro());
            comandoInsert.setString(5, newCita.getMotivoCita());
            comandoInsert.setInt(6, newCita.getAnioCita());
            comandoInsert.setInt(7, newCita.getMesCita());
            comandoInsert.setInt(8, newCita.getDiaCita());
            comandoInsert.setInt(9, newCita.getHora24Cita());
            comandoInsert.setString(10, newCita.getEstadoCita());
            
            int registroAgregados = comandoInsert.executeUpdate();
            System.out.println(registroAgregados);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());        
        }
        
    }
}
 