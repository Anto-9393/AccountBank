package com.example.contoCorrente.entity;

import com.example.contoCorrente.utils.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "movimenti")
public class Movimenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long id_conto;
    @Enumerated(EnumType.STRING)
    private Type tipo;
    @Min(10)
    private int importo;

    public Movimenti() {
    }

    public Movimenti(long id, long id_conto,Type tipo, int importo) {
        this.id = id;
        this.id_conto = id_conto;
        this.tipo = tipo;
        this.importo = importo;
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

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public int getImporto() {
        return importo;
    }

    public void setImporto(int importo) {
        this.importo = importo;
    }


}



