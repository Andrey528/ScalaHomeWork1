package org.example
import scala.io.StdIn.readLine
/**
 * @author ${user.name}
 */
object App {
  def main(args : Array[String]) {
    /** Не собирается в jar:
     * Failed to execute goal net.alchim31.maven:scala-maven-plugin:3.3.2:testCompile (default) on project
     * гугл говорит, что дело в JDK  версии, но у меня она итак 8я, дело скорее всего в версии maven.
     * В шаблоне она 2.12, у меня 3.3.2. Я не смог устранить ошибку, но проект компилируется через Idea
     * Иными словами - компилируется, но jar не собирает при использовании шаблона проекта net.alchim31.maven ArtifactId: scala-archetype-simple
     * НО, при использовании любого другого шаблона от apache в idea - все собирается в jar*/

      /** Напишите программу, которая:
       *
       * i.     выводит фразу «Hello, Scala!» справа налево
       *
       * ii.     переводит всю фразу в нижний регистр
       *
       * iii.     удаляет символ!
       *
       * iv.     добавляет в конец фразы «and goodbye python!»*/

//    var hello_string = "Hello Scala!";
//
//    println( hello_string.reverse );
//    println( hello_string.toLowerCase() );
//    println( hello_string.replaceAll("!", "") );
//    println( hello_string.replaceAll("!", " ").concat("and goodbye python!") );

    /** Напишите программу, которая вычисляет ежемесячный оклад сотрудника после вычета налогов.
     * На вход вашей программе подается значение годового дохода до вычета налогов, размер премии –
     * в процентах от годового дохода и компенсация питания. */

    println("Enter year income before tax:");
    val year_income_before_tax = readLine().toInt;
    println("Bonus count:");
    val bonus = readLine().toFloat;
    println("Food compensation:");
    val food_compensation = readLine().toInt;

    var salary = (year_income_before_tax * bonus + food_compensation + year_income_before_tax) * 0.87 / 12;
    println("Salary size: " + salary);

    /** Напишите программу, которая рассчитывает для каждого сотрудника отклонение(в процентах) от среднего значения
     * оклада на уровень всего отдела. В итоговом значении должно учитываться в большую или меньшую сторону отклоняется
     * размер оклада. На вход вышей программе подаются все значения, аналогичные предыдущей программе, а также список
     * со значениями окладов сотрудников отдела 100, 150, 200, 80, 120, 75. */

    var salary_list = List(100, 150, 200, 80, 120, 75);
    var sum_std = 0;
    val mean_salary = salary_list.sum / salary_list.size;

    for (item <- salary_list) {
      val std = (mean_salary - item) * 100 / item;
      sum_std = sum_std + std;
      print(std + ", ");
    }

    val mean_std = sum_std / salary_list.size;

    /** Попробуйте рассчитать новую зарплату сотрудника, добавив(или отняв, если сотрудник плохо себя вел) необходимую
     * сумму с учетом результатов прошлого задания. Добавьте его зарплату в список и вычислите значение самой высокой
     * зарплаты и самой низкой. */

    println("Mean std is: " + mean_std);
    val new_salary = salary * (mean_std + 100) / 100;
    salary_list = salary_list :+ new_salary.toInt;
    val max_salary = salary_list.max;
    val min_salary = salary_list.min;

    println("New salary list is: " + salary_list);
    println("Max salary is: " + max_salary);
    println("Min salary is: " + min_salary);

    /** Также в вашу команду пришли два специалиста с окладами 350 и 90 тысяч рублей. Попробуйте отсортировать список
     * сотрудников по уровню оклада от меньшего к большему. */

    val salary_new_employees = List(350, 90);
    salary_list = salary_list ++ salary_new_employees;
    salary_list = salary_list.sorted;
    println("Sorted list: " + salary_list);

    /** Кажется, вы взяли в вашу команду еще одного сотрудника и предложили ему оклад 130 тысяч. Вычислите
     * самостоятельно номер сотрудника в списке так, чтобы сортировка не нарушилась и добавьте его на это место. */

    val salary_new_employee : Int = 130;
    var index = 0;
//    var head = List[Int]();
//    var tail = List[Int]();
//
//    for (i <- 0 to salary_list.size-1) {
//      if (salary_list(i) <= salary_new_employee) {
//        head = head :+ salary_list(i);
//      };
//      else {
//        tail = tail :+ salary_list(i);
//      };
//    };
//
//    println("Salary list before: " + salary_list);
//
//    salary_list = head :+ salary_new_employee;
//    salary_list = salary_list ++ tail;
//
//    println("Salary list after: " + salary_list);

    for (i <- 0 to salary_list.size-1) {
      if (salary_list(i) <= salary_new_employee) {
        index = i;
      };
    };

    println("Salary list before: " + salary_list);
    salary_list = salary_list.take(index+1) ++ List(salary_new_employee) ++ salary_list.drop(index+1);
    println("Salary list after: " + salary_list);

    /** Попробуйте вывести номера сотрудников из полученного списка, которые попадают под категорию middle.
     * На входе программе подается «вилка» зарплаты специалистов уровня middle. */

    for (item <- salary_list) {
      if ((item >= 120) && (item <= 200)) println(f"If salary = $item you are middle");
    };

    /** Однако наступил кризис и ваши сотрудники требуют повысить зарплату. Вам необходимо проиндексировать
     * зарплату каждого сотрудника на уровень инфляции – 7% */

    var new_salary_list = List[Double]();
    val inflation = 0.07;

    for (item <- salary_list) {
      new_salary_list = new_salary_list :+ (item * (100 + inflation) / 100);
    };
    println("Salary list after indexing: " + new_salary_list);
  }

}
