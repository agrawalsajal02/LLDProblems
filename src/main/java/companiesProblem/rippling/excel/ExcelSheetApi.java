package companiesProblem.rippling.excel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelSheetApi {

    // cellMap: cellName -> Cell, gives O(1) average lookup/update/reset by references like A1, B2.
    private final Map<String, Cell> cellMap = new LinkedHashMap<>();

    public void set(String cellName, String value) {
        String normalizedCellName = normalizeCellName(cellName);
        String normalizedValue = value == null ? "" : value.trim();

        if (normalizedValue.isEmpty()) {
            reset(normalizedCellName);
            return;
        }

        cellMap.put(normalizedCellName, new Cell(normalizedCellName, normalizedValue));
    }

    public void reset(String cellName) {
        cellMap.remove(normalizeCellName(cellName));
    }

    public int getComputedValue(String cellName) {
        return evaluateCell(normalizeCellName(cellName), new HashSet<>());
    }

    public void print() {
        if (cellMap.isEmpty()) {
            System.out.println("Spreadsheet is empty");
            return;
        }

        for (Cell cell : cellMap.values()) {
            String computedValue;
            try {
                computedValue = String.valueOf(evaluateCell(cell.name, new HashSet<>()));
            } catch (RuntimeException e) {
                computedValue = "ERROR: " + e.getMessage();
            }

            System.out.println(cell.name + " -> raw: " + cell.rawValue + ", computed: " + computedValue);
        }
    }

    public String printAsString() {
        if (cellMap.isEmpty()) {
            return "Spreadsheet is empty";
        }

        StringBuilder out = new StringBuilder();
        for (Cell cell : cellMap.values()) {
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

        return out.toString();
    }

    private int evaluateCell(String cellName, Set<String> visitingCells) {
        Cell cell = cellMap.get(cellName);
        if (cell == null) {
            return 0;
        }

        if (!visitingCells.add(cellName)) {
            throw new IllegalStateException("cycle found at " + cellName);
        }

        int value = evaluateRawValue(cell.rawValue, visitingCells);
        visitingCells.remove(cellName);
        return value;
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
        private final String rawValue;

        private Cell(String name, String rawValue) {
            this.name = name;
            this.rawValue = rawValue;
        }
    }

    public static void main(String[] args) {
        ExcelSheetApi sheet = new ExcelSheetApi();

        sheet.set("A1", "10");
        sheet.set("A2", "-5");
        sheet.set("B1", "=9+10");
        sheet.set("B2", "=-1+-10+2");
        sheet.set("C1", "=A1+10");
        sheet.set("C2", "=A1+B1");

        System.out.println("Initial sheet:");
        sheet.print();

        sheet.set("A1", "100");
        System.out.println("After updating A1:");
        sheet.print();

        sheet.reset("B2");
        System.out.println("After resetting B2:");
        sheet.print();
    }
}
