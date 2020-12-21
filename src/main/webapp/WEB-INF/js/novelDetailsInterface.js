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

            user_id: '',//用户id
            user_name: '',//用户昵称
            work_id:'30',

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

            //用户登录后 获取用户的session信息
            axios.get("/shaohuashuwu_war_exploded/userSession/getPersonalData").then(response => {

                let info = response.data;
                let user_id = info["user_id"];
                let user_name = info["user_name"];
                // let head_portrait = info["head_portrait"];
                // let gender = info["gender"];
                // let area = info["area"];
                // let birthday = info["birthday"];

                this.user_id = user_id;
                this.user_name = user_name;
                // this.user_avatar = head_portrait;
                // this.gender = gender;
                // this.dateValue = birthday;
                // this.area = area;

                this.$message({
                    type: 'success',
                    message: '获取信息成功！'
                });
            }).catch(error => {
                console.log("获取信息失败！" + error);
                this.$message({
                    type: 'error',
                    message: '获取信息失败！'
                });
            });
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
        //存入阅读历史
        addToReadingHistory(){
            axios.post("/shaohuashuwu_war_exploded/readingHistoryInfoController/addToReadingHistory/" +
                this.user_id + "/" + this.work_id ).then(resp => {
                let Result = resp.data;
                console.log("数据同步存到数据库。"+Result);

            }).catch(error => {
                console.log("数据同步存到数据库失败。"+error);
            });
        },
        //加入书架
        addToBookshelf(){
            axios.post("/shaohuashuwu_war_exploded/bookshelfInfoController/addToBookshelf/" +
                this.user_id + "/" + this.work_id ).then(resp => {
                let Result = resp.data;
                console.log("数据同步存到数据库。"+Result);
                    if(Result==true){
                        this.$message({
                            type: 'success',
                            message: '加入书架成功！'
                        });
                    }else{
                        this.$message({
                            type: 'error',
                            message: '小说已加入书架，无需重复添加!'
                        });
                    }
            }).catch(error => {
                console.log("数据同步存到数据库失败。"+error);
            });
        },

    },
    created:function (){ //页面加载时查询所有
        this.satrtthishtml();
    }
})