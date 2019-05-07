 $(function () {
 		//toastr  js初始化
		toastr.options.positionClass = 'toast-top-center';
		//防止重复
		// toastr.options.preventDuplicates = true;

        $("#usermenulist").click(function (){
            $("#userdetails").load("userMenuList.html");
        });
        $("#shoppingcar").click(function (){
            $("#userdetails").load("shoppingCar.html");
        });
        $("#userorderquery").click(function () {
            $("#userdetails").load("userOrderList.html");
        });
});