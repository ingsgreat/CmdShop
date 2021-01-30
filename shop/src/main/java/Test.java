import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    static Product carts[] = new Product[3];//�������ﳵ��������ģ�⣩
    static int count = 0;

    static Map<Integer,Integer> ammount=new HashMap<Integer,Integer>();
    static Map<Integer,Float> totalAmountPerProduct=new HashMap<Integer,Float>();
    public static void main(String[] args) throws ClassNotFoundException {

        boolean bool = true;
        while (bool) {
            System.out.println("�������û�����");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();//��������

            System.out.println("���������룺");
            String password = sc.next();

            //File file=new File("C:\\Users\\Administrator\\IdeaProjects\\ConsoleShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");//  /��ʾ�ľ���classpath
            ReadUserExcel readExcel = new ReadUserExcel();//��������
            User users[] = readExcel.getAllUser(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    bool = false;
                    while (true) {
                        System.out.println("�鿴���ﳵ�밴1");
                        System.out.println("�����밴2");
                        System.out.println("�����밴3");
                        System.out.println("�˳��밴4");
                        int choose = sc.nextInt();
                        if (choose == 1) {
                            viewCarts();
                        } else if (choose == 2) {
                            shopping(sc);//����
                        } else if (choose == 3) {
                            /*
                            1�����������������ж����ࣩ
                            2����POI����Order.xlsx�ļ�
                            3���ѹ��ﳵ�����Ʒд��Order.xlsx�ļ�
                             */
                            Order order = new Order();
                            order.setUser(users[i]);//���������û�
                            Product products[]=new Product[count];
                            int num=0;
                            /*
                            ʵ������2����Ʒ��������carts�е�2��Product�������products
                             */
                            for(int j=0;j<carts.length;j++){
                                if(carts[j]!=null){
                                    products[j]=carts[j];
                                }
                            }
                            order.setProducts(products);//����������Ʒ��ʵ����Ӧ�ý��д�����������Ϊnull��ȥ��
                            //�¶���������Excel��
                            order.setAmmount(ammount);//������������
                            for(Product product:products){
                                //����õ��ĸ���Ʒ�Ĺ�������
                                int cou=ammount.get(Integer.parseInt(product.getId()));//��̬������ת��
                                totalAmountPerProduct.put(Integer.parseInt(product.getId()),product.getPrice()*cou);
                            }
                            order.setTotalAmountPerProduct(totalAmountPerProduct);//����ÿ����Ʒ���ܼ�
                            CreateOrder.createOrder(order);

                        } else if (choose == 4) {
                            break;//���յ���ѭ��������ѭ��������main����������main�̣߳���JavaVMҲ�����
                        }
                    }
                    break;
                } else {
                    System.out.println("��¼ʧ��");
                }
            }
        }
    }

    public static void viewCarts() {
        for (Product product : carts) {
            if (product != null) {
                System.out.print(product.getId());
                System.out.print("\t" + product.getName());
                System.out.print("\t\t" + product.getPrice());
                System.out.println("\t\t" + product.getDesc());
            }
        }
    }

    /**
     * ����
     * @param sc
     * @throws ClassNotFoundException
     */
    public static void shopping(Scanner sc) throws ClassNotFoundException {
        InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /��ʾ�ľ���classpath
        ReadProductExcel readProductExcel = new ReadProductExcel();
        Product products[] = readProductExcel.getAllProduct(inPro);
        for (Product product : products) {
            System.out.print(product.getId());
            System.out.print("\t" + product.getName());
            System.out.print("\t\t" + product.getPrice());
            System.out.println("\t\t" + product.getDesc());
        }
        /*
        ��������
         */
        System.out.println("��������ƷID�Լ�������������ƷID�������ö��Ÿ���������1111,4���Ѹ���Ʒ���빺�ﳵ��");
        String pInfo=sc.next();
        String str[]=pInfo.split(",");

        String pId = str[0];//��ƷID
        String num=str[1];//��������

        ammount.put(Integer.parseInt(pId),Integer.parseInt(num));

        ReadProductExcel readProductExcel1 = new ReadProductExcel();
        inPro = null;
        inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /��ʾ�ľ���classpath
        Product product = readProductExcel1.getProductById(pId, inPro);
        if (product != null) {
            /*
            ����Ʒ���빺�ﳵ
             */
            carts[count++] = product;
        }
    }
}