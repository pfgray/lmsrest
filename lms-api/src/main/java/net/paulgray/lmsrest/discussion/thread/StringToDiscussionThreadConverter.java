/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.discussion.thread;

import net.paulgray.lmsrest.discussion.DiscussionBoard;
import net.paulgray.lmsrest.discussion.DiscussionThread;
import net.paulgray.lmsrest.discussion.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author pfgray
 */
public class StringToDiscussionThreadConverter implements Converter<String, DiscussionThread>{

    @Autowired
    DiscussionService discussionService;
    
    public DiscussionThread convert(String source) {
        return discussionService.getDiscussionThreadForId(source);
    }
    
}