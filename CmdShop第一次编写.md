# 一、创建CmdShop工程

## 1.1

打开IDEA,选择“CREATE NEW PROJECT”,建立一个名为“CmdShop”的工程。

## 1.2

登录github，点击个人头像，选择“settings”,选择“Developer settings”开发者模式，新建一个身份标识token，输入名称，将下面的所以方框打钩。之后复制身份标识，在IDEA中选择VCS，再点击Import into Version Control,再选择Share Project on Github,在弹出的窗口选择“Enter token”,可将身份标识复制到IDEA中，成功后可在github中看到新生成了一个CmdShop仓库。

## 1.3

新建一个名为users.xlsx的Excel电子表格，在里面输入两行用户信息，再将文件复制到CmdShop工程中。

# 二、添加文件到工程中

## 2.1

加入ReadExcel.java Test.java User.java到工程中。

## 2.2

将jar包导入工程：点击file，选择Project Structure,点击libraries,点+，选择jar包所在的目录，选择jar包，即可导入。

# 三、编写代码

- 读取存有用户信息的文件

```java
File file=new File("C:\\Users\\波比\\IdeaProjects\\cmdshop\\src\\users.xlsx");
        ReadExcel readExcel=new ReadExcel();
        User users[]=readExcel.readExcel(file);
```

- 从键盘输入用户名及密码进行登陆

```java
System.out.println("请输入用户名：");
        Scanner scanner=new Scanner(System.in);
        String username= scanner.next();

        System.out.println("请输入密码：");
        String password= scanner.next();
```

- 把输入的用户名、密码与user.xslx文件中的用户名密码进行比对，若相同则在屏幕上显示“登陆成功”，否则显示“登陆失败”

```java
for(User user:users){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                System.out.println("登陆成功");
            }else{
                System.out.println("登陆失败");
            }
        }
```
