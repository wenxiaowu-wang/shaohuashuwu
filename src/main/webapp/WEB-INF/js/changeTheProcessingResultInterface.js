new Vue({
    el: '#administrators_mainInfoRight_div_id',
    data() {
        return {

            /*
           * 相应数据
           * */
            /*处理结果信息*/
            handleResultList:[],
            /*处理结果数量*/
            total:'',


            /*
           * 提交数据
           * */
            //提交的页数
            pageInfo:{
                works_page:1,
                works_page_num:8,
            },
            //搜索信息
            needdate:'',
            adminSelectInfoVo:{
                works_page:1,
                works_page_num:8,
                work_name:'',
                report_time:'',
            }

        }
    },
    methods: {
        /*
        * 初始化界面
        * */
        /*获取处理信息*/
        gethandleResultList(){
            console.log("信息："+JSON.stringify(this.adminSelectInfoVo));
            console.log("信息：("+JSON.stringify(this.adminSelectInfoVo.work_name)+")");

            var _this = this;
            axios.post('/shaohuashuwu/reportWholeInfoVoController/gethandleResultList',_this.adminSelectInfoVo)
                .then(function (response){
                    _this.handleResultList = response.data;
                    for(var i=0;i<_this.handleResultList.length;i++){
                        _this.handleResultList[i].report_time = _this.timestampToTime(_this.handleResultList[i].report_time);
                    }
                    console.log("-----"+JSON.stringify(_this.handleResultList));
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("错误");
                })

        },
        /*获取处理结果数量*/
        gethandleResultTotal(){
            var _this = this;
            axios.post('/shaohuashuwu/reportWholeInfoVoController/gethandleResultListTotal',_this.adminSelectInfoVo)
                .then(function (response){
                    _this.total = response.data;

                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("错误");
                })
        },



        /*
        * 点击事件
        * */
        /*点击去更改*/
        gotohandlingWorks(val){

            var _this = this;
            var report_id = val;
            axios.post('/shaohuashuwu/reportInfoController/addReport_idtoSession?report_id='+report_id)
                .then(function (response){
                    window.location.assign("../pages/changeTheProcessingInterface.html");
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

            console.log("当前页数"+JSON.stringify(this.adminSelectInfoVo.works_page))
            /**
             * 提交表单，获取举报信息
             * */
            this.gethandleResultList();
        },


        /*点击搜索按钮时*/
        getneedReportInfo(){

            /*转换日期类型*/
            if (this.needdate == null || this.needdate == ""){
                this.adminSelectInfoVo.report_time = null;

                this.gethandleResultList();
                this.gethandleResultTotal();
            }else {
                var m = (this.needdate.getMonth() + 1 < 10 ? '0' + (this.needdate.getMonth() + 1) : this.needdate.getMonth() + 1)
                var d = (this.needdate.getDate() < 10 ? '0' + (this.needdate.getDate()) : this.needdate.getDate())
                this.adminSelectInfoVo.report_time = this.needdate.getFullYear() + '-' + m + '-' + d;

                this.gethandleResultList();
                this.gethandleResultTotal();
            }


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

        this.gethandleResultList();
        this.gethandleResultTotal();
    }
})