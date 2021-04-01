package br.com.zup.mercadolivre.usuario.data.domain;

public enum Roles {

    ROLE_USER("ROLE_USER");

    private String value;

    Roles(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value.substring(5,this.value.length());
    }

    @Override
    public String toString() {
        return this.value;
    }
}
