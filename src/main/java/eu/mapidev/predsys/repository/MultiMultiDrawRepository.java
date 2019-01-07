package eu.mapidev.predsys.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import eu.mapidev.predsys.domain.MultiMultiDraw;

@Repository
public interface MultiMultiDrawRepository extends CrudRepository<MultiMultiDraw, Date> {
    
    public List<MultiMultiDraw> findAllByOrderByDateAsc();
    
    public List<MultiMultiDraw> findFirstByOrderByDateDesc();
    
}
