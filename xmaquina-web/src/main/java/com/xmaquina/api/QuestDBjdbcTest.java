package com.xmaquina.api;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static java.time.LocalDateTime.parse;

public class QuestDBjdbcTest {
    private static final String SQL_FIND_HISTORIC_TEMP =
            "SELECT sensor_id,timestamp,temperature from leituras WHERE " +
                    "  timestamp between ? and ? ORDER by timestamp desc limit 100";
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss z");

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "admin");
        properties.setProperty("password", "x");
        //set sslmode value to 'require' if connecting to a QuestDB Cloud instance
        properties.setProperty("sslmode", "disable");

        ZonedDateTime start = ZonedDateTime.parse("2024-03-21T00:01:00 UTC", df);
        ZonedDateTime end = ZonedDateTime.parse("2024-03-21T13:02:00 UTC", df);
        System.out.println(start);
        System.out.println(end);
        final Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://xmaquina.xyz:8812/qdb", properties);
        try (PreparedStatement ps = connection.prepareStatement(
                SQL_FIND_HISTORIC_TEMP)) {
//            ps.setString(1, "1");
            ps.setTimestamp(1, Timestamp.from(start.toInstant()));
            ps.setTimestamp(2, Timestamp.from(end.toInstant()));
            try (ResultSet rs = ps.executeQuery()) {
                int idx = 1;
                while (rs.next()) {
                    System.out.println((idx++) +  " ts=" + rs.getTimestamp(2));
                }
            }
        }
        connection.close();
    }
}
