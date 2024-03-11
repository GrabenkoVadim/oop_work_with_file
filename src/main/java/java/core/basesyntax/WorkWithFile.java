package java.core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int sumOfBuys = 0;
        int sumOfSupplays = 0;
        String[] strings = readFromFile(fromFileName);
        for (String string : strings) {
            String[] splitData = string.split(",");
            switch (splitData[0]) {
                case "supply":
                    sumOfSupplays += Integer.parseInt(splitData[1]);
                    break;
                case "buy":
                    sumOfBuys += Integer.parseInt(splitData[1]);
                    break;
                default:
                    throw new RuntimeException("Empty file");
            }
        }
        String report = makeReport(sumOfBuys, sumOfSupplays);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String string = bufferedReader.readLine();
            while (string != null) {
                stringBuilder.append(string).append(System.lineSeparator());
                string = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file from file", e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }
    private String makeReport(int buySum, int supplySum) {
        return "supply" + "," +
                supplySum + System.lineSeparator() +
                "buy" + "," +
                buySum + System.lineSeparator() +
                "result" + "," +
                (supplySum - buySum) + System.lineSeparator();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
    }
}
