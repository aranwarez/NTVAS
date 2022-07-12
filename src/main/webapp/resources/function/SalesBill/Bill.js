/**
 * 
 */
var itemlist;
var rowcount = 0;
var globaltsc;
var globalvat;
var globalown;


$(document).ready(function() {

	// prevent multiple click
	jQuery('.myClickDisabledElm').bind('click', function(e) {
		e.preventDefault();

	});

	$("#SP_CODE").select2({
		placeholder : "Select a Customer",
		allowClear : true,
		 sorter: function(data) {
		        return data.sort(function(a, b) {
		            return a.text < b.text ? -1 : a.text > b.text ? 1 : 0;
		        });
		    }
	});

	$("#BANK_CODE").select2({
		placeholder : "Select Bank",
		allowClear : true
	});

	$('.nepali-calendar').nepaliDatePicker();

	getTaxableRate();
	getItem();

	$('.overlay').fadeOut();
});

function focusFunction(row) {
	if ($('#rowcnt' + row).css("background-color") != 'rgb(240, 240, 255)') {
		$('#rowcnt' + row).css({
			"background-color" : "#ffff99"
		});
		$('#rate' + row).css({
			'background-color' : '#ffff99'
		});
		$('#quan' + row).css({
			'background-color' : '#ffff99'
		});

	}

}
function blurFunction(row) {
	if ($('#rowcnt' + row).css("background-color") != 'rgb(240, 240, 255)') {
		$('#rowcnt' + row).css({
			"background-color" : "transparent"
		});
		$('#rate' + row).css({
			'background-color' : 'transparent'
		});
		$('#quan' + row).css({
			'background-color' : 'transparent'
		});

	}
}

function validateform(Parameters) {
	var item = 0;
	$.each(Parameters, function(key, value) {
		if (isempty(value)) {
			item = key;
		}
		return (!isempty(value));
	});
	return item;
};

function isempty(object) {
	if (object == "" || object === "" || typeof (object) === "undefined") {
		return true;
	} else
		return false;
}

function getcustomerinfo(SPCODE) {
	$.get('../cashsale/getCustomerInfo', {
		SPCODE : SPCODE
	}, function(response) {
		$.each(response, function(key, value) {
			$('#address').html(value.ADDRESS);
			$('#panno').html(value.PAN_NO);
		});
		// alert(JSON.stringify(response));
	});
	
	// clear all item
	
	$(".bg-red").each(function() {
		var rowid = (this.id.substring(3));
	//	debugger;
		removeitem(this);
		
	});
	

}

function getItemTariff() {
	$('#itemcode' + itemid).html(a.value);

}

function getItem() {
	$.get('../cashsale/getitemlist', {}, function(response) {
		itemlist = response;
		// alert(JSON.stringify(response));
	});

}

function getTaxableRate() {
	$.get('../cashsale/getitemtariff', {
		Itemcode : 'TSC'
	}, function(response) {

		globaltsc = Number(response);
	});

	$.get('../cashsale/getitemtariff', {
		Itemcode : 'VAT'
	}, function(response) {

		globalvat = Number(response);
	});
        
          $.get('../cashsale/getitemtariff', {
		Itemcode : 'OWN'
	}, function(response) {

		globalown = Number(response);
	});

}

function getbank() {
	$.get('../cashsale/getbankList', {}, function(response) {

	});

}

