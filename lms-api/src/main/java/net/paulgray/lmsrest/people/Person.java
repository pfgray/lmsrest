/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.lmsrest.people;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author paul
 */
@Controller
@RequestMapping(value = "/enrollments")
public class Person {
    
    public String id;
    public String given_name;
    public String family_name;
    public String username;
    public String email;
    public String role;
    
}
