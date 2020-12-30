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
        backAdminHomePage(){
            window.location.assign("../pages/handleWorksInterface.html");
        },
        submitForm() {
            //检测已经存在二级密码的输入情况
            if (this.pass === "" || this.checkPass === "" || this.oldPass === ""){
                this.$message({
                    type: 'error',
                    message: '输入不可为空！'
                });
            }else{
                if (this.passwordPattern(this.pass)){
                    if(this.pass !== this.checkPass){
                        this.$message({
                            type: 'error',
                            message: '两次密码不一致！'
                        });
                    }else{
                        this.updatePassword(this.adminId,this.oldPass,this.pass);
                    }
                }else{
                    //！最少6位，包括至少1个大写字母，1个小写字母，1个数字
                    this.$message({
                        type: 'error',
                        message: '新密码格式错误!'
                    });
                }
            }
        },
        passwordPattern(password){
            //必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间
            let pPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$/;
            //输出 true
            let result = false;
            result = pPattern.test(password);
            return result;
        },
        updatePassword(adminId,oldPass,pass){
            //更新二级密码
            axios.post("/shaohuashuwu/adminInfoController/updateAdminPassword/" +
                oldPass+"/"+pass).then(resp =>{
                console.log("更新密码成功。");
                let updateResult = parseInt(resp.data);
                switch (updateResult) {
                    case 0:{
                        this.$message({
                            type: 'error',
                            message: '原密码输入错误，请重新输入。'
                        });
                        break;
                    }case 1:{
                        this.$message({
                            type:'success',
                            message:'修改成功，请妥善保管新的密码。'
                        });
                        //关闭当前界面
                        window.opener=null;
                        // window.open('','_self');
                        window.location.assign("../pages/handleWorksInterface.html");
                        window.close();
                        break;
                    }case 2:{
                        this.$message({
                            type:'error',
                            message:'系统异常，修改密码失败！'
                        });
                        break;
                    }default:break;
                }
            }).catch(error =>{
                this.$message({
                    type:'error',
                    message:'系统异常，修改密码失败！'
                });
                console.log("更新密码失败。"+error);
            });
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
        axios.get("/shaohuashuwu/adminSession/getAdmin").then(response =>{
            let info = response.data;
            this.adminId = info["admin_id"];
        }).catch(error =>{
            this.$confirm('获取管理员信息失败！', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //执行操作
                this.$message({
                    type:'success',
                    message:'修改成功，请妥善保管新的密码。'
                });
            }).catch(() => {
                //执行操作
                this.$message({
                    type:'success',
                    message:'修改成功，请妥善保管新的密码。'
                });
            });
            console.log("获取管理员信息失败！"+error);
        });
        //created钩子函数是在HTML渲染前执行，若涉及到尚未渲染的变量，会无法找到
    },
})