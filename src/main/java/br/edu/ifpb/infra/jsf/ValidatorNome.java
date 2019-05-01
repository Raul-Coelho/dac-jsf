package br.edu.ifpb.infra.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidatorNome implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object valor) throws ValidatorException {
        String nome = (String) valor;
        if(nome == null){
            throw new ValidatorException(new FacesMessage("O nome não foi preenchido"));
        }
        if(nome.length() == 0){
            throw new ValidatorException(new FacesMessage("O campo nome está vazio !"));
        }
    }
}
