
new Vue( {
    el: '#usertop_all_div_id',
    data() {
        return {
            /*获取用户信息*/
            userInfo:[],
            userNum:0,


            /*输入数据*/



            select_input:'',
            work_info:{
                work_name:'',

            },

        };
    },
    methods: {

        /*
        * 初始化加载事件*/
        /*获取用户信息*/
        startUserInfo(){
            var _this = this;
            axios.get('http://localhost:8080/userInfoController/getUserLoginInfo')
                .then(function (response){
                    _this.userInfo = response.data;
                })
                .catch(function (error){
                    alert("相应失败");
                })
        },

        /*获取注册人数*/
        startUserNum(){
            var _this = this;
            axios.get('http://localhost:8080/userInfoController/getUserNum')
                .then(function (response){
                    _this.userNum = response.data;
                    console.log("网站人数"+_this.userNum);
                })
                .catch(function (error){
                    alert("相应失败");
                })
        },






        /*
        * 点击事件
        * */
        /*顶部按钮·点击按钮跳转事件处理*/
        /*首页*/
        gotoUserMainIterface(){
            window.location.assign("../pages/userMainInterface.html");
        },
        /*全部作品*/
        gotoAllWorksInterface(){
            window.location.assign("../pages/allWorksInterface.html");
        },
        /*排行*/
        gotoNovelRankingInterface(){
            window.location.assign("../pages/novelRankingInterface.html");
        },
        /*推荐*/
        gotoPersonalityRecommendInterface(){
            window.location.assign("../pages/personalityRecommendInterface.html");
        },
        /*作者专区*/
        //跳转，未修改
        gotoAuthorPrefecture(){
            window.location.assign("../pages/userMainInterface.html");
        },
        /*个人中心*/
        //跳转，未修改
        gotoMysqelfHtml(){
            window.location.assign("../pages/myHomePage.html");
        },
        /*退出系统按钮*/
        //退出，未销毁session，未返回登录界面
        signOut(){
            this.$confirm('您将退出当前账号, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                    type: 'success',
                    message: '您已经退出当前账号!',
                    window:location.assign("../pages/novelRankingInterface.html"),
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消退出'
                });
            });
        },

        /*搜索按钮*/
        selectworkInfobyinfo(){
            console.log(this.select_input);
            //如果搜索内容为空进行提示，不为空进行跳转
            if(this.select_input == null || this.select_input == ''){
                console.log("搜索为空");
            }
            if(this.select_input != null && this.select_input != ''){

                this.work_info.work_name = this.select_input;
                var _this = this;
                console.log(_this.select_input);

                axios.post('http://localhost:8080/worksInfoController/addSelectInfotoSession',_this.work_info)
                    .then(function (response){
                        window.location.assign("../pages/keywordSearchResultInterface.html");
                    }.bind(this))
                    .catch(function (error){
                        console.log(error);
                        alert("相应失败");
                    })
            }
        },

        /*加入书架*/
        gotoBookshelf(){
            window.location.assign("../pages/bookShelfInterface.html");
        },



    },

    created:function (){ //页面加载时查询所有
        this.startUserInfo();
        this.startUserNum();
    }
})