String.prototype.log = function(tag){
	tag = (tag ? tag : "");

	try{
		console.log(tag+this);
	}catch(e){
		alert(tag+this);
	}
}

var MyFn = {
		
		getAjax:function(url, data){
			return $.ajax({
				
				url:url
				,	type:"POST"
				,	data:data
				,	dataType:"json"
				,	async:true					
			});
		}
}