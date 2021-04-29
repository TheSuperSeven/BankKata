package bank;

class Account {

    // Attributes
    private String name;
    private Integer balance;
    private Integer threshold;
    private String statut;

    // Constructor

    public Account(String name, Integer balance, Integer threshold, String statut) {
        this.name = name;
        this.balance = balance;
        this.threshold = threshold;
        this.statut = this.statut;
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

    public String getStatut() {return statut;}

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

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String toString() {
        String statut2 = "";
        if (this.statut.equals("f"))
            statut2 = "false";
        if (this.statut.equals("t"))
            statut2 = "true";
        return ""+name+" | "+balance+" | "+threshold+" | "+statut2+"\n";
    }
}
