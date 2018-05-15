package lokalspots.auth.services;

import lokalspots.auth.models.User;

public interface UserService {
    void saveMember(User user);

    User findByUsername(String username);
}