$(document).ready(function(){
	$(".chosen-select").chosen({
		disable_search: true
	});
	
	$(".chosen-select-responsive").chosen({
		disable_search: true,
		width: '100%'
	});
	
	$(".prod_refine .sortOptions").chosen({
		disable_search: true
	});
	
	var nav = $('.header-menu');
    if($(window).scrollTop() > 136){
    	nav.addClass("fixed-nav");
    }
	
	
    $(window).scroll(function () {
        if ($(this).scrollTop() > 182) {
            nav.addClass("fixed-nav");
        } else {
            nav.removeClass("fixed-nav");
        }
    });
    
    $("#large-carousel").owlCarousel({
    	navigation : true, // Show next and prev buttons
        slideSpeed : 300,
        paginationSpeed : 400,
        singleItem: true,
        navigationText:	["",""]
    });
    
    $("#products-grid").owlCarousel({
    	navigation : true, // Show next and prev buttons
        slideSpeed : 300,
        paginationSpeed : 400,
        items: 4,
        navigationText:	["",""]
    });
    
    $("#cross-sell-carousel").owlCarousel({
    	navigation : true, // Show next and prev buttons
        slideSpeed : 300,
        paginationSpeed : 400,
        items: 1,
        navigationText:	["",""]
    });
    
    $("#brand-grid").owlCarousel({
    	navigation : true, // Show next and prev buttons
        slideSpeed : 300,
        paginationSpeed : 400,
        items: 4,
        navigationText:	["",""]
    });
    
    $('body').backtotop({
    	topOffset: 300,
    	speed: 200,
    	bckTopLinkTitle: ''
    });
    
    $(".add-to-cart .button-up").click(function(){
    	var input_quantity = $(this).parent(".add-to-cart").find(".qty");
    	if(input_quantity.length == 0) {
    		var input_quantity = $(this).parent(".add-to-cart").find(".sku-quantity");
    	}
    	input_quantity.trigger("focusin");
    	var quantity = input_quantity.val();
    	input_quantity.val(parseInt(quantity) + 1);
    	input_quantity.trigger("focusout");
    });
    
    $(".add-to-cart .button-down").click(function(){
    	var input_quantity = $(this).parent(".add-to-cart").find(".qty");
    	if(input_quantity.length == 0) {
    		var input_quantity = $(this).parent(".add-to-cart").find(".sku-quantity");
    	}
    	input_quantity.trigger("focusin");
    	var quantity = parseInt(input_quantity.val());
    	if(quantity > 0) {
    		input_quantity.val(quantity - 1);
    	}
    	input_quantity.trigger("focusout");
    });
    
    if($("input[type='checkbox']").length > 0) {
    	$("input[type='checkbox']").each(function(){
    		$(this).prettyCheckable();
    	});
    }
    
    if($("input[type='radio']").length > 0) {
    	$("input[type='radio']").each(function(){
    		if(!$(this).hasClass("no-transform-radio"))
    			$(this).prettyCheckable();
    	});
    }
    
    $('.collapsableArrow').unbind('click').click(function(e) {
        e.preventDefault();
        $(this).parents('dt').next().stop().slideToggle();
        if ($(this).hasClass('open')){
        	$(this).removeClass('open');
        	$(this).addClass('closed');
        } else {
        	$(this).addClass('open');
        	$(this).removeClass('closed');
        }
    });
    
    $(document).on('cbox_complete', function () {
    	$.addChosenAndPrettyCheckable();
    });
    
    $.addChosenAndPrettyCheckable = function() {
    	$(".popin .chosen-select-responsive").chosen({
    		disable_search: true,
    		width: '100%'
    	});
    	
    	if($(".popin input[type='checkbox']").length > 0) {
        	$(".popin input[type='checkbox']").each(function(){
        		if(!$(this).parent("div").hasClass("prettycheckbox")){
        			$(this).prettyCheckable();
        		}
        	});
        }
    	
    	if($(".popin input[type='radio']").length > 0) {
        	$(".popin input[type='radio']").each(function(){
        		if(!$(this).parent("div").hasClass("prettycheckbox")){
        			$(this).prettyCheckable();
        		}
        	});
        }
    };
    
    $('#replenishment-schedule-div input:radio').change(function(){
    	$(".replenishmentFrequencyDaily").hide();
    	$(".replenishmentFrequencyWeekly").hide();
    	$(".replenishmentFrequencyMonthly").hide();
    	if($(this).val() == "DAILY"){
    		$(".replenishmentFrequencyDaily").show();
    	}
    
		if($(this).val() == "WEEKLY"){
			$(".replenishmentFrequencyWeekly").show();
		}

		if($(this).val() == "MONTHLY"){
			$(".replenishmentFrequencyMonthly").show();
		}
    });
    
    if($("#replenishment-schedule-div").length > 0) {
    	var value_radio = $("#replenishment-schedule-div input:radio:checked").val();
    	$(".replenishmentFrequencyDaily").hide();
    	$(".replenishmentFrequencyWeekly").hide();
    	$(".replenishmentFrequencyMonthly").hide();
    	if(value_radio == "DAILY"){
    		$(".replenishmentFrequencyDaily").show();
    	}
    
		if(value_radio == "WEEKLY"){
			$(".replenishmentFrequencyWeekly").show();
		}

		if(value_radio == "MONTHLY"){
			$(".replenishmentFrequencyMonthly").show();
		}
    }
});