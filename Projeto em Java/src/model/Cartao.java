package model;

public class Cartao {

    private String clube;
    private String tipo; //amarelo ou vermelho
    private String atleta;

    public Cartao (String clube, String tipo, String atleta){
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
        return "Cartao{" +
               "clube='" + clube + '\'' +
               ", tipo='" + tipo + '\'' +
               ", atleta='" + atleta + '\'' +
               '}';
    }
}