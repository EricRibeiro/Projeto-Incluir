jQuery(function ($) {
    countCharInTextArea();
    datePicker();
    hideEmptySalaryRange();
    sideNavConfig();
});

function countCharInTextArea() {
    $('textarea').keyup(function () {
        var maxLength = 120;
        var length = $(this).val().length;
        var length = maxLength - length;
        $('.character-counter').text(length + "/" + maxLength);
    });

    $('textarea').focus(function () {
        $('.character-counter').show();

    });

    $('textarea').blur(function () {
        $('.character-counter').hide();
    });
};

function datePicker()   {
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 5, // Creates a dropdown of 5 years to control year,
        today: 'Hoje',
        clear: 'Limpar',
        close: 'Ok',
        closeOnSelect: false, // Close upon selecting a date,
        format: 'dd-mm-yyyy'
    });
};

function slider() {
    var slider = document.getElementById('test-slider');
    noUiSlider.create(slider, {
        start: [20, 80],
        connect: true,
        step: 1,
        orientation: 'horizontal', // 'horizontal' or 'vertical'
        range: {
            'min': 0,
            'max': 100
        },
        format: wNumb({
            decimals: 0
        })
    });
}

function hideEmptySalaryRange() {
    if ($('.salaryRange').val() == 0.0)
        $('.salaryRange').val("");
};

function sideNavConfig() {
    $('.button-collapse').sideNav({
            menuWidth: 300,
            edge: 'left',
            closeOnClick: true,
            draggable: true,
            onOpen: function(el) {},
            onClose: function(el) {},
        }
    );
};

