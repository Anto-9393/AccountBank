package com.example.contoCorrente.entity;

import javax.persistence.*;


@Entity
@Table(name="conto")
public class Conto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  long id_persona;
    private int saldo;

    public Conto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_persona() {
        return id_persona;
    }

    public void setId_persona(long id_persona) {
        this.id_persona = id_persona;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
