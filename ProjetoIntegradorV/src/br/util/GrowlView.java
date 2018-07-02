package br.util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class GrowlView {

	public static void MessagemErro() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Oops!", "Não foi encontrado nenhum registro através dessas informações. Tente novamente!");
		context.addMessage(null, fm);		
	}

}
