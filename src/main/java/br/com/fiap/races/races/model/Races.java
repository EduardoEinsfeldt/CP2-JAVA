package br.com.fiap.races.races.model;

import jakarta.persistence.*;

@Entity
public class Races {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;

        private int expectativaDeVida;

        private String origem;

        private String descricao;

        @Enumerated(EnumType.STRING)
        private CorSangue corSangue;

        public Races() {
        }

        public Races(Long id, String nome, int expectativaDeVida, String origem, String descricao, CorSangue corSangue) {
            this.id = id;
            this.nome = nome;
            this.expectativaDeVida = expectativaDeVida;
            this.origem = origem;
            this.descricao = descricao;
            this.corSangue = corSangue;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getExpectativaDeVida() {
            return expectativaDeVida;
        }

        public void setExpectativaDeVida(int expectativaDeVida) {
            this.expectativaDeVida = expectativaDeVida;
        }

        public String getOrigem() {
            return origem;
        }

        public void setOrigem(String origem) {
            this.origem = origem;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public CorSangue getCorSangue() {
            return corSangue;
        }

        public void setCorSangue(CorSangue corSangue) {
            this.corSangue = corSangue;
        }
    }
