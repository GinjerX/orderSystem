 $(function () {

        $("#menulist").click(function (){
            $("#details").load("menus.html");
        });
       /* $("#menuadd").click(function (){
            $("#details").load("menusAdd.html");
        });*/
        $("#orderquery").click(function () {
            $("#details").load("orderQuery.html");
        });
});