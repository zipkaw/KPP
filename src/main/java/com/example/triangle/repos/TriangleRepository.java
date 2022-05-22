package com.example.triangle.repos;


import com.example.triangle.Triangle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface TriangleRepository extends CrudRepository <Triangle, Long> {
    List <Triangle> findByFirstSide(Integer FirstSide);
    Triangle findById(long id);
    @Query("SELECT coalesce(max(e.id), 0) FROM Triangle e")
    Long getMaxId();
}
