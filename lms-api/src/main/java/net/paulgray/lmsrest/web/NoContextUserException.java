/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.web;

/**
 *
 * @author pfgray
 */
public class NoContextUserException extends Exception{

    public NoContextUserException(String message) {
        super(message);
    }
    
}
