package litvin.dao.user;

import litvin.model.user.User;

import java.util.List;

public interface UserDao {
    User getUser(String email);

    boolean saveUser(String firstName, String lastName, String email, String role, String password);

    boolean checkPassword(String email, String password);

    void updateUser(User user);

    List<User> getAllUsers();

    List<User> findByName(String firstName, String lastName);

    void updatePassword(User user, String newPassword);
}
