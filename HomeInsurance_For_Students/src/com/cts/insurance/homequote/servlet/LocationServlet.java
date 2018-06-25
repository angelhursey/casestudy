/**
 * Servlet for capturing Location Information
 * 
 * @author Cognizant
 * @contact Cognizant
 * @version 1.0
 */
package com.cts.insurance.homequote.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cts.insurance.homequote.bo.HomeownerBO;
import com.cts.insurance.homequote.bo.LocationBO;
import com.cts.insurance.homequote.exception.HomequoteSystemException;
import com.cts.insurance.homequote.model.Homeowner;
import com.cts.insurance.homequote.model.Location;
import com.cts.insurance.homequote.model.User;
import com.cts.insurance.homequote.util.HomeInsuranceConstants;

public class LocationServlet extends HttpServlet{
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Executes the action and returns the chosen renderer
	 *
	 * @param req Http Request
	 * @param req Http Response
	 * @return renderer chosen based on the result of saving a product
	 * @throws Throwable if exception occurs while saving the product
	 */
	public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,IOException {
		final Logger logger = Logger.getLogger(this.getClass().getName());
		try {
			final HttpSession session = request.getSession();
			//Fill code here
			if(session.getAttribute("location") == null)
			{
				//Get Request Parameters and set it to the data object
				final Location location = new Location();
				location.setResidenceType(request.getParameter(HomeInsuranceConstants.RESIDENCE_TYPE));
				location.setAddressLine1(request.getParameter(HomeInsuranceConstants.ADDRESS_LINE_1));
				location.setAddressLine2(request.getParameter(HomeInsuranceConstants.ADDRESS_LINE_2));
				location.setCity(request.getParameter(HomeInsuranceConstants.CITY));
				location.setResidenceUse(request.getParameter(HomeInsuranceConstants.RESIDENCE_USE));
				location.setState(request.getParameter(HomeInsuranceConstants.STATE));
				location.setZip(request.getParameter(HomeInsuranceConstants.ZIP));
				
				
				
				if(session.getAttribute("user") != null)
				{
					final User user = (User)session.getAttribute("user");
					location.setUserName(user.getUserName());
				}
				else
				{
					String message = "Username not retrieved after registration page in method LocationServlet.doPost";
					logger.error(message);
					throw new HomequoteSystemException(message);
				}
				final LocationBO locationBo = new LocationBO();
				locationBo.saveHomeLocation(location);
				session.setAttribute("location", location);
				
			}
			final RequestDispatcher dispatcher = request.getRequestDispatcher(HomeInsuranceConstants.HOMEOWNER_INFO);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			//Fill code here
				logger.error("Exception occurred in method LocationServlet.doPost :: " + 
						e);
				request.setAttribute("message", e.getMessage());
				final RequestDispatcher dispatcher = request.getRequestDispatcher(HomeInsuranceConstants.ERROR);
				dispatcher.forward(request, response);
			
		}
	}
}