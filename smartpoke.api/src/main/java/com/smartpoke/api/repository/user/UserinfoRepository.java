package com.smartpoke.api.repository.user;


import com.smartpoke.api.model.users.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo, Long> {
}
