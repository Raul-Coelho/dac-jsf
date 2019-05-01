package br.edu.ifpb.infra.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.LocalDate;

@FacesValidator(value = "validator.dataNascimento")
public class ValidartorDataNascimento implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object valor) throws ValidatorException {
        LocalDate dataNascimento = (LocalDate) valor;

        if (dataNascimento == null){
            throw new ValidatorException(new FacesMessage("Data de Nascimento Invalida !!"));
        }

        if(dataNascimento.isEqual(LocalDate.now()) || dataNascimento.isAfter(LocalDate.now())){
            throw new ValidatorException(new FacesMessage("Data Incompativel!"));
        }
    }
}
