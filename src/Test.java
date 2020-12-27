import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Boolean bool=true;
        while (true){

            System.out.println("请输入用户名：");
            Scanner scanner=new Scanner(System.in);
            String username= scanner.next();

            System.out.println("请输入密码：");
            String password= scanner.next();
        /*
        开始读文件
        */
            InputStream in=Class.forName("Test").getResourceAsStream("/users.xlsx");
            ReadExcel readExcel=new ReadExcel();
            User users[]=readExcel.readExcel(in);
            System.out.println("从Excel读取的密码："+users[0].getPassword());

            for(int i=0;i<users.length;i++){
                if(username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())){
                    System.out.println("登陆成功");
                    bool=false;
                    break;
                }else{
                    System.out.println("登陆失败");
                }
            }
        }
    }
}
