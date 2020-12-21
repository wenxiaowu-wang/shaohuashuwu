new Vue({
    el:"#subscriptionChapter",
    data:{

        work_name:'测试书籍',

        dialogFormVisible: false,//书籍评论控制的dialog

        gold_bean_num:'100',
        gold_bean_num_pay:'200',
        titleChapter:'10',

        chapterList: [
            {
                chapterId: 2,
                chapterName: "第二章 测试章节",
                chapterNum:'123',
                chapterPrice:'10',
                isSubscript:'未订阅',

            },
            {
                chapterId: 3,
                chapterName: "第三章 测试章节",
                chapterNum:'124',
                chapterPrice:'10',
                isSubscript:'未订阅',

            },
            {
                chapterId: 4,
                chapterName: "第四章 测试章节",
                chapterNum:'125',
                chapterPrice:'10',
                isSubscript:'未订阅',
            }
        ]



    },
    methods:{

        subscribeAll(){
            if(this.gold_bean_num < this.gold_bean_num_pay){
                alert("金豆不足");
                this.dialogFormVisible = 'true';
            }
        }

    },

    created(){

    }
})