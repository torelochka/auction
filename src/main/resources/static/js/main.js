flatpickr.localize(flatpickr.l10ns.ru);
(function ($) {

    $("#date-in").flatpickr({
        minDate: new Date(),
        enableTime: true,
        time_24hr: true
    })

})(jQuery);