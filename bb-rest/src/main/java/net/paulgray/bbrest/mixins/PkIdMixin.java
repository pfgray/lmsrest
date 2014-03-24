/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.bbrest.mixins;

import blackboard.persist.Container;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author pgray
 */
public abstract class PkIdMixin {
    
    @JsonIgnore
    public abstract void setContainer(Container container);
    @JsonIgnore
    public abstract Container getContainer();
    
}
