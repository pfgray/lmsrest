/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author paul
 */
@Controller
public class MockRestController {
    
    @RequestMapping(value = "/")
    public String getCurrentUser() {
        return "welcome";
    }
}
