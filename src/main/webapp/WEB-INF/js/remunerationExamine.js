let remunerationInterface_vm = new Vue({
    el:"#remuneration_div",
    data:{
        user_id:0,
        user_name:"我系成龙",

        tip1:"总收入",
        tip2:"金币",
        tip3:"已提现金币数",
        tip4:"可提现金币数",

        header:{
            goldCoin_total_income:0,
            goldCoin_already_withdraw:0,
            goldCoin_able_withdraw:0
        },

        work_options: [{
            value: 0,
            label: '全部'
        }, {
            value: 1,
            label: '作品1ID'
        }, {
            value: 2,
            label: '作品2value为ID'
        }, {
            value: 3,
            label: '作品3'
        }, {
            value: 4,
            label: '作品4'
        }],
        operate_options: [{
            value: '打赏',
            label: '打赏'
        }, {
            value: '订阅',
            label: '订阅'
        }, {
            value: '投票',
            label: '投票'
        }],
        select_value1:[],
        select_value2:'打赏',

        statistics:0,           //统计的表格金币（或推荐票数）
        unit_display:"金币(个)",      //交易单位
        consumer_tip:"打赏用户", //消费者提示

        tableData_display:[{
            work_name: '斗破苍穹后传之大主宰',
            chapter_title: '第五百二十一章：陨落的天才',
            consumer_name: '用户名不宜太长',
            transaction_quantity:50000,
            transaction_time:'2020-11-11-07:51:36',
            transaction_type:"打赏"
        },{
            work_name: '斗破苍穹后传之大主宰',
            chapter_title: '第五百二十一章：陨落的天才',
            consumer_name: '用户名不宜太长',
            transaction_quantity:50000,
            transaction_time:'2020-11-11-07:51:36',
            transaction_type:"打赏"
        }],
        tableData_all:[],   //总收入数据（包含提现）
        withdrawData:[]     //提现数据
    },
    methods:{
        toWithdraw(){

            //将所有金币收入以及所有已经提现的金币数发送到transactionSession中
            axios.post("/shaohuashuwu/transactionSession/saveTransactionGoldCoin/" +
                this.header.goldCoin_total_income+"/"+this.header.goldCoin_already_withdraw).then(resp =>{
                console.log("数据存放成功。");
                console.log("将进入提现稿酬页面。");
                //进入提现稿酬界面
                window.location.assign("../pages/withdrawGoldCoinInterface.html");
            }).catch(error =>{
               console.log("数据存放失败！");
            });
        },
        handleChange(value) {
            console.log(value);
        },
        formatter(row, column) {
            return row.address;
        },
        examine(){
            //选择查看选项后进行的查看操作
            //进行选项的分析筛选
            //根据选择选项的情况进行操作表单
            console.log("选择查看选项后进行的查看操作");
            this.updateTableDisplay();
        },
        updateTableDisplay(){
            //处理选择后的表头展示
            this.changeTableHeaderDisplay(this.select_value2);
            //处理选择后的数据展示
            this.tableData_display = this.changeTableDataDisplay(this.select_value1,this.select_value2);
            //统计交易总额
            this.statistics = this.toStatistics(this.tableData_display);
            console.log("表单以及统计更新完毕。");
        },
        changeTableHeaderDisplay(select_value2){
            //更新表头信息
            switch (select_value2) {
                case "打赏":{
                    this.unit_display = "金币(个)";
                    this.consumer_tip = "打赏用户";
                    break;
                }case "订阅":{
                    this.unit_display = "金币(个)";
                    this.consumer_tip = "订阅用户";
                    break;
                }case "投票":{
                    this.unit_display = "推荐票(张)";
                    this.consumer_tip = "投票用户";
                    break;
                }default:break;
            }
        },
        changeTableDataDisplay(selectWork,selectType){
            //返回与选择对应显示数组 params(num,str)
            let selectData = [];
            let this_ = this;
            for (let i=0;i<selectWork.length;i++){
                let selectWorkValue = selectWork[i];
                if (selectWorkValue === 0){
                    //选中全部的作品
                    this_.tableData_all.forEach(function (value, index, array) {
                        if (value.transaction_type === selectType){
                            selectData.push(value);
                        }
                    });
                    //跳出循环
                    break;
                }else{
                    let selectWorkName = "";
                    this_.work_options.forEach(function (value, index, array) {
                        if (value.value === selectWorkValue){
                            selectWorkName = value.label;
                        }
                    });
                    //选中特定作品
                    this_.tableData_all.forEach(function (value, index, array) {
                        if (value.work_name === selectWorkName && value.transaction_type === selectType){
                            selectData.push(value);
                        }
                    });
                }
            }

            //返回与选择对应的显示数组
            return selectData;
        },
        toStatistics(displayData){
            //更新总交易数量 param:(数组)
            let totalNum = 0;
            displayData.forEach(function (value,index,array) {
                totalNum += value.transaction_quantity;
            });
            return totalNum;
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
        }).catch(error =>{
            this.$confirm('获取用户信息失败！', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //执行操作

            }).catch(() => {
                //执行操作
            });
            console.log("获取本用户ID和name错误。");
        });
        //根据用户的ID获取该作者的作品数据
        axios.get("/shaohuashuwu/worksInfoController/getAllWorksNameByAuthorId").then(response =>{
            //根据用户的ID获取该作者的作品数据
            let objectData = eval(JSON.stringify(response.data));//将字符串转化为数组对象
            // value是当前元素，index当前元素索引，array为当前数组
            this.work_options = [];
            let one_work_option = {
                value:0,
                label:'全部'
            };
            this.work_options.push(one_work_option);
            let this_ = this;       //注意一进入function内嵌函数中，this代表的是其函数，不是vue本身了
            objectData.forEach(function (value,index,array) {
                let one_value = {
                    value:index+1,
                    label:value
                };
                this_.work_options.push(one_value);
            });
            console.log("装配作品数据成功。");
        }).catch(error =>{
            this.$confirm('网络或系统原因，获取界面相关信息失败！', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //执行操作

            }).catch(() => {
                //执行操作
            });
            console.log("装配作品数据失败。");
        });
        //根据用户的ID获取接受者为该作者作品的交易信息
        axios.get("/shaohuashuwu/transactionInfoController/getAllIncomeTransactionInfo").then(response =>{
            //装配交易信息
            let objectData = eval(JSON.stringify(response.data));
            this.tableData_display = [];
            let goldCoin_total_income = 0;
            let goldCoin_already_withdraw = 0;

            let this_ = this;

            objectData.forEach(function (value,index,array) {
                switch (value.transaction_type) {
                    case "打赏":
                    case "订阅":{
                        goldCoin_total_income += value.transaction_quantity/10;
                        let one_data = {
                            work_name: value.recipient_name,
                            chapter_title: value.recipient_name_other,
                            consumer_name: value.consumer_name,
                            transaction_quantity:value.transaction_quantity/10,
                            transaction_time:value.transaction_time,
                            transaction_type:value.transaction_type
                        };
                        this_.tableData_all.push(one_data);
                        break;
                    }
                    case "投票":{
                        let one_data = {
                            work_name: value.recipient_name,
                            chapter_title: value.recipient_name_other,
                            consumer_name: value.consumer_name,
                            transaction_quantity:value.transaction_quantity,
                            transaction_time:value.transaction_time,
                            transaction_type:value.transaction_type
                        };
                        this_.tableData_all.push(one_data);
                        break;
                    }
                    case "提现":{
                        goldCoin_already_withdraw += value.transaction_quantity;
                        this_.withdrawData.push(value);//待后面的数据类型（point:带着数据跳转界面）
                    }
                }
            });
            this.header.goldCoin_total_income = goldCoin_total_income;
            this.header.goldCoin_already_withdraw = goldCoin_already_withdraw;
            this.header.goldCoin_able_withdraw = this.header.goldCoin_total_income - this.header.goldCoin_already_withdraw;
            //调用该js封装好的切换数据的方法（初始展示数据）
            this.tableData_display = this.changeTableDataDisplay([0],"打赏");
            this.statistics = this.toStatistics(this.tableData_display);

            //获取该用户的所有提现记录，用来统计已提现金币数
            axios.get("/shaohuashuwu/transactionInfoController/getAllWithdrawInfo").then(resp =>{
                let objectData = eval(JSON.stringify(resp.data));
                let total_already = 0;
                objectData.forEach(function (value,index,array) {
                    total_already += value.transaction_quantity;
                });
                this.header.goldCoin_already_withdraw = total_already;
                this.header.goldCoin_able_withdraw = this.header.goldCoin_total_income - this.header.goldCoin_already_withdraw;
                console.log("统计已提现金币成功。");
            }).catch(error =>{
                this.$confirm('网络或系统原因，获取界面相关信息失败！', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //执行操作

                }).catch(() => {
                    //执行操作
                });
                console.log("统计已提现金币失败。");
            })
            console.log("装配交易信息成功。");
            /*
            *根据用户的ID统计该作者的总收入（金币数）以及总提现数（金币数）【封装到map集合中】
            * 注意：总收入以及已提现金币数在前端统计即可
            */
        }).catch(error =>{
            console.log("装配交易信息失败。");
        });
    },
})