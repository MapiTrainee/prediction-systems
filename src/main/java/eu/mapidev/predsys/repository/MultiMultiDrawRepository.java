package eu.mapidev.predsys.repository;

import eu.mapidev.predsys.domain.AbstractDraw;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import eu.mapidev.predsys.domain.MultiMultiDraw;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Qualifier("MultiMultiRep")
public interface MultiMultiDrawRepository extends AbstractDrawRepository, JpaRepository<MultiMultiDraw, Long> {

    @Override
    public default AbstractDraw save(AbstractDraw draw) {
	return save((MultiMultiDraw) draw);
    }
    
    
}
