new Vue({

    el: '#authorMainTop_div_id',
    data() {
        return {
            authorInfo:[],
            worksNum:'',
        }
    },
    methods:{
        /*
                * 获取数据
                * */
        //获取作者信息
        getAuthorInfo(){
            var _this = this;
            axios.post('/shaohuashuwu/authorInfoController/getAuthorInfoVo')
                .then(function (response){
                    _this.authorInfo = response.data;
                })
                .catch(function (error){
                    console.log(error);
                })
        },
        //获取作品数量
        getWorksNum(){
            var _this = this;
            axios.post('/shaohuashuwu/worksInfoController/getWorksNumByUser_id')
                .then(function (response){
                    _this.worksNum = response.data;
                })
                .catch(function (error){
                    console.log(error);
                })
        },

        //返回首页
        gotoUserMainIterface(){
            window.location.assign("../pages/userMainInterface.html");
        },
        //工作台
        gotoworBeanch(){
            window.location.assign("../pages/workbenchInterface.html");
        },
        //帮助
        gotohelp(){

        },

    },
    created:function (){
        this.getAuthorInfo();
        this.getWorksNum();
    }
})