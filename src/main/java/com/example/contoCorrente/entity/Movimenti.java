package com.example.contoCorrente.entity;

import javax.persistence.*;

@Entity
@Table(name="movimenti")
public class Movimenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  long id_conto;
    private String tipo;
    private int importo;

    public Movimenti() {
    }

    public Movimenti(String tipo, int importo) {
        this.tipo=tipo;
        this.importo= importo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_conto() {
        return id_conto;
    }

    public void setId_conto(long id_conto) {
        this.id_conto = id_conto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getImporto() {
        return importo;
    }

    public void setImporto(int importo) {
        this.importo = importo;
    }


}



