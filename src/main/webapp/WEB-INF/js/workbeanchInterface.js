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