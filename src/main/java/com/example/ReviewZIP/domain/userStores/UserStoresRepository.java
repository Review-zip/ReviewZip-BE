package com.example.ReviewZIP.domain.userStores;

import com.example.ReviewZIP.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoresRepository extends JpaRepository<UserStores, Long> {
    List<UserStores> findAllByUser (Users user);

    Boolean existsUserStoresByLatitudeAndLongitudeAndUser (String latitude, String longitude, Users user);

    void deleteByIdAndUser(Long id, Users user);
}
