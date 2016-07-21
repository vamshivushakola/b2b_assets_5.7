ACC.product = {
		
	// cached jQuery objects
	$cartPopup:             $('#cart_popup'),
	$addToCartButton:       $(':submit.add_to_cart_button'),
	$addToCartOrderForm:    $('.add_to_cart_order_form'),
	$addToCartForm:         $('.add_to_cart_form'),
	// selector, used for forced refreshes when you need uncached jQuery objects after DOM updates
	addToCartFormSelector: '.add_to_cart_form',
	
	bindToAddToCartForm: function(options) {
		options = ACC.common.ensureAtleastDefaultAttributeSet(options, 'enforce', false);
		if (options.enforce) {
		
			ACC.product.$addToCartForm = $(ACC.product.addToCartFormSelector);
		}
		ACC.product.$addToCartForm.ajaxForm({success: ACC.product.displayAddToCartPopup});
	},
	
	
	initQuickviewLightbox:function(){
		this.enableAddToCartButton();
		this.bindToAddToCartForm();
	},
	
	
	enableAddToCartButton: function ()
	{
		$('#addToCartButton').removeAttr("disabled");
	},

	enableVariantSelectors: function ()
	{
		$('.variant-select').removeAttr("disabled");
	},
	
	
	bindToAddToCartForm: function ()
	{
		ACC.product.$addToCartButton.removeAttr("disabled");
		var addToCartForm = $('.add_to_cart_form');
		addToCartForm.ajaxForm({success: ACC.product.displayAddToCartPopup});
	},

	bindToAddToCartStorePickUpForm: function ()
	{
		var addToCartStorePickUpForm = $('#pickup_store_results .add_to_cart_storepickup_form');

		addToCartStorePickUpForm.ajaxForm({success: ACC.product.displayAddToCartPopup});
	},
	
	
	enableStorePickupButton: function ()
	{
		$('.pickupInStoreButton').removeAttr("disabled");
	},


	displayAddToCartPopup: function (cartResult, statusText, xhr, formElement)
	{
		
		$('#addToCartLayer').remove();
		if (typeof refreshMiniCart == 'function') {
			refreshMiniCart();
		}
//		if (typeof ACC.minicart.refreshMiniCartCount == 'function')
//		{
//			ACC.minicart.refreshMiniCartCount();
//		}
		
		$("#header").append(cartResult.addToCartLayer);
		

		$('#addToCartLayer').fadeIn(function(){
			$.colorbox.close();
			if (typeof timeoutId != 'undefined')
			{
				clearTimeout(timeoutId);
			}
			timeoutId = setTimeout(function ()
			{
				$('#addToCartLayer').fadeOut(function(){
			 	   $('#addToCartLayer').remove();
					
				});
			}, 5000);
			
		});
		
		var productCode = $('[name=productCodePost]', formElement).val();
		
		var quantityField = $('[name=qty]', formElement).val();
		console.log(quantityField);
		var quantity = 1;
		if (quantityField != undefined)
		{
			quantity = quantityField;
		}

		var cartAnalyticsData = cartResult.cartAnalyticsData;

		var cartData = {"cartCode": cartAnalyticsData.cartCode,
				"productCode": productCode, "quantity": quantity,
				"productPrice":cartAnalyticsData.productPostPrice,
				"productName":cartAnalyticsData.productName} ;
		ACC.track.trackAddToCart(productCode, quantity, cartData);
		
//		added for cart popup functionality
		$('#colorbox').hide();

		ACC.product.$cartPopup.hide();
		ACC.product.$cartPopup.html(cartResult.cartPopupHtml);
		console.log(cartResult.cartPopupHtml);
		$('#add_to_cart_close').click(function(event) {
			event.preventDefault();
			ACC.product.$cartPopup.hide();
		});

		ACC.product.$cartPopup.fadeIn();
		if (typeof timeoutId != 'undefined') {
			clearTimeout(timeoutId);
		}
		timeoutId = setTimeout(function() {ACC.product.$cartPopup.fadeOut();}, 5000);
		$.colorbox.close();
		
		// if it is orderForm, disable add to cart button in the end
		if($('#orderFormAddToCart').length > 0) {
			$('#addToCartBtn').attr('disabled','disabled');
		}
	}
	
	

};

$(document).ready(function ()
{

	with(ACC.product)
	{
		bindToAddToCartForm();
		bindToAddToCartStorePickUpForm();
		enableStorePickupButton();
		enableAddToCartButton();
		enableVariantSelectors();
	}
});

