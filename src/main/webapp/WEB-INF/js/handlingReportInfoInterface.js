new Vue({
    el: '#administrators_mainInfoRight_div_id',
    data() {
        return {

            /*
            * 相应数据
            * */
            /*举报信息*/
            reportWholeInfoVoList:[],
            /*举报数量*/
            total:'',

            /*
            * 提交数据
            * */
            //提交的页数
            adminSelectInfoVo:{
                works_page:1,
                works_page_num:8,
                work_name:' ',
                report_time:' ',
            }
        }
    },
    methods: {
        /*
        * 初始化界面
        * */
        /*获取举报信息*/
        getReportWholeInfoVoList(){
            var _this = this;
            axios.post('http://localhost:8080/reportWholeInfoVoController/getreportWholeInfoVoList',_this.adminSelectInfoVo)
                .then(function (response){
                    _this.reportWholeInfoVoList = response.data;
                    for(var i=0;i<_this.reportWholeInfoVoList.length;i++){
                        _this.reportWholeInfoVoList[i].report_time = _this.timestampToTime(_this.reportWholeInfoVoList[i].report_time);
                    }
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                })
        },
        /*获取举报信息数量*/
        getReportWholeInfoTotal(){
            var _this = this;
            axios.post('http://localhost:8080/reportWholeInfoVoController/getreportWholeInfoVoTotal')
                .then(function (response){
                    _this.total = response.data;
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                })

        },


        /*
        * 点击事件
        * */
        /*点击详情*/
        gotohandlingWorks(val){

            var _this = this;
            var report_id = val;
            axios.post('http://localhost:8080/reportInfoController/addReport_idtoSession?report_id='+report_id)
                .then(function (response){
                    window.location.assign("../pages/handleWorksInterface.html");
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                })
        },
        /*
       * 获取输入当前的页数
       * */
        handleCurrentChange(val) {
            //设置当前页数
            this.adminSelectInfoVo.works_page = val;

            /**
             * 提交表单，获取举报信息
             * */
            this.getReportWholeInfoVoList();
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
        this.getReportWholeInfoVoList();
        this.getReportWholeInfoTotal();
    }
})