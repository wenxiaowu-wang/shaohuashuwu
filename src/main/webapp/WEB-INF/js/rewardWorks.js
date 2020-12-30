let rewardInterface_vm = new Vue({
    el:"#dialogForm_reward",
    data:{
        dialogFormVisible: false,
        paymentCheck: "",
        form: {
            beanNum:50,
            balanceBean:0,
            goldCoin:0,
            workName:"斗破苍穹之斗罗大陆",
            chapterTitle:"第一千一百九十二章：重回吞噬星空",

            //后补
            workId:0,
            chapterId:0
        },
        balanceTips:"",
        formLabelWidth: '120px',
    },
    methods:{
        changeCoin(){
            this.form.goldCoin = this.form.beanNum/10;
            if (this.form.balanceBean < this.form.beanNum){
                this.balanceTips = "账户余额不足";
            }else{
                this.balanceTips = "";
            }
        },
        rewardWorks(){
            //校验提交信息

            if(this.form.beanNum<50 || this.userId === 0 || this.workId === 0 || this.form.balanceBean < this.form.beanNum){
                this.$message({
                    type:'error',
                    message:'余额不足或未选择打赏金额'
                });
            }else{
                //后补 发送先存session 手动加密
                axios.post("/shaohuashuwu/transactionInfoController/postIntoSession/"+
                this.form.workId+"/"+this.form.chapterId).then(resp =>{
                    this.rewardCommit();
                }).catch(error =>{
                    this.$message({
                        type:'error',
                        message:'网络或其他原因，系统错误！'
                    });
                });
            }
        },
        rewardCommit(){
            axios.post("/shaohuashuwu/transactionInfoController/tipsWork/"
                +this.form.beanNum
            ).then(response => {
                if (response.data){
                    this.dialogFormVisible = false;
                    this.$message({
                        type: "success",
                        message: "打赏成功"
                    });
                }else{
                    this.$message({
                        type: "error",
                        message: "打赏失败"
                    });
                }

            }).catch(error => {
                this.$message({
                    type:'error',
                    message:'提交打赏请求失败！'
                });
                console.log("提交打赏请求失败"+error);
            });
        },
    },
    created(){
        //获取用户当前剩余金豆数量(后端会根据从session中获取的用户ID查找数据)
        axios.get("/shaohuashuwu/userInfoController/getGoldBeanNum").then(resp =>{
            let goldBean = JSON.stringify(resp.data);
            goldBean = parseInt(goldBean);
            this.form.balanceBean = goldBean;
            console.log("已获取到用户当前剩余金豆数量"+goldBean);
        }).catch(error =>{
            this.$message({
                type:'error',
                message:'获取用户当前金豆数量失败'
            });
            console.log("获取用户当前金豆数量失败"+error);
        });
        //获取作品以及章节信息(暂时不用)
        //created钩子函数是在HTML渲染前执行，若涉及到尚未渲染的变量，会无法找到
    },
})