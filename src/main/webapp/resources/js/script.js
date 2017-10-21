jQuery(function ($) {
    onlyAllowNumbers();
    onKeyDownAddCommomMasks();
    onLoadAddRequiredAttr();
    sideNavConfig();
    datePicker();
    onBtnClickShowInvalidFields();
});

function onlyAllowNumbers() {
    $(".only-numbers").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
    });
}

function onKeyDownAddCommomMasks() {
    var $cep = $("input[id*='cep']");
    $cep.keydown(function () {
        $cep.mask("99999-999");
    });
}

function onBtnClickShowInvalidFields() {
    $('button').click(function () {
        $('[aria-required="true"]').filter(function () {
            return !this.value;
        })
            .addClass('invalid')
            .next()
            .addClass('active');
    });
}

function onLoadAddRequiredAttr() {
    $('[aria-required="true"]')
        .attr('required', "")
}

function datePicker() {
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 5, // Creates a dropdown of 5 years to control year,
        today: 'Hoje',
        clear: 'Limpar',
        close: 'Ok',
        closeOnSelect: false, // Close upon selecting a date,
        format: 'dd-mm-yyyy',
        min: new Date()
    });
};

function sideNavConfig() {
    $('.button-collapse').sideNav({
            menuWidth: 300,
            edge: 'left',
            closeOnClick: true,
            draggable: true,
            onOpen: function (el) {
            },
            onClose: function (el) {
            },
        }
    );
};

