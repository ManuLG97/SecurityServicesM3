package securityservices.operations;

public class OrderDetail {

    protected int amount;
    protected double price;
    protected String ref;

    public OrderDetail() {
    }

    public OrderDetail(String ref, int amount, double price) {
        this.ref = ref;
        this.amount = amount;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
