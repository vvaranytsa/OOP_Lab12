package atm;

public class Handler {
    private Handler next;
    private final int quantity;
    public Handler(int quantity){
        this.quantity = quantity;
    }
    public void setNext(Handler next){
        this.next = next;
    }

    public  void process(int price){
        if (price%quantity > 0 && next == null){
            throw new IllegalArgumentException();
        }else if(next != null){
            next.process(price%quantity);
        }
        System.out.println(price/quantity + "* " + quantity);
    }

}
