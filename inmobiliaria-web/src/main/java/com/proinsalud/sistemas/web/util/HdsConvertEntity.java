package com.proinsalud.sistemas.web.util;

import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * permite convertir de un valor de f:selectItems a una entidad para ello la entidad debe tener el atributo id
 * @author JacquelineLopez
 *
 */
@FacesConverter(value = "hdsConvertEntity")
public class HdsConvertEntity implements Converter {

	/**
	 * getAsObject
	 * Metodo que permite retornar el objeto 
	 * @param String value 
	 * @param FacesContext context
	 * @param UIComponent component
	 * @return Object objeto: objeto retornado de la seleccion
	 */
	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		List<UIComponent> hijos = component.getChildren();
		UISelectItems items = null;
		for (UIComponent uiComponent : hijos) {
			if (uiComponent instanceof UISelectItems) {
				items = (UISelectItems) uiComponent;
				break;
			}
		}
		if (items != null) {
			ValueExpression valueExp = items.getValueExpression("value");
			List<Object> listaValores = (List<Object>) valueExp
					.getValue(FacesContext.getCurrentInstance().getELContext());
			if (null != listaValores) {
				for (Object object : listaValores) {
					if (object.toString().equals(value)) {
						return object;
					}
				}
			}
		}
		return null;
	}

	/**
	 * getAsString
	 * Metodo que permite retonar la cadena del valor del objeto
	 * @param FacesContext context
	 * @param UIComponent component
	 * @param Object value
	 * @return String cadena: cadena que representa el objeto
	 */
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value == null ? null : value.toString());
	}

}
