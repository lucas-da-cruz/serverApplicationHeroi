package br.com.herois.model.form;

import br.com.herois.model.entities.UsuarioAdmin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioAdminFormUpdate {

        @NotBlank
        private String nome;
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String telefone;
        @NotBlank
        private String endereco;

        public UsuarioAdmin converterUsuario(){
            return new UsuarioAdmin(nome, email, telefone, endereco);
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }
    }
