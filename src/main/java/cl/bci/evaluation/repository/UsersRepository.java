package cl.bci.evaluation.repository;

import cl.bci.evaluation.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.Instant;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    @Query(value = "SELECT COUNT(*) FROM USERS WHERE EMAIL = :email", nativeQuery = true)
    Integer findUserByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM USERS WHERE EMAIL = :email AND PASSWORD = :password", nativeQuery = true)
    Integer findUserByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM USERS WHERE EMAIL = :email", nativeQuery = true)
    Users findUser(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE USERS SET token = :token, last_login = :date, is_active = 1 WHERE email = :email", nativeQuery = true)
    void updateUser(String token, Instant date , String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO USERS(id, email, password, created) VALUES(:id,:email,:pass, :date)", nativeQuery = true)
    void insertUser(String id, String email, String pass, Instant date);

}
