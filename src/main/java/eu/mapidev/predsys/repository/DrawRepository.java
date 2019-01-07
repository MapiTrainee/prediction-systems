package eu.mapidev.predsys.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import eu.mapidev.predsys.domain.Draw;

@Repository
public interface DrawRepository extends CrudRepository<Draw, Date> {
    
    public List<Draw> findAllByOrderByDateAsc();
    
    public List<Draw> findFirstByOrderByDateDesc();
    
}
