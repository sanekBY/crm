package by.shalukho.repository;

import by.shalukho.dbo.UserEntity;

public interface UserRepository extends AbstractRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}
