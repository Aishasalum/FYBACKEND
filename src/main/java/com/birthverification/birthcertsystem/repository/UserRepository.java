//package com.birthverification.birthcertsystem.repository;
//
//import com.birthverification.birthcertsystem.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    // Kutafuta user kwa kutumia email (kwa ajili ya login au kuangalia duplicates)
//    Optional<User> findByEmail(String email);
//
//    // Kuangalia kama user yupo kwa kutumia email
//    boolean existsByEmail(String email);
//}


package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Kutafuta user kwa kutumia email
    Optional<User> findByEmail(String email);

    // Kuangalia kama email tayari imetumika
    boolean existsByEmail(String email);

    // Active users only (wale ambao hawajafutwa)
    List<User> findByDeletedFalse();

    // Deleted users only (wale walioko kwenye Recycle Bin)
    List<User> findByDeletedTrue();

    // Tafuta user kwa ID ikiwa hajafutwa
    Optional<User> findByIdAndDeletedFalse(Long id);
}
