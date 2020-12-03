new Vue({
    el: '#selectresult_id',
    data() {
        return {

            /**
             * 界面数据*/
            //主类型标签
            labelList: [
                "全部", "玄幻", "奇幻", "武侠", "仙侠", "都市", "历史", "军事", "悬疑", "科幻", "游戏", "体育", "现实", "轻小说"
            ],
            //状态类型
            serialstateList: [
                "全部","连载","完结"
            ],
            //已选分类
            selectedtips:{
                selected_main_label:' ',
                selected_serial_state:' ',
            },

            /**
             * 上传数据
             * */
            //上传类型和状态
            worksInfoneed:{
                work_main_label:' ',
                work_serial_state:' ',
            },

            /**
             * 相应数据*/
            //获取的作品数据
            worksWholeInfoVoList:[],
            workTotal:0,



        }
    },
    methods: {

        /**
         * 初始化界面
         */
        //初始化标签类型
        startHtmlContent(){
            this.worksInfoneed.work_main_label = null;
            this.worksInfoneed.work_serial_state = null;
            this.selectedtips.selected_main_label = "全部";
            this.selectedtips.selected_serial_state = "全部";
        },
        //初始化界面数据
        startSearchWorkInfo() {
            var _this = this;
            console.log("上传信息"+JSON.stringify(_this.worksInfoneed));
            axios.post('http://localhost:8080/workWholeInfoVoController/getWorkWholeInfoBySelectinput',_this.worksInfoneed)
                .then(function (response) {
                    _this.worksWholeInfoVoList = response.data;
                    _this.workTotal=_this.worksWholeInfoVoList.length;

                })
                .catch(function (error){
                    alert("相应失败");
                })
        },


        /**
         * 点击事件
         */
        //改变主类型信息
        changWorkmian_label(val){
            if(val =="全部"){
                this.worksInfoneed.work_main_label = null;
                this.selectedtips.selected_main_label= "全部";
            }else {
                this.worksInfoneed.work_main_label = val;
                this.selectedtips.selected_main_label= val;
            }
        },
        //点击主类型按钮
        clickWorkmain_label(val){
            //改变做类型，和上传数据类型
            this.changWorkmian_label(val);
            //获取作品信息
            this.startSearchWorkInfo();
        },
        //改变作品状态信息
        changWork_serial_state(val){
            if(val == "全部"){
                this.worksInfoneed.work_serial_state = null;
                this.selectedtips.selected_serial_state= "全部";
            }if(val == "连载") {
                this.worksInfoneed.work_serial_state = 1;
                this.selectedtips.selected_serial_state= "连载";
            }
            if(val == "完结" ){
                this.worksInfoneed.work_serial_state = 2;
                this.selectedtips.selected_serial_state= "完结";
            }
        },
        //点击作品状态按钮
        clickWork_sate(val){
            //改变作品状态，和上传数据类型
            this.changWork_serial_state(val);
            //获取作品信息
            this.startSearchWorkInfo();
        },
        //点击主类型取消按钮
        clickCancelWorkmain_label(){

            this.clickWorkmain_label("全部");
        },
        //点击状态取消按钮
        clickCancelWork_sate(){
            this.clickWork_sate("全部");
        },



        gotoDetail(work_id){
            console.log("id--");
            console.log("id--"+work_id);
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/addWork_idSession?work_id='+work_id)
                .then(function (response) {
                    window.location.assign("../pages/novelDetailsInterface.html");
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },

    },
    created:function (){ //页面加载时查询所有
        this.startHtmlContent();
      this.startSearchWorkInfo();
    }
})