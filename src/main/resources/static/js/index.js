   $(document).ready(function(){
	      loadTotal();
	      $('#cart .top-cart-content').load('/freshfood/cartitems ul#cart-slidebar');
    });

   // Delte cart item
   function del(element){
	      let id = $(element).attr("item-id");
          $.ajax({
	          url: '/freshfood/cart/delete/'+id,
              type: 'delete',
              dataType: 'json',
              success: function(json){
	              $('#cart .top-cart-content').load('/freshfood/cartitems ul#cart-slidebar');
    	          $('.cartCount2').html('('+json['total']+')');
              },
              error: function(){
	             alert("không thành công !");
              }
          });
     };

     // load cart items
     function loadTotal(){
	    $.ajax({
		     url: '/freshfood/cart/total',
             type: 'get',
             dataType: 'json',
		     success: function(json){
    	          $('.cartCount2').html('('+json['total']+')');
              },
              error: function(){
	             alert("không thành công !");
              }
	    });
     }
