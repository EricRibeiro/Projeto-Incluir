jQuery(function ($) {
    onKeyDownAddMasks();
    setLocationCoordinates();
    getAddress();
});

var $address = $(".address");
var $addressLabel = $(".address-label");
var $bairro = $("input[id*='bairro']");
var $bairroHidden = $("input[id*='bairro-hidden']");
var $cep = $("input[id*='cep']");
var $estado = $("input[id*='estado']");
var $estadoHidden = $("input[id*='estado-hidden']");
var $latitude = $("input[id*='latitude']");
var $logradouro = $("input[id*='logradouro']");
var $logradouroHidden = $("input[id*='logradouro-hidden']");
var $longitude = $("input[id*='longitude']");
var $municipio = $("input[id*='municipio']");
var $municipioHidden = $("input[id*='municipio-hidden']");
var $numero = $("input[id*='numero']");

function onKeyDownAddMasks() {
    var $cnpj = $("input[id*='cnpj']");
    $cnpj.keydown(function () {
        $cnpj.mask("99.999.999/9999-99");
    });
};

function setLocationCoordinates() {
    var geocoder = new google.maps.Geocoder();

    $('.coordinate').on('blur mouseleave', function () {
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

function getAddress() {
    $cep.keyup(function () {
        if ($(this).val().length === 9) {
            getAddressAPI();
        }
    })

    $cep.blur(function () {
        if ($cep.val() != "") {
            getAddressAPI();
        }
    })
};

function getAddressAPI() {
    var cep = $cep.val().replace(/\D/g, '');

    $.ajax({
        url: "//viacep.com.br/ws/" + cep + "/json/?callback=?",
        dataType: 'json',
        timeout: 1000,
        success: function (dados) {
            onGetAddressSuccess(dados)
        },
        error: function () {
            onGetAddressError()
        }
    });
};

function onGetAddressSuccess(dados) {
    if (!("erro" in dados)) {
        $cep
            .removeClass("invalid")
            .addClass("valid");
        $logradouro
            .add($logradouroHidden)
            .val(dados.logradouro);
        $bairro
            .add($bairroHidden)
            .val(dados.bairro);
        $municipio
            .add($municipioHidden)
            .val(dados.localidade);
        $estado
            .add($estadoHidden)
            .val(dados.uf);
        $address
            .removeClass("invalid")
            .addClass("valid");
        $addressLabel
            .addClass('active');
        $numero.focus();
    } else {
        onGetAddressError();
    }
};

function onGetAddressError() {
    $cep
        .removeClass("valid")
        .addClass("invalid");
    $logradouro
        .add($logradouroHidden)
        .val("");
    $bairro
        .add($bairroHidden)
        .val("");
    $municipio
        .add($municipioHidden)
        .val("");
    $estado
        .add($estadoHidden)
        .val("");
    $address
        .removeClass("valid")
        .addClass("invalid");
    $addressLabel
        .addClass('active');
};
