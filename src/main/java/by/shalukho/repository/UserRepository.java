package by.shalukho.repository;

import by.shalukho.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<UserEntity> {
    Optional<UserEntity> findByLogin(String login);
}
