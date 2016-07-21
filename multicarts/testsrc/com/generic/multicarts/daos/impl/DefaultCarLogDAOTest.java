package com.generic.multicarts.daos.impl;

import com.generic.multicarts.daos.CartLogDAO;
import com.generic.multicarts.model.CartLogModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Capgemini
 */
public class DefaultCarLogDAOTest extends ServicelayerTransactionalTest{
    private static final String TEST_MESSAGE = "test message";

    @Resource
    private CartLogDAO cartLogDAO;

    @Resource
    private CartService cartService;

    @Resource
    private UserService userService;

    @Before
    public void setUp() {
       // Registry.activateMasterTenant();
    }


}
