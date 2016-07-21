package com.generic.multicarts.services.impl;


import com.generic.multicarts.model.CartLogModel;
import com.generic.multicarts.services.MultiCartsService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Capgemini
 */
@UnitTest
public class MultiCartsServiceTest extends ServicelayerTransactionalTest {
    private static final String TEST_MESSAGE = "test message";

    @Resource(name = "multiCartsService")
    private MultiCartsService multiCartsService;

    @Resource
    private UserService userService;


    @Test
    public void testGetCarts() {
        final List<CartModel> carts = multiCartsService.getCarts(userService.getCurrentUser());
        multiCartsService.getSessionCart();
        final List<CartModel> carts2 = multiCartsService.getCarts(userService.getCurrentUser());
        Assert.assertNotNull(carts);
        Assert.assertNotNull(carts2);
        Assert.assertEquals(carts.size()+1, carts2.size());

    }
}
