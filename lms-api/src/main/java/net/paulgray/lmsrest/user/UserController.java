/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.user;

import net.paulgray.lmsrest.web.ContextUser;
import net.paulgray.lmsrest.web.LmsRestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pfgray
 */
@Controller
public class UserController {
    
    public static final String PATH = "user";

    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/" + LmsRestConstants.API_PREFIX + "/" + UserController.PATH, method = RequestMethod.GET)
    public ResponseEntity getCurrentUser(@ContextUser User user) {
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
