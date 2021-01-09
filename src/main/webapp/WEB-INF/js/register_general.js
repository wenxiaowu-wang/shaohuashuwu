let register_vm = new Vue({
    el: "#app",
    data: {
        pojo: {
            phone_number: '',
            password: '',
        },
        smsCode: '',//这个code是从网页中获取到的
        password: '',
        button: {
            canClick: false,
            loading: false,
            buttonNum: "发送验证码",
        },

    },
    methods: {
        onClick(index) {
            let time = index;
            let clock = window.setInterval(() => {
                index--;
                this.checkButtonNum(index);
                if (index <= 0) {
                    window.clearInterval(clock);//清除函数流
                }
            }, 1000);
        },
        checkButtonNum(countdown) {
            if (countdown === 0) {
                this.button.canClick = false;
                this.button.loading = false;
                this.button.buttonNum = "再次发送";
            } else {
                this.button.canClick = true;
                this.button.loading = true;
                this.button.buttonNum = "重新发送(" + countdown + ")";
            }
        },
        yz() {
            let regphoto = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
            if (!regphoto.test(this.pojo.phone_number)) {
                this.$message({
                    type: "error",
                    message: "手机号格式错误，请重新输入"
                });
            }
        },
        setTime(obj) {

            let countdown = 60;
            if (countdown === 0) {
                obj.removeAttribute("disabled");
                obj.value = "再次发送";
                countdown = 60;
                return;
            } else {
                obj.setAttribute("disabled", true);
                obj.value = "重新发送(" + countdown + ")";
                countdown--;
            }
            setTimeout(function () {
                setTime(obj)
            }, 1000)
        },
        sendSms() {
            axios.get('/shaohuashuwu/smsCodeSession/sendSms/' +
                this.pojo.phone_number).then(response => {
                console.log(response.data);
            }).catch(error => {
                console.log(error);
            });
        },
        save() {

            let regPhoto = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
            let pPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,16}$/;
            if (this.pojo.phone_number === "") {
                this.$message({
                    type: 'error',
                    message: '请输入手机号码'
                });
                return;
            } else if (!regPhoto.test(this.pojo.phone_number)) {
                this.$message({
                    type: 'error',
                    message: '手机号格式有误！'
                });
                // return false;
            } else if (this.smsCode === "") {
                this.$message({
                    type: 'error',
                    message: '请输入验证码！'
                });
            } else if (this.pojo.password === '') {
                this.$message({
                    type: 'error',
                    message: '请设置登录密码！'
                });
            } else if (!pPattern.test(this.pojo.password)) {
                this.$message({
                    type: 'error',
                    message: '密码格式不正确！'
                });
                // return false;
            } else if (this.password === "") {
                this.$message({
                    type: 'error',
                    message: '请输入确认密码！'
                });
                // return false;
            } else if (this.password !== this.pojo.password) {

                this.$message({
                    type: 'error',
                    message: '两次输入的密码不一致！'
                });
                // return false;
            } else {
                axios.get("/shaohuashuwu/smsCodeSession/compareSms/" +
                    this.smsCode + "/" + this.pojo.phone_number).then(resp => {

                    let registerResult = resp.data;

                    if (registerResult === false) {
                        this.$message({
                            type: 'error',
                            message: '验证码超时或错误！'
                        });
                        // return false;
                    } else {
                        axios.get('/shaohuashuwu/userInfoController/userRegister_general/' +
                            this.pojo.phone_number + '/' + this.pojo.password + '/' + this.smsCode).then(response => {

                            let loginResult = response.data;

                            if (loginResult === false) {
                                this.$message({
                                    type: 'error',
                                    message: '用户已存在，注册失败！'
                                });
                            } else {
                                this.$message({
                                    type: 'success',
                                    message: '注册成功。'
                                });
                                this.pojo.phone_number="";
                                this.smsCode="";
                                this.password="";
                                this.pojo.password="";
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
        },
        goToLogin() {
            window.location.assign("../pages/UserLogin.html");
        }
    }
});