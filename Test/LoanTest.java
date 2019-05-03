import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private static Loan loan;
    private static Method getTm;
    private static Field duration;
    private static Field monthly;


    @BeforeAll
    public static void setup() throws NoSuchFieldException, NoSuchMethodException {
        loan = new Loan();

        duration = Loan.class.getDeclaredField("duration"); //On récupère le champ "duration dans la classe "Loan"
        duration.setAccessible(true); //Rend l'attribut accessible

        monthly = Loan.class.getDeclaredField("monthly");
        monthly.setAccessible(true);

        getTm = Loan.class.getDeclaredMethod("getTm", float.class); //Même hose pour les méthodes avec les mêmes paramètres
        getTm.setAccessible(true);


    }


    @Test
    void getTmTest() throws InvocationTargetException, IllegalAccessException {
        assertEquals(0.002f, (float) getTm.invoke(loan, 0.03f), 0.001f);
    }

    /**
     * Test monntrant le capital emprunté
     */
    @Test
    void getAmmountTest() {
        assertThrows(InvalidParameterException.class, () -> {
            loan.getAmmount(12, 0, 0.03f);
        });
        assertThrows(InvalidParameterException.class, () -> {
            loan.getAmmount(12, 500, 0f);
        });
        assertThrows(InvalidParameterException.class, () -> {
            loan.getAmmount(0, 500, 0.03f);
        });

        assertEquals(5905, loan.getAmmount(12, 500, 0.03f));
    }

    /**
     * Test montrant la durée du prêt
     */

    @Test
    void getDurationTest() {
        assertThrows(InvalidParameterException.class, () -> {
            loan.getDuration(5000, 0, 0.03f);
        });
        assertThrows(InvalidParameterException.class, () -> {
            loan.getDuration(5000, 500, 0f);
        });
        assertThrows(InvalidParameterException.class, () -> {
            loan.getDuration(5000, 0, 0.03f);
        });

        assertEquals(12, loan.getDuration(5905, 500, 0.03f));
    }

    /**
     * Test montrant la mensualité à payer
     */
    @Test
    void getMonthlyTest() {
        assertThrows(InvalidParameterException.class, () -> {
            loan.getMonthly(5000,0, 0.03f);
        });
        assertThrows(InvalidParameterException.class, () -> {
            loan.getMonthly(5000,500, 0f);
        });
        assertThrows(InvalidParameterException.class, () -> {
            loan.getMonthly(0,500, 0.03f);
        });

        assertEquals(500, loan.getMonthly(5905, 12, 0.03f));
        assertEquals(110, loan.getMonthly(2400, 24, 0.1f));
    }

    /**
     * Test montrant le coût total du prêt
     */
    @Test
    void getTotalTest() throws IllegalAccessException {

        monthly.set(loan, 600);  //On set les valeurs pour le calcul total
        duration.set(loan, 24);
        assertEquals(14400, loan.getTotal());
    }
}