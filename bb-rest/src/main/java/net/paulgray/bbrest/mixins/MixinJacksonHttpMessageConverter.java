/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.mixins;

import java.util.Set;
import org.reflections.Reflections;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 *
 * @author pgray
 */
public class MixinJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public MixinJacksonHttpMessageConverter(String[] basePackages) {
        for (String pkg: basePackages){
            Reflections reflections = new Reflections(pkg);
            Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(AutoMixin.class);
            
            for(Class<?> mixin : annotated){
                AutoMixin am = mixin.getAnnotation(AutoMixin.class);
                for(Class toMix : am.value()){
                    this.getObjectMapper().addMixInAnnotations(toMix, mixin);
                }
            }
        }

         /*
         for (Map.Entry<Class, Class> entry : mixins.entrySet()) {
         Class key = entry.getKey();
         Class value = entry.getValue();
         this.getObjectMapper().getSerializationConfig().addMixInAnnotations(key, value);
         }
         */
    }

}
