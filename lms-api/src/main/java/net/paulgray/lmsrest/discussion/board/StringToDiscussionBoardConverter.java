/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.discussion.board;

import net.paulgray.lmsrest.discussion.DiscussionBoard;
import net.paulgray.lmsrest.discussion.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author pfgray
 */
public class StringToDiscussionBoardConverter implements Converter<String, DiscussionBoard>{

    @Autowired
    DiscussionService discussionService;
    
    public DiscussionBoard convert(String source) {
        return discussionService.getDiscussionBoardForId(source);
    }
    
}
