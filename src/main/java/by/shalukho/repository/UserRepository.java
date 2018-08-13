package by.shalukho.repository;

import by.shalukho.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDbo, Long> {
}
