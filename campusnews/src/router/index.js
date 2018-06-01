import Vue from 'vue';
import Router from 'vue-router';
import axios from 'axios'
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
Vue.prototype.$axios = axios;

let rot = {
    routes: []
};

Vue.use(Router);
let r = new Router(rot);

export default r;

//从服务器加载路径映射
Vue.prototype.$axios.get("./static/jump.json").then((res) => {
    setResolve(res.data);
    r.addRoutes(res.data);
});



function setResolve(cc) {
    cc.forEach(function (rotePath) {
        if (rotePath.children)setResolve(rotePath.children);
        if (rotePath.component) {
            let rr = rotePath.component;
            rotePath.component = resolve => require(['../' + rr + '.vue'], resolve);
        }
    });
}
