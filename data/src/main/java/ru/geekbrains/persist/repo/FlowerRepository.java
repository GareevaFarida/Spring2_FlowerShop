package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.Flower;

import java.util.Optional;

public interface FlowerRepository extends JpaRepository<Flower, Long> {

    Optional<Flower> findFlowerByName(String name);

}
