/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.web;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author pfgray
 */
public class NoContextUserException extends AuthenticationException {

    public NoContextUserException(String message) {
        super(message);
    }
    
}
