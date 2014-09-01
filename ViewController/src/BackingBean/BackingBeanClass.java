package BackingBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.sun.jersey.api.client.*;

public class BackingBeanClass {
    public BackingBeanClass() {
    }

    public void it1_validator(FacesContext facesContext,
                              UIComponent uIComponent, Object object) {
        String value = object.toString();
        System.out.println(value);
    }

    public String onSubmit() {

        Client client = Client.create();
        String serverURL = "http://localhost:7101/WebService-WebserviceTest-context-root/jersey/user";
        WebResource webResource =
            client.resource(serverURL);
        ClientResponse response;
        response =  
                webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " +
                                       response.getStatus());
        }

        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
        System.out.println(output);
        return "Success";
    }
}
