# 项目介绍
前端vue+nuxt+element ui+bootstrapVue，nginx，后端spring boot+mybatis plus+websocket+mongodb。前后端分离练习项目。
template文件夹下是前端项目，article文件夹下是后端项目，其中的nginx是配置好将所以带‘/’的请求代理到前端项目3000端口，将带‘/api/’代理到后端8081端口。
# 实现的功能
首页的文章分页联表条件查询，发布文章，查询文章详情，文章评论，基于JWT令牌+拦截器实现的登录，添加好友，好友通讯录列表（并显示未读消息数），好友即时聊天，显示已读和未读，使用aop为文章所有的查询方法添加redis缓存，文章收藏，‘发现’页面的随机查询，根据用户id查询用户朋友的文章
# 首页
![Image text](https://raw.githubusercontent.com/528854302/myproject/master/template/static/img/1.PNG)
# 文章详情页
![Image text](https://github.com/528854302/myproject/blob/master/template/static/img/2.PNG)
# 聊天界面
![Image text](https://github.com/528854302/myproject/blob/master/template/static/img/3.PNG)
# 好友通讯录
![Image text](https://raw.githubusercontent.com/528854302/myproject/master/template/static/img/4.PNG)
# 即时聊天
![Image text](https://github.com/528854302/myproject/blob/master/template/static/img/5.PNG)
