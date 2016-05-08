package com.epul.oeuvres.controle;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.epul.oeuvres.dao.OeuvreService;
import com.epul.oeuvres.dao.ReservationService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;

@Controller
public class ReservationControleur extends MultiActionController {
	
	private static final long serialVersionUID = 1L;
	public static final String RESERVATION = "Reservation/";
	private static final String LISTE_RESERVATION = "listerReservations";
	private static final String SUPPRIMER_RESERVATION = "supprimerReservation";


	@RequestMapping(value = RESERVATION+LISTE_RESERVATION)
	public ModelAndView listerLesReservations(HttpServletRequest request, HttpServletResponse response) {
		String destinationPage ="";
		ReservationService unService = new ReservationService();
		try {
			request.setAttribute("listeReservations", unService.consulterListeReservation());
		} catch (MonException e) {
			e.printStackTrace();
		}

		destinationPage = "/listeReservations";
		return new ModelAndView(destinationPage);
	}
	
	@RequestMapping(value = RESERVATION+SUPPRIMER_RESERVATION)
	public ModelAndView supprimerReservation(HttpServletRequest request, HttpServletResponse response) {
		
		String destinationPage ="";
		ReservationService unService = new ReservationService();
		
			int idOeuvre = Integer.parseInt(request.getParameter("txtid"));
			int idAd = Integer.parseInt(request.getParameter("txtadherent"));
			
			try {
				unService.supprimerReservation(idOeuvre,idAd);

			} catch (MonException e) {
				e.printStackTrace();
			}
			destinationPage = "/Oeuvre/listerOeuvres";
		return new ModelAndView(destinationPage);	
	}

}
