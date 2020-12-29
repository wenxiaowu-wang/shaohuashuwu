new Vue({
    // router,
    el: '#personalityRecommend_id',
    data() {
        return {
            drawer: false,
            //作品信息
            workWholeInfoVoList:[],

            /*排行榜信息*/
            rankingInputInfoVo: {
                work_main_label: ' ',
                getneednum: 10,
                transaction_type:' ',
                transaction_time:3,
                time_type:3,
            },
            //榜单数据
            recommendInfoVoList:[],



        }
    },
    methods:{
        /*
        * 初始化界面
        * */
        //获取信息
        getRecommmend(){
            console.log("推荐信息")
            var _this = this;
            axios.post('http://localhost:8080/workWholeInfoVoController/getworkWholeInfoVoByuser_id')
                .then(function (response) {
                    _this.workWholeInfoVoList = response.data;

                    for (var i=0;i<_this.workWholeInfoVoList.length;i++){
                        if(_this.workWholeInfoVoList[i].work_serial_state == 2){
                            _this.workWholeInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.workWholeInfoVoList[i].work_serial_state == 1){
                            _this.workWholeInfoVoList[i].work_serial_state = "连载";
                        }
                        /*字数大于10万字就变为小数*/
                        if(_this.workWholeInfoVoList[i].work_vote_num>100000){
                            var divisornum = _this.workWholeInfoVoList[i].work_vote_num/10000;
                            _this.workWholeInfoVoList[i].work_vote_num = divisornum.toFixed(2);
                            _this.workWholeInfoVoList[i].work_vote_num = _this.workWholeInfoVoList[i].work_vote_num + "万";
                        }
                    }
                }.bind(this))
                .catch(function (error){
                    console.log(error);
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
                    console.log("排行信息")
                })
                .catch(function (error) {
                    console.log(error);
                    alert("相应失败");
                })
        },
    },
    created:function (){ //页面加载时查询所有
        this.getRecommmend();
        this.recommendListInfo();
    }
})