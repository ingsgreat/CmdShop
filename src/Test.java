import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Boolean bool = true;
        while (bool) {
            System.out.println("请输入用户名：");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.next();//阻塞方法

            System.out.println("请输入密码：");
            String password = scanner.next();
        /*
        开始读文件
        */
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");

            InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");

            ReadUserExcel readUserExcel = new ReadUserExcel();
            User users[] = readUserExcel.readUserExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername().trim()) && password.equals(users[i].getPassword().trim())) {
                    bool = false;
                    ReadProductExcel readProductExcel=new ReadProductExcel();
                    Product products[]=readProductExcel.getAllExcel(inProduct);
                    for(Product product:products){
                        System.out.print(product.getPid());
                        System.out.print("\t"+product.getPname());
                        System.out.print("\t"+product.getPrice());
                        System.out.println("\t"+product.getDesc());
                    }
                    int count=0;
                    Product carts[]=new Product[3];//创建购物车，用数组模拟
                    System.out.println("请输入商品ID把该商品加入购物车：");
                    String pid= scanner.next();
                    ReadProductExcel readProductExcel1=new ReadProductExcel();
                    inProduct=null;
                    inProduct= Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product =readProductExcel.getProductByID(pid,inProduct);

                    if(product!=null){
                        Product product1=new Product();
                    }
                    System.out.println("请选择接下来的操作：查看购物车请按1,继续购物请按2");
                    //1表示查看购物车
                    //2表示继续购物

                    int choose=scanner.nextInt();
                    if (choose == 1) {
                        for (int j = 0; j < carts.length; j++) {
                            if (carts[j] != null) {
                                System.out.print(carts[j].getPid());
                                System.out.print("\t" + carts[j].getPname());
                                System.out.print("\t\t" + carts[j].getPrice());
                                System.out.println("\t\t" + carts[j].getDesc());
                            }
                        }
                    } else if (choose == 2) {
                        readProductExcel = new ReadProductExcel();
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
                        products = readProductExcel.getAllExcel(inProduct);
                        for (Product p : products) {
                            System.out.print(p.getPid());
                            System.out.print("\t" + p.getPname());
                            System.out.print("\t\t" + p.getPrice());
                            System.out.println("\t\t" + p.getDesc());
                        }
                        /*
                        遍历数组
                        */
                        System.out.println("请输入商品ID，把该商品加入购物车");
                        pid=scanner.next();
                        readProductExcel1=new ReadProductExcel();
                        inProduct=null;
                        inProduct=Class.forName("Test").getResourceAsStream("/product.xlsx");
                        Product product1=readProductExcel1.getProductByID(pid,inProduct);
                        if(product!=null){
                            carts[count++]=product1;
                        }
                    }
                     /*
                    1、查看购物车
                    （1）购物车是用数组模拟的
                    （2）就是把数组内的元素一个一个找出来：对数组遍历
                    2、继续购物
                    （1）又要显示所有商品
                     */
                        break;
                } else {
                    System.out.println("登陆失败");
                }
            }
        }
    }
}
