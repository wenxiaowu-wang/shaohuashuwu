new Vue({
    el: '#selectresult-id',
    data() {
        return {

            labelList: [
                "全部", "玄幻", "奇幻", "武侠", "仙侠", "都市", "历史", "军事", "悬疑", "科幻", "游戏", "体育", "现实", "轻小说"
            ],
            serialstateList: [
                "全部","连载","完结"
            ],


            //已选分类
            selectedtips:{
                selected_main_label:'',
                selected_serial_state:'',
            },

            //传入数据
            worksInfoneed:{
                work_main_label:'',
                work_serial_state:'',
            },

            //获取的数据
            worksInfoList:[],



        }
    },
    methods: {
        //主类型按钮
        mianwork(val){
            console.log("---"+val);

            if(val =="全部"){
                this.worksInfoneed.work_main_label = null;
                this.selectedtips.selected_main_label= "全部";
            }else {
                this.worksInfoneed.work_main_label = val;
                this.selectedtips.selected_main_label= val;
            }

            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/selectworkbyinfoResult2',_this.worksInfoneed)
                .then(function (response) {
                    _this.worksInfoList = response.data;
                    console.log("相应结果aa："+JSON.stringify(_this.worksInfoList));

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        //状态按钮
        serialstatework(val){
            console.log("---"+val);

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

            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/selectworkbyinfoResult2',_this.worksInfoneed)
                .then(function (response) {
                    _this.worksInfoList = response.data;
                    console.log("相应结果aa："+JSON.stringify(_this.worksInfoList));

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },

        //点击取消
        mainSelected(){

            this.mianwork("全部");
        },
        stateSelected(){
            this.serialstatework(null);
        },

        startthisHtml() {
            this.worksInfoneed.work_main_label = "全部";
            this.worksInfoneed.work_serial_state = null;
            this.selectedtips.selected_main_label = "全部";
            this.selectedtips.selected_serial_state = "全部";

            console.log("11211");
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/selectworkbyinfoResult')
                .then(function (response) {
                    _this.worksInfoList = response.data;
                     console.log("相应结果aa："+JSON.stringify(_this.worksInfoList));

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },

        gotoDetail(work_id){
            console.log("id--"+work_id);
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/selectworksByworkid?work_id='+work_id)
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
      this.startthisHtml();
    }
})