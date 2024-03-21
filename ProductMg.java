class Product{
    private int idno;
    private String PdtName;
    private double price;
    private int quant;
    public void Create(int Idno, String name, double cost, int stock){
        PdtName = name;
        idno = Idno;
        quant = stock;
        price = cost;
        System.out.println("Product is added: " + PdtName);
    }
    public void Read(){
        System.out.println("The ID NO of the Product is " + idno);
        System.out.println("The Price is " + price);
        System.out.println("The Product " + PdtName + " is available with " + " Units " + quant);
    }
    public void Update(double newPr){
        price=newPr;
        System.out.println("The updated price is " + price + " for Product " + PdtName);
    }
    public void Delete(){
        System.out.println("The Product deleted is " + PdtName);
        PdtName=null;
        price=0.0;
        idno=0;
        quant=0;
    }
}
public class ProductManager {
    public static void main(String[] args) {
        Product pdt = new Product();
        pdt.Create(101, "Mobile", 10000.0, 100);
        pdt.Read();
        pdt.Update(9999);
        pdt.Read();
        pdt.Delete();
        pdt.Read();
    }
}
