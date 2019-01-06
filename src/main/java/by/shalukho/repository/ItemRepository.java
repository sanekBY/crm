package by.shalukho.repository;

import by.shalukho.entity.CustomerEntity;
import by.shalukho.entity.ItemEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByActiveIsTrueAndId(Long id);

    List<ItemEntity> findAllByActiveIsTrue();

    Optional<ItemEntity> findByName(String name);

    Page<ItemEntity> findAllByActiveIsTrue(@NonNull final Pageable page);
}
