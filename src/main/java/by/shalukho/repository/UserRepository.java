package by.shalukho.repository;

import by.shalukho.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByActiveIsTrueAndId(Long id);

    List<UserEntity> findAllByActiveIsTrue();
}
