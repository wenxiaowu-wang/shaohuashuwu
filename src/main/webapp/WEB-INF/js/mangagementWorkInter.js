new Vue({
    el: '#app',
    data() {
        return {
            // worksInfo:{
            //     work_name:'',
            //     work_serial_state:'',
            //     work_word_num:'',
            //     work_tip_num:'',
            //     work_subscribe_num:'',
            //     work_vote_num:'',
            //     work_create_time:'',
            // }
            worksInfo:[],
            workstate:'',
            work_serial_state_show:'',
        }
    },
    methods: {
        selectworkByid() {
            console.log("下面");
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/selectworkByid')
                .then(function (respone) {
                     _this.worksInfo = respone.data;
                     _this.showwork_serial_state();
                    console.log(respone.data);
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
            })

        },
        changeworkstate(){
            console.log("改变状态："+this.workstate);
            this.worksInfo.work_serial_state = this.workstate;
            console.log("改变状态后："+this.worksInfo.work_serial_state);
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/updateWorkSerialStateByid',_this.worksInfo)
                .then(function (respone) {
                    console.log("相应结果："+respone.data);
                    _this.showwork_serial_state();
                    //alert(respone.data);
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        onwork(){
            this.workstate = 1;
            this.changeworkstate();
        },
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

            // this.workstate = 3;
            // this.changeworkstate();
        },

        //改变上下架之后的样式
        showwork_serial_state(){
            this.workstate = this.worksInfo.work_serial_state;
            if(this.worksInfo.work_serial_state != 1 && this.worksInfo.work_serial_state != 2){
                this.work_serial_state_show = "下架";
            }
            else{
                this.work_serial_state_show = "上架";
            }
        }

    },
    created:function (){ //页面加载时查询所有
        this.selectworkByid();
    }
})