package model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Estado {

   @Id
   private String uf;

    private String nome;















    public Estado(){}

    public Estado(String nome, String uf) {

        this.nome = nome;
        this.uf = uf;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(uf, estado.uf) && Objects.equals(nome, estado.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uf);
    }
}
