new Vue({
    el: '#app',
    data() {
        return {
            labelList:[
                // "免费章节","付费章节",
                1,2
            ],

            input:'',

        //    界面数据变化


        //    接收相应数据
            worksInfo:[],

        //    上传数据
            chapterInfo:{
                chapter_title:'',
                chapter_word_num:'',
                chapter_content:'',
                chapter_other_word:'',
                chapter_charge:'',
            },
        }
    },
    methods: {
        startthisHtml(){
            console.log("下面");
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/selectworkByid')
                .then(function (respone) {
                    _this.worksInfo = respone.data;
                    // _this.showwork_serial_state();
                    console.log(respone.data);
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },

        savechapter(){
            var _this = this;
            console.log("111");
            console.log(_this.chapterInfo);
            axios.post('http://localhost:8080/chapterInfoController/insertchapter_info',_this.chapterInfo)
                .then(function (respone) {
                    // _this.worksInfo = respone.data;
                    // _this.showwork_serial_state();
                    console.log(respone.data);
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