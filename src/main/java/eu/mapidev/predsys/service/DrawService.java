package eu.mapidev.predsys.service;

import eu.mapidev.predsys.domain.AbstractDraw;
import java.time.LocalDateTime;

public interface DrawService {

    AbstractDraw getDraw(LocalDateTime date);

    Iterable<? extends AbstractDraw> getAllDraws();

    AbstractDraw createResult(AbstractDraw abstractDraw);

    AbstractDraw createTicketDraw(AbstractDraw abstractDraw);

    AbstractDraw getLastDraw();
    
    void deleteDraw(AbstractDraw abstractDraw);
}
