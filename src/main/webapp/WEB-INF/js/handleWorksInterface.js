new Vue({
    el: '#administrators_mainInfoRight_div_id',
    data() {
        return {
            reportWholeInfoVo:[],

        }
    },
    methods: {
        /*
        * 初始化界面
        * */
        /*获取举报信息*/
        getReportWholeInfoVo(){
            var _this = this;
            axios.post('/shaohuashuwu/reportWholeInfoVoController/getreportWholeInfoVoByreport_id')
                .then(function (response){
                    _this.reportWholeInfoVo = response.data;
                    _this.reportWholeInfoVo.report_time = _this.timestampToTime(_this.reportWholeInfoVo.report_time);


                    if(_this.reportWholeInfoVo.report_reason == 1){
                        _this.reportWholeInfoVo.report_reason = "政治敏感"
                    }if(_this.reportWholeInfoVo.report_reason == 2){
                        _this.reportWholeInfoVo.report_reason = "政治敏感"
                    }if(_this.reportWholeInfoVo.report_reason == 3){
                        _this.reportWholeInfoVo.report_reason = "政治敏感"
                    }if(_this.reportWholeInfoVo.report_reason == 4){
                        _this.reportWholeInfoVo.report_reason = "政治敏感"
                    }if(_this.reportWholeInfoVo.report_reason == 5){
                        _this.reportWholeInfoVo.report_reason = "政治敏感"
                    }if(_this.reportWholeInfoVo.report_reason == 6){
                        _this.reportWholeInfoVo.report_reason = "政治敏感"
                    }if(_this.reportWholeInfoVo.report_reason == 7){
                        _this.reportWholeInfoVo.report_reason = "政治敏感"
                    }if(_this.reportWholeInfoVo.report_reason == 8){
                        _this.reportWholeInfoVo.report_reason = "政治敏感"
                    }if(_this.reportWholeInfoVo.report_reason == 9){
                        _this.reportWholeInfoVo.report_reason = "政治敏感"
                    }

                    if(_this.reportWholeInfoVo.handle_state == 2){
                        _this.reportWholeInfoVo.handle_state = "章节可阅读"
                    }
                    if(_this.reportWholeInfoVo.handle_state == 3){
                        _this.reportWholeInfoVo.handle_state = "章节下架"
                    }

                    console.log("----"+JSON.stringify(_this.reportWholeInfoVo));

                }.bind(this))
                .catch(function (error){
                    console.log(error);
                })
        },


        /*点击事件*/
        /*点击修改状态按钮*/
        updateReportInfoByReport_id(val){
            var handle_state =val;
            var _this = this;
            axios.post('/shaohuashuwu/reportInfoController/updateReportInfoByReport_id?handle_state='+handle_state)
                .then(function (response){
                    if(handle_state == 4){
                        window.location.assign("../pages/changeTheProcessingResultInterface.html");
                    }else {
                        window.location.assign("../pages/handlingReportInfoInterface.html");
                    }

                }.bind(this))
                .catch(function (error){
                    console.log(error);
                })

        },

        /*返回更改处理结果界面*/
        gotochangeTheProcessingResultInterface(){
            window.location.assign("../pages/changeTheProcessingResultInterface.html");
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
        }

    },
    created:function (){ //页面加载时查询所有
        this.getReportWholeInfoVo();

    }
})