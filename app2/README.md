# 功能：
1. 显示公众号列表
2. 显示公众号下文章（仅首页，不支持上拉加载更多页）
3. 支持跳转文章详情
# 技术点：
1. retrofit+okhttp:访问网络接口获取数据
2. coroutines:异步调用
3. BottomNavigationView: 底部导航栏
4. recyclerview:显示列表
5. WebView:显示文章详情
6. SwipeRefreshLayout:支持下拉刷新，不支持上拉加载