new Vue({
    el: "#app",
    data: {
        pojo: {
            phone_number: '',
            password: '',
        },
        smsCode: '',//这个code是从网页中获取到的
        password: '',

        dialogFormVisible:false,
    },
    methods: {
        sendSms() {
            //send sms
            let _this = this;
            axios.get('/shaohuashuwu_war_exploded/smsCodeSession/sendSms/' +
                this.pojo.phone_number).then(response => {
                console.log(response.data);
            }).catch(error => {
                console.log(error);
            });
        },
        save() {
            let _this = this;
            let regPhoto = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
            let pPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
            if (this.pojo.phone_number === "") {
                this.$message({
                    type: 'error',
                    message: '请输入手机号码！'
                });
                return false;
            } else if (!regPhoto.test(this.pojo.phone_number)) {
                this.$message({
                    type: 'error',
                    message: '手机号格式有误！'
                });
                return false;
            }else if (this.smsCode === "") {
                this.$message({
                    type: 'error',
                    message: '请输入验证码！'
                });
                return false;
            }else if ( !pPattern.test(this.pojo.password)) {
                this.$message({
                    type: 'error',
                    message: '密码格式不正确！'
                });
                return false;
            }else if (this.password ==="") {
                this.$message({
                    type: 'error',
                    message: '请输入确认密码！'
                });
                return false;
            }else if (this.password !== this.pojo.password) {
                
                this.$message({
                    type: 'error',
                    message: '两次输入的密码不一致！'
                });
                return false;
            }else {
                axios.get("/shaohuashuwu_war_exploded/smsCodeSession/compareSms/" +
                    this.smsCode + "/" + this.pojo.phone_number).then(resp => {

                    let registerResult = resp.data;

                    if (registerResult === false) {
                        this.$message({
                            type: 'error',
                            message: '验证码超时或错误！'
                        });
                        return false;
                    } else {
                        axios.get('/shaohuashuwu_war_exploded/userInfoController/userRegister_general/' +
                            this.pojo.phone_number + '/' + this.pojo.password + '/' + this.smsCode).then(response => {

                            let loginResult = response.data;

                            if (loginResult === false) {
                                this.$message({
                                    type: 'error',
                                    message: '用户已存在，注册失败！'
                                });
                            } else {
                                //window.location.assign("../pages/Success.html");
                                this.$message({
                                    type: 'success',
                                    message: '注册成功。'
                                });
                            }
                        }).catch(error => {
                            console.log(error);
                            alert("响应失败");
                        });

                    }
                }).catch(error => {
                    console.log(error);
                });

            }
        }
    }
});