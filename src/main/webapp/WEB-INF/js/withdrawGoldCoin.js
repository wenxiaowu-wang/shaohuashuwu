let withdrawGoldCoinInterface_vm = new Vue({
    el:"#withdrawGoldCoin_div",
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

        withdraw_options: [{
            value: 0,
            label: '0金币'
        },{
            value: 100,
            label: '100金币'
        }, {
            value: 300,
            label: '300金币'
        }, {
            value: 500,
            label: '500金币'
        }, {
            value: 1000,
            label: '1,000金币'
        }, {
            value: 5000,
            label: '5,000金币'
        },{
            value: 10000,
            label: '10,000金币'
        }],
        select_value:0,

        statistics:0,           //统计的表格金额（元￥）
        unit_display:"金币(个)",      //交易单位
        consumer_tip:"打赏用户", //消费者提示

        dialogFormVisible:false, //提现对话框的显示

        //对话框表单内容
        withdrawForm:{
            third_party_number:"",
            third_party_name:"请选择第三方平台"
        },
        formLabelWidth: '120px',    //对话框表单项宽度

        tableData_display:[{
            withdraw_id: '12',
            third_party_number: '183639874363',
            third_party_name: '支付宝',
            withdraw_quantity:50000,
            withdraw_time:'2020-11-11-07:51:36',
            withdraw_type:"提现"
        },{
            withdraw_id: '12',
            third_party_number: '183639874363',
            third_party_name: '微信',
            withdraw_quantity:50000,
            withdraw_time:'2020-11-11-07:51:36',
            withdraw_type:"提现"
        }],
        tableData_all:[],   //总收入数据（包含提现）
        withdrawData:[]     //提现数据
    },
    methods:{
        toWithdraw(){
            //提现按钮点击后提示
            this.$message({
                type:'info',
                message:'您当前已经在提现界面，您可以选择或输入提现金豆数量'
            });
        },
        handleChange(value) {
            console.log(value);
        },
        formatter(row, column) {
            return row.address;
        },
        determineAmount(){
            //确认金额后执行
            //判断输入或选择是否符合标准
            if (typeof (this.select_value) !== typeof (1) || this.select_value < 100 || this.select_value%10 !== 0){
                this.$message({
                    type: 'error',
                    message: '请输入大于100的正整数，且为10的倍数，或选择已有列表。'
                });
            }else if (this.select_value > this.header.goldCoin_able_withdraw){
                this.$message({
                    type: 'error',
                    message: '您的可供提现的金币数量不足，请重新选择。'
                });
            }else{
                console.log(this.select_value+"   "+this.header.goldCoin_able_withdraw);
                //打开弹窗，输入第三方账户
                this.dialogFormVisible = true;
            }
        },
        commitWithdrawForm(){
            //检查表单
            if (this.withdrawForm.third_party_name === "请选择第三方平台" || this.third_party_number === ""){
                this.$message({
                    type:'error',
                    message:'请输入和选择对应第三方账号，平台将自动打入金额到该账号。'
                });
            }else{
                //提交表单
                axios.post("/shaohuashuwu/transactionInfoController/withdrawGoldCoin/" +
                    this.select_value/10+"/"+this.withdrawForm.third_party_name+"/"+
                this.withdrawForm.third_party_number).then(resp =>{
                    //提现成功进行更新表单和统计数额的操作
                    //提现成功返回的是一条提现消息（只是一条transaction_info中的记录，不包括第三方账号）
                    console.log(typeof (resp.data));
                    console.log(resp.data);
                    this.updateTableDisplay(resp.data);
                    //更新统计数量
                    this.updateStatistics();
                    console.log("提现成功！");
                    //关闭表单
                    this.dialogFormVisible = false;
                    this.$message({
                        type:'success',
                        message:'提现成功，继续努力哦。'
                    });
                }).catch(error =>{
                    console.log("提现失败，数据更新失败");
                    this.$message({
                        type:'error',
                        message:'网络或其它原因，提现失败！'
                    });
                });
            }

        },
        updateStatistics(){
            //更新统计数量
            this.header.goldCoin_already_withdraw += this.select_value;
            this.header.goldCoin_able_withdraw -= this.select_value;
            this.statistics = this.header.goldCoin_already_withdraw / 10;
        },
        updateTableDisplay(objectData){
            //给总数据和显示数据增加一条数据
            let oneData = {
                withdraw_id: objectData.transaction_id,
                third_party_number: this.withdrawForm.third_party_number,
                third_party_name: this.withdrawForm.third_party_name,
                withdraw_quantity:objectData.transaction_quantity,
                withdraw_time:objectData.transaction_time,
                withdraw_type:"提现"
            };
            this.tableData_all = this.tableData_all.push(oneData);
            this.tableData_display = this.tableData_all;
            console.log("表单以及统计更新完毕。");
        },
    },
    mounted(){
        //钩子函数，在加载页面后，渲染数据前执行
        //获取本用户的ID和name以及关注用户的信息
        let dialog = false;
        axios.get("/shaohuashuwu/userSession/getUser").then(resp =>{
            let info = resp.data;
            let userId = JSON.stringify(info["user_id"]);
            let userName = info["user_name"];
            userId = parseInt(userId);
            this.user_id = userId;
            this.user_name = userName;
            console.log("用户数据装配成功");
        }).catch(error =>{
            dialog = true;
            this.header.goldCoin_able_withdraw = 0;     //获取信息失败额处理
            console.log("装配用户数据失败！");
        });
        if (!dialog){
            this.$confirm('网络或其它原因，获取界面信息失败！', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //执行操作
                this.header.goldCoin_able_withdraw = 0;     //获取信息失败额处理
            }).catch(() => {
                //执行操作
                this.header.goldCoin_able_withdraw = 0;     //获取信息失败额处理
            });
        }
        //根据用户的ID获取该作者的作品数据
        axios.get("/shaohuashuwu/transactionSession/getTransactionGoldCoin").then(response =>{
            //获取transactionSession中的总收入以及已提现金豆数
            this.header.goldCoin_total_income = response.data["goldCoin_total_income"];
            this.header.goldCoin_already_withdraw = response.data["goldCoin_already_withdraw"];
            this.header.goldCoin_able_withdraw = this.header.goldCoin_total_income - this.header.goldCoin_already_withdraw;

            /*
            *根据用户的ID统计的该作者的总收入（金币数）以及总提现数（金币数）已经发送到transactionSession中了【封装到map集合中】
            * 注意：总收入以及已提现金币数在从transactionSession中获取即可
            */
            console.log("装配统计金币数成功。");
        }).catch(error =>{
            dialog = true;
            this.header.goldCoin_able_withdraw = 0;     //获取信息失败额处理
            console.log("装配统计金币数失败。");
        });
        //根据用户的ID获取接受者为该作者作品的提现记录信息
        axios.get("/shaohuashuwu/transactionInfoController/getAllWithdrawInfo").then(response =>{
            //装配交易信息
            let objectData = eval(JSON.stringify(response.data));
            this.tableData_display = [];
            let this_ = this;
            let statistics_yuan = 0;

            objectData.forEach(function (value,index,array) {
                let one_data = {
                    withdraw_id: value.transaction_id,
                    third_party_number: value.recipient_name_other,
                    third_party_name: value.transaction_mode,
                    withdraw_quantity:value.transaction_quantity,
                    withdraw_time:value.transaction_time,
                    withdraw_type:value.transaction_type
                };
                this_.tableData_all.push(one_data);
                statistics_yuan += value.transaction_quantity;
            });

            //调用该js封装好的切换数据的方法（初始展示数据）
           this.tableData_display = this.tableData_all;
           this.statistics = statistics_yuan;
           console.log("装配交易信息成功。");

        }).catch(error =>{
            this.$message({
                type:'error',
                message:'网络或其它原因，获取提现记录信息失败！'
            });
           console.log("装配交易信息失败。");
        });

    },
})