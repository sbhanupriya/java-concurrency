package projects.BankTransaction;

public class Account {
    private int accountId;
    private String name;
    private volatile Integer amount;
    public Account(int id, String name, int  money){
        accountId = id;
        this.name = name;
        amount = money;
    }

    public int getAccountId(){
        return  accountId;
    }
    public String getName(){
        return name;
    }
    public Integer getAmount(){
        return amount;
    }
    public synchronized boolean debit(Integer money){
        if(amount<money){
            System.out.println("Money not sufficient!!");
            return false;
        }
        System.out.println(accountId + " DEBIT  " + money + " final " + (amount-money));
        amount = amount-money;

        return true;
    }
    public synchronized boolean credit(Integer money){
        System.out.println(accountId + " CREDIT  TO " + money + "final " + (amount+money));
        amount += money;
        return true;
    }
    public void print(){
        System.out.println("***** " + name + " " + amount);
    }
}
