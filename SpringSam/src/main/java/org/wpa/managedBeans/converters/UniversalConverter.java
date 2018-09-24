package org.wpa.managedBeans.converters;

import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 * Converter from string to map.
 * @author @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova
 * <veronika at maurerova.cz>
 */

@FacesConverter("universalConverter")
public class UniversalConverter implements Converter{
    private static final HashMap<String,Object> map = new HashMap<String, Object>();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string!=null&&!string.isEmpty()){
            Object o = map.get(string);
            map.remove(string);
            return o;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o!=null){
            map.put(o.toString(), o);
            return o.toString();
        }
        return "";
    }
    
}
