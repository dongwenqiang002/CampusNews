var header = getApp().globalData.header;
const app = getApp()
Page({
  data: {
    list: [
      {
        id: 'view',
        name: '姓名',
        open: false,
        pages: ['view', 'scroll-view', 'swiper']
      }, {
        id: 'content',
        name: '学院',
        open: false,
        pages: ['text', 'icon', 'progress']
      }, {
        id: 'form',
        name: '班级',
        open: false,
        pages: ['button', 'checkbox', 'form', 'input', 'label', 'picker', 'radio', 'slider', 'switch']
      }, {
        id: 'feedback',
        name: '学号',
        open: false,
        pages: ['action-sheet', 'modal', 'toast', 'loading']
      }, {
        id: 'nav',
        name: '导航',
        open: false,
        pages: ['navigator']
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
