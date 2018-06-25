/*
 * @author Cognizant
 * @contact Cognizant
 * @version 1.0
 */
package com.cts.insurance.homequote.dao;
import java.io.IOException;
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
        PreparedStatement stmt = null;
        //Fill code here
        try {
            final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(HomeInsuranceConstants.MYSQL);
            conn = daoFactory.getConnection();
            stmt = conn.prepareStatement(SqlQueries.SAVE_HOMEOWNER);
            stmt.setInt(1, homeowner.getQuoteId());
            stmt.setString(2, homeowner.getFirstName());
            stmt.setString(3, homeowner.getLastName());
            stmt.setString(4, homeowner.getDob());
            stmt.setString(5, homeowner.getIsRetired());
            stmt.setString(6, homeowner.getSsn());
            stmt.setString(7, homeowner.getEmailAddress());
            stmt.executeUpdate();
            stmt.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    LOG.error("Exception while trying to close Connection : " + e.getMessage());
                }
                LOG.info("HomeownerDAO.saveHomeowner - ends");
            }
        
        
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
        try {
            final AbstractDAOFactory daoFactory = AbstractDAOFactory.getDaoFactory(HomeInsuranceConstants.MYSQL);
            conn = daoFactory.getConnection();
            stmt = conn.prepareStatement(SqlQueries.GET_HOMEOWNER);
            stmt.setInt(1, quoteId);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                homeowner = new Homeowner();
                homeowner.setFirstName(resultSet.getString(1));
                homeowner.setLastName(resultSet.getString(2));
                homeowner.setDob(resultSet.getString("DOB"));
                homeowner.setEmailAddress(resultSet.getString("EMAIL_ADDRESS"));
                homeowner.setIsRetired(resultSet.getString("IS_RETIRED"));
                homeowner.setQuoteId(resultSet.getInt("QUOTE_ID"));
                homeowner.setSsn(resultSet.getString("SSN"));
            }
            stmt.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            try
            {
                resultSet.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException e)
            {
                LOG.error("Exception while trying to close Connection : " + e.getMessage() );
            }
        }
        LOG.info("HomeownerDAO.getHomeowner - ends");
        return homeowner; //return Object
    }
}