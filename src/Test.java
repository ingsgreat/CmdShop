import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Boolean bool = true;
        while (bool) {

            System.out.println("请输入用户名：");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.next();

            System.out.println("请输入密码：");
            String password = scanner.next();
        /*
        开始读文件
        */
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadUserExcel readUserExcel = new ReadUserExcel();
            User users[] = readUserExcel.readUserExcel(in);
            System.out.println("从Excel读取的密码：" + users[0].getPassword());

            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername().trim()) && password.equals(users[i].getPassword().trim())) {
                    System.out.println("登陆成功");
                    bool = false;
                    ReadProductExcel readProductExcel=new ReadProductExcel();
                    Product products[]=readProductExcel.getAllExcel(inProduct);
                    for(Product product:products){
                        System.out.print(product.getPid());
                        System.out.print("\t"+product.getPname());
                        System.out.print("\t"+product.getPrice());
                        System.out.println("\t"+product.getDesc());
                    }
                    System.out.println("请输入商品ID把该商品加入购物车：");
                    String pid= scanner.next();
                    ReadProductExcel readProductExcel1=new ReadProductExcel();
                    inProduct=null;
                    inProduct= Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product =readProductExcel.getProductByID(pid,inProduct);

                    if(product!=null){
                        int a[]=new int[4];
                        Product product1=new Product();
                    }
                    int choosen=1;
                    System.out.println("请选择接下来的操作：1表示查看购物车,2表示继续购物");
                    //1表示查看购物车
                    //2表示继续购物
                    if (choosen==1){
                        ReadProductExcel readProductExcel2=new ReadProductExcel();
                        inProduct=null;
                        inProduct= Class.forName("Test").getResourceAsStream("/product.xlsx");
                        Product product1 =readProductExcel.getProductByID(pid,inProduct);
                    }
                    break;
                } else {
                    System.out.println("登陆失败");
                }
            }
        }
    }
}
