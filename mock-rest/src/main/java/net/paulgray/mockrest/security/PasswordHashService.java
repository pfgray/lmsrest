/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest.security;

/**
 *
 * @author pgray
 */
public interface PasswordHashService {
    
    public static String SALT = "LmsRestSalt";
    
    public String getHashForPassword(String password);
    
}
