jQuery(function ($) {
    onKeyDownAddMasks();
    onChangeAdjustExpirationDate();
    onLoadHideEmptySalaryRange();
    validateSalaryRange();
    onSuccessfullFinalization();
    onSuccessfullRegister()
});

function onKeyDownAddMasks() {
    var $salary = $(".salary");

    $salary.keydown(function () {
        $salary.mask('00.000,00', {reverse: true});
    });
};

function onLoadHideEmptySalaryRange() {
    var $salaryRange = $('.salary');
    if ($salaryRange.val() == 0.0)
        $salaryRange.val("");
};

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
};

function validateSalaryRange() {
    var $salaryMin = $("input[id*='salary-min']");
    var $salaryMax = $("input[id*='salary-max']");

    $($salaryMin).add($salaryMax).blur(function () {
        if ($salaryMin.val() !== "" && $salaryMax.val() !== "") {
            var salaryMin = parseFloat($salaryMin.val().replace(".", "").replace(",", "."));
            var salaryMax = parseFloat($salaryMax.val().replace(".", "").replace(",", "."));

            if (salaryMin > salaryMax) {
                $salaryMin.val("").removeClass("valid").addClass("invalid");
                $salaryMax.val("").removeClass("valid").addClass("invalid");
                Materialize.toast("Faixa salarial inválida!", 5000);
            }
        }
    });
};

function onSuccessfullFinalization() {
    if ($(location).attr('href').indexOf("finalized=true") >= 0) {
        Materialize.toast("Vaga finalizada", 5000);
    }
}

function onSuccessfullRegister() {
    if ($(location).attr('href').indexOf("persisted=true") >= 0) {
        Materialize.toast("Vaga cadastrada", 5000);
    }
}