package com.epul.oeuvres.controle;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.epul.oeuvres.dao.*;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;


@Controller
public class OeuvreControleur extends MultiActionController {

	private static final String OEUVRE = "Oeuvre/";
	private static final String INSERER_OEUVRE = "insererOeuvre";
	private static final String AJOUTER_OEUVRE = "ajouterOeuvre";
	private static final String MODIFIER_OEUVRE = "modifierOeuvre";
	private static final String LISTER_OEUVRES = "listerOeuvres";
	private static final String RESERVER_OEUVRES ="reserverOeuvres";
	private static final String RESERVER_OEUVRE ="reserverOeuvre";
	private static final String ERROR_PAGE = "/erreur.jsp";

	/**
	 * insertion d'une nouvelle oeuvre vente
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = OEUVRE+INSERER_OEUVRE , method = RequestMethod.POST)
	public ModelAndView insererOeuvre(HttpServletRequest request, HttpServletResponse response) {
		String destinationPage ="";
		try {
			//services dont on aura besoin
			ProprietaireService pService = new ProprietaireService();
			OeuvreService oService = new OeuvreService();
			Oeuvrevente ov = new Oeuvrevente();
			ov.setEtatOeuvrevente("L");

			String expressionR = "^([+]?\\d*\\.?\\d*)$";

			if (!request.getParameter("txtPrix").equals("") && request.getParameter("txtPrix").matches(expressionR)) {
				ov.setPrixOeuvrevente(Float.parseFloat(request.getParameter("txtPrix")));
			}

			Proprietaire proprietaire = new Proprietaire();
			for(Proprietaire p : pService.consulterListeProprietaires())
			{
				if(p.getNomProprietaire().equals(request.getParameter("ddlProp").split(" ")[0]))
				{
					proprietaire = p;
					break;
				}
			}


			ov.setProprietaire(proprietaire);
			ov.setTitreOeuvrevente(request.getParameter("txtTitre"));
			oService.insererOeuvreVente(ov);

		} catch (MonException e)
		{
			request.setAttribute("erreur", e.getMessage());
			destinationPage = ERROR_PAGE;
		}
		destinationPage = "/listerOeuvres";
		return new ModelAndView(destinationPage);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = OEUVRE+AJOUTER_OEUVRE)
	public ModelAndView ajouterOeuvre(HttpServletRequest request, HttpServletResponse response) {
		String destinationPage ="";
		try {

			OeuvreService oService = new OeuvreService();
			ProprietaireService pService = new ProprietaireService();
			request.setAttribute("mesOeuvresReservable", oService.consulterListOeuvresReservables());
			request.setAttribute("mesProprietaires", pService.consulterListeProprietaires());
		} catch (MonException e) {
			e.printStackTrace();
		}

		destinationPage = "/ajouterOeuvre";
		return new ModelAndView(destinationPage);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = OEUVRE+MODIFIER_OEUVRE)
	public ModelAndView modifierOeuvre(HttpServletRequest request, HttpServletResponse response) {
		String destinationPage ="";
		OeuvreService oService = new OeuvreService();
		ProprietaireService pService = new ProprietaireService();

		Oeuvrevente oeuvre = oService.getOeuvre(Integer.parseInt(request.getParameter("txtidoeuvre")));

		oeuvre.setTitreOeuvrevente(request.getParameter("txtoeuvre"));

		String expressionR = "^([+]?\\d*\\.?\\d*)$";

		if (!request.getParameter("txtprixoeuvre").equals("") && 
				request.getParameter("txtprixoeuvre").matches(expressionR)) {
			oeuvre.setPrixOeuvrevente(Float.parseFloat(request.getParameter("txtprixoeuvre")));
		}

		Proprietaire proprietaire = new Proprietaire();
		try {
			for(Proprietaire p : pService.consulterListeProprietaires())
			{
				if(p.getNomProprietaire().equals(request.getParameter("txtpropoeuvre").split(" ")[0]))
				{
					proprietaire = p;
					oeuvre.setProprietaire(proprietaire);
					oService.mettreAJourOeuvreVente(oeuvre);
				}
			}
		} catch (MonException e1) {
			e1.printStackTrace();
		}

		try {
			request.setAttribute("mesOeuvresReservable", oService.consulterListOeuvresReservables());
		} catch (MonException e) {
			request.setAttribute("erreur", e.getMessage());
			destinationPage = ERROR_PAGE;
		}
		destinationPage = "/listerOeuvres";
		return new ModelAndView(destinationPage);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = OEUVRE+LISTER_OEUVRES)
	public ModelAndView listerOeuvres(HttpServletRequest request, HttpServletResponse response) {
		String destinationPage ="";
		try {

			OeuvreService oService = new OeuvreService();
			Service unService = new Service();
			ProprietaireService pService = new ProprietaireService();

			request.setAttribute("mesOeuvresPret", oService.consulterListeOeuvresPret());
			request.setAttribute("mesOeuvresVente", oService.consulterListeOeuvresVentes());
			request.setAttribute("listeAdherents", unService.consulterListeAdherents());
			request.setAttribute("listeProprietaires", pService.consulterListeProprietaires());


		} catch (MonException e) {
			request.setAttribute("erreur", e.getMessage());
			destinationPage = ERROR_PAGE;
		}

		destinationPage = "/listerOeuvres";
		return new ModelAndView(destinationPage);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = OEUVRE+RESERVER_OEUVRES)
	public ModelAndView reserverOeuvres(HttpServletRequest request, HttpServletResponse response) {
		String destinationPage ="";
		try {

			OeuvreService unService = new OeuvreService();
			request.setAttribute("mesOeuvresReservable", unService.consulterListOeuvresReservables());

		} catch (MonException e) {
			request.setAttribute("erreur", e.getMessage());
			destinationPage = ERROR_PAGE;
		}

		destinationPage = "/reserverOeuvre";
		return new ModelAndView(destinationPage);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = OEUVRE+RESERVER_OEUVRE)
	public ModelAndView reserverOeuvre(HttpServletRequest request, HttpServletResponse response) {
		String destinationPage ="";
		OeuvreService oService = new OeuvreService();
		ReservationService rService = new ReservationService();
		Service aService = new Service();

		Oeuvrevente oeuvre = oService.getOeuvre(Integer.parseInt(request.getParameter("txtidoeuvre")));
		Adherent adherent = aService.getAdherent(Integer.parseInt(request.getParameter("selectAd")));

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(request.getParameter("maDate"));
			oeuvre.setEtatOeuvrevente("R");
			oService.mettreAJourOeuvreVente(oeuvre);
			Reservation reservation = new Reservation(date,adherent,oeuvre);
			reservation.setStatut("confirmee");
			rService.insererReservation(reservation);
		} catch (MonException e) {
			request.setAttribute("erreur", e.getMessage());
			destinationPage = ERROR_PAGE;
		} catch (ParseException e) {
			request.setAttribute("erreur", e.getMessage());
			destinationPage = ERROR_PAGE;
		}

		try {
			request.setAttribute("mesOeuvresReservable", oService.consulterListOeuvresReservables());
		} catch (MonException e) {
			request.setAttribute("erreur", e.getMessage());
			destinationPage = ERROR_PAGE;
		}

		destinationPage = "/listerOeuvres";
		return new ModelAndView(destinationPage);
	}

}
