package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        int[] totals = new int[names.length];

        for (String d : data) {

            if (d == null || d.isBlank()) {
                continue;
            }
            String[] parts = d.trim().split("\\s+");
            String date = parts[0];
            String nameOfEmployee = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int moneyPerHour = Integer.parseInt(parts[3]);
            int earned = hours * moneyPerHour;

            LocalDate current = LocalDate.parse(date, formatter);

            if (current.isBefore(from) || current.isAfter(to)) {
                continue;
            }

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(nameOfEmployee)) {
                    totals[i] += earned;
                    break;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i]).append(" - ").append(totals[i]);
            if (i < names.length - 1) {
                builder.append(System.lineSeparator());
            }
        }

        return builder.toString();
    }
}
