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
public class PasswordHashServiceInsecure implements PasswordHashService {

    public String getHashForPassword(String password) {
        return password;
    }

}
