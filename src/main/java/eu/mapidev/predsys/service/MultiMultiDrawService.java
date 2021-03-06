package eu.mapidev.predsys.service;

import eu.mapidev.predsys.repository.AbstractDrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("MultiMultiServ")
public class MultiMultiDrawService extends AbstractDrawService {

    @Autowired
    public MultiMultiDrawService(@Qualifier("MultiMultiRep") AbstractDrawRepository drawRepository) {
	super(drawRepository);
    }

}
