new Vue({
    // router,
    el: '#personalityRecommend_id',
    data() {
        return {
            drawer: false,
            //作品信息
            workWholeInfoVoList:[],
        }
    },
    methods:{
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

            //console.log("最终信息==============="+JSON.stringify(this.workWholeInfoVo));
        },

        test(){
            console.log("最终信息-----------"+JSON.stringify(this.workWholeInfoVoList));
        }
    },
    created:function (){ //页面加载时查询所有
        this.getRecommmend();
    }
})