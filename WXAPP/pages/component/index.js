var header = getApp().globalData.header;
const app = getApp()
Page({
  data: {
    list: [
      {
        id: 'view',
        name: '姓名',
        data: '',
      }, {
        id: 'content',
        name: '学院',
        data: '',
      }, {
        id: 'form',
        name: '班级',
        data: '',
      }, {
        id: 'feedback',
        name: '学号',
        data: '',
      }
    ]
  },
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
