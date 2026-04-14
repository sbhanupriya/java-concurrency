package projects.BankTransaction;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accountList;
    public Bank(){
        accountList = new ArrayList<>();
    }
    public void transfer(int account1, int account2, int amount){
        Account acc1 = accountList.stream().filter(acc -> acc.getAccountId()==account1).findFirst().get();
        Account acc2 = accountList.stream().filter(acc ->  acc.getAccountId()==account2).findFirst().get();

        if(acc1==null || acc2==null)
            throw new RuntimeException("Account not found");

        Account first = acc1.getAccountId()<acc2.getAccountId()?acc1:acc2;
        Account second = acc1.getAccountId()>acc2.getAccountId()?acc1:acc2;

        synchronized (first) {
            synchronized (second){
                if(acc1.debit(amount)){
                    acc2.credit(amount);
                    System.out.println("Transaction successful");
                    return;
                }
                System.out.println("Transaction failed");
            }
        }
    }
    public int openAccount(String name, int amount){
        Account account = new Account(accountList.size()+1, name, amount);
        accountList.add(account);
        return accountList.size();
    }
    public void balance(int acc1){
       Account account =  accountList.stream().filter(acc -> acc.getAccountId()==acc1).findFirst().get();
       account.print();
    }
}
