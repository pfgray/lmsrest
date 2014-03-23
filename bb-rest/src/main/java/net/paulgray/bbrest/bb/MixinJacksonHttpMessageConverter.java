/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.bbrest.bb;

import java.util.Map;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

/**
 *
 * @author pgray
 */
public class MixinJacksonHttpMessageConverter extends MappingJacksonHttpMessageConverter{
    
    public MixinJacksonHttpMessageConverter(Map<Class, Class> mixins){
        for (Map.Entry<Class, Class> entry : mixins.entrySet()) {
            Class key = entry.getKey();
            Class value = entry.getValue();
            this.getObjectMapper().getSerializationConfig().addMixInAnnotations(key, value);
        }
    }
    
    
}
