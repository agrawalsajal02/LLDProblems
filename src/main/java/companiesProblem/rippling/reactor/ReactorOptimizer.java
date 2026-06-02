package companiesProblem.rippling.reactor;

public class ReactorOptimizer {

    private static final double A0 = 3.0; // M
    private static final double EA1 = 6.0; // kcal/mol for A -> B
    private static final double EA2 = 8.0; // kcal/mol for A -> C
    private static final double PRE_EXP_K1 = 30.0; // 1/s
    private static final double PRE_EXP_K2 = 90.0; // 1/s
    private static final double R = 0.001987; // kcal/(mol K)

    private static final double REACTANT_COST = 50.0; // $/M of A input
    private static final double REACTOR_OPERATING_COST = 200.0; // $/minute
    private static final double PRODUCT_BENEFIT = 200.0; // $/M of B
    private static final double PURIFY_REUSE_A_COST = 5.0; // $/M of A left
    private static final double DISPOSE_C_COST = 1.0; // $/M of C

    // Search bounds. Problem statement does not specify bounds, so use a broad practical Kelvin range.
    private static final double MIN_TEMPERATURE_K = 250.0;
    private static final double MAX_TEMPERATURE_K = 2000.0;

    public static void main(String[] args) {
        Result optimum = optimize();

        System.out.printf("Optimal temperature: %.2f K%n", optimum.temperatureK);
        System.out.printf("Optimal residence time: %.2f s (%.4f min)%n",
                optimum.residenceTimeSeconds, optimum.residenceTimeSeconds / 60.0);
        System.out.printf("Final A: %.4f M%n", optimum.a);
        System.out.printf("Final B: %.4f M%n", optimum.b);
        System.out.printf("Final C: %.4f M%n", optimum.c);
        System.out.printf("Net benefit: $%.2f per M of A input%n", optimum.netBenefitPerMInput);

        System.out.println();
        System.out.println("Concentration profile at optimal conditions:");
        printConcentrationProfile(optimum.temperatureK, optimum.residenceTimeSeconds, 10);
    }

    private static Result optimize() {
        double left = MIN_TEMPERATURE_K;
        double right = MAX_TEMPERATURE_K;

        for (int i = 0; i < 200; i++) {
            double mid1 = left + (right - left) / 3.0;
            double mid2 = right - (right - left) / 3.0;

            Result result1 = bestResultForTemperature(mid1);
            Result result2 = bestResultForTemperature(mid2);

            if (result1.netBenefitPerMInput < result2.netBenefitPerMInput) {
                left = mid1;
            } else {
                right = mid2;
            }
        }

        return bestResultForTemperature((left + right) / 2.0);
    }

    private static Result bestResultForTemperature(double temperatureK) {
        double residenceTimeSeconds = bestResidenceTimeForTemperature(temperatureK);
        return evaluate(temperatureK, residenceTimeSeconds);
    }

    private static double bestResidenceTimeForTemperature(double temperatureK) {
        double kB = rateConstantForB(temperatureK);
        double kC = rateConstantForC(temperatureK);
        double totalRate = kB + kC;

        if (totalRate == 0.0) {
            return 0.0;
        }

        double selectivityToB = kB / totalRate;
        double selectivityToC = kC / totalRate;

        double valueGainedPerMReacted =
                PRODUCT_BENEFIT * selectivityToB
                        - DISPOSE_C_COST * selectivityToC
                        + PURIFY_REUSE_A_COST;

        if (valueGainedPerMReacted <= 0.0) {
            return 0.0;
        }

        double unreactedFractionAtOptimum =
                REACTOR_OPERATING_COST / (60.0 * totalRate * A0 * valueGainedPerMReacted);

        if (unreactedFractionAtOptimum >= 1.0) {
            return 0.0;
        }

        return -Math.log(unreactedFractionAtOptimum) / totalRate;
    }

    private static Result evaluate(double temperatureK, double residenceTimeSeconds) {
        double kB = rateConstantForB(temperatureK);
        double kC = rateConstantForC(temperatureK);
        double totalRate = kB + kC;

        double a;
        double b;
        double c;

        if (totalRate == 0.0) {
            a = A0;
            b = 0.0;
            c = 0.0;
        } else {
            double unreactedFraction = Math.exp(-totalRate * residenceTimeSeconds);
            double reactedAmount = A0 * (1.0 - unreactedFraction);

            a = A0 * unreactedFraction;
            b = reactedAmount * kB / totalRate;
            c = reactedAmount * kC / totalRate;
        }

        double totalBenefit = PRODUCT_BENEFIT * b;
        double totalCost =
                REACTANT_COST * A0
                        + REACTOR_OPERATING_COST * (residenceTimeSeconds / 60.0)
                        + PURIFY_REUSE_A_COST * a
                        + DISPOSE_C_COST * c;

        double netBenefitPerMInput = (totalBenefit - totalCost) / A0;
        return new Result(temperatureK, residenceTimeSeconds, a, b, c, netBenefitPerMInput);
    }

    private static double rateConstantForB(double temperatureK) {
        return PRE_EXP_K1 * Math.exp(-EA1 / (R * temperatureK));
    }

    private static double rateConstantForC(double temperatureK) {
        return PRE_EXP_K2 * Math.exp(-EA2 / (R * temperatureK));
    }

    private static void printConcentrationProfile(double temperatureK, double finalTimeSeconds, int points) {
        System.out.println("time_s,A_M,B_M,C_M");

        for (int i = 0; i <= points; i++) {
            double time = finalTimeSeconds * i / points;
            Result result = evaluate(temperatureK, time);
            System.out.printf("%.4f,%.4f,%.4f,%.4f%n", time, result.a, result.b, result.c);
        }
    }

    private static final class Result {
        private final double temperatureK;
        private final double residenceTimeSeconds;
        private final double a;
        private final double b;
        private final double c;
        private final double netBenefitPerMInput;

        private Result(double temperatureK, double residenceTimeSeconds,
                       double a, double b, double c, double netBenefitPerMInput) {
            this.temperatureK = temperatureK;
            this.residenceTimeSeconds = residenceTimeSeconds;
            this.a = a;
            this.b = b;
            this.c = c;
            this.netBenefitPerMInput = netBenefitPerMInput;
        }
    }
}
