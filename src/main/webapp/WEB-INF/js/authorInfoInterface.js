new Vue({
    el: '#authorInfoandwork_div_id',
    data() {
        return {
            authorInfo:[],
            worksWholeInfoVoList:[],
            workTotal:0,
        }
    },
    methods: {

        /*
        * 初始化数据
        * */
        //查看作者信息
        getAuthorInfoByUserclick(){
            console.log("获取作者信息")
            var _this = this;
            axios.post('http://localhost:8080/authorInfoController/getAuthorInfoByUserclick')
                .then(function (response){

                    _this.authorInfo = response.data;
                    console.log("作者信息："+JSON.stringify(_this.authorInfo));
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应作者信息失败");
                })
        },
        //获取作品信息
        getworkWholeInfoVoByauthor_id(){
            var _this = this;
            console.log("获取作者作品信息");
            axios.post('http://localhost:8080/workWholeInfoVoController/getworkWholeInfoVoByauthor_id')
                .then(function (response) {
                    _this.worksWholeInfoVoList = response.data;
                    _this.workTotal=_this.worksWholeInfoVoList.length;
                    console.log("======="+JSON.stringify(_this.worksWholeInfoVoList));

                    for( var i = 0 ;i<_this.worksWholeInfoVoList.length;i++){
                        /*将1或2转为连载或完结*/
                        if( _this.worksWholeInfoVoList[i].work_serial_state == 1){
                            _this.worksWholeInfoVoList[i].work_serial_state = "连载";

                        }
                        else if(_this.worksWholeInfoVoList[i].work_serial_state == 2){
                            _this.worksWholeInfoVoList[i].work_serial_state = "完结";
                        }

                        /*推荐票大于10万就变为小数*/
                        if(_this.worksWholeInfoVoList[i].work_vote_num>100000){
                            var divisornum = _this.worksWholeInfoVoList[i].work_vote_num/10000;
                            _this.worksWholeInfoVoList[i].work_vote_num = divisornum.toFixed(2);
                            _this.worksWholeInfoVoList[i].work_vote_num = _this.worksWholeInfoVoList[i].work_vote_num + "万";
                        }
                        else {

                        }

                        /*时间戳转换*/
                        _this.worksWholeInfoVoList[i].chapter_time = _this.timestampToTime(_this.worksWholeInfoVoList[i].chapter_time);
                        console.log("----"+_this.worksWholeInfoVoList[i].chapter_time)
                    }

                })
                .catch(function (error){
                    alert("相应失败");
                })
        },


        /*
       * 工具类
       * */
        /*时间戳类型转换*/
        timestampToTime(timestamp) {
            var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            Y = date.getFullYear() + '-';
            M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            D = date.getDate() + ' ';
            h = date.getHours() + ':';
            m = date.getMinutes() + ':';
            s = date.getSeconds();
            return Y+M+D+h+m+s;
        },

    },
    created:function (){ //页面加载时查询所有
        this.getAuthorInfoByUserclick();
        this.getworkWholeInfoVoByauthor_id();
    }
})