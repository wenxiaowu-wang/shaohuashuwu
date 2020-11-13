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

        }
    },
    methods: {

        handleSelect(key, keyPath) {
            console.log("选择"+key, keyPath);
        },

        satrtthishtml(){
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/selectworkByid')
                .then(function (response) {
                    _this.worksInfo = response.data;
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