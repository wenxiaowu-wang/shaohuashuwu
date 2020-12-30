let voteWorksInterface_vm = new Vue({
    el:"#dialogForm_vote",
    data:{
        dialogFormVisible: false,
        form: {
            voteNum:1,
            balanceTicket:0,
            workName:"斗破苍穹之斗罗大陆",
            chapterTitle:"第一千一百九十二章：重回吞噬星空",

            //后补
            workId:0,
            chapterId:0
        },
        balanceTips:"",
        formLabelWidth: '120px',
        voteTipsFont:"每周日24点清空推荐票，次日早上八点发放7张推荐票"
    },
    methods:{
        changeVote(){
            if (this.form.balanceTicket < this.form.voteNum){
                this.balanceTips = "账户剩余推荐票不足";
            }else{
                this.balanceTips = "";
            }
        },
        voteWorks(){
            //校验提交信息
            if(this.form.voteNum<1 || this.userId === 0 || this.workId === 0 || this.form.balanceTicket < this.form.voteNum){
                this.$message({
                    type:'error',
                    message:'剩余推荐票不足或未选择投票数量'
                });
            }else{
                //后补 发送先存session 手动加密
                axios.post("/shaohuashuwu/transactionInfoController/postIntoSession/"+
                    this.form.workId+"/"+this.form.chapterId).then(resp =>{
                    this.voteCommit();
                }).catch(error =>{
                    this.$message({
                        type:'error',
                        message:'网络或其他原因，系统错误！'
                    });
                });
            }
        },
        voteCommit(){
            axios.post("/shaohuashuwu/transactionInfoController/voteWork/"
                +this.form.voteNum
            ).then(response => {
                if (response.data){
                    this.dialogFormVisible = false;
                    this.form.balanceTicket = this.form.balanceTicket - this.form.voteNum;
                    this.$message({
                        type: "success",
                        message: "投票成功"
                    });
                }else{
                    this.$message({
                        type: "error",
                        message: "投票失败"
                    });
                }

            }).catch(error => {
                this.$message({
                    type:'error',
                    message:'提交投票请求失败！'
                });
                console.log("提交投票请求失败"+error);
            });
        },
    },
    mounted(){
        //获取用户当前剩余推荐票数量(后端会根据从session中获取的用户ID查找数据)
        axios.get("/shaohuashuwu/userInfoController/getTicketNum").then(resp =>{
            let ticketNum = JSON.stringify(resp.data);
            ticketNum = parseInt(ticketNum);
            this.form.balanceTicket = ticketNum;
            console.log("已获取到用户当前剩余推荐票数量"+ticketNum);
        }).catch(error =>{
            this.$message({
                type:'error',
                message:'获取用户当前推荐票数量失败'
            });
            console.log("获取用户当前推荐票数量失败"+error);
        });
    },
})

