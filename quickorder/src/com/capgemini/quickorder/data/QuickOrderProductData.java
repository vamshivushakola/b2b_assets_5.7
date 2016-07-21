package com.capgemini.quickorder.data;

/**
 * QuickOrderProductData is a POJO class
 * 
 * @author svc-in-ecommusr
 * 
 */
public class QuickOrderProductData
{


	private String code;

	private String name;

	private String errorMessage;

	/**
	 * QuickOrderProductData constructor
	 */
	public QuickOrderProductData()
	{
		this.code = "";
		this.name = "";
		this.errorMessage = "";
	}

	/**
	 * Return the error message
	 * 
	 * @return the errorMessage
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * Set the error message
	 * 
	 * @param errorMessage
	 *           the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	/**
	 * Return the name
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name
	 * 
	 * @param name
	 *           the name to set
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Return the code
	 * 
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Set the code
	 * 
	 * @param code
	 *           the code to set
	 */
	public void setCode(final String code)
	{
		this.code = code;
	}


}
