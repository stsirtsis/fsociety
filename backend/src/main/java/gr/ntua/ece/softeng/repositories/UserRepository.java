package gr.ntua.ece.softeng.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.ntua.ece.softeng.entities.User;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}