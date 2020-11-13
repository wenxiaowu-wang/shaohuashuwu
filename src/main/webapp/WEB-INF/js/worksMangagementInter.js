new Vue({
    // router,
    el: '#app',
    data() {
        return {
            worksInfoList:[],
        }
    },
    methods:{


        findalldate () {
            //当前方法中定义一个变量，表明是vue对象
            var _this= this;
            axios.get('http://localhost:8080/account/findallworksdate')
                .then(function (response) {
                    _this.worksInfoList = response.data;//相应数据给userlist
                    // alert(JSON.stringify(response.data));
                    console.log(response.data);
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })

        },
        gorouter(work_id){
            var _this= true;
            console.log(work_id);
            axios.get('http://localhost:8080/worksInfoController/selectworksByworkid?work_id='+work_id)
                .then(function (response){
                    window.location.assign("../pages/mangagementWorksInterface.html");
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })

        },
        text(){
            console.log("要舔砖用户主界面")
            window.location.assign("../pages/userMainInterface.html");
        },

    //    跳转写作界面
        gotowrite(work_id){
            console.log("作品id"+work_id);
            var _this = this;
            axios.get('http://localhost:8080/worksInfoController/selectworksByworkid?work_id='+work_id)
                .then(function (response){
                    window.location.assign("../pages/addChapterInterface.html");
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        }
    },
    created:function (){ //页面加载时查询所有
        this.findalldate();
    }
})