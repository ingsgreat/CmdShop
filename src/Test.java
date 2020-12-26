import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*
        开始读文件
        */
        File file=new File("C:\\Users\\波比\\IdeaProjects\\cmdshop\\src\\users.xlsx");
        ReadExcel readExcel=new ReadExcel();
        User users[]=readExcel.readExcel(file);
        /*
        从键盘输入用户名及密码进行登陆
         */
        System.out.println("请输入用户名：");
        Scanner scanner=new Scanner(System.in);
        String username= scanner.next();

        System.out.println("请输入密码：");
        String password= scanner.next();

        /*
        把输入的用户名、密码与user.xslx文件中的用户名密码进行比对，若相同则在屏幕上显示“登陆成功”，否则显示“登陆失败”
         */

        for(User user:users){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                System.out.println("登陆成功");
            }else{
                System.out.println("登陆失败");
            }
        }
    }
}
