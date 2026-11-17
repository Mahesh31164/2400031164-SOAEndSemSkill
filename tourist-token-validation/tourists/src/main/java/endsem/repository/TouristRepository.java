package insem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insem.model.Tourist;


@Repository
public interface TouristRepository
        extends JpaRepository<Tourist, Long> {
}