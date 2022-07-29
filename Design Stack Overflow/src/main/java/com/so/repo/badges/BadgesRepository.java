package com.so.repo.badges;

import com.so.models.Badges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgesRepository extends JpaRepository<Badges, Long>{
}
