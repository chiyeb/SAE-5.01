package impulse.passerelle.domain.sorting.estates.enums;

import impulse.passerelle.domain.estates.Annonce;
import impulse.passerelle.domain.estates.elements.Id;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class SortEnumTest {

    @Test
    void fromString_shouldReturnCorrectEnumForValidString() {
        assertEquals(SortEnum.PRICE, SortEnum.fromString("prix"));
        assertEquals(SortEnum.REGISTRATION_DATE, SortEnum.fromString("date_enr"));
    }

    @Test
    void fromString_shouldReturnNullForInvalidString() {
        assertNull(SortEnum.fromString("invalid"));
    }

    @Test
    void getValue_shouldReturnCorrectStringValue() {
        assertEquals("prix", SortEnum.PRICE.getValue());
        assertEquals("date_enr", SortEnum.REGISTRATION_DATE.getValue());
    }

    @Test
    void getComparator_shouldReturnComparatorForPrice() {
        Comparator<Annonce> comparator = SortEnum.PRICE.getComparator();
        Annonce annonce1 = Annonce.getAnnonceForSortingTest(null, 100.0);
        Annonce annonce2 = Annonce.getAnnonceForSortingTest(null, 200.0);
        assertTrue(comparator.compare(annonce1, annonce2) < 0);
    }

    @Test
    void getComparator_shouldReturnComparatorForRegistrationDate() throws ParseException {
        Comparator<Annonce> comparator = SortEnum.REGISTRATION_DATE.getComparator();
        Date date1 = new GregorianCalendar(2021 + 1900, Calendar.JANUARY, 1).getTime();
        Date date2 = new GregorianCalendar(2022 + 1900, Calendar.JANUARY, 1).getTime();
        Annonce annonce1 = Annonce.getAnnonceForSortingTest(new Id(1, date1, date1, "4654"), null);
        Annonce annonce2 = Annonce.getAnnonceForSortingTest(new Id(2, date2, date2, "4654"), null);
        assertTrue(comparator.compare(annonce1, annonce2) < 0);
    }

    @Test
    void getComparator_shouldHandleNullValuesForPrice() {
        Comparator<Annonce> comparator = SortEnum.PRICE.getComparator();
        Annonce annonce1 = Annonce.getAnnonceForSortingTest(null, null);
        Annonce annonce2 = Annonce.getAnnonceForSortingTest(null, 200.0);
        assertTrue(comparator.compare(annonce1, annonce2) > 0);
    }

    @Test
    void getComparator_shouldHandleNullValuesForRegistrationDate() throws ParseException {
        Comparator<Annonce> comparator = SortEnum.REGISTRATION_DATE.getComparator();
        Date date = new GregorianCalendar(2022 + 1900, Calendar.JANUARY, 1).getTime();
        Annonce annonce1 = Annonce.getAnnonceForSortingTest(new Id(1, null, null, "4654"), null);
        Annonce annonce2 = Annonce.getAnnonceForSortingTest(new Id(2, date, date, "4654"), null);
        assertTrue(comparator.compare(annonce1, annonce2) > 0);
    }

    @Test
    void toString_shouldReturnCorrectStringValue() {
        assertEquals("prix", SortEnum.PRICE.toString());
        assertEquals("date_enr", SortEnum.REGISTRATION_DATE.toString());
    }
}