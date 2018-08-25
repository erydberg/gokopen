var csspath = path + '/css/';
$(document).ready(function(){
	$('.switchstatus').on("click", ".paid-action", function(){
		var status = $(this).data('status');
		var patrolid = $(this).data('id');
		if(status=="paid"){
			changetonotpaid(this, patrolid);
		}
		if(status=="notpaid"){
			changetopaid(this, patrolid);
		}
	}); 
	}); 
function changetopaid(obj, id){
    jQuery.ajax({
     url    : path + '/patrol/admin/setpaid/'+id,
     type   : 'PUT',

     success : function(data){
    	 	$(obj).data('status','paid');
			$(obj).html('<strong>Ja</strong>');
               },
      error : function(errorData){
				alert("Det gick inte att uppdatera statusen");
              }
    });
}
function changetonotpaid(obj, id) {
	jQuery.ajax({
		url : path + '/patrol/admin/setnotpaid/' + id,
		type : 'PUT',

		success : function(data) {
			$(obj).data('status','notpaid');
			$(obj).html('<strong>Nej</strong>');
			
		},
		error : function(errorData) {
			alert("Det gick inte att uppdatera statusen");
		}
	});
}

