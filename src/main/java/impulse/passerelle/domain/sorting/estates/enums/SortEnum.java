package impulse.passerelle.domain.sorting.estates.enums;

import impulse.passerelle.domain.estates.Annonce;

import java.util.Comparator;

public enum SortEnum {
    PRICE("prix"),
    REGISTRATION_DATE("date_enr");

    private final String value;

    SortEnum(String value) {
        this.value = value;
    }

    public static SortEnum fromString(String sort) {
        return switch (sort) {
            case "prix" -> PRICE;
            case "date_enr" -> REGISTRATION_DATE;
            default -> null;
        };
    }

    public String getValue() {
        return value;
    }

    public Comparator<Annonce> getComparator() {
        return switch (this) {
            case SortEnum.PRICE ->
                    Comparator.comparing(Annonce::getPrix, Comparator.nullsLast(Comparator.naturalOrder()));
            case SortEnum.REGISTRATION_DATE ->
                    Comparator.comparing(Annonce::getDateEnr, Comparator.nullsLast(Comparator.naturalOrder()));
            default -> throw new IllegalArgumentException("Critère de tri non pris en charge : " + value);
        };
    }

    @Override
    public String toString() {
        return value;
    }
}
