package com.generic.ordersearch.field;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


/**
 * @author Capgemini
 */
public class OrderSearchField
{
	public static enum TYPE
	{
		STARTS_WITH, CONTAINS, ENDS_WITH, EXACT
	}

	private final String fieldName;
	private final Set<String> values = new HashSet<>();
	private final TYPE type;
	private final String beforeValue;
    private boolean sort = false;
    private boolean asc = false;
    private boolean filter = false;
    private String rawString = null;

	public OrderSearchField(final String fieldName, final TYPE type, final String beforeValue)
	{
		this.fieldName = fieldName;
		this.type = type;
		this.beforeValue = beforeValue;
	}

	public OrderSearchField(final String fieldName, final TYPE type)
	{
		this.fieldName = fieldName;
		this.type = type;
		this.beforeValue = "";
	}

    public OrderSearchField(final String fieldName)
    {
        this.fieldName = fieldName;
        this.type = TYPE.EXACT;
        this.beforeValue = "";
    }

    public OrderSearchField()
    {
        this.fieldName = null;
        this.type = TYPE.EXACT;
        this.beforeValue = "";
    }

	public void addValue(String value)
	{
		if (!StringUtils.isBlank(value))
		{
			value = beforeValue + value;
			values.add(value);
			/*
			 * values.add(value + "^200.0"); switch (type) { case STARTS_WITH: values.add("*" + value + "^100.0"); break;
			 * case CONTAINS: values.add("*" + value + "*^100.0"); break; case ENDS_WITH: values.add(value + "*^100.0");
			 * break; }
			 */
		}
	}

	public void clearValues()
	{
		values.clear();
	}

	public TYPE getType()
	{
		return type;
	}

	public final Set<String> getValues()
	{
		return values;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public boolean isEmpty()
	{
		return values.isEmpty();
	}

	public boolean isSort()
	{
		return sort;
	}

	public void setSort(final boolean sort)
	{
		this.sort = sort;
	}

	public boolean isFilter()
	{
		return filter;
	}

	public void setFilter(final boolean filter, final boolean asc)
	{
		this.filter = filter;
		this.asc = asc;
	}

	public String getRawString()
	{
		return rawString;
	}

	public void setRawString(final String rawString)
	{
		this.rawString = rawString;
	}

	public boolean asc()
	{
		return asc;
	}
}
