/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mapidev.predsys.service;

import java.util.Date;
import eu.mapidev.predsys.domain.Draw;

/**
 *
 * @author marek
 */
public interface DrawService {

    Draw getDraw(Date date);

    Iterable<Draw> getDraws();

    Draw updateDraw(Draw draw);
    
    Draw getLastDraw();
}
