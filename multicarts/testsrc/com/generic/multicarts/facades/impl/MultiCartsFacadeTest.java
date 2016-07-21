package com.generic.multicarts.facades.impl;

import com.generic.multicarts.data.CartLogData;
import com.generic.multicarts.data.MultiCartsData;
import com.generic.multicarts.facades.MultiCartsFacade;
import com.generic.multicarts.services.MultiCartsService;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Capgemini
 */
public class MultiCartsFacadeTest extends ServicelayerTransactionalTest {
    private static final String TEST_MESSAGE = "test message";

    @Resource
    MultiCartsFacade multiCartsFacade;

    @Resource
    MultiCartsService multiCartsService;

    @Before
    public void initTests() {
        multiCartsService.getSessionCart();
    }

    @Test
    public void initTest() {
        Assert.assertTrue(multiCartsFacade != null);
        Assert.assertTrue(multiCartsFacade instanceof MultiCartsFacadeImpl);
    }



    @Test
    public void testGetCartByCode() {
//        CartData sessionCart = multiCartsFacade.getSessionCart();
//        String code = sessionCart.getCode();
//        MultiCartsData cart = multiCartsFacade.getCart(code);
//        Assert.assertTrue(cart != null);
//        Assert.assertEquals(cart.getCode(), code);
    }



    @Test
    public void testCreateCart() {
        final List<MultiCartsData> carts = multiCartsFacade.getCarts();
        final MultiCartsData cart = multiCartsFacade.createCart();
        final List<MultiCartsData> all = multiCartsFacade.getCarts();
        Assert.assertNotNull(carts);
        Assert.assertNotNull(cart);
        Assert.assertNotNull(all);
        Assert.assertEquals(carts.size() + 1, all.size());
        Assert.assertEquals(cart.getCode(), all.get(all.size()-1).getCode());
    }

    @Test
    public void testCreateCartName() {
        final List<MultiCartsData> carts = multiCartsFacade.getCarts();
        final MultiCartsData cart = multiCartsFacade.createCart(TEST_MESSAGE);
        final List<MultiCartsData> all = multiCartsFacade.getCarts();
        Assert.assertNotNull(carts);
        Assert.assertNotNull(cart);
        Assert.assertNotNull(all);
        Assert.assertEquals(carts.size() + 1, all.size());
        Assert.assertEquals(cart.getCode(), all.get(all.size()-1).getCode());
        Assert.assertEquals(cart.getName(), TEST_MESSAGE);
        Assert.assertEquals(all.get(all.size()-1).getName(), TEST_MESSAGE);
    }

    @Test
    public void testSelectCart() {
        final MultiCartsData newCart = multiCartsFacade.createCart("cart1");
        final MultiCartsData sessionCart1 = multiCartsFacade.getCurrentCart();
        multiCartsFacade.selectCart(newCart.getCode());
        final MultiCartsData sessionCart2 = multiCartsFacade.getCurrentCart();
        Assert.assertTrue(!sessionCart1.getCode().equals(newCart.getCode()));
        Assert.assertEquals(sessionCart2.getCode(), newCart.getCode());
    }
}
