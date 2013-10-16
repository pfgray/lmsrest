/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.thread;

import gray.paul.lmsdata.discussion.DiscussionBoard;
import gray.paul.lmsdata.discussion.DiscussionThread;
import gray.paul.lmsdata.discussion.DiscussionService;
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