package pl.wmsdev.wmstaskusos4j.task

import pl.wmsdev.usos4j.model.grades.CountsIntoAverage
import pl.wmsdev.usos4j.model.grades.UsosGrade
import spock.lang.Specification

class UsosGradesTaskSpec extends Specification {

    def underTest = new UsosGradesTask();

    def "IsTheExamPassed"() {
        expect:
        result == underTest.isTheExamPassed(results)

        where:
        results                                             || result
        [1: grade(true)]                                    || true
        [1: grade(true), 2: grade(false), 3: grade(true)]   || true
        [1: grade(false), 2: grade(false), 3: grade(false)] || false


    }

    def "CountAverage"() {
        expect:
        result == underTest.countAverage(grades)

        where:
        grades                                                                || result
        [grade(2, true), grade(4, true)]                                      || 3
        [grade(2, true), grade(4, false)]                                     || 2
        [grade(5, true), grade(3, true), grade(3.5, true)]                    || 3.83
        [grade(5, true), grade(3, true), grade(3.5, true), grade(3.5, false)] || 3.83
    }

    def "CountAverage should throw on invalid conditions"() {
        when:
        underTest.countAverage(grades)

        then:
        thrown Exception

        where:
        grades << [[grade(2, true), grade(3.2, true)],
                   [grade(2, false), grade(4, false)],
                   [grade(5, false), grade(3, false), grade(3.5, false)],
                   [grade(5, false), grade(3, false), grade(3.2, true), grade(3.5, false)]]
    }

    UsosGrade grade(Double value, boolean counts) {
        return new UsosGrade(String.valueOf(value), null, null, null, null, counts ? CountsIntoAverage.T : CountsIntoAverage.N, null, null, null, null, null);
    }

    UsosGrade grade(boolean passed) {
        return new UsosGrade(null, passed, null, null, null, null, null, null, null, null, null)
    }
}
