package BackingBean;


import com.obpdemo.model.Party;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.codehaus.jackson.map.ObjectMapper;


public class Helper {

    public Helper() {
        super();
    }
    
    public void showPartyDetails(String viewName, String partyAsJson) {     
          BindingContext ctx = BindingContext.getCurrent();
          DCBindingContainer bc = (DCBindingContainer)ctx.getCurrentBindingsEntry();
          DCIteratorBinding iterator = bc.findIteratorBinding(viewName);
          
          ViewObject userView = iterator.getViewObject();
          Row userRow = userView.createRow();
          Party party = convertToObject(partyAsJson);
          userRow.setAttribute("id", party.getId());
          userRow.setAttribute("name",party.getName());
          userView.insertRow(userRow); 
      }
    
    private Party convertToObject(String partyAsJson){
        ObjectMapper objMapper = new ObjectMapper();
        Party party = null;
        try {
            party = objMapper.readValue(partyAsJson, Party.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return party;
    }
}
