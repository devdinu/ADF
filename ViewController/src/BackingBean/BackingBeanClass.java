package BackingBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class BackingBeanClass {
    public BackingBeanClass() {
    }

    public void it1_validator(FacesContext facesContext, UIComponent uIComponent, Object object) {        
        String value = object.toString();
        System.out.println(value);
    }
}
