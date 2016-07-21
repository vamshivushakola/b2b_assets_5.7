/**
 *
 */
package com.generic.singlevieworders.order.populators;

import de.hybris.platform.b2bacceleratorfacades.order.populators.B2BOrderHistoryPopulator;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.workflow.WorkflowActionService;
import de.hybris.platform.workflow.model.WorkflowActionModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;


/**
 * B2BAssets B2BOrder history populator
 *
 * @author ammandal
 *
 */
public class B2BAssetsB2BOrderHistoryPopulator extends B2BOrderHistoryPopulator
{
	private WorkflowActionService workflowActionService;

	@Override
	public void populate(final OrderModel source, final OrderHistoryData target) throws ConversionException
	{
		target.setPurchaseOrderNumber(source.getPurchaseOrderNumber());
		target.setB2bPermissionResults(Converters.convertAll(source.getPermissionResults(), getB2BPermissionResultConverter()));

		if (source.getWorkflow() != null)
		{

			final Set<PrincipalModel> actionUsers = new HashSet<PrincipalModel>();
			final List<WorkflowActionModel> startWorkflowActions = workflowActionService.getStartWorkflowActions(source
					.getWorkflow());
			for (final WorkflowActionModel action : startWorkflowActions)
			{
				actionUsers.add(action.getPrincipalAssigned());

			}
			target.setManagers(Converters.convertAll(actionUsers, getPrincipalConverter()));
		}
		target.setERPOrderNumber(source.getERPOrderNumber());
		//if (source.getSite() != null)
		if (source.getSalesApplication() != null)
		{
			target.setOrigin("Online");
		}
		else
		{
			target.setOrigin(source.getOrigin());
		}
		target.setAuthor(source.getUser().getName());
	}

	@Override
	protected WorkflowActionService getWorkflowActionService()
	{
		return workflowActionService;
	}

	@Override
	@Required
	public void setWorkflowActionService(final WorkflowActionService workflowActionService)
	{
		this.workflowActionService = workflowActionService;
	}
}
