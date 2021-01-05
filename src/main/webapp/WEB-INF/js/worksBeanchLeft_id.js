new Vue({
    el: '#worksBeanchLeft_id',
    data() {

    },
    methods:{




        //工作台
        gotoworBeanch(){
            window.location.assign("../pages/workbenchInterface.html");
        },
        //作品管理
        gotoworksMangagementInterface(){
            window.location.assign("../pages/worksMangagementInterface.html");
        },
        //数据中心
        gotoworkDataStatisticsInterface(){
            window.location.assign("../pages/workDataStatisticsInterface.html");
        },
        //跳转到稿酬收入界面
        gotoRemunerationInterface(){
            window.location.assign("../pages/remunerationInterface.html");
        },
        //跳转到稿酬收入界面
        gotoInteraction(){
            window.location.assign("../pages/interactionManagementInterface.html");
        },


    },
    created:function (){

    }
})