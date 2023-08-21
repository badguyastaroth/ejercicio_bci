package cl.bci.repository;

import cl.bci.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Query(value = "INSERT INTO PHONE(number, city_code, country_code, id) VALUES(:number,:cityCode,:countryCode,:uuid)", nativeQuery = true)
    void insertPhone(Long number, Integer cityCode, String countryCode, String uuid);

    @Query(value = "SELECT * FROM PHONE WHERE id = :uuid", nativeQuery = true)
    List<Phone> findAllByUserId (String uuid);
}
