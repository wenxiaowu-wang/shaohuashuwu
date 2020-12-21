let changeAdminPasswordInterface_vm = new Vue({
    el:"#changeAdminPassword",
    data:{
        checkedList:[],   //被选中的值
        dialogFormVisible: true,
        title:"修改管理员密码",
        pass:"",
        checkPass:"",
        oldPass:"",

        adminId:0,
    },
    methods:{
        submitForm() {
            //检测已经存在二级密码的输入情况
            if (this.pass === "" || this.checkPass === "" || this.oldPass === ""){
                this.$message({
                    type: 'error',
                    message: '输入不可为空！'
                });
            }else{
                if (this.passwordPattern()){
                    if(this.pass !== this.checkPass){
                        this.$message({
                            type: 'error',
                            message: '两次密码不一致！'
                        });
                    }else{
                        this.updatePassword(this.adminId,this.oldPass,this.pass);
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
        updatePassword(adminId,oldPass,pass){
            //两次密码一致,设置二级密码
            axios.post("/shaohuashuwu_war_exploded/adminInfoController/adminLogin/" +
                oldPass).then(response =>{
                let isOldPass = response.data;
                if (isOldPass){
                    //原密码正确
                    //更新二级密码
                    axios.post("/shaohuashuwu_war_exploded/adminInfoController/updateAdminPassword/" +
                        pass).then(resp =>{
                        console.log("更新密码成功。");
                        this.$message({
                            type:'success',
                            message:'更新密码成功。'
                        });
                        //关闭当前界面
                        window.opener=null;
                        window.open('','_self');
                        window.close();
                    }).catch(error =>{
                        console.log("更新密码失败。"+error);
                    });
                }else{
                    this.$message({
                        type: 'error',
                        message: '原密码输入错误！'
                    });
                }

            })
        },
        resetForm() {
            //重置表单
            this.pass = "";
            this.checkPass = "";
            this.oldPass = "";
        },
    },
    mounted(){
        //获取当前管理员账号（后端session域取值）
        axios.get("/shaohuashuwu_war_exploded/adminSession/getAdmin").then(response =>{
            let info = response.data;
            let admin_id = info["admin_id"];
            this.adminId = admin_id;
        }).catch(error =>{
            console.log("获取管理员信息失败！"+error);
        });
        //created钩子函数是在HTML渲染前执行，若涉及到尚未渲染的变量，会无法找到
    },
})