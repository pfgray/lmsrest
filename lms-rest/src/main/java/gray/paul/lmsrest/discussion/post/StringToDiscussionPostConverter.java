/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.post;

import gray.paul.lmsdata.discussion.DiscussionBoard;
import gray.paul.lmsdata.discussion.DiscussionPost;
import gray.paul.lmsdata.discussion.DiscussionService;
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