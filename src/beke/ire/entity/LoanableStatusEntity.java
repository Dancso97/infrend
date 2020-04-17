package beke.ire.entity;

public enum LoanableStatusEntity {
    loanable("Kölcsönözhető"),
    borrowed("Kikölcsönzött"),
    scrapped("Selejtezett");

    private final String label;

    LoanableStatusEntity(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
