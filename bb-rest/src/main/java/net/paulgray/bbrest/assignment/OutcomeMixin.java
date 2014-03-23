/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.assignment;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author pgray
 */
public abstract class OutcomeMixin {

    @JsonIgnore
    public abstract boolean getHideAttempt(); // we don't need it!

}
