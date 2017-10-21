jQuery(function ($) {
    onInvalidLogin();
    onSuccessfullSignUp();
});

function onInvalidLogin() {
    if ($(location).attr('href').indexOf("login=false") >= 0) {
        Materialize.toast("Login inválido! Tente novamente", 5000);
    }
};

function onSuccessfullSignUp() {
    if ($(location).attr('href').indexOf("persisted=true") >= 0) {
        Materialize.toast("Cadastro realizado!", 5000);
    }
}
