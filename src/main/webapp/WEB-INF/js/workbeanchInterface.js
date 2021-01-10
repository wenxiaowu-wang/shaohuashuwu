new Vue({
    el: '#worksBeanch_id',
    data() {
        return {
            authorInfo:[],
            worksInfoList:[],
        }
    },
    methods:{


        //点击新建作品按钮
        gotonewlyCreatedWorks(){
            window.location.assign("../pages/newlyCreatedWorksInterface.html");
        },
        /*
        * 获取数据
        * */
        //作者信息
        getAuthorInfo(){
            var _this = this;
            axios.post('/shaohuashuwu/authorInfoController/getAuthorInfoVo')
                .then(function (response){
                    _this.authorInfo = response.data;

                })
                .catch(function (error){
                        console.log(error);
                        alert("相应失败");
                    })
        },

        //作品信息
        getWorksInfoListInfo(){
            var _this = this;
            axios.post('/shaohuashuwu/worksInfoController/getWorksInfoByUser_id')
                .then(function (response){
                    _this.worksInfoList = response.data;
                    console.log("作品信息"+JSON.stringify(_this.worksInfoList));
                    console.log("开始循环--------------");
                    for( var i = 0 ;i<_this.worksInfoList.length;i++){
                        console.log("循环");
                        _this.worknull = 1;
                        /*将1或2转为连载或完结*/
                        if( _this.worksInfoList[i].work_serial_state == 1){
                            _this.worksInfoList[i].work_serial_state = "连载";
                        }
                        else if(_this.worksInfoList[i].work_serial_state == 2){
                            _this.worksInfoList[i].work_serial_state = "完结";
                        }
                        else if(_this.worksInfoList[i].work_serial_state == 3){
                            _this.worksInfoList[i].work_serial_state = "下架";
                        }
                    }
                })
                .catch(function (error){
                    console.log(error);

                })
        },





    },
    created:function (){
        this.getAuthorInfo();
        this.getWorksInfoListInfo();
    }
})