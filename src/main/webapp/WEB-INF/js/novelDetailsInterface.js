new Vue({
    el: '#app',
    data() {
        return {

            activeIndex:'1',
            labelList:[
                "hahah","heheheh"
            ],
            jieshao:'　　在破败中崛起，在寂灭中复苏啊。 \n' +
                '　　沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角,沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角,沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角,沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角',

            chapter_name_test:[
                "第一章","第二章","第三章","第一章","第二章","第三章",
                "第一章","第二章","第三章","第一章","第二章","第三章","第一章","第二章","第三章",
                "第一章","第二章","第三章","第一章","第二章","第三章","第一章","第二章","第三章",
                "第一章","第二章","第三章","第一章","第二章","第三章","第一章","第二章","第三章","第一章","第二章","第三章",
                "第一章","第二章","第三章","第一章","第二章","第三章","第一章","第二章","第三章",
            ],


            drawer: false,


        //    相应数据
            worksInfo:[],
            catalogInfoVoList:[],

        }
    },
    methods: {

        handleSelect(key, keyPath) {
            console.log("选择"+key, keyPath);
        },

        //初始化界面
        satrtthishtml(){
            var _this = this;
            //获取作品信息
            axios.post('http://localhost:8080/worksInfoController/selectworkByid')
                .then(function (response) {
                    _this.worksInfo = response.data;
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
            //传入作品id，获取目录
            axios.post('http://localhost:8080/chapterInfoController/selectchaptercatalogBywork_id')
                .then(function (response) {
                    _this.catalogInfoVoList = response.data;
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },

        gotoreadNovelInterface(){
              var fristchapter = this.catalogInfoVoList[0].chapter_id;
              console.log("打印第一章节："+fristchapter);
            //传入作品id，获取目录
            axios.post('http://localhost:8080/chapterInfoController/saveChapter_idSession?chapter_id='+ fristchapter)
                .then(function (response) {
                    window.location.assign("../pages/readNovelInterface.html");
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },


    },
    created:function (){ //页面加载时查询所有
        this.satrtthishtml();
    }
})