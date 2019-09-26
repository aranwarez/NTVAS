﻿$(document).ready(function() {
	$('.overlay').fadeOut();
});


	$( document ).ajaxStart(function() {

		$('.overlay').fadeIn();
		
	});
	
	$( document ).ajaxStop(function() {
		$('.overlay').fadeOut();
	});
	 

	$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
		//	debugger;
			if (options.url !== '../CheckSession') {	
				$.ajax({
				    url: "../CheckSession",
				    type: "GET",
				    async: false,
				    global: false,
				    dataType: 'json' 
				    
				})
				.done (function(data, textStatus, jqXHR) {
					if(data!==true){
						   alert('Session Has Expired!!! Redirecting to Login Page');
						   window.open('login', '_blank');

						   // location.reload();
					   }

				})
				.fail (function(jqXHR, textStatus, errorThrown) {
				    alert("Error while connecting to server!!");
				})
				.always (function(jqXHROrData, textStatus, jqXHROrErrorThrown) {
				 //   alert("complete");
				});
			}
		});

