package com.i2i.netbankingApplication.service;

import java.util.List;

import com.i2i.netbankingApplication.constantVariableUtil.ConstantVariableUtil;
import com.i2i.netbankingApplication.dao.CustomerDao;
import com.i2i.netbankingApplication.exception.CustomerDataException;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.model.Role;
import com.i2i.netbankingApplication.model.UserRole;
import com.i2i.netbankingApplication.util.StringUtil;

/**
 * <p>
 *     When request comes from customerController. customer service performs add or fetch or fetchAll customer with model(customer),
 *     DAO(customer) and return the responses to customerController.
 *     CustomerService operate passing value's to CustomerDao based on requset's from CustomerController.
 *     It handles the customerDataException.
 *     It service validate the business logics using ConstantVariableUtil class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03.
 *
 */
public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();
    
    /**
     * <p> 
     *     Get the customer object from customerController and pass to addCustomer method in customerDao.
     *     Calculate the customer age from customer DOB calculateAge method in StringUtil Class.
     *     To verify the customer attributes.
     *     It method validate the business logics using ConstantVariableUtil class.
     * </p>
     * 
     * @param customerAge
     *     Age of customer to be add.
     * 
     * @param customer
     *     Object of customer model class. It object used for add Customer.
     * 
     * @throws customerDataException
     *     If there is an error in the customer attribute exception is handle by customerDataException.
     * 
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     * @throws ConfigurationException 
     */
    public void getCustomer(Customer customer) throws DataBaseException, CustomerDataException {
    	String accountNumer = customer.getAccountNumber();
    	Account account = customerDao.retrieveAccountByNumber(accountNumer);
    	//verify the Customer account number validate or not.
    	if (account == null) {
    		throw new CustomerDataException("YOUR ACCOUNT NUMBER IS NOT VALID");  
    	}
    	//verify the Customer details already register or not.
    	if (account.getCustomer() != null) {
    		throw new CustomerDataException("YOUR ALREADY REGISTER THE NETBANKING..."); 
    	}
    	//verify the Customer DOB valid or not
    	String dateOBirth = customer.getDob();
        if (StringUtil.isValidFormat(dateOBirth)) {
            throw new DataBaseException("YOUR FORMAT" + dateOBirth +
                "FORMAT MUST 1/05/2000.INSERT VALID DOB..!!");  
        }
        
    	String customerDOB = customer.getDob();
    	//verify the Customer DOB valid or not
        if (StringUtil.isValidFormat(customerDOB)) {
            throw new DataBaseException("YOUR FORMAT" + customerDOB +
                "FORMAT MUST 1/05/2000.INSERT VALID DOB..!!");  
        }
        
        int customerAge = StringUtil.calculateAge(customerDOB);
        //verify the customer age valid limit or not
        if (customerAge > ConstantVariableUtil.maxAgeLimit) {
        	throw new CustomerDataException("YOUR AGE IS NOT VALID");  
        }
        String customerId = "CUSI2I00" + String.valueOf(getLastCustomerId() + 1);
        customerDao.insertUser(customer.getAccountNumber(), new Customer(customerId, customer.getName(), customerAge, customerDOB, 
            customer.getGender(), customer.getMobileNumber(), customer.getEmail(), "i2i" + String.valueOf((int)(Math.random()*9000)), accountNumer, "ACTIVE"));
        insertUserRole(customerId, "1");
    }
    
    /**
     * <p>
     *     This method Calculate the last address id. 
     *     return to the last address Id.
     * </p>
     * 
     * @return id
     *     return to the last customer address Id.
     *     
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     * @throws ConfigurationException 
     */
	public int getLastAddressId() throws DataBaseException {
    	int newAddressId = ConstantVariableUtil.initializeVariable;
    	for (Address address : customerDao.retriveAllAddresses()) {
    		int lastAddressId = address.getAddressId();
    		if (newAddressId <= lastAddressId) {
    			newAddressId = lastAddressId + 1;
    		}
    	}
    	return newAddressId;
    }
    
	/**
	 * <p>
     *     This method Calculate the last customer id. 
     *     return to the last customer Id.
     * </p>
     * 
	 * @return id
	 *     return to the last customer id value.
	 *     
	 * @throws DataBaseException ConfigurationException
	 *     If there is an error in the given data like BadElementException.
	 * @throws ConfigurationException 
	 * @throws NumberFormatException 
	 */
    public int getLastCustomerId() throws DataBaseException {
    	int id = ConstantVariableUtil.initializeVariable;
        for (Customer customer : customerDao.retriveAllCustomer()) {
    		String lastCustomerId = customer.getCustomerId();
    		int temp = Integer.parseInt(lastCustomerId.substring(6, lastCustomerId.length()));
            if (id <= temp) {
            	id = temp;
            }
    	}
   		return id;
   	}
    
    /**
     * Retrieves all customers from CustomerDao.
     * 
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     *          ConfigurationException
     * @return List
     *     Return list of customers.
     * @throws ConfigurationException 
     */
    public List<Customer> getAllCustomer() throws DataBaseException {
    	return customerDao.retriveAllCustomer();
	}
    
    /**
     * <p>
     *     This method get the customer address from Customer controller. 
     *     pass to customer address addAddress method in customerDao.
     * </p>
     * 
     * @param address
     *     Object of Address model class.It object used for add Customer Address.
     *     
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     * @throws ConfigurationException 
     */ 
	public String getAddress(Address address) throws DataBaseException {
	    return customerDao.addAddress("CUSI2I00" + String.valueOf(getLastCustomerId()), new Address(getLastAddressId(), address.getStreet(),
	        address.getCountry(), address.getCity(), address.getState() ,address.getPincode()));
    }
    
	/**
     * <p> 
     *     Get the customer Id from CustomerController.
     *     It is passed to retrieveCustomerById method in CustomerDao and 
     *     returns customer object to CustomerController.
     * </p>
     * 
     * @param customerId ConfigurationException
     *     Id of Customer.
     * 
     * @return customerController
     *     Return to the object of Customer class. 
     * 
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
	 * @throws ConfigurationException 
	 */
	public Customer getCustomerById(String customerId) throws DataBaseException {
        return customerDao.retrieveCustomerById(customerId); 
    }
	
	/**
	 * <p> 
     *     Get the address Id from customerController.
     *     It is passed to retrieveAddressById method in customerDao and 
     *     returns address object to customerController.
     * </p>
     * 
	 * @param addressId
	 *     Id of Address.
	 *     
	 * @return customerController
     *     Return to the object of Address class. 
     *      ConfigurationException
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 * @throws ConfigurationException 
	 */
	public Address getAddressById(int addressId) throws DataBaseException {
	    return customerDao.retrieveAddressById(addressId);
	}
    
	/**
	 * <p>
	 *    Get the customer Id and roleId from customerController.
     *    It is called to insertRole method in customerDao.
     * </p> ConfigurationException
	 * 
	 * @param customerId
	 *     id of Customer.
	 *     
	 * @param roleId
	 *     id of ROle.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 * @throws ConfigurationException 
	 */
	public void insertUserRole(String customerId, String roleId) throws DataBaseException {
		Customer customer = customerDao.retrieveCustomerById(customerId);
		//verify the customer null 0r not.
		if (customer != null) {
			Role role = customerDao.retrieveRoleById(roleId);
			//verify the user already exist or not
			if (IfUserRoleExist(customerId, roleId)) {
			    customerDao.insertRole(new UserRole(getUserRoleId(), customer, role));
			} else {
				throw new DataBaseException("ALREADY CUSTOMER ASSINED SAME ROLE");
			}
		} else {
			throw new DataBaseException("Please enter the valid userId");
		}
	}    
	/**
	 * <p>
	 *     If request comes CustomerController, it will calling to retriveAllRole method in customerDao.
	 *     Return to the lists of role.
     * </p>
     * 
	 * @return CustomerController
     *     Return to the lists of role.
	 * 
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public List<Role>getAllRole() throws DataBaseException {
		return customerDao.retriveAllRole();
	}
    
	/**
	 * <p>
	 *     If role size zero return false otherwiseConfigurationException true.
	 *     It method called to getAllRole method in CustomerService.
	 * </p>
	 * 
	 * @return boolean
	 *     If role size zero return false otherwise true.
	 * 
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public boolean isRoleAvailable() throws DataBaseException {
		return (getAllRole().size() != 0);
	}
    
	/**
	 * <p>
	 *     This method calculate the last userRole Id.
	 *     Return to the last userRole id.
	 * </p>
	 * 
	 * @return lastId
	 *     last Id of userRole.
	 *  
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public int getUserRoleId() throws DataBaseException {
		int newUserRoleId = ConstantVariableUtil.initializeVariable;
		for (UserRole userRole : customerDao.retriveAllUserRole()) {
			 int lastUserRoleId = userRole.getId();
			 if (newUserRoleId <= lastUserRoleId) {
				 newUserRoleId = lastUserRoleId + 1;
			 }
		}
		return newUserRoleId;
    }
	
	/**
	 * <p>
	 *     This method check the user role already allocated or not
	 *     If user role already allocated return false otherwise true.
	 * </p>
	 * 
	 * @param customerId
	 *     customerId of Customer.
	 *     
	 * @param roleId
	 *     roleId of Role.
	 *     
	 * @return boolean
	 *     If user role already allocated return false otherwise true.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public boolean IfUserRoleExist(String customerId, String roleId) throws DataBaseException {
		for (UserRole userRole : customerDao.retriveAllUserRole()) {
			if ((userRole.getCustomer().getCustomerId().equals(customerId)) && (userRole.getRole().getRoleId().equals(roleId))) {
			    return false;
			}
		}
		return true;
	 }
    
	/**
     * Checks the if validate user using customerId and password.
     * 
     * @param customerId
     *      Id of customer to check validate user or not.
     * 
     * @param password
     *      Password of customer to check validate role or not.
     * 
     * @return boolean
     *      If user already exists return true otherwise false.
     * 
     * @throws DataBaseException
     *      If there is an error in the given data like BadElementException.
     */
	public boolean ifValidateUser(String customerId, String password) throws DataBaseException {
	    Customer customer = getCustomerById(customerId); 
	    if (customer == null) {
	    	return false;  
	    }
	    if(!(customer.getPassWord().equals(password))) {
	    	return false; 
	    }
	    return true;
	}
    
    /**
     * <p>
     *     Checks is the role of customer.
     *     If role is approver return true, otherwise return false.
     * </p>
     * 
     * @param customerId
     *     Id of customer to check role.
     * 
     * @return boolean
     *     If userRoleCount is 2 return true, otherwise return false.
     * 
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     */
	public boolean checkIfRole(String customerId) throws DataBaseException {
		int userRoleCount = ConstantVariableUtil.initializeVariable;
		for (UserRole userRole : customerDao.retriveAllUserRole()) {
			if (userRole.getCustomer().getCustomerId().equals(customerId)) {
				userRoleCount++;
			}
		}
	    if (userRoleCount == 1) {
			return false;
		}
		return true;
	}
}
			 
