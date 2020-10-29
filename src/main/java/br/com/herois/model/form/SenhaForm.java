package br.com.herois.model.form;

import javax.validation.constraints.NotBlank;

public class SenhaForm {

    @NotBlank(message = "{senha.not.blank}")
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
