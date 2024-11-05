package pl.wmsdev.wmstaskusos4j.task;

import pl.wmsdev.usos4j.model.grades.CountsIntoAverage;
import pl.wmsdev.usos4j.model.grades.UsosGrade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Map;

/**
 * In this task we will explore more the 'grades' module from USOS API with usos4j, hope you can help our poor
 * Java developer solve some algorithmic problems :)
 */
public class UsosGradesTask {


    /**
     * Your colleague would like to have a method, that will tell him if the user passed an exam, will you be
     * able to help him?
     * <p>
     * The passed argument is a Map of Integer and UsosGrade, where Integer means a session number.
     * For example student could fail an exam during first two sessions, but pass it in the third one
     */
    public boolean isTheExamPassed(Map<Integer, UsosGrade> examResults) {
        for(var k : examResults.values()){
            if(k.passes()){
                return true;
            }
        }
        return false;
    }

    /**
     * Your colleague managed to fetch all the grades of USOS user, and now wants to calculate the average
     * of all the given marks. In your implementation, make sure that the grade counts into average.
     * You don't need to consider if the grade makes the user pass the exam.
     * Round the average up to 2 decimal places.
     * <p>
     * If you are not able to calculate the result, throw any exception.
     * Throw any exception also if the value of the mark is invalid according to academic scale (for example 1.5, 6.0, 0.5)
     */
    public BigDecimal countAverage(Collection<UsosGrade> usosGrades) {
        BigDecimal avg = BigDecimal.ZERO;
        int counted = 0;

        for (var grade : usosGrades){
            if(grade.countsIntoAverage() == CountsIntoAverage.T){
                BigDecimal value = new BigDecimal(grade.valueSymbol());

                if (    value.compareTo(BigDecimal.ZERO) < 0 ||
                        value.compareTo(new BigDecimal("6.0")) > 0 ||
                        value.scale() > 1 ||
                        (value.scale() == 1
                                && value.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0
                                && value.remainder(new BigDecimal("0.5")).compareTo(BigDecimal.ZERO) != 0)) {
                    throw new IllegalArgumentException();
                }

                avg = avg.add(value);
                counted++;
            }
        }
        try {
            return avg.divide(new BigDecimal(counted), 2, RoundingMode.HALF_UP);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
