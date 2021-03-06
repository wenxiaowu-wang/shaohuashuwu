new Vue({
    el: '#rankinginclue-id',
    data() {
        return {
            /*
            * 界面相应数据
            * */
            //主类型显示
            mainlabel:'全部',
            //当前时间显示
            nowtime:'',
            //榜单时间类型
            rankingTimeType:'总榜',
            //榜单类型
            rankingName:'',
            //数量名
            numName:'',
            //用户
            userInfo:[],




            /*
            * 获取数据
            * */
            //排行类型
            rankingType:'',
            //排行作品
            rankingInfoVoList:[],


            /*
            * 上传数据
            * */
            //发送数据
            rankingInputInfoVo: {
                work_main_label: ' ',
                getneednum: 10,
                transaction_type:' ',
                transaction_time:' ',
                time_type:1,
            },


        }
    },
    methods: {

        /**
         * 初始化界面
         */
        //初始化界面类型
        isRankingInfo(){
            var _this = this;
            axios.post('/shaohuashuwu/junitSessionController/getRankingTypeInSession')
                .then(function (response) {
                   _this.rankingType = response.data;
                   _this.rankingInputInfoVo.transaction_type = _this.rankingType;
                   _this.rankingListInfo(_this.rankingType);
                   if(_this.rankingType == 4){
                       _this.rankingName = "收藏榜";
                       _this.numName = "收藏";
                   }
                   else if(_this.rankingType == 3){
                       _this.rankingName = "推荐榜";
                       _this.numName = "推荐";
                   }
                   else if(_this.rankingType == 2){
                       _this.rankingName = "订阅榜";
                       _this.numName = "次订阅";
                   }
                   else if(_this.rankingType == 1){
                       _this.rankingName = "打赏榜";
                       _this.numName = "金豆";
                   }
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        //获取排行信息
        rankingListInfo(val){

            this.rankingInputInfoVo.transaction_type = val;
            var _this = this;
            axios.post('/shaohuashuwu/rankingInfoController/getRankingInfo',this.rankingInputInfoVo)
                .then(function (response) {
                    _this.rankingInfoVoList = response.data;

                    if(_this.rankingType == 2){
                        for (var i=0;i<_this.rankingInfoVoList.length;i++){
                            _this.rankingInfoVoList[i].sumnum = _this.rankingInfoVoList[i].sumnum/10;
                        }
                    }

                    for (var i=0;i<_this.rankingInfoVoList.length;i++){
                        if(_this.rankingInfoVoList[i].work_serial_state == 2){
                            _this.rankingInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.rankingInfoVoList[i].work_serial_state == 1){
                            _this.rankingInfoVoList[i].work_serial_state = "连载";
                        }
                        /*字数大于10万字就变为小数*/
                        if(_this.rankingInfoVoList[i].sumnum>100000){
                            var divisornum = _this.rankingInfoVoList[i].sumnum/10000;
                            _this.rankingInfoVoList[i].sumnum = divisornum.toFixed(2);
                            _this.rankingInfoVoList[i].sumnum = _this.rankingInfoVoList[i].sumnum + "万";
                        }

                    }

                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },



        /*
        * 点击事件
        * */
        //点击作品类型
        clickmainlabel(mian_label){
            this.getnowtime();
            this.mainlabel = mian_label;
            if(mian_label == "全部"){
                this.rankingInputInfoVo.work_main_label = null;
            }
            else {
                this.rankingInputInfoVo.work_main_label = mian_label;
            }
            this.rankingListInfo(this.rankingType);
        },
        //日期类型
        clickneedallRanking(){
            this.getnowtime();
            this.rankingInputInfoVo.time_type = 1;
            this.rankingTimeType = '总榜'
            this.rankingListInfo(this.rankingType);
        },
        clickneedmonthRanking(){
            this.getnowtime();
            this.rankingInputInfoVo.time_type = 2;
            this.rankingTimeType = '月榜'
            this.rankingListInfo(this.rankingType);
        },
        clickneeddayRanking(){
            this.getnowtime();
            this.rankingInputInfoVo.time_type = 3;
            this.rankingTimeType = '日榜'
            this.rankingListInfo(this.rankingType);
        },

        //点击榜单类型
        clicklistbutton(rankingType){
            if (rankingType == 0){
                window.location.assign("../pages/novelRankingInterface.html");
            }
            else {
                var _this = this;
                axios.post('/shaohuashuwu/junitSessionController/addRankingTypeToSession?rankingType='+rankingType)
                    .then(function (response) {
                        window.location.assign("../pages/sectionRankingInterface.html");
                    })
                    .catch(function (error){
                        console.log(error);
                        alert("相应失败");
                    })
            }
        },

        //点击书籍名称
        clickWork_name(work_id){
            console.log("作品id："+work_id);
            var _this = this;
            axios.post('/shaohuashuwu/worksInfoController/addWork_idSession?work_id='+work_id)
                .then(function (response) {
                    window.location.assign("../pages/novelDetailsInterface.html");
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },

        //点击加入书架
        //加入书架
        addToBookshelf(selectwork_id){
            console.log("上传信息======")
            var user_id= this.userInfo.user_id;
            var work_id = selectwork_id;
            console.log("上传信息"+user_id+"---"+work_id)
            axios.post("/shaohuashuwu/bookshelfInfoController/addToBookshelf/" +
                user_id + "/" + work_id ).then(resp => {
                let Result = resp.data;
                console.log("数据同步存到数据库。"+Result);
                if(Result==true){
                    this.$message({
                        type: 'success',
                        message: '加入书架成功！'
                    });
                }else{
                    this.$message({
                        type: 'error',
                        message: '小说已加入书架，无需重复添加!'
                    });
                }
            }).catch(error => {
                console.log("数据同步存到数据库失败。"+error);
            });
        },





        /*
         * 工具类
        * */
        getnowtime(){
            var nowTime = new Date();
            var time=nowTime.toLocaleTimeString();
            this.nowtime = time;
        },

        startUserInfo(){
            var _this = this;
            axios.get('/shaohuashuwu/userInfoController/getUserLoginInfo')
                .then(function (response){
                    _this.userInfo = response.data;
                })
                .catch(function (error){
                    alert("相应失败");
                })
        },


    },
    created:function (){ //页面加载时查询所有

        //加载界面判断界面是哪个排行榜
        this.isRankingInfo();

        this.getnowtime();

        this.startUserInfo();
    }
})