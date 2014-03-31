/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest;

import blackboard.persist.DataType;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pfgray
 */
public class BlackboardUtilities {
    
    public static Id getIdFromPk(String externalId, Class clazz){
        try {
            return Id.generateId(new DataType(clazz), externalId);
        } catch (PersistenceException ex) {
            Logger.getLogger(BlackboardUtilities.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
