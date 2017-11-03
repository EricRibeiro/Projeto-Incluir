jQuery(function ($) {
	onJobApplyDisableBtn();
    onJobApplyShowMessage();
});

function onJobApplyShowMessage() {
    if ($(location).attr('href').indexOf("candidatar=true") >= 0) {
        Materialize.toast("Pronto! A empresa entrará em contato", 5000);
    }
};

function onJobApplyDisableBtn() {
	var $btnCandidatar = $("button[id*='candidatar']");
    $btnCandidatar.each(function() {
    	if ($(this).text() == "Já candidatou") {
    		$(this).prop("disabled", "true");
    	}
    });	
};

