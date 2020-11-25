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
            value_1:0,
            value_2:0,
            value_3:0
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
        selectWork(){
            //提现按钮点击后提示
            this.$message({
                type:'info',
                message:'您当前已经在提现界面，您可以选择或输入提现金豆数量'
            });
        },
        createSubscriptionFigure(xAxisData,data1,data2){
            xAxisData = [];
            data1 = [];
            data2 = [];
            for (let i = 0; i < 101; i++) {
                xAxisData.push('类目' + i);
                // data1.push((Math.sin(i / 5) * (i / 5 -10) + i / 6) * 5);
                // data2.push((Math.cos(i / 5) * (i / 5 -10) + i / 6) * 5);
                if (i%3 === 0) {
                    data1.push(10+i);
                    data2.push(8+i);
                }else{
                    data1.push(0);
                    data2.push(0);
                }
            }
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
        _createSubscriptionFigure(xAxis_data,series_data){
            let myChart = echarts.init(document.getElementById('figure_subscription'));
            // let xAxis_data = [];
            // let series_data = [];
            // 指定图表的配置项和数据
            let option = {
                title: {
                    text: '订阅量统计折线图',
                    subtext: "（金豆个数/日期）",
                    left:'center',
                    textStyle: {
                        fontWeight:'normal',
                        fontSize: 15
                    },
                    subtextStyle: {
                        fontWeight:'normal',
                        fontSize: 13
                    }
                },
                xAxis: {
                    type: 'category',
                    data: xAxis_data
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: series_data,
                    type: 'line'
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
        createMaleReadersGroupFigure(gender,data_value){
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
        createBehaviorTimeFigure(series_data_value_male,series_data_value_female,indicator_max){
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
                        { name: '12点', max: indicator_max}
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
        axios.get("/shaohuashuwu_war_exploded/userSession/getUser").then(resp =>{
            let info = resp.data;
            let userId = JSON.stringify(info["user_id"]);
            let userName = info["user_name"];
            userId = parseInt(userId);
            this.user_id = userId;
            this.user_name = userName;
            console.log("用户数据装配成功");
        }).catch(error =>{
            console.log("获取本用户ID和name错误。");
        });
        //xAxis_data数据为所有订阅时间 series_data为某个时间（天）进行的订阅量
        let xAxis_data = ['周一', '周二', '周三', 'Thu', 'Fri', 'Sat', 'Sun'];
        let series_data = [820, 932, 901, 934, 1290, 1330, 1320];
        //this.createSubscriptionFigure(xAxis_data,series_data);
        this.createSubscriptionFigure(xAxis_data,series_data,series_data);

        //data_value数据为不同岁数段的人数统计
        let data_value = [335,310,234,235,1548];
        let data_value_2 = [300,1000,333,235,1548];
        this.createMaleReadersGroupFigure("male",data_value);
        this.createMaleReadersGroupFigure("female",data_value_2);

        //数据为每个时间段所获取的阅读人数（随机抽取100以内个数据）max为雷达图的每个属性的最大值
        let indicator_max = 52000;
        let series_data_value_male = [4300, 10000, 28000, 35000, 50000, 19000,4300, 10000, 28000, 35000, 50000, 19000];
        let series_data_value_female = [5000, 14000, 28000, 31000, 42000, 21000,5000, 14000, 28000, 31000, 42000, 21000];
        this.createBehaviorTimeFigure(series_data_value_male,series_data_value_female,indicator_max);

        //数据为所有大标签，以及对应男生女生读者喜欢某类标签的人数（200名读者以内）
        let series_like_data_male = [18203, 23489, 29034, 104970, 131744,18203, 23489, 29034, 104970, 131744, 29034, 104970, 131744, 630230];
        let series_like_data_female = [19325, 23438, 31000, 121594, 134141,19325, 23438, 31000, 121594, 134141, 31000, 121594, 134141, 681807];
        let yAxis_data = ['玄幻', '印尼', '美国', '印度', '中国','巴西', '印尼', '美国', '印度', '中国','美国', '印度', '中国', '全部'];
        this.createBehaviorLikeFigure(yAxis_data,series_like_data_male,series_like_data_female);
    },
})