package model;

public class Gol {

    private String clube;
    private String atleta;
    private String tipo; //regular, penalti ou contra

    public Gol (String clube, String atleta, String tipo){
        this.clube = clube;
        this.atleta = atleta;
        this.tipo = tipo;
    }

    public String getClube (){
        return clube;
    }

    public String getAtleta (){
        return atleta;
    }

    public String getTipo (){
        return tipo;
    }

    @Override
    public String toString(){
        return "Gol{" +
               "clube='" + clube + '\'' +
               ", atleta='" + atleta + '\'' +
               ", tipo='" + tipo + '\'' +
               '}';
    }
}