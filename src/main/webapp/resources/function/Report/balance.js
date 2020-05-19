

$(document).ready(function() {
	// $(".js-example-basic-single").select2();
	$('.nepali-calendar').nepaliDatePicker();

	$("#SP_CODE").select2({
		placeholder : "Select a Service Provider Code",
                allowClear: true
	// allowClear: false
	});
        $("#SERVICE_CODE").select2({
		placeholder : "Select a Service Code",
                allowClear: true
	// allowClear: false
	});
                $("#ITEM_CODE").select2({
		placeholder : "Select a Item Code",
                allowClear: true
	// allowClear: false
	});
});

