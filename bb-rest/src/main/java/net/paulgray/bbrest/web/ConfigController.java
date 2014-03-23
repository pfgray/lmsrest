/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.bbrest.web;

import blackboard.platform.monitor.system.JvmInfo;
import blackboard.platform.monitor.system.OsInfo;
import blackboard.platform.monitor.system.SystemInfoService;
import blackboard.platform.monitor.system.SystemInfoServiceFactory;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pgray
 */
@Controller
public class ConfigController {
    
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity configView(){
        
        final SystemInfoService bbInfo = SystemInfoServiceFactory.getInstance();
        
        return new ResponseEntity (new Object(){
            public JvmInfo jvmInfo = bbInfo.getJvmInfo();
            public OsInfo osInfo = bbInfo.getOsInfo();
            public String app="BbRest";
            public String version="1.0.0-SNAPSHOT";
            public Date time = new Date();
        },HttpStatus.OK);
    }
    
}
