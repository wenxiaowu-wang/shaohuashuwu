let authorVerifyManageInterface_vm = new Vue({
    el:"#authorVerifyManage",
    data:{
        topTips:"作者验证管理",
        activeIndex: "0",
        checkedList:[],   //被选中的值
        dialogFormVisible: true,
        title:"成为作者",
        interfaceState:0, //三种状态{0成为作者、1作者登录验证、2作者修改二级密码}
        pass:"",
        checkPass:"",
        oldPass:"",

        user_id:0,
        user_name:"我系成龙",
        checkImage:{
            old_img:{
                name:"el-icon-success",
                color:"green",
                display:"none"
            },
            pass_img:{
                name:"el-icon-success",
                color:"green",
                display:"none"
            },
            check_img:{
                name:"el-icon-success",
                color:"green",
                display:"none"
            },
        },      //输入框右边的检查结果显示（图片）
        passwordTips:"必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间",
        assignUrl:"../pages/workbenchInterface.html",
    },
    methods:{
        //跳转到首页
        backHomePage(){
            window.location.assign("../pages/userMainInterface.html");
        },
        handleSelect(key, keyPath) {
            console.log("当前导航在:(key,keyPath)"+key, keyPath);
            switch (key){
                // case "1":console.log("当前所在URL："+window.location.href);break;
                case "1":window.location.assign("../pages/myHomePage.html");break;
                case "2":window.location.assign("../pages/bookShelfInterface.html");break;
                case "3":window.location.assign("../pages/messageCenterInterface.html");break;
                case "4":window.location.assign("../pages/personalAccountInterface.html");break;
                default:break;
            }
        },
        onInputPasswordFocus(index){
            //密码输入框的获取焦点事件
            switch (index){
                case 1:{
                    //原密码输入框
                    this.checkImage.old_img.display = "none";
                    break;
                }case 2:{
                    //二级密码输入框
                    this.checkImage.pass_img.display = "none";
                    break;
                }case 3:{
                    //密码确认输入框
                    this.checkImage.check_img.display = "none";
                    break;
                }default:break;
            }
        },
        onInputPasswordBlur(index){
            let password,state;
            state = this.interfaceState;
            switch (index) {
                case 1:{
                    password = this.oldPass;
                    break;
                }case 2:{
                    password = this.pass;
                    break;
                }case 3:{
                    password = this.checkPass;
                    break;
                }default:break;
            }
            //密码输入框的失焦事件    params:{密码，界面状态，输入框序号（1、2、3）}
            let checkResult = this.checkIsLegalOfPassWord(password);
            console.log("pass:"+password+"  state="+ state+"  index="+index);
            console.log("checkResult = "+checkResult);
            switch (state) {
                case 0:{
                    //【成为作者】的情况，成为作者界面只有二级密码、输入确认的输入框
                    switch (index) {
                        case 2:{
                            //二级密码输入框
                            if (!checkResult.notNull){
                                //输入为空
                                this.checkImage.pass_img.name = "el-icon-error";
                                this.checkImage.pass_img.color = "red";
                                this.checkImage.pass_img.display = "block";
                                console.log("【成为作者】二级密码输入框失焦事件——输入为空");
                            }else{
                                if (!checkResult.isLegal){
                                    //输入不合法
                                    this.checkImage.pass_img.name = "el-icon-error";
                                    this.checkImage.pass_img.color = "red";
                                    this.checkImage.pass_img.display = "block";
                                    this.$message({
                                        type:'error',
                                        message:this.passwordTips
                                    });
                                }else{
                                    this.checkImage.pass_img.name = "el-icon-success";
                                    this.checkImage.pass_img.color = "green";
                                    this.checkImage.pass_img.display = "block";
                                    console.log("【成为作者】二级密码输入框失焦事件——输入合法")
                                }
                            }
                            break;
                        }case 3:{
                            //确认密码的输入框
                            if (!checkResult.notNull){
                                //输入为空
                                this.checkImage.check_img.name = "el-icon-error";
                                this.checkImage.check_img.color = "red";
                                this.checkImage.check_img.display = "block";
                            }else{
                                if (password === this.pass){
                                    //两次密码一致
                                    this.checkImage.check_img.name = "el-icon-success";
                                    this.checkImage.check_img.color = "green";
                                    this.checkImage.check_img.display = "block";
                                }else{
                                    //两次密码不一致
                                    this.checkImage.check_img.name = "el-icon-error";
                                    this.checkImage.check_img.color = "red";
                                    this.checkImage.check_img.display = "block";
                                    this.$message({
                                        type:'error',
                                        message:'两次密码不一致！'
                                    });
                                }
                            }
                            break;
                        }default:break;
                    }
                    break;
                }case 1:{
                    //【作者登录验证】，该界面只有二级密码输入框
                    switch (index) {
                        case 2:{
                            //二级密码输入框
                            if (!checkResult.notNull){
                                //输入为空
                                this.checkImage.pass_img.name = "el-icon-error";
                                this.checkImage.pass_img.color = "red";
                                this.checkImage.pass_img.display = "block";
                            }else{
                                if (!checkResult.isLegal){
                                    //输入不合法
                                    this.checkImage.pass_img.name = "el-icon-error";
                                    this.checkImage.pass_img.color = "red";
                                    this.checkImage.pass_img.display = "block";
                                    this.$message({
                                        type:'error',
                                        message:this.passwordTips
                                    });
                                }else{
                                    //输入合法
                                    this.checkImage.pass_img.name = "el-icon-success";
                                    this.checkImage.pass_img.color = "green";
                                    this.checkImage.pass_img.display = "none";
                                }
                            }
                            break;
                        }default:break;
                    }
                    break;
                }case 2:{
                    //【修改二级密码】，该界面有原密码、二级密码、确认密码输入框
                    switch (index) {
                        case 1:{
                            //原密码输入框
                            if (!checkResult.notNull){
                                //输入为空
                                this.checkImage.old_img.name = "el-icon-error";
                                this.checkImage.old_img.color = "red";
                                this.checkImage.old_img.display = "block";
                            }else{
                                if (!checkResult.isLegal){
                                    //输入不合法
                                    this.checkImage.old_img.name = "el-icon-error";
                                    this.checkImage.old_img.color = "red";
                                    this.checkImage.old_img.display = "block";
                                    this.$message({
                                        type:'error',
                                        message:this.passwordTips
                                    });
                                }else{
                                    //输入合法
                                    this.checkImage.old_img.name = "el-icon-success";
                                    this.checkImage.old_img.color = "green";
                                    this.checkImage.old_img.display = "none";
                                }
                            }
                            break;
                        }case 2:{
                            //二级密码输入框
                            if (!checkResult.notNull){
                                //输入为空
                                this.checkImage.pass_img.name = "el-icon-error";
                                this.checkImage.pass_img.color = "red";
                                this.checkImage.pass_img.display = "block";
                            }else{
                                if (!checkResult.isLegal){
                                    //输入不合法
                                    this.checkImage.pass_img.name = "el-icon-error";
                                    this.checkImage.pass_img.color = "red";
                                    this.checkImage.pass_img.display = "block";
                                    this.$message({
                                        type:'error',
                                        message:this.passwordTips
                                    });
                                }else{
                                    this.checkImage.pass_img.name = "el-icon-success";
                                    this.checkImage.pass_img.color = "green";
                                    this.checkImage.pass_img.display = "block";
                                }
                            }
                            break;
                        }case 3:{
                            //密码确认输入框
                            if (!checkResult.notNull){
                                //输入为空
                                this.checkImage.check_img.name = "el-icon-error";
                                this.checkImage.check_img.color = "red";
                                this.checkImage.check_img.display = "block";
                            }else{
                                if (password === this.pass){
                                    //两次密码一致
                                    this.checkImage.check_img.name = "el-icon-success";
                                    this.checkImage.check_img.color = "green";
                                    this.checkImage.check_img.display = "block";
                                }else{
                                    //两次密码不一致
                                    this.checkImage.check_img.name = "el-icon-error";
                                    this.checkImage.check_img.color = "red";
                                    this.checkImage.check_img.display = "block";
                                    this.$message({
                                        type:'error',
                                        message:'两次密码不一致！'
                                    });
                                }
                            }
                            break;
                        }default:break;
                    }
                    break;
                }default:break;
            }
        },
        checkIsLegalOfPassWord(password){
            /**
             * 1、字符串为空
             * 2、字符串不符合密码规则
             */
            let result = {
                notNull:true,
                isLegal:true
            };
            if (password === ""){
                this.$message({
                    type:'error',
                    message:'输入不可为空！'
                });
                result.notNull = false;
                return result;
            }else{
                //必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间
                let pPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$/;
                //输出 true
                result.isLegal = pPattern.test(password);
                return result;
            }
        },
        updateInterfaceState(index){
            //更新界面的操作
            this.resetForm();   //先重置表单
            this.interfaceState = index;
            switch (index) {
                case 0:{
                    this.topTips = "作者验证";
                    this.title = "成为作者";
                    break;
                }case 1:{
                    this.topTips = "作者验证";
                    this.title = "作者登录验证";
                    break;
                }case 2:{
                    this.topTips = "作者验证";
                    this.title = "修改二级密码";
                    break;
                }default:break;
            }
        },
        submitForm() {
            //提交表单
            if (this.interfaceState === 0){
                //indexOf(""):返回某个指定的字符串值在字符串中首次出现的位置，如果没有对应字符串，返回-1
                if(this.checkImage.pass_img.name.indexOf("success") !== -1 &&
                    this.checkImage.check_img.name.indexOf("success") !== -1) {
                    //输入是合法的格式正确
                    //成为作者的提交操作
                    this.becomeAnAuthorSubmit();
                }else{
                    this.$notify.error({
                        title: '错误',
                        message: '输入数据存在不合法项，请检查。'
                    });
                }
            }else if(this.interfaceState === 1){
                //作者登录验证二级密码的操作
                if(this.checkImage.pass_img.name.indexOf("success") !== -1) {
                    //输入是合法的格式正确
                    //成为作者的提交操作
                    this.authorVerificationSubmit();
                }else{
                    this.$notify.error({
                        title: '错误',
                        message: '输入数据存在不合法项，请检查。'
                    });
                }
            }else if(this.interfaceState === 2){
                //作者修改二级密码的操作
                if(this.checkImage.old_img.name.indexOf("success") !== -1 &&
                    this.checkImage.pass_img.name.indexOf("success") !== -1 &&
                    this.checkImage.check_img.name.indexOf("success") !== -1) {
                    //输入是合法的格式正确
                    //成为作者的提交操作
                    this.modifySecondaryPasswordSubmit();
                }else{
                    this.$notify.error({
                        title: '错误',
                        message: '输入数据存在不合法项，请检查。'
                    });
                }
            }
        },
        becomeAnAuthorSubmit(){
            //检测已经尚未存在二级密码的输入情况 成为作者
            //发送成为作者的请求
            axios.post("/shaohuashuwu/userInfoController/addDoublePassword/"+this.pass).then(resp =>{
                if (resp.data){
                    this.$message({
                        type:'success',
                        message:'恭喜您成为一名作者，期待您的作品！'
                    });
                    console.log("申请成功！恭喜成为一名作者。");
                    //跳转界面
                    window.location.assign(this.assignUrl);
                }else{
                    this.$message({
                        type:'error',
                        message:'申请成为作者失败！'
                    });
                    console.log("申请成为作者失败！");
                }
            }).catch(error =>{
                this.$message({
                    type:'error',
                    message:'申请成为作者的请求发送失败！'
                });
                console.log("请求发送失败！"+error);
            });
        },
        authorVerificationSubmit(){
            //检测已经存在二级密码的输入情况   作者登录验证二级密码
            //发送作者登录验证的请求
            axios.post("/shaohuashuwu/userInfoController/isOldDoublePassword/"+this.pass).then(resp =>{
                if (resp.data){
                    this.$message({
                        type:'success',
                        message:'验证成功，欢迎来到作者专区！'
                    });
                    console.log("验证成功，欢迎来到作者专区");
                    //跳转界面
                    window.location.assign(this.assignUrl);
                }else{
                    this.$message({
                        type:'error',
                        message:'二级密码错误，作者验证失败！'
                    });
                    console.log("二级密码错误，作者验证失败！");
                }
            }).catch(error =>{
                this.$message({
                    type:'error',
                    message:'作者登录验证请求发送失败！'
                });
                console.log("作者登录验证请求发送失败！"+error);
            });
        },
        modifySecondaryPasswordSubmit(){
            //作者修改二级密码
            //发送修改二级密码请求
            axios.post("/shaohuashuwu/userInfoController/updateDoublePassword/"+this.oldPass+"/"+this.pass).then(resp =>{
                let str = resp.data;
                let str_int = parseInt(str);
                switch (str_int) {
                    case 0:{
                        this.$message({
                            type:'error',
                            message:'原密码错误，请检查输入！'
                        });
                        console.log("原密码错误");
                        break;
                    }case 1:{
                        this.$message({
                            type:'success',
                            message:'修改成功，将跳转到作者专区'
                        });
                        console.log("修改成功，跳转到作则专区");
                        window.location.assign(this.assignUrl);  //跳转界面
                        break;
                    }case 2:{
                        this.$message({
                            type:'error',
                            message:'网络或系统原因，修改失败！'
                        });
                        console.log("网络或系统原因，修改失败！");
                        break;
                    }default:break;
                }
            }).catch(error =>{
                this.$message({
                    type:'error',
                    message:'修改二级密码请求发送失败！'
                });
                console.log("修改二级密码请求发送失败！"+error);
            });
        },
        resetForm() {
            //重置表单
            this.pass = "";
            this.checkPass = "";
            this.oldPass = "";
            this.checkImage.old_img.name = "el-icon-error";
            this.checkImage.pass_img.name = "el-icon-error";
            this.checkImage.check_img.name = "el-icon-error";
            this.checkImage.old_img.display = "none";
            this.checkImage.pass_img.display = "none";
            this.checkImage.check_img.display = "none";
        },
        toReportAnnouncement(){
            window.open("../pages/reportAnnouncement.html");
        },
    },
    mounted(){

        //获取当前用户的ID以及将要打赏作品的ID（后端模拟session域取值）
        axios.get("/shaohuashuwu/userSession/getUser").then(response =>{
            let info = response.data;
            console.log("ok2"+JSON.stringify(info["user_id"]));
            this.user_name = info["user_name"];
            let user_id = JSON.stringify(info["user_id"]);
            user_id = parseInt(user_id);
            this.user_id = user_id;
        }).catch(error =>{
            this.$confirm('获取用户信息失败！', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //执行操作

            }).catch(() => {
                //执行操作
            });
            console.log(error);
        });
        axios.get("/shaohuashuwu/userInfoController/isAlreadyBecameAuthor").then(resp =>{
           if (!resp.data){
               //尚未成为作者
               this.updateInterfaceState(0);
           }else{
               //已经成为了作者
               this.updateInterfaceState(1);
           }
        }).catch(error =>{
            this.$message({
                type:'error',
                message:'判断是否已经成为作者失败'
            });
            console.log("判断是否已经成为作者失败"+error);
        });
        //created钩子函数是在HTML渲染前执行
    },
})