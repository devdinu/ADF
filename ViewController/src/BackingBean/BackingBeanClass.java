package BackingBean;

import com.obpdemo.model.Party;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.sun.jersey.api.client.*;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.io.IOException;

import java.util.HashMap;

import javax.ws.rs.core.MultivaluedMap;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class BackingBeanClass {
    private RichInputText userName;
    private String remoteUrl =
        "http://localhost:7101/WebService-WebserviceTest-context-root/jersey/user";
    private RichInputText userId;
    private RichInputText newUserName;
    private RichInputText newUserId;
    Helper helper = new Helper();

    public BackingBeanClass() {
    }

    public void it1_validator(FacesContext facesContext,
                              UIComponent uIComponent, Object object) {
        String value = object.toString();
        System.out.println(value);
    }

    public String onSubmit() {
        String result = callGetMethod(remoteUrl, "application/json");
        System.out.println(result);
        return "Success";
    }

    private String callGetMethod(String serverUrl, String mediaType) {
        Client client = Client.create();
        WebResource webResource = client.resource(serverUrl);

        //calling get method
        System.out.println("checking ID##" + userId.getValue().toString());

        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("userId", userId.getValue().toString());
        queryParams.add("userName",
                        "some"); // userName.getValue().toString());
        ClientResponse response =
            webResource.queryParams(queryParams).get(ClientResponse.class);
        System.out.println("Respoonse:**" + response.toString());
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " +
                                       response.getStatus());
        }
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n" +
                output);
        //ObjectMapper objMapper = new ObjectMapper();
        // objMapper.readValue(output, User.class);
        helper.showPartyDetails("PartyVO1Iterator", output);
        return "StayOnTheSamePage";
    }

    private void callPostMethod(String serverUrl) {
        Client client = Client.create();
        WebResource webResource = client.resource(remoteUrl);
        ObjectMapper objMapper = new ObjectMapper();
        Party party = new Party(newUserId.getValue().toString(), newUserName.getValue().toString());
        String partyJson = null;
        try {
            partyJson = objMapper.writeValueAsString(party);
            System.out.println(partyJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        webResource.type("application/json").post(ClientResponse.class, partyJson);
    }

    public void setUserName(RichInputText userName) {
        this.userName = userName;
    }

    public RichInputText getUserName() {
        return userName;
    }

    public static void main(String[] args) {
        System.out.println("TestApp");
    }

    public void setUserId(RichInputText userId) {
        this.userId = userId;
    }

    public RichInputText getUserId() {
        return userId;
    }

    public String createParty() {
        callPostMethod(remoteUrl);
        return "StayOnTheSamePage";
    }

    public void setNewUserName(RichInputText newUserName) {
        this.newUserName = newUserName;
    }

    public RichInputText getNewUserName() {
        return newUserName;
    }

    public void setNewUserId(RichInputText newUserId) {
        this.newUserId = newUserId;
    }

    public RichInputText getNewUserId() {
        return newUserId;
    }
}
