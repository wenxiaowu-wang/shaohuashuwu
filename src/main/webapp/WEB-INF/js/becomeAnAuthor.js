let becomeAnAuthorInterface_vm = new Vue({
    el:"#becomeAnAuthor",
    data:{
        checkedList:[],   //被选中的值
        dialogFormVisible: true,
        title:"成为作者",
        isAlreadyAuthor:false, //是否已经成为作者
        pass:"",
        checkPass:"",
        oldPass:"",

        userId:0,
        userName:""
    },
    methods:{
        submitForm() {
            if (this.isAlreadyAuthor){
                //已经存在二级密码的情况
                this.checkInputAlready();
            }else{
                //尚未设置二级密码的情况
                this.checkInputNotAlready();
            }
        },
        checkInputAlready(){
            //检测已经存在二级密码的输入情况
            if (this.pass == "" || this.checkPass == "" || this.oldPass == ""){
                this.$message({
                    type: 'error',
                    message: '输入不可为空！'
                });
            }else{
                if (this.passwordPattern()){
                    if(this.pass != this.checkPass){
                        this.$message({
                            type: 'error',
                            message: '两次密码不一致！'
                        });
                    }else{
                        this.updateDoublePassword(this.userId,this.oldPass,this.pass);
                    }
                }else{
                    this.$message({
                        type: 'error',
                        message: '密码格式错误！最少6位，包括至少1个大写字母，1个小写字母，1个数字'
                    });
                }
            }
        },
        checkInputNotAlready(){
            //检测已经尚未存在二级密码的输入情况
            if (this.pass == "" || this.checkPass == ""){
                this.$message({
                    type: 'error',
                    message: '输入不可为空！'
                });
            }else{
                if (this.passwordPattern()){
                    if(this.pass != this.checkPass){
                        this.$message({
                            type: 'error',
                            message: '两次密码不一致！'
                        });
                    }else{
                        this.setDoublePassword(this.userId,this.pass);
                    }
                }else{
                    this.$message({
                        type: 'error',
                        message: '密码格式错误！最少6位，包括至少1个大写字母，1个小写字母，1个数字'
                    });
                }
            }
        },
        passwordPattern(){
            //密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字
            let pPattern = /^[A-Za-z0-9]+$/;
            //输出 true
            let result = false;
            result = pPattern.test(this.pass);
            return result;
        },
        updateDoublePassword(userId,oldPass,pass){
            //两次密码一致,设置二级密码
            axios.post("/shaohuashuwu_war_exploded/userInfoController/isOldDoublePassword/" +
                userId+"/"+oldPass).then(response =>{
                let isOldPass = response.data;
                console.log("isOldPass = "+isOldPass);
                if (isOldPass){
                    //原密码正确
                    //更新二级密码
                    axios.post("/shaohuashuwu_war_exploded/userInfoController/updateDoublePassword/" +
                        userId+"/"+pass).then(resp =>{
                        console.log("设置二级密码成功。");
                        //关闭当前界面
                        window.opener=null;
                        window.open('','_self');
                        window.close();
                    }).catch(error =>{
                        console.log("设置二级密码失败。"+error);
                    });
                }else{
                    this.$message({
                        type: 'error',
                        message: '原密码输入错误！'
                    });
                }

            })
        },
        setDoublePassword(userId,pass){
            //更新二级密码
            axios.post("/shaohuashuwu_war_exploded/userInfoController/updateDoublePassword/" +
                userId+"/"+pass).then(resp =>{
                console.log("设置二级密码成功。");
                //关闭当前界面
                window.opener=null;
                window.open('','_self');
                window.close();
            }).catch(error =>{
                console.log("设置二级密码失败。"+error);
            });
        },
        resetForm() {
            //重置表单
            this.pass = "";
            this.checkPass = "";
        },
        toReportAnnouncement(){
            window.open("../pages/reportAnnouncement.html");
        },
        reportingWorks(){
            if (this.checkedList.length < 1){
                this.$message({
                    type: 'info',
                    message: '举报原因不得少于一个，不得多于三个！'
                });
            }else{
                axios.post("/shaohuashuwu_war_exploded/reportInfoController/reportDetectionChapter/" +
                    this.userId+"/"+this.chapterId+"/"+this.checkedList).then(resp =>{
                   console.log("举报请求响应成功！");

                }).catch(error =>{
                    console.log("举报请求响应失败！"+error);
                });
            }
        },
    },
    mounted(){
        //获取当前用户的ID以及将要打赏作品的ID（后端模拟session域取值）
        axios.get("/shaohuashuwu_war_exploded/userSession/getUser").then(response =>{
            let info = response.data;
            console.log("ok2"+JSON.stringify(info["user_id"]));
            let userName = info["user_name"];
            this.userName = userName;
            let user_id = JSON.stringify(info["user_id"]);
            user_id = parseInt(user_id);
            this.userId = user_id;
            axios.get("/shaohuashuwu_war_exploded/userInfoController/isAlreadyBecameAuthor/" +
                user_id).then(resp =>{
               console.log("获取是否已经成为作者的信息。");
               let theResult = resp.data;
               console.log("是否已经成为了作者："+theResult);
               if (!theResult){
                   //已经成为作者
                   this.title = "成为作者";
                   this.isAlreadyAuthor = false;
               }else{
                   this.title = "更改二级密码";
                   this.isAlreadyAuthor = true;
               }
            }).catch(error =>{
                console.log("判断是否已经成为作者失败"+error);
            });
        }).catch(error =>{
            console.log(error);
        });
        //created钩子函数是在HTML渲染前执行，若涉及到尚未渲染的变量，会无法找到
    },
})