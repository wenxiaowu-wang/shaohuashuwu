new Vue( {
    el: '#disanhang_id',
    data() {
        return {
            /**
             * 界面数据*/
            //轮播图
            rotationimgList:[
                {
                    ahref:'https://fanyi.baidu.com/?aldtype=16047#auto/zh',
                    imghref:'../images/rotation_advertisement1.jpg'
                },
                {
                    ahref:'https://blog.csdn.net/weixin_38636668/article/details/90482585?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param',
                    imghref:'../images/rotation_advertisement2.jpg',
                },
                {
                    ahref:'https://www.baidu.com/?tn=44004473_1_oem_dg',
                    imghref:'../images/rotation_advertisement3.jpg',
                },
                {
                    ahref:'https://haokan.baidu.com/?sfrom=baidu-top',
                    imghref:'../images/rotation_advertisement4.jpg',
                },

            ],


            /**
             * 上传数据
             * */
            //榜单信息
            rankingInputInfoVo: {
                //作品主类型
                work_main_label: ' ',
                //榜单类型
                transaction_type: ' ',
                //榜单更新时间
                //设置时间类型为当日推荐票
                transaction_time:' ',
            },
            /*排行榜信息*/
            rankingInputInfoVo: {
                work_main_label: ' ',
                getneednum: 10,
                transaction_type:' ',
                transaction_time:3,
                time_type:3,
            },




            /**
             * 相应数据*/
            //每个分类数量
            difvolenumInfo:{
                xuanhuannum:'',
                qihuannum:'',
                wuxianum:'',
                xianxianum:'',
                dushinum:'',
                xianshinum:'',
                lishinum:'',
                junshinum:'',
                youxinum:'',
                xuanyinum:'',
                kehuannum:'',
                tiyunum:'',
                qingxiaoshuonum:'',
            },



            //榜单数据
            recommendInfoVoList:[],
            collectionInfoVoList:[],
            subscribeInfoVoList:[],
            rewardInfoVoList:[],

            /*获取不同状态的作品信息*/
            //完结作品
            endWorkWholeInfoVoList:[],
            //连载作品
            serialWorkWholeInfoVoList:[],
            //最新作品
            newWorkWholeInfoVoList:[],







        };
    },
    methods: {

        /**
         * 初始化信息方法
         */
        /*获取每个分类作品数量*/
        finddifvolenum(){
            var _this = this;
            axios.get('http://localhost:8080/worksInfoController/getdifvolenum')
                .then(function (response){
                    _this.difvolenumInfo = response.data;//相应分类作品数量
            })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },



        //收藏
        collectionListInfo(val){

            console.log("收藏榜");
            this.rankingInputInfoVo.transaction_type = 4;
            var _this = this;

            axios.post('http://localhost:8080/rankingInfoController/getRankingInfo',this.rankingInputInfoVo)
                .then(function (response4) {
                    _this.collectionInfoVoList = response4.data;
                    for (var i=0;i<_this.collectionInfoVoList.length;i++) {
                        if(_this.collectionInfoVoList[i].work_serial_state == 2){
                            _this.collectionInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.collectionInfoVoList[i].work_serial_state == 1){
                            _this.collectionInfoVoList[i].work_serial_state = "连载";
                        }
                    }
                    console.log("收藏榜------："+JSON.stringify(_this.collectionInfoVoList));

                     _this.recommendListInfo(3);

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
            axios.post('http://localhost:8080/rankingInfoController/getRankingInfo', this.rankingInputInfoVo)
                .then(function (response3) {
                    _this.recommendInfoVoList = response3.data;
                    for (var i=0;i<_this.recommendInfoVoList.length;i++) {
                        if(_this.recommendInfoVoList[i].work_serial_state == 2){
                            _this.recommendInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.recommendInfoVoList[i].work_serial_state == 1){
                            _this.recommendInfoVoList[i].work_serial_state = "连载";
                        }
                    }

                        _this.subscribeListInfo(2);


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
            axios.post('http://localhost:8080/rankingInfoController/getRankingInfo',this.rankingInputInfoVo)
                .then(function (response) {
                    _this.subscribeInfoVoList = response.data;

                    console.log("订阅榜------："+JSON.stringify(_this.subscribeInfoVoList));
                    for (var i=0;i<_this.subscribeInfoVoList.length;i++){
                        if(_this.subscribeInfoVoList[i].work_serial_state == 2){
                            _this.subscribeInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.subscribeInfoVoList[i].work_serial_state == 1){
                            _this.subscribeInfoVoList[i].work_serial_state = "连载";
                        }

                    }

                        _this.rewardListInfo(1);

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
            axios.post('http://localhost:8080/rankingInfoController/getRankingInfo',this.rankingInputInfoVo)
                .then(function (response) {
                    _this.rewardInfoVoList = response.data;
                    for (var i=0;i<_this.rewardInfoVoList.length;i++) {
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





        /*获取不同状态的作品信息*/
        startDifferentStateWork(){
            // alert("信息显示：");
            //完本
            this.getdifferentStateWorkInfo(1);

            //连载
            this.getdifferentStateWorkInfo(2);

            //最近上架
            this.getdifferentStateWorkInfo(3);

        },
        /*获取提供状态下的作品信息*/
        getdifferentStateWorkInfo(differentStateWork){
            var _this = this;
            axios.get('http://localhost:8080/workWholeInfoVoController/getdifferentStateWork?differentStateWork='+differentStateWork)
                .then(function (response){

                    if(differentStateWork == 1){

                        _this.endWorkWholeInfoVoList = response.data;//完本
                        console.log("完本："+JSON.stringify(_this.endWorkWholeInfoVoList));
                    }
                    else if(differentStateWork == 2){
                        _this.serialWorkWholeInfoVoList = response.data;//连载
                        console.log("连载："+JSON.stringify(_this.serialWorkWholeInfoVoList));
                    }
                    else if(differentStateWork == 3){
                        _this.newWorkWholeInfoVoList = response.data;//新本
                        console.log("新本："+JSON.stringify(_this.newWorkWholeInfoVoList));
                    }


                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },




        /**
         * 点击事件
         */
        //点击书籍名称
        clickWork_name(work_id){
            console.log("作品id："+work_id);
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/addWork_idSession?work_id='+work_id)
                .then(function (response) {
                    window.location.assign("../pages/novelDetailsInterface.html");
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        //点击用户名字
        clickUser_name(author_id){
            console.log("作者id："+author_id);
            var _this = this;
            axios.post('http://localhost:8080/userInfoController/addAuthor_idSession?author_id='+author_id)
                .then(function (response) {
                    window.location.assign("../pages/authorInfoInterface.html");
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        }


    },
    created:function (){ //页面加载时查询所有

        // this.finddifvolenum();
        this.startDifferentStateWork();
        this.collectionListInfo();

        // this.startrecommendInfoVoList();
        // this.startcollectionInfoVoList();
        // this.startsubscribeInfoVoList();
        // this.startrewardInfoVoList();
    }
})