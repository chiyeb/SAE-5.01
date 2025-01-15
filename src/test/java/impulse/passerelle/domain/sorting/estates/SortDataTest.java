package impulse.passerelle.domain.sorting.estates;

import impulse.passerelle.domain.estates.Annonce;
import impulse.passerelle.domain.estates.elements.Id;
import impulse.passerelle.domain.sorting.estates.enums.SortEnum;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortDataTest {

    @Test
    void getValue_shouldReturnCorrectSortEnum() {
        SortData sortData = new SortData("prix", true);
        assertEquals(SortEnum.PRICE, sortData.getValue());
    }

    @Test
    void getValue_shouldReturnNullForInvalidSortEnum() {
        SortData sortData = new SortData("invalid", true);
        assertNull(sortData.getValue());
    }

    @Test
    void isAscending_shouldReturnTrueWhenAscending() {
        SortData sortData = new SortData("prix", true);
        assertTrue(sortData.isAscending());
    }

    @Test
    void isAscending_shouldReturnFalseWhenDescending() {
        SortData sortData = new SortData("prix", false);
        assertFalse(sortData.isAscending());
    }

    @Test
    void getComparator_shouldReturnComparatorForPriceAscending() {
        SortData sortData = new SortData("prix", true);
        Comparator<Annonce> comparator = sortData.getComparator();
        Annonce annonce1 = Annonce.getAnnonceForSortingTest(null, 100.0);
        Annonce annonce2 = Annonce.getAnnonceForSortingTest(null, 200.0);
        assertTrue(comparator.compare(annonce1, annonce2) < 0);
    }

    @Test
    void getComparator_shouldReturnComparatorForPriceDescending() {
        SortData sortData = new SortData("prix", false);
        Comparator<Annonce> comparator = sortData.getComparator();
        Annonce annonce1 = Annonce.getAnnonceForSortingTest(null, 100.0);
        Annonce annonce2 = Annonce.getAnnonceForSortingTest(null, 200.0);
        assertTrue(comparator.compare(annonce1, annonce2) > 0);
    }

    @Test
    void getComparator_shouldReturnComparatorForRegistrationDateAscending() throws ParseException {
        SortData sortData = new SortData("date_enr", true);
        Comparator<Annonce> comparator = sortData.getComparator();
        Date date1 = new GregorianCalendar(2021 + 1900, Calendar.JANUARY, 1).getTime();
        Date date2 = new GregorianCalendar(2022 + 1900, Calendar.JANUARY, 1).getTime();
        Annonce annonce1 = Annonce.getAnnonceForSortingTest(new Id(1,date1,date1, "4654"), null);
        Annonce annonce2 = Annonce.getAnnonceForSortingTest(new Id(2,date2,date2, "4654"), null);
        assertTrue(comparator.compare(annonce1, annonce2) < 0);
    }

    @Test
    void getComparator_shouldReturnComparatorForRegistrationDateDescending() throws ParseException {
        SortData sortData = new SortData("date_enr", true);
        Comparator<Annonce> comparator = sortData.getComparator();
        Date date1 = new GregorianCalendar(2022 + 1900, Calendar.JANUARY, 1).getTime();
        Date date2 = new GregorianCalendar(2021 + 1900, Calendar.JANUARY, 1).getTime();
        Annonce annonce1 = Annonce.getAnnonceForSortingTest(new Id(1,date1,date1, "4654"), null);
        Annonce annonce2 = Annonce.getAnnonceForSortingTest(new Id(2,date2,date2, "4654"), null);
        assertTrue(comparator.compare(annonce1, annonce2) > 0);

    }
}
