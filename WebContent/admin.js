 $(function () {
		//toastr  js初始化
		toastr.options.positionClass = 'toast-top-center';
		//防止重复
		// toastr.options.preventDuplicates = true;
        $("#menulist").click(function (){
            $("#details").load("menus.html");
        });
        $("#menuadd").click(function (){
            $("#details").load("menuAdd.html");
        });
        $("#orderquery").click(function () {
            $("#details").load("order.html");
        });
});