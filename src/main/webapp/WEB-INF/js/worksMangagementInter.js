new Vue({
    // router,
    el: '#worksMangagement_id',
    data() {
        return {
            worksInfoList:[],
        }
    },
    methods:{

        /*
        * 初始化信息
        * */

        /*获取作品信息*/
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


        /*
        * 点击事件
        * */
        //点击新建作品按钮
        gotonewlyCreatedWorks(){
            window.location.assign("../pages/newlyCreatedWorksInterface.html");
        },

        //点击管理作品按钮
        gotomangagementWorks(work_id){
            var _this= this;
            console.log("作品id");
            console.log("作品id"+work_id);
            axios.get('/shaohuashuwu/worksInfoController/addWork_idSession?work_id='+work_id)
                .then(function (response){
                    window.location.assign("../pages/mangagementWorksInterface.html");
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                })
        },
        //点击添加章节按钮
        gotoaddChapterInter(work_id){
            var _this= this;
            console.log("作品id");
            console.log("作品id"+work_id);
            axios.get('/shaohuashuwu/worksInfoController/addWork_idSession?work_id='+work_id)
                .then(function (response){
                    window.location.assign("../pages/addChapterInterface.html");
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                })
        },

        //点击作品设置按钮
        gotoWorkSet(work_id){
            var _this= this;
            console.log("作品id");
            console.log("作品id"+work_id);
            axios.get('/shaohuashuwu/worksInfoController/addWork_idSession?work_id='+work_id)
                .then(function (response){
                    window.location.assign("../pages/workSetInterface.html");
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                })
        },
    },
    created:function (){ //页面加载时查询所有
        this.getWorksInfoListInfo();
    }
})