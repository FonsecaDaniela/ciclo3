/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.retociclo3.ciclo3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniela fonseca
 */
@Service
public class ServiciosReservaciones {
@Autowired
    private RepositorioReservaciones metodosCrud;
	    
    public List<Reservaciones> getAll(){
    return metodosCrud.getAll();
    }
	
    public Optional<Reservaciones> getReservation(int reservationId) {
    return metodosCrud.getReservation(reservationId);
	    }
	
	    public Reservaciones save(Reservaciones reservations){
	        if(reservations.getIdReservation()==null){
	            return metodosCrud.save(reservations);
	        }else{
	            Optional<Reservaciones> e= metodosCrud.getReservation(reservations.getIdReservation());
	            if(e.isEmpty()){
	                return metodosCrud.save(reservations);
	            }else{
	                return reservations;
	            }
	        }
	    }
            
            public Reservaciones update(Reservaciones reservaciones){
	        if(reservaciones.getIdReservation()!=null){
	            Optional<Reservaciones> e= metodosCrud.getReservation(reservaciones.getIdReservation());
	            if(!e.isEmpty()){
	
	                if(reservaciones.getStartDate()!=null){
	                    e.get().setStartDate(reservaciones.getStartDate());
	                }
	                if(reservaciones.getDevolutionDate()!=null){
	                    e.get().setDevolutionDate(reservaciones.getDevolutionDate());
	                }
	                if(reservaciones.getStatus()!=null){
	                    e.get().setStatus(reservaciones.getStatus());
	                }
	                metodosCrud.save(e.get());
	                return e.get();
	            }else{
	                return reservaciones;
	            }
	        }else{
	            return reservaciones;
	        }
	    }
            public boolean deleteReservations(int reservationsId) {
	        Boolean aBoolean = getReservation(reservationsId).map(reservations -> {
	            metodosCrud.delete(reservations);
	            return true;
	        }).orElse(false);
	        return aBoolean;
	    }
}
