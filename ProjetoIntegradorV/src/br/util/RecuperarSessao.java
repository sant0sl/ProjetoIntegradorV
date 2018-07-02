package br.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RecuperarSessao {
	
	public static <T> Object getObjectSession(String attribute){		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
		HttpSession session = request.getSession(true);  
		return session.getAttribute(attribute);				
	}
	
	public static void  setObjectSession(String attributeName, Object attribute){		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
		HttpSession session = request.getSession(true);  
		session.setAttribute(attributeName, attribute);				
	}
}