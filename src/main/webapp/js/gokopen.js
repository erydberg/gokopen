var csspath = path + '/css/';
$(document).ready(function(){
	$('.switchstatus').on("click", ".patrol", function(){
		var status = $(this).data('status');
		var patrolid = $(this).data('id');
		if(status=="registered"){
			changetoactive(this, patrolid);
		}
		if(status=="active"){
			changetofinished(this, patrolid);
		}
		if(status=="finished"){
			changetoresigned(this, patrolid);
		}
		if(status=="resigned"){
			changetoregistered(this, patrolid);
		}
	}); 
	});
function changetoactive(obj, id){
    jQuery.ajax({
     url    : path + '/startfinish/activate/'+id,
     type   : 'PUT',

     success : function(data){
    	 	$(obj).data('status','active');
			$(obj).attr("src",csspath + "active.png");
               },
      error : function(errorData){
				alert("Det gick inte att uppdatera statusen");
              }
    });
}
function changetofinished(obj, id) {
	jQuery.ajax({
		url : path + '/startfinish/finished/' + id,
		type : 'PUT',

		success : function(data) {
			$(obj).data('status','finished');
			$(obj).attr("src",csspath + "finished.png");
		},
		error : function(errorData) {
			alert("Det gick inte att uppdatera statusen");
		}
	});
}
function changetoresigned(obj, id) {
	jQuery.ajax({
		url : path + '/startfinish/resigned/' + id,
		type : 'PUT',

		success : function(data) {
			$(obj).data('status','resigned');
			$(obj).attr("src",csspath + "resigned.png");
		},
		error : function(errorData) {
			alert("Det gick inte att uppdatera statusen");
		}
	});
}
function changetoregistered(obj, id) {
	jQuery.ajax({
		url : path + '/startfinish/registered/' + id,
		type : 'PUT',

		success : function(data) {
			$(obj).data('status','registered');
			$(obj).attr("src",csspath + "registered.png");
		},
		error : function(errorData) {
			alert("Det gick inte att uppdatera statusen");
		}
	});
}
function reloadPage(){
	   window.location = window.location.pathname;
}

