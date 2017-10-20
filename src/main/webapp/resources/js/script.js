jQuery(function ($) {
    onLoadHideEmptySalaryRange();
    onLoadAddRequiredAttr();
    sideNavConfig();
    onChangeAdjustExpirationDate();
    onKeyupCountCharInTextArea();
    datePicker();
    onBtnClickShowInvalidFields();
    setLocationCoordinates();
});

function onBtnClickShowInvalidFields() {
    $('button').click(function () {
        $('[aria-required="true"]').filter(function () {
            return !this.value;
        }).addClass('invalid');
    });
}

function onLoadAddRequiredAttr() {
    $('[aria-required="true"]').attr('required', "");
}

function onChangeAdjustExpirationDate() {
    var $dateMaterialize = $("input[id*='date-materialize']");
    var $datePrime = $("input[id*='date-prime']");

    $dateMaterialize.val($datePrime.val());

    $dateMaterialize.change(function () {
        $datePrime.val(($(this).val()));
        $dateMaterialize
            .removeClass('invalid')
            .addClass('valid');
    })
}

function onKeyupCountCharInTextArea() {
    var $characterCounter = $('.character-counter');
    var $textArea = $('textarea');

    $textArea.keyup(function () {
        var maxLength = 120;
        var length = $(this).val().length;
        var length = maxLength - length;
        $characterCounter.text(length + "/" + maxLength);
    });

    $textArea.focus(function () {
        $characterCounter.show();

    });

    $textArea.blur(function () {
        $characterCounter.hide();
    });
};

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

function onLoadHideEmptySalaryRange() {
    var $salaryRange = $('.salaryRange');
    if ($salaryRange.val() == 0.0)
        $salaryRange.val("");
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

function setLocationCoordinates() {

    var geocoder = new google.maps.Geocoder();
    var $numero = $("input[id*='numero']");
    var $logradouro = $("input[id*='logradouro']");
    var $bairro = $("input[id*='bairro']");
    var $municipio = $("input[id*='municipio']");
    var $latitude = $("input[id*='latitude']");
    var $longitude = $("input[id*='longitude']");
    
    $('.coordinate').blur(function () {
        var numero = $numero.val();
        var logradouro = $logradouro.val();
        var bairro = $bairro.val();
        var municipio = $municipio.val();

        if (numero !== "" && logradouro !== "" && bairro !== "" && municipio !== "") {
            var endereco = numero + " " + logradouro + ", " + bairro + ", " + municipio;
            var lat = -43.96;
            var lng = -43.96;

            geocoder.geocode({'address': endereco}, function (r, s) {
                lat = r[0].geometry.location.lat();
                lng = r[0].geometry.location.lng();
                $latitude.val(lat);
                $longitude.val(lng);
            });
        }
    });
};
