package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final String SPLIT_REGEX = "\\s+";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);
        int[] totals = new int[names.length];

        for (String dataRecord : data) {

            if (dataRecord == null || dataRecord.isBlank()) {
                continue;
            }
            String[] parts = dataRecord.trim().split(SPLIT_REGEX);
            String date = parts[DATE_INDEX];
            String nameOfEmployee = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int moneyPerHour = Integer.parseInt(parts[MONEY_PER_HOUR_INDEX]);
            int earned = hours * moneyPerHour;

            LocalDate current = LocalDate.parse(date, DATE_FORMATTER);

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
