new Vue( {
    el: '#allWorksInterface-id',
    data() {
        return {
            labelList: [
                "全部", "玄幻", "奇幻", "武侠", "仙侠", "都市", "历史", "军事", "悬疑", "科幻", "游戏", "体育", "现实", "轻小说"
            ],

            vicelabelList: [],
            xuanhuanlist: ["东方玄幻", "异世大陆", "高武世界", "王朝争霸"],
            qihuanlist: ["剑与魔法", "史诗奇幻", "黑暗幻想", "现代魔法", "历史神话", "另类幻想"],
            wuxialist: ["传统武侠", "武侠幻想", "国术无双", "古武未来", "武侠同人"],
            xianxialist: ["修真文明", "幻想修仙", "现代修真", "神话修真", "古典仙侠"],
            dushilist: ["都市生活", "娱乐明星", "商战职场", "异术超能", "都市异能", "青春校园"],
            lishilist: ["架空历史", "两宋元明", "外国历史", "上古先秦", "秦汉三国", "两晋隋唐", "五代十国", "清史民国", "历史传记", "民间传说"],
            jushilist: ["战争幻想", "谍战特工", "军旅生涯", "抗战烽火", "军事战争"],
            xuanyilist: ["悬疑侦探", "诡秘悬疑", "探险生存", "奇妙世界", "古今传奇"],
            kehuanlist: ["星际文明", "时空穿梭", "未来世界", "古武机甲", "超级科技", "进化变异", "末世危机"],
            youxilist: ["电子竞技", "虚拟网游", "游戏世界", "游戏系统", "游戏主播"],
            tiyulist: ["体育赛事", "篮球运动", "足球运动"],
            qingxiaoshuolist: ["原生幻想", "衍生同人", "搞笑吐槽", "青春日常"],
            xianshilist: ["爱情婚姻", "现实百态", "社会乡土", "生活时尚", "文学艺术", "成功励志", "青春文学"],
            serialstateList: [
                "全部","连载","完结"
            ],

            //已选分类
            selectedtips:{
                selected_main_label:'',
                selected_vice_label:'',
                selected_serial_state:''
            },

            //输入数据
            worksInfoneed:{
                work_main_label:'',
                work_vice_label:'',
                work_serial_state:'',
                works_page: '',
                works_page_num:4,
            },

            currentPage:'',


        //    接收数据
            total:0,
            worksInfoList:[],

        }

    },
    methods: {
        mianwork(val){
            console.log(val);
            this.worksInfoneed.work_main_label = val;
            this.worksInfoneed.work_vice_label = null;
            this.selectedtips.selected_main_label = val;

            //设置当前处于第一页
            this.currentPage = 1;
            this.worksInfoneed.works_page = 1;

            console.log(this.worksInfoneed.work_main_label);
            if (val=="全部"){
                this.worksInfoneed.work_main_label = null;
                this.selectedtips.selected_vice_label = null;

                console.log("输出--"+this.worksInfoneed.work_main_label+ this.worksInfoneed.work_vice_label);
                this.vicelabelList = null;
                console.log(this.vicelabelList);
            }
            if (val=="玄幻"){
                this.worksInfoneed.work_vice_label = null;


                this.vicelabelList = this.xuanhuanlist;
                console.log(this.vicelabelList);
            }
            if (val=="奇幻"){


                this.vicelabelList = this.qihuanlist;
                console.log(this.vicelabelList);
            }
            if (val=="武侠"){


                this.vicelabelList = this.wuxialist;
                console.log(this.vicelabelList);
            }
            if (val=="仙侠"){


                this.vicelabelList = this.xianxialist;
                console.log(this.vicelabelList);
            }
            if (val=="都市"){


                this.vicelabelList = this.dushilist;
                console.log(this.vicelabelList);
            }
            if (val=="历史"){


                this.vicelabelList = this.lishilist;
                console.log(this.vicelabelList);
            }
            if (val=="军事"){


                this.vicelabelList = this.jushilist;
                console.log(this.vicelabelList);
            }
            if (val=="悬疑"){


                this.vicelabelList = this.xuanyilist;
                console.log(this.vicelabelList);
            }
            if (val=="科幻"){


                this.vicelabelList = this.kehuanlist;
                console.log(this.vicelabelList);
            }
            if (val=="游戏"){


                this.vicelabelList = this.youxilist;
                console.log(this.vicelabelList);
            }
            if (val=="体育"){


                this.vicelabelList = this.tiyulist;
                console.log(this.vicelabelList);
            }
            if (val=="轻小说"){


                this.vicelabelList = this.qingxiaoshuolist;
                console.log(this.vicelabelList);
            }
            if (val=="现实"){


                this.vicelabelList = this.xianshilist;
                console.log(this.vicelabelList);
            }
            var _this=this;
            axios.post('http://localhost:8080/worksInfoController/selectworksneed',_this.worksInfoneed)
                .then(function (respone){
                    console.log(respone.data);
                    _this.worksInfoList = respone.data;//相应数据给userlist


                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
            axios.post('http://localhost:8080/worksInfoController/selectworkstotal',_this.worksInfoneed)
                .then(function (respone){


                    _this.total = respone.data;

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })


        },
        vicework(val){
            console.log(val);
            this.worksInfoneed.work_vice_label = val;
            this.selectedtips.selected_vice_label = val;

            //设置当前处于第一页
            this.currentPage = 1;
            this.worksInfoneed.works_page = 1;

            //控制组件显示
            var ui =document.getElementById("viceSelected-button-id");
            ui.style.display="";



            var _this=this;
            axios.post('http://localhost:8080/worksInfoController/selectworksneed',_this.worksInfoneed)
                .then(function (respone){
                    console.log(respone.data);
                    _this.worksInfoList = respone.data;//相应数据给userlist

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })


            axios.post('http://localhost:8080/worksInfoController/selectworkstotal',_this.worksInfoneed)
                .then(function (respone){


                    _this.total = respone.data;

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })



        },
        serialstatework(val){
            console.log(val)
            this.worksInfoneed.work_serial_state = val;
            this.selectedtips.selected_serial_state = val;

            //设置当前处于第一页
            this.currentPage = 1;
            this.worksInfoneed.works_page = 1;



            if(val == "全部"){
                this.worksInfoneed.work_serial_state = null;
                console.log("输出--"+this.worksInfoneed.work_serial_state);
            }
            if(val == "连载"){
                this.worksInfoneed.work_serial_state = 1;
                console.log("输出--"+this.worksInfoneed.work_serial_state);
            }
            if(val == "完结"){
                this.worksInfoneed.work_serial_state = 2;
                console.log("输出--"+this.worksInfoneed.work_serial_state);
            }



            var _this=this;
            axios.post('http://localhost:8080/worksInfoController/selectworksneed',_this.worksInfoneed)
                .then(function (respone){
                    console.log(respone.data);
                    _this.worksInfoList = respone.data;//相应数据给userlist

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
            axios.post('http://localhost:8080/worksInfoController/selectworkstotal',_this.worksInfoneed)
                .then(function (respone){
                    _this.total = respone.data;

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },

        mainSelected(){
            var ui;
            this.worksInfoneed.work_mian_label = null;
            this.worksInfoneed.work_vice_label = null;

            this.mianwork("全部");


            if(this.worksInfoneed.work_vice_label == null){
                 ui =document.getElementById("viceSelected-button-id");
                ui.style.display="none";
            }

        },
        viceSelected(){
            var ui;

            this.worksInfoneed.work_vice_label = null;
            this.mianwork(this.worksInfoneed.work_main_label);
            if(this.worksInfoneed.work_vice_label == null){
                 ui =document.getElementById("viceSelected-button-id");
                ui.style.display="none";
            }
        },
        stateSelected(){
            this.serialstatework("全部");
        },

        startthisHtml(){
            this.mainSelected();
            this.stateSelected();
        },

        oktiaozhuan(){
            alert("可以000")
        },

        handleCurrentChange(val) {
            console.log(val);
            this.worksInfoneed.works_page = val;
            console.log("共--页"+this.worksInfoneed.works_page);


            var _this = this;


            axios.post('http://localhost:8080/worksInfoController/selectworksneed',_this.worksInfoneed)
                .then(function (respone){
                    console.log(respone.data);
                    _this.worksInfoList = respone.data;//相应数据给userlist

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })


            // axios.post('http://localhost:8080/worksInfoController/selectworkstotal',_this.worksInfoneed)
            //     .then(function (respone){
            //
            //
            //         _this.total = respone.data;
            //
            //     })
            //     .catch(function (error){
            //         console.log(error);
            //         alert("相应失败");
            //     })

        },


    },
    created:function (){ //页面加载时查询所有
        this.startthisHtml();
    }
})