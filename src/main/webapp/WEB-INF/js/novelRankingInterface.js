new Vue({
    el: '#rankinginclue-id',
    data() {
        return {
            //发送数据
            rankingInputInfoVo: {
                work_main_label: ' ',
                transaction_type: ' ',
                transaction_time:' ',
            },


            //接收数据
            recommendInfoVoList:[],
            collectionInfoVoList:[],
            subscribeInfoVoList:[],
            rewardInfoVoList:[],


        }
    },
    methods: {
        //初始化界面
        startnovelRanking(){
            console.log("初始化界面")
            this.recommendListInfo(3);
            this.collectionListInfo(4);
            this.subscribeListInfo(1);
            this.rewardListInfo(2);
        },
        //推荐榜
        recommendListInfo(val){

            this.rankingInputInfoVo.transaction_type = 3;
            var _this = this;
            console.log("----"+_this.rankingInputInfoVo);
            axios.post('http://localhost:8080/rankingInfoController/selectRankingListInfo',this.rankingInputInfoVo)
                .then(function (response) {
                    _this.recommendInfoVoList = response.data;
                    console.log(_this.recommendInfoVoList);

                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        //收藏
        collectionListInfo(val){
            this.rankingInputInfoVo.transaction_type = 2;
            var _this = this;
            console.log("----"+_this.rankingInputInfoVo);
            axios.post('http://localhost:8080/rankingInfoController/selectRankingListInfo',this.rankingInputInfoVo)
                .then(function (response) {
                    _this.collectionInfoVoList = response.data;
                    console.log(_this.collectionInfoVoList);

                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        //订阅
        subscribeListInfo(val){
            this.rankingInputInfoVo.transaction_type = 2;
            var _this = this;
            console.log("----"+_this.rankingInputInfoVo);
            axios.post('http://localhost:8080/rankingInfoController/selectRankingListInfo',this.rankingInputInfoVo)
                .then(function (response) {
                    _this.subscribeInfoVoList = response.data;
                    console.log(_this.subscribeInfoVoList);

                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        //打赏
        rewardListInfo(val){
            this.rankingInputInfoVo.transaction_type = 1;
            var _this = this;
            console.log("----"+_this.rankingInputInfoVo);
            axios.post('http://localhost:8080/rankingInfoController/selectRankingListInfo',this.rankingInputInfoVo)
                .then(function (response) {
                    _this.rewardInfoVoList = response.data;
                    console.log(_this.rewardInfoVoList);

                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },



       //点击左侧榜单按钮
        clickalllistbutton(val){

        }

    },
    created:function (){ //页面加载时查询所有
        this.startnovelRanking();
    }
})