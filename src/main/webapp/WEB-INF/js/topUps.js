let topUpDialogForm_vm = new Vue({
    el:"#dialogForm",
    data:{
        dialogFormVisible: true,
        alipayVisible: false,
        weChatVisible: false,
        paymentCheck: "",
        form: {
            userId:0,
            name: "汪汪汪",
            method:"0",
            money:10
        },
        formLabelWidth: '120px',
        avatarURL:"../images/avatar/001.jpg",
        avatarURL2:'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        alipayImg: "../images/pay/支付宝.png",
        weChatImg: "../images/pay/微信.png",
    },
    methods:{
        topUpsCheck(){
            //校验提交信息
            let method = parseInt(this.form.method);//将表单数据中的支付方式数据类型由string转为number
            if(this.form.money<10 || this.form.name==="" || method>2){
                alert("提交信息错误");
            }else{
                if (method=== 0){
                    this.$message({
                        type:"info",
                        message:"请用支付宝扫一扫支付"
                    });
                    //支付宝方式付款
                    this.alipayVisible = true;
                }else if (method=== 1){
                    //微信方式付款
                    this.$message({
                        type:"info",
                        message:"请用微信扫一扫支付"
                    });
                    this.weChatVisible = true;
                }else{
                    this.$message({
                        type:"error",
                        message:"系统出错"
                    });
                    // alert("money type ="+typeof (this.form.money)+"值为【"+this.form.money+"】"+" method type ="+typeof (this.form.method)+"值为【"+this.form.method+"】");
                }
            }
        },
        paymentCommit(){
            if (this.paymentCheck === "5006"){
                this.topUpsCommit();
            }else{
                this.$message({
                    type:"error",
                    message:"付款接收码错误，请重试!"
                });
            }
        },
        topUpsCommit(){
            let method = parseInt(this.form.method);//将表单数据中的支付方式数据类型由string转为number
            alert("alert "+JSON.stringify(this.form.name)+"["+this.form.userId+"]");
            axios.post("/shaohuashuwu_war_exploded/transactionInfoController/topUpsGoldBean/"
                +method+"/"
                +this.form.money
            ).then(response => {
                if (response.data){
                    this.dialogFormVisible = false;
                    this.alipayVisible = false;
                    this.weChatVisible = false;
                    this.paymentCheck = "";
                    this.$message({
                        type:"success",
                        message:"充值成功！"
                    });
                }else{
                    this.paymentCheck = "";
                    this.$message({
                        type:"error",
                        message:"充值失败！"
                    });
                }
            }).catch(error => {
                this.paymentCheck = "";
                this.dialogFormVisible = false;
                this.alipayVisible = false;
                this.weChatVisible = false;
                alert("系统错误"+error);
            });
        },
    },
    mounted(){

        // {
        // 此代码块获取不到session中的值，或是读取值都为null
        // let username = JSON.parse(sessionStorage.getItem("user_name"));
        // let userid = JSON.parse(sessionStorage.getItem("user_id"));
        // alert("进入购买金豆页面"+"["+userid+"] ["+username+"]");
        // }
        //获取当前用户的名字以及ID（后端模拟session域取值）
        axios.get("/shaohuashuwu_war_exploded/userSession/getUser").then(response =>{
            console.log("ok"+JSON.stringify(response.data));
            let info = response.data;
            console.log("ok2"+JSON.stringify(info["user_id"]));
            console.log("ok3"+JSON.stringify(info["user_name"]));

            let user_id = JSON.stringify(info["user_id"]);
            user_id = parseInt(user_id);
            let user_name = info["user_name"];
            this.form.name = user_name;
            this.form.userId = user_id;
            alert("id = ["+user_id+"] name = ["+user_name+"]");

        }).catch(error =>{
            console.log(error);
        });
    },
})