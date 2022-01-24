package ru.java.projectfinal.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.java.projectfinal.db.entity.Crypto;

public interface CryptoDBRepository extends JpaRepository<Crypto, Long> {
    @Query(value = "select c from Crypto c where c.name = :name and c.time = :time")
    Crypto findByNameAndTime(@Param("name") String coin, @Param("time") String time);
}
