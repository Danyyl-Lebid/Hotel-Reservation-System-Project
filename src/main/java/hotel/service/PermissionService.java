package hotel.service;

import hotel.model.User;
import hotel.model.UserRole;
import hotel.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PermissionService {

    private final UserRepository userRepository;

    public PermissionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean isAdmin(long id){
        User user = userRepository.findUserById(id);
        return Objects.equals(UserRole.ADMINISTRATOR, user.getRole());
    }

    public void checkPermission(long userId) {
        if (!isAdmin(userId)) {
            throw new RuntimeException("No permission for this operation. User isn't admin");
        }
    }

}
