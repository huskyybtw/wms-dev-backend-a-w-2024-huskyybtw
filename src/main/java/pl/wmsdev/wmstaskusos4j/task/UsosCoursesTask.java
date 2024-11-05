package pl.wmsdev.wmstaskusos4j.task;

import pl.wmsdev.usos4j.model.courses.UsosCourse;

import java.util.Collection;

/**
 * Hello fellow developer!
 * We are very pleased that you decided to take part in our challenge, we hope these tasks will be easy for you,
 * and you have pure developer joy from exploring usos4j and USOS API :)
 * <p>
 * Let's imagine you are a WMS_DEV developer working on a StudentStats project
 * In StudentStats, we need to use USOS API through usos4j to fetch certain statistics about user's studies
 * In task package you will find methods that have missing implementations, will you be able to help us code the functionalities?
 * <p>
 * Library code: <a href="https://github.com/WMS-DEV/usos4j">here</a>
 * <br>
 * JavaDocs: <a href="https://javadoc.io/doc/pl.wmsdev/usos4j/latest/index.html">here</a>
 * <br>
 * If you enjoyed working on the solutions, feel free to leave a star on our usos4j project ;)
 * <p>
 * Enjoy the coding!
 */
public class UsosCoursesTask {
    /**
     * Your colleague finally managed to connect to USOS API through usos4j, but he is stuck on filtering this list of
     * crazy objects...
     * He would like to count how many courses there are, that starts with a polish name of "Algorytmy", and have
     * more than 4 ECTS. Would you be able to help him?
     */
    public int algorythmCoursesWithMoreThanFourEcts(Collection<UsosCourse> usosCourses) {
        int counter = 0;
        for(var i : usosCourses){
            if(i.ects_credits_simplified() >= 4.0 && i.name().pl().startsWith("Algorytmy")){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Cool, that was easy! But can you tell us how many courses there are, that have defined homepageUrl?
     */
    public int homePageCourses(Collection<UsosCourse> usosCourses) {
        int counter = 0;
        for(var i : usosCourses){
            if(i.homepageUrl() != null && !i.homepageUrl().isEmpty() ){
                counter++;
            }
        }
        return counter;
    }

    /**
     * And do you maybe know what is the amount of ECTS points that we can gather from all the courses passed
     * as method argument?
     */
    public int sumEcts(Collection<UsosCourse> usosCourses) {
        int sum  = 0;
        for (var i : usosCourses){
            sum = sum + Math.round(i.ects_credits_simplified());
        }
        return sum;
    }


}
