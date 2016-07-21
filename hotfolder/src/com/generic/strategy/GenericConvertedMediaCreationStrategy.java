package com.generic.strategy;

import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.mediaconversion.conversion.DefaultConvertedMediaCreationStrategy;


/**
 * Generic Converted Media Creation Strategy
 * 
 * @author Capgemini
 */
public class GenericConvertedMediaCreationStrategy extends DefaultConvertedMediaCreationStrategy
{

	/**
	 * /CMFGeneric/{productCode}-{name}.jpg => /NewFormat/{productCode}-{name}.jpg If the image code does not contain the
	 * format, super method is called
	 * 
	 * @param parent
	 *           MediaModel
	 * @param format
	 *           MediaFormatModel
	 * @return the new media code
	 */
	@Override
	protected String createCode(final MediaModel parent, final MediaFormatModel format)
	{
		if (parent.getCode().contains(parent.getMediaFormat().getQualifier()))
		{
			return parent.getCode().replace(parent.getMediaFormat().getQualifier(), format.getQualifier());
		}
		else
		{
			return super.createCode(parent, format);
		}
	}
}
