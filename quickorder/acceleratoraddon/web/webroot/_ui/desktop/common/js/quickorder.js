QuickOrder = window.QuickOrder || {};

QuickOrder.autocomplete = {
    bindAll: function () {
        this.bindSearchAutocomplete();
    },

    
    
    bindSearchAutocomplete: function ()	{
        var searchEl = $(".js-quickorder-autocomplete");
        var options = searchEl.data("options");
               
        var cache = {};

        if (options) {
            if(options.useSolr === 'true') {
                this.doSolrAutocomplete(searchEl, options, cache);
            }
            else {
                this.doAutocomplete(searchEl, options, cache);
            }
        }
    },

    doAutocomplete: function (searchEl, options, cache)	{
        var nbResults = parseInt(options.size) || 4;
        searchEl.autocomplete({
            minLength: parseInt(options.minChars) || 3,
            delay: parseInt(options.delay) || 300,
            appendTo: ".js-append",
            source: function(request, response) {
                var query = request.term;

                if (query in cache) {
                    return response(cache[query]);
                }

                var searchData = {
                    q: query,
                    nb: nbResults
                };

                $.getJSON(options.autocompleteUrl, searchData, function(data) {
                    var results = jQuery.map(data,function(el) {
                        el.value = el.code;
                        $('#errorMessage').text(el.errorMessage);
                        //var parentRowEl = $('.js-quickorder-autocomplete').closest('tr');
                        //$(parentRowEl).find("p").append(el.name);
                        return el;
                    });
                    results = results.slice(0, nbResults);
                    cache[query] = results;
                    return response(results);
                });
            },
            focus: function (event, ui) {
               // $(this).parent().parent().find('td[class="name"]').text(ui.item.name);
               // return false;
            },
            select: function(event, ui) {
                $(this).parent().parent().find('td[class="name"]').text(ui.item.name);
            }
        });
    },

    doSolrAutocomplete: function (searchEl, options, cache)	{
        var nbResults = parseInt(options.size) || 4;
        searchEl.autocomplete({
            minLength: parseInt(options.minChars) || 3,
            delay: parseInt(options.delay) || 300,
            appendTo: ".js-append",
            source: function(request, response) {
                var query = request.term;

                if (query in cache) {
                    return response(cache[query]);
                }

                var searchData = {
                    term: query
                };

                $.getJSON(options.autocompleteSolrUrl, searchData, function(data) {
                    var results = jQuery.map(data['products'],function(el) {
                        el.value = el.code;
                        $('#errorMessage').text(el.errorMessage);
                        return el;
                    });
                    results = results.slice(0, nbResults);
                    cache[query] = results;
                    return response(results);
                });
            },
            focus: function (event, ui) {
                // $(this).parent().parent().find('td[class="name"]').text(ui.item.name);
                // return false;
            },
            select: function(event, ui) {
                $(this).parent().parent().find('td[class="name"]').text(ui.item.name);
            }
        });
    }
};

QuickOrder.clear = {
    bindAll: function () {
        this.bindDelete();
    },

    bindDelete: function() {
        $('.js-quickorder-delete').on('click', function() {
            var parentRowEl = $(this).closest('tr');
            $(parentRowEl).find('input').val('');
            $(parentRowEl).find('td[class="name"]').text('');
            $('#errorMessage').text('');
            
        });
    }
};

$(document).ready(function() {
    QuickOrder.autocomplete.bindAll();
    QuickOrder.clear.bindAll();    
    
});