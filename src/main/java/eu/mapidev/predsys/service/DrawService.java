package eu.mapidev.predsys.service;

import eu.mapidev.predsys.domain.AbstractDraw;
import java.util.Date;

public interface DrawService {

    AbstractDraw getDraw(Date date);

    Iterable<? extends AbstractDraw> getAllDraws();

    AbstractDraw updateDraw(AbstractDraw draw);

    AbstractDraw getLastDraw();
}
