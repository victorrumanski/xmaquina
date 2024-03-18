package com.xmaquina.api;

import io.questdb.client.Sender;

import java.time.Instant;

public class QuestDBSendInfluxLineProtocolSample {
    public static void main(String[] args) {
        try (Sender sender = Sender.builder().address("172.235.141.8:9009").build()) {
            sender.table("inventors")
                    .symbol("born", "Austrian Empire")
                    .timestampColumn("birthday", Instant.parse("1856-07-10T00:00:00.00Z").toEpochMilli())
                    .longColumn("id", 0)
                    .stringColumn("name", "Nicola Tesla")
                    .at(System.nanoTime());
            sender.table("inventors")
                    .symbol("born", "USA")
                    .timestampColumn("birthday", Instant.parse("1847-02-11T00:00:00.00Z").toEpochMilli())
                    .longColumn("id", 1)
                    .stringColumn("name", "Thomas Alva Edison")
                    .at(System.nanoTime());
        }
    }
}
