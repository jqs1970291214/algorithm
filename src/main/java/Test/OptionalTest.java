package Test;

import javax.security.auth.Subject;
import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * summary
 * descriptions
 *
 * @author Junqson
 * @date 2018/11/13 14:11
 */
public class OptionalTest {
    public static void main(String[] args) throws Exception {

        User user = new User();
        Optional<User> optional = Optional.ofNullable(user);
        System.out.println(optional);
        System.out.println(optional.map((user1 -> user1.getId())));
        System.out.println(optional.flatMap(user1 -> Optional.ofNullable(user1.getId())));
        Optional<String> str = Optional.ofNullable("as");
        str.orElseThrow(() -> new Exception("sorry, this str is empty"));

    }



}



class User {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}