/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.bbrest.assignment;

import blackboard.data.gradebook.impl.OutcomeDefinition;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author paul
 */
public class BbOutcomeDefinition extends OutcomeDefinition{
    
    @Override
    @JsonIgnore
    public boolean getHideAttempt(){
        return this.getHideAttempt();
    }
    
    
}
