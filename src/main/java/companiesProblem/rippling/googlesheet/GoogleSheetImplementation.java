package companiesProblem.rippling.googlesheet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoogleSheetImplementation {

    // cellMap gives O(1) average lookup by reference like A1, B2.
    // dependencies: cell -> cells it reads from.
    // dependents: cell -> cells that depend on it, used to mark formulas dirty after updates.
    private final Map<String, Cell> cellMap = new LinkedHashMap<>();

    public void set(String cellName, String value) {
        String normalizedCellName = normalizeCellName(cellName);
        String normalizedValue = value == null ? "" : value.trim();

        if (normalizedValue.isEmpty()) {
            reset(normalizedCellName);
            return;
        }

        Cell cell = getOrCreateCell(normalizedCellName);
        removeOldDependencies(cell);

        cell.rawValue = normalizedValue;
        cell.active = true;

        for (String dependency : parseDependencies(normalizedValue)) {
            cell.dependencies.add(dependency);
            getOrCreateCell(dependency).dependents.add(normalizedCellName);
        }

        markDirty(normalizedCellName, new HashSet<>());
    }

    public void reset(String cellName) {
        String normalizedCellName = normalizeCellName(cellName);
        Cell cell = getOrCreateCell(normalizedCellName);

        removeOldDependencies(cell);
        cell.rawValue = "";
        cell.cachedValue = 0;
        cell.active = false;

        markDirty(normalizedCellName, new HashSet<>());
    }

    public int get(String cellName) {
        return evaluateCell(normalizeCellName(cellName), new HashSet<>());
    }

    public void print() {
        String output = printAsString();
        if (!output.isEmpty()) {
            System.out.print(output);
        }
    }

    public String printAsString() {
        StringBuilder out = new StringBuilder();

        for (Cell cell : cellMap.values()) {
            if (!cell.active) {
                continue;
            }

            String computedValue;
            try {
                computedValue = String.valueOf(evaluateCell(cell.name, new HashSet<>()));
            } catch (RuntimeException e) {
                computedValue = "ERROR: " + e.getMessage();
            }

            out.append(cell.name)
                    .append(" -> raw: ")
                    .append(cell.rawValue)
                    .append(", computed: ")
                    .append(computedValue)
                    .append('\n');
        }

        if (out.length() == 0) {
            return "Spreadsheet is empty\n";
        }

        return out.toString();
    }

    private int evaluateCell(String cellName, Set<String> visitingCells) {
        Cell cell = getOrCreateCell(cellName);
        if (!cell.active) {
            return 0;
        }

        if (!cell.dirty) {
            return cell.cachedValue;
        }

        if (!visitingCells.add(cellName)) {
            throw new IllegalStateException("cycle detected at " + cellName);
        }

        try {
            cell.cachedValue = evaluateRawValue(cell.rawValue, visitingCells);
            cell.dirty = false;
            return cell.cachedValue;
        } finally {
            visitingCells.remove(cellName);
        }
    }

    private int evaluateRawValue(String rawValue, Set<String> visitingCells) {
        if (!rawValue.startsWith("=")) {
            return parseInteger(rawValue);
        }

        int sum = 0;
        for (String token : splitByPlus(rawValue.substring(1))) {
            sum += evaluateToken(token, visitingCells);
        }
        return sum;
    }

    private int evaluateToken(String token, Set<String> visitingCells) {
        String trimmedToken = token.trim();
        if (trimmedToken.isEmpty()) {
            throw new IllegalArgumentException("Invalid empty formula token");
        }

        if (isCellReference(trimmedToken)) {
            return evaluateCell(normalizeCellName(trimmedToken), visitingCells);
        }

        return parseInteger(trimmedToken);
    }

    private Set<String> parseDependencies(String rawValue) {
        Set<String> dependencies = new HashSet<>();
        if (!rawValue.startsWith("=")) {
            return dependencies;
        }

        for (String token : splitByPlus(rawValue.substring(1))) {
            String trimmedToken = token.trim();
            if (isCellReference(trimmedToken)) {
                dependencies.add(normalizeCellName(trimmedToken));
            }
        }

        return dependencies;
    }

    private void removeOldDependencies(Cell cell) {
        for (String dependency : cell.dependencies) {
            Cell dependencyCell = cellMap.get(dependency);
            if (dependencyCell != null) {
                dependencyCell.dependents.remove(cell.name);
            }
        }
        cell.dependencies.clear();
    }

    private void markDirty(String cellName, Set<String> visitedCells) {
        if (!visitedCells.add(cellName)) {
            return;
        }

        Cell cell = getOrCreateCell(cellName);
        cell.dirty = true;

        for (String dependent : cell.dependents) {
            markDirty(dependent, visitedCells);
        }
    }

    private Cell getOrCreateCell(String cellName) {
        return cellMap.computeIfAbsent(cellName, Cell::new);
    }

    private List<String> splitByPlus(String formula) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            if (ch == '+') {
                tokens.add(current.toString());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }

        tokens.add(current.toString());
        return tokens;
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }

    private boolean isCellReference(String value) {
        return value.matches("[A-Za-z]+[1-9][0-9]*");
    }

    private String normalizeCellName(String cellName) {
        if (cellName == null || !isCellReference(cellName.trim())) {
            throw new IllegalArgumentException("Invalid cell name: " + cellName);
        }

        return cellName.trim().toUpperCase();
    }

    private static final class Cell {
        private final String name;
        private String rawValue = "";
        private int cachedValue;
        private boolean active;
        private boolean dirty = true;
        private final Set<String> dependencies = new HashSet<>();
        private final Set<String> dependents = new HashSet<>();

        private Cell(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        GoogleSheetImplementation sheet = new GoogleSheetImplementation();

        sheet.set("A1", "10");
        sheet.set("B1", "20");
        sheet.set("C1", "=A1+B1");
        sheet.set("D1", "=C1+5");

        System.out.println("Initial sheet:");
        sheet.print();

        sheet.set("A1", "100");
        System.out.println("After updating A1:");
        sheet.print();

        sheet.reset("B1");
        System.out.println("After resetting B1:");
        sheet.print();

        sheet.set("X1", "=Y1+1");
        sheet.set("Y1", "=X1+1");
        System.out.println("Cycle example:");
        sheet.print();
    }
}
