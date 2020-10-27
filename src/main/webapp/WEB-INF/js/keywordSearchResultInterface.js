new Vue({
    el: '#selectresult-id',
    data() {
        return {

        }
    },
    methods: {
        startthisHtml() {
            console.log("11211");
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/selectworkbyinfoResult')
                .then(function (respone) {
                    // console.log("相应结果："+respone.data);

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        }

    },
    created:function (){ //页面加载时查询所有
      this.startthisHtml();
    }
})