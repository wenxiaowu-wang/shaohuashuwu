new Vue({
    el: "#app",
    data: {
        userInfo: {
            phone_number: '',
            password: '',
        },
        phone_number: '',
        user_id: '',
        user_name: '',
        head_portrait: '',
        smsCode: '',//这个code是从网页中获取到的
        password: '',

        dialogFormVisible: true,//书籍评论控制的dialog

        activeName: 'first'
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        },
        yz() {
            let regphoto = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
            if (!regphoto.test(this.phone_number)) {
                this.$message({
                    type: "error",
                    message: "手机号格式错误，请重新输入"
                });
            }
        },
        normalLogin() {

            let regPhoto = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;

            if (this.userInfo.phone_number === "") {
                this.$message({
                    type: 'error',
                    message: '请输入手机号码！'
                });
                return;
            } else if (!regPhoto.test(this.userInfo.phone_number)) {
                this.$message({
                    type: 'error',
                    message: '手机号格式有误！'
                });
                return false;
            }
            if (this.userInfo.password === "") {
                this.$message({
                    type: 'error',
                    message: '请输入密码！'
                });
                return;
            } else {
                axios.get('/shaohuashuwu/userInfoController/userLogin/' +
                    this.userInfo.phone_number + '/' + this.userInfo.password).then(response => {

                    let loginResult = response.data;
                    console.log(typeof (loginResult));
                    if (loginResult === false) {
                        this.$message({
                            type: 'error',
                            message: '账号或密码错误！'
                        });
                    } else {
                        //将手机号  密码 code等传入session
                        this.smsCode = '0';
                        axios.get("/shaohuashuwu/userSession/saveUserPhoneNumber/" +
                            this.userInfo.phone_number+"/"+ this.userInfo.password +"/"+ this.smsCode ).then(res => {
                        }).catch(error => {
                            console.log(error);
                            alert("响应失败");
                        });

                       // 获取用户数据
                        axios.get('/shaohuashuwu/userInfoController/getUserIdNameAndHeaderByPhone/' +
                            this.userInfo.phone_number).then(response => {
                            let user_id1 = response.data["user_id"];
                            let user_name1 = response.data["user_name"];
                            let head_portrait1 = response.data["head_portrait"];
                            let gender1 = response.data["gender"];
                            let birthday1 = response.data["birthday"];
                            let area1 = response.data["area"];
                            //保存到userSession
                            axios.post("/shaohuashuwu/userSession/savePersonalData/" +
                                user_id1 + "/" + user_name1 + "/" + head_portrait1+"/"+ gender1 + "/" + birthday1 + "/" + area1).then(resp => {
                                console.log("用户数据同步到session中。");
                                //alert("用户数据同步到session中")
                                window.location.assign("../pages/userMainInterface.html");
                                // window.location.assign("../pages/novelDetailsInterface.html");
                            }).catch(error => {
                                console.log("用户数据同步session失败。"+error);
                            });
                        }).catch(error => {
                            console.log("获取用户信息失败。" + error);
                        });

                    }
                }).catch(error => {
                    console.log(error);
                    alert("响应失败");
                });
            }
        },
        sendSms() {

                axios.get('/shaohuashuwu/smsCodeSession/loginSendSms/' +
                    this.phone_number).then(response => {
                    console.log(response.data);
                }).catch(error => {
                    console.log(error);
                });

        },
        codeLogin() {

            let regPhoto = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;

            if (this.phone_number === "") {
                this.$message({
                    type: 'error',
                    message: '请输入手机号码！'
                });
                return;
            } else if (!regPhoto.test(this.phone_number)) {
                this.$message({
                    type: 'error',
                    message: '手机号格式有误！'
                });
                return false;
            } else if (this.smsCode === "") {
                this.$message({
                    type: 'error',
                    message: '请输入6位验证码！'
                });
                return;
            } else {
                axios.get('/shaohuashuwu/smsCodeSession/compareSms/' +
                    this.smsCode + "/" + this.phone_number).then(response => {

                    let compareResult = response.data;

                    console.log(typeof (compareResult));
                    if (compareResult === false) {
                        this.$message({
                            type: 'error',
                            message: '验证码超时或错误！'
                        });
                        return false;
                    } else {

                        axios.get('/shaohuashuwu/userInfoController/userLogin2/' +
                            this.phone_number).then(response => {
                            let loginResult = response.data;
                            console.log(typeof (loginResult));
                            if (loginResult === false) {
                                this.$message({
                                    type: 'error',
                                    message: '该账号未注册！'
                                });
                            } else {
                                this.password = '0';
                                axios.get("/shaohuashuwu/userSession/saveUserPhoneNumber/" +
                                    this.phone_number+"/"+ this.password +"/"+ this.smsCode ).then(res => {
                                }).catch(error => {
                                    console.log(error);
                                    alert("响应失败");
                                });

                                // 获取用户数据
                                axios.get('/shaohuashuwu/userInfoController/getUserIdNameAndHeaderByPhone/' +
                                    this.phone_number).then(response => {
                                    let user_id1 = response.data["user_id"];
                                    let user_name1 = response.data["user_name"];
                                    let head_portrait1 = response.data["head_portrait"];
                                    let gender1 = response.data["gender"];
                                    let birthday1 = response.data["birthday"];
                                    let area1 = response.data["area"];
                                    //保存到userSession

                                    axios.post("/shaohuashuwu/userSession/savePersonalData/" +
                                        user_id1 + "/" + user_name1 + "/" + head_portrait1+"/"+ gender1 + "/" + birthday1 + "/" + area1).then(resp => {
                                        console.log("用户数据同步到session中。");

                                        // window.location.assign("../pages/bookShelfInterface.html");
                                        // window.location.assign("../pages/readingHistory.html");
                                        // window.location.assign("../pages/myHomePage.html");
                                        window.location.assign("../pages/userMainInterface.html");
                                    }).catch(error => {
                                        console.log("用户数据同步session失败。"+error);
                                    });
                                }).catch(error => {
                                    console.log("获取用户信息失败。" + error);
                                });
                            }
                        }).catch(error => {
                            console.log(error);
                            alert("响应失败1");
                        });
                    }
                }).catch(error => {
                    console.log(error);
                    alert("响应失败2");
                });
            }
        },

        gotoRegistration(){

            window.location.assign("../pages/usersRegistrationInterface.html");
        },
        goToYinSi(){
            window.location.assign("../pages/privacyPolicy.html");
        },
        goToXieYi(){
            window.location.assign("../pages/userAgreement.html");
        }

    }
});