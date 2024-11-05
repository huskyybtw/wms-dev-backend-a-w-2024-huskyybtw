package pl.wmsdev.wmstaskusos4j.task

import pl.wmsdev.usos4j.model.common.UsosLocalizedString
import pl.wmsdev.usos4j.model.courses.UsosCourse
import spock.lang.Specification

class UsosCoursesTaskSpec extends Specification {

    def underTest = new UsosCoursesTask();

    def "AlgorythmCoursesWithMoreThanFourEcts"() {
        expect:
        result == underTest.algorythmCoursesWithMoreThanFourEcts(courses)

        where:
        courses                                                                           || result
        [usosCourse("Test", 5)]                                                           || 0
        [usosCourse("Algorytmy", 3)]                                                      || 0
        [usosCourse("AlgorytmyD", 6)]                                                     || 1
        [usosCourse("Test", 5), usosCourse("Algorytmy", 6), usosCourse("Testt", 3)]       || 1
        [usosCourse("Algorytmy", 3), usosCourse("AlgorytmyD", 6), usosCourse("Testt", 3)] || 1
        [usosCourse("DAlgorytmy", 6), usosCourse("Test", 5), usosCourse("Algorytmy", 3)]  || 0
        [usosCourse("Test", 5), usosCourse("Testt", 3), usosCourse("Algorytmy", 6)]       || 1
    }

    def "HomePageCourses"() {
        expect:
        result == underTest.homePageCourses(courses)

        where:
        courses                                                      || result
        [usosCourse(null)]                                           || 0
        [usosCourse("test")]                                         || 1
        [usosCourse("test"), usosCourse("test"), usosCourse("test")] || 3
        [usosCourse("test"), usosCourse(null), usosCourse(null)]     || 1
        [usosCourse("")]                                             || 0
        [usosCourse(""), usosCourse("test")]                         || 1
    }

    def "SumEcts"() {
        expect:
        result == underTest.sumEcts(courses)

        where:
        courses                                       || result
        [usosCourse(5)]                               || 5
        [usosCourse(5), usosCourse(5), usosCourse(5)] || 15
        [usosCourse(5), usosCourse(5), usosCourse(5)] || 15
        [usosCourse(5), usosCourse(3), usosCourse(5)] || 13
    }

    UsosCourse usosCourse(int ects) {
        return usosCourse(null, ects, null)
    }

    UsosCourse usosCourse(String name, int ects) {
        return usosCourse(name, ects, null)
    }

    UsosCourse usosCourse(String homePageUrl) {
        return usosCourse(null, 0, homePageUrl)
    }

    UsosCourse usosCourse(String name, int ects, String homePageUrl) {
        return new UsosCourse(null, new UsosLocalizedString(name, null), homePageUrl, null, null, null, null, null, ects)
    }
}
