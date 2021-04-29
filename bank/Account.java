package bank;

class Account {

    // Attributes
    private String name;
    private Integer balance;
    private Integer threshold;
    private Integer statut;

    // Constructor

    public Account(String name, Integer balance, Integer threshold) {
        this.name = name;
        this.balance = balance;
        this.threshold = threshold;
        this.statut = statut;
    }

    // Getter
    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public Integer getStatut() {return statut;}

    //Setter

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public void setStatut(Integer statut) {
        this.statut = statut;
    }

    public String toString() {

        return "";
    }
}
