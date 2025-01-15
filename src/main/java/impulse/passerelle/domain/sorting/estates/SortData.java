package impulse.passerelle.domain.sorting.estates;

import impulse.passerelle.domain.estates.Annonce;
import impulse.passerelle.domain.sorting.estates.enums.SortEnum;

import java.util.Comparator;

public class SortData {
    private final SortEnum value;
    private final boolean ascending;

    public SortData(String value, boolean ascending) {
        this.value = SortEnum.fromString(value);
        this.ascending = ascending;
    }

    public SortEnum getValue() {
        return value;
    }

    public boolean isAscending() {
        return ascending;
    }

    public Comparator<Annonce> getComparator() {
        Comparator<Annonce> comparator = value.getComparator();
        if (ascending) {
            return comparator;
        } else {
            return comparator.reversed();
        }
    }
}
