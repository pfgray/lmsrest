/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.discussion.post;

import net.paulgray.lmsrest.discussion.DiscussionBoard;
import net.paulgray.lmsrest.discussion.DiscussionPost;
import net.paulgray.lmsrest.discussion.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author pfgray
 */
public class StringToDiscussionPostConverter implements Converter<String, DiscussionPost>{

    @Autowired
    DiscussionService discussionService;
    
    public DiscussionPost convert(String source) {
        return discussionService.getDiscussionPostForId(source);
    }
    
}