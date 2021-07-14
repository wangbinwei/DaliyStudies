git a-m 用于提交暂存区的文件

git commit -am 用于提交追踪过的文件

git reset 清空暂存区，保留工作目录

git reset --hard ：重置stage区和工作目录，到上一个commit版本

git reset --soft : 保留工作目录，将重置Head所带来的新差异放入暂存区

git reset HEAD 撤回所有add的文件

git rm --cached -r删除暂存区

git版本回退： git reset --hard 目标版本号

git log 看历史的提交版本号

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\39ff81ca60e149028bb6ae8faff4d3b4\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\e3b5cec60793431a8b99165d9dc31207\clipboard.png)

Repository  (Head指针)

工作目录下面的所有文件都不外乎这两种状态：已跟踪或未跟踪。

已跟踪的文件是指本来就被纳入版本控制管理的文件，在上次快照中有它们的记录，工作一段时间后，它们的状态可能是未更新，已修改或者已放入暂存区

例子：文件夹中新增一个a.txt 时，该文件处于untracked 未跟踪状态，未跟踪的文件是无法被提交的

git add a.txt 文件变为跟踪状态

git clone -b 分支名字 http://xxx  克隆远程的指定分支 

git remote add origin http://xxxx    添加远程仓库的别名

git push -u origin master 远程地址 

git remote -v  查看远程分支  origin 可以看到网址 version

git remote remote (baranch) 移除远程仓库

如果当前分支与多个主机存在追踪关系，则可以使用-u参数指定一个默认主机，这样后面就可以不加任何参数使用git push

不加任何参数的git push,默认只推送当前分支

git push -u -f origin master 远程地址 强制推送

git push origin master:wbw  上传到自己的远程分支wbw，如果没有这个分支就创建

分支开发，主干发布

git branch 查看电脑本地分支

git branch -a 查看本地和远程的所有分支

创建分支

git branch (branchname)

切换分支命令

git checkout (branchname)

git checkout -b (brachname) 是创建一个分支，并立即切换到该分支下	

合并分支--1、整合远程代码仓库中的变化  2、用于从一个分支到另一个分支的合并

git merch origin master 将远程master与本地当前分支相合并

git pull = git fetch + git merge

git log 查看提交的详细信息

git stash  用于临时保存和回复修改，可跨分支  （在未add之前才能使用stash )

git stash list 所有保存的记录列表   

git stash pop stash@{num}  num是可选项

git stash drop stash@{num}  num是可选项

 save "save message" : 执行存储时，添加备注，方便查找，只有git stash 也要可以的，但查找时不方便识别。

我现在想做的是把master复制到wbw分支 ,然后将我修改的代码再上传到wbw分支

git status  命令用于查看在你上次提交之后是否有对文件进行再次修改。查看仓库当前的状态，显示有变更的文件。

git merge远程分支