function additem() {
	rowcount = rowcount + 1;
	var button = '<a href="#" id="remo'
			+ rowcount
			+ '" class="btn btn-default bg-red" onclick="removeitem(this)"><i class="fa fa-trash"></i></a>';
	var appendrow = '<tr id="rowcnt' + rowcount
			+ '"><td style="text-align:center">' + button + '</td>';
	appendrow = appendrow + '<td><select style="width: 100%;" id="item'
			+ rowcount
			+ '" onchange="itemchange(this)" onfocus="focusFunction(' + '\''
			+ rowcount + '\'' + ')"  onblur="blurFunction(' + '\'' + rowcount
			+ '\'' + ')"></select></td>';
	appendrow = appendrow
			+ '<td><input id="quan'
			+ rowcount
			+ '" style="text-align:right;"type="number" min="0" onfocus="focusFunction('
			+ '\'' + rowcount + '\'' + ')"  onblur="blurFunction(' + '\''
			+ rowcount + '\''
			+ ')" placeholder="Quantity" onchange="calc(this)"></td>';
	appendrow = appendrow
			+ '<td><input id="rate'
			+ rowcount
			+ '" style="text-align:right;"type="number" min="0" placeholder="Rate" onchange="calc(this)" onfocus="focusFunction('
			+ '\'' + rowcount + '\'' + ')"  onblur="blurFunction(' + '\''
			+ rowcount + '\'' + ')"><input id="tscflag' + rowcount
			+ '" type="hidden"><input id="ownflag' + rowcount
			+ '" type="hidden"><input id="vatflag' + rowcount
			+ '" type="hidden">' + '<input id="catflag' + rowcount
			+ '" type="hidden">';
	appendrow = appendrow
			+ '<input id="pamt'
			+ rowcount
			+ '" style="text-align:right;"type="number" min="0" placeholder="Paid Amount" onchange="calc(this)" onfocus="focusFunction('
			+ '\'' + rowcount + '\'' + ')"  onblur="blurFunction(' + '\''
			+ rowcount + '\'' + ')"></td>';

	appendrow = appendrow
			+ '<td><span id="rev'
			+ rowcount
			+ '" style="text-align:right;"type="number" min="0" class="revclass" onfocus="focusFunction('
			+ '\'' + rowcount + '\'' + ')"  onblur="blurFunction(' + '\''
			+ rowcount + '\'' + ')"></span></td>';
	appendrow = appendrow
			+ '<td><span id="tsc'
			+ rowcount
			+ '" style="text-align:right;"type="number" min="0" class="tscclass"></span></td>';
        appendrow = appendrow
			+ '<td><span id="own'
			+ rowcount
			+ '" style="text-align:right;"type="number" min="0" class="ownclass"></span></td>';        
	appendrow = appendrow
			+ '<td><span id="vat'
			+ rowcount
			+ '" style="text-align:right;"type="number" min="0" class="vatclass"></span></td>';
	appendrow = appendrow
			+ '<td><span id="total'
			+ rowcount
			+ '" style="text-align:right;"type="number" min="0" class="totclass"></span></td>';
	appendrow = appendrow
	+ '<td><span id="totalbal'
	+ rowcount
	+ '" style="text-align:right;"type="number" min="0" class="totbalclass"></span></td>';
	appendrow = appendrow
	+ '<td><span id="totalbalvat'
	+ rowcount
	+ '" style="text-align:right;"type="number" min="0" class="totbalvatclass"></span></td></tr>';

	
	
	
	$('#example1').append(appendrow);

	$.each(itemlist, function(key, value) {
		if (value.IS_RECURRING !== 'T') {
			$('#item' + rowcount)
					.append(
							new Option(value.DESCRIPTION, value.ITEM_CODE,
									false, false));
		}
	});
	$('#item' + rowcount).val('');
	$('#item' + rowcount).select2({
		placeholder : "Select a item"
	// allowClear : true
	});
}

function itemchange(a) {
	var itemid = a.id.substring(4);
	$('#itemcode' + itemid).html(a.value);

	// filling rate and
	$('#totalbal'+  itemid).html('X');
	$('#totalbalvat'+  itemid).html('X');	

	$.each(itemlist, function(key, value) {
		if (value.ITEM_CODE === a.value) {
			$.get('../cashsale/getitemtariff', {
				Itemcode : a.value
			}, function(response) {
				$('#tscflag' + itemid).val(value.TAXABLE_AMT);
                                $('#ownflag' + itemid).val(value.OWN_AMT);
				$('#vatflag' + itemid).val(value.VATABLE_AMT);
				$('#catflag' + itemid).val(value.CATEGORY_CODE);
				if (value.CATEGORY_CODE == "SERVICE" || value.CATEGORY_CODE == "BILLITEM" || value.CATEGORY_CODE == "DEBBOOK") {
					$('#rate' + itemid).hide();
					$('#pamt' + itemid).show();
					$.get('../payment/getSpdueforbilling', {
						ITEM_CODE : a.value,
						SP_CODE : $('#SP_CODE').val()
					}, function(response) {
					$('#totalbal'+  itemid).html(response.PAYABLE_BEFORE_TAX);
					$('#totalbalvat'+  itemid).html(response.BAL_AMT_WITH_TAX);	
					$('#quan' + itemid).val('1');
					$('#quan' + itemid).prop('readonly', true);
					});

				} else {
					$('#rate' + itemid).show();
					$('#pamt' + itemid).val('');
					$('#pamt' + itemid).hide();
					$('#quan' + itemid).prop('readonly', false);
				}
				$('#rate' + itemid).val(Number(response));
				calc(a);
			});

		}

	});
	
}

