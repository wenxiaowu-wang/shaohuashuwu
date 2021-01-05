let adminLoginInterface_vm = new Vue({
    el:"#adminLogin",
    data:{
        labelPosition:"right",
        formLabel:{
            admin_id: "",
            admin_password: ""
        }
    },
    methods: {
        findData() {
            return {
                adminData: []
            }
        },
        adminLogin() {
            if (this.formLabel.admin_id === "" || this.formLabel.admin_password === "") {
                this.$message({
                    type: 'error',
                    message: '输入不可为空！'
                });
            } else {
                axios.post("/shaohuashuwu/adminInfoController/adminLogin/" +
                    this.formLabel.admin_id + "/" + this.formLabel.admin_password).then(response =>{
                    //response.data本身即为字符串格式，JSON处理是为了将整个response对象解析成字符串，否则直接打印response为Object
                    let theResult = response.data;
                    if (theResult) {
                        //登录成功，进行的操作:在当前界面跳进入管理员主界面；
                        //跳转界面
                        window.location.assign("../pages/handlingReportInfoInterface.html");
                    } else {
                        this.$message({
                            type: 'error',
                            message: '账号或密码错误！'
                        });
                    }
                }).catch(error =>{
                    console.log("登录失败+" + error);
                });
            }
        },
    }
})