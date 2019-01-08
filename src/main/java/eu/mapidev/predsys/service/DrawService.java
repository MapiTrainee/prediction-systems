package eu.mapidev.predsys.service;

import eu.mapidev.predsys.domain.AbstractDraw;
import java.time.LocalDateTime;

public interface DrawService {

    AbstractDraw getDraw(LocalDateTime date);

    Iterable<? extends AbstractDraw> getAllDraws();

    AbstractDraw updateDraw(AbstractDraw draw);

    AbstractDraw getLastDraw();
}
