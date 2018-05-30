var header = getApp().globalData.header;
const app = getApp()
Page({
  data: {
    list: [
      {
        name: '姓名',
        data: '',
      }, {
        name: '学院',
        data: '',
      }, {
        name: '班级',
        data: '',
      }, {
        name: '学号',
        data: '',
      }
    ]
  },
  onLoad: function(){
    let that = this;
    wx.request({
      url: app.globalData.services + '/news/userDetail',
      header: header,
      success: function (res) {
        that.setData({
          "list": res.data
        });
      },
      fail: function (r) {
      }
    });
  } ,
  widgetsToggle: function (e) {
    var id = e.currentTarget.id, list = this.data.list;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (list[i].id == id) {
        list[i].open = !list[i].open;
      } else {
        list[i].open = false;
      }
    }
    this.setData({
      list: list
    });
  }
});
