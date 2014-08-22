package com.obpdemo.model;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import oracle.jbo.RowIterator;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;

import oracle.security.idm.IMException;

public class PartyVOImpl extends ViewObjectImpl {
    
    public PartyVOImpl() {}

    protected void executeQueryForCollection(Object qc, Object[] params, int noUserParams) {        
        super.executeQueryForCollection(qc, params, noUserParams);
    }

    int pos = 0;
    protected ViewRowImpl createRowFromResultSet(Object qc, ResultSet rs) {
        ViewRowImpl r = createNewRowForCollection(qc);
        populateAttributeForRow(r, 0, "This is Testing");
        setFetchCompleteForCollection(qc, true);
        pos++;
        return r; 
    }

    protected boolean hasNextForCollection(Object qc) {
        return pos<=0;
    }
    
}
