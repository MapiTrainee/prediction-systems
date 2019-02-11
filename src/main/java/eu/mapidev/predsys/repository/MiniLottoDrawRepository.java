package eu.mapidev.predsys.repository;

import eu.mapidev.predsys.domain.AbstractDraw;
import eu.mapidev.predsys.domain.MiniLottoDraw;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("MiniLottoRep")
public interface MiniLottoDrawRepository extends AbstractDrawRepository, CrudRepository<MiniLottoDraw, Long> {

    @Override
    public default AbstractDraw save(AbstractDraw draw) {
	return save((MiniLottoDraw) draw);
    }

}
