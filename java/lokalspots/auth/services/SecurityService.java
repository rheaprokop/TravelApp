package lokalspots.auth.services;

public interface SecurityService {
    String findLoggedInUsername();

    boolean autologin(String username, String password);
}