/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.board;

import gray.paul.lmsdata.discussion.DiscussionBoard;
import gray.paul.lmsdata.discussion.DiscussionService;
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
