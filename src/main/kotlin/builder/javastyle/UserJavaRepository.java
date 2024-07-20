package builder.javastyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserJavaRepository {
    private final List<UserJava> users = new ArrayList<>();
    private Long id = 0L;

    public UserJava createUser(UserJava user) {
        user.id = id++;
        users.add(user);
        return user;
    }

    public Optional<UserJava> getUser(long id) {
        return users.stream().filter(user -> user.id.equals(id)).findFirst();
    }

    public List<UserJava> getUsers() {
        return users;
    }
}