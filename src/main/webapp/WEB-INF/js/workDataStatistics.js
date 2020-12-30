let workDataStatisticsInterface_vm = new Vue({
    el:"#workDataStatistics_div",
    data:{
        user_id:0,
        user_name:"我系成龙",
        //单选选择器有关的数据
        work_options: [{
            value: 0,
            label: '作品1'
        },{
            value: 100,
            label: '作品2'
        }, {
            value: 300,
            label: '作品3'
        }, {
            value: 500,
            label: '作品4'
        }, {
            value: 1000,
            label: '作品5'
        }],
        select:{
            value_1:'',
            value_2:'',
            value_3:''
        },
        //----end----
        //订阅数据统计
        subscription_data:{
            all:0,
            yesterday:0,
            average:0,
            most:0
        }

    },
    methods:{
        selectWork_1(){
            if (this.select.value_1 === ''){
                this.$message({
                    type:'error',
                    message:'请选择作品'
                });
            }else{
                //根据作品ID获取该作品订阅统计分布
                axios.get("/shaohuashuwu/transactionInfoController/getSubscriptionStatisticsData/" +
                    this.select.value_1).then(resp =>{

                    let objectData = resp.data;
                    // console.log("data eval后数据 objectData类型为："+typeof (objectData));
                    // console.log("data eval后 objectData为:"+objectData);
                    let list_nan = eval(objectData["男"]);
                    let list_nv = eval(objectData["女"]);
                    // console.log("list_nan的数据类型为："+typeof (list_nan));
                    // console.log("list_nan的数据为："+list_nan);

                    let data_nan = [];
                    let data_nv = [];
                    let xAxisData = [];
                    list_nan.forEach(function (value,index,array) {
                        xAxisData.push(value["date_day"]);
                        data_nan.push(value["subscription_quantity"]);
                    });
                    list_nv.forEach(function (value) {
                        data_nv.push(value["subscription_quantity"]);
                    });
                    this.createSubscriptionFigure(xAxisData,data_nan,data_nv);
                    console.log("订阅数据获取并展示成功。");
                }).catch(error =>{
                    console.log("获取订阅分布数据错误。"+error);
                });
                //获取该作品的其它订阅情况
                axios.get("/shaohuashuwu/transactionInfoController/getOtherSubscriptionStatisticsData/" +
                    this.select.value_1).then(response =>{
                    let objectData = response.data;
                    this.subscription_data.all = objectData["all_subscription_num"];
                    this.subscription_data.yesterday = objectData["yesterday_subscription_num"];
                    this.subscription_data.average = objectData["chapter_avg_subscription_num"];
                    this.subscription_data.most = objectData["chapter_max_subscription_num"];
                    console.log("获取其它订阅数据成功。");
                }).catch(error =>{
                    console.log("获取其它订阅统计失败。");
                })
            }


        },
        selectWork_2(){
            //提现按钮点击后提示
            if (this.select.value_2 === ''){
                this.$message({
                    type:'error',
                    message:'请选择作品'
                });
            }else{
                axios.get("/shaohuashuwu/readingHistoryInfoController/getReaderAgeDistributionByWorkId/" +
                    this.select.value_2).then(resp =>{
                    let object = resp.data;
                    let list_nan = eval(object["男"]);
                    let list_nv = eval(object["女"]);

                    let data_nan = this.analysisReadingData(list_nan)["data"];
                    let data_nv = this.analysisReadingData(list_nv)["data"];
                    //装配数据
                    this.createReadersGroupFigure("male",data_nan);
                    this.createReadersGroupFigure("female",data_nv);
                    console.log("读者年龄段分布统计并展示成功。");
                }).catch(error =>{
                    console.log("读者年龄段分布统计或展示失败！"+error);
                });
            }
        },
        selectWork_3(){
            //提现按钮点击后提示
            if (this.select.value_3 === ''){
                this.$message({
                    type:'error',
                    message:'请选择作品'
                });
            }else{
                //获取该作品对应的读者于都时间段分布数据
                axios.get("/shaohuashuwu/readingHistoryInfoController/getReadingTimeDistributionByWorkId/" +
                    this.select.value_3).then(resp =>{
                    let object = resp.data;
                    let list_nan = eval(object["男"]);
                    let list_nv = eval(object["女"]);
                    let data_nan = this.analysisReadingData(list_nan)["data"];
                    let data_nv = this.analysisReadingData(list_nv)["data"];
                    let max_value_nan = this.analysisReadingData(list_nan)["max_value"];
                    let max_value_nv = this.analysisReadingData(list_nv)["max_value"];
                    let max_value = max_value_nan > max_value_nv ? max_value_nan:max_value_nv;
                    this.createReadingTimeFigure(data_nan,data_nv,(max_value+1));
                    console.log("读者阅读时间段分布数据获取并展示成功。"+data_nan);
                    console.log(data_nv);
                }).catch(error =>{
                    console.log("读者阅读时间段分布诗句获取或展示失败！"+error);
                });
                //获取该作品对应的读者于都时间段分布数据
                axios.get("/shaohuashuwu/bookshelfInfoController/getReaderLikeDistributionByWorkId/" +
                    this.select.value_3).then(resp =>{
                    let object = resp.data;
                    let list_nan = eval(object["男"]);
                    let list_nv = eval(object["女"]);
                    //js push 列表到首位
                    let nan_like_data = this.analysisReaderLike(list_nan)["data"];
                    let nv_like_data = this.analysisReaderLike(list_nv)["data"];
                    let yAxis_data = this.analysisReaderLike(list_nv)["label"];

                    this.createBehaviorLikeFigure(yAxis_data,nan_like_data,nv_like_data);
                    console.log("读者喜欢作品类型分布数据获取并展示成功。"+nan_like_data);
                    console.log(data_nv);
                }).catch(error =>{
                    console.log("读者喜欢作品类型分布数据获取并展示失败！"+error);
                });
            }
        },
        //解析读者年龄段信息或者读者阅读时间段信息，返回可直接传入构造图形的值以及最大值
        analysisReadingData(list_data){
            let data = [];
            let max_value = 0;
            list_data.forEach(function (value,index,array) {
                data.push(value["reader_num"]);
                max_value = max_value > value["reader_num"] ? max_value:value["reader_num"];
            });
            return {
                "data":data,
                "max_value":max_value
            };
        },
        //解析读者喜欢的作品标签分类信息，返回可直接传入构造图形已排好序的值
        analysisReaderLike(list_data){
            let data = [];
            let label = [];
            list_data.forEach(function (value,index,array) {
                data.unshift(value["reader_num"]);
                label.unshift(value["work_main_label"]);
            });
            return {
                "data":data,
                "label":label
            };
        },

        //构造订阅统计分布柱状图
        createSubscriptionFigure(xAxisData,data1,data2){
            let myChart = echarts.init(document.getElementById('figure_subscription'));
            let option = {
                title: {
                    text: '订阅量统计柱状图(金币数/日期)',
                    subtext: '最近不超过30天',
                    top:"top",
                    left:"left",
                    textStyle:{
                        fontWeight:"normal",
                        fontSize1:7
                    }
                },
                legend: {
                    left:"right",
                    data: ['男', '女']
                },
                toolbox: {
                    // y: 'bottom',
                    feature: {
                        magicType: {
                            type: ['stack', 'tiled']
                        },
                        dataView: {},
                        saveAsImage: {
                            pixelRatio: 2
                        }
                    }
                },
                tooltip: {},
                xAxis: {
                    data: xAxisData,
                    splitLine: {
                        show: false
                    }
                },
                yAxis: {
                },
                series: [{
                    name: '男',
                    type: 'bar',
                    data: data1,
                    animationDelay: function (idx) {
                        return idx * 20;
                    }
                }, {
                    name: '女',
                    type: 'bar',
                    data: data2,
                    animationDelay: function (idx) {
                        return idx * 20 + 100;
                    }
                }],
                animationEasing: 'elasticOut',
                animationDelayUpdate: function (idx) {
                    return idx * 5;
                }
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
        //构造读者年龄段分布统计饼状图
        createReadersGroupFigure(gender,data_value){
            let title_text = "";
            let id = ""
            if (gender === "male"){
                title_text = "男性读者年龄段分布";
                id = "figure_reader_male";
            }else{
                title_text = "女性读者年龄段分布";
                id = "figure_reader_female"
            }
            //创建读者群体画像读者群体
            let myChart = echarts.init(document.getElementById(id));
            let option = {
                title: {
                    text: title_text,
                    left:'center',
                    bottom:"8%",
                    textStyle: {
                        fontWeight:'normal',
                        fontSize: 15
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 10,
                    data: ['10岁以下', '10~18岁', '18~25岁', '25~35岁', '35岁及以上']
                },
                series: [
                    {
                        name: '年龄段',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: [
                            {value: data_value[0], name: '10岁以下'},//(0,10)
                            {value: data_value[1], name: '10~18岁'},//[10,18)
                            {value: data_value[2], name: '18~25岁'},//[18,25)
                            {value: data_value[3], name: '25~35岁'},//[25,35)
                            {value: data_value[4], name: '35岁及以上'}//[35,∞)
                        ]
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
        //构造读者阅读时间段分布统计雷达图
        createReadingTimeFigure(series_data_value_male,series_data_value_female,indicator_max){
            let myChart = echarts.init(document.getElementById('figure_behavior_time'));
            let option = {
                title: {
                    text: '读者阅读时间段雷达图',
                    top:"top",
                    left:"left",
                    textStyle:{
                        fontWeight:"normal",
                        fontSize1:7
                    }
                },
                tooltip: {},
                legend: {
                    left:"right",
                    data: ['男生', '女生']
                },
                radar: {
                    // shape: 'circle',
                    name: {
                        textStyle: {
                            color: '#fff',
                            backgroundColor: '#999',
                            borderRadius: 3,
                            padding: [3, 5]
                        }
                    },
                    indicator: [
                        { name: '0点', max: indicator_max},
                        { name: '1点', max: indicator_max},
                        { name: '2点', max: indicator_max},
                        { name: '3点', max: indicator_max},
                        { name: '4点', max: indicator_max},
                        { name: '5点', max: indicator_max},
                        { name: '6点', max: indicator_max},
                        { name: '7点', max: indicator_max},
                        { name: '8点', max: indicator_max},
                        { name: '9点', max: indicator_max},
                        { name: '10点', max: indicator_max},
                        { name: '11点', max: indicator_max},
                        { name: '12点', max: indicator_max},
                        { name: '13点', max: indicator_max},
                        { name: '14点', max: indicator_max},
                        { name: '15点', max: indicator_max},
                        { name: '16点', max: indicator_max},
                        { name: '17点', max: indicator_max},
                        { name: '18点', max: indicator_max},
                        { name: '19点', max: indicator_max},
                        { name: '20点', max: indicator_max},
                        { name: '21点', max: indicator_max},
                        { name: '22点', max: indicator_max},
                        { name: '23点', max: indicator_max}
                    ]
                },
                series: [{
                    name: '男生 vs 女生',
                    type: 'radar',
                    // areaStyle: {normal: {}},
                    data: [
                        {
                            value: series_data_value_male,
                            name: '男生'
                        },
                        {
                            value: series_data_value_female,
                            name: '女生'
                        }
                    ]
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
        createBehaviorLikeFigure(yAxis_data,series_like_data_male,series_like_data_female){
            let myChart = echarts.init(document.getElementById('figure_behavior_like'));
            let option = {
                title: {
                    text: '读者喜欢作品类型',
                    subtext: '数据来自平台统计',
                    top:"top",
                    left:"left",
                    textStyle:{
                        fontWeight:"normal",
                        fontSize1:7
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    left:"right",
                    data: ['男生', '女生']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    data: yAxis_data
                },
                series: [
                    {
                        name: '男生',
                        type: 'bar',
                        data: series_like_data_male
                    },
                    {
                        name: '女生',
                        type: 'bar',
                        data: series_like_data_female
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
    },
    mounted(){
        //钩子函数，在加载页面后，渲染数据前执行
        //获取本用户的ID和name以及关注用户的信息
        axios.get("/shaohuashuwu/userSession/getUser").then(resp =>{
            let info = resp.data;
            let userId = JSON.stringify(info["user_id"]);
            let userName = info["user_name"];
            userId = parseInt(userId);
            this.user_id = userId;
            this.user_name = userName;
            console.log("用户数据装配成功");
            //获取该作者所有作品ID以及名字
            axios.get("/shaohuashuwu/worksInfoController/getAllWorksNameAndIdByAuthorId").then(response =>{
                //根据用户的ID获取该作者的作品数据
                let objectData = eval(JSON.stringify(response.data));//将字符串转化为数组对象
                console.log("作品数据对象的数据类型为："+typeof (objectData));
                console.log("作品数据为："+objectData);
                // value是当前元素，index当前元素索引，array为当前数组
                this.work_options = [];
                let this_ = this;       //注意一进入function内嵌函数中，this代表的是其函数，不是vue本身了
                objectData.forEach(function (value,index,array) {
                    let one_value = {
                        value:value["work_id"],
                        label:value["work_name"]
                    };
                    this_.work_options.push(one_value);
                });
                console.log("装配作品数据成功。");
            }).catch(error =>{
                console.log("装配作品数据失败。");
            });
        }).catch(error =>{
            console.log("获取本用户ID和name错误。");
        });

        //xAxis_data数据为所有订阅时间 series_data为某个时间（天）进行的订阅量
        let xAxis_data = ['某天', '某天', '某天', '某天', '某天', '某天', '某天'];
        let series_data_nan = [820, 932, 901, 934, 1290, 1330, 1320];
        let series_data_nv = [1320, 1330, 1290, 934, 901, 932, 820];
        //this.createSubscriptionFigure(xAxis_data,series_data);
        this.createSubscriptionFigure(xAxis_data,series_data_nan,series_data_nv);

        //data_value数据为不同岁数段的人数统计300,1000,333,235,1548
        let data_value = [0,0,0,0,0];
        let data_value_2 = [0,0,0,0,0];

        //初始化一些假数据，等待选择作品后统计
        /*for (let i=0;i<5;i++){
            let map = new Map();
            map.set("age_type","年龄段"+(i+1));
            map.set("reader_num",0);
            data_value.push(map);
            data_value_2.push(map);
        }*/

        this.createReadersGroupFigure("male",data_value);
        this.createReadersGroupFigure("female",data_value_2);

        //数据为每个时间段所获取的阅读人数（随机抽取100以内个数据）max为雷达图的每个属性的最大值
        let indicator_max = 52000;
        let series_data_value_male = [4300, 10000, 28000, 35000, 50000, 19000,4300, 10000, 28000, 35000, 50000, 19000];
        let series_data_value_female = [5000, 14000, 28000, 31000, 42000, 21000,5000, 14000, 28000, 31000, 42000, 21000];
        this.createReadingTimeFigure(series_data_value_male,series_data_value_female,indicator_max);

        //数据为所有大标签，以及对应男生女生读者喜欢某类标签的人数（200名读者以内）
        let series_like_data_male = [18203, 23489, 29034, 104970, 131744,18203, 23489, 29034, 104970, 131744, 29034, 104970, 131744, 630230];
        let series_like_data_female = [19325, 23438, 31000, 121594, 134141,19325, 23438, 31000, 121594, 134141, 31000, 121594, 134141, 681807];
        let yAxis_data = ['玄幻', '印尼', '美国', '印度', '中国','巴西', '印尼', '美国', '印度', '中国','美国', '印度', '中国', '全部'];
        this.createBehaviorLikeFigure(yAxis_data,series_like_data_male,series_like_data_female);
    },
})