package eu.mapidev.predsys.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import eu.mapidev.predsys.domain.MultiMultiDraw;
import java.time.LocalDateTime;

@Repository
public interface MultiMultiDrawRepository extends CrudRepository<MultiMultiDraw, LocalDateTime> {
    
    public List<MultiMultiDraw> findAllByOrderByDateAsc();
    
    public List<MultiMultiDraw> findFirstByOrderByDateDesc();
    
}
