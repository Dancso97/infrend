package beke.ire.entity;

public enum LoanableTypeEntity {
    cd("CD"),
    dvd("DVD"),
    book("Könyv"),
    loudbook("Hangoskönyv");

    private final String label;

    LoanableTypeEntity(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
