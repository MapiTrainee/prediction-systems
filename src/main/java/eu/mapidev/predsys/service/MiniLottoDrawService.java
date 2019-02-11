package eu.mapidev.predsys.service;

import eu.mapidev.predsys.repository.AbstractDrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("MiniLottoServ")
public class MiniLottoDrawService extends AbstractDrawService {

    @Autowired
    public MiniLottoDrawService(@Qualifier("MiniLottoRep") AbstractDrawRepository drawRepository) {
	super(drawRepository);
    }

}
