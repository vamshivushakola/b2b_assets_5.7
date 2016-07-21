package com.generic.multicarts.attributehandlers;

import com.generic.multicarts.model.CartShareModel;
import de.hybris.platform.core.model.order.CartModel;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Capgemini
 */
public class CartIsSharedAttributeUnitTest {

    private CartModel cart;
    private CartIsSharedAttributeHandler handler;

    @Before
    public void setUp() {
        cart = new CartModel();
        cart.setShares(new ArrayList<CartShareModel>());

        handler = new CartIsSharedAttributeHandler();
    }

    @Test
    public void testHandler()
    {
        assertEquals(false, handler.get(cart));

        cart.getShares().add(new CartShareModel());
        assertEquals(true, handler.get(cart));
    }
}
