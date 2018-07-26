# template

## classroom.html
教室
- 个人已选课列表 courseList,
- 显示部分个人信息 user
- 提供编辑个人信息入口 setting.html
- 选课入口 selectcourse.html
- 学习动作入口 course. 

需要到的具体字段
- 用户名(username)
- 信息条数(messageCount)
- 头像(userLogo)
- 上一次学习的课程(lastCourse)
- 正在学习的动作(currentAction)
- 课程列表(courseList)
- 课程推荐(recommendCourse)

## class.html
课程教学页面

- 课程标题(courseTitle)
- beanList{//具有关联性

    目录-图片路径(视频)-动作标题-动作介绍
    
}

## person.html
个人训练数据页面
数据列表 personList
按月段来划分 

## setting.html
修改个人信息页面，以及设置页面
用户名 密码 邮箱

课程退订，续费

## selectClass.html
选课页面

- 课程类型(选做)
- 课程列表（课程图片[picUrl]，课程标题[courseTitle]，课程简介[courseMessage]，课程介绍链接[courseInfo.html]）

## courseInfo.html
课程介绍
- 精美的课程内容介绍(自定义介绍动作内容，学习时间，适合人群，达到的效果。。。)
- 学习课程 course.html

## game.html
游戏大厅列表(list)
- 游戏图片(gamePic)
- 游戏标题(gameTitle)
- 游戏简介(gameInfo)

