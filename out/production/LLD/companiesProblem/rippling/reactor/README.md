# Reactor Optimizer

## Intuition

Reaction parallel hai:

- `A -> B` desired product
- `A -> C` unwanted side product

Temperature badhane se reaction fast hoti hai, lekin C ka activation energy zyada hai, to high temperature par C ka ratio worse hota hai.

Goal:

- Temperature `T`
- Residence time `t_res`

aise choose karna ki net benefit maximum ho.

Important simplification:

Both reactions first-order in A hain. Isliye ODE solver ki zarurat nahi. Closed form use kar sakte hain:

- `A(t) = A0 * exp(-(kB + kC) * t)`
- reacted A B/C me split hota hai based on `kB / (kB + kC)` and `kC / (kB + kC)`.

For each temperature, best residence time formula se nikal sakta hai. Fir temperature par ternary search karte hain.

## Classes

- `ReactorOptimizer`: constants, optimization, evaluation, and output.
- `Result`: ek condition ka output: T, time, A, B, C, net benefit.

## Functions

- `optimize()`: best temperature search karta hai. Time: O(I), where I = fixed ternary-search iterations, practically O(1).
- `bestResultForTemperature(...)`: given T ke liye best residence time and result nikalta hai. Time: O(1).
- `bestResidenceTimeForTemperature(...)`: economic optimum residence time calculate karta hai. Time: O(1).
- `evaluate(...)`: given T and time par A, B, C, net benefit calculate karta hai. Time: O(1).
- `rateConstantForB(...)`: Arrhenius equation se B reaction rate. Time: O(1).
- `rateConstantForC(...)`: Arrhenius equation se C reaction rate. Time: O(1).
- `printConcentrationProfile(...)`: follow-up plot ke liye CSV-style time profile print karta hai. Time: O(P), where P = number of printed points.

## Complexity

- Optimization fixed iterations ka hai, so practically O(1).
- Concentration profile printing O(P), jahan P number of points hai.
