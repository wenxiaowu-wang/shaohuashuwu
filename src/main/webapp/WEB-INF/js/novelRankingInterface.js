new Vue({
    el: '#rankinginclue_right_id',
    data() {
        return {
            /*
            * 界面数据变化
            * */
            nowtime:'',
            mainlabel:'全部',

            //发送数据
            rankingInputInfoVo: {
                work_main_label: ' ',
                getneednum: 10,
                transaction_type:' ',
                transaction_time:' ',
                time_type:1,
            },


            //接收数据
            recommendInfoVoList:[],
            collectionInfoVoList:[],
            subscribeInfoVoList:[],
            rewardInfoVoList:[],


        }
    },
    methods: {

        /*
         *初始化界面
         */
        /*
        * 获取事件，提交事件
        * */
        //收藏
        collectionListInfo(val){

            console.log("收藏榜");
            this.rankingInputInfoVo.transaction_type = 4;
            var _this = this;

            axios.post('/shaohuashuwu/rankingInfoController/getRankingInfo',this.rankingInputInfoVo)
                .then(function (response4) {
                    _this.collectionInfoVoList = response4.data;
                    for (var i=0;i<_this.collectionInfoVoList.length;i++) {
                        /*字数大于10万字就变为小数*/
                        if (_this.collectionInfoVoList[i].sumnum > 100000) {
                            var divisornum = _this.collectionInfoVoList[i].sumnum / 10000;
                            _this.collectionInfoVoList[i].sumnum = divisornum.toFixed(2);
                            _this.collectionInfoVoList[i].sumnum = _this.collectionInfoVoList[i].sumnum + "万";
                        }
                        if(_this.collectionInfoVoList[i].work_serial_state == 2){
                            _this.collectionInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.collectionInfoVoList[i].work_serial_state == 1){
                            _this.collectionInfoVoList[i].work_serial_state = "连载";
                        }
                    }
                    console.log("收藏榜------："+JSON.stringify(_this.collectionInfoVoList));
                    if(_this.rankingInputInfoVo.time_type == 1){
                        _this.recommendListInfo(4);

                    }
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        //推荐榜
        recommendListInfo(val){
            console.log("推荐榜");
            this.rankingInputInfoVo.transaction_type = 3;
            var _this = this;
            axios.post('/shaohuashuwu/rankingInfoController/getRankingInfo', this.rankingInputInfoVo)
                .then(function (response3) {
                    _this.recommendInfoVoList = response3.data;
                    for (var i=0;i<_this.recommendInfoVoList.length;i++) {

                        /*字数大于10万字就变为小数*/
                        if (_this.recommendInfoVoList[i].sumnum > 100000) {
                            var divisornum = _this.recommendInfoVoList[i].sumnum / 10000;
                            _this.recommendInfoVoList[i].sumnum = divisornum.toFixed(2);
                            _this.recommendInfoVoList[i].sumnum = _this.recommendInfoVoList[i].sumnum + "万";
                        }
                        if(_this.recommendInfoVoList[i].work_serial_state == 2){
                            _this.recommendInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.recommendInfoVoList[i].work_serial_state == 1){
                            _this.recommendInfoVoList[i].work_serial_state = "连载";
                        }
                    }
                    console.log("推荐榜------："+JSON.stringify(_this.recommendInfoVoList));
                    if(_this.rankingInputInfoVo.time_type == 1){
                        _this.subscribeListInfo(2);
                    }

                })
                .catch(function (error) {
                    console.log(error);
                    alert("相应失败");
                })

        },
        //订阅
        subscribeListInfo(val){
            console.log("订阅榜");
            this.rankingInputInfoVo.transaction_type = 2;
            var _this = this;
            axios.post('/shaohuashuwu/rankingInfoController/getRankingInfo',this.rankingInputInfoVo)
                .then(function (response) {
                    _this.subscribeInfoVoList = response.data;

                    console.log("订阅榜------："+JSON.stringify(_this.subscribeInfoVoList));
                    for (var i=0;i<_this.subscribeInfoVoList.length;i++){
                        _this.subscribeInfoVoList[i].sumnum = _this.subscribeInfoVoList[i].sumnum/10;
                        if (_this.subscribeInfoVoList[i].sumnum > 100000) {
                            var divisornum = _this.subscribeInfoVoList[i].sumnum / 10000;
                            _this.subscribeInfoVoList[i].sumnum = divisornum.toFixed(2);
                            _this.subscribeInfoVoList[i].sumnum = _this.subscribeInfoVoList[i].sumnum + "万";
                        }

                        if(_this.subscribeInfoVoList[i].work_serial_state == 2){
                            _this.subscribeInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.subscribeInfoVoList[i].work_serial_state == 1){
                            _this.subscribeInfoVoList[i].work_serial_state = "连载";
                        }

                    }
                    if(_this.rankingInputInfoVo.time_type == 1){
                        _this.rewardListInfo(1);
                    }
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        //打赏
        rewardListInfo(val){
            console.log("打赏榜");
            this.rankingInputInfoVo.transaction_type = 1;
            var _this = this;
            console.log("----"+_this.rankingInputInfoVo);
            axios.post('/shaohuashuwu/rankingInfoController/getRankingInfo',this.rankingInputInfoVo)
                .then(function (response) {
                    _this.rewardInfoVoList = response.data;
                    for (var i=0;i<_this.rewardInfoVoList.length;i++) {
                        /*字数大于10万字就变为小数*/
                        if (_this.rewardInfoVoList[i].sumnum > 100000) {
                            var divisornum = _this.rewardInfoVoList[i].sumnum / 10000;
                            _this.rewardInfoVoList[i].sumnum = divisornum.toFixed(2);
                            _this.rewardInfoVoList[i].sumnum = _this.rewardInfoVoList[i].sumnum + "万";
                        }
                        if(_this.rewardInfoVoList[i].work_serial_state == 2){
                            _this.rewardInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.rewardInfoVoList[i].work_serial_state == 1){
                            _this.rewardInfoVoList[i].work_serial_state = "连载";
                        }
                    }
                    console.log("打赏榜------："+JSON.stringify(_this.rewardInfoVoList));
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },

        /*
        * 点击事件
        * */
        //点击总榜
        clickallRanking(val){
            this.getnowtime();
            this.rankingInputInfoVo.time_type = 1;
            if (val == 4){
                this.collectionListInfo(4);
            }
            else if (val == 3){
                this.recommendListInfo(3);
            }
            else if (val == 2){
                this.subscribeListInfo(2);
            }
            else if (val == 1){
                this.rewardListInfo(1);
            }
        },
        //点击月榜
        clickmonthRanking(val){
            this.getnowtime();
            this.rankingInputInfoVo.time_type = 2;
            console.log("时间类型：========");
            console.log("时间类型："+ this.rankingInputInfoVo.time_type);
            if (val == 4){
                this.collectionListInfo(4);
            }
            else if (val == 3){
                this.recommendListInfo(3);
            }
            else if (val == 2){
                this.subscribeListInfo(2);
            }
            else if (val == 1){
                this.rewardListInfo(1);
            }
        },
        //点击日榜
        clickdayRanking(val){
            this.getnowtime();
            this.rankingInputInfoVo.time_type = 3;
            if (val == 4){
                this.collectionListInfo(4);
            }
            else if (val == 3){
                this.recommendListInfo(3);
            }
            else if (val == 2){
                this.subscribeListInfo(2);
            }
            else if (val == 1){
                this.rewardListInfo(1);
            }
        },
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
            this.collectionListInfo();
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


    /*
    * 工具类
    * */
        getnowtime(){
            var nowTime = new Date();
            var time=nowTime.toLocaleTimeString();
            this.nowtime = time;
            },
    },
    created:function (){ //页面加载时查询所有
        this.collectionListInfo();
        this.getnowtime();

    }
})