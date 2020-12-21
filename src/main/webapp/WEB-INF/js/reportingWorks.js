let reportingWorksInterface_vm = new Vue({
    el:"#reportingWorks",
    data:{
        checkedList:[],   //被选中的值
        dialogFormVisible: true,

        // userId:0,
        workId:0,
        workName:"疯狂传奇",
        chapterId:0,
        chapterTitle:"第三十二章：每个人都是一个疯子",
    },
    methods:{
        toReportAnnouncement(){
            window.open("../pages/reportAnnouncement.html");
        },
        reportingWorks(){
            if (this.checkedList.length < 1){
                this.$message({
                    type: 'error',
                    message: '举报原因不得少于一个，不得多于三个！'
                });
            }else{
                axios.post("/shaohuashuwu_war_exploded/reportInfoController/reportDetectionChapter/" +
                    this.checkedList).then(resp =>{
                    let data_int = parseInt(JSON.stringify(resp.data));
                    switch (data_int){
                        case 0:{
                            //举报失败，该内容未涉及相关违规
                            this.$message({
                                type:'error',
                                message:'举报失败，该内容未涉及相关违规。'
                            });
                            break;
                        }case 1:{
                            //举报成功，该内容成功下架
                            this.$message({
                                type:'success',
                                message:'举报成功，该内容成功下架。'
                            });
                            break;
                        }case 2:{
                            //系统智能无法判断，等待人工审核。
                            this.$message({
                                type:'info',
                                message:'系统无法判定，举报消息保存成功，请等待人工审核。'
                            });
                            break;
                        }default:{
                            this.$message({
                                type:'error',
                                message:'举报请求响应失败！'
                            });
                            break;
                        }
                    }
                        console.log("举报请求响应成功！");

                }).catch(error =>{
                    console.log("举报请求响应失败！"+error);
                });
            }
        },
    },
    mounted(){
        //获取当前用户的ID以及将要打赏作品的ID（后端模拟session域取值）
        axios.get("/shaohuashuwu_war_exploded/worksSession/getWork").then(response =>{
            console.log("获取作品ID成功，作品ID=["+JSON.stringify(response.data["workId"])+"]")
            let work_id = JSON.stringify(response.data["work_id"]);
            let work_name = response.data["work_name"];

            work_id = parseInt(work_id);
            this.workId = work_id;
            this.workName = work_name;
        }).catch(error =>{
            this.$confirm('获取作品以及章节信息失败！', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //执行操作
                this.dialogFormVisible = false;
            }).catch(() => {
                //执行操作
                this.dialogFormVisible = false;
            });
            console.log("获取作品ID失败");
        });
        axios.get("/shaohuashuwu_war_exploded/chapterSession/getChapter").then(response =>{
            console.log("获取章节ID成功，作品ID=["+JSON.stringify(response.data["chapterId"])+"]")
            let chapter_id = JSON.stringify(response.data["chapter_id"]);
            let chapter_title = response.data["chapter_title"];

            chapter_id = parseInt(chapter_id);
            this.chapterId = chapter_id;
            this.chapterTitle = chapter_title;
        }).catch(error =>{
            this.$confirm('获取作品以及章节信息失败！', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //执行操作
                this.dialogFormVisible = false;
            }).catch(() => {
                //执行操作
                this.dialogFormVisible = false;
            });
            alert("获取章节信息失败");
        });
        //created钩子函数是在HTML渲染前执行，若涉及到尚未渲染的变量，会无法找到
    },
})