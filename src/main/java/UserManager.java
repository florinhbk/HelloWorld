import org.apache.commons.validator.routines.EmailValidator;
import java.util.ArrayList;
import java.util.Collection;

public class UserManager {

    private Collection<String> userStorage;

    public UserManager(){
        userStorage = new ArrayList<String>();
    }

    public boolean addUser(String userEmail) {
        if(!EmailValidator.getInstance().isValid(userEmail)){
            throw new IllegalArgumentException("Not a valid email");
    }
        for(String s: userStorage){
            if(s.equals(userEmail)){
                System.out.println("User already exists");
            }
        }
        return userStorage.add(userEmail);
    }

    public String getUser(String userEmail){
        for(String s : userStorage){
            if(s.equals(userEmail)){
                return s;
            }
        }
    return null;
    }

    public boolean deleteUser(final String userEmail){
        if(userStorage.contains(userEmail))
        {
            userStorage.remove(userEmail);
            return true;
        }
            else return false;
    }
}
