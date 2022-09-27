package com.onebeyond.kata2022;

import com.google.common.collect.Lists;
import de.vandermeer.asciitable.AsciiTable;
import org.apache.commons.lang3.tuple.Pair;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class Kata2022 {

    private static final int NUMBER_OF_EXECUTIONS = 1000;

    private static final String INPUT = ".-- .... .- -   .... .- - ....   --. --- -..   .-- .-. --- ..- --. .... -";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Kata2022 kata2022 = new Kata2022();
        kata2022.executeForAllTeamsAndPrintResults();
    }

    private void executeForAllTeamsAndPrintResults() throws InstantiationException, IllegalAccessException {
        List<Map.Entry<String, Pair<String, Long>>> timeAndSolutionPerTeamSorted = executeForAllTeamsAndGetResultsSortedByAscDuration();
        Long fastestTime = timeAndSolutionPerTeamSorted.stream().filter(it -> !it.getValue().getRight().equals(-1L)).findFirst().map(it -> it.getValue().getRight()).orElseThrow(() -> new RuntimeException("No implementations with correct result"));
        List<List<String>> table = createTableToBePrintedWithValues(timeAndSolutionPerTeamSorted, fastestTime);
        System.out.println();
        System.out.println("These are the results for " + NUMBER_OF_EXECUTIONS + " executions");
        System.out.println();
        printTableWithValues(table);
    }

    private List<List<String>> createTableToBePrintedWithValues(List<Map.Entry<String, Pair<String, Long>>> timeAndSolutionPerTeamSorted, Long fastestTime) {
        List<List<String>> table = new ArrayList<>();
        table.add (Lists.newArrayList("Team", "Nanoseconds", "Difference with fastest", "Result"));
        for (Map.Entry<String, Pair<String, Long>> item : timeAndSolutionPerTeamSorted) {
            table.add(Lists.newArrayList(item.getKey(), String.valueOf(item.getValue().getRight()), String.valueOf(item.getValue().getRight() - fastestTime), item.getValue().getLeft()));
        }
        return table;
    }

    private void printTableWithValues(List<List<String>> table) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        for (List<String> row : table) {
            at.addRow(row);
            at.addRule();
        }
        System.out.println(at.render());
    }

    private List<Map.Entry<String, Pair<String, Long>>> executeForAllTeamsAndGetResultsSortedByAscDuration() throws InstantiationException, IllegalAccessException {
        return executeForAllTeamsAndGetResults()
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(l -> l.getValue().getRight())).collect(Collectors.toList());
    }

    private Map<String, Pair<String, Long>> executeForAllTeamsAndGetResults() throws InstantiationException, IllegalAccessException {
        Map<String, Pair<String, Long>> durationsPerTeam = new HashMap<>();
        Set<MorseDecoder> implementations = getSolutionImplementations();
        for (int i = 0; i < NUMBER_OF_EXECUTIONS; i++) {
            for (MorseDecoder implementation : implementations) {
                String teamsName = implementation.getClass().getSimpleName();
                try {
                    long startTime = System.nanoTime();
                    String result = implementation.decodeMisteryMessage(INPUT);
                    long endTime = System.nanoTime();
                    long duration = endTime - startTime;
                    Pair<String, Long> pairForTeam = durationsPerTeam.computeIfAbsent(teamsName, k -> Pair.of(result, 0L));
                    pairForTeam = ensurePreviousResultWasTheSameAsCurrentOne(durationsPerTeam, teamsName, result, pairForTeam);
                    pairForTeam = addNewExecutionTimeOnlyIfNotInFailedState(durationsPerTeam, teamsName, result, duration, pairForTeam);
                    removeFromValidImplementationsIfFailed(implementations, teamsName, pairForTeam);
                } catch (RuntimeException exception) {
                    System.err.println(exception);
                    durationsPerTeam.put(teamsName, Pair.of("Exception during execution", -1L));
                }
            }
        }
        return durationsPerTeam;
    }

    private void removeFromValidImplementationsIfFailed(Set<MorseDecoder> implementations, String teamsName, Pair<String, Long> pairForTeam) {
        if (pairForTeam.getValue().equals(-1L)) {
            implementations.removeIf(it -> it.getClass().getSimpleName().equals(teamsName));
        }
    }

    private Pair<String, Long> addNewExecutionTimeOnlyIfNotInFailedState(Map<String, Pair<String, Long>> durationsPerTeam, String teamsName, String result, long duration, Pair<String, Long> pairForTeam) {
        if (!pairForTeam.getValue().equals(-1L)) {
            pairForTeam = Pair.of(result, pairForTeam.getValue() + duration);
            durationsPerTeam.put(teamsName, pairForTeam);
        }
        return pairForTeam;
    }

    private Pair<String, Long> ensurePreviousResultWasTheSameAsCurrentOne(Map<String, Pair<String, Long>> durationsPerTeam, String teamsName, String result, Pair<String, Long> pairForTeam) {
        if (!result.equals(pairForTeam.getLeft())) {
            String errorMessage = "Different results in different runs. Before: " + pairForTeam.getLeft() + ". Now: " + result;
            pairForTeam = Pair.of(errorMessage, -1L);
            durationsPerTeam.put(teamsName, pairForTeam);
            System.err.println("Team " + teamsName + "Error: " + errorMessage);
        }
        return pairForTeam;
    }

    private Set<MorseDecoder> getSolutionImplementations() throws InstantiationException, IllegalAccessException {
        Set<MorseDecoder> implementations = new HashSet<>();
        Reflections reflections = new Reflections("com.onebeyond.kata2022");
        for (Class<? extends MorseDecoder> solutionClass : reflections.getSubTypesOf(MorseDecoder.class)) {
            implementations.add(solutionClass.newInstance());
        }
        return implementations;
    }

}
