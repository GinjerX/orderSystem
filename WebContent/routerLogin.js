
	
						//1. 定义组件
						var Login = {
							template:"#userlogin",
							data(){
								return{
									user:{
										username:"",
										age:""
										
									}
								}
							},
							methods:{
								Affirmlogin(){
									if(this.username == "" || this.password == "" ){
										alert("请输入姓名或者密码");
									}else{
									   axios.get("http://localhost:8080/orderSystem/userLoginServlet",{
										   params:this.user
									   }).then(res=>{
										   console.log(res);
										   if(res.data.error){
											   alert(res.data.msg);
										   }else{
											   if(res.data.body.userType == "1"){
												   console.log("用户登录");
											   }else{
												   console.log("管理员登录");
												   router.replace({path:"/admin"});
											   }
										   }
									   })	
									}	
								},
								replace(){
									router.replace({path:"/register"});
								}
							},
						}
						var Register = {
							template:"#register",
							data(){
								return{
									user:{
									    username:"",
									    password:"",
									    phone:"",
									    address:""
									
									},
									users:[]
								}
							},
							methods:{
							    addUser(){
									
							        this.users.push(this.user);
							        this.user = {}
									var obj = {
										userObj:JSON.stringify(this.users[0])
									}
							        axios.get("http://localhost:8080/orderSystem/registerServlet.do",{
							            params:obj
							        }).then(res=>{
										console.log(res);
										alert(res.data.msg);
										/* 
										toastr.success("注册成功"); */
							        });
							    },
								replace(){
									router.replace({path:"/userlogin"});
								}
							}
							
						}
						var Admin = {
							template:"#admin",
							data(){
								return {
									
								}
							},
							methods:{
								replace(){
									router.replace({path:"/admin/menulist"});
								},
								a(){	
									router.replace({path:"/admin/menuAdd"});
								},
								
							}
						
						}
						
						var Menuslist ={
							template:"#menuslist",
							data(){
								return{
									menus:[],
									menuss:{
									    name:"",
									    price:"",
									    discountPrice:"",
									    groundingTime:"",
									    state:"",
									    isTop:"",
										menuquery:""
									
									},
									menusObj:{},
									selectIndex:""
								}
								
							},
							mounted(){
								this.menuslist();
							},
							methods:{
								menuslist(){
									axios.get("http://localhost:8080/orderSystem/MenuAllServlet").then(res=>{
										console.log(res);
										this.menus = res.data.body;
									});
								},
								deletemenu(menuId){//根据菜单id修改菜单列表删除功能(软删除)
									axios({
										method:"get",
										url:"CancelOrderStateServlet",
										params:{
											menuId:menuId,
											state:4
										}
								
									}).then(res=>{
										this.menuslist();
										toastr.success("删除成功");
								
									}
								   )
								},
								querymenusName(){//根据menuName 查询订单
									var menusss = {
										obj: JSON.stringify(this.menuss.menuquery)
									} 
									
									axios({
										 method:"get",
										 url:"MenuAllServlet",
										 params:{
											 menusname:this.menuss.menuquery
										 }
									
									 }).then(res=>{
										 console.log(res+"aaaaaaaaaa")
										 this.menus = res.data.body;
									 })
								},
								b(index){
									this.selectIndex=index;
									this.menuss = this.menus[index];
									router.replace({path:"/admin/update",query:{
										menussstr:this.menuss,
										selectIndex:this.selectIndex
									}});
								}
							},
						}
						var MenusAdd ={ 
							template:"#menuAdd",
							data(){
								return{
									menus:{
									    name:"",
									    price:"",
									    discountPrice:"",
									    stateName:"",
									    isTop:""
									},
									menuspush:[]
								}
							},
							methods:{
							    addMenus(){
							        this.menuspush.push(this.menus);
							        this.menus = {};
							        var p = {
							            obj:JSON.stringify(this.menuspush[0])
							        }
							    	axios.get("http://localhost:8080/orderSystem/MenuAddServlet",{
							                params:p
							            }).then(res=>{
							                console.log(res);
							    	});
							    },
							}
							
						}
						var Update = {
							template:"#menuupdate",
							mounted(){
								this.menuinfo = this.$route.query.menussstr;
							},
							data(){
								return{
									menuinfo:{
									    name:"",
									    price:"",
									    discountPrice:"",
									    groundingTime:"",
									    state:"",
									    isTop:""
									
									}
								}
							},
							methods:{
								updatemnus(){//修改菜单
									var p = {
										obj:JSON.stringify(this.menuinfo)
									}
									axios.get("http://localhost:8080/orderSystem/UpdateMenuServlet",{
											params:p
										}).then(res=>{
											this.menuinfo = {};
											router.replace({path:"/admin/menulist"});
									});
								
								},
							}
						}
						
						
						
						//2. 配置路由与组件之间的关系
						const routes = [
							{
								path:"/userlogin",
								component:Login,
								
							},
							{
								path:"/admin",
								component:Admin,
								children:[
									{
										path:"menulist",
										component:Menuslist
										
									},
									{
										path:"menuAdd",
										component:MenusAdd
									},
									{
										path:"update",
										component:Update
									}
									
								]
							},
							{
								path:"/register",
								component:Register
							},
							{path:"*",redirect:"/userlogin"} //重定向
						]
						
						//3. 创建路由实例
						const router = new VueRouter({
							routes,
							linkActiveClass:"action",
							//mode:"history"
						});
						
						var vm = new Vue({
							el:"#vueel",
							data:{
								
							},
							router
						})
						
				
