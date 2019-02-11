package eu.mapidev.predsys.repository;

import eu.mapidev.predsys.domain.AbstractDraw;
import java.time.LocalDateTime;
import java.util.List;

public interface AbstractDrawRepository {

    public List<AbstractDraw> findAllByOrderByDateAsc();

    public List<AbstractDraw> findFirstByOrderByDateAsc();

    public AbstractDraw findByDate(LocalDateTime date);

    public AbstractDraw save(AbstractDraw draw);

}
