package com.mariaamigateam.mariaamiga.model;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
    private String nombre;
    private String telefono;
    private String genero;
    private Date fechaNacimiento;
    private boolean perfilCompleto;
    private boolean gustosCompleto;
    private ArrayList<String> gustos;
    private ArrayList<String> diaPerfecto;

    public Usuario(){
        this.nombre="";
        this.telefono="";
        this.genero="";
        this.fechaNacimiento = new Date();
        this.perfilCompleto=false;
        this.gustosCompleto=false;
        this.gustos =new ArrayList<>();
        this.diaPerfecto=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isPerfilCompleto() {
        return perfilCompleto;
    }

    public void setPerfilCompleto(boolean perfilCompleto) {
        this.perfilCompleto = perfilCompleto;
    }

    public boolean isGustosCompleto() {
        return gustosCompleto;
    }

    public void setGustosCompleto(boolean gustosCompleto) {
        this.gustosCompleto = gustosCompleto;
    }

    public ArrayList<String> getGustos() {
        return gustos;
    }

    public void setGustos(ArrayList<String> gustos) {
        this.gustos = gustos;
    }

    public ArrayList<String> getDiaPerfecto() {
        return diaPerfecto;
    }

    public void setDiaPerfecto(ArrayList<String> diaPerfecto) {
        this.diaPerfecto = diaPerfecto;
    }
}
