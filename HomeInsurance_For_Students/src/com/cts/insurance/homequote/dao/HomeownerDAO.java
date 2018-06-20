/**
 * This DAO class is used to for Homeowner Information
 *
 * @author Cognizant
 * @contact Cognizant
 * @version 1.0
 */
package com.cts.insurance.homequote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cts.insurance.homequote.exception.HomequoteSystemException;
import com.cts.insurance.homequote.model.Homeowner;
import com.cts.insurance.homequote.util.HomeInsuranceConstants;
import com.cts.insurance.homequote.util.SqlQueries;

public class HomeownerDAO {
	
	private final static Logger LOG = Logger.getLogger(HomeownerDAO.class);

	/**
	 * @param homeowner
	 */
	public void saveHomeowner(final Homeowner homeowner) throws HomequoteSystemException
	{
		LOG.info("HomeownerDAO.saveHomeowner - starts");
		Connection conn = null;
		//Fill code here
	}
	

	/**
	 * @param homeowner
	 */
	public Homeowner getHomeowner(final int quoteId) throws HomequoteSystemException
	{
		LOG.info("HomeownerDAO.getHomeowner - starts");
		Connection conn = null;
		Homeowner homeowner = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		//Fill code here
		return null; //return Object
	}

}