function calc(a) {
    //debugger;
    var itemid = a.id.substring(4);
    //Checking for tscflag and vatflag if zero storing globaltscvat to
    //temp variable need to revert to global again
    var temptsc = globaltsc;
    var tempvat = globalvat;
    var tempown = globalown;
    if (Number($('#tscflag' + itemid).val()) == 0)
        globaltsc = 0;
    if (Number($('#tscflag' + itemid).val()) == 0)
        globaltsc = 0;
    if (Number($('#ownflag' + itemid).val()) == 0)
        globalown = 0;


    // for billing item type


    if ($('#catflag' + itemid).val() == 'SERVICE' || $('#catflag' + itemid).val() == 'BILLITEM' || $('#catflag' + itemid).val() == 'DEBBOOK') {

        //redoing calculation using formula provided by NT
        //debugger;
        var paidamt = $('#pamt' + itemid).val();
        var divisorf = 1 + (globalvat / 100);
        divisorf = paidamt / divisorf;
        var divisore = 1 + (globalown / 100);
        divisore = divisorf / divisore;
        var divisord = 1 + (globaltsc / 100);
        divisord = divisore / divisord;
        var rev = divisord;
        tsc = divisore - divisord;
        var own = divisorf - divisore;
        var vat = paidamt - divisorf;
        rev = math.round(rev, 2);
        tsc = math.round(tsc, 2);
        own = math.round(own, 2);
        vat = math.add(paidamt, -rev, -tsc, -own);
        vat = math.round(vat, 2);
        var totalamt = math.add(rev, tsc, own, vat);
        totalamt = math.round(totalamt, 2);

        $('#vat' + itemid).html(vat);
        $('#rev' + itemid).html(rev);
        $('#own' + itemid).html(own);
        $('#tsc' + itemid).html(tsc);
        // var total=
        $('#total' + itemid).html(totalamt);
        
        // till here redooing cal.

    } else {

        var rate = $('#rate' + itemid).val();
        var quantity = $('#quan' + itemid).val();
        var tsc = 0;
        if ($('#tscflag' + itemid).val() != 0) {
            tsc = globaltsc * 0.01 * Number(rate) * Number(quantity);
            tsc = Number(tsc.toFixed(2));
        }

        $('#tsc' + itemid).html(tsc);

        //for OT new req
        var own = 0;
        if ($('#ownflag' + itemid).val() != 0) {
            own = globalown * 0.01 * Number(tsc + (rate * quantity));
            own = Number(own.toFixed(2));
        }

        $('#own' + itemid).html(own);


        //---


        var vat = 0;
        if ($('#vatflag' + itemid).val() != 0) {
            vat = globalvat * 0.01 * Number(tsc + own + (rate * quantity));
            vat = Number(vat.toFixed(2));
        }
        var rev = Number(rate * quantity) + Number(tsc) + Number(vat) + Number(own);

        rev = Number(rev.toFixed(2));
        $('#vat' + itemid).html(vat);
        $('#rev' + itemid).html(rate * quantity);

        $('#total' + itemid).html(rev);
    }
    // getting sum of footer
    getsumoffooter();


    //reverting original global tsc vat from temp
    globaltsc = temptsc;
    globalvat = tempvat;
    globalown = tempown;
}

function revcalc(a){
	var itemid = a.id.substring(4);
	
	//Checking for tscflag and vatflag if zero storing globaltscvat to
	//temp variable need to revert to global again
	var temptsc=globaltsc;
	var tempvat=globalvat;
         var tempown=globalown;
	if(Number($('#tscflag' + itemid).val())==0)
		globaltsc=0;
	if(Number($('#vatflag' + itemid).val())==0)
		globalvat=0;
	if(Number($('#ownflag' + itemid).val())==0)
		globalown=0;
	

	if ($('#catflag' + itemid).val() == 'SERVICE' ) {
		var divisor = (1 + (globaltsc / 100) + ((globalvat / 100) * (1 + (globaltsc / 100))));
		var rev = $('#pamt'+itemid).val()/divisor;
		tsc= ((globaltsc/100)*rev).toFixed(2);
		vat =+($('#pamt'+itemid).val())- +rev- +tsc;
		vat = vat.toFixed(2);
		rev = rev.toFixed(2);
		$('#vat' + itemid).html(vat);
		$('#rev' + itemid).html(rev);
		$('#tsc' + itemid).html(tsc);
		// var total=
		$('#total' + itemid).html(+tsc+ +vat+ +rev);
		
	}
	
	//reverting original global tsc vat from temp
	globaltsc=temptsc;
	globalvat=tempvat;
        globalvat=tempown;
}

