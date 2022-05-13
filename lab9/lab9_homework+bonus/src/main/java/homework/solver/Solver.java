package homework.solver;

import homework.repository.CitiesRepository;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

/**
 * Solver that selects the cities that have:
 * - the first same letter
 * - population sum between 2 given bounds
 * - different countries
 */
public class Solver {
    public static void solve(CitiesRepository citiesRepository) {

        Model model = new Model("solver");
        int[] ids = citiesRepository.getAllIds();

        IntVar id1 = model.intVar("id1", ids);
        IntVar id2 = model.intVar("id2", ids);

        IntVar char1 = model.intVar("char1", citiesRepository.findById(id1.getValue()).getName().charAt(0));
        IntVar char2 = model.intVar("char2", citiesRepository.findById(id2.getValue()).getName().charAt(0));
        int population1 = citiesRepository.findById(id1.getValue()).getPopulation();
        int population2 = citiesRepository.findById(id2.getValue()).getPopulation();
        IntVar sum = model.intVar("sum", population1 + population2);
        IntVar country1 = model.intVar("country1", citiesRepository.findById(id1.getValue()).getIdCountry());
        IntVar country2 = model.intVar("country2", citiesRepository.findById(id2.getValue()).getIdCountry());

        model.arithm(char1, "=", char2).post();
        model.arithm(sum, ">=", 20).post();
        model.arithm(sum, "<=", 100).post();
        model.arithm(country1, "!=", country2).post();

        int count = 1;
        while (model.getSolver().solve()) {

            System.out.println("Solution " + count++ + ": " + id1 + ", " + id2);
            //System.out.println(id1.getValue() + ", " + id2.getValue() + "\n" + char1.getValue() + " = " + char2.getValue()
            //        + "\n" + sum.getValue() + " = " + population1 + " + " + population2 + "\n" + country1.getValue() + " != " + country2.getValue());

            char1 = model.intVar(citiesRepository.findById(id1.getValue()).getName().charAt(0));
            char2 = model.intVar(citiesRepository.findById(id2.getValue()).getName().charAt(0));
            population1 = citiesRepository.findById(id1.getValue()).getPopulation();
            population2 = citiesRepository.findById(id2.getValue()).getPopulation();
            sum = model.intVar(population1 + population2);
            country1 = model.intVar(citiesRepository.findById(id1.getValue()).getIdCountry());
            country2 = model.intVar(citiesRepository.findById(id2.getValue()).getIdCountry());

            model.arithm(char1, "=", char2).post();
            model.arithm(sum, ">=", 20).post();
            model.arithm(sum, "<=", 100).post();
            model.arithm(country1, "!=", country2).post();
        }
    }
}
