/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.user;

import blackboard.persist.DbLoaderFactory;
import blackboard.persist.PersistenceException;
import blackboard.persist.user.UserDbLoader;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.user.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author pfgray
 */
@Service
public class BbUserService implements UserService {

    @Override
    public User getUserForId(String id) {
        try {
            UserDbLoader userDbLoader = UserDbLoader.Default.getInstance();
            return new BbUser(userDbLoader.loadById(BlackboardUtilities.getIdFromPk(id, blackboard.data.user.User.class)));
        } catch (PersistenceException ex) {
            Logger.getLogger(BbUserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public User getUserForUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
