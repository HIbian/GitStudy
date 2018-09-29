# 查询条件 分页

```java
	private void queryPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName")==null?"":request.getParameter("userName");//第一次进入index时，为null
		int state = request.getParameter("state")==null?-1:Integer.parseInt(request.getParameter("state"));
		//获取页码，第一次进入为null，需要设置初始值
		int pageIndex = request.getParameter("indexPage")==null?1:Integer.parseInt( request.getParameter("indexPage"));
		//设置查询条件
		User userT = new User(userName, state);
		//设置分页每一页大小
		int pageSize = 5;
		//获取查询结果
		int totalCount = us.getTotalCount(userT);
		List<User> userPage = us.getUserPage(userT, pageIndex, pageSize);
		//封装为对象传递给jsp,需要做数据回显，需要传入的值有userT，pageIndex，pageSize，totalCount，userPage
		pageHelper ph = new pageHelper(userT, pageIndex, pageSize, totalCount, userPage);
		//+++获取页码按钮信息
		int[] pageIndexList = getPageIndexList(pageIndex, pageSize, totalCount);
		//设置attribute并传入jsp中
		request.setAttribute("ph", ph);
		request.setAttribute("pageIndexList", pageIndexList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
```
获取页码列表
```java
	//获取页码列表
	private int[] getPageIndexList(int pageIndex,int pageSize,int totalCount) {
		int maxPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize+1);
		if (maxPage<=10) {
			int[] pageIndexList = new int[maxPage];
			for (int i = 0; i <maxPage; i++) {
				pageIndexList[i] = i+1;
			}
			return pageIndexList;
		}
		int[] pageindexList = new int[10];
		if (pageIndex<=5) {
			for (int i = 0; i < pageindexList.length; i++) {
				pageindexList[i] = i+1;
			}
			return pageindexList;
		}
		if (maxPage-pageIndex<=5) {
			for (int i = 0; i < pageindexList.length; i++) {
				pageindexList[i] = maxPage-pageindexList.length+1+i;
			}
			return pageindexList;
		}
		for (int i = 0; i < pageindexList.length; i++) {
			pageindexList[i] = pageIndex-5+i;
		}
		return pageindexList;
	}
```

```java
	@Override
	public int getTotalCount(User u) {
		//获取查询的两个条件
		String userName = u.getUserName();
		//states -1查询全部 0禁用 1启用
		int state = u.getState();
		//sql字符串
		StringBuffer sql = new StringBuffer("select * from t_user where delState=0");
		//创建存放sql参数的list
		List<Object> parms = new ArrayList<Object>();
		if (userName!=null) {
			sql.append(" and userName like ?");
			parms.add("%"+userName+"%");
		}
		//查询启用或禁用的user
		if (state==1||state==0) {
			sql.append(" and state =?");
			parms.add(state);
		}
		//执行查询语句
		List<User> users = null;
		try {
			users = qr.query(sql.toString(), new BeanListHandler<User>(User.class),parms.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users.size();
	}
  @Override
	public List<User> getUserPage(User u, int pageIndex, int pageSize) {
		//获取查询的两个条件
		String userName = u.getUserName();
		//states -1查询全部 0禁用 1启用
		int state = u.getState();
		//sql字符串
		StringBuffer sql = new StringBuffer("select * from t_user where delState=0");
		//创建存放sql参数的list
		List<Object> parms = new ArrayList<Object>();
		if (userName!=null) {
			sql.append(" and userName like ?");
			parms.add("%"+userName+"%");
		}
		//查询启用或禁用的user
		if (state==1||state==0) {
			sql.append(" and state =?");
			parms.add(state);
		}
		//添加分页条件
		sql.append(" limit ?,?");
		parms.add((pageIndex-1)*pageSize);//查询的起始位置
		parms.add(pageSize);
		//执行查询语句
		List<User> users = null;
		try {
			users = qr.query(sql.toString(), new BeanListHandler<User>(User.class),parms.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
```