function getsumoffooter() {
	tfooter('revclass', 'sumrev', null);
	tfooter('tscclass', 'sumtsc', null);
         tfooter('ownclass', 'sumown', null);
	tfooter('vatclass', 'sumvat', null);
	tfooter('totclass', 'sumtot', null);

}
function removeitem(a) {
	var itemid = a.id.substring(4);
	$('#rowcnt' + itemid).remove();
	getsumoffooter();
}

function tfooter(clas, fid, msid) {
	var sum = 0;

	// iterate through each textboxes and add the values
	$("." + clas).each(function() {

		// add only if the value is number
		if (!isNaN($(this).text()) && $(this).text().length != 0) {
			sum += parseFloat($(this).text());
		} else if (!isNaN($(this).val()) && $(this).val().length != 0) {
			sum += parseFloat($(this).val());
		}
	});

	// .to111Fixed() method will roundoff the final sum to 2 decimal places
	$('#' + fid).text(sum.toFixed(2));
	if (msid != null) {
		$('#' + msid).text(sum.toFixed(2));
	}
}

function post() {
	var temparray = [];
	var validflag=true;
	$(".revclass").each(function() {
		var rowid = (this.id.substring(3));
		//debugger;
		if(isempty($('#rev' + rowid).html()) || Number($('#rev' + rowid).html())<=0 ){
			alert('Invalid Amount!!!!');
			$('#pamt' + rowid).focus();
			validflag= false;
		}
		
		var arraydata = new Object();
		arraydata.ITEM_CODE = $('#item' + rowid).val();
		arraydata.RATE = $('#rate' + rowid).val();
		arraydata.QUANTITY = $('#quan' + rowid).val();
		arraydata.REV = $('#rev' + rowid).html();
		arraydata.TSC = $('#tsc' + rowid).html();
                arraydata.OWN = $('#own' + rowid).html();
		arraydata.VAT = $('#vat' + rowid).html();
		temparray.push(arraydata);
		
	});
	
	// alert(JSON.stringify(temparray));
	var param = {
		SP_CODE : $('#SP_CODE').val(),
		nepdate : $('#nepdate').val(),
		BANK_CODE : $('#BANK_CODE').val(),
		// REMARKS:$('#remarks').val(),
		AMT : $('#AMT').val()

	};
	var valid = validateform(param);
	if (valid !== 0) {
		alert("Invalid !!!!! " + valid)
		return false;
	}
	if ($('#AMT').val() != $('#sumtot').html()) {
		alert("Invalid Bank Amount");
		return false;
	}
	if(validflag==false){
		return false;
	}
	$('.overlay').fadeIn();
	$('#savebtn').prop('disabled', true);

	$("#savebtn").html('Saving...');

	// posting data
	$.post('../cashsale/savebill', {
		SP_CODE : $('#SP_CODE').val(),
		nepdate : $('#nepdate').val(),
		BANK_CODE : $('#BANK_CODE').val(),
		REMARKS : $('#remarks').val(),
		AMT : $('#AMT').val(),
		DATA : JSON.stringify(temparray)

	}, function(response) {
		
		alert((response));
		$('#savebtn').prop('disabled', false);
		$("#savebtn").html('response.substring(0, 6)');

		if (response.substring(0, 6) === "Sucess") {
			$("#transno").val(response.substring(40));
			$("#savebtn").html('Saved');
			$('#savebtn').prop('disabled', true);
			$("#hiddentransno").val(response.substring(40));
			$('#printbtn').prop('disabled', false);

		}

		// this.disabled=false;
	});
	$('.overlay').fadeOut();

	// alert(JSON.stringify(temparray));
}
