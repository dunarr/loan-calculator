import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {


    /**
     * Test monntrant le capital emprunté
     */
    @Test
    void getAmmountTest() {
        Loan ammount = new Loan();
       assertEquals(2,300,3);
    }

    /**
     * Test montrant la durée du prêt
     */

    @Test
    void getDurationTest() {
        Loan duration = new Loan();
//        assertEquals();
    }

    /**
     * Test montrant la mensualité à payer
     */
    @Test
    void getMonthlyTest() {
        Loan monthly = new Loan();
//        assertEquals();
    }

    /**
     * Test montrant le coût total du prêt
     */
    @Test
    void getTotalTest() {
        Loan total = new Loan();
//        assertEquals();
    }
}