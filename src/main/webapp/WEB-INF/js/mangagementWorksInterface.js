new Vue({
    el: '#app',
    data() {
        return {

            /*
            *界面相应数据*/
            //作品信息
            worksInfo:[],

            /*
            * 界面变化数据
            * */
            //两个按钮控制
            workstate:'',
            //作品状态现实
            work_serial_state_show:'',
        }
    },
    methods: {



        /*
        * 初始化界面
        * */
        /*根据作品id，获取作品信息*/
        selectworkByid() {
            var _this = this;
            axios.post('/shaohuashuwu/worksInfoController/getworkInfoByWork_id')
                .then(function (respone) {
                     _this.worksInfo = respone.data;
                     _this.showwork_serial_state();
                })
                .catch(function (error){
                    alert("相应作品失败");
            })

        },


        /*
        * 界面变化
        * */
        //改变上下架之后的按钮样式和作品状态现实样式
        showwork_serial_state(){
            this.workstate = this.worksInfo.work_serial_state;
            if(this.worksInfo.work_serial_state == 1 ){
                this.work_serial_state_show = "连载";
            }
            else if(this.worksInfo.work_serial_state == 2){
                this.work_serial_state_show = "完结";
            }
            else{
                this.work_serial_state_show = "下架";
            }
        },



        /*
        * 点击事件
        * */
        //点击上架作品
        onwork(){
            this.workstate = 1;
            this.changeworkstate();
        },
        //点击下架作品
        offwork(){
            var _athis = this;
            this.$confirm('此操作将下架该作品,下架后其他用户将不能看到该作品, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                _athis.workstate = 3;
                _athis.changeworkstate();
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },


        /*
        * 点击事件处理
        * */
        changeworkstate(){
            //修改上传信息
            this.worksInfo.work_serial_state = this.workstate;
            var _this = this;
            axios.post('/shaohuashuwu/worksInfoController/updateWorkSerialStateByid',_this.worksInfo)
                .then(function (respone) {
                    //改变界面显示效果
                    _this.selectworkByid();
                })
                .catch(function (error){
                    console.log(error);
                })
        },





    },
    created:function (){ //页面加载时查询所有
        this.selectworkByid();
    }
})