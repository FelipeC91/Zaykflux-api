package br.com.felipec91.infrastructure.web.filter;

import java.util.List;

public record TicketViewParamFilter(String groupBy, String groupByValue, List<String> columns) {}
