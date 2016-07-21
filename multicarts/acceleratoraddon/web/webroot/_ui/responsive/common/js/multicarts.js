$(document).ready(function () {
    $(document).ready(function () {
        $('.multicarts-selectcartcomponent').on('change', function (e) {
            var cartCode = $('.multicarts-selectcartcomponent :selected').val();
            $.ajax({url:ACC.multicartsSelectCartUrl + cartCode}).done(function() {
                location.reload();
            });
        });
    });
});

